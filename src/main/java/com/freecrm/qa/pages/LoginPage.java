package com.freecrm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.qa.base.Testbase;

public class LoginPage extends Testbase{
	
	

	@FindBy(name="username")
	WebElement userNameTextBox;
	
	@FindBy(name="password")
	WebElement passwordTextBox;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(linkText="Sign Up")
	WebElement signUpLink;
	
	@FindBy(xpath="//img[contains(@src,'logo')]")
	WebElement loginPageLogo;
	
	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public String validatePageTitle() {
		return driver.getTitle();
	}
	
	public Boolean validdatePageLogo() {
		return loginPageLogo.isDisplayed();
	}
	
	public HomePage login(String userName, String password) throws IOException {
		userNameTextBox.sendKeys(userName);
		passwordTextBox.sendKeys(password);
		loginButton.click();
		return new HomePage();
	}
}
