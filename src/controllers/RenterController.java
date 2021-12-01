package controllers;
import models.Database;
import views.RenterView;
public class RenterController extends GUIcontroller {
	RenterView view;
	public RenterController(Database model, RenterView view) {
		super(model);
		this.view = view;
		
		view.addSubmitListener(e->
		propertySearch());
	}
	public RenterController(Database model) {
		super(model);
		view.addSubmitListener(e->
		propertySearch());
	}
	public void propertySearch() {
		
	}

}
