package com.sg.assignment3.vendingmachine.service;

import com.sg.assignment3.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.assignment3.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment3.vendingmachine.dao.VendingMachinePersistenceExeption;
import com.sg.assignment3.vendingmachine.dto.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ServiceLayer Implementor class
public class VendingMachineServiceImpl implements VendingMachineService{

    // declaring dao and auditDao
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;


    // Constructor Instantiating implementations
    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;

    }

    // this method calls the dao to get a list of all items in the current stock
    @Override
    public List<Item> getAllItemsFromDao() throws VendingMachinePersistenceExeption {
        return dao.loadItemsFormFileToList();
    }

    // this method calls dao to compare user money-input with the item price in the stock
    @Override
    public boolean processUserMoney(double userMoney, int selection) throws InsufficientFundException {
        return dao.processMoneyEntered(userMoney, selection);
    }

    // method to check if an item is out of stock.
    // It calls the Dao method to find out if the item is out of stock
    // If it is out, it throws NoItemInventoryException back to the controller
    // Otherwise, it returns false to the controller
    @Override
    public boolean checkItemStockLevel(int itemId) throws NoItemInventoryException {
        boolean isItemOutOfStock = false;

        dao.checkItemLevelInTheStock(itemId);

        return isItemOutOfStock;
    }


    // This method calculates change
    // it takes the difference between the customer's money and the item price
    // it returns a hashMap of Integer and String from the Coin enum
    @Override
    public Map<Integer, String> findChange(int inputMoney)  {

        // define a hashMap to intercept the number of denominations and coin names
        Map<Integer, String> coinMap = new HashMap<>();

        int numPennies = inputMoney; // assign the due change number of pennies or cents
        int numDimes = 0;
        int numNickels = 0;
        int numQuarters = 0;

        numQuarters = Coin.QUARTER.toDenomination(numPennies); // convert the pennies to num of quarters
        coinMap.put(numQuarters, Coin.QUARTER.name()); // put the result in the map

        numPennies -= numQuarters * Coin.QUARTER.denomValue(); // subtract numQuarters (in pennies) from the original numPennies.
        numDimes = Coin.DIME.toDenomination(numPennies); // convert the pennies to coin
        coinMap.put(numDimes, Coin.DIME.name());

        numPennies -= numDimes * Coin.DIME.denomValue(); // repeating the process above with Dimes
        numNickels = Coin.NICKEL.toDenomination(numPennies);
        coinMap.put(numNickels, Coin.NICKEL.name());

        numPennies -= numNickels*Coin.NICKEL.denomValue();
        coinMap.put(numPennies, Coin.PENNY.name());

        return coinMap;
    }

    // calls dao to update item stock level
    @Override
    public void updateItemList(int i) throws VendingMachinePersistenceExeption {
        dao.updateItemsInTheListinDao(i);
    }


}
