package DataObjects;

public class DOClient {
	private String name;
	private String lastName;
	private String email;
	private int lada;
	private int phone;
	private int cellphone;
	private String bornDate;
	
	public DOClient() {
		super();
		this.name = "";
		this.lastName = "";
		this.email = "";
		this.lada = 0;
		this.phone = 0;
		this.cellphone = 0;
		this.bornDate = "";
	}
	
	public DOClient(String name, String lastName, String email, int lada, int phone, int cellphone, String bornDate) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.lada = lada;
		this.phone = phone;
		this.cellphone = cellphone;
		this.bornDate = bornDate;
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
	public int getCellphone() {
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
	
}
