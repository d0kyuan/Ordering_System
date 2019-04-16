package Console_System;

import javax.swing.DefaultListModel;

import java.sql.*;

public class Food_DB_Connn 	{
	
	public static DefaultListModel<Menu> get_food_list(String url,String user,String password,String sql) {
		DefaultListModel<Menu> dim2  = new DefaultListModel<>();


		try{
			Class.forName("com.mysql.jdbc.Driver");
				 Connection conDB = DriverManager.getConnection(url, user, password);
				 
				 
				 
				 Statement stmt = conDB.createStatement();
		
		
				 stmt.executeQuery(sql);
		
			
				 
				
				ResultSet rs = stmt.getResultSet();
				
				while( rs.next() ){
			
					dim2.addElement(new Menu(rs.getString("food_name"),rs.getInt("food_price"),rs.getInt("food_id")));
		
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
