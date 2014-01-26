package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Film;
import model.FilmTitleRating;
import model.Model;
import model.Rating;
import model.User;
import model.UserToken;

@Path("/ownRatings")
public class OwnRatingsResource {
	@Context ServletContext context;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<FilmTitleRating> getOwnRatings(UserToken token) {
		System.out.println(token.getToken());
		Model model = (Model) context.getAttribute("model");
		User u = model.getUserByToken(token.getToken());
		//System.out.println(u.toString());
		ArrayList<Rating> ratings = model.getOwnRatings(u.getNickname());
		System.out.println("" + ratings.get(0).getRating());
		ArrayList<FilmTitleRating> ftm = new ArrayList<FilmTitleRating>();
		for (Rating r : ratings) {
			Film f = model.getFilmByIMDBTTNumber(r.getImdbNumber());
			String title = f.getTitle();
			System.out.println(title);
			ftm.add(new FilmTitleRating(title, r));
		}
		return ftm;
	}
	
	@POST
	@Path("/notRated")	
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ArrayList<Film> getNotYetRatedFilms(UserToken token) {
		System.out.println(token.getToken());
		Model model = (Model) context.getAttribute("model");
		return model.getFilmsWithoutRating(token.getToken());
	}
}
