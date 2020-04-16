package com.freecrm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.utils.FileUtil;
import com.freecrm.qa.base.Testbase;

public class Testutil extends Testbase {
	

public Testutil() throws IOException {
		super();
	}

public static final long PAGE_LOADTIMEOUT=20;
public static final long IMPLICIT_WAIT=20;

public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\com\\freecrm\\qa\\testdata\\FreeCRM_TestData.xlsx";

static Workbook book;
static Sheet sheet;

public void switchToFrame() {
	driver.switchTo().frame("mainpanel");
	
}

public static Object[][] getTestData(String sheetName) {
	FileInputStream file = null;
	try {
		file = new FileInputStream(TESTDATA_SHEET_PATH);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		book = WorkbookFactory.create(file);
	} catch (InvalidFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	sheet = book.getSheet(sheetName);
	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	// System.out.println(sheet.getLastRowNum() + "--------" +
	// sheet.getRow(0).getLastCellNum());
	for (int i = 0; i < sheet.getLastRowNum(); i++) {
		for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
			data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			// System.out.println(data[i][k]);
		}
	}
	return data;
}

public static void takeScreenShot() throws IOException {
	File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String currentDir=System.getProperty("user.dir");
	FileUtils.copyFile(srcFile, new File(currentDir + "/Screenshots/" + System.currentTimeMillis() + ".png"));
}

}

