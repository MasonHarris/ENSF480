package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.Property;

public class LandlordView extends GUIview {
    JButton registerButton;
    JButton changeStateButton;
    JButton logoutButton;
    JButton payFeeButton;
    JButton submitButton;
    JButton viewNotifications;
    TablePanel tPanel;
    String options[] = { "Suspended", "Unsuspended", "Cancelled", "Rented" };

    public LandlordView(String frameName, int width, int height) {
        super(width, height);
        tPanel = new TablePanel(width, height, "Save changes");
        initalizeFrame(frameName);
    }

    // creates and saves dashboard elements into memory
    public void intializeDashboard() {
        panel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register property");
        changeStateButton = new JButton("Change listing state");
        logoutButton = new JButton("Logout");
        submitButton = new JButton("Submit");
        viewNotifications = new JButton("View notifications");
        panel.add(registerButton);
        panel.add(changeStateButton);
        panel.add(viewNotifications);
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

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);

    }

    public void addNotificationsListener(ActionListener listener) {
        viewNotifications.addActionListener(listener);
    }

    public void listingChangesListener(ActionListener listener) {
        tPanel.submitButton.addActionListener(listener);
    }

    public void displayListingChanges(ArrayList<Property> properties) {
        tPanel.displayPropertyTableChangeListing(properties, options);
        frame.setContentPane(tPanel);
        frame.revalidate();

    }

}