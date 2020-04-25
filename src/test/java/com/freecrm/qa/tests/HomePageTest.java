package com.freecrm.qa.tests;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.freecrm.qa.base.Testbase;
import com.freecrm.qa.pages.ContactsPage;
import com.freecrm.qa.pages.HomePage;
import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.Testutil;

@Listeners(com.freecrm.qa.listener.MyCustomListener.class)

public class HomePageTest extends Testbase {
	LoginPage loginPage;
	HomePage homePage;
	Testutil testUtil;
	ContactsPage contactsPage;

	public HomePageTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		loginPage=new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil=new Testutil();
		contactsPage=new ContactsPage();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String actualTitle=homePage.verifyHomePageTitle();
		Assert.assertEquals(actualTitle, "CRMPRO","Home Page Title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		Boolean userNameDisplayedFlag=homePage.verifyUserName();
		Assert.assertTrue(userNameDisplayedFlag);
	}
	
	@Test(priority=3)
	public void verifyLogoutTest() throws IOException {
		testUtil.switchToFrame();
		loginPage=homePage.verifyLogout();
		String loginPageTitle=loginPage.validatePageTitle();
		Assert.assertEquals(loginPageTitle, "CRMPRO - CRM software for customer relationship management, sales, and support.","Login Page Title is not matched");
	}
	
	@Test(priority=4)
	public void verifyContactsLinkTest() throws IOException {
		testUtil.switchToFrame();
		contactsPage=homePage.clickOnContactsLink();
	}
	
	@Test(priority=5)
	public void verifySearchTest() {
		testUtil.switchToFrame();
		homePage.verifySearch("Test Contact");
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Test Contact')]")).isDisplayed());		
	}

	@Test(priority=6)
	public void clickOnLogoutTest() throws IOException {
		testUtil.switchToFrame();
		loginPage=homePage.clickOnLogout();
		String title=driver.getTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
