package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class database {
	public static void main(String[] args) throws SQLException {
		
		Connection connection=null;
		String url=null;
		String username=null;
		String password=null;
		String last_name=null;
		Driver driver=new Driver();
	     DriverManager.registerDriver(driver);
	     connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33","root","root");
	     Statement statement=connection.createStatement();
	     ResultSet result = statement.executeQuery("select * from vtiger");
	     while(result.next()) {
	    	 url=result.getString(1);
	    	 username=result.getString(2);
	    	 password=result.getString(3);
	    	 last_name=result.getString(4);
	     }
	 }

}
