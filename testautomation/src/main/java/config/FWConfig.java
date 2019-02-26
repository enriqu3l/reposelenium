package config;

public class FWConfig {
	
	//About Hotel List
	public static final int TOTALRECORDSPERPAGES = 20;
	
	//Waits for each Framework
	public static final int WAIT_PT = 40;
	public static final int WAIT_TB = 40;
	public static final int WAIT_INTERJET = 40;
	public static final int WAIT_PT_MOBILE = 40;
	//Waits for AjaxElementLocatorFactory (PageFactory)
	public static final int WAITPF_PT = 30;
	public static final int WAITPF_TB = 30;
	public static final int WAITPF_INTERJET = 30;
	public static final int WAITPF_PT_MOBILE = 30;
	
	//URL - Environments 
	public static final String BASEURL_PTCOMMX_TEST = "http://test.pricetravel.com.mx";
	public static final String BASEURL_PTCOMMX_STAGE = "https://stage.pricetravel.com.mx";
	public static final String BASEURL_PTCOMMX_PROD = "https://www.pricetravel.com.mx";
	public static final String BASEURL_PTCOM_STAGE = "https://stage.pricetravel.com";
	public static final String BASEURL_PTCOMSPA_STAGE = "https://stage-spa.pricetravel.com";
	public static final String BASEURL_PTCOM_PROD = "https://www.pricetravel.com";
	public static final String BASEURL_PTCO_PROD = "https://www.pricetravel.co";
	
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
	public static final String FILE_HLLANDINGPAGEDATA = "HLLandingPageData.xlsx";
	public static final String FILE_RLLANDINGPAGEDATA = "HRLandingPageData.xlsx";
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
