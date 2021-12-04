package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RegisteredRenterView extends RenterView {
	JButton logout;
	JButton viewNotifications;
	JButton unsubscribe;
	JButton search;
	JPanel optionsPanel;
	JPanel notificationsPanel;

	public RegisteredRenterView(String frameName, int width, int height) {
		super(frameName, width, height);

		intializeDashboard();
		// add methods to initialize other panels

	}

	// needs to display Initial registered renter screen
	public void displayDashboard() {
		frame.setContentPane(optionsPanel);
		frame.invalidate();
		frame.validate();

	}

	public void intializeDashboard() {
		optionsPanel = new JPanel(new FlowLayout());
		logout = new JButton("Logout");
		viewNotifications = new JButton("View Notificiatons");
		unsubscribe = new JButton("Unsubscribe");
		search = new JButton("Search");

		optionsPanel.add(search);
		optionsPanel.add(viewNotifications);
		optionsPanel.add(unsubscribe);
		optionsPanel.add(logout);

	}

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
	public void displayNotificationsPanel() {

	}

	// should display a pop up to confirm unsubscription is unsuccessful
	public void unsubscribeConfirmation() {

	}

}
