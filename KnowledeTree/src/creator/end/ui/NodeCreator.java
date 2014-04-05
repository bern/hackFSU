package creator.end.ui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class NodeCreator extends JPanel {
	
	public static JLabel node_name_label;
	public static JLabel node_name;
	public static JLabel node_body_label;
	public static JTextArea node_body;
	public static JLabel node_pred_label;
	public static ArrayList<JLabel> node_pred;
	
	public NodeCreator() {
		setLayout(new GridLayout(6,1));
		
		node_name_label = new JLabel("Name: ");
		node_name = new JLabel("[No node selected]");
		
		node_body_label = new JLabel("Body: ");
		node_body = new JTextArea();
		node_body.setText("N/A");
		
		node_pred_label = new JLabel("Predecessors: ");
		
		add(node_name_label);
		add(node_name);
		add(node_body_label);
		add(node_body);
		add(node_pred_label);
	}
}
