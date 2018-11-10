package FrameworkConfig;

public class GeneralConfig {
	//Paths
	public static final String DATASOURCE_PATH = "";
	public static final String SCREENSHOOT_PATH = "";
	
	//BrowserConfig
	public static final String DEFAULTBROWSER = "chrome";
	public static final boolean BROWSER_CANCELLNOTIFICATION = true;
	
	//Enums
	public static enum Products{
		HOTELES,PAQUETES,VUELOS,TRASLADOS,TOURS,AUTOS,AUTOBUSES,CRUCEROS
	}
	public static enum Days{
		SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
	}
	public static enum payNetworks{
		VISA,MASTERCARD,AMERICANEXPRESS,DINERSCLUB
	}
}
