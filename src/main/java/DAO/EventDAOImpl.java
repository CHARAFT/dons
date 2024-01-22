package DAO;

import java.sql.Connection;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Event;

public class EventDAOImpl implements EventDAO{
	private Connection connection;
	
	public EventDAOImpl(Connection connection) {
        this.connection = connection;
    }
	@Override
	public Event getById(int id) {
		Event event = null;
        String sql = "SELECT * FROM event WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	event= new Event();
            	event.setId(resultSet.getInt("id"));
            	event.setNom(resultSet.getString("nom"));
            	event.setDebut(resultSet.getDate("debut"));
            	event.setFin(resultSet.getDate("fin"));
            	event.setObjectif(resultSet.getString("objectif"));
            	event.setLieu(resultSet.getString("lieu"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return event;
	}

	@Override
	public List<Event> getAll() {
		List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM event";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
            	event.setNom(resultSet.getString("nom"));
            	event.setDebut(resultSet.getDate("debut"));
            	event.setFin(resultSet.getDate("fin"));
            	event.setObjectif(resultSet.getString("objectif"));
            	event.setLieu(resultSet.getString("lieu"));
            	event.setImage(resultSet.getString("image"));


                events.add(event);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
	}

	@Override
	public void insert(Event event) {
		String sql = "INSERT INTO event (nom,debut,fin,objectif,lieu,image) VALUES (?, ?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getNom());
            java.sql.Date debutSqlDate = new java.sql.Date(event.getDebut().getTime());
            statement.setDate(2, debutSqlDate);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date finSqlDate = new java.sql.Date(event.getFin().getTime());
            statement.setDate(3, finSqlDate);
            statement.setString(4, event.getObjectif());
            statement.setString(5, event.getLieu());
            statement.setString(6, event.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
	public void update(Event event) {
		String sql = "UPDATE event SET nom = ?, debut=? , fin=? , objectif=? , lieu=? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	 statement.setString(1, event.getNom());
        	 java.sql.Date debutSqlDate = new java.sql.Date(event.getDebut().getTime());
             statement.setDate(2, debutSqlDate);

             // Convert java.util.Date to java.sql.Date
             java.sql.Date finSqlDate = new java.sql.Date(event.getFin().getTime());
             statement.setDate(3, finSqlDate);
             statement.setString(4, event.getObjectif());
             statement.setString(5, event.getLieu());
            statement.setInt(6, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM event WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
