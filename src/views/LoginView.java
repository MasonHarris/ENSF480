package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends GUIview {
	JButton renterButton;
	JButton selectLogin;
	JButton loginButton;
	JPanel loginPanel;
	JPanel signupPanel;
	JTextField usernameText;
	JPasswordField passwordText;
	JButton selectSignUpButton;
	JButton signupButton;
	JLabel error = null;

	public LoginView(String frameName, int width, int height) {

		super(width, height);
		intializeDashboard();

	}

	public void intializeDashboard() {

		panel = new JPanel(new FlowLayout());
		renterButton = new JButton("Proceed as normal renter");
		loginButton = new JButton("login");
		signupButton = new JButton("signup");
		selectLogin = new JButton("Login as registered user");
		selectSignUpButton = new JButton("Sign up");
		panel.add(renterButton);
		panel.add(selectSignUpButton);
		panel.add(selectLogin);
		panel.add(renterButton);

	}

	public void displayDashboard() {

		frame.setContentPane(panel);
		frame.invalidate();
		frame.validate();

	}

	public void showLogin() {
		loginPanel = new JPanel(null);
		JLabel user = new JLabel("Username");
		System.out.println("height " + height);
		System.out.println("width " + width);
		user.setBounds((int) (width * 0.025), (int) (height * 0.1), (int) (width * 0.2), (int) (height * 0.125));

		usernameText = new JTextField(20);
		usernameText.setBounds((int) (width * 0.25), (int) (height * 0.1), (int) (width * 0.41),
				(int) (height * 0.125));
		JLabel password = new JLabel("Password");
		password.setBounds((int) (width * 0.025), (int) (height * 0.25), (int) (width * 0.2), (int) (height * 0.125));
		passwordText = new JPasswordField();
		passwordText.setBounds((int) (width * 0.25), (int) (height * 0.25), (int) (width * 0.41),
				(int) (height * 0.125));

		loginButton.setBounds((int) (width * 0.25), (int) (height * 0.40), (int) (width * 0.41),
				(int) (height * 0.125));
		loginPanel.add(user);
		loginPanel.add(usernameText);
		loginPanel.add(password);
		loginPanel.add(passwordText);
		loginPanel.add(loginButton);

		frame.setContentPane(loginPanel);
		frame.invalidate();
		frame.validate();

	}

	public void signUpPanel() {
		signupPanel = new JPanel(null);
		JLabel user = new JLabel("Username");
		System.out.println("height " + height);
		System.out.println("width " + width);
		user.setBounds((int) (width * 0.025), (int) (height * 0.1), (int) (width * 0.2), (int) (height * 0.125));

		usernameText = new JTextField(20);
		usernameText.setBounds((int) (width * 0.25), (int) (height * 0.1), (int) (width * 0.41),
				(int) (height * 0.125));
		JLabel password = new JLabel("Password");
		password.setBounds((int) (width * 0.025), (int) (height * 0.25), (int) (width * 0.2), (int) (height * 0.125));
		passwordText = new JPasswordField();
		passwordText.setBounds((int) (width * 0.25), (int) (height * 0.25), (int) (width * 0.41),
				(int) (height * 0.125));

		signupButton.setBounds((int) (width * 0.25), (int) (height * 0.40), (int) (width * 0.41),
				(int) (height * 0.125));
		signupPanel.add(user);
		signupPanel.add(usernameText);
		signupPanel.add(password);
		signupPanel.add(passwordText);
		signupPanel.add(signupButton);
		frame.setTitle("Signup");
		frame.setContentPane(signupPanel);
		frame.invalidate();
		frame.validate();

	}

	// the following methods until the end add event listeners to all the buttons
	public void addRenterListener(ActionListener listener) {
		renterButton.addActionListener(listener);
	}

	public void addLoginListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	public void addSelectLoginListener(ActionListener listener) {
		selectLogin.addActionListener(listener);
	}

	public void addSelectSignupListener(ActionListener listener) {
		selectSignUpButton.addActionListener(listener);
	}

	public void addSignupListener(ActionListener listener) {
		signupButton.addActionListener(listener);
	}

	// methods used to retrieve values in user input sections
	public String getUserText() {
		return usernameText.getText();
	}

	public String getPasswordText() {
		return new String(passwordText.getPassword());
	}

	public void displayLoginError() {
		JLabel error = new JLabel("Invalid login");
		error.setBounds((int) (width * 0.25), (int) (height * 0.55), (int) (width * 0.41), (int) (height * 0.125));
		loginPanel.add(error);
		loginPanel.repaint();

	}
	public void displaySignupError() {
		if(error != null){
			signupPanel.remove(error);
			signupPanel.revalidate();
			signupPanel.repaint();
			error = null;
		}
		error = new JLabel("Invalid Signup -- username already exists");
		error.setBounds((int) (width * 0.25), (int) (height * 0.55), (int) (width * 0.7), (int) (height * 0.125));
		signupPanel.add(error);
		signupPanel.repaint();

	}
	public void displayEmptyTextError(String empty) {
		if(error != null){
			signupPanel.remove(error);
			signupPanel.revalidate();
			signupPanel.repaint();
			error = null;
		}
		error = new JLabel("Empty Text field in " + empty);
		error.setBounds((int) (width * 0.25), (int) (height * 0.55), (int) (width * 0.7), (int) (height * 0.125));
		signupPanel.add(error);
		signupPanel.repaint();

	}

}
