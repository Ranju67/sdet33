package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileRead {

	public static void main(String[] args) throws IOException {
    //step:1 convert physical file into java readable object
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		
		//step:2 create object of properties
		Properties properties=new Properties();
		
		//step:3 load all the keys
		properties.load(fis);
		
		//step:4 fetch the data
		String url = properties.getProperty("url");
		System.out.println(url);
		
		String username = (properties.getProperty("username"));
		System.out.println(username);

		System.out.println(properties.getProperty("password"));
		System.out.println(properties.getProperty("timeout"));
		System.out.println(properties.getProperty("browser"));
		
	}

}

