package controllers;

import models.Database;
import views.RenterView;
import java.util.ArrayList;
import java.util.HashMap;

import models.Property;
import java.util.Map;
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
		view.displaySearchPanel();
		view.formListener(e -> {
			sendEmail();

		});

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
		String quadString = view.getQuadrant();
		// clear any previous errors
		view.displayError("");
		// this should call a function to match the properties with the search
		// information and return an arraylist of property objects
		ArrayList<Property> properties = model.getAllProperties(); // change this later
		view.displaySearch(properties);

	}

	// checks if a string only contains alphabetical characters
	public boolean isStringAlpha(String check) {
		// deals with empty and null strings
		if (check == null) {
			return false;
		} else if (check.length() == 0) {
			return false;
		}
		// iterates each over each character in string to check it's an alphabetical
		// letter
		for (int i = 0; i < check.length(); i++) {
			if (!Character.isLetter(check.charAt(i))) {
				System.out.println("This is not a letter " + (int) check.charAt(i));
				return false;
			}
		}
		return true;

	}

	public void contactLandlord() {
		// int is entry in list,String is property id
		HashMap<Integer, String> map = view.getSelectedProperties();
		if (map == null) {
			return;
		}
		for (var pair : map.entrySet()) {
			// gets landlord information from database
			// then creates contact form

		}
<<<<<<< Updated upstream
=======
		view.displayForm();

	}

	// updates database with email

	public void sendEmail() {
		// first value in array is email address, second value is email text
		String[] values = view.getEmail();
		if (values[0].length() == 0 || values[1].length() == 0) {
			view.displayEmailError("Neither field can be left blank");
			return;
		}
		view.displaySearchPanel();
>>>>>>> Stashed changes

	}

}
