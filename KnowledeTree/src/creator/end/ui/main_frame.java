package creator.end.ui;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class main_frame extends JFrame {
	
	public static JPanel main_panel;
	public static node_creator node_panel;
	
	public static void main(String[] args) {
			main_frame Creator_UI = new main_frame("Test UI");
			Creator_UI.setSize(640,480);
			Creator_UI.setResizable(false);
			Creator_UI.setVisible(true);
			Creator_UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public main_frame(String s) {
		super(s);
		
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.X_AXIS));
		
		JLabel test1 = new JLabel ("test1");
		node_panel = new node_creator();
		
		main_panel.add(test1);//, BorderLayout.WEST);
		main_panel.add(node_panel);//, BorderLayout.CENTER);
		
		add(main_panel);
	}
}
