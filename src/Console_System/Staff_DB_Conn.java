package Console_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Staff_DB_Conn {
	public static String Login(String act,String psw,String url){
		String code = "0";
		String db_account,db_password = null;
		int db_staff_id=0;
		/*
		 * code 5 cant find account
		 * code 6 password not right
		 * code 7 act or psw have Special characters
		 */
		
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~¡I@#¢D%¡K¡K&*¡]¡^¡X¡X+|{}¡i¡j¡¥¡F¡G¡¨¡§¡¦¡C¡A¡B¡H]";
		Pattern p = Pattern.compile(regEx);
		Matcher m;
		m = p.matcher(act);
		if(m.find()){
			return code= "3";
		}
		 m = p.matcher(psw);
		if(m.find()){
			return code= "3";
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
				 Connection conDB = DriverManager.getConnection(url, "System_use", "system123");
				 
				 
				 
				 Statement stmt = conDB.createStatement();
		
				 System.out.println("Select staff_id,staff_password,staff_dep from staff_info where staff_account like \""+act+"\";");
				 stmt.executeQuery("Select staff_id,staff_password,staff_dep from controll_system.staff_info where staff_account like \""+act+"\";");
		
			
				 
				
				ResultSet rs = stmt.getResultSet();
				int count =0;
				int db_staff_dep=0;
				while( rs.next() ){
					count++;
					db_staff_id=rs.getInt("staff_id");
					db_password=rs.getString("staff_password");
					db_staff_dep=rs.getInt("staff_dep");
				}
				if(count==0){
					return "5";
				}else if(db_password.equals(psw)){
					return db_staff_dep+":"+db_staff_id;
				}else{
					return "6";
				}
				

		}
		catch(Exception ex){
			System.out.println(ex);
			return code="1";
		}
	}
}
