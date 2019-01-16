package workflows.hoteles;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.DDManager;
import pages.Pages;
import valueobjects.VOHotelResNew;

public class WSPAHLWidget {
private static Logger logger = LogManager.getLogger(WSPAHLWidget.class);

	/**
	 * Este test, prueba que almenos 10 destinos diferentes funcionen con autocomplete
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void destinationAutocompleteTest(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow destinationAutocompleteTest");
		Reporter.log("Starting workflow destinationAutocompleteTest");
		List<String> data = new ArrayList<String>();
		data.add("canc");
		data.add("nueva");
		data.add("vegas");
		data.add("vallarta");
		data.add("bogot");
		Pages pages = new Pages(driver);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.verifyWidgetInputDestinationAutocomplete(data);
	}

	public static void changeDestinTest(WebDriver driver) throws InterruptedException{
		logger.info("Starting workflow changeDestinTest");
		Reporter.log("Starting workflow changeDestinTest");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOHotelResNew voHotelResNew = DDManager.getHotelResNew(2);
		Pages pages = new Pages(driver);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeDestin(voHotelResNew.getDestination());
		pages.hotelList_page.widgetClickSubmit();
		
		//Verificar que el widget tiene el nuevo destino
		pages.hotelList_page.verifyWidgetInputDestinationToBe(voHotelResNew.getDestination());		
		//Verificar que el Page Header Title tiene el nuevo destino
		pages.hotelList_page.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		//Verificar que tenemos resultados
		pages.hotelList_page.verifyResultListHasElements();
	}
	
	public static void changeStartDateTest(WebDriver driver) {
		logger.info("Starting workflow changeDestinTest");
		Reporter.log("Starting workflow changeDestinTest");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOHotelResNew voHotelResNew = DDManager.getHotelResNew(2);
		Pages pages = new Pages(driver);
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeStartDate(voHotelResNew.getStartDate());
		pages.hotelList_page.widgetClickSubmit();
		
		//Verificar que el widget tiene la nueva fecha
		pages.hotelList_page.verifyWidgetStartDateToBe(voHotelResNew.getStartDate());
		//Verificar que la url tiene la nueva fecha
		pages.hotelList_page.verifyURLToContain(""); //Aun no la construyo
		//Verificar que tenemos resultados
		pages.hotelList_page.verifyResultListHasElements();
	}
	
	public static void changeEndDateTest(WebDriver driver) {
		
	}
}
