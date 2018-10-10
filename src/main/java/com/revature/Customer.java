package com.revature;

public class Customer {
	private int custID;
	private String custName;
	private String custUserName;
	private String custPassword;
	
	public Customer(int id, String name, String userName, String password) {
		super();
		this.custID = id;
		this.custName = name;
		this.custUserName = userName;
		this.custPassword = password;
	}
	
	public Customer() {
		super();
	}
	
	public int getCustID() {
		return custID;
	}
	
	public void setCustID(int id) {
		this.custID = id;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public void setCustName(String name) {
		this.custName = name;
	}
	
	public String getCustUserName() {
		return custUserName;
	}
	
	public void setCustUserName(String userName) {
		this.custUserName = userName;
	}
	
	public String getCustPassword() {
		return custPassword;
	}
	
	public void setCustPassword(String userPassword) {
		this.custPassword = userPassword;
	}

	@Override
	public String toString() {
		return "Customer [ID=" + custID + ", Name=" + custName + ", User Name=" + custUserName + ", Password=" + custPassword + "]";
	}
}
