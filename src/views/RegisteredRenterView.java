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
	JPanel dashBoardPanel;
	JPanel notificationsPanel;

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

	//creates and saves dashboard elements into memory
	public void intializeDashboard() {
		dashBoardPanel = new JPanel(new FlowLayout());
		logout = new JButton("Logout");
		viewNotifications = new JButton("View Notificiatons");
		unsubscribe = new JButton("Unsubscribe");
		search = new JButton("Search");

		dashBoardPanel.add(search);
		dashBoardPanel.add(viewNotifications);
		dashBoardPanel.add(unsubscribe);
		dashBoardPanel.add(logout);

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
	public void displayNotificationsPanel() {

	}

	// should display a pop up to confirm unsubscription is unsuccessful
	public void unsubscribeConfirmation() {

	}

}
