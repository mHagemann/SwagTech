
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
import model.Room;

/**
 * Servlet van de class myRooms
 * @author Sander / Maickel
 */
@WebServlet("/MyRooms")
public class MyRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRoomsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	
		int user_id = -1;
		
		try {
			user_id = (int)session.getAttribute("user_id");
		} catch(NullPointerException e) {
			response.sendRedirect("login.html");
			return;
		}
		
		sc =  getServletContext();

		Model model = (Model)sc.getAttribute("model");
		
		response.setContentType("text/html");
		
		PrintWriter p = response.getWriter();
		
		p.print("<h1>Kamers van: " + model.getUsers().get(user_id-1).getUsername() + "</h1>");
		
		p.print("<table><tr><th>Land</th>\n"
				+ "<th>Stad</th>\n"
				+ "<th>Adres</th>\n"
				+ "<th>Prijs</th>\n"
				+ "<th>Oppervlakte</th>\n"
				+ "<th>Verhuurder</th></tr>");
		
		ArrayList<Room> rooms = (ArrayList<Room>) model.getRooms();
		for(int i = 0;i < rooms.size();i++) {
			Room r = rooms.get(i);
			int id = r.getUserId()-1;
			
			if(r.getUserId() == user_id) {
				p.print("<tr><td>" + r.getLand() + "</td>"
								+"<td>" + r.getStad() + "</td>"
								+"<td>" + r.getAdres() + "</td>"
								+"<td>" + r.getPrijs() + "</td>"
								+"<td>" + r.getOppervlak() + "</td>"
								+"<td>" + model.getUsers().get(id).getUsername() + "</td>"
								+"</tr>");
			}
		}
		p.print("</table>");
		p.print("<a href=\"AddRoom\">Voeg nieuwe kamer toe</a>");
	}
}
