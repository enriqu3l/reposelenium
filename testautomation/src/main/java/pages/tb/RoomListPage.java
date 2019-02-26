package pages.tb;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FWConfig;
import utility.FWUtils;

public class RoomListPage {
	private WebDriverWait wait;
	private WebDriver driver;
	
	public RoomListPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,FWConfig.WAIT_TB);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, FWConfig.WAITPF_TB),this);
	}
	
	//+++++++++PageFactory Elements+++++++++++++++++
	@FindBy(how=How.CSS, css=".hotel-rooms-table .room-table")
	private List<WebElement> allSearchResults;
	
	@FindBy(how=How.CSS, css=".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary")
	private WebElement roomButton;
	
	//Los lementos que se cargan al inicio junto con la pagina se declaran con @FindBy
	//Los elementos que se cargan despues, por cuestion de AJAX o por una SPA se declararan
	//usando By elements

	//---------NON PageFactory Elements-------------
	//private By BYrate = By.cssSelector("");
	//private By BYroomButton = By.cssSelector(".hotel-rooms-table .room-table .room-table-actions .btn.btn-primary");
	
	public void selectRoomDefault() {
		/* 1.- Esperar a que se cargue la pagina
		 * 3.- Moverse hasta el listado
		 * 4.- Ver si hay cuartos con disponibilidad
		 * 5.- Seleccionar un cuarto
		 */
		
		System.out.println("RoomListF - Tamaño de la lista antes: "+allSearchResults.size());
		
		//Cambié el scroll por un Action, hasta ahora parece que esta funcionando bien ;)...
		//Actions actions = new Actions(driver);
		//(actions.moveToElement(roomButton).perform();
		//Workaround for firefox
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", roomButton);
		//-----------------------------------------------------
		
		wait.until( ExpectedConditions.elementToBeClickable(roomButton) );
		wait.until( ExpectedConditions.visibilityOfElementLocated(FWUtils.toByVal(roomButton)) );
		
		System.out.println("RoomListF - Tamaño de la lista despues: "+allSearchResults.size());
		
		//Falla al momento de querer dar click porque aun se encuentra el spiner encima del elemento
		roomButton.click();
	}
	
	
	
}
