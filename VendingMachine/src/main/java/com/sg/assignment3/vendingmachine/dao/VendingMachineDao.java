package com.sg.assignment3.vendingmachine.dao;

import com.sg.assignment3.vendingmachine.dto.Item;
import com.sg.assignment3.vendingmachine.service.InsufficientFundException;
import com.sg.assignment3.vendingmachine.service.NoItemInventoryException;

import java.util.List;

public interface VendingMachineDao {

    boolean processMoneyEntered(double userMoney, int selection) throws InsufficientFundException;

    boolean checkItemLevelInTheStock(int itemId) throws NoItemInventoryException;

    List<Item> loadItemsFormFileToList() throws VendingMachinePersistenceExeption;

    void updateItemsInTheListinDao(int i) throws VendingMachinePersistenceExeption;
}
