package com.sg.assignment3.vendingmachinetest.servicetest;

import com.sg.assignment3.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.assignment3.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment3.vendingmachine.dto.Item;
import com.sg.assignment3.vendingmachine.service.VendingMachineService;
import com.sg.assignment3.vendingmachine.service.VendingMachineServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VendingMachineServiceLayerImplTest {

    private VendingMachineService service;

    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceImpl(dao, auditDao);
    }

    @Test
    public void testGetAllItemsFromDao() throws Exception {
        // ARRANGE
        Item testClone = new Item("Mars");
        testClone.setItemCost(120);
        testClone.setNumOfItemsInInventory(10);


        // ACT & ASSERT
        // Text file should have only one it ("Mars")
//        assertEquals( 1, service.getAllItemsFromDao().size(),
//                "Should only have one Item.");
        assertTrue( service.getAllItemsFromDao().contains(testClone),
                "The one item should be Mars.");
    }



}
