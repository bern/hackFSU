package creator.end.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import creator.end.api.KnowledgeNode;

public class ListCreator extends JPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	public static JList node_list;
	public static NodeCreator node_panel;
	public static Button create_leaf;
	public static Button remove_leaf;
	public static DefaultListModel list_manager;
	public static KnowledgeNode n;
	public static ArrayList<KnowledgeNode> nodes;
	
	public ListCreator(ArrayList<KnowledgeNode> nodes, NodeCreator node_panel) {		
		
		setLayout(new BorderLayout());
		
		this.node_panel = node_panel;
		this.nodes = nodes;
		
		list_manager = new DefaultListModel();
		
		for(int i = 0; i < nodes.size(); i++) {
			list_manager.addElement((String)(nodes.get(i).getName()));
		}
		
		node_list = new JList(list_manager);
		
        node_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        node_list.setSelectedIndex(0);
        node_list.addListSelectionListener(this);
        node_list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(node_list);
        
        create_leaf = new Button("Create Leaf");
        remove_leaf = new Button("Remove Leaf");
        
        create_leaf.addActionListener(new ListManager("create"));
        remove_leaf.addActionListener(new ListManager("remove"));
        
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(create_leaf);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        //buttonPane.add(employeeName);
        buttonPane.add(remove_leaf);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
		
        node_panel.updateFields(nodes.get(0));
        
		setBackground(Color.WHITE);
	}
	
	public class ListManager implements ActionListener {
		
		public String btnName;
		
		public ListManager(String n) {
			btnName = n;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(btnName.equals("create")) {
				KnowledgeNode empty = new KnowledgeNode();
				empty.setName("Empty Leaf");
				empty.setBody("There's nothing here. Add some information!");
				empty.setId(0);
				nodes.add(empty);
				list_manager.addElement((String)(nodes.get(nodes.size()-1).getName()));
			}
			else if(btnName.equals("remove")) {
				System.out.println("SELECTED INDEX TO REMOVE "+node_list.getSelectedIndex());
				int x = node_list.getSelectedIndex();
				list_manager.remove(x);
				nodes.remove(x);
			}
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
	     if (e.getValueIsAdjusting() == false) {
	
	         if (node_list.getSelectedIndex() == -1) {
	         //No selection, disable fire button.
	             //fireButton.setEnabled(false);
	            } else {
	         //Selection, enable the fire button.
	             //fireButton.setEnabled(true);
	            	System.out.println("Selected");
	            	String nodeName = nodes.get(node_list.getSelectedIndex()).getName();
	            	node_panel.updateFields(nodes.get(node_list.getSelectedIndex()));
	         }
	     }
	 }
}
