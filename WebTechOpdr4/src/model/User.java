package model;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	@Context ServletContext context;
	
	private String surname;
	private String tussenvoegsel;
	private String firstname;
	private String nickname;
	private String password;
	private int id;
	
	/**
	 * omdat Jersey
	 */
	public User() { }
	
	/**
	 * Constructor om een User object aan te maken
	 * @param id
	 * @param surname
	 * @param firstname
	 * @param nickname
	 * @param password
	 */
	public User(int id, String surname, String firstname, String tussenvoegsel, String nickname, String password) {
		this.id = id;
		this.surname = surname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.password = password;
		this.tussenvoegsel = tussenvoegsel;
	}
	
	/**
	 * getter voor de achternaam
	 * @return de achternaam
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * getter voor het tussenvoegsel
	 * @return het tussenvoegsel
	 */
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	
	/**
	 * getter voor de voornaam
	 * @return de voornaam
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * getter voor de nickname
	 * @return de nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * getter voor het id
	 * @return het id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * setter voor het id
	 * @param id 
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * getter voor het wachtwoord
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * to string methode van de user
	 */
	public String toString() {
		String user = id + ": " + firstname + " ";
		if (tussenvoegsel != null || !tussenvoegsel.equals("")) {
			return user + tussenvoegsel + " " + surname + ", " + nickname;
		}
		return user + " " + surname + ", " + nickname; 
	}
}
