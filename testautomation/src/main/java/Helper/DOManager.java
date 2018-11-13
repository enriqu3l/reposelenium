package Helper;

import java.util.Random;

import DataObjects.DOHotelRes;
import Utility.ExcelUtils;

public class DOManager {
	public static String rHotelResData = "SourceDataFiles/HotelResData.xlsx";
	public static String rCreditCardData = "SourceDataFiles/CreditCardsData.xlsx";
	public static String rClientData = "SourceDataFiles/ClientData.xlsx";
	
	//Constructor
	public DOManager() {
	}
	
	public  static DOHotelRes getHotelResByListItem(int item) {
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
}