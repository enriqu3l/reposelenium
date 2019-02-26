package testcases.pt.smook;

import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.pt.Provider;
import pages.pt.Pages;
import testbases.pt.TB_Smook;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class TC_HPPaquete extends TB_Smook{
	
	@Test (enabled=true, priority = 1, dataProvider = "hpPaqueteProvider", dataProviderClass = Provider.class)
	public void HPPackage(VOResData voResData, VOClient voClient, VOCreditCard voCreditCard){
		Reporter.log("Starting test HPPackage");
		logger.info("Starting test HPPackage");
		Pages.home().widget.searchPackage(voResData);
		Pages.home().widget.clickSearchPackageButton();
		Pages.packageList().listSelectFirstHotelAvailable();
		Pages.packageRoomList().selectFirstRoom();
		Pages.packageResDetail().clearAndFillForm(voClient);
		//Pages.packageResDetail().clickContinue();
		//Pages.payMethod().fillCreditForm(voCreditCard);
		//Pages.payMethod().clickOnCompleteReservation();
		//Pages.thankYou().verifyCheckOutCompleted();
	}
}
