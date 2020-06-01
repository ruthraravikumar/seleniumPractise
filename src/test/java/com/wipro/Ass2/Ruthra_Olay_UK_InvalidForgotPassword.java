package com.wipro.Ass2;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wipro.BaseTest;

@Test
public class Ruthra_Olay_UK_InvalidForgotPassword extends BaseTest {
	
	public static Logger log= Logger.getLogger(RuthraOlayUK.class.getName());
	
	By emailSignIn= By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_username']");
	By passSignIn=By.xpath("//input[@name='phdesktopbody_0$phdesktopbody_0_password']");
	By signInButton =By.xpath("//input[@name='phdesktopbody_0$SIGN IN']");
	By signInBtnGlobal=By.xpath("//a[@class='event_profile_login']");
	By registerLink=By.xpath("//a[contains(text(),'Register')]");
	By rejectCookie=By.xpath("//button[@id='onetrust-reject-all-handler']");
	By popUpCrossXpath=By.xpath("//span[@class='close-icon event_button_click']//img");
	By errorMsg= By.xpath("//span[@class='pc_error-message2']");
	By forgotPass=By.xpath("//a[@class='forgotpwd']");
	By nextBtn=By.xpath("//input[@name='phdesktopbody_0$NEXT']");
	By resetPassMsg=By.xpath("//div[@id='phdesktopbody_0_afterSubmit']");
	
	
	public void olayUKInvalidSignIn(){
		  driver.manage().deleteAllCookies();
		  driver.get("https://www.olay.co.uk/en-gb");
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
		  
		  fluentwait(signInBtnGlobal);
			 click(signInBtnGlobal);
			 
			 if(isDisplayed(popUpCrossXpath)){
				 click(popUpCrossXpath);
			 }
			 
			 if (isDisplayed(rejectCookie)) {
					click(rejectCookie);
				}
			 
			 //checking for signIn again
			 enterText(emailSignIn, getProperty("validEmail"));
			 enterText(passSignIn, getProperty("invalidPassword"));
			 click(signInButton);
		
			 Assert.assertEquals(getText(errorMsg), getProperty("errorMessageForInvalidPassword"),"Login functionality is not correctly working with Inavlid Password ");
			 
			 
	}
	
	
	public void olayUKForgotPassword(){
		  driver.manage().deleteAllCookies();
		  driver.get("https://www.olay.co.uk/en-gb");
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
		  
		  fluentwait(signInBtnGlobal);
			 click(signInBtnGlobal);
			 
			 fluentwait(emailSignIn);
			 
			 if(isDisplayed(popUpCrossXpath)){
				 click(popUpCrossXpath);
			 }
			 
			 if (isDisplayed(rejectCookie)) {
					click(rejectCookie);
				}
			 
			 click(forgotPass);
			 fluentwait(emailSignIn);
			 enterText(emailSignIn, getProperty("validEmail"));
			 click(nextBtn);
		
			 fluentwait(resetPassMsg);
			 Assert.assertTrue(getText(resetPassMsg).contains(getProperty("messageForResetPassword")),"Reset Password functionality is not correctly working");
			 
			 
	}

}
