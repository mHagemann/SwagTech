package model;

import java.util.ArrayList;
import java.util.UUID;

public class Model {
	
	private ArrayList<Film> films = new ArrayList<Film>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Rating> ratings = new ArrayList<Rating>();
	private ArrayList<FilmRating> filmRatings = new ArrayList<FilmRating>();
	private ArrayList<String> tokens = new ArrayList<String>();
	private ArrayList<UserToken> usertokens = new ArrayList<UserToken>();
	
	/**
	 * constructor van het model
	 * hier worden standaard users en films toegevoegd
	 */
	public Model() { 
		
		/**
		 * standaard users worden aangemaakt en in de lijst met users gestopt
		 */
		User u1 = new User(users.size() + 1, "Vries", "Henk", "de", "Henkiee", "banaan");
		users.add(u1);
		
		User u2 = new User(users.size() + 1, "Bla", "Hans", null, "admin", "banaan");
		users.add(u2);
		//standaard ratings
		addRating(new Rating(ratings.size() + 1, 6, "admin", "tt0111161"));
		addRating(new Rating(ratings.size() + 1, 7, "admin", "tt0060196"));
		addRating(new Rating(ratings.size() + 1, 9, "admin", "tt0167260"));
		addRating(new Rating(ratings.size() + 1, 4, "admin", "tt0068646"));
		
		User u3 = new User(users.size() + 1, "PRPRPR", "Ewewewe", "ten", "ewprpwe", "appel");
		users.add(u3);
		User u4 = new User(users.size() + 1, "uiop", "qwerty", "van", "qwerty", "appel");		
		users.add(u4);
		
		/**
		 * standaard films worden aangemaakt en in de lijst met films gestopt
		 */
		Film film1 = new Film("tt0111161", "The Shawshank Redemption", 142, "02-03-1995", "Frank Darabont", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");
		Film film2 = new Film("tt0068646", "The Godfather", 175, "18-01-1973", "Francis Ford Coppola", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");
		Film film3 = new Film("tt0071562", "The Godfather. Part II", 200, "17-07-1975", "Francis Ford Coppola", "The early life and career of Vito Corleone in 1920s New York is portrayed while his son, Michael, expands and tightens his grip on his crime syndicate stretching from Lake Tahoe, Nevada to pre-revolution 1958 Cuba.");
		Film film4 = new Film("tt0110912", "Pulp Fiction", 154, "01-12-1994", "Quentin Tarantino", "The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.");
		Film film5 = new Film("tt0060196", "Il buono, il brutto, il cattivo", 146, "08-02-1968", "Sergio Leone", "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.");
		Film film6 = new Film("tt0468569", "The Dark Knight", 152, "24-07-2008", "Christopher Nolan", "When Batman, Gordon and Harvey Dent launch an assault on the mob, they let the clown out of the box, the Joker, bent on turning Gotham on itself and bringing any heroes down to his level.");
		Film film7 = new Film("tt0050083", "12 Angry Men ", 96, "04-1957", "Sidney Lumet", "A dissenting juror in a murder trial slowly manages to convince the others that the case is not as obviously clear as it seemed in court.");
		Film film8 = new Film("tt0108052", "Schindler's List", 195, "03-03-1993", "Steven Spielberg", "In Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.");
		Film film9 = new Film("tt0167260", "The Lord of the Rings: The Return of the King", 201, "17-12-2003", "Peter Jackson", "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.");
		Film film10 = new Film("tt0137523", "Fight Club", 139, "04-11-1999", "David Fincher", "An insomniac office worker looking for a way to change his life crosses paths with a devil-may-care soap maker and they form an underground fight club that evolves into something much, much more...");

		films.add(film1);
		films.add(film2);
		films.add(film3);
		films.add(film4);
		films.add(film5);
		films.add(film6);
		films.add(film7);
		films.add(film8);
		films.add(film9);
		films.add(film10);
	}
	
	/**
	 * Getter om de users op te halen,
	 * @return arraylist van users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Hier wordt er een userobject toegevoegd
	 * aan de users lijst.
	 */
	public void addUser(User user) {
		user.setID(users.size() + 1);
		users.add(user);
	}
	
	/**
	 * Hier wordt er een userobject teruggegeven die aan de opgegeven nickname voldoet
	 * @return het userobject, null als niks is gevonden. 
	 */
	public User getUserByNickname(String nickname) {
		for (User u : users) {
			if(u.getNickname().equals(nickname)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Methode om een rating toe te voegen
	 * @param r de rating
	 * @return true als de rating is toegevoegd.
	 */
	public boolean addRating(Rating rating) {
		ratings.add(rating);
		for (Rating r : ratings) {
			if (r.equals(rating)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Methode om een rating te verwijderen
	 * @param rating
	 * @return returned true als het verwijderen gelukt is
	 */
	public boolean deleteRating(Rating rating) {
		for (Rating r : ratings) {
			if (r.equals(rating)) {
				ratings.remove(r);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * getter voor alle ratings
	 * @return arraylist van alle ratings
	 */
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	/**
	 * Returned een arraylist van ratings die bij de opgegeven gebruiker hoort
	 * @param nickname
	 * @return arraylist van ratings
	 */
	public ArrayList<Rating> getOwnRatings(String nickname) {
		ArrayList<Rating> ownRatings = new ArrayList<Rating>();
		for (Rating r : ratings) {
			if (r.getNickname().equals(nickname)) {
				ownRatings.add(r);
			}
		}
		return ownRatings;
	}
	
	/**
	 * Getter om de films op te halen
	 * @return arraylist met film objecten
	 */
	public ArrayList<Film> getFilms() {
		return films;
	}
	
	/**
	 * getter om de films op te halen met de gemiddelde rating
	 * @return
	 */
	public ArrayList<FilmRating> getFilmsWithRating() {
		filmRatings = new ArrayList<FilmRating>();
		for (Rating r : ratings) {
			int totalScore = 0;
			int counter = 0;
			String imbdnumber = r.getImdbNumber();
			Film film = getFilmByIMDBTTNumber(imbdnumber);
			
			for (Rating x : ratings) {
				if (x.getImdbNumber().equals(imbdnumber)) {
					counter++;
					totalScore += x.getRating();
				}
			}
			
			int averageScore = 0;
			if (counter > 0) {
				averageScore = totalScore / counter;
			}
			
			FilmRating rating = new FilmRating(averageScore, film);
			
			if (!hasFilmRating(rating)) {
				filmRatings.add(rating);
			}
		}
		
		return filmRatings;
	}
	
	/**
	 * Methode om een lijst met films op te halen die nog niet door de gebruiker gerate zijn
	 * @return arraylist met film objecten
	 */
	public ArrayList<Film> getFilmsWithoutRating(String token) {
		User u = getUserByToken(token);
		ArrayList<Film> notRated = new ArrayList<Film>();
		ArrayList<Rating> ownRatings = getOwnRatings(u.getNickname());
		
		for (Film f : films) {
			if (!hasRating(ownRatings, f)) {
				notRated.add(f);
			}
		}
		return notRated;
	}
	
	/**
	 * Methode die kijkt of een film al gerate is
	 * @param r
	 * @return
	 */
	private boolean hasRating(ArrayList<Rating> r, Film film) {
		
		for (Rating rating : r) {
			if (rating.getImdbNumber().equals(film.getImdbNumber())) {
				System.out.println(film.getTitle() + " has already been rated!");
				return true;
			}
		}
		System.out.println(film.getTitle() + " has not yet been rated!");
		return false;
	}
	
	/**
	 * In deze methode wordt gekeken of er al een filmrating 
	 * in de lijst met filmratings staat. Wordt gebruikt door de
	 * getFilmsWithRating() methode.
	 * @param filmrating
	 * @return true als de opgegeven filmrating in de lijst bestaat
	 */
	private boolean hasFilmRating(FilmRating filmrating) {
		for (FilmRating f : filmRatings) {
			if (f.getFilm().equals(filmrating.getFilm())) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Getter om films op te halen aan de hand van het IMDB tt-nummer
	 * @return Film object die het opgegeven IMBD nummer heeft, anders null
	 */
	public Film getFilmByIMDBTTNumber(String imbdTTnumber) {
		for (Film f : films) {
			if (f.getImdbNumber().equals(imbdTTnumber)) {
				return f;
			}
		}
		return null;
	}
	
	/**
	 * Methode die een UserToken object maakt
	 * @param user de gebruiker voor wie een token gemaakt wordt
	 * @return het UserToken object
	 */
	public UserToken createUserToken(User user) {
		String token = createToken();
		if (user != null && token != null) {
			UserToken ut = new UserToken(user, token);
			usertokens.add(ut);
			return ut;
		}
		return null;
	}

	/**
	 * Methode die Tokens maakt en in een lijst toevoegt.
	 * @return
	 */
	public String createToken() {
		String token = UUID.randomUUID().toString();

		for (String t : tokens) {
			if (t.equalsIgnoreCase(token)) {
				this.createToken();
			}
		}
		if (!hasToken(token)) {
			tokens.add(token);
			return token;
		}
		return null;
	}
	
	/**
	 * Methode die controlleerd of het token uniek is of niet.
	 * Wordt gebruikt door de createToken() methode
	 * @param token
	 * @return true als het token al bestaat
	 */
	private boolean hasToken(String token) {
		for (String t : tokens) {
			if (t.equals(token)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode die aan de hand van de Nickname en Password kijk of de gebruiker bestaat in het systeem.
	 * @param nickname
	 * @param password
	 * @return true als gebruiker binnen het systeem bestaat.
	 */
	public boolean loginCompare(String nickname, String password) {
		for (User u: users) {
			if (u.getNickname().equals(nickname) && u.getPassword().equals(password)) {
				System.out.println("User " + nickname
						+ " met wachtwoord " + password
						+ " bestaat in het systeem!");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Getter om een user aan de hand van de nickname en wachtwoord op te halen
	 * @param nickname
	 * @param password
	 * @return de user
	 */
	public User getUser(String nickname, String password) {
		for (User u : users) {
			if (u.getNickname().equals(nickname) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Een methode om de user op te halen aan de hand van het usertoken.
	 * @param token de usertoken
	 * @return de user die bij het opgegeven token hoort.
	 */
	public User getUserByToken(String token) {
		for (UserToken t : usertokens) {
			if (t.getToken().equals(token)) {
				return t.getUser();
			}
		}
		return null;
	}
}