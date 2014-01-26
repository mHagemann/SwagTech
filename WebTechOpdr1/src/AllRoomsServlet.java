
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;
import model.Room;
import model.User;

/**
 * Servlet voor de AllRooms class
 * @author Maickel / Sander
 *
 */
@WebServlet("/AllRooms")
public class AllRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllRoomsServlet() {
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

		HttpSession session = request.getSession(true);	

		int userid = -1;
		
		try {
			userid = (int)session.getAttribute("user_id");
		} catch(NullPointerException e) {
			response.sendRedirect("login.html");
			return;
		}
		
		Model model = (Model) sc.getAttribute("model");
	
		response.setContentType("text/html");
		
		PrintWriter p = response.getWriter();
		
		
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("lastLogin")) {
				p.write("Laatste keer ingelogd: " + c.getValue());
			}
		}
		for(Cookie c : cookies) {
			if(c.getName().equals("timesVisited")) {
				p.write("<br />Aantal keren ingelogd: " + c.getValue());
			}
		}
		
		p.print("<h1>Kamers</h1>");
		p.print("<table><tr><th>Land</th>\n"
				+ "<th>Stad</th>\n"
				+ "<th>Adres</th>\n"
				+ "<th>Prijs</th>\n"
				+ "<th>Oppervlakte</th>\n"
				+ "<th>Verhuurder</th></tr>");
		ArrayList<Room> rooms = (ArrayList<Room>) model.getRooms();
		for(int i = 0;i < rooms.size();i++) {
			Room r = rooms.get(i);
			p.print("<tr><td>"+ r.getLand() + "</td>"
							+"<td>" + r.getStad() + "</td>"
							+"<td>" + r.getAdres() + "</td>"
							+"<td>" + r.getPrijs() + "</td>"
							+"<td>" + r.getOppervlak() + "</td>"
							+"<td>" + model.getUsers().get(r.getUserId()-1).getUsername() + "</td>"
							+"</tr>");
		}
		p.print("</table>");
		p.print("<h1>Users</h1>");
		p.print("<table>"
				+ "<tr><th>Naam</th>\n"
				+ "<th>Type</th></tr>");
		
		ArrayList<User> users = (ArrayList<User>) model.getUsers();
		for(int i = 0;i < users.size();i++) {
			User u = users.get(i);
			p.print("<tr><td>" + model.getUsers().get(u.getId() - 1).getUsername() + "</td>"
							+ "<td>" + u.getType().toString() + "</td>"
							+ "</tr>");
		}
		p.print("</table>");
		
	}
}
