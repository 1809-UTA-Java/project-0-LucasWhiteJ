package com.revature;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class AccountDaoTest {

	@Test
	public void addNewAccountTest() {
		AccountDao aDao = new AccountDao();
		aDao.addNewAccount("checking", 1, 2);
		List<Account> a = aDao.getAccountsByOwnerID(1);
		assertEquals("checking", a.get(0).getaccName());
	}
	
	@Test
	public void updateAccountStatusTest() {
		AccountDao aDao = new AccountDao();
		aDao.updateAccStatus(1);
		List<Account> a = aDao.getAccountsByOwnerID(1);
		assertEquals("approve", a.get(0).getStatus());
	}
	
	@Test
	public void makeDepositTest() {
		AccountDao aDao = new AccountDao();
		aDao.makeDeposit("checking", 1, 500);
		List<Account> a = aDao.getAccountsByOwnerID(1);
		assertEquals(500, a.get(0).getBalance());
	}
	
	@Test
	public void makeWithdrawalTest() {
		AccountDao aDao = new AccountDao();
		aDao.makeWithdrawal("checking", 1, 500);
		List<Account> a = aDao.getAccountsByOwnerID(1);
		assertEquals(500, a.get(0).getBalance());
	}
	
	@Test
	public void makeTransferTest() {
		AccountDao aDao = new AccountDao();
		aDao.makeTransfer("checking", "saving", 1, 500);
		List<Account> a = aDao.getAccountsByOwnerID(1);
		assertEquals(500, a.get(0).getBalance());
	}
}
