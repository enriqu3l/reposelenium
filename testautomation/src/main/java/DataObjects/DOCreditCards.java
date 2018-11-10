package DataObjects;
import FrameworkConfig.GeneralConfig.payNetworks;

public class DOCreditCards {
	
	private long number;
	private String holderName;
	private int month;
	private int year;
	private int ccv;
	private String country;
	private int cp;
	private String bank;
	private payNetworks payNetwork;

	public DOCreditCards() {
		super();
		this.number = 0;
		this.holderName = "";
		this.month = 0;
		this.year = 0;
		this.ccv = 0;
		this.country = "";
		this.cp = 0;
		this.bank = "";
		this.payNetwork = null;
	}
	
	public DOCreditCards(long number, String holderName, int month, int year, int ccv, String country, int cp,String bank, payNetworks p) {
		super();
		this.number = number;
		this.holderName = holderName;
		this.month = month;
		this.year = year;
		this.ccv = ccv;
		this.country = country;
		this.cp = cp;
		this.bank = bank;
		this.payNetwork = p;
	}
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCCV() {
		return ccv;
	}
	public void setCCV(int ccv) {
		this.ccv = ccv;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getCP() {
		return cp;
	}
	public void setCP(int cp) {
		this.cp = cp;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public payNetworks getPayNetwork() {
		return payNetwork;
	}
	public void setPayNetwork(payNetworks p) {
		this.payNetwork = p;
	}
}
