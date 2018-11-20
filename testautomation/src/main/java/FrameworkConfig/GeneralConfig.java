package FrameworkConfig;

public class GeneralConfig {
	
	//Default Reservation Data
	public static final String R_ID = "HR001";
	public static final String R_ORIGIN = "Ciudad de M�xico (y alrededores), Distrito Federal, M�xico";
	public static final String R_DESTIN = "Canc�n (y alrededores), M�xico";
	public static final int R_ADULTS = 2;
	public static final int R_KIDS = 0;
	public static final int R_STARTDATE_PlusMonth = 1;
	public static final int R_ENDDATE_PlusDay = 2;
	
	//URL - Environments 
	public static final String URL_TESTENV = "http://test.pricetravel.com.mx";
	public static final String URL_PRODENV_ = "https://www.pricetravel.com.mx";
	
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
