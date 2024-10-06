package DAO;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Demande;

public class DemandeDAOImpl implements DemandeDAO{
private Connection connection;
	
	public DemandeDAOImpl(Connection connection) {
        this.connection = connection;
    }
	@Override
	public Demande getById(int id) {
		Demande dem = null;
        String sql = "SELECT * FROM demande WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	dem= new Demande();
            	dem.setId(resultSet.getInt("id"));
            	dem.setObjet(resultSet.getString("objet"));
            	dem.setDesc(resultSet.getString("description"));
            	dem.setNature(resultSet.getString("nature"));
            	dem.setQuantite(resultSet.getInt("quantite"));
            	dem.setDate(resultSet.getDate("date"));
            	dem.setStatus(resultSet.getInt("status"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dem;
	}

	@Override
	public List<Demande> getAll() {
		List<Demande> dems = new ArrayList<>();
        String sql = "SELECT * FROM demande";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Demande dem = new Demande();
                dem.setId(resultSet.getInt("id"));
                dem.setObjet(resultSet.getString("objet"));
            	dem.setDesc(resultSet.getString("description"));
            	dem.setNature(resultSet.getString("nature"));
            	dem.setQuantite(resultSet.getInt("quantite"));
            	dem.setDate(resultSet.getDate("date"));
            	dem.setStatus(resultSet.getInt("status"));

                dems.add(dem);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dems;
	}

	@Override
	public void insert(Demande demande) {
		String sql = "INSERT INTO demande (objet, description , nature, quantite, date) VALUES (?, ?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(4, demande.getNature());
            statement.setString(1, demande.getObjet());
            statement.setString(2, demande.getDesc());
            statement.setInt(3, demande.getQuantite());
            statement.setDate(5, (Date) demande.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
	public void update(int status,int id) {
		String sql = "UPDATE demande SET status=? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
       
            statement.setInt(1, status);
            statement.setInt(2, id);

            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM demande WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

}
