package DAO;

import java.util.List;

import entities.Don;

public interface DonDAO {
	Don getById(int id);
    List<Don> getAll();
    void insert(Don don);
    void update(Don don);
    void delete(int id);
}
