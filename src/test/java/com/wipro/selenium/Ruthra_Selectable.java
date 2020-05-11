package com.wipro.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

@Test
public class Ruthra_Selectable  extends BaseTest {
	
	By title=By.xpath("//h1[@class='entry-title']");
	By itemList =By.xpath("//li[@class='ui-widget-content ui-selectee']");
	
	
  public void selectable(){
	  
	  driver.get("https://demoqa.com/selectable/");
	  
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
	  
	  WebElement element = findElement(title);	  
	  Assert.assertTrue(element.isDisplayed(),"Element is not displayed");
	  
	  List<WebElement> list = driver.findElements(itemList);
	  
	  //System.out.println(list.size());
	  
	  for (int i = 0; i < list.size(); i++) {
		
		  list.get(i).click();
		  System.out.println(list.get(i).getText());
		  //System.out.println(" print i "+i );
		  System.out.println(list.get(i).getText()+" is clicked ");
		  
	}
	  
	    
	
  }
	

}
