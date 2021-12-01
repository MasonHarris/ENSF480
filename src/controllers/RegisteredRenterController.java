package controllers;
import models.Database;
import views.RegisteredRenterView;
public class RegisteredRenterController extends RenterController {
	RegisteredRenterView view;
	public RegisteredRenterController(Database model,RegisteredRenterView view) {
		super(model);
	    this.view = view;
	}

}
