package com.freecrm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.freecrm.qa.util.Testutil;

public class Testbase {
public static WebDriver driver;
public static Properties prop;
public static File file;
public static FileInputStream fs;

public Testbase() throws IOException {
	file = new File(System.getProperty("user.dir")+"\\config\\config.properties");
	prop=new Properties();
	fs = new FileInputStream(file);
	prop.load(fs);
	
}

public void initialization() {
	String browserName=prop.getProperty("browser");
	if(browserName.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if(browserName.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(Testutil.PAGE_LOADTIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Testutil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));
	
}
}
