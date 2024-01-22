package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import DAO.TransporteurDAO;
import DAO.TransporteurDAOImpl;
import entities.Event;
import entities.Transporteur;

/**
 * Servlet implementation class TransporteursServlet
 */
public class TransporteursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransporteursServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
        // Récupérez l'ID de l'événement depuis les paramètres de la requête
        //int eventId = Integer.parseInt(request.getParameter("id"));
        //int eventId = 3;
        // Récupérez les détails de l'événement depuis la base de données
        try {
			conn = Getconnexion.getConnection();
			EventDAO eventDAO = new EventDAOImpl(conn);
			String action = request.getParameter("action");

//	        Event event = eventDAO.getById(eventId);
//
//	        // Récupérez la liste des transporteurs associés à cet événement
	        TransporteurDAO transporteurDAO = new TransporteurDAOImpl(conn);

	        if (action == null || action.equals("listtr")) {
			List<Event> events = eventDAO.getAll();

	        // Créez une Map pour stocker les transporteurs par événement
	        Map<Event, List<Transporteur>> transporteursParEvent = new HashMap<>();

	        // Pour chaque événement, récupérez les transporteurs associés
	        for (Event event : events) {
	            int eventid = event.getId();
	            List<Transporteur> transporteurs = transporteurDAO.getTransporteursByEvent(eventid);
	            transporteursParEvent.put(event, transporteurs);
	        }
	        //	        System.out.println("Transporteurs par événement dans la session : " + transporteursParEvent);


	        // Placez la Map dans la portée de la requête
	        request.getSession().setAttribute("transporteursParEvent", transporteursParEvent);
            response.sendRedirect("admin/base.jsp?action=listtr");
	        response.getWriter().append("Served at: ").append(request.getContextPath());
	        }else if (action.equals("delete")) {
	        	System.out.println(action);
	            // Perform delete action based on transporter ID
	            int transporterId = Integer.parseInt(request.getParameter("id"));
	            // Perform the delete operation using your DAO or service class
	            transporteurDAO.delete(transporterId);
	            
	            // Redirect or forward to the appropriate page
	            response.sendRedirect("TransporteursServlet?action=listtr");
		        response.getWriter().append("Served at: ").append(request.getContextPath());
	        }
//	        }else if (action.equals("invit")) {
//	        	 int eventID = Integer.parseInt(request.getParameter("id"));
//	        	 request.setAttribute("eventID", eventID);
//	            request.getRequestDispatcher("inviter-transporteur.jsp?").forward(request, response);
//	        }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

	    try {
	        conn = Getconnexion.getConnection();
	        TransporteurDAO transporteurDAO = new TransporteurDAOImpl(conn);

	        String action = request.getParameter("action");
	        int  eventID = Integer.parseInt(request.getParameter("id"));

	        if (action != null && action.equals("add")) {
	            // Récupérer les données du formulaire
	            String nom = request.getParameter("nom");
	            String email = request.getParameter("email");
String type_tr="";
String ville = "";
	            // Envoie de l'e-mail (remplacez ceci par votre logique d'envoi d'e-mail)
	            // Utilisez une bibliothèque comme JavaMail pour envoyer l'e-mail

	            // Ajouter le transporteur à la base de données
	            String password = null;
	            Transporteur transporteur = new Transporteur(nom,type_tr,ville,eventID);
	            transporteurDAO.sendEmail(email);
	            // Redirection vers une page de confirmation ou autre
	            transporteurDAO.insert(transporteur);
	            response.sendRedirect("admin/base.jsp?action=listtr");
	            

	        } else {
	            // Autres actions ou gestion des erreurs
	            response.sendRedirect("erreur.jsp");
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        // Gestion des exceptions
	        e.printStackTrace();
	        response.sendRedirect("erreur.jsp");
	    }	}

}
