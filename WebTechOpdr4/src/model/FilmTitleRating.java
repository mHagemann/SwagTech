package model;

/**
 * Een class die een rating met titel opslaat
 * @author Hagemann
 *
 */
public class FilmTitleRating {
	private Rating rating;
	private String titel;
	
	public FilmTitleRating() {}
	
	public FilmTitleRating(String titel, Rating rating) {
		this.titel = titel;
		this.rating = rating;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
}
