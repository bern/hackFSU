package creator.end.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import creator.end.api.KnowledgeNode;

import java.util.ArrayList;

public class NodeCreator extends JPanel {
	
	public static JLabel node_name_label;
	public static JLabel node_name;
	public static JLabel node_body_label;
	public static JTextArea node_body;
	public static JLabel node_pred_label;
	public static ArrayList<JLabel> node_pred;
	public static JPanel node_pred_panel;
	public static Button save_leaf;
	public static Button generate_xml;
	public static GridBagConstraints c;
	
	public NodeCreator() {
		
		setLayout(new GridBagLayout());
		c = new GridBagConstraints(); 
		
		JPanel name_panel = new JPanel();
		node_name_label = new JLabel("Name: ");
		node_name = new JLabel("[No node selected]");
		name_panel.add(node_name_label);
		name_panel.add(node_name);
		
		node_body_label = new JLabel("Body: ");
		node_body = new JTextArea(500,500);
		node_body.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(node_body); 
		node_body.setText("N/A");
		
		/*JPanel buttonPane = new JPanel();
		save_leaf = new JButton("Save Leaf");
		generate_xml = new JButton("Generate XML");
		buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(save_leaf);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        //buttonPane.add(employeeName);
        buttonPane.add(generate_xml);
        //buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));*/
		
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        save_leaf = new Button("Save Leaf");
        generate_xml = new Button("Generate XML");
        buttonPane.add(save_leaf);
        //buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        //buttonPane.add(Box.createHorizontalStrut(5));
        //buttonPane.add(employeeName);
        buttonPane.add(generate_xml);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		node_pred_label = new JLabel("Predecessors: ");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(name_panel,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		add(node_body_label,c);
		
		//c.gridwidth = 200;
		//c.gridheight = 100;
		c.ipady = 350;
		c.ipadx = 450;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(scrollPane,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(13,0,0,0);
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		add(node_pred_label,c);
		
		node_pred_panel = new JPanel();
		
		node_pred = new ArrayList<JLabel>();
		node_pred.add(new JLabel("Drag to add a leaf!"));
		
		for(int i = 0; i < node_pred.size(); i++) {
			Border a = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
			Border combined = BorderFactory.createCompoundBorder(b, a);
			node_pred.get(i).setBorder(combined);
			node_pred_panel.add(node_pred.get(i));
		}
		
		c.gridx = 0;
		c.gridy = 4;	
		c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(node_pred_panel,c);
		
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 5;
		add(buttonPane,c);
	}
	
	public void updateFields(KnowledgeNode node) {
		//System.out.println("what happened"+node.getName());
		node_name.setText(node.getName());
		node_body.setText(node.getBody());
		
		int length = node.getDependencies().size();
		
		remove(node_pred_panel);
		node_pred.clear();
		node_pred_panel = new JPanel();
		
		boolean goodToAdd = true;
		
		for(int i = 0; i < length; i++) {
			goodToAdd = true;
			for(int j = 0; j < node_pred.size(); j++) {
				if(node_pred.get(j).getText().equals(node.getDependencies().get(i).getName())) {
					goodToAdd = false;
				}
			}
			if(goodToAdd) {
				node_pred.add(new JLabel(node.getDependencies().get(i).getName()));
				//System.out.println("Added dependency "+node.getDependencies().get(i).getName());
			}
		}
		
		if(node_pred.size() == 0)
			node_pred.add(new JLabel("Drag to add a leaf!"));
		else
			node_pred.add(node_pred.size()-1, new JLabel("Drag to add a leaf!"));
		
		for(int i = node_pred.size()-1; i >= 0; i--) {
			Border a = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
			Border combined = BorderFactory.createCompoundBorder(b, a);
			node_pred.get(i).setBorder(combined);
			node_pred_panel.add(node_pred.get(i));
			//System.out.println("added" + node_pred.get(i).getText());
		}
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		this.add(node_pred_panel,c);
	}
}
