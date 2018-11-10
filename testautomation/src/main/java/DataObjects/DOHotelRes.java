package DataObjects;

public class DOHotelRes {
	private String hotelProductType;
	private String destination;
	private String startDate;
	private String endDate;
	private int adults;
	private int kids;
	private int[] ageKids;
	
	public DOHotelRes() {
		super();
		this.hotelProductType = "";
		this.destination = "";
		this.startDate = "";
		this.endDate = "";
		this.adults = 0;
		this.kids = 0;
		this.ageKids = new int[0];
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
	public DOHotelRes(String hotelProductType, String destination, String startDate, String endDate, int adults,
			int kids, int[] ageKids) {
		super();
		this.hotelProductType = hotelProductType;
		this.destination = destination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adults = adults;
		this.kids = kids;
		this.ageKids = ageKids;
	}
	
	public String getHotelProductType() {
		return hotelProductType;
	}
	public void setHotelProductType(String hotelProductType) {
		this.hotelProductType = hotelProductType;
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
	
	

}
