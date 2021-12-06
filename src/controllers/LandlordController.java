package controllers;

import java.util.ArrayList;

import models.Database;
import models.Property;
import views.LandlordView;

public class LandlordController extends GUIcontroller {

    private LandlordView view;
    private LoginController login;
    private String username;

    public LandlordController(Database model, LandlordView view, LoginController login, String username) {
        super(model);
        this.view = view;
        this.login = login;

    }

    public void start() {
        view.intializeDashboard();
        view.displayDashboard();
        // logouts by destroying current user frame and recreating intial login frame
        view.addLogoutListener(e -> {
            view.dispose();
            login.start();

        });
        ArrayList<Property> properties = model.getLandlordProperties(username);
        view.addChangeListingListener(e -> {
            view.displayListingChanges(properties);
        });
    }
    public void registerProperty(Property property){
        model.registerProperty(property);
    }
    public void changeListing(int propertyiD, String newListing){
        model.changePropertyListing(propertyiD, newListing);
    }

}
