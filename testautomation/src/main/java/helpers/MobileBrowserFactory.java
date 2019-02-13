package helpers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import config.FrameworkConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

//Para poder usar BrowserFactory es necesario setear el path de los drivers
//en las variables del sistema

public class MobileBrowserFactory {
	public static WebDriver driver;
	
	public static WebDriver StartBrowser(String _device, String _version, String _browserName, String _url) {
		Assert.assertFalse((_device.equals("") || _url.equals(null)),"El parametro _device es nullo o no contiene informacion");
		Assert.assertFalse((_browserName.equals("") || _browserName.equals(null)),"El parametro _browserName es nullo o no contiene informacion");
		Assert.assertFalse((_url.equals("") || _url.equals(null)),"El parametro _url es nullo o no contiene informacion");
		
		if(_browserName.equalsIgnoreCase(FrameworkConfig.BROWSER_CHROME)) {
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("autoDismissAlerts", true);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,_device);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, _version);
			URL url = null;
			try {
				url = new URL("http://127.0.0.1:4723/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			// Create object of  AndroidDriver class and pass the url and capability that we created
			driver = new AndroidDriver<>(url, capabilities);
		}else {
			Assert.fail("No existe la ocpion:"+_browserName);
			return null;
		}
		
		driver.get(_url);
		//driver.manage().timeouts().pageLoadTimeout(FrameworkConfig.BROWSER_PAGELOADTIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
}