import java.util.Properties;

	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
public class testemail {

	    public static void main(String[] args) {
	    	
	    	 //provide recipient's email ID
	        String to = "jakartato@example.com";
	        //provide sender's email ID
	        String from = "jakartafrom@example.com";
	        //provide Mailtrap's username
	        final String username = "6b75e65746d9bf";
	        //provide Mailtrap's password
	        final String password = "********cee6";
	        //provide Mailtrap's host address
	        String host = "sandbox.smtp.mailtrap.io";
	        //configure Mailtrap's SMTP server details
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "2525");
	        // Set your email credentials
//	        String username = "tatataii2002@gmail.com";
//	        String password = "litcxxfidzhajook";
//
//	        // Set the recipient email address
//	        String to = "tatataii2002@gmail.com";
//
//	        // Set the email properties
//	        Properties props = new Properties();
//	        props.put("mail.smtp.auth", "true");
//	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.smtp.host", "smtp.gmail.com");
//	        props.put("mail.smtp.port", "587");

	        // Create a Session with the email credentials
	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	                        return new javax.mail.PasswordAuthentication(username, password);
	                    }
	                });

	        try {
	            // Create a MimeMessage
	            Message message = new MimeMessage(session);
	            // Set the sender email address
	            message.setFrom(new InternetAddress(username));
	            // Set the recipient email address
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            // Set the email subject
	            message.setSubject("Testing JavaMail");
	            // Set the email body
	            message.setText("This is a test email from JavaMail.");

	            // Send the email
	            Transport.send(message);

	            System.out.println("Email sent successfully.");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	

}
