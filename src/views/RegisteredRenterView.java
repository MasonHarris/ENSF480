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
	JPanel searchPanel;
	JPanel notificationsPanel;
	public RegisteredRenterView(String frameName, int width, int height) {
		super(frameName,width,height);
	}
	@Override
	//needs to display Initial registered renter screen
	public void displayInitial() {
		//these probably need to be adjusted
		panel = new JPanel(new FlowLayout());
		logout = new JButton("Logout");
		viewNotifications = new JButton("View Notificiatons");
		unsubscribe = new JButton("Unsubscribe");
		search = new JButton("Search");
		panel.add(search);
		panel.add(viewNotifications);
		panel.add(unsubscribe);
		panel.add(logout);
		
		
		
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
	public void search() {
		super.displayInitial();
	}
	//switches to display search panel on frame
	public void displaySearchPanel() {
		
	}
	//switches to display notifications panel on frame
	public void displayNotificationsPanel() {
		
	}
	//should display a pop up to confirm unsubscription is unsuccessful
	public void unsubscribeConfirmation() {
		
	}

}
