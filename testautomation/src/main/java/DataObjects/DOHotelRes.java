package DataObjects;

import java.util.List;

import Utility.ExcelUtils;

public class DOHotelRes {
	//A esta clase me falta gregarle la variable de los Rooms!!!!!
	
	private String idHotelRes;
	private String destination;
	private String startDate;
	private String endDate;
	private int adults;
	private int kids;
	private int[] ageKids;
	
	public DOHotelRes() {
		super();
		this.idHotelRes = "";
		this.destination = "";
		this.startDate = "";
		this.endDate = "";
		this.adults = 0;
		this.kids = 0;
		this.ageKids = null;
	}
	
	/**
	 * @param hotelProductType
	 * @param destination
	 * @param startDate
	 * @param endDate
	 * @param adults
	 * @param kids
	 * @param ageKids
	 */
	public DOHotelRes(String idHotelRes, String destination, String startDate, String endDate, int adults,
			int kids, int[] ageKids) {
		super();
		this.idHotelRes = idHotelRes;
		this.destination = destination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adults = adults;
		this.kids = kids;
		this.ageKids = ageKids;
	}
	
	public DOHotelRes(List<String> data) {
		super();
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
	
	public String getIdHotelRes() {
		return idHotelRes;
	}
	public void setIdHotelRes(String idHotelRes) {
		this.idHotelRes = idHotelRes;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getKids() {
		return kids;
	}
	public void setKids(int kids) {
		this.kids = kids;
	}
	public int[] getAgeKids() {
		return ageKids;
	}
	public void setAgeKids(int[] ageKids) {
		this.ageKids = ageKids;
	}
	
	public void setDataUsingList(List<String> data) {
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

}
