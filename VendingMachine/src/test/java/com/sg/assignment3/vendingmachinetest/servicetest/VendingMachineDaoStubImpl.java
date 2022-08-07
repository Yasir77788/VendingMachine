package com.sg.assignment3.vendingmachinetest.servicetest;

import com.sg.assignment3.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment3.vendingmachine.dao.VendingMachinePersistenceExeption;
import com.sg.assignment3.vendingmachine.dto.Item;
import com.sg.assignment3.vendingmachine.service.InsufficientFundException;
import com.sg.assignment3.vendingmachine.service.NoItemInventoryException;

import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {
    @Override
    public boolean processMoneyEntered(double userMoney, int selection) throws InsufficientFundException {
        return false;
    }

    @Override
    public boolean checkItemLevelInTheStock(int itemId) throws NoItemInventoryException {
        return false;
    }

    @Override
    public List<Item> loadItemsFormFileToList() throws VendingMachinePersistenceExeption {
        return null;
    }

    @Override
    public void updateItemsInTheListinDao(int i) throws VendingMachinePersistenceExeption {

    }
}
