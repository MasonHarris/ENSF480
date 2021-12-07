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
	public Property getProperty(int property_id){
		try{
			String query = "SELECT * FROM PROPERTY WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, property_id);
			ResultSet res = statement.executeQuery();
			Property property = new Property();
			while(res.next()){
				property = new Property(res.getString("propertyType"), res.getBoolean("isListed"), res.getInt("noOfBedrooms"), 
				res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"), res.getString("cityQuadrant"), 
				res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"), res.getDouble("amountofFee"), 
				res.getString("landlordUsername"), res.getBoolean("isPaid"));
			}
			return property;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return new Property();
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
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	// used for getting a unique property id
	public int getRegisterPropertyID(){
		try{
			String query = "SELECT MAX(propertyID) FROM PROPERTY";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			int result = 0;
			while(res.next()){
				result = res.getInt("MAX(propertyID)");
			}
			return result + 1;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return -1;
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
	public void setFee(double feeAmount, int propety_id){
		try{
			String query = "UPDATE PROPERTY SET amountofFee = ? WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, feeAmount);
			statement.setInt(2, propety_id);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public void payFee(int propety_id){
		try{
			String query = "UPDATE PROPERTY SET isPaid = 1 WHERE propertyID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, propety_id);
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
			String query = "INSERT INTO PROPERTY(Username, noOfBedrooms, noOfBathrooms, Furnished, cityQuadrant, propertyType)";
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
			query = "DELETE FROM NOTIFICATION_RENTER WHERE Username = ?";
			statement = connection.prepareStatement(query);
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
					users.add(res.getString("Username"));
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
	public ArrayList<Property> getRenterNotifications(String username){
		try{
			String query = "SELECT * FROM NOTIFICATION_RENTER WHERE Username = ?";
			ArrayList<Property> properties = new ArrayList<Property>();
			ArrayList<Integer> property_ids = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				property_ids.add(res.getInt("property_id"));
			}
			for(int i = 0; i<property_ids.size();i++){
				query = "SELECT * FROM PROPERTY WHERE propertyID = ?";
				statement = connection.prepareStatement(query);
				statement.setInt(1, property_ids.get(i));
				res = statement.executeQuery();
				while(res.next()){
					properties.add(new Property(res.getString("propertyType"), res.getBoolean("isListed"), res.getInt("noOfBedrooms"), 
					res.getInt("noOfBathrooms"), res.getBoolean("Furnished"), res.getInt("propertyID"), res.getString("cityQuadrant"), 
					res.getString("listingState"), res.getString("address"), res.getInt("listingPeriod"), res.getDouble("amountofFee"), 
					res.getString("landlordUsername"), res.getBoolean("isPaid")));
				}
			}
			return properties;
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
			return new ArrayList<Property>();
		}
		
	}
	public void registerPropertyNotification(String user, int property_id){
		try{
			String query = "INSERT INTO NOTIFICATION_RENTER(Username, property_id) Values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setInt(2, property_id);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	public void registerLandlordNotification(String land_user, String renter_user, int property_id){
		try{
			String query = "INSERT INTO NOTIFICATION_LANDLORD(Username, renter_username, property_id) Values(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, land_user);
			statement.setString(2, renter_user);
			statement.setInt(3, property_id);
			statement.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	//the search property method should return an arraylist of property objects
}
