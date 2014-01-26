

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Model;

/**
 * Servlet voor de class Register
 * @author Sander / Maickel
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
		sc =  getServletContext();    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.html");
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		Model model = (Model)sc.getAttribute("model");
	
		ArrayList<String> errors = model.addUser(
				request.getParameter("username"), 
				request.getParameter("password"), 
				request.getParameter("password2"),
				request.getParameter("type"));
		
		if(errors.size() == 0) {
			response.sendRedirect("login.html");;
		} else {
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			out.println("<ul>");
			for(String e : errors) {
				out.println("<li>" + e + "</li>");
			}
			out.println("</ul>");
			out.println("<a href=\"register.html\">Keer terug</a>");
		}
	}
}
