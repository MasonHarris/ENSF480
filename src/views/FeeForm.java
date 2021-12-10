package views;

import java.util.AbstractMap.SimpleEntry;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
//this class is used by managers to set/change fees
public class FeeForm extends JPanel {
    JButton submit;
    JTextField fee;
    JTextField period;
    JLabel errorLabel;

    public FeeForm() {
        setLayout(null);
        submit = new JButton("Submit");

    }

    public void displayFees(int width, int height, SimpleEntry<Integer, Double> feePair) {
        this.removeAll();
        fee = new JTextField(Double.toString(feePair.getValue()));

        period = new JTextField(Integer.toString(feePair.getKey()));
        int distance = (int) (width * 0.08);
        int boxWidth = (int) (width * 0.09);
        fee.setBounds(distance, (int) (height * 0.05), boxWidth, (int) (height * 0.025));
        JLabel feeLabel = new JLabel("Fees(dollars)");
        feeLabel.setBounds(10, (int) (height * 0.05), (int) (width * 0.1), (int) (height * 0.025));
        JLabel periodLabel = new JLabel("Period(days)");
        period.setBounds(distance, (int) (height * 0.10), boxWidth, (int) (height * 0.025));
        periodLabel.setBounds(10, (int) (height * 0.10), (int) (width * 0.1), (int) (height * 0.025));
        submit.setBounds(110, (int) (height * 0.15), (int) (width * 0.1), (int) (height * 0.025));

        errorLabel = new JLabel("");
        errorLabel.setBounds(110, (int) (height * 0.30), (int) (width * 0.1), (int) (height * 0.025));
        add(period);
        add(periodLabel);
        add(fee);
        add(feeLabel);
        add(submit);
        add(errorLabel);

    }
    //returns string array in format {fee value, fee period}
    public String[] getFields() {
        return new String[] { fee.getText(), period.getText() };
    }

    public void displayError(String error) {
        errorLabel.setText(error);
        repaint();
    }

}
