package controllers;

import models.Database;
import views.RegisteredRenterView;
import views.RenterView;
import models.Property;

public class RegisteredRenterController extends RenterController {
	private RegisteredRenterView view;
	private LoginController login;
	private String username;

	public RegisteredRenterController(Database model, RegisteredRenterView view, LoginController login,
			String username) {
		super(model);
		this.view = view;

		this.login = login;
		this.username = username;
		// super.view = view;

	}

	@Override
	public void start() {
		view.displayDashboard();
		view.addSubmitListener(e -> propertySearch(view));
		// logouts by destroying current user frame and recreating intial login frame
		view.addLogoutListener(e -> {
			view.dispose();
			// System.out.println("hi");

			login.start();
		});
		view.addSearchListener(e -> view.displaySearchPanel());
		view.addUnsubscribeListener(e -> unsubscribeRenter());
		// someone should add a function to get all the properties the renter was
		// notified of as an arraylist of property
		// then put that arraylist as the argument of view.displayNotificationsPanel()
		// view.addNotificationsListener(e -> view.displayNotificationsPanel());
		view.addBackListener(e -> view.displayDashboard());
		view.addContactListener(e -> contactLandlord(this.view));
	}

	@Override
	public void propertySearch(RenterView view) {
		super.propertySearch(view);
		// code to subscribe renter to database

	}

	public void viewNotifications() {
		// gets notifications from database(as arraylist of properties)

	}

	public void subscribeRenter() {
	}

	public void unsubscribeRenter() {
		// code to unsubscribe renter
		// if renter unsubscribed
		view.confirmation("You have been unsubscribed");
		model.unsubscribeNotification(username);

	}

	public RegisteredRenterView getView() {
		return this.view;
	}

	public void setView(RegisteredRenterView view) {
		this.view = view;
	}

	public LoginController getLogin() {
		return this.login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



}
