package controllers;

import java.util.ArrayList;

import models.Database;
import models.Property;
import views.LandlordView;

public class LandlordController extends GUIcontroller {

    private LandlordView view;
    private LoginController login;

    public LandlordController(Database model, LandlordView view, LoginController login) {
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
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("Apartment", true, 5, 5, false, 5, "NW", "Listed", "123 street", 5, 5.6));
        properties.add(new Property("House", true, 55, 59, false, 5, "SW", "Listed", "1234 street", 52, 5.62));
        view.addChangeListingListener(e -> {
            view.displayListingChanges(properties);
        });
    }

}
