import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

/**
 * Servlet voor het toevoegen van kamers aan het systeem
 * 
 * @author Maickel / Sander
 */
@WebServlet("/AddRoom")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	private HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		sc = getServletContext();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check if the user is logged in
		session = request.getSession(true);

		int user_id = -1;

		try {
			user_id = (int) session.getAttribute("user_id");
			System.out.println("user:" + user_id);
		} catch (NullPointerException e) {
			response.sendRedirect("login.html");
			return;
		}

		sc.getRequestDispatcher("/WEB-INF/pages/addRoom.html").forward(request,
				response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(true);

		int user_id = -1;

		try {
			user_id = (int) session.getAttribute("user_id");
			System.out.println("user:" + user_id);
		} catch (NullPointerException e) {
			response.sendRedirect("login.html");
			return;
		}

		/**
		 * Hier wordt er een kamer toegevoegd aan het systeem
		 */
		try {
			ArrayList<String> errors = ((Model) sc.getAttribute("model"))
					.addRoom(
							request.getParameter("land"),
							request.getParameter("stad"),
							request.getParameter("adres"),
							Integer.parseInt(request.getParameter("oppervlak")),
							Integer.parseInt(request.getParameter("prijs")),
							user_id);
			/**
			 * Als er geen errors zijn met het toevoegen dan wordt je naar
			 * myRooms gestuurd Anders wordt het niet toegevoegd
			 */
			if (errors.size() == 0) {
				response.sendRedirect("MyRooms");
			} else {
				System.out.println("Niet toegevoegd");
			}
		} catch (Exception e) {
			sc.getRequestDispatcher("/WEB-INF/pages/errorAddRoom.html").forward(
					request, response);
		}
	}
}
