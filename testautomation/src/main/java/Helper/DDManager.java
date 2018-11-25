package Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import FrameworkConfig.GeneralConfig;
import Utility.ExcelUtils;

public class DDManager {
	/*Ya no usare resources internos, ahora los archivos estaran fuera del proyecto
	public static String rHotelResData = "SourceDataFiles/HotelResData.xlsx";
	public static String rCreditCardData = "SourceDataFiles/CreditCardsData.xlsx";
	public static String rClientData = "SourceDataFiles/ClientData.xlsx";
	public static String rLocatorsGenerated = "SourceDataFiles/LocatorsGenerated.xlsx";
	*/
	
	//Constructor
	public DDManager() {
	}
	
	//-------------------------HOTEL RES------------------------------
	public static DOHotelRes getHotelResDefault() {
		LocalDate startDate;
		LocalDate endDate;
		DOHotelRes DOHotelRes = new DOHotelRes();
		DOHotelRes.setIdHotelRes("HR001");
		DOHotelRes.setDestination(GeneralConfig.R_DESTIN);
		startDate = LocalDate.now().plusMonths(GeneralConfig.R_STARTDATE_PlusMonth);
		DOHotelRes.setStartDate(startDate.toString("dd/MM/yyyy"));
		endDate = startDate.plusDays(GeneralConfig.R_ENDDATE_PlusDay);
		DOHotelRes.setEndDate(endDate.toString("dd/MM/yyyy"));
		DOHotelRes.setAdults(GeneralConfig.R_ADULTS);
		DOHotelRes.setKids(GeneralConfig.R_KIDS);
		return DOHotelRes;
	}//EndFunction
	
	public  static DOHotelRes getHotelRes(int item) {
		String filePath = GeneralConfig.DATASOURCE_PATH+GeneralConfig.FILE_HotelResData;
		
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		DOHotelRes DOHotelRes = new DOHotelRes();
		DOHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return DOHotelRes;
	}//EndFunction
	
	public static DOHotelRes getRandomHotelRes() {
		String filePath = GeneralConfig.DATASOURCE_PATH+GeneralConfig.FILE_HotelResData;
		
		int min = 1;  //Comienza en 1 porque la fila de cabecera es la 0
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		System.out.println("randomNum: "+randomNum);
		
		DOHotelRes DOHotelRes = new DOHotelRes();
		DOHotelRes.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return DOHotelRes;
	}//EndFunction
	
	//-------------------------CREDIT CARD------------------------------
	public static DOCreditCard getCreditCardDefault() {
		//Me falta jalar los datos desde la Clase GeneralConfig
		DOCreditCard DOCreditCard = new DOCreditCard();
		DOCreditCard.setNumber(Long.parseLong("4772130000000003"));
		DOCreditCard.setHolderName("Virginia Chavez");
		DOCreditCard.setMonth("08");
		DOCreditCard.setYear("2021");
		DOCreditCard.setCCV(114);
		DOCreditCard.setCountry("MX");
		DOCreditCard.setCP(44777);
		DOCreditCard.setBank("Bancomer");
		DOCreditCard.setPayNetwork(GeneralConfig.CardTypes.VISA);
		return DOCreditCard;
	}//EndFunction
	
	public static DOCreditCard getCreditCard(int item) {
		String filePath = GeneralConfig.DATASOURCE_PATH+GeneralConfig.FILE_CreditCardsData;
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCount(filePath)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		DOCreditCard DOCreditCard = new DOCreditCard();
		DOCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, item));
		return DOCreditCard;
	}
	
	public static DOCreditCard getRandomCreditCard() {
		String filePath = GeneralConfig.DATASOURCE_PATH+GeneralConfig.FILE_CreditCardsData;
		int min = 1;
		int max = ExcelUtils.getRowCount(filePath)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		DOCreditCard DOCreditCard = new DOCreditCard();
		DOCreditCard.setDataUsingList(ExcelUtils.getRow(filePath, randomNum));
		return DOCreditCard;
	}
	
	//Aun no la usar�, hasta hacer la modificacion para que el archivo LocatorsGenerated.xlsx
	//sea alojado directamente en C en vez de alojarlo dentro del proyecto
	public static boolean saveLocator(String locator) {
		String filePath = GeneralConfig.DATASOURCE_PATH+GeneralConfig.FILE_LocatorsGenerated;
		List<String> data = new ArrayList<String>();
		data.add(locator);
		data.add(LocalDateTime.now().toString("dd/MM/yyyy_HH:mm:ss"));
		ExcelUtils.saveNewRowInExistingFile(filePath, data);
		return true;
	}
	
	public static String getLocator(int item) {
		//Aqui el codigo para obtener el locator en base a el listado
		
		return "";
	}
}