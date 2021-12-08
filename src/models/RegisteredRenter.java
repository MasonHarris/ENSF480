package models;

public class RegisteredRenter extends Renter {
	private String username;
	private String email;
	private int ID;
	private String password;

	public RegisteredRenter(String username, String email, String password, int id) {
		this.username = username;
		this.email = email;
		this.ID = id;
		this.password = password;
	}

	public RegisteredRenter() {

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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void login() {

	}

	public void notified() {

	}

	public void unsubscribe() {

	}
}
