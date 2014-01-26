package model;

import java.util.ArrayList;

/**
 * Het model
 * @author Maickel / Sander
 *
 */
public class Model {

	private ArrayList<User> users;
	private ArrayList<Room> rooms;
	
	/**
	 * Constructor voor het model
	 */
	public Model() {
		// Standaard gebruikers
		users = new ArrayList<User>();
		users.add( new User(1, "yolo", "swag", "huurder")); 
		users.add( new User(2, "swag", "yolo", "verhuurder"));
		users.add( new User(3, "admin", "admin", "admin"));
		//Standaard kamers
		rooms = new ArrayList<Room>();
		rooms.add(new Room(1, "Verenigde Staten", "New York", "mainstreet 555", 55, 200, 2));
		rooms.add(new Room(2, "Nederland", "Amsterdam", "Straatnaam 322", 63, 1000, 2));
	}
	
	/**
	 * Getter die een lijst met gebruiker teruggeeft
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Methode om te kijken of er een gebruiker bestaat met de opgegeven gebruikersnaam 
	 * @param username
	 * @return
	 */
	public boolean userExists(String username) {
		for(User u : users)
		{
			if(u.getUsername().equals(username))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Methode om een user toe te voegen aan het systeem. Ook wordt er gekeken of de 
	 * gebruikersnaam en het wachtwoord goed zijn (Wel iets ingevuld? Bestaat het al? etc..)
	 * Alle fouten worden in een arraylist gezet en die lijst wordt teruggestuurd.
	 * @param username
	 * @param password
	 * @param password2
	 * @param type
	 * @return Lijst met errors
	 */
	public ArrayList<String> addUser(String username, String password, String password2, String type) {
		ArrayList<String> errors = new ArrayList<String>();
		
		/**
		 * Hier wordt gekeken of gebruikersnaam goed is
		 */
		if(username.isEmpty()) {
			errors.add("Geen gebruikersnaam ingevoerd");
		} else if(username.length() < 4) {
			errors.add("Gebruikersnaam moet langer zijn dan 4 tekens");
		} else if(userExists(username)) {
			errors.add("Gebruikersnaam bestaat al");
		}
		
		/**
		 * Hier wordt gekeken of het wachtwoord goed is
		 */
		if (password.isEmpty()) {
			errors.add("Geen wachtwoord ingevoerd!");
		} else if (password.length() < 4) {
			errors.add("Wachtwoord moet langer zijn dan 4 tekens!");			
		} else if (!password.equals(password2)) {
			errors.add("Wachtwoorden komen niet overeen!");	
		}
		
		/**
		 * Als er geen fouten zijn opgetreden, wordt de gebruiker toegevoegd.
		 */
		if (errors.size() == 0) {
			users.add( new User(getUsers().size() + 1, username, password, type));
			System.out.println("Gebruiker toegevoegd!");	
		}
		return errors;
	}
	
	/**
	 * Getter voor alle kamers
	 * @return
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * Methode om te kijken of het adres al bestaat zodat een gebruiker niet twee keer dezelfde kamer kan aanbieden.
	 * @param address
	 * @return
	 */
	public boolean addressExists(String address) {
		for(Room r : rooms) {
			if(r.getAdres().equals(address)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Methode om een kamer toe te voegen. ook hier wordt er gekeken of de kamer aan alle eisen voldoet.
	 * Als er iets fout is, dan wordt er een arraylist met errors teruggestuurd.
	 * @param country
	 * @param city
	 * @param address
	 * @param price
	 * @param area
	 * @return ArrayList met errors
	 */
	public ArrayList<String> addRoom(String land, String stad, String adres, int prijs, int oppervlak, int id) {
		ArrayList<String> errors = new ArrayList<String>();
		
		System.out.println(land + stad + adres + prijs + oppervlak);
		
		if (land.isEmpty()) {
			errors.add("Geen land ingevoerd");
		}
		if (stad.isEmpty()) {
			errors.add("Geen stad ingevoerd");
		}
		if (adres.isEmpty()) {
			errors.add("Geen adres ingevoerd");
		}
		if (addressExists(adres)) {
			errors.add("Er staat al een huis op dat adres te huur");
		}
		if (prijs <= 0) {
			errors.add("Prijs te laag");
		}
		if (oppervlak <= 0) {
			errors.add("Oppervlakte te klein");
		}
		if(errors.size() == 0) {
			rooms.add(new Room(getRooms().size() + 1, land, stad, adres, prijs, oppervlak, id));
			System.out.println("Kamer toegevoegd");
		}
		
		return errors;
	}

	
}
