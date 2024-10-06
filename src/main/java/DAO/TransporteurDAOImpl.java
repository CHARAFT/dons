
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Benificiaire;
import entities.Transporteur;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
public class TransporteurDAOImpl implements TransporteurDAO{
	private Connection connection;
	
	public TransporteurDAOImpl(Connection connection) {
        this.connection = connection;
    }
	@Override
	public Transporteur getById(int id) {
		Transporteur trans = null;
        String sql = "SELECT * FROM transporteur WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	trans= new Transporteur();
            	trans.setId(resultSet.getInt("id"));
            	trans.setNom(resultSet.getString("nom"));
            	trans.setType_de_transport(resultSet.getString("type_de_transport"));
            	trans.setVille(resultSet.getString("ville"));
            	trans.setNbr_transport(resultSet.getInt("eventId"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trans;
	}

	@Override
	public List<Transporteur> getAll() {
		List<Transporteur> transps = new ArrayList<>();
        String sql = "SELECT * FROM transporteur";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Transporteur trans = new Transporteur();
                trans.setId(resultSet.getInt("id"));
                trans.setNom(resultSet.getString("nom"));
                trans.setType_de_transport(resultSet.getString("type_de_transport"));
            	trans.setVille(resultSet.getString("ville"));
            	trans.setNbr_transport(resultSet.getInt("eventId"));


                transps.add(trans);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transps;
	}

	@Override
	public void insert(Transporteur transporteur) {
		String sql = "INSERT INTO transporteur (nom,type_de_transport,ville,eventId) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transporteur.getNom());
            statement.setString(2, transporteur.getType_de_transport());
            statement.setString(3, transporteur.getVille());
            statement.setInt(4, transporteur.getNbr_transport());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}
	public void update(Transporteur transporteur) {
		String sql = "UPDATE transporteur SET nom = ?, type_de_transport = ?, ville=?, eventId=?  WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transporteur.getNom());
            statement.setString(2, transporteur.getType_de_transport());
            statement.setString(3, transporteur.getVille());
            statement.setInt(4, transporteur.getNbr_transport());
            statement.setInt(5, transporteur.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM transporteur WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	public List<Transporteur> getTransporteursByEvent(int eventId) {
        List<Transporteur> transporteurs = new ArrayList<>();

        try (
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM transporteur WHERE eventId = ?")) {

            statement.setInt(1, eventId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int transporteurId = resultSet.getInt("id");
                   
                    Transporteur transporteur = getById(transporteurId);
                    if (transporteur != null) {
                        transporteurs.add(transporteur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transporteurs;
    }
	public void sendEmail(String to , String passwd) {
	    // Remplacez ces valeurs par les vôtres
	    String username = "tatataii2002@gmail.com";
	    String password = "litcxxfidzhajook";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        message.setSubject("Invitation au service de transport");
	        message.setText("Bonjour,\n\nVous êtes invité à rejoindre notre service de transport. Votre mot de passe est "+ passwd);

	        Transport.send(message);

	        System.out.println("E-mail envoyé avec succès.");
	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
}
