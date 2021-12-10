package models;

import java.sql.*;
import java.util.AbstractMap;
//this should be a singleton
import java.util.ArrayList;
import java.util.Iterator;
import java.util.AbstractMap.SimpleEntry;

public class Database {
	public static Database onlyInstance;
	private final String URL;
	private final String USERNAME;
	private final String PASSWORD;
	private static Connection connection;

	private Database(String url, String username, String password) {
		URL = url;
		USERNAME = username;
		PASSWORD = password;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	public static Database getOnlyInstance() {
		if (onlyInstance == null) {
			onlyInstance = new Database("jdbc:mysql://localhost:3306/PROPERTY_RENTAL", "root", "password");
		}
		return onlyInstance;
	}
	// checks if given username and password is a valid account
	// returns true if it is
	// else false
	public boolean validateLogin(String user, String password) {
		try {
			String query = "SELECT * FROM ACCOUNT";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				if (res.getString("Username").equals(user) && res.getString("Password").equals(password)) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return false;
		}
	}
	// checks if given username is in the database
	// returns true if it is 
	// else false
	public boolean validateAccount(String user) {
		try {
			String query = "SELECT * FROM ACCOUNT WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return false;
		}
	}
	// Checks the type of user given the username
	// returns Manager if Account is manager
	// returns Landlord if Account is landlord
	// returns RegisteredRenter if Account is Registered Renter
	public String validateAccountType(String user) {
		try {
			String query = "SELECT * FROM LANDLORD";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				if (res.getString("Username").equals(user)) {
					return "Landlord";
				}
			}
			query = "SELECT * FROM MANAGER";
			statement = connection.prepareStatement(query);
			res = statement.executeQuery();
			while (res.next()) {
				if (res.getString("Username").equals(user)) {
					return "Manager";
				}
			}
			return "RegisteredRenter";
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return "RegisteredRenter";
		}
	}
	//helper function that gets ID and Password of a user
	public SimpleEntry<Integer, String> getIDPassword(String user) {
		try {
			String query = "SELECT * FROM ACCOUNT WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet res = statement.executeQuery();
			SimpleEntry<Integer, String> pair = new SimpleEntry<>(0, "");
			while (res.next()) {
				pair = new AbstractMap.SimpleEntry<Integer, String>(res.getInt("ID"), res.getString("Password"));
			}
			return pair;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new SimpleEntry<>(0, "");
		}
	}
	// returns a Landlord object from the Database, given its username
	public Landlord getLandlord(String Username) {
		try {
			String query = "SELECT * FROM LANDLORD WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, Username);
			ResultSet res = statement.executeQuery();
			Landlord landlord = new Landlord();
			while (res.next()) {
				SimpleEntry<Integer, String> pair = getIDPassword(Username);
				landlord = new Landlord(res.getString("Username"), res.getString("emailAddress"), pair.getKey());
			}
			return landlord;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new Landlord();
		}
	}
	// Returns a list of all renters in the Database
	public ArrayList<RegisteredRenter> getAllRenters() {
		try {
			ArrayList<RegisteredRenter> list = new ArrayList<RegisteredRenter>();
			String query = "SELECT * FROM RENTER";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				SimpleEntry<Integer, String> pair = getIDPassword(res.getString("Username"));
				list.add(new RegisteredRenter(res.getString("Username"), res.getString("email"), pair.getKey(),
						getSubscription(res.getString("Username"))));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<RegisteredRenter>();
		}
	}
	// returns list of all landlords in the Database
	public ArrayList<Landlord> getAllLandlords() {
		try {
			ArrayList<Landlord> list = new ArrayList<Landlord>();
			String query = "SELECT * FROM LANDLORD";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				SimpleEntry<Integer, String> pair = getIDPassword(res.getString("Username"));
				list.add(new Landlord(res.getString("Username"), res.getString("emailAddress"), pair.getKey()));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Landlord>();
		}

	}
  // Returns RegisteredRenter object given username
	public RegisteredRenter getRegisteredRenter(String user) {
		try {
			String query = "SELECT * FROM RENTER WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet res = statement.executeQuery();
			RegisteredRenter RegisteredRenter = new RegisteredRenter();
			while (res.next()) {
				SimpleEntry<Integer, String> pair = getIDPassword(user);
				RegisteredRenter = new RegisteredRenter(user, res.getString("email"), pair.getKey(),
						getSubscription(user));
			}
			return RegisteredRenter;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new RegisteredRenter();
		}
	}
	// gets a RegisteredRenter's subscription, given thier username
	public Subscription getSubscription(String user) {
		try {
			String query = "SELECT * FROM NOTIFICATION WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet res = statement.executeQuery();
			Subscription subscription = new Subscription();
			boolean empty = true;
			while (res.next()) {
				empty = false;
				subscription = new Subscription(user, res.getInt("noOfBedrooms"), res.getInt("noOfBathrooms"),
						res.getBoolean("Furnished"), res.getString("cityQuadrant"), res.getString("propertyType"));
			}
			if (empty) {
				return null;
			}
			return subscription;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return null;
		}
	}
	//Returns an arraylist of all properties in the Database 
	public ArrayList<Property> getAllProperties() {
		try {
			ArrayList<Property> list = new ArrayList<Property>();
			String query = "SELECT * FROM PROPERTY";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				list.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"),
						res.getInt("noOfBedrooms"),
						res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"),
						res.getString("cityQuadrant"),
						res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"),
						res.getDouble("amountofFee"),
						res.getString("landlordUsername"), res.getBoolean("isPaid")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}
	}
	// returns a Property object from the Database, given its property id
	public Property getProperty(int property_id) {
		try {
			String query = "SELECT * FROM PROPERTY WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, property_id);
			ResultSet res = statement.executeQuery();
			Property property = new Property();
			while (res.next()) {
				property = new Property(res.getString("propertyType"), res.getBoolean("isListed"),
						res.getInt("noOfBedrooms"),
						res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"),
						res.getString("cityQuadrant"),
						res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"),
						res.getDouble("amountofFee"),
						res.getString("landlordUsername"), res.getBoolean("isPaid"));
			}
			return property;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new Property();
		}
	}
	//searches for properties with given search filters
	//returns a list of Property objects that fit the search filter
	public ArrayList<Property> searchForProperties(int bath, int bed, String prope, boolean fur, String quad) {
		try {
			ArrayList<Property> list = new ArrayList<Property>();
			String query = "SELECT * FROM PROPERTY WHERE isListed = 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				list.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"),
						res.getInt("noOfBedrooms"),
						res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"),
						res.getString("cityQuadrant"),
						res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"),
						res.getDouble("amountofFee"),
						res.getString("landlordUsername"), res.getBoolean("isPaid")));
			}
			Iterator<Property> it = list.iterator();
			while (it.hasNext()) {
				Property property = it.next();
				if (property.getNumOfBath() != bath) {
					System.out.println(bath + " is not equal to " + property.getNumOfBath());
					it.remove();
				} else if (property.getNumOfBed() != bed) {
					System.out.println(bed + " is not equal to " + property.getNumOfBed());
					it.remove();
				} else if (!property.getPropertyType().equals(prope)) {
					System.out.println(prope + " is not equal to " + property.getPropertyType());
					it.remove();
				} else if (property.getIsFurnished() != fur) {
					System.out.println(fur + " is not equal to " + property.getIsFurnished());
					it.remove();
				} else if (!property.getCityQuadrant().equals(quad)) {
					System.out.println(quad + " is not equal to " + property.getCityQuadrant());
					it.remove();
				}
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e + " search error");
			System.exit(0);
			return new ArrayList<Property>();
		}
	}
	// returns an arraylist of a landlord properties, given its username
	public ArrayList<Property> getLandlordProperties(String username) {
		try {
			ArrayList<Property> list = new ArrayList<Property>();
			String query = "SELECT * FROM PROPERTY WHERE landlordUsername = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				list.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"),
						res.getInt("noOfBedrooms"),
						res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"),
						res.getString("cityQuadrant"),
						res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"),
						res.getDouble("amountofFee"),
						res.getString("landlordUsername"), res.getBoolean("isPaid")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}
	}
	public void registerProperty(Property p) {
		try {
			String query = "INSERT INTO PROPERTY(propertyType, propertyID, isListed, noOfBedrooms, noOfBathrooms, Furnished, cityQuadrant, listingPeriod, landlordUsername, listingState, amountofFee, address, isPaid)";
			query = query + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, p.getPropertyType());
			statement.setInt(2, p.getPropertyId());
			statement.setBoolean(3, p.isIsListed());
			statement.setInt(4, p.getNumOfBed());
			statement.setInt(5, p.getNumOfBath());
			statement.setBoolean(6, p.isIsFurnished());
			statement.setString(7, p.getCityQuadrant());
			statement.setInt(8, p.getListingPeriod());
			statement.setString(9, p.getLandlordUsername());
			statement.setString(10, p.getPropertyStatus());
			statement.setDouble(11, p.getAmountOfFee());
			statement.setString(12, p.getAddress());
			statement.setBoolean(13, p.getisPaid());
			statement.executeUpdate();
			// notify applicable renters

			String query2 = "SELECT * FROM NOTIFICATION WHERE noOfBathrooms = ? AND noOfBedrooms = ? AND FURNISHED = ? AND cityQuadrant = ? AND propertyType = ?";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setInt(1, p.getNumOfBath());
			statement2.setInt(2, p.getNumOfBed());
			statement2.setBoolean(3, p.getIsFurnished());
			statement2.setString(4, p.getCityQuadrant());
			statement2.setString(5, p.getPropertyType());
			ResultSet res = statement2.executeQuery();
			while (res.next()) {
				String update = "INSERT INTO NOTIFICATION_RENTER(Username, property_id)Values(?,?)";
				PreparedStatement statement3 = connection.prepareStatement(update);
				statement3.setString(1, res.getString("Username"));
				statement3.setInt(2, p.getPropertyId());
				statement3.executeUpdate();

			}
				
		} catch (SQLException e) {
			System.out.println(e + "happened here ");
			System.exit(0);
		}
	}

	// used for getting a unique property id
	public int getRegisterPropertyID() {
		try {
			String query = "SELECT MAX(propertyID) FROM PROPERTY";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			int result = 0;
			while (res.next()) {
				result = res.getInt("MAX(propertyID)");
			}
			return result + 1;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return -1;
		}
	}

	public int getManagerID(){
		try {
			String query = "SELECT MAX(id) FROM Manager";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			int result = 0;
			while (res.next()) {
				result = res.getInt("MAX(id)");
			}
			return result + 1;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return -1;
		}


	}
	//changes a property listing given property id and string of new listing
	public void changePropertyListing(int id, String listing) {
		try {
			String query = "UPDATE PROPERTY SET listingState = ? WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, listing);
			statement.setInt(2, id);
			statement.executeUpdate();
			//changes isListed based on state change
			if (listing.equals("Active")) {
				String query2 = "UPDATE PROPERTY SET isListed = 1 WHERE propertyID = ?";
				PreparedStatement statement2 = connection.prepareStatement(query2);
				statement2.setInt(1, id);
				statement2.executeUpdate();

			}

			else {
				String query2 = "UPDATE PROPERTY SET isListed = 0 WHERE propertyID = ?";
				PreparedStatement statement2 = connection.prepareStatement(query2);
				statement2.setInt(1, id);
				statement2.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	//Sets fee of all properties in Database
	public void setFee(double feeAmount) {
		try {
			String query = "UPDATE PROPERTY SET amountofFee = ? WHERE isPaid = 0";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, feeAmount);
			statement.executeUpdate(); 
			query = "UPDATE PERIOD_FEE SET fee = ?";
			statement = connection.prepareStatement(query);
			statement.setDouble(1, feeAmount);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	//sets period of all properties in Database
	public void setPeriod(int periodAmount) {
		try {
			String query = "UPDATE PERIOD_FEE SET startListingPeriod = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, periodAmount);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	// pays Fee of property, given property_id
	public void payFee(int propety_id) {
		try {
			String query = "UPDATE PROPERTY SET isPaid = 1 WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, propety_id);
			String query2 = "UPDATE PROPERTY SET isListed = 1 WHERE propertyID = ?";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setInt(1, propety_id);
			statement.executeUpdate();
			statement2.executeUpdate();
			//start listing period
			String query3 = "UPDATE PROPERTY SET listingPeriod = ? WHERE propertyID = ?";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setInt(1, getListingPeriodFee().getKey());
			statement3.setInt(2, propety_id);
			statement.executeUpdate();
			statement3.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	// registers user into ACCOUNT table in database
	public void registerUser(String user, String password, String email, String userType) {
		try {
			String query = "INSERT INTO ACCOUNT(Username, Password) Values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setString(2, password);
			statement.executeUpdate();
			if(userType.equals("Renter")){
				query = "INSERT INTO RENTER(Username, email)Value(?,?)";
				statement = connection.prepareStatement(query);
				statement.setString(1, user);
			    statement.setString(2, email);
				statement.executeUpdate();

			}
			else if(userType.equals("Landlord")){
				query = "INSERT INTO LANDLORD(Username, name, emailAddress)Value(?,?,?)";
				PreparedStatement statement2 = connection.prepareStatement(query);
				statement2.setString(1, user);
			    statement2.setString(2, user);
				statement2.setString(3,email);
				statement2.executeUpdate();

			}
			else if(userType.equals("Manager")){
				query = "INSERT INTO MANAGER(id,Username)Value(?,?)";
				PreparedStatement statement2 = connection.prepareStatement(query);
				statement2.setInt(1,getManagerID());
				statement2.setString(2, user);
				statement2.executeUpdate();

			

			}
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	//Registers RegisteredRenter into Notification Table in Database when they subscribe
	// given username of RegisteredRenter and property attributes noOfBath, noOfBed, Furnished, cityQuadrant and propertyType
	public void subscribeNotification(String user, int noOfBath, int noOfBed, Boolean Furnished, String cityQuadString,
			String propertyType) {
		try {
			//removes previous subscription
			unsubscribeNotification(user);
			String query = "INSERT INTO NOTIFICATION(Username, noOfBedrooms, noOfBathrooms, Furnished, cityQuadrant, propertyType)";
			query = query + "Values(?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setInt(2, noOfBath);
			statement.setInt(3, noOfBed);
			statement.setBoolean(4, Furnished);
			statement.setString(5, cityQuadString);
			statement.setString(6, propertyType);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	//removes RegisteredRenter from Notification Table when they want to subscribe
	// given username of RegisteredRenter
	public void unsubscribeNotification(String user) {
		try {
			String query = "DELETE FROM NOTIFICATION WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.executeUpdate();
			query = "DELETE FROM NOTIFICATION_RENTER WHERE Username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	// checks if a property's attributes meet a registeredrenter's subscription
	// given username of RegisteredRenter
	// returns a list of usernames of RegisteredRenter that meet thier subscription
	public ArrayList<String> checkNotification(String user, int noOfBath, int noOfBed, Boolean Furnished,
			String cityQuadString, String propertyType) {
		try {
			String query = "SELECT * FROM NOTIFICATION";
			ArrayList<String> users = new ArrayList<String>();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				if ((res.getInt("noOfBathrooms") == noOfBath) && (res.getInt("noOfBedrooms") == noOfBed)
						&& (res.getBoolean("Furnished") == Furnished)
						&& (res.getString("cityQuadrant").equals(cityQuadString))
						&& (res.getString("propertyType").equals(propertyType))) {
					users.add(res.getString("Username"));
				}
			}
			return users;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<String>();
		}
	}
	// gets a list of properties from the renter's notifications
	// given renter's username
	public ArrayList<Property> getRenterNotifications(String username) {
		try {
			String query = "SELECT * FROM NOTIFICATION_RENTER WHERE Username = ?";
			ArrayList<Property> properties = new ArrayList<Property>();
			ArrayList<Integer> property_ids = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				property_ids.add(res.getInt("property_id"));
			}
			for (int i = 0; i < property_ids.size(); i++) {
				query = "SELECT * FROM PROPERTY WHERE propertyID = ? AND isListed = 1";
				statement = connection.prepareStatement(query);
				statement.setInt(1, property_ids.get(i));
				res = statement.executeQuery();
				while (res.next()) {
					properties.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"),
							res.getInt("noOfBedrooms"),
							res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"),
							res.getString("cityQuadrant"),
							res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"),
							res.getDouble("amountofFee"),
							res.getString("landlordUsername"), res.getBoolean("isPaid")));
				}
			}
			return properties;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}

	}
	//register a notification to a registeredrenter about a property that matches their subscription
	// given registered renter username and property id
	public void registerPropertyNotification(String user, int property_id) {
		try {
			String query = "INSERT INTO NOTIFICATION_RENTER(Username, property_id) Values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setInt(2, property_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	//registers a notification to landlord given landlord username, renter email and property id
	public void registerLandlordNotification(String land_user, String renter_email, int property_id) {
		try {
			String query = "INSERT INTO NOTIFICATION_LANDLORD(Username, renter_email, property_id) Values(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, land_user);
			statement.setString(2, renter_email);
			statement.setInt(3, property_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	// get landlordnotifications given a landlord username, 
	// returns arraylist string array = { property id, renter email , address}
	public ArrayList<String[]> getLandlordNotification(String user) {
		try {
			String query = "SELECT * FROM NOTIFICATION_LANDLORD WHERE Username = ?";
			ArrayList<String[]> arr = new ArrayList<String[]>();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				int id = res.getInt("property_id");
				
				arr.add(new String[]{Integer.toString(id),res.getString("renter_email"),getProperty(id).getAddress()});
			}
			return arr;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<String[]> ();
		}
	}
	// returns a SimpleEntry(Pair) of the current Listing period and current fees for all properties
	// SimpleEntry<listingPeriod, fee>
	public SimpleEntry<Integer, Double> getListingPeriodFee(){
		try {
			String query = "SELECT * FROM PERIOD_FEE";
			SimpleEntry<Integer, Double> pair = new SimpleEntry<>(0, 0.0);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				pair = new SimpleEntry<Integer, Double>(res.getInt("startListingPeriod"), res.getDouble("fee"));
			}
			return pair;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new SimpleEntry<Integer, Double>(0, 0.0);
		}
	}

}
