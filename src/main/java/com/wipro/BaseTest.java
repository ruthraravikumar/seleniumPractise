package com.wipro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public static WebDriver driver;
	public Properties properties;
	public WebDriverWait wait ;
	String driverName;

	public static Logger log= Logger.getLogger(BaseTest.class.getName());
	
	@BeforeSuite
	public void loadPropFile(){
		
	}
	
	@BeforeClass
	public void startUp(){
		try {
			properties = new Properties();
			FileInputStream  fileObj = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\DataFiles\\application.properties");
		    properties.load(fileObj);
		    
		    log.info("Property file is initiated successfully" );
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("In Before Test");
		
		driverName=properties.getProperty("Driver");
		log.info("driver is :"+driverName);
		
		if(driverName.equalsIgnoreCase("Chrome"))
		{
		//System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}
		
		else if(driverName.equalsIgnoreCase("FireFox")){
			log.info("Inside firefox");
		//	 System.setProperty("webdriver.gecko.driver", "geckodriver.exe");  
//			 DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
//			 desiredCapabilities.setCapability("marionette", true);
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
		log.info("In After Test");
		
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
		 log.info("Text entered is "+text);
	}
	
	public void click(By element)
	{
		driver.findElement(element).click();
		log.info("Element is clicked");
	}
	
	public String getText(By element)
	{
		return driver.findElement(element).getText();
		 
	}
	
	public void selectList(String selectText,By xpath){
		List<WebElement> listFile= driver.findElements(xpath);		  
		  for (WebElement webElement : listFile) {
			  
			 String text=webElement.getText();
			 log.info(text);
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
		
		log.info(text);
		
		return text;
	}*/
	
	
	public String getProperty(String keyword){
		
		return properties.getProperty(keyword);
	}
	
	public void selectRadioButton(By locator){
		boolean result=true;
         Actions action= new Actions(driver);
         result = driver.findElement(locator).isSelected();
 		if (result == false) {
		action.moveToElement(driver.findElement(locator)).click().build().perform();
 		}
	}
	
	public void selectFromListDropDown(By locator,String value){
		Actions a = new Actions(driver);
		List<WebElement> element=driver.findElements(locator);
		
		Iterator<WebElement> itr=element.iterator();
		while (itr.hasNext()){
			WebElement ele=itr.next();
			
			if(ele.getText().equals(value)){
				a.moveToElement(ele).click().build().perform();
				break;
			}
		}
		
		
		
	}
	
	public void actionMoveToElementClick(final By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).click().build().perform();
	}
	
	public void switchingToNewTabOrWindow() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentWindow = it.next();
		System.out.println(parentWindow);
		String childWindow = it.next();
		System.out.println(childWindow);
		driver.switchTo().window(childWindow);
	}
}
