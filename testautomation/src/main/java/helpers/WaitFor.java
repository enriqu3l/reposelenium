package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitFor {
	//Ejemplo de como usar una clase de tipo ExpectedConditions
	public static ExpectedCondition<Boolean> attributeValueClass(By locator, String attr, String expectedValue){
		return new attributeValueClass(locator, attr, expectedValue);
	}
	
	public static ExpectedCondition<Boolean> attributeValue(By locator, String attr, String expectedValue){
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				 WebElement element = input.findElement(locator);
		            String attribute = element.getAttribute(attr);
		            System.out.println("attribute: "+attribute);
		            if(attribute.equals(expectedValue)) 
		                return true;
		            else
		                return false;
			}
			
		};
	}
	
	public static ExpectedCondition<Boolean> attributeChanged(By locator, String attr, String actualValue){
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				 WebElement element = input.findElement(locator);
		            String attribute = element.getAttribute(attr);
		            System.out.println("attribute: "+attribute);
		            if(attribute.equals(actualValue)) 
		                return false;
		            else
		                return true;
			}
			
		};
	}
	
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

}


//Ejemplo de como crear una clase de tipo ExpectedConditions
class attributeValueClass implements ExpectedCondition<Boolean>{
	private By locator;
    private String attr;
    private String expectedValue;
	public attributeValueClass(By locator, String attr, String expectedValue){
		this.locator = locator;
        this.attr = attr;
        this.expectedValue = expectedValue;
	}
	@Override
	public Boolean apply(WebDriver driver) {
		WebElement element = driver.findElement(this.locator);
        String attribute = element.getAttribute(this.attr);
        System.out.println("attribute: "+attribute);
        if(attribute.equals(this.expectedValue)) 
            return true;
        else
            return false;
	}
}
