package Pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow button.btn")
	WebElement startDate_beforeMonth;
	
	@FindBy(how=How.CSS, css="#start-datepicker .ngb-dp-arrow.right button.btn")
	WebElement startDate_nextMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow button.btn")
	WebElement endDate_beforeMonth;
	
	@FindBy(how=How.CSS, css="#end-datepicker .ngb-dp-arrow.right button.btn")
	WebElement endDate_nextMonth;
	
	//----------- Bys ----------------------
	By startDate_dropdownMenu = By.cssSelector("#start-datepicker .dropdown-menu");
	By endDate_dropdownMenu = By.cssSelector("#end-datepicker .dropdown-menu");
	
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
		//Me falta poner candados y validar el parametro		
		//Necesito esperar a que el elemento este visible
		wait.until( ExpectedConditions.presenceOfElementLocated(BasicUtils.toByVal(firstButton)) );
		wait.until( ExpectedConditions.elementToBeClickable(firstButton));
		wait.until( ExpectedConditions.visibilityOfElementLocated(BasicUtils.toByVal(firstButton)) );

		int day = Integer.parseInt(date.trim().substring(0, 2));
		int month = Integer.parseInt(date.trim().substring(3, 5));
		int year = Integer.parseInt(date.trim().substring(6, 10));
		
		Image_startDatePicker.click();
		//getTitle
		String titledate = startDate_Title.getText().trim();
		String titlemonthString = titledate.substring(0,titledate.length()-4).trim().toUpperCase();
		
		System.out.println("titleMonthString: "+titlemonthString);
		int titlemonth = BasicUtils.toMonthNumber(titlemonthString);
		
		int titleyear = Integer.parseInt(titledate.substring(titledate.length()-4, titledate.length()));
		int yearDifference = year-titleyear;
		int monthDifference = month-titlemonth; 
		int TotalMountDifference = 0;
		System.out.println("Mont and Year Actual on widget: "+titlemonth+"/"+titleyear);
		System.out.println("Mont and Year Expected: "+month+"/"+year);
		
		if(yearDifference!=0 || monthDifference!=0) {
			TotalMountDifference =  monthDifference+(yearDifference*12);	
			System.out.println("TotalDifference: "+TotalMountDifference);
			//Ciclos para los clics hacia adelante o hacia atras
			if(TotalMountDifference>0) {
				for(int i=0; i<TotalMountDifference;i++) {
					//click hacia adelante
					startDate_nextMonth.click();
				}
			}else {
				for(int i=0; i>TotalMountDifference;i--) {
					//click hacia adelante
					startDate_beforeMonth.click();
				}
			}
		}
		
		String selector = "#start-datepicker div[aria-label*=' "+Integer.toString(day)+" '].ngb-dp-day div";
		driver.findElement(By.cssSelector(selector)).click();
	}
	public void widget_selectEndDate(String date) {
		if(driver.findElements(endDate_dropdownMenu)!=null) {
			int day = Integer.parseInt(date.trim().substring(0, 2));
			int month = Integer.parseInt(date.trim().substring(3, 5));
			int year = Integer.parseInt(date.trim().substring(6, 10));
			
			//Image_endDatePicker.click();
			//getTitle
			String titledate = endDate_Title.getText().trim();
			String titlemonthString = titledate.substring(0,titledate.length()-4).trim().toUpperCase();
			
			System.out.println("titleMonthString: "+titlemonthString);
			int titlemonth = BasicUtils.toMonthNumber(titlemonthString);
			
			int titleyear = Integer.parseInt(titledate.substring(titledate.length()-4, titledate.length()));
			int yearDifference = year-titleyear;
			int monthDifference = month-titlemonth; 
			int TotalMountDifference = 0;
			System.out.println("Mont and Year Actual on widget: "+titlemonth+"/"+titleyear);
			System.out.println("Mont and Year Expected: "+month+"/"+year);
			
			if(yearDifference!=0 || monthDifference!=0) {
				TotalMountDifference =  monthDifference+(yearDifference*12);	
				System.out.println("TotalDifference: "+TotalMountDifference);
				//Ciclos para los clics hacia adelante o hacia atras
				if(TotalMountDifference>0) {
					for(int i=0; i<TotalMountDifference;i++) {
						//click hacia adelante
						endDate_nextMonth.click();
					}
				}else {
					for(int i=0; i>TotalMountDifference;i--) {
						//click hacia adelante
						endDate_beforeMonth.click();
					}
				}
			}
			
			String selector = "#end-datepicker div[aria-label*=' "+Integer.toString(day)+" '].ngb-dp-day div";
			driver.findElement(By.cssSelector(selector)).click();
		}
	}
}
