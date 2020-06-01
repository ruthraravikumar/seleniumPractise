package com.wipro.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;
import com.wipro.Ass2.Ruthra_Olay_Germany;

@Test
public class Ruthra_ControlGroup extends BaseTest {

	public static Logger log= Logger.getLogger(Ruthra_ControlGroup.class.getName());
	
	By title=By.xpath("//h1[@class='entry-title']");
	By radio=By.xpath("//label[@for='transmission-automatic-v']");
	By checkbox=By.xpath("//label[@for='insurance-v']");
	By CarNo=By.xpath("//div[@class='controlgroup-vertical ui-controlgroup ui-controlgroup-vertical']//span[@class='ui-button-icon ui-icon ui-icon-triangle-1-n']");
	By button = By.xpath("//button[@id='book']");
	By dropdown=By.xpath("//span[@id='ui-id-8-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']");
	By  dropdownList= By.xpath("//div[@class='ui-menu-item-wrapper']");
	
	public void controlGroup(){
		
		driver.get("https://demoqa.com/controlgroup/");
		  
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(title));
		  
		  WebElement element = findElement(title);	  
		  Assert.assertTrue(element.isDisplayed(),"Title is not displayed");
		  
          click(dropdown);
		  selectList("Luxury",dropdownList);
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(radio));
		  WebElement radioelement= driver.findElement(radio);
		  radioelement.click();
		  
		  WebElement checkboxelement= driver.findElement(checkbox);
		  checkboxelement.click();
		  
		  WebElement carNum= driver.findElement(CarNo);
		  carNum.click();
		  carNum.click();
		  
		  driver.findElement(button).click();
		  
	
	}

}
