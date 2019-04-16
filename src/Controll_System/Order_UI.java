package Controll_System;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.text.AbstractDocument.Content;

import org.magiclen.magicqrcode.QRCodeEncoder;

import Console_System.Food_DB_Connn;
import Console_System.Get_String;
import Console_System.Menu;
import Console_System.MenuRenderer;
import Console_System.Orderrecord_DB_Conn;

import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;



public class Order_UI extends JFrame  implements  ActionListener{
	private JLayeredPane layeredPane_1;
	private JLayeredPane layeredPane_2;
	private JLayeredPane layeredPane;
	private JPanel contentPane;
	private JTextField textField_3;
	private int totalprice;
	static  Order_UI main_frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					main_frame = new Order_UI();
					main_frame.setVisible(true);
					main_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					main_frame.addWindowListener(new WindowAdapter() {
				      public void windowClosing(WindowEvent e) {
				        int result=JOptionPane.showConfirmDialog(main_frame,
				                   "確定要結束程式嗎?",
				                   "確認訊息",
				                   JOptionPane.YES_NO_OPTION,
				                   JOptionPane.WARNING_MESSAGE);
				        if (result==JOptionPane.YES_OPTION) {System.exit(0);}
				        }    
				      });
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Get_String gs = new Get_String("Order_UI");
	//菜單
	DefaultListModel<Menu> dim  = new DefaultListModel<>();
	DefaultListModel<Menu> dim2  = new DefaultListModel<>();
	JList<Menu> list;
	String[] foodname;
	JTextField pricetext;
	static String Staff_id;
	//pre data from database 

	
	
	Boolean tickt= true;
	int[] foodprice;
	/**
	 * Create the frame.
	 */
	
	public static void setstaff_id (String St){
		Staff_id = St;
	}
	public Order_UI() {
		
		
		
		setTitle(gs.get_name("Title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		
	
		
		
	
        
		//TODO order list
		list = new JList<>();
		list.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		
		list.setCellRenderer(new MenuRenderer());
		
		list.setBounds(284, 12, 130, 183);

		JScrollPane js =new JScrollPane(list);
		js.setBounds(284, 12, 130, 153);
		layeredPane.add(js);

	
		
		//TODO get food menu and price
				
				
		//TODO layeredPane_1 setting
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBackground(new Color(102, 204, 51));
		layeredPane_1.setBounds(21, 42, 264, 153);
	
		layeredPane.add(layeredPane_1);

		
		//TODO noodles button
		JButton btnNewButton = new JButton(gs.get_name("noodlesclass"));
		btnNewButton.addActionListener((ActionListener) this);
		btnNewButton.setActionCommand("noodles");
		btnNewButton.setBounds(0, 0, 127, 50);
		layeredPane_1.add(btnNewButton);
		
		//TODO rice button
		JButton button = new JButton(gs.get_name("riceclass"));
		button.addActionListener(this);
		button.setActionCommand("rice");
		button.setBounds(127, 0, 127, 50);
		layeredPane_1.add(button);
		
		//TODO soup button
		JButton button_1 = new JButton(gs.get_name("soupclass"));
		button_1.addActionListener(this);
		button_1.setActionCommand("soup");
		button_1.setBounds(127, 51, 127, 50);
		layeredPane_1.add(button_1);
		
		//TODO dumpling button
		JButton button_2 = new JButton(gs.get_name("dumplinglclass"));
		button_2.addActionListener(this);
		button_2.setActionCommand("dumpling");
		button_2.setBounds(0, 51, 127, 50);
		layeredPane_1.add(button_2);
		
		//TODO drink button
		JButton button_3 = new JButton(gs.get_name("drinklclass"));
		button_3.addActionListener(this);
		button_3.setActionCommand("drink");
		button_3.setBounds(127, 102, 127, 50);
		layeredPane_1.add(button_3);
		
		//TODO dessert button
		JButton button_4 = new JButton(gs.get_name("dessertclass"));
		button_4.addActionListener(this);
		button_4.setActionCommand("dessert");
		button_4.setBounds(0, 102, 127, 50);
		layeredPane_1.add(button_4);
		
		//TODO price show
		pricetext= new JTextField();
		pricetext.setEditable(false);
		pricetext.setBounds(329, 205, 95, 31);
		layeredPane.add(pricetext);
		pricetext.setColumns(10);
		
		//TODO price name text
		JTextField textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText(gs.get_name("Totalprice"));
		textField_1.setBounds(284, 205, 41, 31);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		//TODO send the order 
		
		JButton btnNewButton_1 = new JButton(gs.get_name("sendmell"));
		btnNewButton_1.setBounds(21, 204, 121, 43);
		btnNewButton_1.setActionCommand("sendmell");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tickt){
					tickt=false;
					JFrame frame = new JFrame();
					
					//TODO send order and get order name
					String code = Orderrecord_DB_Conn.send_order(Login_UI.staff_id, dim, totalprice);
					//TODO create qrcode
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					Date now = new Date();
					java.sql.Date sqlDate = new java.sql.Date(now.getTime());
					String url="http://127.0.0.1/java_end_proj/page/order_record.html?ordername="+code;
					//"Who_Order : "+Login_UI.staff_id+" tickttime:"+sqlDate +"tickid "+code
					QRCodeEncoder qr = new QRCodeEncoder(url);
					System.out.print(url);
					boolean[][] qrData = qr.encode();
					//TODO draw ticke

					if(code.indexOf("errorcode:")>1){
						JOptionPane.showMessageDialog(
								  null,
								  "點餐並未送出!發生錯誤" ,
								  "錯誤!" ,
								  JOptionPane.ERROR_MESSAGE
								  );
					}
					frame.setContentPane (new JScrollPane( new JPanel (){
			            public void paint (Graphics g)
			            {
			            	int width = 140;
			            	int height = 140;
			            	final int imageSize = Math.min(width, height);
				        	final int length = qrData.length;
				        	final int size = imageSize / length;
				        	final int actualImageSize = size * length;
				        	final int offsetImageX = (width - actualImageSize) / 2;
				        	final int offsetImageY = (height - actualImageSize) / 2;
			            	g.setColor(Color.BLACK);
				        	for (int i = 0; i < length; i++) {
				        		for (int j = 0; j < length; j++) {
				        			if (qrData[i][j]) {
				        				final int x = i * size + offsetImageX+90;
				        				final int y = j * size + offsetImageY;
				        				g.fillRect(x, y, size, size);
				        			}
				        		}
				        	}
				        	int i2 = 0;
				        	String t1 ="";
				        	String name="";
				        	String space="", space2="";
				        	for(int i=0;i<dim.size();i++){
								Menu me =dim.getElementAt(i);
								space="";
								name =me.getName();
								t1=me.getprice()+"";
								int i1 = name.length();
								
								if(i1>i2){i2=i1;}
						    	for(int k = 0;k<5-i1;k++){
						    		space+="　";
						    	}

								g.drawString(name+space+me.getprice(), 110, 160+(20*(i+1)));
								
								
							}
				        	String k="";
				        	for(int i = 0;i<name.length()+space.length()+t1.length();i++){
				        		k+="－";
				        	}
				        	
				        
				        	g.drawString(k,110,150);
				        	g.drawString(k,110,160+(20*(dim.size()+1)));
				        	g.drawString("總共"+totalprice+"元",110,160+(20*(dim.size()+2)));
				        	g.drawString("服務人員編號  : "+Login_UI.staff_id, 20, 160+(20*(dim.size()+4)));
				        	
				        	g.drawString("單據編號編號  : "+code, 20, 160+(20*(dim.size()+5)));
			            }
			         }));
					frame.setBounds(450, 100, 370, 500);
					frame.setVisible(true);
			        
					frame.addWindowListener(new WindowAdapter() {
				      public void windowClosing(WindowEvent e) {
				    	  tickt=true;
				    	  frame. setVisible(false); 
				    	  frame. dispose(); 
				        }    
				      });
				}else{
					//TODO error for tickt is show 
					JOptionPane.showMessageDialog(
							  null,
							  "發票已經產生 請勿重新送出點餐" ,
							  "錯誤!" ,
							  JOptionPane.ERROR_MESSAGE
							  );
				}
			}
		});
		layeredPane.add(btnNewButton_1);
		
		//TODO back to menu button
		JButton button_5 = new JButton(gs.get_name("backmenu"));
		button_5.setBounds(152, 204, 122, 43);
		button_5.setActionCommand("backmenu");
		button_5.addActionListener(this);
		layeredPane.add(button_5);
		
		
		//TODO work ID text
		JTextField textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText(gs.get_name("staffid"));
		textField_2.setBounds(21, 10, 75, 21);
		layeredPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		//TODO show work ID
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(106, 10, 168, 21);
		layeredPane.add(textField_3);
		textField_3.setText(Login_UI.staff_id);
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//TODO dele mel
		JButton btnDelect = new JButton(gs.get_name("del"));
		btnDelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Menu me = (Menu) list.getSelectedValue();
					totalprice-=me.getprice();
					pricetext.setText(totalprice+"元");
					dim.removeElement(me);
					list.setModel(dim);
				}catch(Exception ex){
					//TODO no selet dele mel
					  JOptionPane.showMessageDialog(
							  null,
							  "你沒有選擇要刪除的餐點" ,
							  "錯誤!" ,
							  JOptionPane.ERROR_MESSAGE
							  );
				}
				
			}
		});
		btnDelect.setBounds(308, 175, 87, 23);
		layeredPane.add(btnDelect);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getActionCommand().toString()){
			case "noodles":		
				Switch_DB(1);
				break;
			case "rice":
				//TODO layeredPane_1 change to rice menu
				Switch_DB(2);
				break;
			case "soup":
				//TODO layeredPane_1 change to soup menu
				break;
			case "dumpling":
				//TODO layeredPane_1 change to dumpling menu
				break;
			case "drink":
				//TODO layeredPane_1 change to drink menu
				break;
			case "dessert":
				//TODO layeredPane_1 change to dessert menu
				break;
			case "backmenu":
				//TODO logout 
				
				//TODO back to Main_System
				try{
					Main_System frame1 = new Main_System();
					frame1.setVisible(true);
					this.setVisible(false);
				}catch(Exception ex){
					
				}
				break;
		}
		
	}
	public  void Switch_DB(int i){
		//TODO get noodle list from database
		
		try{
		
			dim2=Food_DB_Connn.get_food_list(
					"jdbc:mysql://localhost:3306/controll_system?autoReconnect=true&useSSL=false",
					"System_use",
					"system123",
					"select * from food_list where food_class like "+i
					);
		
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex+"", "錯誤!", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
		//TODO layeredPane_1 change to noodles menu
		this.layeredPane_1.setVisible(false);
		
		//TODO add new board
		layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBackground(new Color(102, 204, 51));
		layeredPane_2.setBounds(21, 41, 253, 153);
		layeredPane.add(layeredPane_2);
		
		//TODO add list on board
		JList<Menu> list_1 = new JList<Menu>();
		list_1.setCellRenderer(new MenuRenderer());
		JScrollPane js1 =new JScrollPane(list_1);
		js1.setBounds(62, 10, 101, 100);
		layeredPane_2.add(js1);
		list_1.setModel(dim2);
		list_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		
		//TODO add addmeu button on board
		JButton btnNewButton_2 = new JButton(gs.get_name("addnew"));
		btnNewButton_2.setBounds(10, 120, 87, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu me = (Menu) list_1.getSelectedValue();
				int a = me.getprice();
				totalprice+=a;
				pricetext.setText(totalprice+"元");
				dim.addElement(me);
				list.setModel(dim);
				
			}
		} );
		layeredPane_2.add(btnNewButton_2);
		
		//TODO add  return button  on board
		JButton btnNewButton_3 = new JButton(gs.get_name("back"));
		btnNewButton_3.setBounds(156, 120, 87, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane_2.setVisible(false);
				layeredPane_1.setVisible(true);
			}
		} );
		layeredPane_2.add(btnNewButton_3);
		
		
		this.layeredPane_2.setVisible(true);
		
		
	}
}

