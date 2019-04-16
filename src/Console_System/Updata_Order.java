package Console_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;

public class Updata_Order {
	public static DefaultListModel<Cooker_order> get_order_list(String url,String user,String password,String name, int sh,int change) {
		
		
		
		DefaultListModel<Cooker_order> dim2  = new DefaultListModel<>();


		try{
			Class.forName("com.mysql.jdbc.Driver");
				 Connection conDB = DriverManager.getConnection(url, user, password);
				 
				 
				 
				 Statement stmt = conDB.createStatement();
		
		

		
				 String sql = "UPDATE order_record " +
		                   "SET record_schedule = "+change+" WHERE record_name like '" +name+"'";
		      stmt.executeUpdate(sql);
				 
				
				ResultSet rs = stmt.getResultSet();
				

				return dim2;
		}
		catch(Exception ex){
			System.out.println(ex);
			return dim2;
		}
				
	
	}
}
