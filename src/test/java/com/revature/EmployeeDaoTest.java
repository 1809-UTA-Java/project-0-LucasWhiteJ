package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeDaoTest {

	@Test
	public void addNewEmployeeTest() {
		EmployeeDao cDao = new EmployeeDao();
		cDao.addNewEmployee("john", "johnny", "password", "false");
		Employee c = cDao.getEmpByUserName("johnny");
		assertEquals("johnny", c.getEmpUserName());
	}
}
