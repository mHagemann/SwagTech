
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

/**
 * Servlet van de class SearchRooms
 * @author Maickel / Sander
 */
@WebServlet("/SearchRooms")
public class SearchRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	private HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRoomsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
		sc = getServletContext(); 	
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);		
		int user_id = -1;
		
		try {
			user_id = (int)session.getAttribute("user_id");
		} catch(NullPointerException e) {
			response.sendRedirect("login.html");
			return;
		}
		sc.getRequestDispatcher("/WEB-INF/pages/searchRooms.html").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);		
		int user_id = -1;
		try {
			user_id = (int)session.getAttribute("user_id");
		} catch(NullPointerException e) {
			sc.getRequestDispatcher("/WEB-INF/pages/login.html").forward(request, response);
			return;
		}
		
		PrintWriter p = response.getWriter();
		response.setContentType("text/html");

		Model model = (Model)sc.getAttribute("model");
		
		p.print("<table><tr><th>Land</th>\n"
				+ "<th>Stad</th>\n"
				+ "<th>Adres</th>\n"
				+ "<th>Prijs</th>\n"
				+ "<th>Oppervlakte</th>\n"
				+ "<th>Verhuurder</th></tr>");
		
		ArrayList<Room> rooms = (ArrayList<Room>) ((Model) sc.getAttribute("model")).getRooms();
		for(int i = 0;i < rooms.size();i++) {
			Room r = rooms.get(i);
			if (rooms.get(i).getStad().equals(request.getParameter("zoek"))) {
				p.print("<tr><td>" + r.getLand() + "</td>"
								+"<td>" + r.getStad() + "</td>"
								+"<td>" + r.getAdres() + "</td>"
								+"<td>" + r.getPrijs() + "</td>"
								+"<td>" + r.getOppervlak() + "</td>"
								+"<td>" + model.getUsers().get(r.getUserId()-1).getUsername() + "</td></tr>");
				}
		}
		p.print("</table>");
		
		Cookie cookie = new Cookie("searchCity", request.getParameter("zoek"));
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		Cookie[] cookies = request.getCookies();
		List<Cookie> lijstCookies = Arrays.asList(cookies);
		p.write("Recent gezochte stad: ");
		for(Cookie c : lijstCookies) {
			if(c.getName().equals("searchCity")) {
				p.write(c.getValue() + ", ");
				System.out.println("Waarde " + c.getValue());
			} 
		}
	}
}
