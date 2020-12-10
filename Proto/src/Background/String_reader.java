package Background;
import java.util.ArrayList;
import java.util.Stack;

import application.*;

public class String_reader {

	
	public ArrayList<String> returner(String half, ArrayList<Key> a) {
		String exact = "";
		String num = "";
		
		ArrayList<String> Token = new ArrayList<String>();
		// ArrayList<String> DidYAMean = new ArrayList<String>();
		
		String DidYaMean = "";
		
		// Each typing triggers these method..
		
		Boolean CheckF = false;
		Boolean CheckO = false;
	
		
		/*if(half.contains("+") == true || half.contains("-") == true
			|| half.contains("*") == true || half.contains("/") == true) 
			{
			CheckO = true;
			if(CheckO) {
				Token.add(half);
				Token.add("o");
				Token.add("2");
			}
		}*/
		
		for(char each : half.toCharArray()) {
			
			if(Character.isLetter(each)) {
				exact += each;
				//System.out.println(exact);
			}
			else {
				
				num += each;
				num = num.replaceAll("\\s+","");
				//System.out.println(num);
				//target = target.replaceAll("\\s+",""); 
			}
			
		
			for(Key er : a) {
				
				if(er.name.equals(exact)) {
					CheckF = true;
					if(CheckF) {
						
						Token.add(half);
						Token.add(Character.toString(er.type));
						Token.add(Integer.toString(er.parameter_no));
						
					}
					break;
					
				}
				else if(er.name.equals(num)) {
					
					CheckO = true;
					if(CheckO) {
						Token.add(half);
						Token.add(Character.toString(er.type));
						Token.add(Integer.toString(er.parameter_no));
					}
					break;
				}
				
				
			}
			
		}
		
		
		

		return Token;
		
	}
	
	
}

