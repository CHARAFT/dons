package entities;

import java.util.Date;

public class Event {
	private int id;
	private String nom;
	private Date debut;
	private Date fin;
	private String objectif;
	private String lieu;
	 private String image;
	
	
	
	public Event(String nom, Date debut, Date fin, String objectif, String lieu,String image) {
		super();
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.objectif = objectif;
		this.lieu = lieu;
		this.image = image;
	}
	public Event() {
		super();
	}
	public Event(int id, String nom, Date debut, Date fin, String objectif, String lieu, String image) {
		super();
		this.id = id;
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.objectif = objectif;
		this.lieu = lieu;
		this.image = image;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * @return the debut
	 */
	public Date getDebut() {
		return debut;
	}
	/**
	 * @param debut the debut to set
	 */
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}
	/**
	 * @return the objectif
	 */
	public String getObjectif() {
		return objectif;
	}
	/**
	 * @param objectif the objectif to set
	 */
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}
	/**
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}
	/**
	 * @param lieu the lieu to set
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
}
