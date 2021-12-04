package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LandlordView extends GUIview {
    JButton registerButton;
    JButton changeStateButton;
    JButton logoutButton;
    JButton payFeeButton;
    JButton submitButton;
    JButton viewNotifications;

    public LandlordView(String frameName, int width, int height) {
        super(width, height);
        initalizeFrame(frameName);
    }

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

}