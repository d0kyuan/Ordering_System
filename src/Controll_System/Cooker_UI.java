package Controll_System;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Console_System.Cooker_order;
import Console_System.Cooker_orderRenderer;
import Console_System.Food_DB_Connn;
import Console_System.Menu;
import Console_System.MenuRenderer;
import Console_System.Order_DB_conn;
import Console_System.Updata_Order;

import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class Cooker_UI extends JFrame {
	JLayeredPane layeredPane;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cooker_UI frame = new Cooker_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static JList list;
	DefaultListModel<Cooker_order> dim2  = new DefaultListModel<>();
	DefaultListModel<Cooker_order> dim  = new DefaultListModel<>();
	public Cooker_UI() {
		layeredPane = new JLayeredPane();
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(59, 10, 96, 21);
		layeredPane.add(textField);
		textField.setColumns(10);
		textField.setText(Login_UI.staff_id);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("廚師 : ");
		textPane.setBounds(10, 10, 39, 21);
		layeredPane.add(textPane);
		
		 list = new JList();
		list.setBounds(20, 41, 140, 115);
		JScrollPane js =new JScrollPane(list);
		js.setBounds(20, 41, 140, 115);
		layeredPane.add(js);
		js.setBounds(261, 12, 140, 196);
		
		JList list_1 = new JList();
		list_1.setBounds(37, 41, 107, 125);
		layeredPane.add(list_1);
		
		JButton btnNewButton = new JButton("接單");
		btnNewButton.setBounds(170, 49, 87, 23);
		layeredPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cooker_order me = (Cooker_order) list.getSelectedValue();
				String order_id = me.getName();
				Updata_Order.get_order_list(
						"jdbc:mysql://localhost:3306/controll_system?autoReconnect=true&useSSL=false",
						"System_use",
						"system123",
						order_id,
						1,
						1
				);
				list_1.setCellRenderer(new Cooker_orderRenderer());
				dim.addElement(me);
				list_1.setModel(dim);
				
			}
		} );
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("製作中");
		rdbtnNewRadioButton.setBounds(20, 172, 107, 23);
		rdbtnNewRadioButton.setActionCommand("2");
		layeredPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("完成");
		rdbtnNewRadioButton_1.setBounds(20, 197, 107, 23);
		rdbtnNewRadioButton_1.setActionCommand("3");
		layeredPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("錯誤");
		rdbtnNewRadioButton_2.setBounds(20, 222, 107, 23);
		rdbtnNewRadioButton_2.setActionCommand("4");
		layeredPane.add(rdbtnNewRadioButton_2);
		
		 ButtonGroup group = new ButtonGroup();
		 group.add(rdbtnNewRadioButton);
		 group.add(rdbtnNewRadioButton_1);
		 group.add(rdbtnNewRadioButton_2);
		
		
		
		JButton btnNewButton_1 = new JButton("更改狀態");
		btnNewButton_1.setBounds(134, 222, 87, 23);
		layeredPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				String a = group.getSelection().getActionCommand();
				Cooker_order me = (Cooker_order) list_1.getSelectedValue();
				String order_id = me.getName();
				System.out.println(a);
				Updata_Order.get_order_list(
						"jdbc:mysql://localhost:3306/controll_system?autoReconnect=true&useSSL=false",
						"System_use",
						"system123",
						order_id,
						1,
						Integer.valueOf(a)
				);
				list_1.setCellRenderer(new Cooker_orderRenderer());
				if(a.equals("3")){
					dim.removeElement(me);
				}
				list_1.setModel(dim);
				
			}
			
			
			
			
		});
		
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Main_System frame;
				try {
					frame = new Main_System();
					frame.setVisible(true);
					Cooker_UI.this.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(238, 222, 87, 23);
		layeredPane.add(btnNewButton_2);
		
	
		
		//TODO get_cooker order 
		Thread thread = new Thread(new Runnable() {
            public void run() {
	            	while(true){
	            	
	            	
	            	dim2=Order_DB_conn.get_order_list(
							"jdbc:mysql://localhost:3306/controll_system?autoReconnect=true&useSSL=false",
							"System_use",
							"system123",
							"select * from order_record where record_cooker = 4 and record_schedule = 0"); //Login_UI.staff_id);
	        		list.setCellRenderer(new Cooker_orderRenderer());
	            	list.setModel(dim2);
	        		list.setFont(new Font("微軟正黑體", Font.BOLD, 14));
	            	
	        		
	        		
	        		try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            	}
	            	
            }
           });
        thread.start();
	}
	 
	    private static int timercun =0;
}
