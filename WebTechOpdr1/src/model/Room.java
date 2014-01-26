package model;

/**
 * De kamer class
 * @author Sander / Maickel
 *
 */
public class Room {

	private int id;
	private String land;
	private String stad;
	private String adres;
	private int prijs;
	private int oppervlak;
	private int userId;

	public Room(int id, String land, String stad, String adres, int prijs, int oppervlak, int userId) {
		this.id = id;
		this.land = land;
		this.stad = stad;
		this.adres = adres;
		this.prijs = prijs;
		this.oppervlak = oppervlak;
		this.userId = userId;
	}

	public String getLand() {
		return land;
	}

	public String getStad() {
		return stad;
	}

	public String getAdres() {
		return adres;
	}

	public int getPrijs() {
		return prijs;
	}

	public int getOppervlak() {
		return oppervlak;
	}

	public int getUserId() {
		return userId;
	}
	
	public String toString() {
		return "Land: " + land + " Stad: " + stad + " Adres: " + adres + "\n" +
				"Prijs: " + prijs + " Oppervlak: " + oppervlak;
	}
}
