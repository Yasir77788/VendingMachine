package com.sg.assignment3.vendingmachinetest.servicetest.daotest;

import com.sg.assignment3.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment3.vendingmachine.dao.VendingMachineDaoFileImpl;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;

public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testitems.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }


}
