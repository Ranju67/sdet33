package com.crm.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;
/**
 * This class contains All the Generic Methods Of Data Base
 * @author SHASHI KUMAR
 *
 */
public class DataBaseUtility {

	static Connection connection;
	static ArrayList<String> List=new ArrayList<String>();
	
	/**
	 * This method is used to establish the connection of the mySql Database
	 * @param dbUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	public static void getMySqlDatabaseConnection(String dbUrl,String dbUserName,String dbPassword) throws SQLException {
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		connection=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
	}
	/**
	 * This method is used to fetch the data from database on the column Number
	 * @param query
	 * @param columnNumber
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFormDatabase(String query,int columnNumber) throws SQLException {
	
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next())
			{
			List.add(result.getString(columnNumber));
			}
			statement.close();
			return List;
	}
	
	/**
	 * This method is used to fetch the data from database on the column Name
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String>getDataFromDatabase(String query,String columnNameOrColumnNumber)throws SQLException{
	String num="";
	for (int i = 0; i < columnNameOrColumnNumber.length(); i++) {
		char ch=columnNameOrColumnNumber.charAt(i);
				if(Character.isDigit(ch)) {
			num=num+columnNameOrColumnNumber.charAt(i);//1
		}else {
			break;
		}
	}
	Statement statement=connection.createStatement();
	ResultSet result = statement.executeQuery(query);
	
	int columnNumber=0;
	String columnName= null;
	if(num.equalsIgnoreCase(columnNameOrColumnNumber))
	{
		columnNumber=Integer.parseInt(num);
		while(result.next()) {
			List.add(result.getString(columnNumber));
		}
	}
	else {
		columnName=columnNameOrColumnNumber;
		while (result.next()){
			List.add(result.getString(columnNumber));
		}
		}
	statement.close();
	return List;
	}
	
	/**
	 * This method is used to update/write/modify the data inside the database
	 * @param query
	 * @throws SQLException
	 */
	public static void updateDatIntoDatabase(String query) throws SQLException {
		Statement statement=connection.createStatement();
		statement.executeUpdate(query);
		System.out.println("Query created successfully");
		statement.close();
	}
	/**
	 * This method is used close the database connection
	 * @throws SQLException
	 */
	public static void closeDatabaseConnection() throws SQLException {
		connection.close();
	}
	
	/**
	 * This method is used to verify the data whether it is present in the database or not
	 * @param query
	 * @param columnNameOrColumnNumber
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	public static boolean verifyData(String query,String columnNameOrColumnNumber,String expData) throws SQLException {
ArrayList<String>listData=getDataFromDatabase(query,columnNameOrColumnNumber);	
	boolean flog=false;
	
	for(String data:listData)
	{
		if(data.equalsIgnoreCase(expData)) {
			flog=true;
			break;
		}
	}
	return flog;
}
}
