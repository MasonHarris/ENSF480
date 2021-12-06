package controllers;

import java.util.ArrayList;

import models.Database;
import models.Property;
import views.ManagerView;
import java.util.HashMap;

public class ManagerController extends GUIcontroller {
    private ManagerView view;
    private LoginController login;
    private String username;
    private ArrayList<Property> allProperties;

    public ManagerController(Database model, ManagerView view, LoginController login, String username) {
        super(model);
        this.view = view;
        this.login = login;
        this.username = username;
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
        view.addChangeStateListener(e -> view.displayListingChanges(allProperties));
        view.TableButtonListener(e -> changeListingState());

    }

    // used to fill properties arraylist
    public void getAllProperties() {

    }

    public void changeListingState() {
        // integer contains index of altered property, string is altered properties new
        // state
        HashMap<Integer, String> alteredProperties = view.getSelectedProperties();
        if (alteredProperties == null) {
            return;
        }

        for (var pair : alteredProperties.entrySet()) {

            // if old status is different to new status, update database
            String oldstate = allProperties.get(pair.getKey()).getPropertyStatus();
            if ((oldstate != pair.getValue())) {
                // update database with new state(pair.getValue()) should also update properties
                // arraylist by calling getAllProperties after loop
                System.out.println("Property " + pair.getKey() + " has a new status of  " + pair.getValue());
            }
        }

    }

}
