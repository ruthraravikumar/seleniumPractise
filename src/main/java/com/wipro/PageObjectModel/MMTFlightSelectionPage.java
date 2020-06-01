package com.wipro.PageObjectModel;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.wipro.BaseTest;




public class MMTFlightSelectionPage extends BaseTest {
	
	public static Logger log= Logger.getLogger(MMTFlightSelectionPage.class.getName());
	
	//public static By tripTypeDropdown = By.xpath(getProperty("TripTypeDropdown"));
	public static By departureFlightDetails = By.xpath("(//p[@class='LatoMedium'])[1]");
	public static By returnFlightDetails = By.xpath("(//p[@class='LatoMedium'])[2]");
	public static By sortedByOptionForOnwardJourney = By.xpath("//button[@id='sorter_btn_onward']");
	public static By sortByDropdownListForOnwardJourney = By.xpath("//div[contains(@class,'dropdown pull-right c-dropdown sortby-dropdown open')]//span[@class='sortByRTname'][contains(text(),'Price')]");
	//public static By sortByOptions = By.xpath(getProperty("SortByOptions"));
	public static By sortedByOptionForReturnJourney = By.xpath("//button[@id='sorter_btn_return']");
	public static By sortByDropdownListForReturnJourney = By.xpath("//div[contains(@class,'dropdown pull-right c-dropdown sortby-dropdown open')]//span[@class='sortByRTname'][contains(text(),'Price')]");
	public static By departureFlight = By.xpath("(//div[@id='ow-domrt-jrny']//span[@class='splitVw-outer append_right9'])[1]");
	public static By returnFlight = By.xpath("(//div[@id='rt-domrt-jrny']//span[@class='splitVw-outer append_right9'])[1]");
	public static By bookNowBtn = By.xpath("//button[@class='fli_primary_btn text-uppercase ']");
	public static By footerSectionDepartureFlightInfo = By.xpath("//div[@class='splitVw-footer-left']//span[@class='font10 prepend_left5']");
	public static By footerSectionReturnFlightInfo = By.xpath("//div[@class='splitVw-footer-right']//span[@class='font10 prepend_left5']");
	public static By continueBtn = By.xpath("//button[@class='btn fli_primary_btn']");

	public HashMap<String, String> selectingCheapestOnwardAndReturnFlightFromList(String departureData,
			String returnData, String sortByCriteria) throws InterruptedException {
		HashMap<String, String> flightDetails = new HashMap<String, String>();

		log.debug("Validating departure flight details");
		String departureFlightData = getText(departureFlightDetails);
		Assert.assertTrue(departureFlightData.contains((departureData)),
				"Departure flight Details are not correct");
		log.debug("Validating Return flight details");
		String returnFlightData = getText(returnFlightDetails);
		Assert.assertTrue(returnFlightData.contains((returnData)), "Return flight Details are not correct");
		log.info("Return flight Details are correct");
		log.debug("Selecting Price low-high in sort by options for departure flight");
		
		if (!(getText(sortedByOptionForOnwardJourney).contains((sortByCriteria)))) {
			click(sortedByOptionForOnwardJourney);
			click(sortByDropdownListForOnwardJourney);
			
		}
		log.debug("Selecting Price low-high in sort by options for return flight");
		if (!(getText(sortedByOptionForReturnJourney).contains((sortByCriteria)))) {
			click(sortedByOptionForReturnJourney);
			click(sortByDropdownListForReturnJourney);
		}
		
		log.debug("Selecting Onward journey flight");
		selectRadioButton(departureFlight);
		log.info("Onward Journey flight selected");
		log.debug("Selecting Return journey flight");
		selectRadioButton(returnFlight);
		log.info("Return Journey flight selected");
		Thread.sleep(3000);
		log.debug("Fetching Departure flight number and storing for review");
		flightDetails.put("DeptFlightInfo", getText(footerSectionDepartureFlightInfo));
		log.debug("Fetching return flight number and storing for review");
		flightDetails.put("ReturnFlightInfo", getText(footerSectionReturnFlightInfo));
		log.debug("Clicking on Book Now Button");
		actionMoveToElementClick(bookNowBtn);
		log.info("Book now button clicked");
		
		if (isDisplayed(continueBtn) == true) {
			log.debug("Clicking on continue button");
			click(continueBtn);
		}
		switchingToNewTabOrWindow();
		log.debug("Checking for an element in the new window to confirm navigation");
		isDisplayed(MMTFlightReviewPage.reviewYourBookingHeadingText);
		log.info("Successfully navigated to new tab");
		return flightDetails;
	}
	

}
