package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import web.Getconnexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("logout".equals(action)) {
	        // Logique de déconnexion
	        HttpSession session = request.getSession();
	        session.invalidate();
	        response.sendRedirect("Registration/auth.jsp");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uemail = request.getParameter("username");
		String upwd = request.getParameter("password");

		HttpSession session = request.getSession();

		Connection conn = null; 
		try {
			conn = Getconnexion.getConnection(); 
			PreparedStatement pst = conn.prepareStatement("select * from users where uemail=? and upwd=?");			
			pst.setString(1,uemail);
			pst.setString(2,upwd);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {

				 String role = rs.getString("role");
				 int user_id = rs.getInt("id"); // Récupérer l'ID de l'utilisateur
                 session.setAttribute("user_id", user_id); // Définir l'attribut "user_id" dans la session

 				session.setAttribute("name",rs.getString("uname"));
                 redirectToDashboard(response, role);
			}else {
				request.setAttribute("status","failed");
				request.getRequestDispatcher("Registration/login.jsp").forward(request, response);
			
			}
			
		 } catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
				conn.close();}
				catch (SQLException e){e.printStackTrace();
					
				}
			}

}
	  private void redirectToDashboard(HttpServletResponse response, String role) throws IOException {
	        // Redirection vers le tableau de bord approprié
	        switch (role) {
	            case "transporteur":
	            	
	                response.sendRedirect("/dons/transporteur/base.jsp");
	                break;
	            case "beneficiaire":
	                response.sendRedirect("/beneficiaire_dashboard.jsp");
	                break;
	            case "donateur":
	                response.sendRedirect("/dons/donateur/donateur_dashboard.jsp");
	                break;
	            
	            default:
	              
	                response.sendRedirect("admin/base.jsp");
	        }
	    }

	

}
