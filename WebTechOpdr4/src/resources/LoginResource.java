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
import model.UserPass;
import model.UserToken;

@Path("/login")
public class LoginResource {
	@Context ServletContext context;

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response Login(UserPass u) {
		Model model = (Model) context.getAttribute("model");
		String password = u.getPassword();
		String nickname = u.getNickname();
		System.out.println(nickname + ":" + password);
		UserToken token = null;
		try {
			if (model.loginCompare(nickname, password)) {
				User user = model.getUser(nickname, password);
				token = model.createUserToken(user);
				System.out.println(token.getToken());
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		if (token != null) {
			return Response.status(Response.Status.OK).entity("{\"token\": \"" + token.getToken() + "\","
					+ "\"nickname\" : \"" + nickname +  "\"}").build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(token).build();
	}
}