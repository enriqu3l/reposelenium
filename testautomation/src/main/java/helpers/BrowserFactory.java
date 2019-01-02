package helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

import config.FrameworkConfig;

//Para poder usar BrowserFactory es necesario setear el path de los drivers
//en las variables del sistema

public class BrowserFactory {
	public static WebDriver driver;
	
	public static WebDriver StartBrowser(String BrowserName, String url) {
		if(BrowserName.equalsIgnoreCase(FrameworkConfig.BROWSER_FIREFOX)) {
			if(FrameworkConfig.BROWSER_CANCELNOTIFICATIONS) {
				//Desabilitando las notificaciones
				FirefoxProfile profile =new FirefoxProfile();
				profile.setPreference("dom.webnotifications.enabled", false);
				FirefoxOptions option=new FirefoxOptions();
				option.setProfile(profile);
				driver = new FirefoxDriver(option);
			}else {
				driver = new FirefoxDriver();
			}
			
		}
		else if(BrowserName.equalsIgnoreCase(FrameworkConfig.BROWSER_CHROME)) {
			if(FrameworkConfig.BROWSER_CANCELNOTIFICATIONS) {
				//Opening chrome with notification disabled
				ChromeOptions ops = new ChromeOptions();
	            ops.addArguments("--disable-notifications");
				driver = new ChromeDriver(ops);
			}else {
				driver = new ChromeDriver();
			}
			
		}
		else if(BrowserName.equalsIgnoreCase(FrameworkConfig.BROWSER_IE)) {
			//Me falta agregar el if de la cancelacion de notificaciones en ie
			driver = new InternetExplorerDriver();
		}
		else if(BrowserName.equalsIgnoreCase(FrameworkConfig.BROWSER_OPERA)) {
			//Me falta agregar el if de la cancelacion de notificaciones en ie
			driver = new OperaDriver();
		}else{
			Assert.fail("No se ha especificado el browser correctamente (solo puede ser: chrome, firefox, ie, opera)");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		return driver;
	}
}
