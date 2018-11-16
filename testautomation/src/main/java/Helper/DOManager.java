package Helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import Utility.ExcelUtils;

public class DOManager {
	public static String rHotelResData = "SourceDataFiles/HotelResData.xlsx";
	public static String rCreditCardData = "SourceDataFiles/CreditCardsData.xlsx";
	public static String rClientData = "SourceDataFiles/ClientData.xlsx";
	public static String rLocatorsGenerated = "SourceDataFiles/LocatorsGenerated.xlsx";
	
	//Constructor
	public DOManager() {
	}
	
	public  static DOHotelRes getHotelRes(int item) {
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCountR(rHotelResData)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		DOHotelRes DOHotelRes = new DOHotelRes();
		DOHotelRes.setDataUsingList(ExcelUtils.getRowR(rHotelResData, item));
		return DOHotelRes;
	}
	
	public static DOHotelRes getRandomHotelRes() {
		int min = 1;
		int max = ExcelUtils.getRowCountR(rHotelResData)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		DOHotelRes DOHotelRes = new DOHotelRes();
		DOHotelRes.setDataUsingList(ExcelUtils.getRowR(rHotelResData, randomNum));
		return DOHotelRes;
	}
	
	public static DOCreditCard getCreditCard(int item) {
		//Candado que limita los valores que puede tener la variable item
		int max = ExcelUtils.getRowCountR(rCreditCardData)-1;
		if(item<=0){item=1;}
		if(item>max){item=max;}
		
		DOCreditCard DOCreditCard = new DOCreditCard();
		DOCreditCard.setDataUsingList(ExcelUtils.getRowR(rCreditCardData, item));
		return DOCreditCard;
	}
	
	public static DOCreditCard getRandomCreditCard() {
		int min = 1;
		int max = ExcelUtils.getRowCountR(rCreditCardData)-1; //Le resto 1 porque la primer fila es la 0
		//Asi se obtiene un numero random entre valor min y max.
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		DOCreditCard DOCreditCard = new DOCreditCard();
		DOCreditCard.setDataUsingList(ExcelUtils.getRowR(rCreditCardData, randomNum));
		return DOCreditCard;
	}
	
	//Aun no la usaré, hasta hacer la modificacion para que el archivo LocatorsGenerated.xlsx
	//sea alojado directamente en C en vez de alojarlo dentro del proyecto
	public static boolean saveLocator(String locator) {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		
		List<String> data = new ArrayList<String>();
		data.add(locator);
		data.add(currentDate);
		
		ExcelUtils.saveNewRowInExistingFile(rLocatorsGenerated, data);
		
		return true;
	}
	
	public static String getLocator(int item) {
		//Aqui el codigo para obtener el locator en base a el listado
		
		return "";
	}
}