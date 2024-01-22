package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Donateur;

public class DonnateurDAOImpl implements DonnateurDAO {
	private Connection connection;
	public DonnateurDAOImpl(Connection connection) {
        this.connection = connection;
    }
	@Override
	public Donateur getById(int id) {
		Donateur ben = null;
        String sql = "SELECT * FROM donateur WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ben= new Donateur();
                ben.setId(resultSet.getInt("id"));
                ben.setNom(resultSet.getString("nom"));
                ben.setCIN(resultSet.getString("CIN"));
                ben.setPassword(resultSet.getString("password"));
                ben.setEmail(resultSet.getString("email"));
                ben.setNum_tel(resultSet.getLong("num_tel"));
                ben.setAddress(resultSet.getString("address"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ben;
	}

	@Override
	public List<Donateur> getAll() {
		List<Donateur> bens = new ArrayList<>();
        String sql = "SELECT * FROM donateur";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Donateur ben = new Donateur();
                ben.setId(resultSet.getInt("id"));
                ben.setNom(resultSet.getString("nom"));
                ben.setCIN(resultSet.getString("CIN"));
                ben.setPassword(resultSet.getString("password"));
                ben.setEmail(resultSet.getString("email"));
                ben.setNum_tel(resultSet.getLong("num_tel"));
                ben.setAddress(resultSet.getString("address"));


                bens.add(ben);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bens;
	}

	@Override
	public void insert(Donateur donateur) {
		String sql = "INSERT INTO donateur (nom,CIN,email,password,num_tel,address) VALUES (?, ?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, donateur.getNom());
            statement.setString(2, donateur.getCIN());
            statement.setString(3, donateur.getEmail());
            statement.setString(4, donateur.getPassword());
            statement.setLong(5, donateur.getNum_tel());
            statement.setString(6, donateur.getAddress());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void update(Donateur donateur) {
		String sql = "UPDATE donateur SET nom = ?, CIN = ?, email=?, password=? , num_tel =? , address = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, donateur.getNom());
            statement.setString(2, donateur.getCIN());
            statement.setString(3, donateur.getEmail());
            statement.setString(4, donateur.getPassword());
            statement.setLong(5, donateur.getNum_tel());
            statement.setString(6, donateur.getAddress());

            statement.setInt(7, donateur.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM donateur WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

}
