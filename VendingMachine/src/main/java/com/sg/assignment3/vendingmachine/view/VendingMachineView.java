package com.sg.assignment3.vendingmachine.view;

import com.sg.assignment3.vendingmachine.dto.Item;
import com.sg.assignment3.vendingmachine.service.Coin;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// VendingMachineView class to for processing i/o
public class VendingMachineView {

    // declare and initialize class variables
    private UserIO io;
    public static int counter = 0; // to tack number of out stock items during displaying items


    // class constructor
    public VendingMachineView(UserIO io) {
        this.io = io;
    }


    // method to display items to customer
    public void displayItemsToConsumer(List<Item> viewList) {

        io.print("=====Vending Machine====");
        io.print("  Item      Price");
        System.out.println("========================");

        // for-loop to iterate dynamically over stock items
        // It will not display any out of stock item
        for(int i = 0; i < viewList.size(); i++){
            if(viewList.get(i).getNumOfItemsInInventory()  <= -1) {
                counter++;
                continue; // go to the next cycle in the for loop
            }
            System.out.print((i) + "- " + viewList.get(i).getItemName() + "      ");
            System.out.println(viewList.get(i).getItemCost() + "      ");
            //System.out.println(viewList.get(i).getNumOfItemsInInventory()); // use it of verifying stock level
        }
        System.out.println((viewList.size()) + "- Exit");
    }

    // banner to get the input
    public int getMoneyAmount() {
        return io.readInt("Please deposit money in cents: ");
    }

    public int getUserSelection(int len) {
        return io.readInt("Please enter your item-selection from the item-list above");
    }

    // method to iterate over a map using forEach collection method and lambda expression
    // forEach method takes Functional interface of type Consumer and returns nothing
    public void displayChange(Map<Integer, String> changeMap) {
        System.out.println("\nPlease, take the dispensed change... Thank you");
        System.out.println("Denomination values:");

        // using forEach and lambda to display map elements
        // k is for the map-key, and v for the map-value
        changeMap.forEach((k, v) -> System.out.println((k + " " + v)));
    }
    // banner
    public void displayTransactionComplet() {
        System.out.println("Transaction complete.");
    }

    // banner
    public void displaDisplayEnterCorrectSelection() {
        System.out.println("You entered an incorrect selection. Please, enter the right selection.");
    }
}
