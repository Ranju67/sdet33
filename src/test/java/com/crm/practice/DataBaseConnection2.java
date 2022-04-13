package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnection2 {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
    try {
    	Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33","root","root");
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from sdet3");
        
        while(result.next()) {
     	   System.out.println(result.getString(2));
     	   System.out.println(result.getString(1));
        }
    }
    finally {
    	 connection.close();
    	 System.out.println("Connection is closed");
    }
	}

}
