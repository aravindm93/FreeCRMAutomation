package com.freecrm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.freecrm.qa.base.Testbase;

public class HomePage extends Testbase{

	
	
	@FindBy(xpath="//td[contains(text(),'Demo User')]")
	WebElement userNameText;
	
	@FindBy(xpath="//a[text()='Contacts']")
	WebElement contactsTab;
	
	@FindBy(linkText="Deals")
	WebElement dealsTab;
	
	@FindBy(linkText="Tasks")
	WebElement tasksTab;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement searchTextBox;
	
	@FindBy(xpath="//input[@type='image']")
	WebElement searchButton;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//i[@class='fa fa-sign-out icon-2x']")
	WebElement logoutLink;
	
	public HomePage() throws IOException {
		
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyUserName() {
		return userNameText.isDisplayed();
	}
	
	public LoginPage verifyLogout() throws IOException {
		logoutLink.click();
		return new LoginPage();
		
	}
	
	public ContactsPage clickOnContactsLink() throws IOException {
		contactsTab.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsTab.click();
		return new DealsPage();
	}
	
	public void verifySearch(String text) {
		searchTextBox.sendKeys(text);
		searchButton.click();
	}

	public void clickOnNewContactsLink() {
		Actions action=new Actions(driver);
		action.moveToElement(contactsTab).build().perform();
		newContactLink.click();
	}

	public LoginPage clickOnLogout() throws IOException {
		logoutLink.click();
		return new LoginPage();
	}

}
