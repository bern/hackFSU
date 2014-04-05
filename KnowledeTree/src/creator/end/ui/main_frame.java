package creator.end.ui;
import java.awt.*;
import javax.swing.*;

public class main_frame extends JFrame{
	public static void main(String[] args) {
			main_frame Creator_UI = new main_frame("Test UI");
			Creator_UI.setSize(640,480);
			Creator_UI.setVisible(true);
			Creator_UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public main_frame(String s) {
		super(s);
	}
}
