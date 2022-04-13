package com.crm.genericUtility;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	/**
	 * This method is used to generate Random Numbers
	 * @param limit
	 * @return
	 */
	public static int generateRndomNumber(int limit) {
     Random ran=new Random();
     int randomNumber = ran.nextInt(limit);
     return randomNumber;
		
	}
	/**
	 * this method is used to get the current date and time in required 
	 * @return
	 */
	public static String getCurrentTimeAndDate() {
	 SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	Date date = new Date();
		 String requireFormatDate = sdf.format(date);
		 return requireFormatDate;
	}
	/**
	 * this method is used to covert the String to long datatype
	 * @param value
	 * @return
	 */
	public static long convertStringToLong(String value) {
		long data=Long.parseLong(value);
		return data;
	}
	

}
