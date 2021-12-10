package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import models.Subscription;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Property;

public class RegisteredRenterView extends RenterView {
	JButton logout;
	JButton viewNotifications;
	JButton unsubscribe;
	JButton search;
	JPanel dashBoardPanel;
	JPanel notificationsPanel;
	JButton subscribe;
	
	public RegisteredRenterView(String frameName, int width, int height) {
		super(frameName, width, height);
		
		intializeDashboard();
		// add methods to initialize other panels

	}

	// needs to display Initial registered renter screen
	public void displayDashboard() {
		frame.setContentPane(dashBoardPanel);
		frame.invalidate();
		frame.validate();

	}

	// creates and saves dashboard elements into memory
	public void intializeDashboard() {
		dashBoardPanel = new JPanel(new FlowLayout());
		logout = new JButton("Logout");
		viewNotifications = new JButton("View Notifications");
		unsubscribe = new JButton("Unsubscribe");
		search = new JButton("Search");
		subscribe = new JButton("Subscribe");
		dashBoardPanel.add(search);
		dashBoardPanel.add(viewNotifications);
		dashBoardPanel.add(unsubscribe);
		dashBoardPanel.add(logout);

	}

	@Override
	public void displaySearchPanel() {
		backButton.setBounds((int) (width * 0.5), (int) (height * 0.15), (int) (width * 0.3), (int) (height * 0.03));
		panel.add(backButton);
		super.displaySearchPanel();

	}

	// the following methods until the end add event listeners to all the buttons
	public void addLogoutListener(ActionListener listener) {
		logout.addActionListener(listener);

	}

	public void addUnsubscribeListener(ActionListener listener) {
		unsubscribe.addActionListener(listener);

	}

	public void addSearchListener(ActionListener listener) {
		search.addActionListener(listener);

	}

	public void addNotificationsListener(ActionListener listener) {
		viewNotifications.addActionListener(listener);

	}

	// switches to display notifications optionsPanel on frame
	public void displayNotificationsPanel(ArrayList<Property> notifications, Subscription s) {
		super.displaySearch(notifications);
		JLabel subLabel;
		if(s != null){
		 subLabel = new JLabel("You are subscribed for " + s.getNoOfBathrooms() + " bathrooms " + s.getNoOfBedrooms() + " bedrooms"
		+ " furnished is " + s.getIsFurnished() + " and city quadrant " + s.getCityQuadrant() );
		}
		else {
			subLabel = new JLabel("You are not subscribed");
		}
		subLabel.setBounds((int) (width * 0.2), (int) (height * 0.60), (int) (width * 0.81),
		(int) (height * 0.030));
		tPanel.add(subLabel);
		

	}
	@Override
	public void displaySearch(ArrayList<Property> properties) {
		super.displaySearch(properties);
		subscribe.setBounds((int) (width * 0.75), (int) (height * 0.55), (int) (width * 0.1),
				(int) (height * 0.030));
		tPanel.add(subscribe);


	}

	public void subscribeListener(ActionListener listener){
		subscribe.addActionListener(listener);
	}

}

