package com.freecrm.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.freecrm.qa.base.Testbase;
import com.freecrm.qa.pages.HomePage;
import com.freecrm.qa.pages.LoginPage;

@Listeners(com.freecrm.qa.listener.MyCustomListener.class)

public class LoginTest extends Testbase{
	LoginPage login;
	HomePage home;

	public LoginTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		login=new LoginPage();	
	}
	
	@Test(priority=1,testName="Login Page Title Test")
	public void loginPageTitleTest() {
		String actualTitle=login.validatePageTitle();
		Assert.assertEquals(actualTitle, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2, testName="Login Page Logo Test")
	public void loginPageLogoTest() {
		Boolean actualResult=login.validdatePageLogo();
		Assert.assertTrue(actualResult);
	}
	
	@Test(priority=3, testName="Login Test")
	public void loginTest() throws IOException {
		home=login.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
