package models;
//this should be a singleton
import java.util.ArrayList;
public class Database {
	static Database onlyInstance;
	
	private Database() {
		
	}
	
	public static Database getOnlyInstance() {
		if(onlyInstance == null) {
			onlyInstance = new Database();
		}
		return onlyInstance;
	}
	//the search property method should return an arraylist of property objects

}
