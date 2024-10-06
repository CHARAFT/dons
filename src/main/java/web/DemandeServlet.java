package web;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entities.Demande;
import entities.Event;
import DAO.DemandeDAO;
import DAO.DemandeDAOImpl;
import DAO.EventDAO;
import DAO.EventDAOImpl;

/**
 * Servlet implementation class DemandeServlet
 */
public class DemandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null; // Initialiser la connexion
		Demande dem = null;

	    try {
			conn = Getconnexion.getConnection(); 
			DemandeDAO demandeDAO = new DemandeDAOImpl(conn);
			String action = request.getParameter("action");

	        if (action == null || action.equals("listdem")) {
	            // Liste des demandess
		        List<Demande> dems = demandeDAO.getAll();
		        request.getSession().setAttribute("demandes", dems);
//	            request.getRequestDispatcher("admin/base.jsp?action=list").forward(request, response);
	            response.sendRedirect("admin/base.jsp?action=listdem");
	    		response.getWriter().append("Served at: ").append(request.getContextPath());

	        } else if (action.equals("ajout")) {
	            // Afficher le formulaire d'ajout de demande
	            request.getRequestDispatcher("ajouter-demande.jsp").forward(request, response);
	        } else if (action.equals("edit")) {
	            // Récupérer l'ID du freelancer à éditer depuis les paramètres de la requête
	            int demandeId = Integer.parseInt(request.getParameter("id"));

	            // Récupérer les données du demande depuis la base de données
	            dem = demandeDAO.getById(demandeId);

	            // Placer le demande dans la portée de la requête
	            request.setAttribute("demande", dem);

	            // Afficher le formulaire d'édition de freelancer
	            request.getRequestDispatcher("editer-demande.jsp").forward(request, response);
	        } else if (action.equals("supprimer")) {
	            // Récupérer l'ID du freelancer à supprimer depuis les paramètres de la requête
	            int demId = Integer.parseInt(request.getParameter("id"));

	            // Supprimer le freelancer de la base de données
	            demandeDAO.delete(demId);

	            // Rediriger vers la liste des freelancers
	            response.sendRedirect("EventServlet?action=listevent");
	        }else if (action.equals("consulter")){
	            int demId = Integer.parseInt(request.getParameter("id"));
	           dem = demandeDAO.getById(demId);
	            request.setAttribute("demande", dem);
	            request.getRequestDispatcher("consulter-demande.jsp").forward(request, response);

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
		DemandeDAO demDAO = new DemandeDAOImpl(conn);
        if (action.equals("ajouter")) {
            // Récupérer les données du formulaire d'ajout
//        	int id  = Integer.parseInt(request.getParameter("id"));


//            Date debut = request.getParameter("debut");
            Date date;
            
			try {
				 int demandeId = Integer.parseInt(request.getParameter("demandeId"));

				 String desc = request.getParameter("description");
				 String objet = request.getParameter("objet");
				 int qu= Integer.parseInt(request.getParameter("quantite"));
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				date = dateFormat.parse(request.getParameter("date"));
	            String nature = request.getParameter("nature");
	            Demande newdemande = new Demande (objet, desc, qu,  nature,date);
	            response.sendRedirect("DemandeServlet?action=listev");
	            // Ajouter le freelancer à la base de données
	            demDAO.insert(newdemande);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
           
           
        } else if (action.equals("editer")) {
            
					 int demandeId = Integer.parseInt(request.getParameter("id"));
					 int status = 1;
					 response.sendRedirect("DemandeServlet?action=listdem");
		            // Ajouter le freelancer à la base de données
		            demDAO.update(status,demandeId);

			
        }
	}

}
