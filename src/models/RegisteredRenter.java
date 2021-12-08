package models;

import java.util.concurrent.Flow.Subscription;

public class RegisteredRenter {
	private String username;
	private String email;
	private int ID;
	private String password;
	private Subscription subscription;

	public RegisteredRenter(String username, String email, String password, int id, Subscription subscription) {
		this.username = username;
		this.email = email;
		this.ID = id;
		this.password = password;
		this.subscription = subscription;
	}

<<<<<<< HEAD
	public RegisteredRenter(String string, String string2) {
=======
	public RegisteredRenter() {
		subscription = null;

	}

	public RegisteredRenter(String user, String string, String value, Integer key, models.Subscription subscription2) {
	}
>>>>>>> 912c729f65d0dcf88f7cdf96d83d1700f82b7b6e

	public RegisteredRenter(String user, String email) {
		this.username = user;
		this.email = email;
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

	public void setSubscription(Subscription s) {
		this.subscription = s;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void login() {

	}

	public void notified() {

	}

	public void unsubscribe() {

	}
}
