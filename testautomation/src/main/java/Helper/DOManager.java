package Helper;

import java.util.List;

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
	
	public void getRandomHotelRes() {
		//Aqui todo el codigo para seleccionar un hotel random
		
	}
}