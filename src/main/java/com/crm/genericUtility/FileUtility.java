package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	static Properties properties;
	/**
	 * This class contains only property file common methods
	 * @param key
	 * @return
	 */
	public static String fetchDataFromProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}
	/**
	 * This method is used to initialize the property file
	 * @param path
	 * @throws IOException
	 */
	public static void intiallizePropertyFile(String path) throws IOException  {
		FileInputStream fis=new FileInputStream(path);
		properties=new Properties();
		properties.load(fis);
	}
}
 