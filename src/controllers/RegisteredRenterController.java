package controllers;

import java.util.ArrayList;

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
		super(model,login);
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

		view.addNotificationsListener(e -> view.displayNotificationsPanel(model.getRenterNotifications(username)));
		view.addBackListener(e -> view.displayDashboard());
		view.addContactListener(e -> contactLandlord(this.view, model.getRegisteredRenter(username).getEmail()));
	}

	@Override
	public void propertySearch(RenterView view) {
		int bathrooms;
		int bedrooms;
		// error checking
		try {
			if (view == null) {
				System.out.println("view is null");
			} else if (view.getBathRoomsText() == null) {
				System.out.println("bathrooms is null");
			} else if (view.getBedRoomsText() == null) {
				System.out.println("bedrooms is null");
			}
			bathrooms = Integer.parseInt(view.getBathRoomsText());
			bedrooms = Integer.parseInt(view.getBedRoomsText());

		} catch (NumberFormatException e) {
			view.displayError("Bedroom/bathroom number require a valid integer input");
			return;

		}
		System.out.println("bathrooms " + bathrooms + " bedrooms " + bedrooms);
		if (bathrooms <= 0 || bedrooms <= 0) {
			view.displayError("Bedroom/bathroom number cannot be equal to or less than 0");
			return;

		}
		String propeString = view.getPropertyType();
		if (!isStringAlpha(propeString)) {
			view.displayError("Property type must contain only alphabetical characters");
			return;
		}
		String furString = view.getFurnishingInfo().toLowerCase();
		boolean furBool;
		if (furString.equals("furnished")) {
			furBool = true;
		} else {
			furBool = false;
		}
		String quadString = view.getQuadrant();
		// clear any previous errors
		view.displayError("");
		// this should call a function to match the properties with the search
		// information and return an arraylist of property objects
		ArrayList<Property> properties = model.searchForProperties(bathrooms, bedrooms, propeString, furBool,
				quadString);

		/*
		 * ArrayList<Property> dummy = new ArrayList<Property>();
		 * dummy.add(new Property("Apartment", true, 1, 1, true, 1, "SW", "Active",
		 * "123", 1, 8, "joe", false));
		 * dummy.add(new Property("Apartment", true, 1, 1, true, 79, "NW", "Active",
		 * "123", 1, 8, "joe", false));
		 */

		view.displaySearch(properties);

		if (furString.equals("furnished")) {
			model.subscribeNotification(username, bathrooms, bedrooms, true, quadString, propeString);
		} else {
			model.subscribeNotification(username, bathrooms, bedrooms, false, quadString, propeString);
		}

	}

	/*
	 * public void viewNotifications() {
	 * ArrayList<Property> properties = model.getRenterNotifications(username);
	 * view.displayNotificationsPanel(properties);
	 * 
	 * }
	 */

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
