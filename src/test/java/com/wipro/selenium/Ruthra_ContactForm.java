package com.wipro.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;
import com.wipro.Ass2.Ruthra_Olay_Germany;

@Test
public class Ruthra_ContactForm  extends BaseTest{
	public static Logger log= Logger.getLogger(Ruthra_ContactForm.class.getName());
	
	
	
	By title=By.xpath("//h1[@class='entry-title']");
	By firstName= By.xpath("//input[@placeholder='Your name..']");
	By lastName= By.xpath("//input[@id='lname']");
	By countryName=By.xpath("//input[@placeholder='Enter your Country']");
	By googleLink1=By.linkText("Google Link");
	By googleLink2=By.xpath("//a[contains(text(),'Google Link is here')]");
	By submit =By.xpath("//input[@type='submit']");
	By subject=By.xpath("//textarea[@id='subject']");
	
	public void ContactForm(){
		  
		  driver.get("https://demoqa.com/html-contact-form/");
		  
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		  
		  WebElement element = findElement(title);	  
		  Assert.assertTrue(element.isDisplayed(),"Title is not displayed");
		  
		  enterText(firstName, properties.getProperty("Firstname"));
		  enterText(lastName, properties.getProperty("Lastname"));
		  enterText(countryName, properties.getProperty("CountryName"));
		  enterText(subject, properties.getProperty("Subject"));
		  
		  WebElement link1=findElement(googleLink1);
		  Assert.assertTrue(link1.isDisplayed(),"Link1 is not displayed");
		  
		  WebElement link2=findElement(googleLink2);
		  Assert.assertTrue(link2.isDisplayed(),"Link2 is not displayed");
		  
		  String linkToOpen = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		  driver.findElement(By.linkText("Google Link")).sendKeys(linkToOpen);
		  
		  driver.findElement(googleLink2).sendKeys(linkToOpen);
		  click(submit);
		  
		  
		  
		  
	}

}
