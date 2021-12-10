package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

import models.Property;

public class LandlordView extends GUIview {
    JButton registerButton;
    JButton changeStateButton;
    JButton logoutButton;
    JButton payFeeButton;
    JButton submitButton;
    JButton viewNotifications;
    JButton payFeesButton;
    TablePanel feePanel;
    TablePanel tPanel;
    RegistrationForm form;
    ViewLandlordNotifications n;
    //state change options
    String options[] = { "Suspended", "Unsuspended", "Cancelled", "Rented" };

    public LandlordView(String frameName, int width, int height) {
        super(width, height);
        tPanel = new TablePanel(width, height, "Save changes");
        form = new RegistrationForm();
        feePanel = new TablePanel(width, height, "Pay fees");
        n = new ViewLandlordNotifications(width, height);
        initalizeFrame(frameName);

    }

    // creates and saves dashboard elements into memory
    public void intializeDashboard() {
        panel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register property");
        changeStateButton = new JButton("Change listing state");
        logoutButton = new JButton("Logout");
        submitButton = new JButton("Submit");
        payFeeButton = new JButton("Pay fees");
        viewNotifications = new JButton("View notifications");
        panel.add(registerButton);
        panel.add(changeStateButton);
        panel.add(viewNotifications);
        panel.add(payFeeButton);
        panel.add(logoutButton);

    }

    public void displayDashboard() {
        frame.setContentPane(panel);
        frame.revalidate();
    }

    // the following methods until the end add event listeners to all the buttons
    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);

    }

    public void addChangeListingListener(ActionListener listener) {
        changeStateButton.addActionListener(listener);

    }
    //add listener for button to take user to pay fee page
    public void addPayFeeListener(ActionListener listener) {
        payFeeButton.addActionListener(listener);
    }
    //adds listener to actual pay fee button
    public void payFormListener(ActionListener listener) {
        feePanel.submitButton.addActionListener(listener);
    }
    
    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);

    }

    public void addNotificationsListener(ActionListener listener) {
        viewNotifications.addActionListener(listener);
    }
    //adds listener to save listing state changes
    public void TableButtonListener(ActionListener listener) {
        tPanel.submitButton.addActionListener(listener);
    }
    //adds listener to submit registered property
    public void registerFormListener(ActionListener listener) {
        form.register.addActionListener(listener);
    }
    //displays errors on registration form
    public void formError(String error) {
        form.displayError(error);
    }
    //string arrays in format {property id, renter email , address} 
    public void displayNotifications(ArrayList<String[]> notifications) {
        n.displayNotifications(notifications);
        backButton.setBounds(110, (int) (height * 0.62), (int) (width * 0.2), (int) (height * 0.03));
        n.add(backButton);
        frame.setContentPane(n);
        frame.revalidate();
    }
    //display outstanding fees for landlords
    public void displayFees(ArrayList<Property> properties, SimpleEntry<Integer, Double> feePair) {
        backButton.setBounds((int) (width * 0.25), (int) (height * 0.55), (int) (width * 0.2),
                (int) (height * 0.030));
        feePanel.displayPropertyPayFees(properties,feePair);
        feePanel.add(backButton);
        frame.setContentPane(feePanel);
        frame.revalidate();
    }
    //displays properties landlords can change the state of
    public void displayListingChanges(ArrayList<Property> properties) {
        tPanel.displayPropertyTableChangeListing(properties);
        backButton.setBounds((int) (width * 0.25), (int) (height * 0.55), (int) (width * 0.2),
                (int) (height * 0.030));
        tPanel.add(backButton);

        frame.setContentPane(tPanel);
        frame.revalidate();

    }
    // gets registration values in this order address number, addresss name,
    // bathrooms, bedrooms, property type, quadrant, furnished
    public String[] getformFields() {
        return form.getValues();
    }
    //returns property id has key and new property state as value
    public HashMap<Integer, String> getSelectedProperties() {
        return tPanel.getSelectedPropertiesMap(7);
    }
    //returns int array of property ids that have been paid for by the landlord
    public int[] getPaymentProperties() {
        return feePanel.getSelectedPropertiesID();
    }
    //displays register property form
    public void displayRegisterProperty() {
        backButton.setBounds(110, (int) (height * 0.40), (int) (width * 0.1), (int) (height * 0.025));
        
        form.displayForm(width, height);
        form.add(backButton);
        frame.setContentPane(form);
        frame.revalidate();
    }

    public HashMap<Integer, String> getPaymentPropertiesID() {
        return null;
    }

}