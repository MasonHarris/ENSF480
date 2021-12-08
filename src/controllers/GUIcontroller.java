package controllers;

import models.Database;

public abstract class GUIcontroller {
	Database model;

	public GUIcontroller(Database model) {
		this.model = model;
	}

	// checks if a string only contains alphabetical characters(max 1 space not in
	// start or end)
	public boolean isStringAlpha(String check) {
		// deals with empty and null strings
		if (check == null) {
			return false;
		} else if (check.length() == 0) {
			return false;
		}
		if (check.charAt(0) == ' ' || check.charAt(check.length() - 1) == ' ') {
			return false;
		}
		int spaceCounter = 0;
		// iterates each over each character in string to check it's an alphabetical
		// letter
		for (int i = 0; i < check.length(); i++) {
			if (check.charAt(i) == ' ') {
				spaceCounter++;
				if (spaceCounter > 1) {
					System.out.println(
							"This is not a letter " + (int) check.charAt(i) + " space counter " + spaceCounter);
					return false;
				}
			} else if (!Character.isLetter(check.charAt(i))) {
				System.out.println("This is not a letter " + (int) check.charAt(i) + " space counter " + spaceCounter);
				return false;

			}
		}
		return true;

	}

}
