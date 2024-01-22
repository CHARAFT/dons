package DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.Benificiaire;


public class BenificiaireDAOImpl implements BenificiaireDAO{
	private Connection connection;
	
	public BenificiaireDAOImpl(Connection connection) {
        this.connection = connection;
    }
	@Override
	public Benificiaire getById(int id) {
		Benificiaire ben = null;
        String sql = "SELECT * FROM benificiaire WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ben= new Benificiaire();
                ben.setId(resultSet.getInt("id"));
                ben.setNom(resultSet.getString("nom"));
                ben.setCIN(resultSet.getString("CIN"));
                ben.setPassword(resultSet.getString("password"));
                ben.setEmail(resultSet.getString("email"));
                ben.setNum_tel(resultSet.getLong("num_tel"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ben;
	}

	@Override
	public List<Benificiaire> getAll() {
		List<Benificiaire> bens = new ArrayList<>();
        String sql = "SELECT * FROM benificiaire";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Benificiaire ben = new Benificiaire();
                ben.setId(resultSet.getInt("id"));
                ben.setNom(resultSet.getString("nom"));
                ben.setCIN(resultSet.getString("CIN"));
                ben.setPassword(resultSet.getString("password"));
                ben.setEmail(resultSet.getString("email"));
                ben.setNum_tel(resultSet.getLong("num_tel"));


                bens.add(ben);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bens;
	}

	@Override
	public void insert(Benificiaire beni) {
		String sql = "INSERT INTO benificiaire (nom,CIN,email,password,num_tel) VALUES (?, ?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, beni.getNom());
            statement.setString(2, beni.getCIN());
            statement.setString(3, beni.getEmail());
            statement.setString(4, beni.getPassword());
            statement.setLong(5, beni.getNum_tel());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void update(Benificiaire beni) {
		String sql = "UPDATE benificiaire SET nom = ?, CIN = ?, email=?, password=? , num_tel =? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, beni.getNom());
            statement.setString(2, beni.getCIN());
            statement.setString(3, beni.getEmail());
            statement.setString(4, beni.getPassword());
            statement.setLong(5, beni.getNum_tel());
            statement.setInt(6, beni.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM benificiaire WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
