package com.crm.practice;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNGpractise2Test {
	
	@BeforeClass
	public void demo() {
		Reporter.log("hi",true);
	}
	@Test @AfterClass
	public void tester() {
		Reporter.log("hello",true);
	}
	@Test
	public void testing() {
		Reporter.log("processing",true);
	} 
	@Test
	public void complete() {
		Reporter.log("complete",true);
	}
	
}
