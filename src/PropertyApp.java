import controllers.*;
import views.*;
import models.*;
public class PropertyApp {

	

	public static void main(String[] args) {
		//creates a new login and starts it to begin gui
		LoginController login = new LoginController(Database.getOnlyInstance(), new LoginView("Login Screen",600,400));
		login.start();

	}

}
