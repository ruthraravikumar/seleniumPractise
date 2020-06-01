package com.wipro.Ass2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;
import com.wipro.Excel;

@Test
public class Ruthra_Olay_Germany extends BaseTest{
	
	public static Logger log= Logger.getLogger(Ruthra_Olay_Germany.class.getName());
	
	By registerLink = By.xpath("//a[contains(text(),'Registrieren')]");
	By femaleImg=By.id("phdesktopbody_0_imgfemale");
	By firstName=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[firstname]']");
	By lastName=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_consumer[lastname]']");
	By emailXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[emails][0][address]']");
	By passwordXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[password][password]']");
	By confirmPassXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[password][confirm]']");
	By selectDay=By.name("phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][day]");
	By selectMonth=By.name("phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][month]");
	By selectYear=By.name("phdesktopbody_0$phdesktopbody_0_grs_consumer[birthdate][year]");
	By LandXpath=By.name("phdesktopbody_0$phdesktopbody_0_labelgrs_account[addresses][0][country]");
	By houseNoXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_labelgrs_account[addresses][0][line1]']");
	By postalCode=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_grs_account[addresses][0][postalarea]']");
	By LocationXpath=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_labelgrs_account[addresses][0][city]']");
	By registerButton=By.name("phdesktopbody_0$phdesktopbody_0_submit");
	By registerSucessMsg=By.xpath("//h1[contains(text(),'Your Registration Is Complete')]");
	By acceptCookie=By.xpath("//button[@id='onetrust-accept-btn-handler']");
	
	public void olayGermany() throws IOException, ParseException{
		
		
		
		driver.manage().deleteAllCookies();
		driver.get("https://www.olaz.de/de-de");
		
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\DataFiles\\datafile.json");
		Object obj= new JSONParser().parse(file);
		JSONObject json= (JSONObject) obj; 
		
		
		
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
		  
		  click(registerLink);
		  if (isDisplayed(acceptCookie)) {
				click(acceptCookie);
			}
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
		 
		  click(femaleImg);
		 
		 // wait.until(ExpectedConditions.elementToBeClickable(firstName));
		  
		  String fName=(String) json.get("FirstName");
		  String lName=(String) json.get("LastName");
		  String EmailName=(String) json.get("EmailName");
		  String EmailHost=(String) json.get("EmailHost");
		  String Password=(String) json.get("Password");
		  String DayVale=(String) json.get("Day");
		  String YearValue=(String) json.get("Year");
		  String CountryValue=(String) json.get("Country");
		  String HouseNo=(String) json.get("HouseNo");
		  String PostCode=(String) json.get("PostCode");
		  String Location=(String) json.get("Location");
		  
		  enterText(firstName, fName);
		  enterText(lastName, lName);
		  
		  Random random= new Random();
		  int randNum=random.nextInt(1000);
		  
		 String email=EmailName+randNum+EmailHost;
		 enterText(emailXpath, email);
		 
		 String password=Password+randNum;
		 enterText(passwordXpath, password);
		 enterText(confirmPassXpath, password);
		  
		 
		 Select day= new Select(driver.findElement(selectDay));
		 day.selectByValue(DayVale);
		 
		 Select month= new Select(driver.findElement(selectMonth));
		 month.selectByIndex(5);
		 
		 Select year= new Select(driver.findElement(selectYear));
		 year.selectByVisibleText(YearValue);
		 
		 Select country= new Select(driver.findElement(LandXpath));
		 country.selectByValue(CountryValue);
		 
		 enterText(houseNoXpath, HouseNo);
		 enterText(postalCode, PostCode);
		 enterText(LocationXpath, Location);
		 
		 click(registerButton);
		 
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(registerSucessMsg));
		  
		  Assert.assertTrue(isDisplayed(registerSucessMsg),"Registered is not sucessfully");
		  
	}

}
