package Controll_System;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Console_System.Get_String;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Main_System extends JFrame implements  ActionListener{

	private static final long serialVersionUID = 1L;
		JLayeredPane layeredPane;
		JLayeredPane layeredPane1;
		JPanel contentPane = new JPanel();
		JPanel contentPane1;
		static int go = 0;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Main_System frame = new Main_System();
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
		 * @throws ParseException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 */
		public Main_System() throws Exception {
			Get_String gs = new Get_String("Main_System");

			
			setTitle(gs.get_name("Title"));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 487, 298);

			
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			layeredPane = new JLayeredPane();
			contentPane.add(layeredPane, BorderLayout.CENTER);
			
			//
			JButton btnNewButton = new JButton(gs.get_name("Order_System"));
			btnNewButton.addActionListener(this);
			btnNewButton.setActionCommand("Order_System");
			btnNewButton.setBounds(0, 0, 152, 245);
			layeredPane.add(btnNewButton);
			
			JButton button = new JButton(gs.get_name("Kitchenware_System"));
			button.addActionListener(this);
			button.setActionCommand("Kitchenware_System");
			button.setBounds(153, 0, 152, 245);
			layeredPane.add(button);
			
			JButton button_1 = new JButton(gs.get_name("accounting_System"));
			button_1.addActionListener(this);
			button_1.setActionCommand("accounting_System");
			button_1.setBounds(306, 0, 152, 245);
			layeredPane.add(button_1);
			
			
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print(e.getActionCommand().toString());
			// TODO Auto-generated method stub
			switch(e.getActionCommand().toString()){
				case "Order_System":
					go=1;

					break;
				case "Kitchenware_System":
					go=2;

					break;
				case "accounting_System":
					go=3;
					break;
		}		
			System.out.print(go);
			Login_UI frame = new Login_UI();
			frame.setVisible(true);
			this.setVisible(false);
	}
}
