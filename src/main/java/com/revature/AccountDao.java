package com.revature;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.Account;
import com.revature.ConnectionUtil;
import oracle.jdbc.internal.OracleTypes;

public class AccountDao {

	public List<Account> getAccounts() {
		PreparedStatement ps = null;
		Account acc = null;
		List<Account> accounts = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("accid");
				String name = rs.getString("name");
				String status = rs.getString("status");
				int balance = rs.getInt("balance");
				int ownerid = rs.getInt("ownerid");
				int coownerid = rs.getInt("ownerid");
				
				acc = new Account(id, name, status, balance, ownerid, coownerid);
				accounts.add(acc);
			}
			ps.close();
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return accounts;
	}
	
	public List<Account> getAccountsByOwnerID(int ownerID) {
		PreparedStatement ps = null;
		Account acc = null;
		List<Account> accounts = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account where ownerid=? or coownerid=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerID);
			ps.setInt(2, ownerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("accid");
				String name = rs.getString("name");
				String status = rs.getString("status");
				int balance = rs.getInt("balance");
				int ownerid = rs.getInt("ownerid");
				int coownerid = rs.getInt("ownerid");
				
				acc = new Account(id, name, status, balance, ownerid, coownerid);
				accounts.add(acc);
			}
			ps.close();
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return accounts;
	}
	
	public void updateAccStatus(int ownerID) {
		PreparedStatement ps = null;
		Account acc = null;
		List<Account> accounts = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account where ownerid=? or coownerid=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerID);
			ps.setInt(2, ownerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("accid");
				String name = rs.getString("name");
				String status = rs.getString("status");
				int balance = rs.getInt("balance");
				int ownerid = rs.getInt("ownerid");
				int coownerid = rs.getInt("ownerid");
				
				acc = new Account(id, name, status, balance, ownerid, coownerid);
				
				if(status.equals("pending")) {
				System.out.println("Account number:" + id);
				System.out.println("Account status:" + status);
				System.out.println("Do you want to 1: Approve or 2: Deny this account?");
				String input = sc.nextLine();
				if (input.equals("1")) {
					String sql2 = "update account set status=? where name=? and ownerid=?";
					
					status = "approve";
					ps = conn.prepareStatement(sql2);
					ps.setString(1, status);
					ps.setString(2, name);
					ps.setInt(3, ownerid);
					ps.executeQuery();
				} else if (input.equals("2")) {
					String sql2 = "update account set status=? where name=? and ownerid=?";
					
					status = "denied";
					ps = conn.prepareStatement(sql2);
					ps.setString(1, status);
					ps.setString(2, name);
					ps.setInt(3, ownerid);
					ps.executeQuery();
					
					String sql3 = "delete from account where status=?";
					ps = conn.prepareStatement(sql3);
					ps.setString(1, "denied");
					ps.executeQuery();
				}
				} else
					System.out.println("No pending accounts");
			
			}
			ps.close();
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	public void cancelAccount(int ownerID, String accName) {
		PreparedStatement ps = null;
		Account acc = null;
		List<Account> accounts = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account where ownerid=? or coownerid=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerID);
			ps.setInt(2, ownerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("accid");
				String name = rs.getString("name");
				String status = rs.getString("status");
				int balance = rs.getInt("balance");
				int ownerid = rs.getInt("ownerid");
				int coownerid = rs.getInt("ownerid");
				
				acc = new Account(id, name, status, balance, ownerid, coownerid);
				
					String sql2 = "update account set status=? where name=? and ownerid=?";
					
					status = "denied";
					ps = conn.prepareStatement(sql2);
					ps.setString(1, status);
					ps.setString(2, accName);
					ps.setInt(3, ownerID);
					ps.executeQuery();
					
					String sql3 = "delete from account where status=?";
					ps = conn.prepareStatement(sql3);
					ps.setString(1, "denied");
					ps.executeQuery();
					ps.close();
			}
		}
		catch (SQLException ex) {
			ex.getMessage();
		}catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	public void addNewAccount(String accName, int ownerid, int coownerid) {
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into account (accid, name, status, balance, ownerid, coownerid) values (accidincrement.nextval,?,?,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, accName);
			cs.setString(2, "pending");
			cs.setInt(3, 0);
			cs.setInt(4, ownerid);
			if(coownerid == 0)
				cs.setNull(5, 0);
			else
				cs.setInt(5, coownerid);
			
			cs.execute();
			cs.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
	}
	
	public void makeDeposit(String accName, int ownerid, int amount) {
		CallableStatement cs2 = null;
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account where ownerid=? or coownerid=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerid);
			ps.setInt(2, ownerid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String status = rs.getString("status");
				int balance = rs.getInt("balance");
				int id = rs.getInt("ownerid");
				int coID = rs.getInt("coownerid");

				if(status.equals("approve")) {
				if(accName.equals(name) && ownerid==id) {
					amount += balance;
					sql = "update account set balance=? where name=? and ownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, amount);
					cs2.setString(2, accName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				} else if (accName.equals(name) && ownerid==coID) {
					amount += balance;
					sql = "update account set balance=? where name=? and coownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, amount);
					cs2.setString(2, accName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				}
			} else
				System.out.println("Account not approved yet");
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
	}
	
	public void makeWithdrawal(String accName, int ownerid, int amount) {
		CallableStatement cs2 = null;
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account where ownerid=? or coownerid=?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerid);
			ps.setInt(2, ownerid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String status = rs.getString("status");
				int balance = rs.getInt("balance");
				int id = rs.getInt("ownerid");
				int coID = rs.getInt("coownerid");

				if(status.equals("approve")) {
				if(accName.equals(name) && ownerid==id) {
					amount = balance - amount;
					System.out.println(amount);
					sql = "update account set balance=? where name=? and ownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, amount);
					cs2.setString(2, accName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				} else if (accName.equals(name) && ownerid==coID) {
					amount = balance - amount;
					sql = "update account set balance=? where name=? and coownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, amount);
					cs2.setString(2, accName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				}
			} else
				System.out.println("Account not approved yet");
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	public void makeTransfer(String accName, String otherAccName, int ownerid, int amount) {
		CallableStatement cs2 = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int newAmount = 0;

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from account where ownerid=? or coownerid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerid);
			ps.setInt(2, ownerid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				int balance = rs.getInt("balance");
				String status = rs.getString("status");
				int id = rs.getInt("ownerid");
				int coID = rs.getInt("coownerid");

				if(status.equals("approve")) {
				if(accName.equals(name) && ownerid==id) {
					newAmount = balance - amount;
					sql = "update account set balance=? where name=? and ownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, newAmount);
					cs2.setString(2, accName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				} else if (otherAccName.equals(name) && ownerid==id) {
					newAmount = balance + amount;
					sql = "update account set balance=? where name=? and ownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, newAmount);
					cs2.setString(2, otherAccName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				} else if (accName.equals(name) && ownerid==coID) {
					newAmount = balance - amount;
					sql = "update account set balance=? where name=? and ownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, newAmount);
					cs2.setString(2, accName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				} else if (otherAccName.equals(name) && ownerid==coID) {
					newAmount = balance + amount;
					sql = "update account set balance=? where name=? and ownerid=?";
					cs2 = conn.prepareCall(sql);
					cs2.setInt(1, newAmount);
					cs2.setString(2, otherAccName);
					cs2.setInt(3, ownerid);
					cs2.execute();
					cs2.close();
				}
			} else
				System.out.println("Account not approved yet");
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	
}
