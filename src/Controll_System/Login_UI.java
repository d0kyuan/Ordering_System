package Controll_System;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Console_System.Food_DB_Connn;
import Console_System.Get_String;
import Console_System.Staff_DB_Conn;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Login_UI extends JFrame implements ActionListener{

	public static JPanel contentPane;
	public static JPasswordField passwordField;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JButton button_1;
	JTextPane textPane;
	JLayeredPane layeredPane;
	 
	public static String staff_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_UI frame = new Login_UI();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {
				      public void windowClosing(WindowEvent e) {
				        int result=JOptionPane.showConfirmDialog(frame,
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

	/**
	 * Create the frame.
	 */
	Get_String gs = new Get_String("Login_UI");
	public Login_UI() {
		setTitle(gs.get_name("Title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
 
		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(187, 93, 127, 21);
		layeredPane.add(passwordField);
		
		textPane = new JTextPane();
		textPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textPane.setBounds(187, 57, 127, 21);
		layeredPane.add(textPane);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBorder(null);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText(gs.get_name("password"));
		textField.setBounds(109, 93, 35, 20);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBorder(null);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText(gs.get_name("account"));
		textField_1.setBounds(109, 57, 35, 20);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton(gs.get_name("login"));
		button.setBounds(109, 165, 87, 23);
		button.setActionCommand("login");
		button.addActionListener(this);
		layeredPane.add(button);
		
		button_1 = new JButton(gs.get_name("cancel"));
		button_1.setBounds(227, 165, 87, 23);
		button_1.setActionCommand("cancel");
		button_1.addActionListener(this);
		layeredPane.add(button_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand().toString()){
		case "login":
			//TODO for login testing

	
			String feed_back="def";
			try{
			
				feed_back=Staff_DB_Conn.Login(
						textPane.getText(),
						passwordField.getText(),
						"jdbc:mysql://localhost:3306/controll_system?autoReconnect=true&useSSL=false"
						);
				System.out.println(feed_back);
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, ex+"", "錯誤!", JOptionPane.ERROR_MESSAGE);
			}
			
			switch(feed_back){
				case "def":
					System.out.print("system_problem");
					break;
				case "5":
					 JOptionPane.showMessageDialog(
							  null,
							  "無此帳號" ,
							  "錯誤!" ,
							  JOptionPane.ERROR_MESSAGE
							  );
					break;
				case "6":
					 JOptionPane.showMessageDialog(
							  null,
							  "密碼錯誤!" ,
							  "錯誤!" ,
							  JOptionPane.ERROR_MESSAGE
							  );
					break;
				case "7":
					 JOptionPane.showMessageDialog(
							  null,
							  "帳號或是密碼含有特殊字元" ,
							  "錯誤!" ,
							  JOptionPane.ERROR_MESSAGE
							  );
					break;
				default:
					String[] temp = feed_back.split(":");
					System.out.println(temp[0]);
					switch(Main_System.go){
						case 1:
							//TODO waiter go 
							System.out.println((!temp[0].equals("2")));
							
							if ( (!temp[0].equals("2")) ){
								 JOptionPane.showMessageDialog(
										  null,
										  "您並沒有權限進入!!!" ,
										  "錯誤!" ,
										  JOptionPane.ERROR_MESSAGE
										  );
							}else{
								//TODO timer
								calltimer();
								//TODO ss to login 
								staff_id= temp[1];
								//TODO start timer!
								
								//TODO set visible
								Order_UI frame = new Order_UI();
								frame.setVisible(true);
								this.setVisible(false);	
							}
							break;
						case 2:
							if ((!temp[0].equals("1")) ){
								 JOptionPane.showMessageDialog(
										  null,
										  "您並沒有權限進入!!!" ,
										  "錯誤!" ,
										  JOptionPane.ERROR_MESSAGE
										  );
							}else{
								//TODO　cooker go 
								staff_id= temp[1];
								//TODO start timer!
								
								//TODO set visible
								Cooker_UI frame = new Cooker_UI();
								frame.setVisible(true);
								this.setVisible(false);	
							}
							break;
						case 3:
							break;
						case 4:
							break;
					}
					
					
				
			}
			
			
			break;
		case "cancel":
			try{
			Main_System frame1 = new Main_System();
			frame1.setVisible(true);
			this.setVisible(false);
			}catch(Exception ex){
				
			}
			break;

	}
		
		
	}
	
	private void calltimer(){
		   Timer timer = new Timer();
		   timer.schedule(new WorkTimeCounter(), 100000);
		   
	}
	
}
class WorkTime{
	private static int worktime;
	public void settime(int worktime){
		this.worktime = worktime;
	}
	
	public int gettime(){
		return worktime;
	}
}
class WorkTimeCounter  extends TimerTask  {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			WorkTime wk = new WorkTime();
			int a = wk.gettime();
			a+=10;
			System.out.print(a);
			wk.settime(a);
				
		}

}
