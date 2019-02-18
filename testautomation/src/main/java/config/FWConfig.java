package config;

public class FWConfig {
	
	//About Hotel List
	public static final int TOTALRECORDSPERPAGES = 20;
	
	//Waits for each Framework
	public static final int WAIT_PT = 40;
	public static final int WAIT_INTERJET = 40;
	public static final int WAIT_PT_MOBILE = 40;
	//Waits for AjaxElementLocatorFactory (PageFactory)
	public static final int WAITPF_PT = 30;
	public static final int WAITPF_INTERJET = 30;
	public static final int WAITPF_PT_MOBILE = 30;
	
	//Default Reservation Data
	public static final String R_ID = "HR001";
	public static final String R_ORIGIN = "Ciudad de M�xico (y alrededores), Distrito Federal, M�xico";
	public static final String R_DESTIN = "Canc�n (y alrededores), M�xico";
	public static final int R_ADULTS = 2;
	public static final int R_KIDS = 0;
	public static final int R_STARTDATE_PLUSMONTH = 1;
	public static final int R_ENDDATE_RESDAYS = 2;
	
	//URL - Environments 
	public static final String URL_PTCOMMX_TEST = "http://test.pricetravel.com.mx";
	public static final String URL_PTCOMMX_PROD = "https://www.pricetravel.com.mx";
	public static final String URL_PTCOM_PROD = "https://www.pricetravel.com";
	public static final String URL_PTCO_PROD = "https://www.pricetravel.co";
	
	//Resources Paths
	public static final String PATH_DATASOURCE = "C:\\PTFrameworkData\\SourceDataFiles\\";
	public static final String PATH_SCREENSHOOT = "C:\\PTFrameworkData\\Screenshots\\";
	public static final String PATH_SCREENSHOOT_FAILURES = "C:\\PTFrameworkData\\Screenshots\\Failures\\";
	public static final String PATH_SCREENSHOOT_LOCATORS = "C:\\PTFrameworkData\\Screenshots\\Locators\\";
	
	//Files Names
	public static final String FILE_CLIENTDATA = "ClientData.xlsx";
	public static final String FILE_CREDITCARDSDATA = "CreditCardsData.xlsx";
	public static final String FILE_LOCATORSGENERATED = "LocatorsGenerated.xlsx";
	public static final String FILE_HPHOTELRESDATA = "HPHotelResData.xlsx";
	public static final String FILE_REGRESSIONHOTELRESDATA = "RegressionHotelResData.xlsx";
	public static final String FILE_HOTELLANDINGPAGEDATA = "HotelLandingPageData.xlsx";
	public static final String FILE_INTERJETHOTELRESDATA = "InterjetHotelResData.xlsx";
	public static final String FILE_HPPACKAGERESDATA = "HPPackageResData.xlsx";
	
	//BrowserConfig
	public static final String BROWSER_CHROME = "chrome";
	public static final String BROWSER_FIREFOX = "firefox";
	public static final String BROWSER_IE = "ie";
	public static final String BROWSER_OPERA = "opera";
	public static final String BROWSER_DEFAULT = "chrome";
	public static final boolean BROWSER_CANCELNOTIFICATIONS = true;
	public static final int BROWSER_PAGELOADTIMEOUT = 40;
	
	//Enums
	public static enum Products{
		Hotels,Paquetes,Vuelos,Traslados,Tours,Autos,Autobuses,Cruceros
	}
	public static enum Days{
		SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
	}
	public static enum CardTypes{
		VISA,MASTERCARD,AMERICANEXPRESS,DINERSCLUB
	}
}