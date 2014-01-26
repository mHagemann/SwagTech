package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.Rating;
import model.User;
import model.UserToken;

@Path("/rate")
public class RateResource {
	@Context ServletContext context;
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response rate( Rating r ) {
		Model model = (Model) context.getAttribute("model");
		try {
			int ratingID = model.getRatings().size() + 1;
			if (r.getRating() > 0 && r.getRating() < 11) {
				r.setId(ratingID);
				model.addRating(r);
			} else {
				throw new WebApplicationException(Response.Status.BAD_REQUEST);
			}

		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		return Response.status(Response.Status.CREATED).entity(r).build();
	}
	
	@DELETE	
	@Path("{ratingID}")	
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteRating(@PathParam("ratingID") int ratingID, UserToken token) {
		System.out.println("Deleting rating number: " + ratingID);
		Model model = (Model) context.getAttribute("model");
		try {
			User u = model.getUserByToken(token.getToken());
			ArrayList<Rating> ratings = model.getRatings();
			for (Rating r : ratings) {
				if (r.getId() == ratingID && r.getNickname().equals(u.getNickname())) {
					if (model.deleteRating(r)) {
						System.out.println("deleted");
						return Response.status(Response.Status.OK).entity("{\"rating\" : \"deleted\"}").build();
					}
				}
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("Failed to delete the rating :(").build();
	}
	
	@POST	
	@Path("{ratingID}")	
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateRating(@PathParam("ratingID") int ratingID, Rating rating) {
		
		Model model = (Model) context.getAttribute("model");
		try {
			ArrayList<Rating> ratings = model.getRatings();
			for (Rating r : ratings) {
				if(rating.getRating() > 0 && rating.getRating() < 11) {
					if (r.getId() == ratingID && rating.getNickname().equals(r.getNickname())) {
						if (model.deleteRating(r)) {
							rating.setId(ratingID);
							if (model.addRating(rating)) {
								return Response.status(Response.Status.OK).entity("{\"rating\" : \"updated\"}").build();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("Failed to update the rating :(").build();
	}
}
