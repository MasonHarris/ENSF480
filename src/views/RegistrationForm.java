package views;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class RegistrationForm extends JPanel {
    JButton register;
    JTextField addressNumber;
    JTextField addressName;
    JTextField bathrooms;
    JTextField bedrooms;
    JComboBox<String> quadrant;
    JComboBox<String> furnished;
    JTextField propertyType;
    JLabel errorLabel;

    public RegistrationForm() {
        register = new JButton("Register");

        addressNumber = new JTextField();

        bathrooms = new JTextField();

        bedrooms = new JTextField();

        addressName = new JTextField();

        quadrant = new JComboBox<String>(new String[] { "NW", "NE", "SE", "SW" });

        propertyType = new JTextField();

        furnished = new JComboBox<String>(new String[] { "Furnished", "unfurnished" });
        errorLabel = new JLabel("");
        setLayout(null);
    }

    public void displayForm(int width, int height) {
        JLabel address1Label = new JLabel("Address number");
        address1Label.setBounds(10, (int) (height * 0.05), (int) (width * 0.1), (int) (height * 0.025));
        addressNumber.setBounds(110, (int) (height * 0.05), (int) (width * 0.1), (int) (height * 0.025));
        add(address1Label);
        add(addressNumber);

        JLabel address2Label = new JLabel("Address name");
        address2Label.setBounds(10, (int) (height * 0.08), (int) (width * 0.1), (int) (height * 0.025));
        addressName.setBounds(110, (int) (height * 0.08), (int) (width * 0.1), (int) (height * 0.025));
        add(address2Label);
        add(addressName);

        JLabel bathRoomsLabel = new JLabel("# of bathrooms");
        JTextField bathrooms = new JTextField();
        bathRoomsLabel.setBounds(10, (int) (height * 0.11), (int) (width * 0.1), (int) (height * 0.03));
        bathrooms.setBounds(110, (int) (height * 0.11), (int) (width * 0.1), (int) (height * 0.03));
        add(bathRoomsLabel);
        add(bathrooms);

        JLabel bedRoomsLabel = new JLabel("# of bedrooms");
        bedRoomsLabel.setBounds(10, (int) (height * 0.15), (int) (width * 0.1), (int) (height * 0.025));
        bedrooms.setBounds(110, (int) (height * 0.15), (int) (width * 0.1), (int) (height * 0.025));
        add(bedRoomsLabel);
        add(bedrooms);

        JLabel propetyJLabel = new JLabel("Property type");
        propetyJLabel.setBounds(10, (int) (height * 0.18), (int) (width * 0.1), (int) (height * 0.025));
        propertyType.setBounds(110, (int) (height * 0.18), (int) (width * 0.1), (int) (height * 0.025));
        add(propetyJLabel);
        add(propertyType);

        JLabel quadrantLabel = new JLabel("Quadrant");
        quadrantLabel.setLabelFor(quadrant);
        quadrantLabel.setBounds(110, (int) (height * 0.21), (int) (width * 0.1), (int) (height * 0.025));
        add(quadrantLabel);
        quadrant.setBounds(110, (int) (height * 0.23), (int) (width * 0.1), (int) (height * 0.025));
        add(quadrant);

        JLabel furnishedLabel = new JLabel("Furnished");
        furnishedLabel.setLabelFor(furnished);
        furnishedLabel.setBounds(110, (int) (height * 0.26), (int) (width * 0.1), (int) (height * 0.025));
        furnished.setBounds(110, (int) (height * 0.29), (int) (width * 0.1), (int) (height * 0.025));

        add(furnishedLabel);
        add(furnished);
        register.setBounds(110, (int) (height * 0.33), (int) (width * 0.1), (int) (height * 0.025));

        add(register);
        errorLabel.setBounds(110, (int) (height * 0.55), (int) (width * 0.3), (int) (height * 0.025));

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
