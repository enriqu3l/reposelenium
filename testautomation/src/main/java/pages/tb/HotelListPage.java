package pages.tb;

import java.util.List;

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

public class HotelListPage {
	private WebDriverWait wait;
	private WebDriver driver;
	
	public HotelListPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,FWConfig.WAIT_TB);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, FWConfig.WAITPF_TB),this);
	}
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allSearchResults;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block .list-product-rate .list-product-rate-action a")
	WebElement firstButton;
	
	public void selectFirstHotel() {
		System.out.println("HotelListF - Tamaño de la lista al inicio: "+allSearchResults.size());
		
		//Necesito esperar a que el elemento este visible
		wait.until( ExpectedConditions.presenceOfElementLocated(FWUtils.toByVal(firstButton)) );
		wait.until( ExpectedConditions.elementToBeClickable(firstButton));
		wait.until( ExpectedConditions.visibilityOfElementLocated(FWUtils.toByVal(firstButton)) );
		int tabsCount = driver.getWindowHandles().size();
		
		System.out.println("HotelListF - Tamaño de la lista al final: "+allSearchResults.size());
		firstButton.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		FWUtils.switchToNewTabIfOpened(driver, tabsCount);
	}
	
	//NOT READY!
	public void selectHotel(int itemList) throws InterruptedException {
		//Aqui el codigo para seleccionar un hotel especifico de acuerdo al item de la lista
	}
}
