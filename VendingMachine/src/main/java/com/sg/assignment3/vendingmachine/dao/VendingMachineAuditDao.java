package com.sg.assignment3.vendingmachine.dao;

public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceExeption;
}
