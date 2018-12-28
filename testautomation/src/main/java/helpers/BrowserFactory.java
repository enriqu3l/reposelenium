package helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

//Para poder usar BrowserFactory es necesario setear el path de los drivers
//en las variables del sistema

public class BrowserFactory {
	public static WebDriver driver;
	
	public static WebDriver StartBrowser(String BrowserName, String url) {
		if(BrowserName.equals("firefox")) {
			//Desabilitando las notificaciones
			FirefoxProfile profile =new FirefoxProfile();
			profile.setPreference("dom.webnotifications.enabled", false);
			FirefoxOptions option=new FirefoxOptions();
			option.setProfile(profile);
			
			driver = new FirefoxDriver(option);
		}
		else if(BrowserName.equals("chrome")) {
			
			//Opening chrome with notification disabled
			ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--disable-notifications");
			driver = new ChromeDriver(ops);
		}
		else if(BrowserName.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		return driver;
	}
}
