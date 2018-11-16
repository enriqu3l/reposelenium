package Pages;

import java.util.ArrayList;
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
	private WebDriverWait wait;
	private WebDriver driver;
	
	public HotelListPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
	}
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allSearchResults;
	
	By BYallSearchResults = By.cssSelector(".list-product .list-product-block");
	By BYfisrtButton = By.cssSelector(".list-product .list-product-block .list-product-rate .list-product-rate-action a");
	
	public void SelectFirstHotel() {
		System.out.println("HotelListF - Tamaño de la lista al inicio: "+allSearchResults.size());
		
		try {
			Thread.sleep(5000); //No eliminar hasta no solucionar el wait del contenido Ajax
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until( ExpectedConditions.presenceOfElementLocated(BYfisrtButton) );
		wait.until( ExpectedConditions.elementToBeClickable(BYfisrtButton));
		//Los dos wait anteriores en veces no son suficientes para esperar
		//a que carge completamente el contenido de la SPA. Tengo que solucionarlo!!!
		
		System.out.println("HotelListF - Tamaño de la lista al final: "+allSearchResults.size());
		
		WebElement button = driver.findElement(BYfisrtButton);
		button.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	//NOT READY!
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
		//check is it correct page opened or not (e.g. check page's title)
		//...
		//then close tab and get back
		//driver.close();
		//driver.switchTo().window(browserTabs.get(0));
	}
}
