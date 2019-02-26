package testcases.tb.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.tb.Provider;
import pages.tb.Pages;
import testbases.tb.TB_Smook;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPHotel extends TB_Smook{

	@Test (enabled=true, priority = 1, dataProvider = "hpHotelProvider", dataProviderClass = Provider.class)
	public void HPHotel(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPHotel");
		logger.info("Starting test HPHotel");
		Pages.home().searchHotel(voResData);
		Pages.hotelList().selectFirstHotel();
		Pages.roomList().selectRoomDefault();
		Pages.resDetail().clearAndFillForm(voClient);
		Pages.resDetail().clickOnContinue();
		Pages.payMethod().fillCreditForm(voCreditCard);
		//Pages.payMethod().clickOnCompleteReservation();
		//Pages.thankYou().verifyCheckOutCompleted();
	}
}
