package DAO;

import java.util.List;

import entities.Event;

public interface EventDAO {
	Event getById(int id);
    List<Event> getAll();
    void insert(Event don);
    void update(Event don);
    void delete(int id);
}
