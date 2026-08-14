package com.repositary;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jwd70";
	static String name = "root";
	static String password = "123456";
	
	public static Connection getConnection2 () {
		
		Connection connection = null;
			
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, name, password);
			}  catch (ClassNotFoundException e) {
				System.out.println("Driver error: " + e.getMessage());
			} catch (SQLException e) {
				System.out.println("Connection error: " + e.getMessage());
			}
			
			return connection ;
			
		}

}
