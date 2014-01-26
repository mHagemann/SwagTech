package model;

public class Rating {
	private String nickname;
	private String imdbNumber;
	private int rating; //number from 1 through 10
	private int id;
	
	
	/**
	 * Default constructor voor Jesery
	 */
	public Rating() {}
	
	/**
	 * Constructor voor de Rating class
	 * @param rating
	 * @param id
	 * @param nickname
	 * @param imdbNumber
	 */
	public Rating(int id, int rating, String nickname, String imdbNumber) {
		this.id = id;
		this.rating = rating;
		this.nickname = nickname;
		this.imdbNumber = imdbNumber;
	}
	
	/**
	 * Getter voor het id van een rating
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * setter voor het id;
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter om de rating op te halen
	 * @return int tussen 1 t/m 10
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * setter voor de rating van een film.
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	/**
	 * Getter om de gerate film op te halen
	 * @return het imdbNumber van de film
	 */
	public String getImdbNumber() {
		return imdbNumber;
	}
	
	/**
	 * setter voor het imdb nummer
	 * @param imdbNumber
	 */
	public void setImdbNumber(String imdbNumber) {
		this.imdbNumber = imdbNumber;
	}
	
	/**
	 * Getter om de nickname van de gebruiker die de rating heeft geplaatst op te halen
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
}
