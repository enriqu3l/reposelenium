package valueobjects;
import java.util.List;

import config.FWConfig.CardTypes;

public class VOCreditCard {
	private String idCreditCard;
	private long cardNumber;
	private String holderName;
	private String month;
	private String year;
	private int cvv;
	private String country;
	private int cp;
	private CardTypes type;
	private String bank;
	private int nationalId;

	public VOCreditCard() {
		super();
		this.idCreditCard = "";
		this.cardNumber = 0;
		this.holderName = "";
		this.month = "";
		this.year = "";
		this.cvv = 0;
		this.country = "";
		this.cp = 0;
		this.type = null;
		this.bank = "";
		this.nationalId = 0;
	}
	
	public VOCreditCard(String idCreditCard, long cardNumber, String holderName, String month, String year, int ccv, String country, int cp, CardTypes type, String bank, int nationalId) {
		super();
		this.idCreditCard = idCreditCard;
		this.cardNumber = cardNumber;
		this.holderName = holderName;
		this.month = month;
		this.year = year;
		this.cvv = ccv;
		this.country = country;
		this.cp = cp;
		this.type = type;
		this.bank = bank;
		this.nationalId = nationalId;
	}
	
	public VOCreditCard(List<String> data) {
		super();
		this.idCreditCard = data.get(0).trim();
		this.cardNumber = Long.parseLong(data.get(1).trim());
		this.holderName = data.get(2).trim();
		this.month = data.get(3).trim();
		this.year = data.get(4).trim();
		this.cvv = Integer.parseInt(data.get(5).trim());
		this.country = data.get(6).trim();
		this.cp = Integer.parseInt(data.get(7).trim());
		this.type = CardTypes.valueOf(data.get(8).trim());
		this.bank = data.get(9).trim();
		this.nationalId = Integer.parseInt(data.get(10).trim());
	}
	
	public String getIdCreditCard() {
		return idCreditCard;
	}
	public void setIdCreditCard(String idCreditCard) {
		this.idCreditCard = idCreditCard;
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
		return cvv;
	}
	public void setCCV(int ccv) {
		this.cvv = ccv;
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
	public int getNationalId() {
		return nationalId;
	}
	public void setNationalId(int nationalId) {
		this.nationalId = nationalId;
	}

	public void setDataUsingList(List<String> data) {
		this.idCreditCard = data.get(0).trim();
		this.cardNumber = Long.parseLong(data.get(1).trim());
		this.holderName = data.get(2).trim();
		this.month = data.get(3).trim();
		this.year = data.get(4).trim();
		this.cvv = Integer.parseInt(data.get(5).trim());
		this.country = data.get(6).trim();
		this.cp = Integer.parseInt(data.get(7).trim());
		this.type = CardTypes.valueOf(data.get(8).trim());
		this.bank = data.get(9).trim();
		this.nationalId = Integer.parseInt(data.get(10).trim());
	}
}
