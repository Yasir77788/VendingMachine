// Trainee Name: Yasir Hassan
// Assignment 3 - Vending Machine
package com.sg.assignment3.vendingmachine;

import com.sg.assignment3.vendingmachine.controller.VendingMachineController;
import com.sg.assignment3.vendingmachine.dao.*;
import com.sg.assignment3.vendingmachine.service.InsufficientFundException;
import com.sg.assignment3.vendingmachine.service.VendingMachineService;
import com.sg.assignment3.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.assignment3.vendingmachine.view.UserIO;
import com.sg.assignment3.vendingmachine.view.UserIoConsoleImpl;
import com.sg.assignment3.vendingmachine.view.VendingMachineView;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceExeption{

        // Instantiate the VendingMachineController class
        VendingMachineController controller = new VendingMachineController();

        // Kick off the Controller
        controller.run();
    }

}
