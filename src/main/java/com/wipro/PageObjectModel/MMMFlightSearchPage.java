package com.wipro.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import com.wipro.BaseTest;


public class MMMFlightSearchPage extends BaseTest {
	
	By roundTrip=By.xpath("//li[contains(text(),'Round Trip')]");
	By fromtab=By.xpath("//input[@id='fromCity']");
	By fromSearchBox=By.xpath("//input[@placeholder='From']");
	By suggestion=By.xpath("//p[@class='font14 appendBottom5 blackText']");
	By toTab=By.xpath("//input[@id='toCity']");
	By toSearchBox=By.xpath("//input[@placeholder='To']");
	By depDateBox=By.xpath("//div[contains(@class,'fsw_inputBox dates inactiveWidget')]//label");
	By returnDateBox=By.xpath("//div[contains(@class,'fsw_inputBox dates reDates inactiveWidget')]//div//label");
	By datePicker=By.xpath("//div[@class='dateInnerCell']");
	By searchBtn=By.xpath("//a[contains(@class,'primaryBtn font24 latoBold widgetSearchBtn')]");
	By monthPicker=By.xpath("(//div[@class='DayPicker-Caption'])[1]");
	By calendarNavigationArrow= By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']");
	
	
	public void selectLocationDetails(String fromCity,String fromCityFull,String toCity,String toCityFull) throws InterruptedException{
		
		selectRadioButton(roundTrip);
				
		click(fromtab);
		enterText(fromSearchBox, fromCity);
		
		fluentwait(suggestion);
		try{
		selectFromListDropDown(suggestion, fromCityFull);
	} catch (StaleElementReferenceException e) {
		log.info("Handled");
	}
		
		//click(toTab);
		enterText(toSearchBox, toCity);
		
		fluentwait(suggestion);
		Thread.sleep(3000);
		selectFromListDropDown(suggestion, toCityFull);
		
	}
	
	
	public void selectDate(String depDate,String returnDate,String month) throws InterruptedException{
		//click(depDateBox);
		
		
		
		//click(returnDateBox);
		
		
		
		log.debug("Selecting Departure date");
		while (!(getText(monthPicker).contains(month))) {
			click(calendarNavigationArrow);
		}
		log.info("Month successfully selected");
		log.info("Selecting departure date");
		try {
			selectFromListDropDown(datePicker, depDate);
		} catch (StaleElementReferenceException e) {
			log.info("Handled");
		}

		log.debug("Selecting return date");
		try {
			selectFromListDropDown(datePicker, returnDate);
		} catch (StaleElementReferenceException e) {
			log.info("Handled");
		}
		log.debug("Clicking on search");
		
		click(searchBtn);
		
		Thread.sleep(3000);
		
		log.debug("Checking for an element in next page to confirm correct page navigation");
		fluentwait(MMTFlightSelectionPage.departureFlightDetails);
		Assert.assertTrue(isDisplayed(MMTFlightSelectionPage.departureFlightDetails),"Page Navigation is not correct");
		log.info("Page navigation is correct");
	}

}
