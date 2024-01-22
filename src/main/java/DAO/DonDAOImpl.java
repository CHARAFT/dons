package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import entities.Don;

public class DonDAOImpl implements DonDAO{
	private Connection connection;
	
	public DonDAOImpl(Connection connection) {
        this.connection = connection;
    }

	@Override
	public Don getById(int id) {
		Don don = null;
        String sql = "SELECT * FROM don WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	don= new Don();
            	don.setId(resultSet.getInt("id"));
            	don.setQuantite(resultSet.getInt("quantite"));
            	don.setNature(resultSet.getString("nature"));
            	don.setVille(resultSet.getString("ville"));
            	don.setAddress(resultSet.getString("address"));
            	don.setDate_De_collecte(resultSet.getDate("date_de_collecte"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return don;
	}

	@Override
	public List<Don> getAll() {
		List<Don> dons = new ArrayList<>();
        String sql = "SELECT * FROM don";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Don don = new Don();
                don.setId(resultSet.getInt("id"));
                don.setQuantite(resultSet.getInt("quantite"));
            	don.setNature(resultSet.getString("nature"));
            	don.setVille(resultSet.getString("ville"));
            	don.setAddress(resultSet.getString("address"));
            	don.setDate_De_collecte(resultSet.getDate("date_de_collecte"));

                dons.add(don);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dons;
	}

	@Override
	public void insert(Don don) {
		String sql = "INSERT INTO don (quantite,nature,ville,address,date_de_collecte) VALUES (?, ?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, don.getQuantite());
            statement.setString(2, don.getNature());
            statement.setString(3, don.getVille());
            statement.setString(4, don.getAddress());
            statement.setDate(5, (Date) don.getDate_De_collecte());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
	public void update(Don don) {
		String sql = "UPDATE don SET quantite=? ,nature=? ,ville=?,address=?,date_de_collecte=? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, don.getQuantite());
            statement.setString(2, don.getNature());
            statement.setString(3, don.getVille());
            statement.setString(4, don.getAddress());
            statement.setDate(5, (Date) don.getDate_De_collecte());
            statement.setInt(6, don.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM don WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

}
