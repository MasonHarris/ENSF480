package views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class feeForm extends JPanel {
    JButton submit;
    JTextField fee;
    JTextField period;
    JLabel errorLabel;

    public feeForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public void displayFees(int periodValue, double feesValue) {
        fee = new JTextField(Double.toString(feesValue));
        period = new JTextField(Integer.toString(periodValue));
        submit = new JButton("Submit");
        JLabel feeLabel = new JLabel("Fee value");
        add(feeLabel);
        add(fee);
        JLabel periodLabel = new JLabel("Period");
        add(periodLabel);
        add(period);
        add(submit);
        errorLabel = new JLabel();
        add(errorLabel);

    }

    public String[] getFields() {
        return new String[] { fee.getText(), period.getText() };
    }

    public void displayError(String error) {
        errorLabel.setText(error);
        repaint();
    }

}
