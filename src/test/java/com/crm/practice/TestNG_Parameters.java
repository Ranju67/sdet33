package com.crm.practice;

//import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNG_Parameters {

	@Test(groups={"Smoke","regression"})
	public void a() {
		Reporter.log("exceute",true);
	}
	@Test(groups="Smoke")
	public void B() {
		Reporter.log("HI",true);
	}
	@Test(groups ="regression")
	public void D() {
		Reporter.log("BYE",true);
	}
	/*@Test(dependsOnMethods = "e",enabled = true)
	public void c() {
		Reporter.log("HELLO",true);
	}
	
	@Test 
	public void e() {
		Reporter.log("pass",true);
	Assert.fail();
	}*/
	
	
	
}
