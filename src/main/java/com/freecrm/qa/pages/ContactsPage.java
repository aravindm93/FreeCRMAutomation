package com.freecrm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.freecrm.qa.base.Testbase;

public class ContactsPage extends Testbase{
	
	Select selectTitleDropDown;

	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsPageLabel;
	
	@FindBy(xpath="//select[@name='title']")
	WebElement titleDropDown;
	
	@FindBy(id="first_name")
	WebElement firstNameTextBox;
	
	@FindBy(id="surname")
	WebElement lastNameTextBox;
	
	@FindBy(name="client_lookup")
	WebElement companyNameTextBox;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']//preceding-sibling::input[@value='Load From Company']")
	WebElement saveBtn;

	public ContactsPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsPageLabel() {
		return contactsPageLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+"//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();;
	}
	
	public void createNewContact(String title,String ftName,String ltName,String comp) {
		selectTitleDropDown=new Select(titleDropDown);
		selectTitleDropDown.selectByValue(title);
		firstNameTextBox.sendKeys(ftName);
		lastNameTextBox.sendKeys(ltName);
		companyNameTextBox.sendKeys(comp);
		saveBtn.click();
		
	}
	
}
