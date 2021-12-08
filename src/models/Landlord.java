package models;

import java.util.ArrayList;

public class Landlord {
	public String username;
	public String name;
	public String email;


	public Landlord(String username, String name, String address) {
		this.username = username;
		this.name = name;
		this.email = address;
	}

	public Landlord() {
	}

	public void RegisterProperty(Property newProperty) {
		
	}
	public void changeStateOfListing(int propertyID, String newStatus) {
		
	}
}
