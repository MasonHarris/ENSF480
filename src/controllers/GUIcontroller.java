package controllers;

import models.Database;

public abstract class GUIcontroller {
	Database model;

	public GUIcontroller(Database model) {
		this.model = model;
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

}
