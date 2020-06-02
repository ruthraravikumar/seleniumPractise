package com.wipro.PageObjectModel;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.wipro.BaseTest;

public class MMTFlightReviewPage extends BaseTest{
	
	public static By reviewYourBookingHeading = By.xpath("//h4[@class='font22 latoBold whiteText headerTitle']");
	public  By departureFlightNumber = By.xpath("(//div[@class='pull-left airways-info-sect'])[1]");
	public  By returnFlightNumber = By.xpath("(//div[@class='pull-left airways-info-sect'])[2]");
	public  By depatureDateXpath = By.xpath("(//div[@class='rvw-labelView-block'])[1]");
	public  By returnDateXpath = By.xpath("(//div[@class='rvw-labelView-block'])[2]");
	public  By departureFromToPlace = By.xpath("(//div[@class='rvw-labelView-block']/following-sibling::div)[1]");
	public  By returnFromToPlace = By.xpath("(//div[@class='rvw-labelView-block']/following-sibling::div)[2]");

	public void flightReview(Map<String, String> flightInfoToReview, String deptDate, String returnDate,
			String fromToPlaceForDeparture, String fromToPlaceForReturn) {
		
		String infoOfDeptFlight = flightInfoToReview.get("DeptFlightInfo");
		String infoOfReturnFlight = flightInfoToReview.get("ReturnFlightInfo");
		
		log.info("Departure " + getText(departureFlightNumber) + "   " + infoOfDeptFlight);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getText(departureFlightNumber).contains(infoOfDeptFlight),
				"Departure Flight Info is not correct");
		softAssert.assertTrue(getText(returnFlightNumber).contains(infoOfReturnFlight),
				"Return Flight Info is not correct");
		softAssert.assertTrue(getText(depatureDateXpath).contains((deptDate)),
				"Departure date Info is not correct");
		softAssert.assertTrue(getText(returnDateXpath).contains((returnDate)),
				"Return Date Info is not correct");
		log.info(getText(departureFromToPlace)+""+fromToPlaceForDeparture);
		softAssert.assertTrue(
				getText(departureFromToPlace).contains((fromToPlaceForDeparture)),
				"Onward Journey source and destination Info is not correct");
		log.info(getText(returnFromToPlace)+""+fromToPlaceForReturn);
		softAssert.assertTrue(getText(returnFromToPlace).contains((fromToPlaceForReturn)),
				"Return Journey source and destination Info is not correct");
		softAssert.assertAll();

		log.info("Flight Information has been reviewed and all are correct");
	}

}
