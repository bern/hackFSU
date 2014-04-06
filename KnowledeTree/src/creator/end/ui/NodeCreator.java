package creator.end.ui;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import creator.end.api.KnowledgeNode;

import java.util.ArrayList;

public class NodeCreator extends JPanel {
	
	public static JLabel node_name_label;
	public static JTextField node_name;
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
		node_name = new JTextField("");
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
        save_leaf.addActionListener(new NodeManager("save"));
        generate_xml = new Button("Generate XML");
        generate_xml.addActionListener(new NodeManager("gen"));
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
		
		for(int i = 0; i < node_pred.size(); i++) {
			Border a = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
			Border combined = BorderFactory.createCompoundBorder(b, a);
			node_pred.get(i).setBorder(combined);
			node_pred_panel.add(node_pred.get(i));
		}
		
		c.gridx = 0;
		c.gridy = 4;
		c.ipadx = 500;
		c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(node_pred_panel,c);
		
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 5;
		add(buttonPane,c);
	}
	
	public class NodeManager implements ActionListener {
		
		public String btnName;
		
		public NodeManager(String n) {
			btnName = n;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(btnName.equals("save")) {
				ListCreator.nodes.get(ListCreator.node_list.getSelectedIndex()).setName(node_name.getText());
				ListCreator.nodes.get(ListCreator.node_list.getSelectedIndex()).setBody(node_body.getText());
				int length = ListCreator.nodes.size();
				int index = ListCreator.node_list.getSelectedIndex();
				
				for(int i = index; i < length; i++) {
					ListCreator.list_manager.remove(index);
				}
				
				for(int i = index; i < length; i++) {
					ListCreator.list_manager.addElement(ListCreator.nodes.get(i).getName());
				}
				if(ListCreator.setChildren) {
					ListCreator.setChildren = false;
					ListCreator.add_dep.setLabel("Add Children");
				}
			}
			else if(btnName.equals("gen")) {
			}
		}
	}
	
	public void updateFields(KnowledgeNode node) {
		//System.out.println("what happened"+node.getName());
		node_name.setText(node.getName());
		node_body.setText(node.getBody());
		
		int length = node.getDependencies().size();
		
		node_pred.clear();
		node_pred_panel.removeAll();
		
		//try{Thread.sleep(1000);}catch(Exception e){}
		
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
			}
		}
		
		for(int i = node_pred.size()-1; i >= 0; i--) {
			Border a = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
			Border combined = BorderFactory.createCompoundBorder(b, a);
			node_pred.get(i).setBorder(combined);
			node_pred_panel.add(node_pred.get(i));
		}
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		node_pred_panel.updateUI();
		this.add(node_pred_panel,c);
	}
}
