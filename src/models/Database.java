package models;

import java.sql.*;
//this should be a singleton
import java.util.ArrayList;

public class Database {
	public static Database onlyInstance;
	private final String URL;
	private final String USERNAME;
	private final String PASSWORD;
	private Connection connection;

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
			return "Renter";
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return "Renter";
		}
	}

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
						res.getString("landlordUsername")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}
	}

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
						res.getString("landlordUsername")));
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
			String query = "INSERT INTO PROPERTY(propertyType, propertyID, isListed, noOfBedrooms, noOfBathrooms, Furnished, cityQuadrant, listingPeriod, landlordUsername, listingState, amountofFee, address)";
			query = query + "Values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, p.getPropertyType());
			statement.setBoolean(2, p.isIsListed());
			statement.setInt(3, p.getNumOfBed());
			statement.setInt(4, p.getNumOfBath());
			statement.setBoolean(5, p.isIsFurnished());
			statement.setString(6, p.getCityQuadrant());
			statement.setInt(7, p.getListingPeriod());
			statement.setString(8, p.getLandlordUsername());
			statement.setString(9, p.getPropertyStatus());
			statement.setDouble(10, p.getAmountOfFee());
			statement.setString(11, p.getAddress());
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
			System.out.println(e);
			System.exit(0);
		}
	}

	public void changePropertyListing(int id, String listing) {
		try {

			//	PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, listing);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void registerUser(String user, String password){
		try{
		

	
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setString(2, password);
			statement.executeUpdate();
		}
	}

	public void subscribeNotification(String user, int noOfBath, int noOfBed, Boolean Furnished, String cityQuadString,
			String propertyType) {
		try {
			// deletes old notification if any
			unsubscribeNotification(user);
			String query = "INSERT INTO NOTIFICATION(Username, noOfBedrooms, noOfBathrooms, Furnished, cityQuadrant, propertyType)";
			query = query + "Values(?,?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			System.out.println("hello adding notification");
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

	public void unsubscribeNotification(String user){
		

			PreparedStatement statement =

	connection.prepa reStatement(query);
			st atement.setString(1, user);
		

		}

Syst em.exit(0);

the search
prop
