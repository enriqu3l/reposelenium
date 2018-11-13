package DataObjects;
import java.util.List;

import FrameworkConfig.GeneralConfig.CardTypes;

public class DOCreditCard {
	
	private long cardNumber;
	private String holderName;
	private String month;
	private String year;
	private int ccv;
	private String country;
	private int cp;
	private String bank;
	private CardTypes type;

	public DOCreditCard() {
		super();
		this.cardNumber = 0;
		this.holderName = "";
		this.month = "";
		this.year = "";
		this.ccv = 0;
		this.country = "";
		this.cp = 0;
		this.bank = "";
		this.type = null;
	}
	
	public DOCreditCard(long cardNumber, String holderName, String month, String year, int ccv, String country, int cp,String bank, CardTypes type) {
		super();
		this.cardNumber = cardNumber;
		this.holderName = holderName;
		this.month = month;
		this.year = year;
		this.ccv = ccv;
		this.country = country;
		this.cp = cp;
		this.bank = bank;
		this.type = type;
	}
	
	public DOCreditCard(List<String> data) {
		super();
		this.cardNumber = Long.parseLong(data.get(0));
		this.holderName = data.get(1);
		this.month = data.get(2);
		this.year = data.get(3);
		this.ccv = Integer.parseInt(data.get(4));
		this.country = data.get(5);
		this.cp = Integer.parseInt(data.get(6));
		this.type = CardTypes.valueOf(data.get(7));
		this.bank = data.get(8);
	}
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setNumber(long number) {
		this.cardNumber = number;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
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
	public CardTypes getPayNetwork() {
		return type;
	}
	public void setPayNetwork(CardTypes type) {
		this.type = type;
	}

	public void setDataUsingList(List<String> data) {
		this.cardNumber = Long.parseLong(data.get(0));
		this.holderName = data.get(1);
		this.month = data.get(2);
		this.year = data.get(3);
		this.ccv = Integer.parseInt(data.get(4));
		this.country = data.get(5);
		this.cp = Integer.parseInt(data.get(6));
		this.type = CardTypes.valueOf(data.get(7));
		this.bank = data.get(8);
	}
}
