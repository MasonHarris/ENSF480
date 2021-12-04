package views;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RenterView extends GUIview {
	JButton submitButton;
	JTextField bathRoomsText;
	JTextField bedRoomsText;
	JTextField propertyTypeText;
	JComboBox<String> quadrant;
	JComboBox<String> furnished;
	JLabel errorLabel;
	// used to display search results
	JPanel displayResults;

	public RenterView(String frameName, int width, int height) {
		super(width, height);
		intializeSearchPanel();
		initalizeFrame(frameName);

	}

	public void intializeSearchPanel() {
		JLabel searchLabel = new JLabel("Search criteria for property");
		searchLabel.setBounds((int) (width * 0.3), (int) (height * 0.01), (int) (width * 0.5), (int) (height * 0.02));

		JLabel bathRoomsLabel = new JLabel("Number of bathrooms");
		bathRoomsLabel.setBounds((int) (width * 0.01), (int) (height * 0.05), (int) (width * 0.5),
				(int) (height * 0.03));

		JLabel bedRoomsLabel = new JLabel("Number of bedrooms");
		bedRoomsLabel.setBounds((int) (width * 0.21), (int) (height * 0.05), (int) (width * 0.5),
				(int) (height * 0.03));

		JLabel propertyTypeLabel = new JLabel("Property type");
		propertyTypeLabel.setBounds((int) (width * 0.41), (int) (height * 0.05), (int) (width * 0.5),
				(int) (height * 0.03));

		JLabel quadrantLabel = new JLabel("City quadrant");
		quadrantLabel.setBounds((int) (width * 0.61), (int) (height * 0.05), (int) (width * 0.5),
				(int) (height * 0.03));

		JLabel furnishedLabel = new JLabel("Furnishing status");
		furnishedLabel.setBounds((int) (width * 0.81), (int) (height * 0.05), (int) (width * 0.5),
				(int) (height * 0.03));

		bathRoomsText = new JTextField();
		bathRoomsText.setBounds((int) (width * 0.01), (int) (height * 0.1), (int) (width * 0.1), (int) (height * 0.03));

		bedRoomsText = new JTextField();
		bedRoomsText.setBounds((int) (width * 0.21), (int) (height * 0.1), (int) (width * 0.1), (int) (height * 0.03));

		propertyTypeText = new JTextField();
		propertyTypeText.setBounds((int) (width * 0.41), (int) (height * 0.1), (int) (width * 0.1),
				(int) (height * 0.03));

		String[] quadrants = { "NW", "NE", "SW", "SE" };
		quadrant = new JComboBox<String>(quadrants);
		quadrant.setSelectedIndex(0);
		quadrant.setBounds((int) (width * 0.61), (int) (height * 0.1), (int) (width * 0.1), (int) (height * 0.03));

		String[] furnishedOptions = { "furnished", "unfurnished" };
		furnished = new JComboBox<String>(furnishedOptions);
		furnished.setBounds((int) (width * 0.81), (int) (height * 0.1), (int) (width * 0.1), (int) (height * 0.03));

		submitButton = new JButton("Submit");
		submitButton.setBounds((int) (width * 0.3), (int) (height * 0.15), (int) (width * 0.1), (int) (height * 0.03));

		errorLabel = new JLabel();
		errorLabel.setBounds((int) (width * 0.3), (int) (height * 0.18), (int) (width * 0.5), (int) (height * 0.03));

		panel = new JPanel(null);
		panel.add(searchLabel);
		panel.add(bathRoomsLabel);
		panel.add(bedRoomsLabel);
		panel.add(propertyTypeLabel);
		panel.add(quadrantLabel);
		panel.add(furnishedLabel);
		panel.add(bathRoomsText);
		panel.add(bedRoomsText);
		panel.add(propertyTypeText);
		panel.add(quadrant);
		panel.add(furnished);
		panel.add(submitButton);
		panel.add(errorLabel);

	}

	public void displaySearchPanel() {
		frame.setContentPane(panel);
		frame.revalidate();

	}

	public String getBedRoomsText() {
		return bedRoomsText.getText();
	}

	public String getBathRoomsText() {
		return bathRoomsText.getText();
	}

	public String getFurnishingInfo() {
		return (String) furnished.getSelectedItem();
	}

	public String getQuadrant() {
		return (String) quadrant.getSelectedItem();
	}

	public String getPropertyType() {
		return propertyTypeText.getText();
	}

	public void addSubmitListener(ActionListener listener) {
		submitButton.addActionListener(listener);
	}

	public void displayError(String error) {
		errorLabel.setText(error);
		panel.repaint();
	}
	// receives arraylist of properties to display
	/*
	 * public void displaySearch(ArrayList<Property>) {
	 * 
	 * }
	 */

}
