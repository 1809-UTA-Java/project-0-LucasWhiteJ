package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerDaoTest {

	@Test
	public void addNewCustomerTest() {
		CustomerDao cDao = new CustomerDao();
		cDao.addNewCustomer("john", "johnny", "password");
		Customer c = cDao.getCustByUserName("johnny");
		assertEquals("johnny", c.getCustUserName());
	}
}
