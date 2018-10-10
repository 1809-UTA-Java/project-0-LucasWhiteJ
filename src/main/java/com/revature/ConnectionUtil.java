package com.revature;

import java.io.IOException;
import java.sql.*;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException, IOException {
		String url = "jdbc:oracle:thin:@192.168.56.105:1521:xe";
		String userName = "lucas";
		String password = "password";
		
		return DriverManager.getConnection(url, userName, password);
	}

}
