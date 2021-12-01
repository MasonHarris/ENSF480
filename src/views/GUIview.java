package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public abstract class GUIview {
	JFrame frame;
	JPanel panel;
	int width;
	int height;
	public GUIview(String frameName,int width, int height) {
		frame = new JFrame(frameName);
		frame.setSize(width,height);
		this.width = width;
		this.height = height;
		System.out.println("width " + this.width);
		System.out.println("height " + this.height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
    public void dispose() {
    	frame.dispose();
    }

}
