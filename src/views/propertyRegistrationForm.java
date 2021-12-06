package views;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class propertyRegistrationForm extends JPanel {
    JButton sendButton;
    JTextField address;

    JComboBox<String> quadrant;
    JTextField numBathrooms;
    JTextField numBedrooms;
    JComboBox<String> furnished;
    JTextField propertyType;
    JLabel errorLabel;

    public propertyRegistrationForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        sendButton = new JButton("Submit property");
        address = new JTextField();
        quadrant = new JComboBox<String>(new String[] { "NW", "SW", "SE", "NE" });
        furnished = new JComboBox<String>(new String[] { "Furnished", "Unfurnished" });
        propertyType = new JTextField();
        numBathrooms = new JTextField();
        numBedrooms = new JTextField();
        errorLabel = new JLabel();

    }

    public void displayForm() {
        JLabel addressLabel = new JLabel("Address");
        add(addressLabel);
        add(address);
        JLabel quadrantLabel = new JLabel("Quadrant");
        add(quadrantLabel);
        add(quadrant);
        JLabel furnishedLabel = new JLabel("Furnished");
        add(furnishedLabel);
        add(furnished);
        JLabel propertyTypeLabel = new JLabel("Property type");
        add(propertyTypeLabel);
        add(propertyType);
        JLabel bathRoomsLabel = new JLabel("Number of bathrooms");
        add(bathRoomsLabel);
        add(numBathrooms);
        JLabel bedRoomsLabel = new JLabel("Number of bedrooms");
        add(bedRoomsLabel);
        add(numBedrooms);
        add(sendButton);
        add(errorLabel);

    }

    public void displayError(String error) {
        errorLabel.setText(error);
        repaint();
    }

    // returns all text and combobox field information in the following order
    // address,quadrant,furnished,property type, number of bathrooms, number of
    // bedrooms
    public String[] getFields() {
        String values[] = { address.getText(), (String) quadrant.getSelectedItem(),
                (String) furnished.getSelectedItem(),
                propertyType.getText(), numBathrooms.getText(), numBedrooms.getText() };
        return values;

    }
}