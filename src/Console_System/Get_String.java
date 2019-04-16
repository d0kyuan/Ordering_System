package Console_System;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Get_String {
	private static String where_str_form;
	public Get_String(String st){
		this.where_str_form = st;
	}

	public static String get_name(String st){
		try{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/Controll_System/Chinese_String.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		
		return (String) jsonObject.get(where_str_form+"_"+st);
		}catch(Exception ex){
			return "null"+ex;
		}
	}

	
}
