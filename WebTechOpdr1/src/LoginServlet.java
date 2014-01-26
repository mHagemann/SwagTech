

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
import model.User;

/**
 * Servlet implementation class Login
 * @author Maickel / Sander
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();        
    }
    
    @Override
    public void init() {
		sc =  getServletContext();    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("login.html");
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter p = response.getWriter();
		p.write("User: " + request.getParameter("username") + "\n");
		p.write("Wachtwoord: " + request.getParameter("password") + "\n");
		
        ArrayList<User> users = (ArrayList<User>) ((Model) sc.getAttribute("model")).getUsers();
        
		System.out.println(users);

		for(int i = 0;i < users.size();i++)
		{
			if(request.getParameter("username").equals(users.get(i).getUsername()))
			{
				if(request.getParameter("password").equals(users.get(i).getPassword()))
				{
					HttpSession session = request.getSession(true);
					session.setAttribute("user_id", users.get(i).getId());
					
					/**
					 * Kijkt wat voor een rol de gebruiker heeft en stuurt de gebruiker
					 * vervolgens naar de goede kamer door
					 */
					System.out.println("User group: " + users.get(i).getType());
					if(users.get(i).getType().equals("huurder")) {
						System.out.println("huurder");
						response.sendRedirect("SearchRooms");
						return;
					} else if(users.get(i).getType().equals("verhuurder")) {
						System.out.println("verhuurder");
						response.sendRedirect("MyRooms");
						return;
					} else if(users.get(i).getType().equals("admin")) {
						System.out.println("admin");
						Calendar cal = Calendar.getInstance();
				    	cal.getTime();
				    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				    	Date date = new Date();
				    	System.out.println( sdf.format(cal.getTime()));
						Cookie cookie = new Cookie("lastLogin", date + sdf.format(cal.getTime()));
						cookie.setMaxAge(14400);
						response.addCookie(cookie);
						
						if(!(request.getCookies() == null)) {
							Cookie[] cookies = request.getCookies();
							for(Cookie c : cookies) {
								if(c.getName().equals("timesVisited")) {
									int n = Integer.parseInt(c.getValue());
									n++;
									Cookie cookieTimesVisited = new Cookie("timesVisited", Integer.toString(n));
									cookieTimesVisited.setMaxAge(14400);
									response.addCookie(cookieTimesVisited);
								} else {
									Cookie cookieFirstVisit = new Cookie("timesVisited", Integer.toString(1));
									cookieFirstVisit.setMaxAge(14400);
									response.addCookie(cookieFirstVisit);
								}
							}
						}
						response.sendRedirect("AllRooms");
						return;
					}
					return;
				}
			}
		}
		sc.getRequestDispatcher("/WEB-INF/pages/badLogin.html").forward(request, response);
		return;
	}
}
