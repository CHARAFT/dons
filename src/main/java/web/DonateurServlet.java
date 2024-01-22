package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import entities.Donateur;
import entities.Event;
import DAO.DonnateurDAOImpl;
import DAO.DonnateurDAO;
/**
 * Servlet implementation class DonateurServlet
 */
public class DonateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Donateur dona = null;
		Connection conn = null; // Initialiser la connexion

	    try {
			conn = Getconnexion.getConnection(); 
			DonnateurDAO donaDAO = new DonnateurDAOImpl(conn);
			String action = request.getParameter("action");

	        if (action == null || action.equals("listdona")) {
	            // Liste des donnateurs
		        List<Donateur> donas = donaDAO.getAll();
		        request.getSession().setAttribute("donateurs", donas);
//	            request.getRequestDispatcher("admin/base.jsp?action=list").forward(request, response);
	            response.sendRedirect("admin/base.jsp?action=listdona");
	    		response.getWriter().append("Served at: ").append(request.getContextPath());

	        } else if (action.equals("ajout")) {
	            // Afficher le formulaire d'ajout de donnateur
	            request.getRequestDispatcher("ajouter-donateur.jsp").forward(request, response);
	        } else if (action.equals("edit")) {
	            // Récupérer l'ID du freelancer à éditer depuis les paramètres de la requête
	            int donaId = Integer.parseInt(request.getParameter("id"));

	            // Récupérer les données du donnateur depuis la base de données
	            dona = donaDAO.getById(donaId);

	            // Placer le donnateur dans la portée de la requête
	            request.setAttribute("donateur", dona);

	            // Afficher le formulaire d'édition de freelancer
	            request.getRequestDispatcher("editer-donateur.jsp").forward(request, response);
	        } else if (action.equals("supprimer")) {
	            // Récupérer l'ID du donateur à supprimer depuis les paramètres de la requête
	            int donaId = Integer.parseInt(request.getParameter("id"));

	            // Supprimer le donnateur de la base de données
	            donaDAO.delete(donaId);

	            // Rediriger vers la liste des donnateurs
	            response.sendRedirect("DonateurServlet?action=listdona");
	        }else if (action.equals("consulter")){
	            int donaId = Integer.parseInt(request.getParameter("id"));
	            dona = donaDAO.getById(donaId);
	            request.setAttribute("donateur", dona);
	            request.getRequestDispatcher("consulter-donateur.jsp").forward(request, response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
