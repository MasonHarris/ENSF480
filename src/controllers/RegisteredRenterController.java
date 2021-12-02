package controllers;
import models.Database;
import views.RegisteredRenterView;
public class RegisteredRenterController extends RenterController {
	RegisteredRenterView view;
	public RegisteredRenterController(Database model,RegisteredRenterView view) {
		super(model);
	    this.view = view;
	    view.addLogoutListener(e-> view.dispose());
	    view.addSearchListener(e->view.displaySearchPanel());
	    view.addUnsubscribeListener(e->unsubscribeRenter());
	    view.addNotificationsListener(e->view.displayNotificationsPanel());
	}
	
	public void unsubscribeRenter() {
		//code to unsubscribe renter
		//if renter unsubscribed
		view.unsubscribeConfirmation();
		
	}
	
	
	

}
