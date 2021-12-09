package models;


public class Landlord {
	private String username;
	private int ID;
	private String emailAddress;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public Landlord() {
	}

	public Landlord(String username, String emailAddress, int ID) {
		this.username = username;
		this.ID = ID;
		this.emailAddress = emailAddress;

	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void RegisterProperty(Property newProperty) {

	}

	public void changeStateOfListing(int propertyID, String newStatus) {

	}
}
