package Pages;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Config.CoreConfig;
import DataObjects.DOHotelRes;
import Utility.BasicUtils;
import junit.framework.Assert;

public class HotelListPageF {
	enum searchPages{
		FisrtPage,TwoPages,ThreePages,AllPages
	}
	
	private WebDriverWait wait;
	private WebDriver driver;
	
	public HotelListPageF(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
	}
	
	//Ejemplos de como buscar attributos en xpath y cssSelector
	By overlayOcultoXPath = By.xpath("//*[@class='loader-overlay'][contains(@style, 'display: none')]");
	By overlayOculto = By.cssSelector(".loader-overlay[style='display: none; opacity: 0;']");
	
	By loaderOverlayPage = By.cssSelector(".loader-overlay.ng-trigger");
	By loaderButton = By.cssSelector(".list-product-rate .loader");
	By loaderOverlayFiltros = By.cssSelector(".card-body .loader-overlay");
	
	@FindBy(how=How.CSS, css=".loader__title")
	WebElement loaderTitle;
	
	@FindBy(how=How.CSS, css=".spinner")
	WebElement spiner;
	
	//-------------- List Section ----------------------------------
	@FindBy(how=How.CSS, css=".list-product .list-product-block")
	List<WebElement> allBlocksResults;
	
	@FindBy(how=How.CSS, css=".list-product .list-product-block .list-product-rate .list-product-rate-action a")
	WebElement Button_firstItem;
	
	By BYlistProduct = By.cssSelector(".list-product");
	By BYproductRateFinal = By.cssSelector(".list-product-rate .product-rate-final");
	By BYButton_seeOffer = By.cssSelector(".list-product-rate .list-product-rate-action .btn");
	
	//--------------- Lateral Widget elements - Basados en SPA-Hoteles!! -------
	@FindBy(how=How.CSS, css="#ptw-container #destination")
	WebElement Input_destination;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ui-datepicker-trigger")
	WebElement Image_startDatePicker;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ui-datepicker-trigger")
	WebElement Image_endDatePicker;
	
	@FindBy(how=How.CSS, css="#start-datepicker .dropdown-menu .ngb-dp-month-name")
	WebElement startDate_Title;
	
	@FindBy(how=How.CSS, css="#end-datepicker .dropdown-menu .ngb-dp-month-name")
	WebElement endDate_Title;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow button.btn")
	WebElement startDate_beforeMonth;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow.right button.btn")
	WebElement startDate_nextMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow button.btn")
	WebElement endDate_beforeMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow.right button.btn")
	WebElement endDate_nextMonth;
	
	@FindBy(how=How.CSS, css="#ptw-container #ap_booker_Hotel_rooms")
	WebElement Select_bookerHotelRooms;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_adults")
	WebElement Select_bookerHotelAdults;
	
	@FindBy(how=How.CSS, css="#ptw-container .ap_booker_Hotel_minors")
	WebElement Select_bookerHotelMinors;
	
	@FindBy(how=How.CSS, css="#ptw-container .ptw-submit-btn")
	WebElement Button_search;
	
	By startDate_dropdownMenu = By.cssSelector("#start-datepicker .dropdown-menu");
	By endDate_dropdownMenu = By.cssSelector("#end-datepicker .dropdown-menu");
	
	//Esta funcion se diseño pensando solo en la funcionalidad de las SPA
	public void SelectFirstHotel() {
		//Esperar a que se quite el overlay, falla en Test porque no es SPA
		wait.until(ExpectedConditions.attributeContains(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		
		Assert.assertTrue("ENF>>>No se encontro ninguna lista de resultados!.", BasicUtils.existsElement(driver,BYlistProduct));
		Assert.assertFalse("ENF>>>La lista de resultados esta vacia!.",allBlocksResults.isEmpty());
		System.out.println("Info - HotelListF - Tamaño de la lista: "+allBlocksResults.size());
		
		int index = getItemNum_firstHotelAvailable(allBlocksResults);
		Assert.assertFalse("LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.",CoreConfig.FaultValue==index);
		WebElement Button_seeOffer = allBlocksResults.get(index).findElement(BYButton_seeOffer);
		Button_seeOffer.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	public void SelectHotel(int index) throws InterruptedException {
		Assert.assertTrue("LAF>>>Parametro invalido, index tiene que ser menor a 20!.",index<20);
		
		//Esperar a que se quite el overlay, falla en Test porque no es SPA
		wait.until(ExpectedConditions.attributeContains(loaderOverlayPage, "style", "display: none; opacity: 0;"));

		Assert.assertTrue("ENF>>>No se encontro ninguna lista de resultados!.",
				BasicUtils.existsElement(driver, BYlistProduct));
		Assert.assertFalse("ENF>>>La lista de resultados esta vacia!.", allBlocksResults.isEmpty());
		System.out.println("HotelListF - Tamaño de la lista: " + allBlocksResults.size());
		
		Assert.assertTrue("LAF>>>No se encontro ningun hotel con disponibilidad en la primer pagina!.",CoreConfig.FaultValue==index);
		WebElement Button_seeOffer = allBlocksResults.get(index).findElement(BYButton_seeOffer);
		Button_seeOffer.click();
		
		//En caso de encontrar una nueva tab, switchear a ella.
		//Esta funcion se agrego para tener compatibilidad con ambiente test
		verifyIfANewTabOpened();
	}
	
	public void verifyIfANewTabOpened() {
		//Obtener las tabs existentes
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		if(browserTabs.size()>1) {
			//En caso de haber mas de 1 tab, switchear a esa nueva tab.
			driver.switchTo().window(browserTabs.get(1)); //La primer tab comienza con 0 por eso seleccionamos la 1
		}
		System.out.println("HotelListPage - Cantidad de tabs: "+browserTabs.size());
		
		//switch to new tab
		//driver.switchTo().window(browserTabs.get(1));
		//check is it correct page opened or not (e.g. check page's title) then close tab and get back
		//driver.close();
		//driver.switchTo().window(browserTabs.get(0));
	}
	
	//Ya se construyó pero aun no se ha probado
	public void widget_changeSearch(DOHotelRes DO_HotelRes){
		Input_destination.clear();
		Input_destination.sendKeys(DO_HotelRes.getDestination());
		widget_selectStartDate(DO_HotelRes.getStartDate());		
		widget_selectEndDate(DO_HotelRes.getEndDate());
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByValue("1");
		Select adults = new Select(Select_bookerHotelAdults);
		adults.selectByValue(Integer.toString(DO_HotelRes.getAdults()));
		Select kids = new Select(Select_bookerHotelMinors);
		kids.selectByValue("0");
		Button_search.click();
	}
	
	//En construccion, ya mero esta lista!!!
	public void widget_selectStartDate(String date) {
		//Me falta poner candados para validar el parametro		
		
		//Esperar a que se quite el overlay
		//wait.until(WaitFor.attributeValue(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		wait.until(ExpectedConditions.attributeContains(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		//Abrir el calendario si no esta abierto
		if(BasicUtils.noExistsElement(driver,startDate_dropdownMenu)){Image_startDatePicker.click();}
		
		LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
		int day = localDate.getDayOfMonth();
		String actualDate = BasicUtils.toddMMyyyyFormat(startDate_Title.getText().trim());
		int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
		System.out.println("TotalMonthDifference: "+TotalMonthDifference);
		if(TotalMonthDifference>0) {
			for(int i=0; i<TotalMonthDifference;i++) {
				startDate_nextMonth.click(); //click hacia adelante
			}
		}else if(TotalMonthDifference<0) {
			for(int i=0; i>TotalMonthDifference;i--) {
				startDate_beforeMonth.click(); //click hacia atras
			}
		}
		String xpath = "//*[@id='start-datepicker']//div[text()='"+day+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String selector = "#start-datepicker div[aria-label*=' "+day+" '].ngb-dp-day div";
		//driver.findElement(By.cssSelector(selector)).click();
	}
	public void widget_selectEndDate(String date) {
		//Esperar a que se quite el overlay
		wait.until(ExpectedConditions.attributeContains(loaderOverlayPage, "style", "display: none; opacity: 0;"));
		//Abrir el calendario si no esta abierto
		if(BasicUtils.noExistsElement(driver,endDate_dropdownMenu)){Image_endDatePicker.click();}
		
		LocalDate localDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
		int day = localDate.getDayOfMonth();
		String actualDate = BasicUtils.toddMMyyyyFormat(endDate_Title.getText().trim());
		int TotalMonthDifference = BasicUtils.monthDiference(date, actualDate);	
		System.out.println("TotalMonthDifference: "+TotalMonthDifference);
		if(TotalMonthDifference>0) {
			for(int i=0; i<TotalMonthDifference;i++) {
				endDate_nextMonth.click(); //click hacia adelante
			}
		}else if(TotalMonthDifference<0) {
			for(int i=0; i>TotalMonthDifference;i--) {
				endDate_beforeMonth.click(); //click hacia atras
			}
		}
		
		String xpath = "//*[@id='end-datepicker']//div[text()='"+day+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String selector = "#end-datepicker div[aria-label*=' " + Integer.toString(day) + " '].ngb-dp-day div";
		//driver.findElement(By.cssSelector(selector)).click();
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//---------------- FUNCIONES PRIVADAS ------------------------------------------------
	private int getItemNum_firstHotelAvailable(List<WebElement> allSearchResults) {
		Assert.assertFalse("ENF>>>La lista de resultados esta vacia!.",allSearchResults.isEmpty());
		
		int index = CoreConfig.FaultValue;
		for (int i = 0; i < allSearchResults.size(); i++) {
			WebElement listProductBlock = allSearchResults.get(i);
			wait.until( ExpectedConditions.presenceOfNestedElementLocatedBy(listProductBlock, BYButton_seeOffer));
			
			if(!listProductBlock.findElements(BYproductRateFinal).isEmpty()) {
				WebElement rateFinal = listProductBlock.findElement(BYproductRateFinal);
				if (rateFinal.getText().contains("$")) {
					int i_masuno = i+1;
					System.out.println("Info - Se encontro disponibilidad hasta el Hotel No: " + i_masuno);
					index = i;
					break;
				}
			}
			else{
				System.out.println("Info - No se encontro tarifa $ en el item: " + i);
			}
		}
		if(CoreConfig.FaultValue==index){System.out.println("Info - No se encontro ningun hotel con disponibilidad!.");}
		return index;
	}
	private int getItemNum_byHotelName(String hotel) {
		//Aqui el codigo para buscar por el nombre del hotel
		return 0;
	}
	private int getItemNum_byHotelId(int hotelId) {
		//Aqui el codigo para buscar por Id del Hotel
		return 0;
	}
}
