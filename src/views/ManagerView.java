package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerView extends GUIview {
    JButton logoutButton;
    JButton changeStateButton;
    JButton submitButton;
    JButton accessDatabaseButton;
    JButton generateReportButton;

    public ManagerView(String frameName, int width, int height) {
        super(width, height);
        initalizeFrame(frameName);
    }

    public void intializeDashboard() {
        changeStateButton = new JButton("Change listing state");
        logoutButton = new JButton("Logout");
        generateReportButton = new JButton("Generate periodical report");
        accessDatabaseButton = new JButton("Access database");
        submitButton = new JButton("Submit");
        panel = new JPanel(new FlowLayout());
        panel.add(changeStateButton);
        panel.add(generateReportButton);
        panel.add(accessDatabaseButton);
        panel.add(logoutButton);

    }

    public void displayDashboard() {
        frame.setContentPane(panel);
        frame.revalidate();

    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);

    }

    public void addChangeStateListener(ActionListener listener) {
        changeStateButton.addActionListener(listener);

    }

    public void addAccessDBListener(ActionListener listener) {
        accessDatabaseButton.addActionListener(listener);

    }

    public void addReportListener(ActionListener listener) {
        generateReportButton.addActionListener(listener);

    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

}