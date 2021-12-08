package controllers;

import models.Database;
import views.RenterView;
import java.util.ArrayList;
import java.util.HashMap;

import models.Property;

import models.Property;

public class RenterController extends GUIcontroller {
	private RenterView view;

	public RenterController(Database model, RenterView view) {
		super(model);
		this.view = view;

	}

	public void start() {
		view.addSubmitListener(e -> propertySearch(this.view));
		view.addBackListener(e -> view.displaySearchPanel());
		view.addContactListener(e -> contactLandlord(this.view));
		view.displaySearchPanel();

	}

	public RenterController(Database model) {
		super(model);

	}

	// deals with property search. Takes in view as an argument so that this method
	// can be used with registered renter as well
	public void propertySearch(RenterView view) {
		int bathrooms;
		int bedRooms;
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
			bedRooms = Integer.parseInt(view.getBedRoomsText());

		} catch (NumberFormatException e) {
			view.displayError("Bedroom/bathroom number require a valid integer input");
			return;

		}
		System.out.println("bathrooms " + bathrooms + " bedrooms " + bedRooms);
		if (bathrooms <= 0 || bedRooms <= 0) {
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
		ArrayList<Property> properties = model.searchForProperties(bathrooms,bedrooms,propeString,furBool,quadString);

		/*ArrayList<Property> dummy = new ArrayList<Property>();
		dummy.add(new Property("Apartment", true, 1, 1, true, 1, "SW", "Active",
				"123", 1, 8, "joe", false));
		dummy.add(new Property("Apartment", true, 1, 1, true, 79, "NW", "Active",
				"123", 1, 8, "joe", false));*/

		view.displaySearch(properties);

	}

	public void contactLandlord(RenterView view) {

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
		view.displayForm();
		final int p = propertyID;
		view.formListener(e -> {
			sendEmail(p, view.getEmail());
			view.returnToSearchResults();
		});

	}

	// updates database with email

	public void sendEmail(int propertyID, String email) {
		System.out.println("PropertyID " + propertyID + " got an email from " + email);

	}

}
