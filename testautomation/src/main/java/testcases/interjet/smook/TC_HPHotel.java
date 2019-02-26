package testcases.interjet.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.interjet.Provider;
import pages.interjet.Pages;
import testbases.interjet.TB_Smook;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPHotel extends TB_Smook{
	@Test (enabled=true, priority = 1,dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotel(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPHotelDefault");
		logger.info("Starting test HPHotelDefault");
		Pages.homePage().widgetSearchHotel(voResData);
		Pages.homePage().widgetClickSearchButton();
		Pages.hotelListPage().listSelectFirstHotelAvailable();
		Pages.resDetailPage().removeAddOns();
		Pages.resDetailPage().clickContinue();
		Pages.infoViajeroPage().clearAndFillForm(voClient);
		Pages.infoViajeroPage().clickOnContinue();
		Pages.payMethodPage().fillCreditForm(voCreditCard);
		//Pages.payMethodPage().clickOnCompleteReservation();
		//Pages.thankYouPage().verifyCheckOutCompleted();
	}
}
