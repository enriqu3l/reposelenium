package valueobjects;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class VOClient {
	private String idClient;
	private String name;
	private String lastName;
	private String gender;
	private String email;
	private int lada;
	private int phone;
	private long cellphone;
	private LocalDate bornDate;
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
		this.bornDate = LocalDate.parse("2000-01-01");
		this.nationalId = 0;
	}
	
	public VOClient(String idClient, String name, String lastName, String gender, String email, int lada, int phone, long cellphone, String bornDate, int nationalId) {
		super();
		this.idClient = idClient.trim();
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.gender = gender.trim();
		this.email = email.trim();
		this.lada = lada;
		this.phone = phone;
		this.cellphone = cellphone;
		this.bornDate = LocalDate.parse(bornDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
		this.nationalId = nationalId;
	}
	
	public VOClient(String idClient, String name, String lastName, String gender, String email, int lada, int phone, long cellphone, LocalDate bornDate, int nationalId) {
		super();
		this.idClient = idClient.trim();
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.gender = gender.trim();
		this.email = email.trim();
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
		this.gender = data.get(3).trim();
		this.email = data.get(4).trim();
		this.lada = Integer.parseInt(data.get(5).trim());
		this.phone = Integer.parseInt(data.get(6).trim());
		this.cellphone = Long.parseLong(data.get(7).trim());
		String bornDate = data.get(8).trim();
		this.bornDate = LocalDate.parse(bornDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
		this.nationalId = Integer.parseInt(data.get(9).trim());
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public LocalDate getBornDateLocalDateFormat() {
		return bornDate;
	}
	
	/**
	 * Regresa la fecha en formato dd/MM/yyyy
	 * @return String.
	 */
	public String getBornDate() {
		return bornDate.toString("dd/MM/yyyy");
	}
	
	/**
	 * El formato puede ser dd/MM/yyyy, MM/dd/yyyy, yyyy/MM/dd, ...
	 * @param format. String
	 * @return String. Fecha en formato indicado
	 */
	public String getBornDate(String format) {
		//Se supone que toString manda a llamar a un: DateTimeFormat.forPattern(format);
		return bornDate.toString(format);
	}
	
	/**
	 * El formato puede ser cualquiera aceptado por jodatime
	 * @param format. DateTimeFormatter
	 * @return String. Fecha en formato indicado
	 */
	public String getBornDate(DateTimeFormatter format) {
		return bornDate.toString(format);
	}
	
	public void setBornDate(LocalDate bornDate) {
		this.bornDate = bornDate;
	}
	
	public void setBornDate(String bornDate) {
		this.bornDate = LocalDate.parse(bornDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
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
		this.gender = data.get(3).trim();
		this.email = data.get(4).trim();
		this.lada = Integer.parseInt(data.get(5).trim());
		this.phone = Integer.parseInt(data.get(6).trim());
		this.cellphone = Long.parseLong(data.get(7).trim());
		String bornDate = data.get(8).trim();
		this.bornDate = LocalDate.parse(bornDate.trim(),DateTimeFormat.forPattern("dd/MM/yyyy"));
		this.nationalId = Integer.parseInt(data.get(9).trim());
	}
}
