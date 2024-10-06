package entities;

import java.util.Date;

public class Demande {
	private int id;
	private String objet;
	private String desc;
	private int quantite;
	private String nature;
	private int status;
	
	public Demande(String objet, String desc, int quantite, String nature, Date date) {
		super();
		this.objet = objet;
		this.desc = desc;
		this.quantite = quantite;
		this.nature = nature;
		this.date = date;
	}
	private Date date;
	
	
	
	public Demande() {
		super();
	}
	public Demande(int id, String objet, String desc, int quantite, String nature, Date date) {
		super();
		this.id = id;
		this.objet = objet;
		this.desc = desc;
		this.quantite = quantite;
		this.nature = nature;
		this.date = date;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}
	/**
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}
	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}
	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
  
}
