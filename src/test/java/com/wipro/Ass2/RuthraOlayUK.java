package com.wipro.Ass2;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;

@Test
public class RuthraOlayUK extends BaseTest {
	
	By registerLink=By.xpath("//a[contains(text(),'Register')]");
	By emailXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[emails][0][address]']");
	By passwordXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[password][password]']");
	By confirmPassXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[password][confirm]']");
	By popUpCrossXpath=By.xpath("//span[@class='close-icon event_button_click']//img");
	By selectDay=By.name("phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][day]");
	By selectMonth=By.name("phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][month]");
	By selectYear=By.name("phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][year]");
	By registerButton=By.name("phdesktopbody_0$phdesktopbody_0_submit");
	
	By firstNameXpath=By.xpath("//input[@name='phdesktopbody_1$phdesktopbody_0_grs_consumer[firstname]']");
	By lastNameXpath=By.xpath("//input[@name='phdesktopbody_1$phdesktopbody_0_grs_consumer[lastname]']");
	By addToProfileXpath=By.xpath("//input[@name='phdesktopbody_1$phdesktopbody_0_SubmitBtn']");
	
	By registerSucessMsg=By.xpath("//h1[contains(text(),'Your Registration Is Complete')]");
	By rejectCookie=By.xpath("//button[@id='onetrust-reject-all-handler']");
	
	public void olayUKRegistration() {
		  driver.manage().deleteAllCookies();
		  driver.get("https://www.olay.co.uk/en-gb");
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
		  
		  
		  click(registerLink);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(emailXpath));
		  
		  Random random= new Random();
		  int randNum=random.nextInt(1000);
		  
		 String email="rrrruth"+randNum+"@gmail.com";
		 enterText(emailXpath, email);
		 
		 String password="Ravikumar!"+randNum;
		 enterText(passwordXpath, password);
		 enterText(confirmPassXpath, password);
		 
		 if(isDisplayed(popUpCrossXpath)){
			 click(popUpCrossXpath);
		 }
		 
		 if (isDisplayed(rejectCookie)) {
				click(rejectCookie);
			}
		 
		 Select day= new Select(driver.findElement(selectDay));
		 day.selectByValue("22");
		 
		 Select month= new Select(driver.findElement(selectMonth));
		 month.selectByIndex(5);
		 
		 Select year= new Select(driver.findElement(selectYear));
		 year.selectByVisibleText("2001");
		 
		
		 wait.until(ExpectedConditions.elementToBeClickable(registerButton));
		
		 click(registerButton);
		 	 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameXpath));
		 
		 enterText(firstNameXpath, "Sundar");
		 enterText(lastNameXpath, "Ruth");
		  click(addToProfileXpath);
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(registerSucessMsg));
		  
		  Assert.assertTrue(isDisplayed(registerSucessMsg),"Registered is not sucessfully");
		
	}

}
