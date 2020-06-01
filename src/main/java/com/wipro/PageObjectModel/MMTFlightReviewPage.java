package com.wipro.PageObjectModel;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.wipro.BaseTest;

public class MMTFlightReviewPage extends BaseTest{
	
	public static By reviewYourBookingHeadingText = By.xpath("//h4[@class='font22 latoBold whiteText headerTitle']");
	public static By departureFlightNumberReview = By.xpath("(//div[@class='pull-left airways-info-sect'])[1]");
	public static By returnFlightNumberReview = By.xpath("(//div[@class='pull-left airways-info-sect'])[2]");
	public static By depatureDateReview = By.xpath("(//div[@class='rvw-labelView-block'])[1]");
	public static By returnDateReview = By.xpath("(//div[@class='rvw-labelView-block'])[2]");
	public static By departureFromAndToPlaceReview = By.xpath("(//div[@class='rvw-labelView-block']/following-sibling::div)[1]");
	public static By returnFromAndToPlaceReview = By.xpath("(//div[@class='rvw-labelView-block']/following-sibling::div)[2]");

	public void flightBookingReview(HashMap<String, String> flightInfoToReview, String deptDate, String returnDate,
			String fromToPlaceForDeparture, String fromToPlaceForReturn) {
		
		String infoOfDeptFlight = flightInfoToReview.get("DeptFlightInfo");
		String infoOfReturnFlight = flightInfoToReview.get("ReturnFlightInfo");
		
		log.info("Departure " + getText(departureFlightNumberReview) + "   " + infoOfDeptFlight);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getText(departureFlightNumberReview).contains(infoOfDeptFlight),
				"Departure Flight Info is not correct");
		softAssert.assertTrue(getText(returnFlightNumberReview).contains(infoOfReturnFlight),
				"Return Flight Info is not correct");
		softAssert.assertTrue(getText(depatureDateReview).contains(getProperty(deptDate)),
				"Departure date Info is not correct");
		softAssert.assertTrue(getText(returnDateReview).contains(getProperty(returnDate)),
				"Return Date Info is not correct");
		softAssert.assertTrue(
				getText(departureFromAndToPlaceReview).contains(getProperty(fromToPlaceForDeparture)),
				"Onward Journey source and destination Info is not correct");
		softAssert.assertTrue(getText(returnFromAndToPlaceReview).contains(getProperty(fromToPlaceForReturn)),
				"Return Journey source and destination Info is not correct");
		softAssert.assertAll();

		log.info("Flight Information has been reviewed and all are correct");
	}

}
