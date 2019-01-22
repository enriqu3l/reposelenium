package workflows.hoteles;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import config.FrameworkConfig;
import helpers.DDManager;
import pages.Pages;
import valueobjects.VOHotelRes;

public class WSPAHLWidget {
private static Logger logger = LogManager.getLogger(WSPAHLWidget.class);

	/**
	 * Este testcase verifica que el autocomplete se active con X palabras diferentes.
	 * 
	 * @param driver - WebDriver
	 * @throws InterruptedException
	 * @author enrique.lopez
	 */
	public static void autocompleteDestinationTest(WebDriver driver){
		//<Setup>
		logger.info("Starting workflow autocompleteDestinationTest");
		Reporter.log("Starting workflow autocompleteDestinationTest");
		//Por lo pronto esta harcodeado a las siguientes palabras de busqueda
		List<String> data = new ArrayList<String>();
		data.add("canc");
		data.add("nueva");
		data.add("vegas");
		data.add("vallarta");
		data.add("bogot");
		Pages pages = new Pages(driver);
		//<When>
		pages.hotelListPage_Initialize();
		//<Then>
		pages.hotelList_page.widgetVerifyAutocompleteDestination(data);
	}
	
	//En construccion
	public static void autocompleteTest(WebDriver driver) throws InterruptedException{
		//<Setup>
		logger.info("Starting workflow destinationAutocompleteTest");
		Reporter.log("Starting workflow destinationAutocompleteTest");
		//Por lo pronto esta harcodeado a las siguientes palabras de busqueda
		List<String> data = new ArrayList<String>();
		data.add("canc");
		data.add("nueva");
		data.add("vegas");
		data.add("vallarta");
		data.add("bogot");
		/*
		Pages pages = new Pages(driver);
		//<When>
		pages.hotelListPage_Initialize();
		pages.hotelList_page.sendKeysWidgetDestination(data.get(0));
		//<Then>
		pages.hotelList_page.verifyIsDisplayedWidgetDestinationAutocomplete();
		//<When>
		pages.hotelList_page.sendKeysWidgetDestination(Keys.ENTER);
		//<Then>
		pages.hotelList_page.verifyDestinationContains();
		pages.hotelList_page.clearDestination();
		*/
	}

	/**
	 * Este testcase valida que el campo destination del widget funcione correctamente.
	 * Se verifica:
	 * 1.- Que el campo sea actualizado despues de aplicar la busqueda
	 * 2.- Que el HeaderTitle cambie al destino buscado
	 * 3.- Que la pagina resultante de la busqueda contenga listado de hoteles
	 * 
	 * @param driver
	 * @throws InterruptedException
	 * @author enrique.lopez
	 */
	public static void searchUsingDifferentDestinTest(WebDriver driver){
		//<Setup>
		logger.info("Starting workflow changeDestinTest");
		Reporter.log("Starting workflow changeDestinTest");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		//<When>
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeDestin(voHotelResNew.getDestination());
		pages.hotelList_page.widgetClickSubmit();
		//<Then>
		//Verificar que el widget tiene el nuevo destino
		pages.hotelList_page.widgetVerifyDestinationToBe(voHotelResNew.getDestination());		
		//Verificar que el Page Header Title tiene el nuevo destino
		pages.hotelList_page.verifyHeaderTitleToBe(voHotelResNew.getDestination());
		//Verificar que tenemos resultados
		pages.hotelList_page.listVerifyResultListHasElements();
	}
	
	public static void searchUsingDifferentDatesTest(WebDriver driver) {
		//<Setup>
		logger.info("Starting workflow changeStartDateTest");
		Reporter.log("Starting workflow changeStartDateTest");
		//Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA,2);
		Pages pages = new Pages(driver);
		//<When>
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeStartDate(voHotelResNew.getStartDate());
		pages.hotelList_page.widgetChangeEndDate(voHotelResNew.getEndDate());
		pages.hotelList_page.widgetClickSubmit();
		//<Then>
		//Verificar que el widget tiene la nueva fecha
		pages.hotelList_page.widgetVerifyStartDateToBe(voHotelResNew.getStartDate());
		pages.hotelList_page.widgetVerifyEndDateToBe(voHotelResNew.getStartDate());
		//Verificar que la url tiene la nueva fecha
		pages.hotelList_page.verifyUrlContains(voHotelResNew.getStartDate("yyyy-MM-dd"),false);
		//Verificar que tenemos resultados
		pages.hotelList_page.listVerifyResultListHasElements();
	}
	
	public static void searchMoreRooms(WebDriver driver) {
		// <Setup>
		logger.info("Starting workflow searchMoreRooms");
		Reporter.log("Starting workflow searchMoreRooms");
		// Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOHotelRes voHotelResNew = DDManager.getHotelRes(FrameworkConfig.FILE_REGRESSIONHOTELRESDATA,11);// Aqui leo el row 11 donde tengo varios rooms
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelListPage_Initialize();
		pages.hotelList_page.widgetChangeSearch(voHotelResNew);
		pages.hotelList_page.widgetClickSubmit();
		// <Then>
		// Verificar que tenemos resultados
		pages.hotelList_page.listVerifyResultListHasElements();
	}
}
