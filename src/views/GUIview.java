package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public abstract class GUIview {
	JFrame frame;
	JPanel panel;
	
	public GUIview() {
		
		
	}
    public void dispose() {
    	frame.dispose();
    }

}
