package models;

public class RegisteredRenter extends Renter{
	private String username;
	private String email;

	public RegisteredRenter(String user, String email){
		this.username = user;
		this.email = email;
	}
	public RegisteredRenter(){
		
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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
