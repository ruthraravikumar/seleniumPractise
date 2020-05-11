package com.wipro.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;

@Test
public class Ruthra_DatePicker extends BaseTest{
	
	By title = By.xpath("//h1[@class='entry-title']");
	By datePicker= By.xpath("//input[@id='datepicker']");
	
	
	public void datePicker(){
		driver.get("https://demoqa.com/datepicker/");
		  
		  WebDriverWait wait = new WebDriverWait(driver, 30);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		  
		  WebElement element = findElement(title);	  
		  Assert.assertTrue(element.isDisplayed(),"Title is not displayed");
		  
		  enterText(datePicker, "02/20/1995");
		  
		  System.out.println("Date is entered");
		  
//		  String enteredDate=getText(datePicker);
//		  Assert.assertEquals(enteredDate, "05/14/2020");
//		  
		  
		  
		  
	}

}
