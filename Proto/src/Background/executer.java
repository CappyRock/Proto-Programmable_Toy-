package Background;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



import Background.String_reader;
import Background.executer;
import Background.seperator;
import Background.calculator;
import application.Key;

public class executer {
	public executer(ArrayList<Key> keys){
		keyz = keys;
	}
	
	static ArrayList<Key> keyz = new ArrayList<Key>();
	static String_reader str = new String_reader();
	static seperator spr = new seperator();
	static calculator cal = new calculator();
	public static ArrayList<Integer> sat;
	 
	
	public static void execute(String input) {
		
		for(String s : spr.Corper(input, keyz)) {	
			
			
		
			s = s.trim();
			//+System.out.println(s);
			String codeBlock = str.returner(s, keyz).get(0);
			char functionType = str.returner(s, keyz).get(1).charAt(0);
			int parameterNo = Integer.parseInt(str.returner(s, keyz).get(2));
			
			if (functionType == 'f' ) {
				codeBlock = codeBlock.replaceAll("\\s+","");
			}
			String[] dicedCode = spr.seperator(codeBlock, functionType, parameterNo);
		/*
		for(String a : dicedCode) {
	        System.out.println(a);
	    }
		*/
			sat = cal.calculate(dicedCode, keyz);
			
			
		
	}
	
	}
}



