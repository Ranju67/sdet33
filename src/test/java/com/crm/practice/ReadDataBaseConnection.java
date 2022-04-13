package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadDataBaseConnection {

	public static void main(String[] args) throws SQLException {
      //step:1 create object of driver and register driver
		Driver driver = new Driver();
	
       DriverManager.registerDriver(driver);
       //step:2 get connection using MydriverManager
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33","root","root");
       //STEP:3 CREATE STATEMENT
       Statement statement = connection.createStatement();
       //STEP:4 GET QUERY 
       ResultSet result = statement.executeQuery("select * from sdet33");
       
       while(result.next()) {
    	   System.out.println(result.getString(2));
    	   System.out.println(result.getString(1));
       }
       //SREP:5 close connection
       connection.close();
	}

}
