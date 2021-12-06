package views;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class RegistrationForm extends JPanel {
    JButton register;
    JTextArea addressNumber;
    JTextArea addressName;
    JTextArea bathrooms;
    JTextArea bedrooms;
    JComboBox<String> quadrant;
    JComboBox<String> furnished;
    JTextArea propertyType;
    JLabel errorLabel;

    public RegistrationForm() {
        register = new JButton("Register");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addressNumber = new JTextArea();
        bathrooms = new JTextArea();
        bedrooms = new JTextArea();
        addressName = new JTextArea();
        quadrant = new JComboBox<String>(new String[] { "NW", "NE", "SE", "SW" });
        propertyType = new JTextArea();
        furnished = new JComboBox<String>(new String[] { "Furnished", "unfurnished" });
        errorLabel = new JLabel("");
    }

    public void displayForm() {
        JLabel address1Label = new JLabel("Address number");
        add(address1Label);
        add(addressNumber);
        JLabel address2Label = new JLabel("Address name");
        add(address2Label);
        add(addressName);
        JLabel bathRoomsLabel = new JLabel("Number of bathrooms");
        add(bathRoomsLabel);
        add(bathrooms);
        JLabel bedRoomsLabel = new JLabel("Number of bedrooms");
        add(bedRoomsLabel);
        add(bedrooms);
        JLabel propetyJLabel = new JLabel("Property type");
        add(propetyJLabel);
        add(propertyType);
        JLabel quadrantLabel = new JLabel("Quadrant");
        add(quadrantLabel);
        add(quadrant);
        JLabel furnishedLabel = new JLabel("Furnished");
        add(furnishedLabel);
        add(furnished);
        add(register);
        add(errorLabel);

    }

    public void displayError(String error) {
        errorLabel.setText(error);
        repaint();
    }

    // gets registration values in this order address number, addresss name,
    // bathrooms, bedrooms, property type, quadrant, furnished
    public String[] getValues() {
        return new String[] { addressNumber.getText(), addressName.getText(), bathrooms.getText(),
                bedrooms.getText(), propertyType.getText(), (String) quadrant.getSelectedItem(),
                (String) furnished.getSelectedItem() };
    }

}
