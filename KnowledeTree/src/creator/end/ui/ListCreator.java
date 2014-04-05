package creator.end.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListCreator extends JPanel implements ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JList node_list;
	public static DefaultListModel list_manager;
	
	public ListCreator() {		
		
		setLayout(new BorderLayout());
		
		list_manager = new DefaultListModel();
		list_manager.addElement("Node 1");
		list_manager.addElement("Node 2");
		list_manager.addElement("This is a humongous element");
		node_list = new JList(list_manager);
		
        node_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        node_list.setSelectedIndex(0);
        node_list.addListSelectionListener(this);
        node_list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(node_list);
        
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(new Button("Create Leaf"));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        //buttonPane.add(employeeName);
        buttonPane.add(new Button("Remove Leaf"));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
		
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
	            	System.out.println("Selected");
	         }
	     }
	 }
}
