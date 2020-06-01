package com.wipro.Ass2;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.wipro.BaseTest;
import com.wipro.PageObjectModel.MMMFlightSearchPage;
import com.wipro.PageObjectModel.MMTFlightReviewPage;
import com.wipro.PageObjectModel.MMTFlightSelectionPage;

@Test
public class Ruthra_MakeMyTrip extends BaseTest{
	
	
	MMMFlightSearchPage flightSearchPg= new MMMFlightSearchPage();
	MMTFlightSelectionPage flightSelectionPage= new MMTFlightSelectionPage();
	MMTFlightReviewPage reviewPage= new MMTFlightReviewPage();
	
	public void MMMFightBooking() throws InterruptedException{
		driver.manage().deleteAllCookies();
		  driver.get("https://www.makemytrip.com/");
		  flightSearchPg.selectLocationDetails(getProperty("fromCity"), getProperty("fromCityFull"), getProperty("toCity"), getProperty("toCityFull"));
		  flightSearchPg.selectDate(getProperty("depDate"), getProperty("returnDate"),getProperty("month"));
		  
		  HashMap<String, String> flightInfoForReview=flightSelectionPage.selectingCheapestOnwardAndReturnFlightFromList(getProperty("departureData"), getProperty("returnData") ,getProperty("filter"));
		  
		  reviewPage.flightBookingReview(flightInfoForReview, "dateOfDept", "dateOfReturn",
					"fromToPlaceForDeparture", "fromToPlaceForReturn");
	}

}
