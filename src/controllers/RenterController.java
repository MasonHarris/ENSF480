package controllers;

import models.Database;
import views.RenterView;
import java.util.ArrayList;
import java.util.HashMap;

import models.Property;

import models.Property;

public class RenterController extends GUIcontroller {
	private RenterView view;
	//used to return to login
	LoginController login;

	public RenterController(Database model, RenterView view, LoginController login) {
		super(model);
		this.view = view;
		this.login = login;

	}
	public RenterController(Database model, LoginController login) {
		super(model);
		this.login = login;

	}

	public void start() {
		view.addSubmitListener(e -> propertySearch(this.view));
		view.addBackListener(e -> view.displaySearchPanel());
		view.addContactListener(e -> contactLandlord(this.view, ""));
		view.displaySearchPanel();
		view.endSession(e-> {
			view.dispose();
			login.start();
		});

	}

	

	// deals with property search. Takes in view as an argument so that this method
	// can be used with registered renter as well
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
		String propeString = view.getPropertyType().toLowerCase();
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

	}

	public void contactLandlord(RenterView view, String email) {

		// array of propertyIDs contacted
		int[] propertyIDs = view.getSelectedProperties();
		int propertyID = -1;
		System.out.println("hello");
		if (propertyIDs == null) {
			return;
		}
		System.out.println("hello2");
		// the array should really only have 1 value since only 1 property can be
		// selected at a time
		for (int value : propertyIDs) {
			propertyID = value;

		}
		view.displayForm(email);
		final int p = propertyID;
		
		

		view.formListener(e -> {
			sendEmail(p, view.getEmail(),view);
			
		});

	}

	// updates database with email, email[0] is address, email[1] is body

	public void sendEmail(int propertyID, String[] email, RenterView view) {
		if(email[0].length() == 0 || email[1].length() == 0){
			view.displayEmailError("Neither field can be left blank");
			
			return;
		}
		
		System.out.println("Email body is " + email[1]);
	
		model.registerLandlordNotification(model.getProperty(propertyID).getLandlordUsername(), email[0], propertyID);
		view.returnToSearchResults();
		view.confirmation("Email sent");

	}

}
