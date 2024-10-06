package entities;

public class Collecte {
private int id;
private String date;
private int don_id;
private int transp_id;

public Collecte(String date, int don_id, int transp_id) {
	super();
	this.date = date;
	this.don_id = don_id;
	this.transp_id = transp_id;
}
public Collecte() {
	super();
}
public Collecte(int id, String date, int don_id, int transp_id) {
	super();
	this.id = id;
	this.date = date;
	this.don_id = don_id;
	this.transp_id = transp_id;
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
 * @return the date
 */
public String getDate() {
	return date;
}
/**
 * @param date the date to set
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @return the don_id
 */
public int getDon_id() {
	return don_id;
}
/**
 * @param don_id the don_id to set
 */
public void setDon_id(int don_id) {
	this.don_id = don_id;
}
/**
 * @return the transp_id
 */
public int getTransp_id() {
	return transp_id;
}
/**
 * @param transp_id the transp_id to set
 */
public void setTransp_id(int transp_id) {
	this.transp_id = transp_id;
}

}
