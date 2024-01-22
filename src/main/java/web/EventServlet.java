package web;
import java.sql.Connection;



import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import web.Getconnexion;
import DAO.EventDAO;
import DAO.EventDAOImpl;
import entities.Event;




/**
 * Servlet implementation class EventServlet
 */
@MultipartConfig
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null; // Initialiser la connexion
//	    try {
//	        conn = Getconnexion.getConnection(); // Obtenez une connexion JDBC
//	        EventDAO eventDAO = new EventDAOImpl(conn);
//	        List<Event> events = eventDAO.getAll();
//			request.setAttribute("events", events);
//			request.getRequestDispatcher("admin/base.jsp").forward(request, response);
//			response.getWriter().append("Served at: ").append(request.getContextPath());
//	    } catch (ClassNotFoundException | SQLException e) {
//	        e.printStackTrace();
//	        // Gérez l'exception ici, par exemple en renvoyant une erreur HTTP 500
//	    } finally {
//	        if (conn != null) {
//	            try {
//	                conn.close(); // Fermez la connexion dans la clause finally
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	                // Gérez l'exception ici si la fermeture échoue
//	            }
//	        }
//	    }
	    Event event = null;

	    try {
			conn = Getconnexion.getConnection(); 
			EventDAO eventDAO = new EventDAOImpl(conn);
			String action = request.getParameter("action");

	        if (action == null || action.equals("listev")) {
	            // Liste des freelancers
		        List<Event> events = eventDAO.getAll();
		        request.getSession().setAttribute("events", events);
//	            request.getRequestDispatcher("admin/base.jsp?action=list").forward(request, response);
	            response.sendRedirect("admin/base.jsp?action=listev");
	    		response.getWriter().append("Served at: ").append(request.getContextPath());

	        } else if (action.equals("ajout")) {
	            // Afficher le formulaire d'ajout de freelancer
	            request.getRequestDispatcher("ajouter-event.jsp").forward(request, response);
	        } else if (action.equals("edit")) {
	            // Récupérer l'ID du freelancer à éditer depuis les paramètres de la requête
	            int eventId = Integer.parseInt(request.getParameter("id"));

	            // Récupérer les données du freelancer depuis la base de données
	            event = eventDAO.getById(eventId);

	            // Placer le freelancer dans la portée de la requête
	            request.setAttribute("event", event);

	            // Afficher le formulaire d'édition de freelancer
	            request.getRequestDispatcher("editer-event.jsp").forward(request, response);
	        } else if (action.equals("supprimer")) {
	            // Récupérer l'ID du freelancer à supprimer depuis les paramètres de la requête
	            int eventId = Integer.parseInt(request.getParameter("id"));

	            // Supprimer le freelancer de la base de données
	            eventDAO.delete(eventId);

	            // Rediriger vers la liste des freelancers
	            response.sendRedirect("EventServlet?action=listev");
	        }else if (action.equals("consulter")){
	            int eventId = Integer.parseInt(request.getParameter("id"));
	            event = eventDAO.getById(eventId);
	            request.setAttribute("event", event);
	            request.getRequestDispatcher("consulter-event.jsp").forward(request, response);

	        }

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Connection conn = null; // Initialiser la connexion
	   
		try {
			conn = Getconnexion.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		EventDAO eventDAO = new EventDAOImpl(conn);
        if (action.equals("ajouter")) {
            // Récupérer les données du formulaire d'ajout
//        	int id  = Integer.parseInt(request.getParameter("id"));


//            Date debut = request.getParameter("debut");
            Date debut;
            Date fin;
			try {
				 String nom = request.getParameter("nom");
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				debut = dateFormat.parse(request.getParameter("debut"));
				fin = dateFormat.parse(request.getParameter("fin"));
				String objectif =request.getParameter("objectif");
	            String lieu = request.getParameter("lieu");
	            Part filePart = request.getPart("image");  // Assurez-vous que votre champ d'upload s'appelle "image" dans le formulaire
	            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	            String basePath = getServletContext().getRealPath("/");
	            String imagePath = "C:/Users/pc/eclipse-workspace/dons/WebContent/admin/images" + File.separator + fileName;
	            //System.out.println("dcs"+imagePath);
	            // Stocker l'image sur le serveur
	            try (InputStream fileContent = filePart.getInputStream()) {
	                Files.copy(fileContent, Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
	            }

	            // Création d'un nouvel objet Event avec le chemin de l'image
	            String image="/admin/images/"+ fileName;
	            Event newEvent = new Event(nom, debut, fin, objectif, lieu, image);
	            response.sendRedirect("EventServlet?action=listev");
	            // Ajouter le event à la base de données
	            eventDAO.insert(newEvent);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
           
           
        } else if (action.equals("editer")) {
            // Récupérer les données du formulaire d'édition
           

            Date debut;
            Date fin;
			try {
				 int eventId = Integer.parseInt(request.getParameter("eventId"));
				 String nom = request.getParameter("nom");
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				debut = dateFormat.parse(request.getParameter("debut"));
				fin = dateFormat.parse(request.getParameter("fin"));
				String objectif =request.getParameter("objectif");
	            String lieu = request.getParameter("lieu");
	            
	            


	            // Mettre à jour le freelancer dans la base de données
//	           Event updatedEvent = new Event(eventId, nom, debut, fin, objectif, lieu);
//	            eventDAO.update(updatedEvent);
	            response.sendRedirect("EventServlet?action=listev");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	

}
