package web;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import web.Getconnexion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("name");
		String uemail=request.getParameter("email");
		String upwd =request.getParameter("pass");
		String umobile=request.getParameter("contact");
		String role = request.getParameter("role");
	
		Connection conn = null; 
	
		 try {
				conn = Getconnexion.getConnection();
				PreparedStatement pst = conn.prepareStatement("insert into users(uname,uemail,upwd,umobile,role) values(?,?,?,?,?)");
				pst.setString(1,uname);
				pst.setString(2,uemail);
				pst.setString(3,upwd);
				pst.setString(4,umobile);
				 pst.setString(5, role);
				
				
				int rowCount = pst.executeUpdate();
				dispatcher = request.getRequestDispatcher("Registration/auth.jsp");
				
			
				if (rowCount > 0) {
					request.setAttribute("status","success");
					
				}else {request.setAttribute("status","failed");
					
				}
				dispatcher.forward(request, response);
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

}
