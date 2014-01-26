package model;

/**
 * De user class
 * @author Sander / Maickel
 *
 */
public class User {

	private int id;
	private String username;
	private String password;
	private String type; //Huurder of verhuurder
	
	public User(int id, String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return getUsername();
	}
}
