package DAO;

import java.util.List;

import entities.Benificiaire;

public interface BenificiaireDAO {
	Benificiaire getById(int id);
    List<Benificiaire> getAll();
    void insert(Benificiaire beni);
    void update(Benificiaire beni);
    void delete(int id);
}
