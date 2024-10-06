package DAO;
import java.util.List;

import entities.Transporteur;
public interface TransporteurDAO {
	Transporteur getById(int id);
    List<Transporteur> getAll();
    void insert(Transporteur transporteur);
    void delete(int id);
	List<Transporteur> getTransporteursByEvent(int eventId);
	void sendEmail(String to , String psswd) ;
}
