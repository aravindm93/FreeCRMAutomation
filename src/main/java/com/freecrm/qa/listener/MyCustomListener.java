package com.freecrm.qa.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.freecrm.qa.util.Testutil;


public class MyCustomListener implements ITestListener {
	
	public static ExtentReports extent; // Step 1
	public static ExtentTest test; // Step 2

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********* Starting Test" + result.getName() + "**************");
		test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********* Test Pass" + result.getName());
		test.log(Status.PASS, result.getName());
		try {
			Testutil.takeScreenShot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********* Test Fail" + result.getName());
		test.log(Status.FAIL, result.getName());
		try {
			Testutil.takeScreenShot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********* Test Skipped" + result.getName());
		test.log(Status.SKIP, result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("********* Running " + context.getClass().getName());
		ExtentHtmlReporter html = new ExtentHtmlReporter("MyTestReport.html"); // Step3
		extent = new ExtentReports(); // step4
		extent.attachReporter(html);

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
