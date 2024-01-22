package entities;

public class Transporteur {
	private int id;
	private String nom;
	private String type_de_transport;
	private String ville;
	private int nbr_transport;
	
	
	
	public Transporteur() {
		super();
	}
	public Transporteur(int id, String nom, String type_de_transport, String ville, int nbr_transport) {
		super();
		this.id = id;
		this.nom = nom;
		this.type_de_transport = type_de_transport;
		this.ville = ville;
		this.nbr_transport = nbr_transport;
	}
	public Transporteur(String nom, String type_de_transport, String ville, int nbr_transport) {
		super();
		this.nom = nom;
		this.type_de_transport = type_de_transport;
		this.ville = ville;
		this.nbr_transport = nbr_transport;
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
	 * @return the type_de_transport
	 */
	public String getType_de_transport() {
		return type_de_transport;
	}
	/**
	 * @param type_de_transport the type_de_transport to set
	 */
	public void setType_de_transport(String type_de_transport) {
		this.type_de_transport = type_de_transport;
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
	 * @return the nbr_transport
	 */
	public int getNbr_transport() {
		return nbr_transport;
	}
	/**
	 * @param nbr_transport the nbr_transport to set
	 */
	public void setNbr_transport(int nbr_transport) {
		this.nbr_transport = nbr_transport;
	}
}
