package controllers;

import java.util.ArrayList;

import models.Database;
import models.Landlord;
import models.Property;
import models.RegisteredRenter;
import models.Subscription;
import models.SummaryReport;
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
        allProperties = new ArrayList<Property>();
        // allProperties = model.getAllProperties();
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
        // needs to retrieve fees from database(please add this) random values hardcoded
        // for now
        view.addSetFeesListner(e -> view.displayForm(100, 100));
        view.FeesListener(e -> changeFees());
        view.addReportListener(e -> summary());
        // someone should fill these in with the correct arraylist argument(these should
        // be created by the model)
        ArrayList<RegisteredRenter> renters = new ArrayList<RegisteredRenter>();
        renters.add(new RegisteredRenter("miku", "nakano@email.com", "pass", 1, new Subscription()));
        renters.add(new RegisteredRenter("nino", "nakano@email.com", "pass", 31, new Subscription()));
        ArrayList<Landlord> landlords = new ArrayList<Landlord>();
        landlords.add(new Landlord("ai", 1, "hayasaka", "hayasaka@gmail.com"));
        landlords.add(new Landlord("raiden", 18, "shogun", "raiden@gmail.com"));

        view.addAccessLandlordListener(e -> view.displayLandlordTable(landlords));
        view.addAccessRentersListener(e -> view.displayRenterTable(renters));
        // view.addAccessPropertyListener(e -> view.displayPropertyTable());
    }

    public void summary() {
        // add code here to get a summary report object from the database
        ArrayList<Property> properties = model.getAllProperties();
        int totalHousesListed = 0;
        int totalHousesRented = 0;
        int totalHousesActive = 0;
        ArrayList<Property> rentedHouses = new ArrayList<>();
        for(int i = 0; i<properties.size();i++){
            if((properties.get(i).getListingPeriod() > 0) && (properties.get(i).getPropertyStatus().equals("Active"))){
                totalHousesListed++;
                totalHousesActive++;
            }
            if((properties.get(i).getListingPeriod() > 0) && (properties.get(i).getPropertyStatus().equals("Rented"))){
                totalHousesListed++;
                totalHousesRented++;
                rentedHouses.add(properties.get(i));
            }
        }
        // dummy.add(new Property("Apartment", true, 1, 1, true, 1, "SW", "Active",
        //         "123", 1, 8, "joe", true));
        // dummy.add(new Property("Apartment", true, 1, 1, true, 79, "NW", "Active",
        //         "123", 1, 8, "joe", true));
        SummaryReport r = new SummaryReport(totalHousesListed, totalHousesRented, totalHousesActive, rentedHouses);
        view.displaySummaryReport(r);

    }

    public void changeFees() {
        // fee value first, period second in array
        String[] values = view.getFormFields();
        double feeValue;
        int period;
        try {
            feeValue = Double.parseDouble(values[0]);
            period = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
            view.displayFormError("INVALID VALUES");
            return;
        }

        // update database fee period and value code here
        model.setFee(feeValue);
        model.setPeriod(period);
        view.confirmation("Fees changed");
        view.displayDashboard();

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
                model.changePropertyListing(allProperties.get(pair.getKey()).getPropertyId(), pair.getValue());
            }
        }

    }

}
