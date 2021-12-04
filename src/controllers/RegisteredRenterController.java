package controllers;

import models.Database;
import views.RegisteredRenterView;

public class RegisteredRenterController extends RenterController {
	private RegisteredRenterView view;
	private LoginController login;

	public RegisteredRenterController(Database model, RegisteredRenterView view, LoginController login) {
		super(model);
		this.view = view;
		this.login = login;
		// super.view = view;

	}

	@Override
	public void start() {
		view.displayDashboard();
		view.addSubmitListener(e -> propertySearch(view));
		view.addLogoutListener(e -> {
			view.dispose();
			// System.out.println("hi");

			login.start();
		});
		view.addSearchListener(e -> view.displaySearchPanel());
		view.addUnsubscribeListener(e -> unsubscribeRenter());
		view.addNotificationsListener(e -> view.displayNotificationsPanel());
	}

	public void unsubscribeRenter() {
		// code to unsubscribe renter
		// if renter unsubscribed
		view.unsubscribeConfirmation();

	}

}
