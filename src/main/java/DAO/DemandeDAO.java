package DAO;

import java.util.List;

import entities.Demande;

public interface DemandeDAO {
	Demande getById(int id);
    List<Demande> getAll();
    void insert(Demande demande);
    void update(Demande demande);
    void delete(int id);
}
