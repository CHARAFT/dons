package web;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DonDAO;
import DAO.DonDAOImpl;
import DAO.TransporteurDAO;
import DAO.TransporteurDAOImpl;
import entities.Don;
import entities.Transporteur;

/**
 * Servlet implementation class DonServlet
 */
public class DonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Don don = null;
		Connection conn = null; // Initialiser la connexion

	    try {
			conn = Getconnexion.getConnection(); 
			DonDAO donDAO = new DonDAOImpl(conn);
			TransporteurDAO transporteurDAO = new TransporteurDAOImpl(conn);


			String action = request.getParameter("action");

	        if (action == null || action.equals("listdon")) {
	            // Liste des donnateurs
		        List<Don> dons = donDAO.getAll();
	            List<Transporteur> transporteurs = transporteurDAO.getAll();
	            System.out.println(transporteurs);
		        request.getSession().setAttribute("dons", dons);
		        request.getSession().setAttribute("transporteurs", transporteurs);

//	            request.getRequestDispatcher("admin/base.jsp?action=list").forward(request, response);
	            response.sendRedirect("admin/base.jsp?action=listdon");
	    		response.getWriter().append("Served at: ").append(request.getContextPath());

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
		Connection conn = null;
		String transpId = request.getParameter("transporteur");
		String[] selectedDonsIds = request.getParameterValues("selectedDons");
		try {
			conn = Getconnexion.getConnection();
			if (selectedDonsIds != null && selectedDonsIds.length > 0) {
	        List<Don> selectedDons = new ArrayList<>();
	        DonDAO donDAO = new DonDAOImpl(conn); // Remplacez cela par votre gestionnaire d'accès aux données

	        for (String donId : selectedDonsIds) {
	            int id = Integer.parseInt(donId);
	            Don don = donDAO.getById(id);
	            selectedDons.add(don);
	        }
	        System.out.println(selectedDons);
	        System.out.println(transpId);
            response.sendRedirect("admin/base.jsp?action=listdon");
}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    

	       
	    
	}

}
