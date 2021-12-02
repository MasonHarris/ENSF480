package controllers;
import models.Database;
import views.RenterView;
public class RenterController extends GUIcontroller {
	RenterView view;
	public RenterController(Database model, RenterView view) {
		super(model);
		this.view = view;
		
		
	}
	public void start() {
		view.addSubmitListener(e->
		propertySearch());
		view.displaySearchPanel();
		
	}
	public RenterController(Database model) {
		super(model);
		
	}
	public void propertySearch() {
		
	}

}
