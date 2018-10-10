package com.revature;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.Employee;
import com.revature.ConnectionUtil;
import oracle.jdbc.internal.OracleTypes;

public class EmployeeDao {

	public List<Employee> getEmployees() {
		PreparedStatement ps = null;
		Employee emp = null;
		List<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from employee";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("empID");
				String name = rs.getString("empName");
				String userName = rs.getString("empUserName");
				String password = rs.getString("empPassword");
				String isAnAdmin = rs.getString("isanadmin");
				
				emp = new Employee(id, name, userName, password, isAnAdmin);
				employees.add(emp);
			}
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return employees;
	}
	
	public void addNewEmployee(String name, String userName, String password, String isAnAdmin) {
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into employee (empid, empname, empusername, emppassword, isanadmin) values (empidincrement.nextval,?,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, name);
			cs.setString(2, userName);
			cs.setString(3, password);
			cs.setString(4, isAnAdmin);
			
			cs.execute();
			cs.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
	}
	
	public Employee getEmpByUserName(String uName) {
		PreparedStatement ps = null;
		Employee emp = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from employee where empusername=?";
			ps = conn.prepareCall(sql);
			ps.setString(1, uName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("empid");
				String name = rs.getString("empname");
				String userName = rs.getString("empusername");
				String password = rs.getString("emppassword");
				String isAnAdmin = rs.getString("isanadmin");
				
				emp = new Employee(id, name, userName, password, isAnAdmin);
				//employees.add(emp);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return emp;
	}

}
