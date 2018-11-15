package Helper;

import java.util.Random;

import DataObjects.DOCreditCard;
import DataObjects.DOHotelRes;
import Utility.ExcelUtils;

public class DOManager {
	public static String rHotelResData = "SourceDataFiles/HotelResData.xlsx";
	public static String rCreditCardData = "SourceDataFiles/CreditCardsData.xlsx";
	public static String rClientData = "SourceDataFiles/ClientData.xlsx";
	
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
	
	public static boolean saveLocator(String locator) {
		//Aqui el codigo para guardar el localizador
		
		
		return true;
	}
	
	public static String getLocator(String locator) {
		//Aqui el codigo para obtener el locator en base a un match
		
		
		return "";
	}
	
	public static String getLocator(int item) {
		//Aqui el codigo para obtener el locator en base a el listado
		
		
		return "";
	}
}