
package views;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

//form class for a form with 2 fields
public lass

    JTextField emailAddress;
        JTe
        JButton sendButton;
            JLa
        el errorLa 
        public emailForm() {
            sendButton = ne
            setLayout(null

            }

    
    pub

        this.removeAll();
        JLabel addressLabel = new JLabel("Email Address");
        addressLabel.setBounds(10, (int) (0.1 * height), (int) (0.2 * width), (int) (0.05 * height));
        emailAddress = new JTextField();
        emailAddress.setBounds(110, (int) (0.1 * height), (int) (0.05 * width), (int) (0.05 * height));
        JLabel bodyLabel = new JLabel("Body");
        bodyLabel.setBounds(10, (int) (0.2 * height), (int) (0.05 * width), (int) (0.05 * height));
        emailBody = new JTextArea();
        emailBody.setBounds(110, (int) (0.2 * height), (int) (0.4 * width), (int) (0.4 * height));

        JLabel errorLabel = new JLabel();
        sendButton.setBounds(110, (int) (0.61 * height), (int) (0.1 * width), (int) (0.025 * height));
        errorLabel.setBounds(110, (int) (0.67 * height), (int) (0.1 * width), (int) (0.05 * height));

        add(addressLabel);
        add(emailAddress);
        add(bodyLabel);
        add(emailBody);
        add(sendButton);
        add(errorLabel);

    }

    public void displayError(String error) {
        errorLabel.setText(error);
        repaint();
    }

    // first value in array is email address, second value is email text
    public String[] getEmail() {
        return new String[] { emailAddress.getText(), emailBody.getText() };
    }

}
        

        
            
            
        

        

    