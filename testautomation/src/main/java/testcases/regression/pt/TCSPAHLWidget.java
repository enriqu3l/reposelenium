package testcases.regression.pt;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import config.FWConfig;
import helpers.DDManager;
import pages.pt.Pages;
import testbases.TBRegressionSPAHL;
import valueobjects.VOResData;

public class TCSPAHLWidget extends TBRegressionSPAHL{

	@Test(enabled = true)
	public void autocompleteDestinationTest() {
		Reporter.log("Starting autocompleteDestinationTest");
		logger.info("Starting autocompleteDestinationTest");
		// <Setup>
		logger.info("Starting autocompleteDestinationTest");
		Reporter.log("Starting autocompleteDestinationTest");
		// Por lo pronto esta harcodeado a las siguientes palabras de busqueda
		List<String> data = new ArrayList<String>();
		data.add("canc");
		data.add("nueva");
		data.add("vegas");
		data.add("vallarta");
		data.add("bogot");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		// <Then>
		// Verificar que el autocompletado del campo destino funcione correctamente
		Assert.assertTrue(pages.hotelList.widget.verifyAutocompleteDestination(data));
	}

	@Test(enabled = true)
	public void datePickersFunctionalityTest() {
		Reporter.log("Starting datePickersFunctionalityTest");
		logger.info("Starting datePickersFunctionalityTest");
		// <Setup>
		logger.info("Starting datePickersFunctionalityTest");
		Reporter.log("Starting datePickersFunctionalityTest");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		// <Then>
		// Verificar que las fechas iniciales sean las que tenga la URL
		Assert.assertTrue(pages.hotelList.widget.verifyCurrentUrlDateOnDatePickers());
		// Verificar que que el datepicker se abra y se cierre en 10 iteraciones
		Assert.assertTrue(pages.hotelList.widget.verifyOpenAndCloseDatePickers());
	}

	@Test(enabled = true)
	public void searchWithEmptyFieldsTest() {
		Reporter.log("Starting searchWithEmptyFieldsTest");
		logger.info("Starting searchWithEmptyFieldsTest");
		// <Setup>
		logger.info("Starting searchWithEmptyFieldsTest");
		Reporter.log("Starting searchWithEmptyFieldsTest");
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		pages.hotelList.widget.clearDestination();
		pages.hotelList.widget.clickSubmit();
		// <Then>
		// Verificar que se muestre el mensaje de error si no se ingresa un destino
		Assert.assertTrue(pages.hotelList.widget.verifyErrorPlace());
	}

	@Test(enabled = true)
	public void searchDifferentDestinTest() {
		Reporter.log("Starting searchDifferentDestinTest");
		logger.info("Starting searchDifferentDestinTest");
		// <Setup>
		logger.info("Starting searchDifferentDestinTest");
		Reporter.log("Starting searchDifferentDestinTest");
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		pages.hotelList.widget.setDestin(voHotelRes.getDestination());
		pages.hotelList.widget.clickSubmit();
		// <Then>
		// Verificar que el widget tiene el nuevo destino
		Assert.assertEquals(pages.hotelList.widget.getDestination(), voHotelRes.getDestination(),"Error, el destino es el esperado: "+voHotelRes.getDestination());
		// Verificar que el Page Header Title tiene el nuevo destino
		Assert.assertTrue(pages.hotelList.getHeaderTitle().contains(voHotelRes.getDestination()),"Error, el titulo no es el esperado:"+voHotelRes.getDestination());
		// Verificar que tenemos resultados
		pages.hotelList.list.verifyResultListHasElements();
	}

	@Test(enabled = true)
	public void searchDifferentDatesTest() {
		Reporter.log("Starting searchDifferentDatesTest");
		logger.info("Starting searchDifferentDatesTest");
		// <Setup>
		logger.info("Starting searchDifferentDatesTest");
		Reporter.log("Starting searchDifferentDatesTest");
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 2);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		pages.hotelList.widget.setStartDate(voHotelRes.getStartDate());
		pages.hotelList.widget.setEndDate(voHotelRes.getEndDate());
		pages.hotelList.widget.clickSubmit();
		// <Then>
		// Verificar que el widget tiene la nueva fecha
		Assert.assertEquals(pages.hotelList.widget.getStartDate(), voHotelRes.getStartDate(),"Error, la fecha de inicio no es la esperado:"+voHotelRes.getStartDate());
		Assert.assertEquals(pages.hotelList.widget.getEndDate(), voHotelRes.getEndDate(),"Error, la fecha de fin no es la esperado:"+voHotelRes.getEndDate());
		// Verificar que la url tiene la nueva fecha
		
		pages.hotelList.verifyUrlStartDateToBe(voHotelRes.getStartDate("yyyy-MM-dd"));
		pages.hotelList.verifyUrlEndDateToBe(voHotelRes.getEndDate("yyyy-MM-dd"));
		// Verificar que la lista de resultados tenga elementos
		pages.hotelList.list.verifyResultListHasElements();
	}

	@Test(enabled = true)
	public void searchDifferentOccupantsTest() {
		Reporter.log("Starting searchDifferentOccupantsTest");
		logger.info("Starting searchDifferentOccupantsTest");
		// <Setup>
		logger.info("Starting searchDifferentOccupantsTest");
		Reporter.log("Starting searchDifferentOccupantsTest");
		// Aqui leo el row 11 donde tengo varios rooms
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 11);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		pages.hotelList.widget.setOccupants(voHotelRes);
		pages.hotelList.widget.clickSubmit();
		// <Then>
		// Verificar que los ocupantes sean los que se seleccionaron
		pages.hotelList.widget.verifyOccupantsToBe(voHotelRes);
		// Verificar que la lista de resultados tenga elementos
		pages.hotelList.list.verifyResultListHasElements();
	}

	@Test(enabled = true)
	public void searchDifferentReservationTest() {
		Reporter.log("Starting searchDifferentReservationTest");
		logger.info("Starting searchDifferentReservationTest");
		// <Setup>
		logger.info("Starting searchDifferentReservationTest");
		Reporter.log("Starting searchDifferentReservationTest");
		// Obtengo el row 12 del archivo
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 12);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		pages.hotelList.widget.setReservation(voHotelRes);
		pages.hotelList.widget.clickSubmit();
		// <Then>
		// Verificar que la info de la reservacion sea la que se selecciono
		Assert.assertTrue(pages.hotelList.widget.verifyReservationToBe(voHotelRes));
		// Verificar que la lista de resultados tenga elementos
		pages.hotelList.list.verifyResultListHasElements();
	}

	@Test(enabled = false)
	public void prueba() {
		Reporter.log("Starting prueba");
		logger.info("Starting prueba");
		// Esta funcion la uso para pruebas durante el desarrollo
		// Estoy revisando el comportamiento cuando realizo una busqueda pero sin
		// realizar cambios...

		// <Setup>
		logger.info("Starting prueba");
		Reporter.log("Starting prueba");
		// Aqui estoy utilizando una funcion del DDManager para generar el DefaultData
		VOResData voHotelRes = DDManager.getResData(FWConfig.FILE_REGRESSIONHOTELRESDATA, 1);
		Pages pages = new Pages(driver);
		// <When>
		pages.hotelList_Initialize();
		pages.hotelList.widget.setDestin(voHotelRes.getDestination());
		pages.hotelList.widget.clickSubmit();
		// <Then>
		// Verificar que el widget tiene el nuevo destino
		Assert.assertEquals(pages.hotelList.widget.getDestination(), voHotelRes.getDestination(),"Error, el destino es el esperado: "+voHotelRes.getDestination());
		// Verificar que el Page Header Title tiene el nuevo destino
		Assert.assertTrue(pages.hotelList.getHeaderTitle().contains(voHotelRes.getDestination()),"Error, el titulo no es el esperado:"+voHotelRes.getDestination());
		// Verificar que tenemos resultados
		pages.hotelList.list.verifyResultListHasElements();
	}
}
