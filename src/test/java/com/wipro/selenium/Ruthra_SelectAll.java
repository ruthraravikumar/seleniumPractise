package com.wipro.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;

@Test
public class Ruthra_SelectAll extends BaseTest{
	
	public static Logger log= Logger.getLogger(Ruthra_SelectAll.class.getName());
	
	
	By title=By.xpath("//h1[@class='entry-title']");
	By speed=By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");
	By  selectList= By.xpath("//div[@class='ui-menu-item-wrapper']");
	By file=By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");
	By number= By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");
	By selectTitle=By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");
	
	
	public void select(){
		
		driver.get("https://demoqa.com/selectmenu/");
		  
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		  
		  WebElement element = findElement(title);	  
		  Assert.assertTrue(element.isDisplayed(),"Title is not displayed");
		  
		  click(speed);
		  selectList("Fast",selectList);
		  
		  click(file);
		  selectList("ui.jQuery.js",selectList);
		  
		  click(number);
		  selectList("5",selectList);
		  
		  click(selectTitle);
		  selectList("Other",selectList);
		  
		  
		  
		  
		 /* Select selectSpeed = new Select(driver.findElement(By.id("speed")));
		  selectSpeed.selectByIndex(1);
		  
		  Select selectfile= new Select(findElement(file));
		  selectfile.selectByValue("jqueryui");
		  
		  Select selectNumber= new Select(findElement(number));
		  selectNumber.selectByIndex(7);
		  
		  Select selecttitle= new Select(findElement(selectTitle));
		  selecttitle.selectByVisibleText("Mrs.");*/	  
		  
	}
	
	

}
