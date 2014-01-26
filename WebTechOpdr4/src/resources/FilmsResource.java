package resources;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import model.Film;
import model.FilmRating;
import model.Model;

@Path("/films")
public class FilmsResource {
	@Context ServletContext context;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<FilmRating> getFilms() {	
		Model model = (Model) context.getAttribute("model");
		return model.getFilmsWithRating();
	}
	
	@GET	
	@Path("{imbdNumber}")	
	@Produces({MediaType.APPLICATION_JSON})
	public Film getFilm(@PathParam("imbdNumber") String imbdNumber) {
		Model model = (Model) context.getAttribute("model");
		return model.getFilmByIMDBTTNumber(imbdNumber);
	}
}
