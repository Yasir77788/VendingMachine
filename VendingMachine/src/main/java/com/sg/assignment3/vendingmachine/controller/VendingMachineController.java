package com.sg.assignment3.vendingmachine.controller;

import com.sg.assignment3.vendingmachine.dao.*;
import com.sg.assignment3.vendingmachine.dto.Item;
import com.sg.assignment3.vendingmachine.service.InsufficientFundException;
import com.sg.assignment3.vendingmachine.service.NoItemInventoryException;
import com.sg.assignment3.vendingmachine.service.VendingMachineService;
import com.sg.assignment3.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.assignment3.vendingmachine.view.UserIO;
import com.sg.assignment3.vendingmachine.view.UserIoConsoleImpl;
import com.sg.assignment3.vendingmachine.view.VendingMachineView;

import java.util.List;
import java.util.Map;

// controller class to coordinate communication between dao and view
public class VendingMachineController {

    // declare references and variables to be used by the controller
    // to access model and view implementations
    private VendingMachineView view;
    private UserIO io;
    VendingMachineDao myDao;
    VendingMachineAuditDao myAuditDao;
    private VendingMachineService service;

    private int moneyInput;
    private int selection; // user selection
    private static boolean isOutOfStock = false;

    // No-rg constructor for the controller.
    // It instantiates controller reference objects
    public VendingMachineController() {
        myDao = new VendingMachineDaoFileImpl();
        myAuditDao = new VendingMachineAuditDaoFileImpl();
        this.service = new VendingMachineServiceImpl(myDao, myAuditDao);
        this.io = new UserIoConsoleImpl();
        this.view = new VendingMachineView(io);
    }

   // run() method processes the user inputs
    public void run() throws VendingMachinePersistenceExeption {

        boolean leaveTheLoop = true; // flag to control the while-loop
        int listLength =   getAllItems().size(); // size of the arraylist of items in the stock

        do { // start of the do-while loop
            view.displayItemsToConsumer(getAllItems());
            moneyInput = view.getMoneyAmount(); // get money amount entered by the user

            selection = view.getUserSelection(listLength); // get user's item selection number

            if(selection == (listLength)){ // user selecting exit number
                System.out.println("Exiting... Thank you for using our Vending machine!");
                System.exit(0);
            }

            else if(selection >= 0 && selection < (listLength +1)) { // valid user selection range
                boolean isOutOfStockFlag = checkItemWithZeroInventory(selection); // check item level in stock

                if (moneyInput >= getAllItems().get(selection).getItemCost()) { // user put enough money for item
                    // call calculate change method in service
                    if(!isOutOfStockFlag) { // if the item is not out of stock
                        calculateChange(moneyInput - getAllItems().get(selection).getItemCost());
                        service.updateItemList(selection); // update item stock level
                        view.displayTransactionComplet();
                    }else{ // when the item is out of stock, return the whole money entered by the user
                        calculateChange(moneyInput);
                        service.updateItemList(selection);
                        view.displayTransactionComplet();
                    }
                }

                if(moneyInput < getAllItems().get(selection).getItemCost())// if money < price
                    checkMoneyInputLessThanPrice(moneyInput, selection);
            }
            else // if invalid selection, display a message and continue looping
                System.out.println("Please, enter the correct selection...");
        } while (leaveTheLoop); // end of the do-while loop
    }


    // method to get all the current items in the stock.
    // It returns a list of Items
    private List<Item> getAllItems() {
        try {
            List<Item> listOfItemsFromFile = service.getAllItemsFromDao();
            return listOfItemsFromFile;
        } catch (VendingMachinePersistenceExeption e) {
            System.out.println("No item found: " + e);
        }
        return null;
    }

    // method to validate the money entered against the prospective item
    // It throws insufficient fund exception, if userMoney < selection price
    public void checkMoneyInputLessThanPrice(int userMoney, int selection){
        try{
            service.processUserMoney(userMoney, selection);
        }catch(InsufficientFundException e){
            System.out.println("Insufficient fund: "+ userMoney + " cents.");
        }
    }

    // method to check if an item has zero inventory (out) (-1 by my algorithm)
    // It calls the service layer to handle to issue
    // If the item is out of stock, it handles it with NoItemInventoryException
    // which will be thrown from the service layer
    private boolean checkItemWithZeroInventory(int itemId){
        isOutOfStock = false;
        try{
            service.checkItemStockLevel(itemId);
        }catch(NoItemInventoryException e){
            isOutOfStock = true;
            System.out.println("Sorry, " + getAllItems().get(itemId).getItemName() + " is out of stock.");
        }
        return isOutOfStock;
    }

    // Method to calculate the due change
    // it returns a map of Integers (number of coins) and Coin names as strings
    private void calculateChange(int inputMoney)  {
         Map<Integer, String> changeMap = service.findChange(inputMoney);
         view.displayChange(changeMap);
    }

}
