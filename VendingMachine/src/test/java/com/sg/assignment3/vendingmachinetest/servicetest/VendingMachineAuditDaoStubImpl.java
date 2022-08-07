package com.sg.assignment3.vendingmachinetest.servicetest;

import com.sg.assignment3.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.assignment3.vendingmachine.dao.VendingMachinePersistenceExeption;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceExeption {

    }
}
