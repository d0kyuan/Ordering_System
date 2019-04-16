package Console_System;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class MenuRenderer extends JLabel implements ListCellRenderer<Menu> {

    public MenuRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Menu> list, Menu Menu, int index,
            boolean isSelected, boolean cellHasFocus) {
    	String t1=Menu.getName(),space="";
    	int i1 = Menu.getName().length();
    	for(int i = 0;i<5-i1;i++){
    		space+="¡@";
    	}
        setText(t1 + space + Menu.getprice()+"¤¸");
        
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
