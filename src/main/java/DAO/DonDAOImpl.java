package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import entities.Collecte;
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
            	don.setStatut(resultSet.getInt("statut"));
            	don.setTrans_id(resultSet.getInt("transp_id"));
            	don.setDonateur_id(resultSet.getInt("donateur_id"));
            	don.setEvent_id(resultSet.getInt("event_id"));


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
            	don.setStatut(resultSet.getInt("statut"));
            	don.setTrans_id(resultSet.getInt("transp_id"));
            	don.setDonateur_id(resultSet.getInt("donateur_id"));
            	don.setEvent_id(resultSet.getInt("event_id"));
                dons.add(don);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dons;
	}

	@Override
	public void insert(Don don) {
		String sql = "INSERT INTO don (quantite,nature,ville,address,date_de_collecte, transp_id,donateur_id,event_id) VALUES (?, ?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, don.getQuantite());
            statement.setString(2, don.getNature());
            statement.setString(3, don.getVille());
            statement.setString(4, don.getAddress());
            statement.setDate(5, (Date) don.getDate_De_collecte());
            statement.setInt(6, don.getTrans_id());
            statement.setInt(7, don.getDonateur_id());
            statement.setInt(8, don.getEvent_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
	public void update(Don don) {
		String sql = "UPDATE don SET quantite=? ,nature=? ,ville=?,address=?,date_de_collecte=? ,trans_id=?,donateur_id=?,event_id=? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, don.getQuantite());
            statement.setString(2, don.getNature());
            statement.setString(3, don.getVille());
            statement.setString(4, don.getAddress());
            statement.setDate(5, (Date) don.getDate_De_collecte());
            statement.setInt(6, don.getTrans_id());
            statement.setInt(7, don.getDonateur_id());
            statement.setInt(8, don.getEvent_id());

            statement.setInt(9, don.getId());
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
	@Override
	public List<Don> getDonByIdTransporteur(int transporteurId) {
        List<Don> dons = new ArrayList<>();
        Don don = null;
        String sql = "SELECT * FROM don WHERE transp_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transporteurId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	don= new Don();
            	don.setId(resultSet.getInt("id"));
            	don.setQuantite(resultSet.getInt("quantite"));
            	don.setNature(resultSet.getString("nature"));
            	don.setVille(resultSet.getString("ville"));
            	don.setAddress(resultSet.getString("address"));
            	don.setDate_De_collecte(resultSet.getDate("date_de_collecte"));
            	don.setStatut(resultSet.getInt("statut"));
            	don.setTrans_id(resultSet.getInt("transp_id"));
            	don.setDonateur_id(resultSet.getInt("donateur_id"));
            	don.setEvent_id(resultSet.getInt("event_id"));
            	dons.add(don);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dons;
        }
	 @Override
	    public void updateDonStatus(int donId, int newStatus) {
	        PreparedStatement preparedStatement = null;

	        try {
	            // Obtenez une connexion à la base de données (vous devrez remplacer les détails de la connexion)
	            // Requête SQL pour mettre à jour le statut du don
	            String sql = "UPDATE don SET statut = ? WHERE id = ?";
	            preparedStatement = connection.prepareStatement(sql);

	            // Définir les paramètres de la requête
	            preparedStatement.setInt(1, newStatus);
	            preparedStatement.setInt(2, donId);

	            // Exécutez la mise à jour
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Gérez les exceptions selon votre logique
	        } }

	@Override
	public void insert(Collecte cl) {
		String sql = "INSERT INTO collecte (date, trans_id,id_don) VALUES (?, ?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cl.getDate());
            statement.setInt(2, cl.getTransp_id());
            statement.setInt(3, cl.getDon_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }			
	}
}
