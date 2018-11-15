package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	private List<WebElement> allSearchResults;
	
	
	//Cuando se complica la seleccion de una elemento por cuestiones de carga de paginas
	//de tipo SPA o que usan alguna funcion AJAX, se utilizara BY junto con un ExplicitWait
	//By elements
	//---------NON PageFactory Elements-------------
	private By BYallSearchResults = By.cssSelector(".hotel-rooms-table .room-table");
	//private By BYroomButton = By.cssSelector(".hotel-rooms-table.has-loader .room-table.ng-star-inserted .room-table-actions  button.btn.btn-primary");
	private By BYroomButton = By.cssSelector(".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary");
	
	public void SelectRoomDefault() {
		List<WebElement> allSearchResults = driver.findElements(BYallSearchResults);
		System.out.println("RoomListF - Tamaño de la lista antes: "+allSearchResults.size());
		
		//Makes a simple scrooll in order to see the buttons, is not relevant!!!
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700)");
		
		wait.until( ExpectedConditions.presenceOfElementLocated(BYroomButton) );
		wait.until( ExpectedConditions.elementToBeClickable(BYroomButton));
		//Los dos wait anteriores en veces no son suficientes para esperar
		//a que carge completamente el contenido de la SPA. Tengo que solucionarlo!!!
		try {
			Thread.sleep(5000); //No eliminar hasta no solucionar el wait del contenido Ajax
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> allSearchResults2 = driver.findElements(BYallSearchResults);
		System.out.println("RoomListF - Tamaño de la lista despues: "+allSearchResults2.size());
		
		WebElement button = driver.findElement(BYroomButton);
		button.click();
		
	}
	
	//NOT READY!
	public void SelectRoom(int itemList) throws InterruptedException {
		//Aun no funciona correctamente, necesita un sleep para funcionar
		//wait.until( ExpectedConditions.presenceOfElementLocated(BYallSearchResults));
		//List<WebElement> allSearchResults = driver.findElements(BYallSearchResults);
		//System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
		
		allSearchResults.get(itemList-1).findElement(BYroomButton).click();
		
		System.out.println("->>>>>>>>Tamaño de la lista: "+allSearchResults.size());
	}
}
