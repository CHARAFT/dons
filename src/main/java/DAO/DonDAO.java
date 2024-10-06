package DAO;

import java.util.List;

import entities.Don;
import entities.Collecte;

public interface DonDAO {
	Don getById(int id);
    List<Don> getAll();
    void insert(Don don);
    void update(Don don);
    void delete(int id);
    List<Don> getDonByIdTransporteur(int transporteurId);
    void updateDonStatus(int donId, int newStatus);
    void insert(Collecte cl);
}
