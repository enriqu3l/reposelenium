package dataproviders.tb;

import org.testng.annotations.DataProvider;

import config.FWConfig;
import helpers.DDManager;
import valueobjects.VOClient;
import valueobjects.VOCreditCard;
import valueobjects.VOResData;

public class Provider {
	@DataProvider(name="hpHotelProvider")
	public Object[][] hpHotelProvider() {
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPHOTELRESDATA);
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		return new Object[][] {
			   { voResData,voClient,voCreditCard }
			 };
	}
	
	@DataProvider(name="hpPaqueteProvider")
	public Object[][] hpPaqueteProvider() {
		VOResData voResData = DDManager.getResDataDefault(FWConfig.FILE_HPPACKAGERESDATA);
		VOClient voClient = DDManager.getClientDataDefault(FWConfig.FILE_CLIENTDATA);
		VOCreditCard voCreditCard = DDManager.getCreditCardDefault();
		return new Object[][] {
			   { voResData,voClient,voCreditCard }
			 };
	}
}
