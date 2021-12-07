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
		try{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public static Database getOnlyInstance() {
		if(onlyInstance == null) {
			onlyInstance = new Database("jdbc:mysql://localhost:3306/PROPERTY_RENTAL", "root", "password");
		}
		return onlyInstance;
	}
	public boolean validateLogin(String user, String password){
		try{
			String query = "SELECT * FROM ACCOUNT";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				if(res.getString("Username").equals(user) && res.getString("Password").equals(password)){
					return true;
				}
			}
			return false;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return false;
		}
	}
	public String validateAccountType(String user){
		try{
			String query = "SELECT * FROM LANDLORD";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				if(res.getString("Username").equals(user)){
					return "Landlord";
				}
			}
			query = "SELECT * FROM MANAGER";
			statement = connection.prepareStatement(query);
			res = statement.executeQuery();
			while(res.next()){
				if(res.getString("Username").equals(user)){
					return "Manager";
				}
			}
			return "Renter";
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return "Renter";
		}
	}
	public ArrayList<Property> getAllProperties(){
		try{
			ArrayList<Property> list = new ArrayList<Property>();
			String query = "SELECT * FROM PROPERTY";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				list.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"), res.getInt("noOfBedrooms"), 
				res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"), res.getString("cityQuadrant"), 
				res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"), res.getDouble("amountofFee"), 
				res.getString("landlordUsername"), res.getBoolean("isPaid")));
			}
			return list;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}
	}
	public ArrayList<Property> getLandlordProperties(String username){
		try{
			ArrayList<Property> list = new ArrayList<Property>();
			String query = "SELECT * FROM PROPERTY WHERE landlordUsername = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				list.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"), res.getInt("noOfBedrooms"), 
				res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"), res.getString("cityQuadrant"), 
				res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"), res.getDouble("amountofFee"), 
				res.getString("landlordUsername"), res.getBoolean("isPaid")));
			}
			return list;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}
	}
	public void registerProperty(Property p){
		try{
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
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public void changePropertyListing(int id, String listing){
		try{
			String query = "UPDATE PROPERTY SET listingState = ? WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, listing);
			statement.setInt(2, id);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public void registerUser(String user, String password){
		try{
			String query = "INSERT INTO ACCOUNT(Username, Password) Values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setString(2, password);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public void subscribeNotification(String user, int noOfBath, int noOfBed, Boolean Furnished, String cityQuadString, String propertyType){
		try{
			String query = "INSERT INTO PROPERTY(username, noOfBedrooms, noOfBathrooms, Furnished, cityQuadrant, propertyType)";
			query = query + "Values(?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setInt(2, noOfBath);
			statement.setInt(3, noOfBed);
			statement.setBoolean(4, Furnished);
			statement.setString(5, cityQuadString);
			statement.setString(6, propertyType);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public void unsubscribeNotification(String user){
		try{
			String query = "DELETE FROM NOTIFICATION WHERE Username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public ArrayList<String> checkNotification(String user, int noOfBath, int noOfBed, Boolean Furnished, String cityQuadString, String propertyType){
		try{
			String query = "SELECT * FROM NOTIFICATION";
			ArrayList<String> users = new ArrayList<String>();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				if((res.getInt("noOfBathrooms") == noOfBath) &&(res.getInt("noOfBedrooms") == noOfBed) && (res.getBoolean("Furnished") == Furnished)
				&& (res.getString("cityQuadrant").equals(cityQuadString))&& (res.getString("propertyType").equals(propertyType))){
					users.add(res.getString("username"));
				}
			}
			return users;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return new ArrayList<String>();
		}
	}
	//the search property method should return an arraylist of property objects
}
