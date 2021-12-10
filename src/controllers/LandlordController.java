package controllers;

import java.util.ArrayList;

import models.Database;
import models.Property;
import views.LandlordView;
import java.util.HashMap;

public class LandlordController extends GUIcontroller {
    private ArrayList<Property> landlord_properties;
    private LandlordView view;
    private LoginController login;
    private String username;

    public LandlordController(Database model, LandlordView view, LoginController login, String username) {
        super(model);
        this.view = view;
        this.login = login;
        this.username = username;
        landlord_properties = new ArrayList<Property>();
        landlord_properties = model.getLandlordProperties(this.username);

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

        view.addChangeListingListener(e -> {

            view.displayListingChanges(getPaid());
        });
        view.TableButtonListener(e -> changeListingState());
        view.registerFormListener(e -> registerProperty());
        view.addRegisterListener(e -> view.displayRegisterProperty());
        view.addNotificationsListener(e -> view.displayNotifications(model.getLandlordNotification(username)));
        // displayFees needs an arraylist argument consisting of an arraylist of
        // property objects that are unpaid for this landlord

        view.addPayFeeListener(e -> view.displayFees(getUnpaid(),model.getListingPeriodFee()));
        view.payFormListener(e -> payFees());

    }

    public ArrayList<Property> getPaid() {
        landlord_properties = model.getLandlordProperties(username);
        ArrayList<Property> paid = new ArrayList<Property>();
        for (var p : landlord_properties) {
            if (p.getisPaid()) {
                paid.add(p);
            }
        }

        return paid;

    }

    public ArrayList<Property> getUnpaid() {
        landlord_properties = model.getLandlordProperties(username);
        ArrayList<Property> unpaid = new ArrayList<Property>();
        for (Property property : landlord_properties) {
            if (!property.getisPaid()) {
                unpaid.add(property);
            }
        }
        return unpaid;

    }

    // used to fill properties arraylist
    public void getRegisteredProperties() {
        landlord_properties = model.getLandlordProperties(username);
    }

    public void registerProperty() {
        // gets registration values in this order address number, addresss name,
        // bathrooms, bedrooms, property type, quadrant, furnished
        String value[] = view.getformFields();
        int bedrooms;
        int bathrooms;
        Boolean furnished = false;
        //error checking
        try {
            int addressNumber = Integer.parseInt(value[0]);
            bedrooms = Integer.parseInt(value[3]);
            bathrooms = Integer.parseInt(value[2]);

            if (bedrooms <= 0 || bathrooms <= 0 || addressNumber < 0) {
                view.formError("Invalid number");
                return;
            }
            if (value[6].equals("Furnished")) {
                furnished = true;
            }

        } catch (NumberFormatException e) {
            view.formError("Non number put in number field");
            return;
        }

        if (!isStringAlpha(value[4]) || !isStringAlpha(value[1])) {
            view.formError("Non alphabetic character put in invalid field");
            return;
        }
        Property property = new Property(value[4].toLowerCase(), false, bedrooms, bathrooms, furnished,
                model.getRegisterPropertyID(), value[5], "Registered", value[0] + " " + value[1].toLowerCase(), 0, 0, username,
                false);
        model.registerProperty(property);
        view.formError("");
        view.confirmation("registered property successfully");
        landlord_properties.add(property);

    }

    public void changeListingState() {
        // integer contains index of altered property, string is altered properties new
        // state
        landlord_properties = model.getLandlordProperties(username);
        HashMap<Integer, String> alteredProperties = view.getSelectedProperties();
        if (alteredProperties == null) {
            return;
        }
        System.out.println("hello");
        for (var pair : alteredProperties.entrySet()) {
            System.out.println("hello");
            // if old status is different to new status, update database
            String oldstate = landlord_properties.get(pair.getKey()).getPropertyStatus();
            if ((oldstate != pair.getValue())) {
                // update database with new state(pair.getValue()) should also update properties
                // arraylist by calling getAllProperties after loop
                model.changePropertyListing(landlord_properties.get(pair.getKey()).getPropertyId(), pair.getValue());
            }
        }
        landlord_properties = model.getLandlordProperties(username);
        view.confirmation("Changes saved");

    }

    public void payFees() {
        // array of property ID's that have been paid for
        int[] paidFees = view.getPaymentProperties();
        if (paidFees == null) {
            return;
        }
        for (int propertyID : paidFees) {
            // use the pair.getkey() to get property id and update accoridinly
            model.payFee(propertyID);
            model.changePropertyListing(propertyID, "Active");
        }

        landlord_properties = model.getLandlordProperties(username);
        // Needs to be changed
        
        view.confirmation(
                "Payment successful, paid $" + paidFees.length * model.getListingPeriodFee().getValue());
        view.displayDashboard();

    }

}
