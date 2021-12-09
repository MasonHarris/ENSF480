package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

import models.Property;
import models.SummaryReport;
import models.RegisteredRenter;
import models.Landlord;

public class ManagerView extends GUIview {
    JButton logoutButton;
    JButton changeStateButton;
    JButton submitButton;
    JButton accessLandlordButton;
    JButton accessPropertyButton;
    JButton accessRenterButton;
    JButton generateReportButton;
    JButton setFeesButton;
    JButton viewSubscriptions;
    TablePanel tPanel;
    FeeForm form;
    SummaryReportView reportView;
    ManagerTables viewTable;

    public ManagerView(String frameName, int width, int height) {
        super(width, height);
        tPanel = new TablePanel(width, height, "save changes");
        initalizeFrame(frameName);
        form = new FeeForm();
        reportView = new SummaryReportView(width, height);
        viewTable = new ManagerTables(width, height);
    }

    // creates and saves dashboard elements into memory
    public void intializeDashboard() {
        changeStateButton = new JButton("Change listing state");
        logoutButton = new JButton("Logout");
        generateReportButton = new JButton("Generate periodical report");
        accessLandlordButton = new JButton("Access landlord information");
        accessPropertyButton = new JButton("Access Property information");
        accessRenterButton = new JButton("Access renter information ");
        submitButton = new JButton("Submit");
        setFeesButton = new JButton("Set or change fees");

        panel = new JPanel(new FlowLayout());
        panel.add(changeStateButton);
        panel.add(generateReportButton);
        panel.add(accessLandlordButton);
        panel.add(accessPropertyButton);
        panel.add(accessRenterButton);
        panel.add(setFeesButton);
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

    public void addChangeStateListener(ActionListener listener) {
        changeStateButton.addActionListener(listener);

    }

    public void addAccessLandlordListener(ActionListener listener) {
        accessLandlordButton.addActionListener(listener);

    }

    public void addAccessPropertyListener(ActionListener listener) {
        accessPropertyButton.addActionListener(listener);
    }

    public void addAccessRentersListener(ActionListener listener) {
        accessRenterButton.addActionListener(listener);
    }

    public void addReportListener(ActionListener listener) {
        generateReportButton.addActionListener(listener);

    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addSetFeesListner(ActionListener listener) {
        setFeesButton.addActionListener(listener);
    }

    public void TableButtonListener(ActionListener listener) {
        tPanel.submitButton.addActionListener(listener);
    }

    public void FeesListener(ActionListener listener) {
        if (form.submit == null) {
            System.out.println("submit is null");
        }
        form.submit.addActionListener(listener);

    }

    public void displayForm(SimpleEntry<Integer,Double> feePair) {
        backButton.setBounds(110, (int) (height * 0.20), (int) (width * 0.2), (int) (height * 0.025));

        form.displayFees(width, height, feePair );
        form.add(backButton);
        frame.setContentPane(form);
        frame.revalidate();
    }

    public void displayFormError(String error) {
        form.displayError(error);
    }

    public String[] getFormFields() {
        return form.getFields();
    }

    public HashMap<Integer, String> getSelectedProperties() {
        return tPanel.getSelectedPropertiesMap(7);
    }

    public void displayListingChanges(ArrayList<Property> properties) {
        backButton.setBounds((int) (width * 0.25), (int) (height * 0.55), (int) (width * 0.2),
                (int) (height * 0.030));

        tPanel.displayPropertyTableChangeListing(properties);
        tPanel.add(backButton);
        frame.setContentPane(tPanel);
        frame.revalidate();

    }

    public void displaySummaryReport(SummaryReport r) {
        reportView.displayReport(width, height, r);
        backButton.setBounds(110, (int) (height * 0.62), (int) (width * 0.2), (int) (height * 0.03));
        reportView.add(backButton);
        frame.setContentPane(reportView);
        frame.revalidate();
    }

    public void displayRenterTable(ArrayList<RegisteredRenter> renters) {
        backButton.setBounds(110, (int) (height * 0.6), (int) (width * 0.2), (int) (height * 0.03));
        viewTable.displayRenters(renters);

        viewTable.add(backButton);

        frame.setContentPane(viewTable);
        frame.revalidate();

    }

    public void displayLandlordTable(ArrayList<Landlord> landlords) {
        backButton.setBounds(110, (int) (height * 0.6), (int) (width * 0.2), (int) (height * 0.03));
        viewTable.displayLandlords(landlords);

        viewTable.add(backButton);

        frame.setContentPane(viewTable);
        frame.revalidate();

    }

    public void displayPropertyTable(ArrayList<Property> properties) {
        backButton.setBounds(110, (int) (height * 0.6), (int) (width * 0.2), (int) (height * 0.03));
        viewTable.displayProperties(properties);

        viewTable.add(backButton);
        viewTable.repaint();
        frame.setContentPane(viewTable);
        frame.revalidate();

    }

}