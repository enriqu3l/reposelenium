package Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasicUtils {
	
	//Check if a element is present
	public static boolean existsElement(WebDriver driver, By by) {
		return !driver.findElements(by).isEmpty();
	}
	
	public static boolean noExistsElement(WebDriver driver, By by) {
		return driver.findElements(by).isEmpty();
	}
	
	//Funciona a partir de Java version 8
	public static String getCurrentDatePlusMonth(int plusMonth) {
		if(plusMonth>12) {plusMonth=12;} //Aqui lo limito a m�ximo un a�o de avance
		LocalDate futureDate = LocalDate.now().plusMonths(plusMonth);
		return futureDate.toString("dd/MM/yyyy");
	}
	
	//Esta funcion No la estoy utilizando!!!
	//Esta funcion utiliza la libreria Date la cual ya fue sustituida por LocalDate en Java 8
	public static String getCurrentDatePlusMonth2(int plusMonth) {
		if(plusMonth>12) {plusMonth=12;}//Aqui lo limito a m�ximo un a�o de avance
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date current = new Date();
		if(plusMonth>0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(current);
			cal.add(Calendar.MONTH, plusMonth);
			current = cal.getTime();
		}
		return dateFormat.format(current);
	}
	
	/*//Seleccionar una fecha de un calendario, usar formato: dd/mm/yyyy
	public static void selectDateFromCalendar(WebDriver d,String setDateStr) {
		d.findElement(By.id("datepicker")).click();
		
		String currDateStr = d.findElement(By.className("ui-datepicker-title")).getText();
		Date setDate = new SimpleDateFormat( pattern: "dd/MM/yyyy").parse(setDateStr);
	}*/
	
	/*WebElement select = driver.findElement(By.id("ap_origin_flightPackage"));
	List<WebElement> options = select.findElements(By.tagName("li"));
	for (WebElement option1 : options) {
		if("SYDNEY, New South Wales, Australia, 1001".equals(option1.getText().trim()))
	    option1.click();   
	}  
	
	selenium.typeKeys("county_search", "lake");
	for (int second = 0;; second++) {
	    if (second >= 60) fail("timeout");
	    try { if (selenium.isTextPresent("Lake County, IL")) break; } catch (Exception e) {}
	    Thread.sleep(1000);
	}
	selenium.mouseOver("//html/body/ul/li/a[. = \"Lake County, IL\"]");
	selenium.click("//html/body/ul/li/a[. = \"Lake County, IL\"]");
	*/
	   
	//Como obtener elementos de una tag ul
	/*WebElement countryUL= driver.findElement(By.xpath("//[@id='country_id']/ul"));
	List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
	for (WebElement li : countriesList) {
	if (li.getText().equals("India (+91")) {
	     li.click();
	   }
	}*/
		
	public static By toByVal(WebElement we) {
	    // By format = "[foundFrom] -> locator: term"
	    // see RemoteWebElement toString() implementation
	    String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
	    String locator = data[0];
	    String term = data[1];

	    switch (locator) {
	    case "xpath":
	        return By.xpath(term);
	    case "css selector":
	        return By.cssSelector(term);
	    case "id":
	        return By.id(term);
	    case "tag name":
	        return By.tagName(term);
	    case "name":
	        return By.name(term);
	    case "link text":
	        return By.linkText(term);
	    case "class name":
	        return By.className(term);
	    }
	    return (By) we;
	}
	
	//Guardar un ScreenShot
	public static void ScreenShot(WebDriver d) {
		File src=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File("C:/Selenium/Test/Screen"+System.currentTimeMillis()+".png"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}//End of function
	
	//Guardar un ScreenShot y permite ponerle un nombre a la captura
	public static void ScreenShot(WebDriver d, String _name) {
		File src=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File("C:/PTFrameworkData/Screen_"+_name+System.currentTimeMillis()+".png"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}//End of function
	
	//Guardar un ScreenShot y permite ponerle un nombre a la capture y decidir donde guardar la capture
	/**
	 * @param webDriver
	 * @param name
	 * @param path
	 */
	public static void ScreenShot(WebDriver d, String _name, String _path) {
		File src=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File(_path+"Screen_"+_name+"_"+System.currentTimeMillis()+".png"));
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}//End of function

	//Funcion para pedir credenciales
	public static String[] requestCredentials() {
		Scanner scn = new Scanner(System.in);
		String[] credentials = new String[2];
		System.out.println("Ingresa tu Usuario|Email: ");
		credentials[0] = scn.nextLine();
		System.out.println("Ingresa tu Password: ");
		credentials[1] = scn.nextLine();
		scn.close();
		return credentials;
	}
	
	
	//----------- DATE & TIME Functions --------------------------------------
	
	public static int toMonthNumber(String month) {
		month = month.trim();
		//Lanza una excepcion si el parametro no es valido
		switch(month) {
		case "ENERO":
			return 1;
		case "FEBRERO":
			return 2;
		case "MARZO":
			return 3;
		case "ABRIL":
			return 4;
		case "MAYO":
			return 5;
		case "JUNIO":
			return 6;
		case "JULIO":
			return 7;
		case "AGOSTO":
			return 8;
		case "SEPTIEMBRE":
			return 9;
		case "OCTUBRE":
			return 10;
		case "NOVIEMBRE":
			return 11;
		case "DICIEMBRE":
			return 12;
		case "JANUARY":
			return 1;
		case "FEBRUARY":
			return 2;
		case "MARCH":
			return 3;
		case "APRIL":
			return 4;
		case "MAY":
			return 5;
		case "JUNE":
			return 6;
		case "JULY":
			return 7;
		case "AUGUST":
			return 8;
		case "SEPTEMBER":
			return 9;
		case "OCTOBER":
			return 10;
		case "NOVEMBER":
			return 11;
		case "DECEMBER":
			return 12;
		default:
			throw new IllegalArgumentException("toMonthNumber->El parametro no es una opcion valida.");
		}
	}
	
	public static String toMonthString(int month) {
		String monthString="";
		
		switch (month) {
        case 1:  monthString = "JANUARY";
                 break;
        case 2:  monthString = "FEBRUARY";
                 break;
        case 3:  monthString = "MARCH";
                 break;
        case 4:  monthString = "APRIL";
                 break;
        case 5:  monthString = "MAY";
                 break;
        case 6:  monthString = "JUNE";
                 break;
        case 7:  monthString = "JULY";
                 break;
        case 8:  monthString = "AUGUST";
                 break;
        case 9:  monthString = "SEPTEMBER";
                 break;
        case 10: monthString = "OCTOBER";
                 break;
        case 11: monthString = "NOVEMBER";
                 break;
        case 12: monthString = "DECEMBER";
                 break;
        default: monthString = "Invalid month";
                 break;
		}
		return monthString;
	}
	
	public static String toddMMyyyyFormat(String monthYear) {
		//Esta funcion solo acepta el formato: " mes yyyy ", "mes yyyy" o "mesyyyy"
		String monthYearTrimed = monthYear.trim();
		String monthWord = monthYearTrimed.substring(0,monthYearTrimed.length()-4).trim().toUpperCase();
		int month = BasicUtils.toMonthNumber(monthWord);
		int year = Integer.parseInt(monthYearTrimed.substring(monthYearTrimed.length()-4, monthYearTrimed.length()));
		
		String monthString="";
		if(Integer.toString(month).length()==1) {
			monthString = "0"+month;
		}else {
			monthString = Integer.toString(month); 
		}
		
		return "01/"+monthString+"/"+year;
	}
	
	public static int monthDiference(String expectedDate, String actualDate) {
		//Esta funcion solo acepta el formato dd/MM/yyyy!!
		LocalDate eDate = LocalDate.parse(expectedDate,DateTimeFormat.forPattern("dd/MM/yyyy"));
		LocalDate aDate = LocalDate.parse(actualDate,DateTimeFormat.forPattern("dd/MM/yyyy"));
		
		int yearDifference = eDate.getYear()-aDate.getYear();
		int monthDifference = eDate.getMonthOfYear()-aDate.getMonthOfYear();
		int TotalMountDifference=0;
		if(yearDifference!=0 || monthDifference!=0) {
			TotalMountDifference =  monthDifference+(yearDifference*12);
		}
		return TotalMountDifference;
	}
}
