package testautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.FrameworkConfig;
import helpers.BrowserFactory;
import helpers.DDManager;
import utility.ExcelUtils;
import valueobjects.VOHotelResNew;

public class testautomation {

	public static void main(String[] args) {
		/* Ejecucion con Maven
		 * mvn clean -----Limpa valores de antiguas ejecuciones
		 * mvn compile ---Verifica que no existan errores
		 * mvn test ------Ejecutas todos los test que se encuentren en la carpeta src/test/java
		 * mvn test -PHappypath --Para ejecutar con un perfil deseado
		 */
		
		
		/*String data[][];
		data = ExcelUtils.getDataTableFormat("ProductHotelData.xlsx","C:\\Automation\\",1,1,4,5);
		data = ExcelUtils.getDataTableFormatR("SourceDataFiles/HotelResData.xlsx", 1, 1, 1, 12);
		System.out.println("Data [0][0]: " + data[0][0]);
		*/
		
		//int row = ExcelUtils.getRowCountR("SourceDataFiles/HotelResData.xlsx");
		//int column = ExcelUtils.getColumnCountR("SourceDataFiles/HotelResData.xlsx");
		//System.out.println("Numero de Rows: " + row);
		//System.out.println("Numero de Columns: " + column);
		
		
		/*
		List<String> row = ExcelUtils.getRowR("SourceDataFiles/HotelResData.xlsx",4);
		int i=0;
		Iterator<String> iterator = row.iterator();
		while (iterator.hasNext()) {
			i++;
		    System.out.println("item["+i+"]: "+iterator.next());
		}
		*/
		
		
		/*Aun no soluciono el error de editar y guardar el recurso excel
		 * Se esta guardando el archivo de target pero no el de src
		 * Estare guardando capturas de pantalla en lo que veo que hacer
		 * con este asunto.
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String currentDate= dateFormat.format(date);
		List<String> data = new ArrayList<String>();
		data.add("45234932");
		data.add(currentDate);
		System.out.println("Lista Dato 1: "+data.get(0));
		System.out.println("Lista Dato 1: "+data.get(1));
		ExcelUtils.saveNewRowInExistingFile("SourceDataFiles/LocatorsGenerated.xlsx", data);
		*/
		
		/*String date = BasicUtils.getCurrentDatePlusMonth(1);
		String date2 = BasicUtils.getCurrentDatePlusMonth2(1);
		System.out.println("Fecha1 mas 1 mes: "+date);
		System.out.println("Fecha2 mas 1 mes: "+date2);
		*/
		
		/*
		ClassLoader classLoader = ExcelUtils.class.getClassLoader();
		String file = classLoader.getResource("SourceDataFiles/HotelResData.xlsx").getFile();
		System.out.println("File path: "+file);
		*/
		
		/*String date = LocalDateTime.now().toString("dd/MM/yyyy_HH:mm:ss");
		System.out.println("Fecha de ahora: "+date);	
		String date = "4/4/2004";
	    LocalDate inputDate = LocalDate.parse(date,DateTimeFormat.forPattern("dd/MM/yyyy"));
	    String date2 = inputDate.toString("dd/MM/yyyy");
	    System.out.println("Date2: "+date2);
		String date = " ENEERO 2018 ";
		String result = BasicUtils.toddMMyyyyFormat(date);
		System.out.println("Result: "+result);
		*/
		
		//WebDriver driver = BrowserFactory.StartBrowser("chrome", "https://www.pricetravel.com/hoteles/cancun-area?room1.adults=2&room1.kids=0&room1.agekids=&checkin=2018%2F12%2F26&checkout=2018%2F12%2F28&rooms=1&adults=2&kids=0&agekids=&pdisplay=Canc%C3%BAn%20(y%20alrededores),%20M%C3%A9xico&placeid=69364&placetype=3&puri=cancun-area&quotelist=true&returningfromairport=&startingfromairport=&actiontype=1");
		//HotelListPageF hotelList_page;
		//hotelList_page = new HotelListPageF(driver);
		//hotelList_page.SelectFirstHotel();
		
		//By listProductBlock = By.cssSelector(".sdsd .sddsd");
		//System.out.println("Contenido: "+listProductBlock.toString());
		
		/*WebDriver driver = BrowserFactory.StartBrowser("chrome", "http://www.itgeared.com/demo/1506-ajax-loading.html");
		By link = By.xpath("//*[@id=\"content\"]/a[2]");
		By text = By.xpath("//*[@id=\"results\"]");
		driver.findElement(link).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(text,"completed"));
		String texto = driver.findElement(text).getText();
		System.out.println("Texto: "+texto);*/
		
		/*
		WebDriver driver = BrowserFactory.StartBrowser("chrome", "http://qaclickacademy.com/practice.php");
		WebDriverWait wait = new WebDriverWait(driver,30);
		By cb1 = By.id("checkBoxOption1");
		By cb2 = By.id("checkBoxOption2");
		By cb3 = By.id("checkBoxOption3");
		By label1 = By.cssSelector("#checkbox-example label[for='bmw']");
		By label2 = By.cssSelector("#checkbox-example label[for='benz']");
		By label3 = By.cssSelector("#checkbox-example label[for='honda']");
		By dropdown = By.id("dropdown-class-example");
		By inputAlert = By.cssSelector(".alert_example #name");
		By buttonAlert = By.cssSelector(".alert_example #alertbtn");
		driver.findElement(cb2).click();
		
		String opc="";
		if(driver.findElement(cb1).isSelected() )
		{
			opc = driver.findElement(label1).getText();
		}else if(driver.findElement(cb2).isSelected()) {
			opc = driver.findElement(label2).getText();
		}else if(driver.findElement(cb3).isSelected()) {
			opc = driver.findElement(label3).getText();
		}
		
		Select select = new Select(driver.findElement(dropdown));
		select.selectByVisibleText(opc);
		
		driver.findElement(inputAlert).sendKeys(opc);
		driver.findElement(buttonAlert).click();
		
		String alertText = driver.switchTo().alert().getText();
		if(alertText.contains(opc)) {
			System.out.println("Great! Text Found...");
		}
		*/
		
		String filePath = FrameworkConfig.PATH_DATASOURCE+FrameworkConfig.FILE_HOTELLISTWIDGETDATA;
		int defaultRow = 1; //Para leer el primer registro del archivo
		
		VOHotelResNew voHotelResNew = new VOHotelResNew();
		voHotelResNew.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		
		System.out.println("idHotelRes: "+voHotelResNew.getIdHotelRes());
		System.out.println("destination: "+voHotelResNew.getDestination());
		System.out.println("startDate: "+voHotelResNew.getStartDate());
		System.out.println("endDate: "+voHotelResNew.getEndDate());
		System.out.println("rooms Count: "+voHotelResNew.getRoomCount());
		for(int i=0;i<voHotelResNew.getRoomCount();i++) {
			System.out.println("Adults in room["+i+"]: "+voHotelResNew.getAdultsFromRoom(i));
			System.out.println("Kids in room["+i+"]: "+voHotelResNew.getKidsFromRoom(i));
			System.out.println("AgeKids in room["+i+"]: "+voHotelResNew.getAgekidsFromRoom(i));
			for(int j=0;j<voHotelResNew.getKidsFromRoom(i);j++) {
				System.out.println("Age of Kid "+j+" in room["+i+"]: "+voHotelResNew.getAgeFromAgekids(i, j));
			}
			System.out.println("-----------------------------------");
		}
		
	}
}