package com.wipro.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;
import com.wipro.Ass2.Ruthra_Olay_Germany;

@Test
public class Ruthra_Selectable  extends BaseTest {
	
	By title=By.xpath("//h1[@class='entry-title']");
	By itemList =By.xpath("//li[@class='ui-widget-content ui-selectee']");
	
	public static Logger log= Logger.getLogger(Ruthra_Selectable.class.getName());
	
  public void selectable(){
	  
	  driver.get("https://demoqa.com/selectable/");
	  
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
	  
	  WebElement element = findElement(title);	  
	  Assert.assertTrue(element.isDisplayed(),"Element is not displayed");
	  
	  List<WebElement> list = driver.findElements(itemList);
	  
	  //log.info(list.size());
	  
	  for (int i = 0; i < list.size(); i++) {
		
		  list.get(i).click();
		  log.info(list.get(i).getText());
		  //log.info(" print i "+i );
		  log.info(list.get(i).getText()+" is clicked ");
		  
	}
	  
	    
	
  }
	

}
