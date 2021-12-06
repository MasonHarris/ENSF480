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
		backButton = new JButton("Return home");
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

	public void addBackListener(ActionListener l) {
		backButton.addActionListener(l);
	}

	// destroys frame
	public void dispose() {
		frame.dispose();
	}

}
