package Console_System;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class Cooker_orderRenderer extends JLabel implements ListCellRenderer<Cooker_order> {
	  public Cooker_orderRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getListCellRendererComponent(JList<? extends Cooker_order> list, Cooker_order Menu, int index,
	            boolean isSelected, boolean cellHasFocus) {
	    	String t1=Menu.getName(),space="";
	    	int i1 = Menu.getName().length();

	        setText(Menu.getfood()+ "" +Menu.getschedule() );
	        
	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }

	        return this;
	    }
}
