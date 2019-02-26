package testcases.pt.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.pt.Provider;
import pages.pt.Pages;
import testbases.pt.TB_Smook;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPHotel extends TB_Smook{
	
	@Test (enabled=true, priority = 1, dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotel(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPHotel");
		logger.info("Starting test HPHotel");
		Pages.home().widget.searchHotel(voResData);
		Pages.home().widget.clickSearchHotelButton();
		Pages.hotelList().list.selectFirstHotelAvailable();
		Pages.roomList().selectFirstRoom();
		Pages.resDetail().clearAndFillForm(voClient);
		//Pages.resDetail().clickOnContinue();
		//Pages.payMethod().fillCreditForm(voCreditCard);
		//Pages.payMethod().clickOnCompleteReservation();
		//Pages.thankYou().verifyCheckOutCompleted();
	}
}
