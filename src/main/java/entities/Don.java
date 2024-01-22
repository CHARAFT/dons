package entities;

import java.util.Date;

public class Don {
 private int id ;
 private int quantite;
 private String nature;
 private String ville;
 private String address;
 private Date date_De_collecte;

public Don() {
	super();
}
public Don(int id, int quantite, String nature, String ville, String address, Date date_De_collecte) {
	super();
	this.id = id;
	this.quantite = quantite;
	this.nature = nature;
	this.ville = ville;
	this.address = address;
	this.date_De_collecte = date_De_collecte;
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
 * @return the ville
 */
public String getVille() {
	return ville;
}
/**
 * @param ville the ville to set
 */
public void setVille(String ville) {
	this.ville = ville;
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
/**
 * @return the date_De_collecte
 */
public Date getDate_De_collecte() {
	return date_De_collecte;
}
/**
 * @param date_De_collecte the date_De_collecte to set
 */
public void setDate_De_collecte(Date date_De_collecte) {
	this.date_De_collecte = date_De_collecte;
}
 
 
}
