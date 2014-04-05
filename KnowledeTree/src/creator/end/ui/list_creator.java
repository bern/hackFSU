package creator.end.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class list_creator extends JPanel implements ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JList node_list;
	public static DefaultListModel list_manager;
	
	public list_creator() {		
		list_manager = new DefaultListModel();
		list_manager.addElement("Node 1");
		list_manager.addElement("Node 2");
		node_list = new JList(list_manager);
		
        node_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        node_list.setSelectedIndex(0);
        node_list.addListSelectionListener(this);
        node_list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(node_list);
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); 
        add(listScrollPane, BorderLayout.CENTER);
		
		list_manager.addElement("Node 3");
		
		setBackground(Color.WHITE);
	}
	
	public void valueChanged(ListSelectionEvent e) {
	     if (e.getValueIsAdjusting() == false) {
	
	         if (node_list.getSelectedIndex() == -1) {
	         //No selection, disable fire button.
	             //fireButton.setEnabled(false);
	            } else {
	         //Selection, enable the fire button.
	             //fireButton.setEnabled(true);
	         }
	     }
	 }
}
