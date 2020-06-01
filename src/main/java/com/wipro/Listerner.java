package com.wipro;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listerner implements ITestListener {

	public static Logger log= Logger.getLogger(Listerner.class.getName());
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Failed TestCase : " + result.getName());
		
		
		 File scrFile = ((TakesScreenshot)BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
		 String imagesLocation = "target/screenshot/"+result.getName()+".png";
		 try {
			FileUtils.copyFile(scrFile, new File(imagesLocation));
		
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 log.info("Please refer the screenshot in target/screenshot");
		 
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Starting TestCase : " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Success TestCase : " + result.getName());
		
	}
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext result) {
		
		
	}


}
