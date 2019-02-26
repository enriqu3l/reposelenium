package dummy;

public class dummy {

	public static void main(String[] args) {
		/* Ejecucion con Maven
		 * mvn clean -----Limpa valores de antiguas ejecuciones
		 * mvn compile ---Verifica que no existan errores
		 * mvn test ------Ejecutas todos los test que se encuentren en la carpeta src/test/java
		 * mvn test -PHappypath --Para ejecutar con un perfil deseado
		 */
		
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
		 */
		
		/*
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
		
		/*
		ClassLoader classLoader = ExcelUtils.class.getClassLoader();
		String file = classLoader.getResource("SourceDataFiles/HPHotelResData.xlsx").getFile();
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
		
		/*
		 * Este ejemplo es usando Java 8 (no joda time)
		Local date formats: 1. dd/MM/yyyy 2. dd/MM/yy 3. yyyy/MM/dd
		LocalDate ld = LocalDate.now();
		System.out.println("ld yyyy/MM/dd format: "+ld.toString("yyyy/MM/dd"));
		System.out.println("ld dd/MM/yyyy format: "+ld.toString("dd/MM/yyyy"));
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
		
		/*
		//Validando el funcionamiento del nuevo archivo de Excel
		String filePath = FWConfig.PATH_DATASOURCE+FWConfig.FILE_HPHOTELRESDATA;
		int defaultRow = 1; //Para leer el primer registro del archivo
		
		VOResData voHotelResNew = new VOResData();
		//voHotelResNew.setDataUsingList(ExcelUtils.getRow(filePath, defaultRow));
		voHotelResNew.setDataUsingList(ExcelUtils.getRow(filePath, "ssssss"));
		
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
		*/
		
		//String url = BasicUtils.createUrlHotelLP("www.pricetravel.com", "hotel/grand-oasis-cancun", "2019-02-20", "2019-02-23", "3", "69364", "SPA-Hotel-List", "1", "1", "2");
		//System.out.println("URL: "+url);
		
		/*
		 * HackerRank - Counting Valley - Solution
		String s = "DDUUUUUDDDDDDUUUUUUUUDDD";
		int valley = 0;
		int level = 0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='D') {
				level--;
			}else if(s.charAt(i)=='U') {
				level++;
				if(level==0) {
					valley++;
				}
			}else {
				System.out.println("Error. Opcion no valida");
			}
		}
		System.out.println("Valleys: "+valley);
		*/
		
		
		
		
	}
}

/*Knowledge

1.- Cuando se abre una nueva tab, asegurarse de seleccionarla
porque el driver no switchea por si solo a la nueva tab!!

*/

/*Mover el mouse al "Input Destino" para que no afecte la seleccion de los elementos
try {
	Point location = Input_destHotel.getLocation();
    Robot robot;
	robot = new Robot();
	robot.mouseMove(location.getX(),location.getY()+120);
} catch (AWTException e) {
	e.printStackTrace();
	System.out.println("Error moviendo mouse: "+e.toString());
}*/

/*
 * Los actions no mueven el mouse, solo realizan acciones a nivel de DOM!!
 * Actions a = new Actions(driver);
 * a.moveToElement(Input_destHotel).build().perform();
*/