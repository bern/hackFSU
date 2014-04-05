package creator.end.ui;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class MainFrame extends JFrame {
	
	public static JPanel main_panel;
	public static ListCreator list_panel;
	public static NodeCreator node_panel;
	
	public static void main(String[] args) {
			MainFrame Creator_UI = new MainFrame("Test UI");
			Creator_UI.setSize(800,600);
			Creator_UI.setResizable(false);
			Creator_UI.setVisible(true);
			Creator_UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public MainFrame(String s) {
		super(s);
		
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.X_AXIS));
		//main_panel.add(Box.createRigidArea(new Dimension(150, 500)));
		
		list_panel = new ListCreator();
		
		//JLabel test1 = new JLabel ("test1");
		node_panel = new NodeCreator();
		
		Border a = BorderFactory.createEmptyBorder(10, 30, 10, 30);
		Border a2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border b = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border combined = BorderFactory.createCompoundBorder(b, a);
		Border combined2 = BorderFactory.createCompoundBorder(b, a2);
		
		list_panel.setBorder(combined2);
		node_panel.setBorder(combined);
		
		list_panel.setMaximumSize(new Dimension(300,600));
		
		main_panel.add(list_panel);//, BorderLayout.WEST);
		main_panel.add(node_panel);//, BorderLayout.CENTER);
		
		add(main_panel);
	}
}
