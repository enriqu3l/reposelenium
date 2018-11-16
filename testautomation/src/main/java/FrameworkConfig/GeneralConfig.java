package FrameworkConfig;

public class GeneralConfig {
	
	//Environments
	public static final String TESTENV = "http://test.pricetravel.com.mx";
	public static final String PRODENV = "https://www.pricetravel.com.mx";
	
	//Resources Paths
	public static final String DATASOURCE_PATH = "C:\\PTFrameworkData\\SourceDataFiles\\";
	public static final String SCREENSHOOT_PATH = "C:\\PTFrameworkData\\Screenshots\\";
	
	//BrowserConfig
	public static final String DEFAULTBROWSER = "chrome";
	public static final boolean BROWSER_CANCELLNOTIFICATION = true;
	
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
