package Background;
import Background.String_reader;

import java.util.ArrayList;
import java.util.Stack;

import application.*;

public class seperator {

private static final ArrayList<String> NULL = null;
private static final String Null = null;
public String[] seperator( String target, char type, int ParameterNo) {
	
	target = target.substring(0, target.length()-1);
	String[] result = null;
	//Chose seperation method
	switch (type) {
	
	//Function
	case 'f':
		if (ParameterNo == 1) {
			target = target.replaceAll("\\)","");
			String[] partsF = target.split("\\(");
			/*
			for(String s : partsF) {
				System.out.println(s);
			}*/
			
			//Example Output: ["decrease","3"]
			
			result = partsF;
			
			
		}
		else if (ParameterNo == 2) {
			target = target.replaceAll("\\)","");

			String[] prepart = target.split("\\(");
			String[] parameterPart = prepart[1].split(",");
			String[] partsF = {prepart[0], parameterPart[0],parameterPart[1]};
			/*
			for(String s : partsF) {
				System.out.println(s);
			}*/
			
			//Example Output: ["swap","3","6"]
			
			result = partsF;
			
		}
	break;
	
	//Statement
	case 's':
		target = target.replaceAll("\\s+",""); 
		target = target.replace("\\}","");   
		String[] preparts1 = target.split("\\{",2);
		String[] preparts2 = preparts1[0].split("\\(");
		preparts2[1] = preparts2[1].replace(")", "");
		String[] partsF = {preparts2[0],preparts2[1],preparts1[1]};
		result = partsF;
		/*
		for(String s : partsF) {
			System.out.println(s);
		}*/
		
		break;
	
		
		//Variable
	case 'v':
		String[] preparts = target.split(" ",2);
		String valuePart = preparts[1];
		valuePart = valuePart.replaceAll("\\s+","");
		String[] values = valuePart.split("\\=");
		String[] partsV = {preparts[0],values[0],values[1]};
		
		for(String s : partsV) {
		System.out.println(s);
		}
		result = partsV;
		
		
	break;
			
	//Operation
	case 'o':
		target = target.replaceAll("\\s+","");
		String[] firstDivision = target.split("=");
		String leftPart = firstDivision[0];
		String[] secondDivision = firstDivision[1].split("[+\\-\\*\\/]");
		
		String conditionSign = ".";
		
		if(firstDivision[1].contains("+")) {
			conditionSign = "+";
		}
		else if (firstDivision[1].contains("-")) {
			conditionSign = "-";
		}
		else if (firstDivision[1].contains("*")) {
			conditionSign = "*";
		}
		else if (firstDivision[1].contains("/")) {
			conditionSign = "/";
		}
		
		String[] partsO = {leftPart,"=",secondDivision[0],conditionSign,secondDivision[1]};
		/*
		for(String s : partsO) {
			System.out.println(s);
		}*/
		
		//Example Output: ["a","=","b","+","c"]	
		result = partsO;
	break;
			
	default:
		break;
	}
	return result;
	
	
	
	}
	///////////////////////Second Function//////////////////////////////
	public ArrayList<String> Corper(String a, ArrayList<Key> b){
			
			String RemainingString = a;
			
			ArrayList<String> lasttable = new ArrayList<String>();
			
			String_reader str = new String_reader();
			
			
			while(!RemainingString.isEmpty()) {
				int temp = 0;
				
				// DEAL WITH FUNCTIONS
				
				if(str.returner(RemainingString, b).get(1).equals("f") ||
				   str.returner(RemainingString, b).get(1).equals("o") ||
				   str.returner(RemainingString, b).get(1).equals("v")){
					System.out.println(str.returner(RemainingString, b).get(1));
					for(int i = 0; i<RemainingString.length(); i++) {
						if(RemainingString.charAt(i) == ';') {
							
							temp = i;
							break;
						}
					}
					
					lasttable.add(RemainingString.substring(0, temp+1));
					//System.out.println(RemainingString.substring(0, temp+1));
					RemainingString = RemainingString.substring(temp+1);
					//System.out.println(RemainingString);
				}
				else if (str.returner(RemainingString, b).get(1).equals("s")) {
					System.out.println(str.returner(RemainingString, b).get(1));
					Stack<Character> bracketContoller = new Stack<Character>() ;
					for(int i = 0; i<RemainingString.length(); i++) {

						if(RemainingString.charAt(i) == '{') {
							bracketContoller.push('{');
						
						}
						else if(RemainingString.charAt(i) == '}') {
							bracketContoller.pop();

							if (bracketContoller.isEmpty()) {
								temp = i;
								break;
							}
						}
						
						
					
					}
			
					lasttable.add(RemainingString.substring(0, temp+1));
					//System.out.println(RemainingString.substring(0, temp+1));
					RemainingString = RemainingString.substring(temp+1);
					//System.out.println(RemainingString);
			
				}
			
			}
		//System.out.println("Last TANLE" + lasttable);	
		
		return lasttable;
	}

}


