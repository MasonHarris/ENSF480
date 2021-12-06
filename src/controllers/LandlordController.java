package controllers;

import java.util.ArrayList;

import javax.print.attribute.standard.RequestingUserName;

import models.Database;
import models.Property;
import views.LandlordView;
import java.util.HashMap;

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
        view.addBackListener(e -> view.displayDashboard());

        regiProperties.add(new Property("Apartment", true, 5, 5, false, 5, "NW", "Listed", "123 street", 5, 5.6));
        regiProperties.add(new Property("House", true, 55, 59, false, 5, "SW", "Listed", "1234 street", 52, 5.62));
        view.addChangeListingListener(e -> {
            view.displayListingChanges(regiProperties);
        });
        view.TableButtonListener(e -> changeListingState());
        view.registerFormListener(e -> registerProperty());
    }

    // used to fill properties arraylist
    public void getRegisteredProperties() {

    }

    public void registerProperty() {
        String[] inputs = view.getformFields();
        // need to add error checking to inputs, if successful this should call a
        // function to update database

    }

    public void changeListingState() {
        // integer contains index of altered property, string is altered properties new
        // state
        HashMap<Integer, String> alteredProperties = view.getSelectedProperties();
        if (alteredProperties == null) {
            return;
        }
        System.out.println("hello");
        for (var pair : alteredProperties.entrySet()) {
            System.out.println("hello");
            // if old status is different to new status, update database
            String oldstate = regiProperties.get(pair.getKey()).getPropertyStatus();
            if ((oldstate != pair.getValue())) {
                // update database with new state(pair.getValue()) should also update properties
                // arraylist by calling getAllProperties after loop
                System.out.println("Property " + pair.getKey() + " has a new status of  " + pair.getValue());
            }
        }

    }

}
