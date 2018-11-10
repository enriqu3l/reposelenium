package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelListPageF {
	WebDriverWait wait;
	WebDriver driver;
	
	public HotelListPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
	}
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allSearchResults;
	
	public void SelectFirstHotel() {
		System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
		WebElement button = wait.until( ExpectedConditions.presenceOfElementLocated(By.cssSelector(".list-product .list-product-block .list-product-rate .list-product-rate-action a")) );
		System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
		button.click();
	}
	
	public void SelectHotel(int itemList) throws InterruptedException {
		//allSearchResults.get(itemList-1);
		//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".list-product .list-product-block"), 0));
		//By button = By.cssSelector(".list-product .list-product-block .list-product-rate .list-product-rate-action a");
	
		/*
		//wait.until(ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(allSearchResults.get(itemList-1).findElement(By.cssSelector(".list-product-rate a")))));
		//
		System.out.println("->>>>>>>>Ya se ejecuto el Wait");
		allSearchResults.get(itemList-1).findElement(By.cssSelector(".list-product-rate .list-product-rate-action a")).click();
		System.out.println("->>>>>>>>Ya se ejecuto el Click");
		*/
	}
	
}
