package com.sg.assignment3.vendingmachine.service;

import com.sg.assignment3.vendingmachine.dao.VendingMachinePersistenceExeption;
import com.sg.assignment3.vendingmachine.dto.Item;

import java.util.List;
import java.util.Map;

public interface VendingMachineService {
    List<Item> getAllItemsFromDao() throws VendingMachinePersistenceExeption;

    boolean processUserMoney(double userMoney, int selection) throws InsufficientFundException;

    boolean checkItemStockLevel(int itemId) throws NoItemInventoryException;

    Map<Integer, String> findChange(int inputMoney);

    void updateItemList(int i) throws VendingMachinePersistenceExeption;

}
