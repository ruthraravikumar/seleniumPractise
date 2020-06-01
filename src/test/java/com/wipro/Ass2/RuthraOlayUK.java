package com.wipro.Ass2;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;
import com.wipro.Excel;

@Test
public class RuthraOlayUK extends BaseTest {
	
	public static Logger log= Logger.getLogger(RuthraOlayUK.class.getName());
	
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
	
	By emailSignIn= By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_username']");
	By passSignIn=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_password']");
	By signInButton =By.xpath("//input[@name='phdesktopbody_0$SIGN IN']");
	
	By profileDetail=By.xpath("//h1[contains(text(),'YOUR PROFILE')]");
	By signOut=By.xpath("//a[@class='logoutbtn']");
	By logoutBtn=By.xpath("//a[@class='continue-btn btn event_profile_logout']");
	
	By signInBtnGlobal=By.xpath("//a[@class='event_profile_login']");
	
	public void olayUKRegistration() throws IOException {
		  driver.manage().deleteAllCookies();
		  driver.get("https://www.olay.co.uk/en-gb");
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
		  
		  
		  click(registerLink);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(emailXpath));
		  
		  Excel excel= new Excel();
		  
		  Map<String, String> map = excel.retriveExcel();
		  
		  Random random= new Random();
		  int randNum=random.nextInt(1000);
		  
		  String mail=map.get("EmailName");
		  String pass= map.get("Password");
		  String date=map.get("D.o.B");
		  String firstN=map.get("FirstName");
		  String lastN= map.get("LastName");
		  
		 String email=mail.substring(0, 7)+randNum+mail.substring(7);
		 enterText(emailXpath, email);
		 
		 String password=pass+randNum;
		 enterText(passwordXpath, password);
		 enterText(confirmPassXpath, password);
		 
		 if(isDisplayed(popUpCrossXpath)){
			 click(popUpCrossXpath);
		 }
		 
		 if (isDisplayed(rejectCookie)) {
				click(rejectCookie);
			}
		 
		 Select day= new Select(driver.findElement(selectDay));
		 day.selectByValue(date.substring(7));
		 
		 
		 Select month= new Select(driver.findElement(selectMonth));
		 month.selectByIndex(Integer.parseInt(date.substring(5, 6)));
		 
		
		 Select year= new Select(driver.findElement(selectYear));
		 year.selectByVisibleText(date.substring(0, 4));
		 
		
		 wait.until(ExpectedConditions.elementToBeClickable(registerButton));
		
		 click(registerButton);
		 	 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameXpath));
		 
		 enterText(firstNameXpath, firstN);
		 enterText(lastNameXpath, lastN);
		 click(addToProfileXpath);
		  
		 fluentwait(registerSucessMsg);
		  
		 Assert.assertTrue(isDisplayed(registerSucessMsg),"Registered is not sucessfully");
		
		  //checking for signIn
		 enterText(emailSignIn, email);
		 enterText(passSignIn, password);
		 click(signInButton);
		 
		 Assert.assertTrue(isDisplayed(profileDetail), "Profile Details is not displayed");
		 click(signOut);
		 fluentwait(logoutBtn);
		 click(logoutBtn);
		 
         click(signOut);
		 fluentwait(logoutBtn);
		 click(logoutBtn);
		 
		 
		 fluentwait(signInBtnGlobal);
		 click(signInBtnGlobal);
		 
		 //checking for signIn again
		 enterText(emailSignIn, email);
		 enterText(passSignIn, password);
		 click(signInButton);
		 
		 Assert.assertTrue(isDisplayed(profileDetail), "Profile Details is not displayed");
		 click(signOut);
		 
		 fluentwait(logoutBtn);
		 click(logoutBtn);
		 
	}

}
