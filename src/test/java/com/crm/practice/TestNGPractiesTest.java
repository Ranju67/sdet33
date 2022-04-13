package com.crm.practice;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestNGPractiesTest {

	@AfterSuite
	public void test() {
		Reporter.log("HI",true);
	}
	@Test
	public void test2() {
		Reporter.log("hello",true);
	}
	 @BeforeSuite
	public void testing() {
		Reporter.log("processing",true);
	}
}
