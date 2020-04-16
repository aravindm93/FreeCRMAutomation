package com.freecrm.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.freecrm.qa.base.Testbase;
import com.freecrm.qa.pages.ContactsPage;
import com.freecrm.qa.pages.HomePage;
import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.Testutil;

@Listeners(com.freecrm.qa.listener.MyCustomListener.class)

public class ContactsPageTest extends Testbase {
	LoginPage loginPage;
	HomePage homePage;
	Testutil testUtil;
	ContactsPage contactsPage;
	String sheetName="Contacts";

	public ContactsPageTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		loginPage=new LoginPage();
		testUtil=new Testutil();
		contactsPage=new ContactsPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsPageLabel(),"Contracts Page Label is not matched");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactsByName("abc xyz");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("abc xyz");
		contactsPage.selectContactsByName("akash reddy");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]=Testutil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4,dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title,String firstName,String lastName,String company) {
		homePage.clickOnNewContactsLink();
		//contactsPage.createNewContact("Mr.", "John", "Snow", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
