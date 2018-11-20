package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.BasicUtils;

public class HotelListPageF {
	private WebDriverWait wait;
	private WebDriver driver;
	
	public HotelListPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
	}
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allSearchResults;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block .list-product-rate .list-product-rate-action a")
	WebElement firstButton;
	
	public void SelectFirstHotel() {
		System.out.println("HotelListF - Tamaño de la lista al inicio: "+allSearchResults.size());
		
		//Necesito esperar a que el elemento este visible
		wait.until( ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(firstButton)) );
		wait.until( ExpectedConditions.elementToBeClickable(firstButton));
		wait.until( ExpectedConditions.visibilityOfElementLocated(BasicUtils.toByVal(firstButton)) );
		
		System.out.println("HotelListF - Tamaño de la lista al final: "+allSearchResults.size());
		firstButton.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	//NOT READY!
	public void SelectHotel(int itemList) throws InterruptedException {
		//Aqui el codigo para seleccionar un hotel especifico de acuerdo al item de la lista
	}
	
	public void verifyIfANewTabOpened() {
		//Obtener las tabs existentes
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		if(browserTabs.size()>1) {
			//En caso de haber mas de 1 tab, switchear a esa nueva tab.
			//La primer tab comienza con 0 por eso seleccionamos la 1
			driver.switchTo().window(browserTabs.get(1));
		}
		System.out.println("HotelListPage - Cantidad de tabs: "+browserTabs.size());
		
		//switch to new tab
		//driver.switchTo().window(browserTabs.get(1));
		//check is it correct page opened or not (e.g. check page's title) then close tab and get back
		//driver.close();
		//driver.switchTo().window(browserTabs.get(0));
	}
}
