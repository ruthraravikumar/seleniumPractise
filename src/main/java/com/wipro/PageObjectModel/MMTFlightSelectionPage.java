package com.wipro.PageObjectModel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.wipro.BaseTest;




public class MMTFlightSelectionPage extends BaseTest {
	
	public static Logger log= Logger.getLogger(MMTFlightSelectionPage.class.getName());
	
	
	public static By departureFlightDetails = By.xpath("(//p[@class='LatoMedium'])[1]");
	public  By returnFlightDetails = By.xpath("(//p[@class='LatoMedium'])[2]");
	public  By sortedByOptionForDeparture = By.xpath("//button[@id='sorter_btn_onward']");
	public  By sortByDropdownListForDeparture = By.xpath("//div[contains(@class,'dropdown pull-right c-dropdown sortby-dropdown open')]//span[@class='sortByRTname'][contains(text(),'Price')]");
	//public  By sortByOptions = By.xpath(getProperty("SortByOptions"));
	public  By sortedByOptionForReturn = By.xpath("//button[@id='sorter_btn_return']");
	public  By sortByDropdownListForReturn = By.xpath("//div[contains(@class,'dropdown pull-right c-dropdown sortby-dropdown open')]//span[@class='sortByRTname'][contains(text(),'Price')]");
	public  By departureFlight = By.xpath("(//div[@id='ow-domrt-jrny']//span[@class='splitVw-outer append_right9'])[1]");
	public  By returnFlight = By.xpath("(//div[@id='rt-domrt-jrny']//span[@class='splitVw-outer append_right9'])[1]");
	public  By bookNowBtn = By.xpath("//button[@class='fli_primary_btn text-uppercase ']");
	public  By footerSectionDepartureFlightInfo = By.xpath("//div[@class='splitVw-footer-left']//span[@class='font10 prepend_left5']");
	public  By footerSectionReturnFlightInfo = By.xpath("//div[@class='splitVw-footer-right']//span[@class='font10 prepend_left5']");
	public  By continueBtn = By.xpath("//button[@class='btn fli_primary_btn ']");

	public Map<String, String> selectingCheapestOnwardAndReturnFlightFromList(String departureData,
			String returnData, String sortByCriteria) throws InterruptedException {
		Map<String, String> flightDetails = new HashMap<String, String>();

		log.info("Validating departure flight details");
		String departureFlightData = getText(departureFlightDetails);
		log.info(departureFlightData+" "+departureData);
		Assert.assertTrue(departureFlightData.contains((departureData)),
				"Departure flight Details are not correct");
		log.info("Validating Return flight details");
		String returnFlightData = getText(returnFlightDetails);
		Assert.assertTrue(returnFlightData.contains((returnData)), "Return flight Details are not correct");
		log.info("Return flight Details are correct");
		log.info("Selecting Price low-high in sort by options for departure flight");
		
		if (!(getText(sortedByOptionForDeparture).contains((sortByCriteria)))) {
			click(sortedByOptionForDeparture);
			click(sortByDropdownListForDeparture);
			
		}
		log.info("Selecting Price low-high in sort by options for return flight");
		if (!(getText(sortedByOptionForReturn).contains((sortByCriteria)))) {
			click(sortedByOptionForReturn);
			click(sortByDropdownListForReturn);
		}
		
		log.info("Selecting Onward journey flight");
		selectRadioButton(departureFlight);
		log.info("Onward Journey flight selected");
		log.info("Selecting Return journey flight");
		selectRadioButton(returnFlight);
		log.info("Return Journey flight selected");
		Thread.sleep(3000);
		log.info("Fetching Departure flight number and storing for review");
		flightDetails.put("DeptFlightInfo", getText(footerSectionDepartureFlightInfo));
		log.info("Fetching return flight number and storing for review");
		flightDetails.put("ReturnFlightInfo", getText(footerSectionReturnFlightInfo));
		String parentWinHandle = driver.getWindowHandle();
		log.info("Clicking on Book Now Button");
		actionMoveToElementClick(bookNowBtn);
		log.info("Book now button clicked");
		
		
			log.info("Clicking on continue button");
			click(continueBtn);
		
		switchingToWindow(parentWinHandle);
		log.info("Checking for an element in the new window to confirm navigation");
		isDisplayed(MMTFlightReviewPage.reviewYourBookingHeading);
		log.info("Successfully navigated to new tab");
		return flightDetails;
	}
	

}
