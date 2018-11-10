package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoomListPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	
	public RoomListPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
	}
	
	//+++++++++PageFactory Elements+++++++++++++++++
	@FindBys(@FindBy(how=How.CSS, css=".hotel-rooms-table.has-loader .room-table.ng-star-inserted"))
	@CacheLookup
	private List<WebElement> allSearchResults;
	
	
	//---------NON PageFactory Elements-------------
	private By roombutton = By.cssSelector(".room-table-actions  button.btn.btn-primary");
	
	public void SelectRoomDefault() {
		System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700)");
		WebElement button = wait.until( ExpectedConditions.presenceOfElementLocated(By.cssSelector(".hotel-rooms-table.has-loader .room-table.ng-star-inserted .room-table-actions  button.btn.btn-primary")) );
		System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
		button.click();
	}
	
	public void SelectRoom(int itemList) throws InterruptedException {
		//Aun no funciona correctamente, necesita un sleep para funcionar
		allSearchResults.get(itemList-1).findElement(roombutton).click();
	}
}
