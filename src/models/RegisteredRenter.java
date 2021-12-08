package models;

public class RegisteredRenter extends Renter {
	private String username;

	private int id;
	private String password;

	public RegisteredRenter(String username, String password, int id) {
		this.username = username;
		this.id = id;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
