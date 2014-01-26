package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Model;
import model.User;

@Path("/register")
public class RegisterResource {
	@Context ServletContext context;

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response register( User u ) {
		User user = null;
		Model model = (Model) context.getAttribute("model");
		
		try {
			user = u;
			model.addUser(user);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		return Response.status(Response.Status.CREATED).entity(user).build();
	}
}
