package models;

public class RegisteredRenter extends Renter {
	private String username;

	private int ID;
	private String password;

	public RegisteredRenter(String username, String password, int id) {
		this.username = username;
		this.ID = id;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getID() {
		return this.ID;
	}

	public void setId(int ID) {
		this.ID = ID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {

	}

	public void notified() {

	}

	public void unsubscribe() {

	}
}
