package Utility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicUtils {
	
	/*//Seleccionar una fecha de un calendario, usar formato: dd/mm/yyyy
	public static void selectDateFromCalendar(WebDriver d,String setDateStr) {
		d.findElement(By.id("datepicker")).click();
		
		String currDateStr = d.findElement(By.className("ui-datepicker-title")).getText();
		Date setDate = new SimpleDateFormat( pattern: "dd/MM/yyyy").parse(setDateStr);
	}*/
	
	/*WebElement select = driver.findElement(By.id("ap_origin_flightPackage"));
	List<WebElement> options = select.findElements(By.tagName("li"));
	for (WebElement option1 : options) {
		if("SYDNEY, New South Wales, Australia, 1001".equals(option1.getText().trim()))
	    option1.click();   
	}  
	
	selenium.typeKeys("county_search", "lake");
	for (int second = 0;; second++) {
	    if (second >= 60) fail("timeout");
	    try { if (selenium.isTextPresent("Lake County, IL")) break; } catch (Exception e) {}
	    Thread.sleep(1000);
	}
	selenium.mouseOver("//html/body/ul/li/a[. = \"Lake County, IL\"]");
	selenium.click("//html/body/ul/li/a[. = \"Lake County, IL\"]");
	*/
	   
	//Como obtener elementos de una tag ul
	/*WebElement countryUL= driver.findElement(By.xpath("//[@id='country_id']/ul"));
	List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
	for (WebElement li : countriesList) {
	if (li.getText().equals("India (+91")) {
	     li.click();
	   }
	}*/
	
	
	//Estoy casi seguro que esto es lo que hace AjaxElementLocatorFactory
	//Por ahora no lo estoy usando!!
	public static boolean waitForJSandJQueryToLoad(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, 30);

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          // no jQuery present
	          return true;
	        }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
	        .toString().equals("complete");
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
	
	//EN PROCESO DE REVISION, LA ESTABA USANDO EN ROOMLIST,
	//pero mejor puse los @FindBy en la clase para que AjaxElementLocatorFactory
	//espere y localize a los elementos
	//No me sirve para la SPA, solo funciona en legacy
	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(pageLoadCondition);
	}
	
	public static By toByVal(WebElement we) {
	    // By format = "[foundFrom] -> locator: term"
	    // see RemoteWebElement toString() implementation
	    String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
	    String locator = data[0];
	    String term = data[1];

	    switch (locator) {
	    case "xpath":
	        return By.xpath(term);
	    case "css selector":
	        return By.cssSelector(term);
	    case "id":
	        return By.id(term);
	    case "tag name":
	        return By.tagName(term);
	    case "name":
	        return By.name(term);
	    case "link text":
	        return By.linkText(term);
	    case "class name":
	        return By.className(term);
	    }
	    return (By) we;
	}
	
	//Guardar un ScreenShot
	public static void ScreenShot(WebDriver d) {
		File src=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File("C:/Selenium/Test/Screen"+System.currentTimeMillis()+".png"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}//End of function
	
	//Guardar un ScreenShot y permite ponerle un nombre a la captura
	public static void ScreenShot(WebDriver d, String _name) {
		File src=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File("C:/PTFrameworkData/Screen_"+_name+System.currentTimeMillis()+".png"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}//End of function
	
	//Guardar un ScreenShot y permite ponerle un nombre a la capture y decidir donde guardar la capture
	/**
	 * @param webDriver
	 * @param name
	 * @param path
	 */
	public static void ScreenShot(WebDriver d, String _name, String _path) {
		File src=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File(_path+"Screen_"+_name+"_"+System.currentTimeMillis()+".png"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}//End of function

	//Funcion para pedir credenciales
	public static String[] requestCredentials() {
		Scanner scn = new Scanner(System.in);
		String[] credentials = new String[2];
		System.out.println("Ingresa tu Usuario|Email: ");
		credentials[0] = scn.nextLine();
		System.out.println("Ingresa tu Password: ");
		credentials[1] = scn.nextLine();
		scn.close();
		return credentials;
	}
}
