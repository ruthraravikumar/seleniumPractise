package com.wipro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BaseTest {
	
	public static WebDriver driver;
	public Properties properties;
	public WebDriverWait wait ;
	String driverName;

	
	@BeforeSuite
	public void loadPropFile(){
		
	}
	
	@BeforeClass
	public void startUp(){
		try {
			properties = new Properties();
			FileInputStream  fileObj = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\DataFiles\\application.properties");
		    properties.load(fileObj);
		    
		    System.out.println("Property file is initiated successfully" );
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("In Before Test");
		
		driverName=properties.getProperty("Driver");
		System.out.println("driver is :"+driverName);
		
		if(driverName.equalsIgnoreCase("ChromeDriver"))
		{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}
		
		else if(driverName.equalsIgnoreCase("FireFox")){
			 System.setProperty("webdriver.gecko.driver", "geckodriver.exe");   
		      driver= new FirefoxDriver();
		      driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			  driver.manage().window().maximize();
		}
		
		 wait = new WebDriverWait(driver, 30);
		
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
	
	@AfterClass
	public void tearUP(){
		System.out.println("In After Test");
		
		driver.quit();
	}
	
	public void fluentwait(By locator){
		
	FluentWait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver)							
	.withTimeout(60, TimeUnit.SECONDS) 			
	.pollingEvery(5, TimeUnit.SECONDS) 			
	.ignoring(NoSuchElementException.class);
	
	fluentwait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	
	}
	
	public WebElement findElement(By input){
		return driver.findElement(input);
		
	}
	
	public void enterText(By element , String text)
	{
		driver.findElement(element).click();
		 driver.findElement(element).sendKeys(text);
		 System.out.println("Text entered is "+text);
	}
	
	public void click(By element)
	{
		driver.findElement(element).click();
		 System.out.println("Element is clicked");
	}
	
	public String getText(By element)
	{
		return driver.findElement(element).getText();
		 
	}
	
	public void selectList(String selectText,By xpath){
		List<WebElement> listFile= driver.findElements(xpath);		  
		  for (WebElement webElement : listFile) {
			  
			 String text=webElement.getText();
			 System.out.println(text);
			  if (text.equals(selectText)){
				 webElement.click();
			  }
		  }
	}
	
	public boolean isDisplayed( By locator) {
		boolean found = false;
		
		try {
			if (driver.findElement(locator) != null)
				found = true;
		} catch (NoSuchElementException e) {
			found = false;
		}

		return found;
	}
	
	/*public String readFromJsonFile(String input) throws IOException, ParseException{
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\DataFiles\\datafile.json");
		Object obj= new JSONParser().parse(file);
		JSONObject json= (JSONObject) obj; 
		
		String text=(String) json.get(input);
		
		System.out.println(text);
		
		return text;
	}*/
	
	
}
