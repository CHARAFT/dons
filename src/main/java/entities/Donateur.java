package entities;

public class Donateur {
	private int id;
	private String nom;
	private String CIN;
	private String email;
	private String password;
 	private long num_tel;
 	
 	
 	public Donateur() {
		super();
	}
	public Donateur(int id, String nom, String cIN, String email, String password, long num_tel, String address) {
		super();
		this.id = id;
		this.nom = nom;
		CIN = cIN;
		this.email = email;
		this.password = password;
		this.num_tel = num_tel;
		this.address = address;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the cIN
	 */
	public String getCIN() {
		return CIN;
	}
	/**
	 * @param cIN the cIN to set
	 */
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the num_tel
	 */
	public long getNum_tel() {
		return num_tel;
	}
	/**
	 * @param num_tel the num_tel to set
	 */
	public void setNum_tel(long num_tel) {
		this.num_tel = num_tel;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	private String address;
 	
	
 	
 
}
