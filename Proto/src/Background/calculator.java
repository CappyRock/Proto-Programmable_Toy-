package Background;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.lang.String;


import javax.swing.GroupLayout.Alignment;

import Background.executer;
import Communication.Sender;
import application.Key;
import memory.Keys;

public class calculator {
	
	static int[] currentBarrels = {0,0,0,0,0,0};
	
	public static int Wrapperazed(String condition) {
		int type = -1;
		
		if(condition.equals("=")) type = 1;
		if(condition.equals(">")) type = 2;
		if(condition.equals("<")) type = 3;
		
		return type;
	}
	
	public static boolean conditionHandler(String conditions) {
		boolean result = false;
		System.out.println("Condition handler working");
		String conditionSign = ".";
		
		if(conditions.contains("=")) {
			conditionSign = "=";
		}
		else if (conditions.contains("<")) {
			conditionSign = "<";
		}
		else if (conditions.contains(">")) {
			conditionSign = ">";
		}
			
		String[] parts = conditions.split("[=\\<\\>]");
		String firstPart = parts[0];
		String secondPart = parts[1];
		
		if(firstPart.equals(secondPart)){
			result = true;
			return result;
		}
		
		int LeftSide = 0;
		//Left Side
		if(firstPart.contains("[+\\*\\/\\-]") == false) {
			try {
			/*LEFT SIDE */ 
			LeftSide = Integer.parseInt(firstPart);
			}
			catch (Exception e) {
				System.out.println(variables.get(0).name + " : " +variables.get(0).t);
				for(Box b : variables) {
					if(b.name.equals(firstPart)) {
						System.out.println("2222222222222" + b.t);
						LeftSide =  (int) b.t;
					}
				}
				
				
				}
			}
		
		int RightSide = 0;
		//Right Side
		if(secondPart.contains("[+\\*\\/\\-]") == false) {
			try {
			/*RIGHT SIDE */ 
				RightSide = Integer.parseInt(secondPart);
				System.out.println(RightSide);
			}
			catch (Exception e) {
				System.out.println(variables.get(0).name + " : " +variables.get(0).t);
				for(Box b : variables) {
					if(b.name.equals(secondPart)) {
						System.out.println(b.t);
						RightSide = (int) b.t;
					}
				}
				
				
				}
			}
			
		if(Wrapperazed(conditionSign) == 1) {result = (LeftSide == RightSide);}
		else if(Wrapperazed(conditionSign) == 2) {result = (LeftSide > RightSide);}
		else if(Wrapperazed(conditionSign) == 3) {result = (LeftSide < RightSide);}
		
		
			
		return result;
	}

	
	//ALL VARIABLES
	/*public class Box<T> {
		
		public T t;
		public String name;

		public Box(String blame, T t) {
			this.name = blame;
			this.t = t;
			
		}
		public Box(String blame) {
			this.name = blame;
			this.t = null;
		}
		
	}*/
	static ArrayList<Box> variables = new ArrayList<Box>();
	
	public static ArrayList<Integer> calculate(String[] input, ArrayList<Key> keyz){
		executer ex = new executer(keyz);
		
		
		ArrayList<Integer> calculation = new ArrayList<Integer>();
		
		// --------------- COMMUNUCATION VALUES (increase) ----------------------
		ArrayList<Integer> sentTask = new ArrayList<Integer>();
		// ----------------------------------------------------------------------
		
		int positiveRotation = 1;
		int negativeRotation = -1;
		
		/*
		System.out.println("ÝLK ÝNDEX --------- "+input[0]);
		*/
		
		if(input[0].equals("increase")) {
			System.out.println("Increase Worked!");
			
			// For Barrel Simulation
			int incIndex = Integer.parseInt(input[1]) - 1 ;
			
			currentBarrels[incIndex] = (currentBarrels[incIndex] + 1) % 10;
			
			for (int i = 0; i < currentBarrels.length; i++) {
				System.out.print(currentBarrels[i]);
				
			}
			System.out.println();
			
			
			// --------------- COMMUNUCATION VALUES ----------------------
			sentTask.add(Integer.parseInt(input[1]));
			sentTask.add(positiveRotation);
			int[] a = Sender.converter((Sender.vals(sentTask.get(1), sentTask.get(0))));
			for(int s : a) System.out.print(s);
			System.out.println();
			System.out.println(sentTask);
			// ----------------------------------------------------------------------
			 
		}
		else if (input[0].equals("decrease")) {
			System.out.println("Decrease Worked!");
			
			// For Barrel Simulation
			int incIndex = Integer.parseInt(input[1]) - 1 ;
			currentBarrels[incIndex] = (currentBarrels[incIndex] - 1) % 9;
			if(currentBarrels[incIndex] == -1) {
				currentBarrels[incIndex] = 9;
				
			}

			for (int i = 0; i < currentBarrels.length; i++) {
				System.out.print(currentBarrels[i]);
			}
			System.out.println();
			
			// --------------- COMMUNUCATION VALUES  ----------------------
			sentTask.add(Integer.parseInt(input[1]));
			sentTask.add(negativeRotation);
			int[] a = Sender.converter((Sender.vals(sentTask.get(1), sentTask.get(0))));
			for(int s : a) System.out.print(s);
			System.out.println();
			System.out.println(sentTask);
			// ----------------------------------------------------------------------
			
		}
		else if (input[0].equals("swap")) {
			System.out.println("Swap Worked!");
			int currentIndex1 = Integer.parseInt(input[1]) -1;
			int currentIndex2 = Integer.parseInt(input[2]) -1;
			
			int currentNumber1 = currentBarrels[currentIndex1];
			int currentNumber2 = currentBarrels[currentIndex2];
			
			// For Barrel Simulation
						
			currentBarrels[currentIndex1] = currentNumber2;
			currentBarrels[currentIndex2] = currentNumber1;
			
			

			for (int i = 0; i < currentBarrels.length; i++) {
					System.out.print(currentBarrels[i]);
			}
			System.out.println();
			
			// --------------- COMMUNUCATION VALUES  ----------------------	
			int tempNumber = currentBarrels[currentIndex1];
			int numberDiff = currentNumber2 - currentNumber1;
			
			sentTask.add(currentIndex1 + 1);
			sentTask.add(positiveRotation * numberDiff);
			
			sentTask.add(currentIndex2 + 1);
			sentTask.add(positiveRotation * -numberDiff);
			
			System.out.println(sentTask);
			// ----------------------------------------------------------------------
			
		}
		else if (input[0].equals("randomize")) {
			System.out.println("Randomize Worked!");
			
			Random random = new Random();
			int randomInt = random.nextInt(9);
					
			
			// For Barrel Simulation
			int incIndex = Integer.parseInt(input[1]) - 1 ;
			currentBarrels[incIndex] = randomInt;
						
			for (int i = 0; i < currentBarrels.length; i++) {
					System.out.print(currentBarrels[i]);
							
			}
			System.out.println();
			
			// --------------- COMMUNUCATION VALUES ----------------------	
			sentTask.add(Integer.parseInt(input[1]));
			sentTask.add(positiveRotation * randomInt);
			
			System.out.println(sentTask);
			// ----------------------------------------------------------------------
		}
		else if (input[0].equals("reset")) {
			System.out.println("Reset Worked!");
			
			// For Barrel Simulation
				int incIndex = Integer.parseInt(input[1]) - 1 ;
					currentBarrels[incIndex] = 0;
									
				for (int i = 0; i < currentBarrels.length; i++) {
						System.out.print(currentBarrels[i]);
										
				}
				System.out.println();
				
			// --------------- COMMUNUCATION VALUES ----------------------	
			int currentNumber = currentBarrels[Integer.parseInt(input[1])];
			int requiredMoveCount = ( 10 - currentNumber);
			
			calculation.add(Integer.parseInt(input[1]));
			calculation.add(requiredMoveCount * positiveRotation);
			
			System.out.println(sentTask);
			// ----------------------------------------------------------------------
			
		}
		else if(input[0].equals("name")){
		System.out.println("Name Worked!");
		
		String varName  = input[1];
		String varValue = input[2];
		
		variables.add(new Box(varName, varValue));
		
		/*
		String varName  = input[1];
		String varValue = input[2];
		ArrayList<String>  tempArray = new ArrayList<String>();
		tempArray.add(varName);
		tempArray.add(varValue);
		
		System.out.println("Local variable " + varName + " added with value " + varValue);	
		
		variables.add(tempArray);
		*/
	
		
		}
		else if(input[0].equals("number")){
		System.out.println("Number Worked!");
		
		String varName  = input[1];
		int varValue = Integer.parseInt(input[2]);
		
		variables.add(new Box(varName, varValue));
		/*
		String varName  = input[1];
		String varValue = input[2];		
		ArrayList<String>  temp = new ArrayList<String>();
		temp.add(varName);
		temp.add(varValue);
		System.out.println("Local variable " + varName + " added with value " + varValue);		
		variables.add(temp);	
		*/
		
		}
		else if (input[1] == "=") {
			if(input[3] == "+") {

				int number1 = 0;
				int number2 = 0;
					
			
				try {
					/* Ýlk Sayý */ 
					number1 = Integer.parseInt(input[2]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[2])) {
								number1 = (int) b.t;
							}
						}
					}
					System.out.println("ilk sayý -- " + number1);
				
					
				try {
					/* Ýkinci Sayý */ 
					number2 = Integer.parseInt(input[4]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[4])) {
								number2 = (int) b.t;
							}
						}
					}
					System.out.println("Ýkinci sayý -- " + number2);
				
					
					// Assign result
					for(Box b : variables) {
						if(b.name.equals(input[0])) {
							b.t = number1 + number2;
							System.out.println("New value of (" +b.name+ ") is " + b.t);
						}
					}
					
					
				
				
			}
			else if(input[3] == "-") {
				
				int number1 = 0;
				int number2 = 0;
					
			
				try {
					/* Ýlk Sayý */ 
					number1 = Integer.parseInt(input[2]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[2])) {
								number1 = (int) b.t;
							}
						}
					}
					System.out.println("ilk sayý -- " + number1);
				
					
				try {
					/* Ýkinci Sayý */ 
					number2 = Integer.parseInt(input[4]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[4])) {
								number2 = (int) b.t;
							}
						}
					}
					System.out.println("Ýkinci sayý -- " + number2);
				
					
					// Assign result
					for(Box b : variables) {
						if(b.name.equals(input[0])) {
							b.t = number1 - number2;
							System.out.println("New value of (" +b.name+ ") is " + b.t);
						}
					}
				
			}
			else if(input[3] == "*") {
				int number1 = 0;
				int number2 = 0;
					
			
				try {
					/* Ýlk Sayý */ 
					number1 = Integer.parseInt(input[2]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[2])) {
								number1 = (int) b.t;
							}
						}
					}
					System.out.println("ilk sayý -- " + number1);
				
					
				try {
					/* Ýkinci Sayý */ 
					number2 = Integer.parseInt(input[4]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[4])) {
								number2 = (int) b.t;
							}
						}
					}
					System.out.println("Ýkinci sayý -- " + number2);
				
					
					// Assign result
					for(Box b : variables) {
						if(b.name.equals(input[0])) {
							b.t = number1 * number2;
							System.out.println("New value of (" +b.name+ ") is " + b.t);
						}
					}
			}
			else if(input[3] == "/") {
				int number1 = 0;
				int number2 = 0;
					
			
				try {
					/* Ýlk Sayý */ 
					number1 = Integer.parseInt(input[2]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[2])) {
								number1 = (int) b.t;
							}
						}
					}
					System.out.println("ilk sayý -- " + number1);
				
					
				try {
					/* Ýkinci Sayý */ 
					number2 = Integer.parseInt(input[4]);
					}
					catch (Exception e) {
						for(Box b : variables) {
							if(b.name.equals(input[4])) {
								number2 = (int) b.t;
							}
						}
					}
					System.out.println("Ýkinci sayý -- " + number2);
				
					
					// Assign result
					for(Box b : variables) {
						if(b.name.equals(input[0])) {
							b.t = number1 / number2;
							System.out.println("New value of (" +b.name+ ") is " + b.t);
						}
					}
			}
		}
		else if(input[0].equals("if")){
			System.out.println("if Worked!");
			
			String condis = input[1];
			Boolean resultt = conditionHandler(condis);
			
			if(resultt == true) {
				ex.execute(input[2]);
			}
				
			}
		else if(input[0].equals("set")){
			System.out.println("Set Worked!");
			
			// For Barrel Simulation
				int incIndex = Integer.parseInt(input[1]) - 1 ;
				int setNum = Integer.parseInt(input[2]);
				
				// --------------- COMMUNUCATION VALUES -------------------------------	
				int currentValue = currentBarrels[incIndex];
				int requiredSpin = setNum - currentValue;
				
				
				sentTask.add(incIndex + 1);
				sentTask.add(positiveRotation * requiredSpin);

				// ----------------------------------------------------------------------
						
				currentBarrels[incIndex] = setNum;
						
				for (int i = 0; i < currentBarrels.length; i++) {
							System.out.print(currentBarrels[i]);
							
						}
				System.out.println();
				System.out.println(sentTask);
				
				
			}
		else if(input[0].equals("repeat")){
			System.out.println("Repeat Worked!");
			
			int repeatCount = Integer.parseInt(input[1]);
			 System.out.println(input[0]);
			 System.out.println(input[1]);
			 System.out.println(input[2]);
			for(int i = 0; i< repeatCount; i++) {
				ex.execute(input[2]);
			}
			
			
				
		}
		else if(input[0].equals("while")){
			System.out.println("While Worked!");
			
			while(conditionHandler(input[1])) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				ex.execute(input[2]);
			}
			
				
		}
		else if(input[0].equals("superset")){
			System.out.println("superset Worked!");
			
			// For Barrel Simulation
			
			int incIndex = Integer.parseInt(input[1]);
			int[] senderValsforSSet;
			// --------------- COMMUNUCATION VALUES  ---------------------------	
			for(int a = 1; a <= 6; a++) {
				sentTask.add(a);
				int currentValue = currentBarrels[a-1];
				int requiredSpin = incIndex - currentValue;
				
				sentTask.add(requiredSpin);
				senderValsforSSet = Sender.converter((Sender.vals(sentTask.get(2*a - 1), sentTask.get(2*a - 2))));
				for(int s : senderValsforSSet) System.out.print(s);
				System.out.println();
			}		
			// ----------------------------------------------------------------------
			
			if(incIndex < 10) {			
				for (int i = 0; i < currentBarrels.length; i++) {
					currentBarrels[i] = incIndex;
					System.out.print(currentBarrels[i]);
							
				}
			}
			else {System.out.println("Invalid Input..");}
			System.out.println();
			System.out.println(sentTask);
				
		}
		else if(input[0].equals("superreset")){
			System.out.println("superreset Worked!");
			
			// For Barrel Simulation
			int s1 = Integer.parseInt(input[1]) - 1;
			int e1 = Integer.parseInt(input[2]) - 1;
			
			int y = 1;
			int[] senderValsforSReset;
			// --------------- COMMUNUCATION VALUES  ---------------------------	
			int firstNum = Integer.parseInt(input[1]);
			int secNum = Integer.parseInt(input[2]);
			for(int a = firstNum; a <= secNum; a++) {
				sentTask.add(a);

				int currentNumber = currentBarrels[a-1];
				int requiredMoveCount = ( 10 - currentNumber);
				
				sentTask.add(requiredMoveCount);
				senderValsforSReset = Sender.converter((Sender.vals(sentTask.get(2*y - 1), sentTask.get(2*y - 2))));
				for(int h : senderValsforSReset) System.out.print(h);
				System.out.println();
				y++;

			}	
					
			for (int i = 0; i < currentBarrels.length; i++) {
				if(i >= s1 && i <= e1) currentBarrels[i] = 0;
				System.out.print(currentBarrels[i]);
							
			}
		
			
			System.out.println();
			System.out.println(sentTask);
				
		}
		else if(input[0].equals("superrandom")){
			System.out.println("superrandom Worked!");
			
			// For Barrel Simulation
			int s2 = Integer.parseInt(input[1]) - 1;
			int e2 = Integer.parseInt(input[2]) - 1;
			
			int[] senderValsforSRand;
			// --------------- COMMUNUCATION VALUES  ---------------------------	
			int firstNum = Integer.parseInt(input[1]);
			int secNum = Integer.parseInt(input[2]);
			
			Random random = new Random();
			
			int m = 1;
			for(int a = firstNum; a <= secNum; a++) {
				sentTask.add(a);
				
				int randomInt = random.nextInt(9);
				
				int currentBarrelVal = currentBarrels[a-1];
				int requiredMoveCount =  randomInt - currentBarrelVal ;
				
				sentTask.add(requiredMoveCount);
				
				currentBarrels[a-1] = randomInt;
				
				senderValsforSRand = Sender.converter((Sender.vals(sentTask.get(2*m - 2), sentTask.get(2*m - 1))));
				for(int u : senderValsforSRand) System.out.print(u);
				System.out.println();
				m++;
				
				
			}	
			
			
			
			for (int i = 0; i < currentBarrels.length; i++) {
				//if(i >= s && i <= e) currentBarrels[i] = 0;
				System.out.print(currentBarrels[i]);
							
			}
		
			
			System.out.println();
			System.out.println(sentTask);
				
		}
		
		
		for(int s: currentBarrels) {
			calculation.add(s);
		}
		
		
		
		return calculation;
	}


}
