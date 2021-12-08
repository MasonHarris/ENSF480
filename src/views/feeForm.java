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
        setLayout(null);
        submit = new JButton("Submit");

    }

    public void displayFees(int width, int height, int periodValue, double feesValue) {
        this.removeAll();
        fee = new JTextField(Double.toString(feesValue));

        period = new JTextField(Integer.toString(periodValue));
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
        errorLabel.setBounds(110, (int) (height * 0.20), (int) (width * 0.1), (int) (height * 0.025));
        add(period);
        add(periodLabel);
        add(fee);
        add(feeLabel);
        add(submit);
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
