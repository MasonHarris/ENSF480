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
        view.formListener(e -> registerProperty());
        ArrayList<Property> properties = model.getLandlordProperties(username);
        view.addChangeListingListener(e -> {
            view.displayListingChanges(properties);
        });
        view.addBackListener(e -> {
            view.displayDashboard();
        });
        view.addRegisterListener(e -> view.displayRegisterForm());
    }

    public void registerProperty(Property property) {
        model.registerProperty(property);
    }

    public void changeListing(int propertyiD, String newListing) {
        model.changePropertyListing(propertyiD, newListing);
    }

    public void registerProperty() {
        // gets registration values in this order address number, addresss name,
        // bathrooms, bedrooms, property type, quadrant, furnished
        String value[] = view.getFormValues();
        int addressNumber;
        int bedrooms;
        int bathrooms;
        try {
            addressNumber = Integer.parseInt(value[0]);
            bedrooms = Integer.parseInt(value[2]);
            bathrooms = Integer.parseInt(value[3]);
        } catch (NumberFormatException e) {
            view.formError("Non number put in number field");

            return;

        }

        if (!isStringAlpha(value[4]) || !isStringAlpha(value[1])) {
            view.formError("Non alphabetic character put in invalid field");
            return;
        }

    }

}
