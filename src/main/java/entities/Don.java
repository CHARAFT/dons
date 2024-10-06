package entities;

import java.util.Date;

public class Don {
 private int id ;
 private int quantite;
 private String nature;
private String ville;
 private String address;
 private Date date_De_collecte;
private int trans_id ;
private int donateur_id ;
private int event_id ;
/**
 * @return the donateur_id
 */
public int getDonateur_id() {
	return donateur_id;
}
/**
 * @param donateur_id the donateur_id to set
 */
public void setDonateur_id(int donateur_id) {
	this.donateur_id = donateur_id;
}
/**
 * @return the event_id
 */
public int getEvent_id() {
	return event_id;
}
/**
 * @param event_id the event_id to set
 */
public void setEvent_id(int event_id) {
	this.event_id = event_id;
}
public Don(int quantite, String nature, String ville, String address, Date date_De_collecte, int trans_id,
		int donateur_id, int event_id, int statut) {
	super();
	this.quantite = quantite;
	this.nature = nature;
	this.ville = ville;
	this.address = address;
	this.date_De_collecte = date_De_collecte;
	this.trans_id = trans_id;
	this.donateur_id = donateur_id;
	this.event_id = event_id;
	this.statut = statut;
}
private int statut;


public Don(int id, int quantite, String nature, String ville, String address, Date date_De_collecte, int trans_id,
		int statut) {
	super();
	this.id = id;
	this.quantite = quantite;
	this.nature = nature;
	this.ville = ville;
	this.address = address;
	this.date_De_collecte = date_De_collecte;
	this.trans_id = trans_id;
	this.statut = statut;
}
/**
 * @return the statut
 */
public int getStatut() {
	return statut;
}
/**
 * @param statut the statut to set
 */
public void setStatut(int statut) {
	this.statut = statut;
}
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
 * @return the trans_id
 */
public int getTrans_id() {
	return trans_id;
}
/**
 * @param trans_id the trans_id to set
 */
public void setTrans_id(int trans_id) {
	this.trans_id = trans_id;
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
