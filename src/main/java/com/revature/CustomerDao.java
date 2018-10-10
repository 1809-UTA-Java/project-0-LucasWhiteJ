package com.revature;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.Customer;
import com.revature.ConnectionUtil;
import oracle.jdbc.internal.OracleTypes;

public class CustomerDao {

	public List<Customer> getCustomers() {
		PreparedStatement ps = null;
		Customer cust = null;
		List<Customer> customers = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from customer";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("custID");
				String name = rs.getString("custName");
				String userName = rs.getString("custUserName");
				String password = rs.getString("custPassword");
				
				cust = new Customer(id, name, userName, password);
				customers.add(cust);
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return customers;
	}
	
	public void addNewCustomer(String name, String userName, String password) {
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into customer (custid, custname, custusername, custpassword) values (custidincrement.nextval,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, name);
			cs.setString(2, userName);
			cs.setString(3, password);
			
			cs.execute();
			cs.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
	}
	
	public Customer getCustByUserName(String uName) {
		PreparedStatement ps = null;
		Customer cust = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from customer where custusername=?";
			ps = conn.prepareCall(sql);
			ps.setString(1, uName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("custid");
				String name = rs.getString("custname");
				String userName = rs.getString("custusername");
				String password = rs.getString("custpassword");
				
				cust = new Customer(id, name, userName, password);
				//customers.add(cust);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return cust;
	}
}
