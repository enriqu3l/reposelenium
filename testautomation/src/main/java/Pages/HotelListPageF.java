package Pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataObjects.DOHotelRes;
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
	
	
	//Lateral Widget elements
	@FindBy(how=How.CSS,css="#start-datepicker .ui-datepicker-trigger")
	WebElement Image_startDatePicker;
	
	@FindBy(how=How.CSS,css="#end-datepicker .ui-datepicker-trigger")
	WebElement Image_endDatePicker;
	
	@FindBy(how=How.CSS,css="#start-datepicker .dropdown-menu .ngb-dp-month-name")
	WebElement startDate_Title;
	
	@FindBy(how=How.CSS,css="#end-datepicker .dropdown-menu .ngb-dp-month-name")
	WebElement endDate_Title;
	
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
	
	public void widget_changeSearch(DOHotelRes DO_HotelRes){
		/*verifyProductSelectedOnWidgetMenu("Hoteles");
		Input_destHotel.clear();
		Input_destHotel.sendKeys(DO_HotelRes.getDestination());
		Input_destStartHotel.clear();
		Input_destStartHotel.sendKeys(DO_HotelRes.getStartDate());		
		Input_destEndHotel.clear();
		Input_destEndHotel.sendKeys(DO_HotelRes.getEndDate());
		//Image_destEndHotelTrigger.click();  esconde el calendario
		Select rooms = new Select(Select_bookerHotelRooms);
		rooms.selectByVisibleText("1");
		Select adults = new Select(Select_bookerHotelAdults0);
		adults.selectByValue(Integer.toString(DO_HotelRes.getAdults()));
		Select kids = new Select(Select_bookerHotelMinors0);
		kids.selectByValue("0");
		Button_search.click();*/
	}
	
	//En construccion!!!
	public void widget_selectStartDate(String date) {
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		
		//Tengo que validar si la fecha es menor que la actual retornar un error
		//...
		
		Image_startDatePicker.click();
		//getTitle
		String titledate = startDate_Title.getText().trim();
		String titlemonthString = titledate.substring(0,titledate.length()-4).trim();
		int titlemonth = BasicUtils.toMonthNumber(titlemonthString);
		int titleyear = Integer.parseInt(titledate.substring(titledate.length()-4, titledate.length()));
		int yearDifference = year-titleyear;
		int monthDifference = month-titlemonth; 
		if(yearDifference!=0 || monthDifference!=0) {
			if(yearDifference>0) {
				//Hacer click hasta llegar al año seleccionado
			
			}else if(yearDifference<0) {
				//Hacer clik hasta llegar al año seleccionado
			}
		}
		
		while(yearDifference!=0 || monthDifference!=0) {
			//Hacerclick en el boton de 
			
		}
		
		
	}
	public void widget_selectEndDate(String date) {
		
	}
}
