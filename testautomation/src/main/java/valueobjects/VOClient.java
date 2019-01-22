package valueobjects;

import java.util.List;

public class VOClient {
	private String idClient;
	private String name;
	private String lastName;
	private String email;
	private int lada;
	private int phone;
	private long cellphone;
	private String bornDate;
	private int nationalId;
	
	public VOClient() {
		super();
		this.idClient = "";
		this.name = "";
		this.lastName = "";
		this.email = "";
		this.lada = 0;
		this.phone = 0;
		this.cellphone = 0;
		this.bornDate = "";
		this.nationalId = 0;
	}
	
	public VOClient(String idClient, String name, String lastName, String email, int lada, int phone, long cellphone, String bornDate, int nationalId) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.lada = lada;
		this.phone = phone;
		this.cellphone = cellphone;
		this.bornDate = bornDate;
		this.nationalId = nationalId;
	}
	
	public VOClient(List<String> data) {
		this.idClient = data.get(0).trim();
		this.name = data.get(1).trim();
		this.lastName = data.get(2).trim();
		this.email = data.get(3).trim();
		this.lada = Integer.parseInt(data.get(4).trim());
		this.phone = Integer.parseInt(data.get(5).trim());
		this.cellphone = Long.parseLong(data.get(6).trim());
		this.bornDate = data.get(7).trim();
		this.nationalId = Integer.parseInt(data.get(8).trim());
	}
	
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLada() {
		return lada;
	}
	public void setLada(int lada) {
		this.lada = lada;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public long getCellphone() {
		return cellphone;
	}
	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public int getNationalId() {
		return nationalId;
	}
	public void setNationalId(int nationalId) {
		this.nationalId = nationalId;
	}
	
	public void setDataUsingList(List<String> data) {
		this.idClient = data.get(0).trim();
		this.name = data.get(1).trim();
		this.lastName = data.get(2).trim();
		this.email = data.get(3).trim();
		this.lada = Integer.parseInt(data.get(4).trim());
		this.phone = Integer.parseInt(data.get(5).trim());
		this.cellphone = Long.parseLong(data.get(6).trim());
		this.bornDate = data.get(7).trim();
		this.nationalId = Integer.parseInt(data.get(8).trim());
	}
	
}
