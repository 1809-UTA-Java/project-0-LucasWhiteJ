package com.revature;

public class Account {
	private int accID;
	private String accName;
	private String status;
	private int balance;
	private int ownerid;
	private int coownerid;
	
	public Account(int id, String name, String status, int balance, int ownerid, int coownerid) {
		super();
		this.accID = id;
		this.accName = name;
		this.status = status;
		this.balance = balance;
		this.ownerid = ownerid;
		this.coownerid = coownerid;
	}
	
	public Account() {
		super();
	}
	
	public int getAccID() {
		return accID;
	}
	
	public void setaccID(int id) {
		this.accID = id;
	}
	
	public String getaccName() {
		return accName;
	}
	
	public void setaccName(String name) {
		this.accName = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public Integer getOwnerID() {
		return ownerid;
	}
	
	public void setOwnerID(int ownerid) {
		this.ownerid = ownerid;
	}
	
	public Integer getCoOwnerID() {
		return coownerid;
	}
	
	public void setCoOwnerID(int coownerid) {
		this.coownerid = coownerid;
	}

	@Override
	public String toString() {
		return "Account [ID=" + accID + ", Name=" + accName + ", Status=" + status + ", balance=" + balance + ", Owner ID=" + ownerid + ", Co-Owner ID=" + coownerid + "]";
	}
}
