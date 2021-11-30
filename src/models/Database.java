package models;
//this should be a singleton
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

}
