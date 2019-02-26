package valueobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.testng.Assert;

import config.CoreConfig;

public class VOResData {
	public static final String ADULTS = "adults";
	public static final String KIDS = "kids";
	public static final String AGEKIDS = "agekids";
	
	private String idRes;
	private String origin;
	private String destination;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<Map<String, String>> rooms;
	
	public VOResData() {
		super();
		this.idRes = "";
		this.origin = "";
		this.destination = "";
		this.startDate = LocalDate.parse("2000-01-01");
		this.endDate = LocalDate.parse("2000-01-01");
		this.rooms = new ArrayList<Map<String, String>>();
	}
	
	/**
	 * @param idHotelRes - String. Identificador de la reservacion
	 * @param origin - String. Origen
	 * @param destination - String. Destino
	 * @param startDate - String. Fecha de inicio de la reservacion
	 * @param endDate - String. Fecha final de la reservacion
	 * @param rooms - List-Map-String,String--. Lista con los datos de cada room
	 */
	public VOResData(String idHotelRes, String origin, String destination, String startDate, String endDate,
			List<Map<String, String>> rooms) {
		super();
		this.idRes = idHotelRes.trim();
		this.origin = origin.trim();
		this.destination = destination.trim();
		this.startDate = LocalDate.parse(startDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
		this.endDate = LocalDate.parse(endDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
		this.rooms = rooms;
	}
	
	
	public VOResData(String idHotelRes, String origin, String destination, LocalDate startDate, LocalDate endDate,
			List<Map<String, String>> rooms) {
		super();
		this.idRes = idHotelRes.trim();
		this.origin = origin.trim();
		this.destination = destination.trim();
		this.startDate = startDate;
		this.endDate = endDate;
		this.rooms = rooms;
	}

	public String getIdHotelRes() {
		return idRes;
	}

	public void setIdHotelRes(String idHotelRes) {
		this.idRes = idHotelRes;
	}
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Regresa la fecha en formato LocalDate
	 * @return LocalDate
	 */
	public LocalDate getStartDateAsLocalDate() {
		return startDate;
	}
	
	/**
	 * Regresa la fecha en formato dd/MM/yyyy
	 * @return String.
	 */
	public String getStartDate() {
		return startDate.toString("dd/MM/yyyy");
	}
	
	/**
	 * El formato puede ser dd/MM/yyyy, MM/dd/yyyy, yyyy/MM/dd, ...
	 * @param format. String
	 * @return String. Fecha en formato indicado
	 */
	public String getStartDate(String format) {
		//Se supone que toString manda a llamar a un: DateTimeFormat.forPattern(format);
		return startDate.toString(format);
	}
	
	/**
	 * El formato puede ser cualquiera aceptado por jodatime
	 * @param format. String
	 * @return String. Fecha en formato indicado
	 */
	public String getStartDate(DateTimeFormatter format) {
		return startDate.toString(format);
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = LocalDate.parse(startDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
	}

	/**
	 * Regresa la fecha en formato LocalDate
	 * @return LocalDate
	 */
	public LocalDate getEndDateAsLocalDate() {
		return endDate;
	}
	
	/**
	 * Regresa la fecha en formato dd/MM/yyyy
	 * @return String.
	 */
	public String getEndDate() {
		return endDate.toString("dd/MM/yyyy");
	}
	
	/**
	 * El foramto puede ser dd/MM/yyyy, MM/dd/yyyy, yyyy/MM/dd, ...
	 * @param format. String
	 * @return String. Fecha en formato indicado
	 */
	public String getEndDate(String format) {
		//Se supone que toString manda a llamar a un: DateTimeFormat.forPattern(format);
		return endDate.toString(format);
	}
	
	/**
	 * El foramto puede ser cualquiera aceptado por jodatime
	 * @param format. String
	 * @return String. Fecha en formato indicado
	 */
	public String getEndDate(DateTimeFormatter format) {
		return endDate.toString(format);
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = LocalDate.parse(endDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
	}

	public List<Map<String, String>> getRooms() {
		return rooms;
	}
	
	public Map<String, String> getRoom(int i) {
		return rooms.get(i);
	}

	public void setRooms(List<Map<String, String>> rooms) {
		this.rooms = rooms;
	}
	
	public void addRoom(Map<String, String> room) {
		this.rooms.add(room);
	}
	
	public int getRoomCount() {
		return rooms.size();
	}
	
	/**
	 * Regresa el adult del cuarto indicado. Base 0
	 * @param roomIndex.
	 * @return int.
	 */
	public int getAdultsFromRoom(int roomIndex) {
		return Integer.parseInt(rooms.get(roomIndex).get(ADULTS));
	}
	
	/**
	 * Regresa el kid del cuarto indicado. Base 0
	 * @param roomIndex
	 * @return
	 */
	public int getKidsFromRoom(int roomIndex) {
		return Integer.parseInt(rooms.get(roomIndex).get(KIDS));
	}
	
	public String getAgekidsFromRoom(int roomIndex) {
		return rooms.get(roomIndex).get(AGEKIDS);
	}
	
	/**
	 * Esta funcion devuelve in objeto int[]
	 * @param roomIndex int. indice del room. Base 0
	 */
	public int[] getAgekidsFromRoomIntArray(int roomIndex) {
		if(roomIndex >= rooms.size()) {System.out.println("roomIndex fuera de rango");Assert.fail("roomIndex fuera de rango");}
		
		String ageKids = rooms.get(roomIndex).get(AGEKIDS);
		String[] ageArray = ageKids.split(CoreConfig.SPLIT);//splits the string based on ","  
		int[] ageIntArray =  new int[ageArray.length];
		//using java foreach loop to print elements of string array  
		int i=0;
		for(String age:ageArray){  
			i++;
			ageIntArray[i] = Integer.parseInt(age.trim());   
		}
		return ageIntArray;
	}
	
	/**
	 * Esta funcion devuelve in objeto List-Integer-
	 * @param roomIndex int. indice del room. Base 0
	 */
	public List<Integer> getAgekidsFromRoomArrayList(int roomIndex) {
		if(roomIndex >= rooms.size()) {System.out.println("roomIndex fuera de rango");Assert.fail("roomIndex fuera de rango");}
		String ageKids = rooms.get(roomIndex).get(AGEKIDS);
		String[] ageArray = ageKids.split(CoreConfig.SPLIT);//splits the string based on "," 
		List<Integer> ageList =  new ArrayList<Integer>();
		//using java foreach loop to print elements of string array  
		for(String age:ageArray){
			ageList.add(Integer.parseInt(age.trim()));
		}
		return ageList;
	}
	
	/**
	 * Esta funcion devuelve la cantidad total de niños en todos los cuartos
	 * @return Int
	 */
	public int getKidsCount() {
		int result=0;
		for(int i=0;i<rooms.size();i++) {
			 result = result + getKidsFromRoom(i);
		}
		return result;
	}
	
	/**
	 * Esta funcion devuelve un valor de tipo int
	 * @param roomIndex int. indice del room. Base 0
	 * @param kidIndex int. indice del kid. Base 0
	 */
	public int getAgeFromAgekids(int roomIndex, int kidIndex) {
		if(roomIndex >= rooms.size()) {System.out.println("roomIndex fuera de rango");Assert.fail("roomIndex fuera de rango");}
		String ageKids = rooms.get(roomIndex).get(AGEKIDS);
		String[] ageArray = ageKids.split(CoreConfig.SPLIT);//splits the string based on "," 
		
		if(kidIndex >= ageArray.length){System.out.println("kidIndex fuera de rango");Assert.fail("kidIndex fuera de rango");}
		return Integer.parseInt(ageArray[kidIndex].trim());
	}
	
	
	/**
	 * Esta funcion obtiene un Listado-String- y lo almacena en valores adecuados para la clase 
	 * @param data List. listado de Strings
	 */
	public void setDataUsingList(List<String> data) {
		/*According Excel file, this is how data is structured:
		 * 0 - IdHotelRes
		 * 1 - origin
		 * 2 - Destination
		 * 3 - PlusDays
		 * 4 - ResDays
		 * 5 - Room1Adults
		 * 6 - Room1Kids
		 * 7 - Room1AgeKids
		 * 8 - Room2Adults
		 * 9 - Room2Kids
		 * 10 - Room2AgeKids
		 * 11 - Room3Adults
		 * 12 - Room3Kids
		 * 13 - Room3AgeKids
		 * ...
		 */
		
		if(data==null || data.isEmpty()){System.out.println("VOResData.setDataUsingList() La lista recibida esta vacia");Assert.assertFalse(data.isEmpty(),"data is empty");}
		this.idRes = data.get(0).trim();
		this.origin = data.get(1).trim();
		this.destination = data.get(2).trim();
		int startDay = Integer.parseInt(data.get(3).trim());
		this.startDate = LocalDate.now().plusDays(startDay);
		int endDay = Integer.parseInt(data.get(3).trim()) + Integer.parseInt(data.get(4).trim());
		this.endDate = LocalDate.now().plusDays(endDay);
		this.rooms = null;
		this.rooms = new ArrayList<Map<String, String>>();
		for(int i=5;i<34;i+=3) {
			//Validamos que la columna de Adultos NO este vacia o No tenga valor "0", dado que un cuarto no puede tener 0 adultos
			if(!(data.get(i).isEmpty() || data.get(i).trim().equals("0"))) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(ADULTS, data.get(i).trim());
				map.put(KIDS, data.get(i+1).trim());
				map.put(AGEKIDS, data.get(i+2).trim());
				this.rooms.add(map);
				map = null;
			}
		}
	}
	
	/*
	List<Map<String, String>> myListOfMaps = new ArrayList<Map<String, String>>();
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("Fname", "Ankur");

    Map<String, String> map2 = new HashMap<String, String>();
    map2.put("Lname", "Singhal");

    myListOfMaps.add(map1);
    myListOfMaps.add(map2);

    for (int i = 0 ; i < myListOfMaps.size() ; i++) {
        Map<String, String> myMap = myListOfMaps.get(i);
        System.out.println("Data For Map" + i);
        for (Entry<String, String> entrySet : myMap.entrySet()) {
            System.out.println("Key = " + entrySet.getKey() + " , Value = " + entrySet.getValue());
        }
    }
	*/
	
	
	/*
	Map<Long,List<String>> myMap=new HashMap<Long,List<String>>();
    List<String> myList=new ArrayList<String>();
    myList.add("abc");
    myList.add("xyz");
    myMap.put(new Long(1), myList);
    for(int i=0;i<myList.size();i++)
        System.out.println(myMap.get(new Long(1)).get(i));
	*/
	
	
	/*
	public void setDataUsingList(List<String> data) {
		if(data==null || data.isEmpty()){System.out.println("La lista esta vacia");Assert.assertFalse(data.isEmpty());}
		this.idHotelRes = data.get(0);
		this.destination = data.get(1);
		this.startDate = data.get(2);
		this.endDate = data.get(3);
		this.adults = Integer.parseInt(data.get(4));
		this.kids = Integer.parseInt(data.get(5));
		if(Integer.parseInt(data.get(5)) > 0) {
			int[] agekids = new int[Integer.parseInt(data.get(5))];
			for(int i=0;i<Integer.parseInt(data.get(5));i++) {
				agekids[i]=Integer.parseInt(data.get(6+i));
			}
			this.ageKids = agekids;
		}else {
			this.ageKids = null;
		}
	}
	*/
}
