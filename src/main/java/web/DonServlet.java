package web;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DAO.DonDAO;
import DAO.DonDAOImpl;
import DAO.TransporteurDAO;
import DAO.TransporteurDAOImpl;
import entities.Collecte;
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

	        } else if (action.equals("ajouter")) {
	            System.out.println("test");
	            
	        	  response.sendRedirect("donateur/ajouter-don.jsp");
		    	response.getWriter().append("Served at: ").append(request.getContextPath());

	            
	        }else if(action.equals("listdons")) {

	        	 List<Don> dons = donDAO.getDonByIdTransporteur(13);
			        request.getSession().setAttribute("dons", dons);
		            response.sendRedirect("transporteur/base.jsp?action=listdons");
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
		
		try {
			String action = request.getParameter("action");
			conn = Getconnexion.getConnection();
			if (action.equals("ajouter")) {
	            Date dateDeCollecte;
	          DonDAO donDAO = new DonDAOImpl(conn);
				
					 int quantite = Integer.parseInt(request.getParameter("quantite"));
				        String nature = request.getParameter("nature");
				        String ville = request.getParameter("ville");
				        String address = request.getParameter("address");
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						dateDeCollecte = (Date) dateFormat.parse(request.getParameter("date_de_collecte"));
				        int donateur_id = Integer.parseInt(request.getParameter("uid"));
				        int event_id = Integer.parseInt(request.getParameter("id"));
				        int transp_id=13;
				        int statut=0;
				        Don newdon = new Don(quantite, nature, ville, address, dateDeCollecte,transp_id, donateur_id, event_id,statut);
		        
		            // Ajouter le freelancer à la base de données
		            donDAO.insert(newdon);
			}else if(action.equals("selecttrans")) {
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
					} }catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
					else if(action.equals("edit")) {
						
						int donId = Integer.parseInt(request.getParameter("donId"));
				        int newStatus = Integer.parseInt(request.getParameter("newStatus"));

				        System.out.println("test");
				        // Mettre à jour le statut du don dans la base de données
				        DonDAO donDAO = new DonDAOImpl(conn); // Supposons que vous avez une classe DonDAO pour accéder à la base de données
				        donDAO.updateDonStatus(donId, newStatus); // Méthode pour mettre à jour le statut du don
				        
				        Don don = donDAO.getById(donId);
				        if (newStatus == 1) {
				        	LocalDateTime currentDateTime = LocalDateTime.now();

				            // Formater la date selon vos besoins
				            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				            String formattedDateTime = currentDateTime.format(formatter);

System.out.println(donId);
				        // Stocker la date formatée
				        String date = formattedDateTime;
				        int id= don.getTrans_id();
				        Collecte collecte = new Collecte(date,donId,id);
				        donDAO.insert(collecte);}
				        
			            response.sendRedirect("/dons/DonServlet?action=listdons");	        
					}
					
				 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
           
           
     
        
	
		
	    
			}
	       
	    
	}


