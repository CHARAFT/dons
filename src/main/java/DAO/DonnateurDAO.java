package DAO;

import java.util.List;

import entities.Donateur;

public interface DonnateurDAO {
	Donateur getById(int id);
    List<Donateur> getAll();
    void insert(Donateur donateur);
    void update(Donateur donateur);
    void delete(int id);
}
