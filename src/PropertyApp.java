import controllers.*;
import views.*;
import models.*;
public class PropertyApp {

	

	public static void main(String[] args) {
		LoginController login = new LoginController(Database.getOnlyInstance(), new LoginView("Login Screen",400,200));

	}

}
