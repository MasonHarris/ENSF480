package views;

import javax.swing.*;

import java.awt.event.ActionListener;

public abstract class GUIview {
	JFrame frame;
	JPanel panel;
	int width;
	int height;
	JButton backButton;

	public GUIview(int width, int height) {

		this.width = width;
		this.height = height;
		backButton = new JButton("Back to dashboard");
		// System.out.println("width " + this.width);
		// System.out.println("height " + this.height);
		// initalizeFrame(frameName);

	}

	// creates and saves the frame into memory
	public void initalizeFrame(String frameName) {
		frame = new JFrame(frameName);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// destroys frame
	public void dispose() {
		frame.dispose();
	}

	public void addBackListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}

	public void confirmation(String message) {
		JDialog confirm = new JDialog(frame, message);
		JLabel l = new JLabel("<html> " + message + "<br/><br/>Close this Dialog Box to continue</html>");
		confirm.add(l);
		// setsize of dialog
		confirm.setSize(400, 200);
		confirm.setModal(true);
		confirm.setVisible(true);
	}

}