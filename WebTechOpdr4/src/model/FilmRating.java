package model;

public class FilmRating {

	private int average;
	private Film film;
	
	/**
	 * omdat Jersey
	 */
	public FilmRating() { }
	
	/**
	 * Constructor voor de film ratings.
	 * Hier wordt het film object met de bijbehorende gemiddelde rating opgeslagen.
	 * @param average
	 * @param film
	 */
	public FilmRating(int average, Film film) {
		this.average = average;
		this.film = film;
	}
	
	/**
	 * Getter voor de film
	 * @return
	 */
	public Film getFilm() {
		return film;
	}
	
	/**
	 * Getter voor de gemiddelde rating
	 * @return
	 */
	public int getAverage() {
		return average;
	}
	
}
