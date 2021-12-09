package models;

public class RegisteredRenter {
	private String username;
	private String email;
	private int ID;

	private Subscription subscription;

	public RegisteredRenter(String username, String email, int i, models.Subscription subscription2) {
		this.username = username;
		this.email = email;
		this.ID = i;

		this.subscription = subscription2;
	}

	public RegisteredRenter() {
		subscription = null;

	}

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
