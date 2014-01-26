package resources;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import model.Model;
import model.User;

@Path("/users")
public class UsersResource {
	@Context ServletContext context;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<User> getUsers() {
		Model model = (Model) context.getAttribute("model");
		return model.getUsers();		
	}
	
	@GET	
	@Path("{nickname}")	
	@Produces({MediaType.APPLICATION_JSON})
	public User getUser(@PathParam("nickname") String nickname) {
		User u = ((Model) context.getAttribute("model")).getUserByNickname(nickname);
		System.out.println(u);
		if( u != null) {
			return u;
		} else {
			return new User(0, null, null, null, null, null);
		}
	}
}
