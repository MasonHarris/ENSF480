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
	//the search property method should return an arraylist of property objects

}
