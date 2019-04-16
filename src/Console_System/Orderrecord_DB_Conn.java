package Console_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class Orderrecord_DB_Conn {
	public static  String  send_order(String Staff_id, DefaultListModel<Menu> order,int total_price){
		 String md5_code ="",order_str="";
		java.util.Date now = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
		
		
		Menu me;
		for(int i = 0;i<order.size()-1;i++){
			me =order.getElementAt(i);
			order_str+=me.getName()+":";
		}
		me =order.getElementAt(order.size()-1);
		order_str+=me.getName();
		
		md5_code=md5.md5(sqlDate+""+Staff_id+total_price+order_str+Math.random());
		try{
		
		Connection conDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/controll_system?autoReconnect=true&useSSL=false","System_use", "system123");
		 
		 
		 
		 Statement stmt = conDB.createStatement();
		 
		 stmt.executeQuery("select staff_id from staff_info where login like 1");
			
			
		 ArrayList alive  = new ArrayList();
			
		ResultSet rs = stmt.getResultSet();
			
			while( rs.next() ){
				alive.add(rs.getInt("staff_id"));
			}
			
			double people_count = Math.random()*alive.size()-1;
			
			int temp = (int)people_count;
		 stmt.executeUpdate("INSERT INTO order_record (record_name,record_staff,record_food,record_price,record_time,record_cooker)"+
				 								"VALUES (\""+md5_code+"\","+Staff_id+",\""+order_str+"\","+total_price+",\""+sqlDate+"\",\""+alive.get(temp)+"\");");
		 
		 
		 
		 
		 
		 	return md5_code;
		}catch(Exception ex){
			System.out.println(sqlDate);
			return "errorcode:"+ex;
		}
		 
		 
	}
}
