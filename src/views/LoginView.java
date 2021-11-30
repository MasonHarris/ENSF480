package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginView extends GUIview {
	JButton renterButton;
    JButton selectLogin;
    JButton loginButton;
    JPanel loginPanel;
   JTextField usernameText;
   JPasswordField passwordText;
	public LoginView() {
		frame = new JFrame("Login Screen");
		frame.setSize(400,200);
		panel = new JPanel(new FlowLayout());
		renterButton = new JButton("Proceed as normal renter");
		loginButton = new JButton("login");
		selectLogin = new JButton("Login as registered user");
		panel.add(renterButton);
		
		panel.add(selectLogin);
		panel.add(renterButton);
		
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public void showLogin() {
		loginPanel = new JPanel(null);
		JLabel user = new JLabel("Username");
		user.setBounds(10,20,80,25);
		
		usernameText = new JTextField(20);
		usernameText.setBounds(100,20,165,25);
		JLabel password = new JLabel("Password");
		password.setBounds(10,50,80,25);
		passwordText = new JPasswordField();
		passwordText.setBounds(100,50,165,25);
		
		loginButton.setBounds(100,80,165,25);
		loginPanel.add(user);
		loginPanel.add(usernameText);
		loginPanel.add(password);
		loginPanel.add(passwordText);
		loginPanel.add(loginButton);
		
		frame.setContentPane(loginPanel);
		frame.invalidate();
		frame.validate();
		
		
	}
	public void addRenterListener(ActionListener listener) {
		renterButton.addActionListener(listener);
	}
	
	public void addLoginListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}
	public void addSelectLoginListener(ActionListener listener) {
		selectLogin.addActionListener(listener);
	}
	public String getUserText() {
		return usernameText.getText();
	}
	public String getPasswordText() {
		return new String(passwordText.getPassword());
	}
	public void displayLoginError() {
		JLabel error = new JLabel("Invalid login");
		error.setBounds(100,100,165,25);
		loginPanel.add(error);
		loginPanel.repaint();
		
		
	}

}
