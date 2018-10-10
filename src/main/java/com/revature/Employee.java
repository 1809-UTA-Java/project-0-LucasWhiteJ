package com.revature;

public class Employee {
	private int empID;
	private String empName;
	private String empUserName;
	private String empPassword;
	private String isAnAdmin;
	
	public Employee(int id, String name, String userName, String password, String isAnAdmin) {
		super();
		this.empID = id;
		this.empName = name;
		this.empUserName = userName;
		this.empPassword = password;
		this.isAnAdmin = isAnAdmin;
	}
	
	public Employee() {
		super();
	}
	
	public int getEmpID() {
		return empID;
	}
	
	public void setEmpID(int id) {
		this.empID = id;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String name) {
		this.empName = name;
	}
	
	public String getEmpUserName() {
		return empUserName;
	}
	
	public void setEmpUserName(String userName) {
		this.empUserName = userName;
	}
	
	public String getEmpPassword() {
		return empPassword;
	}
	
	public void setEmpPassword(String userPassword) {
		this.empPassword = userPassword;
	}
	
	public String getIsAnAdmin() {
		return isAnAdmin;
	}
	
	public void setIsAnAdmin(String isAnAdmin) {
		this.isAnAdmin = isAnAdmin;
	}

}
