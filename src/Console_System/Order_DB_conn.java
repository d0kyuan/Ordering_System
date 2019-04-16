package Console_System;


import javax.swing.DefaultListModel;

import java.sql.*;
import java.util.Timer;

public class Order_DB_conn 	{


	public static DefaultListModel<Cooker_order> get_order_list(String url,String user,String password,String sql) {
		
		
	
		DefaultListModel<Cooker_order> dim2  = new DefaultListModel<>();


		try{
			Class.forName("com.mysql.jdbc.Driver");
				 Connection conDB = DriverManager.getConnection(url, user, password);
				 
				 
				 
				 Statement stmt = conDB.createStatement();
		
		
				 stmt.executeQuery(sql);
		
			
				 
				
				ResultSet rs = stmt.getResultSet();
				
				while( rs.next() ){
					//System.out.println(rs.getString("record_name")+rs.getString("record_food")+rs.getInt("record_schedule"));
					dim2.addElement(new Cooker_order(rs.getString("record_name"),rs.getString("record_food"),rs.getInt("record_schedule")));
		
					//System.out.printf(" %s  %s  %s  %n",rs.getString("food_name"),rs.getInt("food_price"),rs.getInt("food_id"));
				}
				return dim2;
		}
		catch(Exception ex){
			System.out.println(ex);
			return dim2;
		}
				
	
	}
	
}


