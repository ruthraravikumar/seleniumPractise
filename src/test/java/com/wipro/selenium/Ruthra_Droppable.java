package com.wipro.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;

@Test
public class Ruthra_Droppable extends BaseTest{
	
	By title = By.xpath("//h1[@class='entry-title']");
	By draggable = By.xpath("//div[@id='draggable']");
	By droppable=By.xpath("//div[@id='droppable']");
	
	public static Logger log= Logger.getLogger(Ruthra_Droppable.class.getName());
	
	
	public void Droppable() {
		  
		  driver.get("https://demoqa.com/droppable/");
		  
		  WebDriverWait wait = new WebDriverWait(driver, 30);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		  
		  WebElement element = findElement(title);	  
		  Assert.assertTrue(element.isDisplayed(),"Title is not displayed");
		  
		  String  dragText= getText(draggable);
		  log.info(dragText);
		  Assert.assertEquals(dragText, "Drag me to my target");
		  
		  String  dropText= getText(droppable);
		  log.info(dropText);
		  Assert.assertEquals(dropText, "Drop here");
		  
		  WebElement from = findElement(draggable);
		  
		  WebElement to = findElement(droppable);
		  
		  
		Actions action = new Actions(driver);
		action.dragAndDrop(from, to).build().perform();
		  
	
		String  droppedText= getText(droppable);
		  log.info(droppedText);
		  Assert.assertEquals(droppedText, "Dropped!");
		 
	}
	
	
	

}
