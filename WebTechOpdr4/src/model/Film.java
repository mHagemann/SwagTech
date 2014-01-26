package model;

public class Film {
	
	private String imdbNumber;
	private String title;
	private int duration;
	private String date;
	private String director;
	private String description;
	
	/**
	 * omdat Jersey
	 */
	public Film() { }
	
	/**
	 * Constructor voor de Film class
	 * @param imdbNumber
	 * @param title
	 * @param duration
	 * @param date
	 * @param director
	 * @param description
	 */
	public Film(String imdbNumber, String title, int duration,
			String date, String director, String description) {
		this.imdbNumber = imdbNumber;
		this.title = title;
		this.duration = duration;
		this.date = date;
		this.director = director;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getImdbNumber() {
		return imdbNumber;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getDescription() {
		return description;
	}
}
