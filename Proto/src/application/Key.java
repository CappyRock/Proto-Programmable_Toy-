package application;

public class Key {
	
	int key_id;
	public String name;
	
	public int parameter_no;
	public char type; // f == 1, v == 2, o == 3;
	
	public Key(String s, int pn, char t){
		
		name = s;
		parameter_no = pn;
		type = t;
		key_id = key_generator(s, t);
		
	}
	
	int key_generator(String name, char type) {
		
		int res = 0;
		int sum = 0;
		
		for(int i = 0; i<name.length(); i++) {
			sum += (int) name.charAt(i);
		} 
		
		sum = sum*100;
		sum = sum/37;
		
		int digitno = Integer.toString(sum).length();
		
		switch(type)
		{
		case 'f': 
			res = (int)(Math.pow(10, digitno)) + sum;
			break;
		case 's': 
			res = (int)(2*Math.pow(10, digitno)) + sum;
			break;
		case 'v':
			res = (int)(3*Math.pow(10, digitno)) + sum;
			break;
		case 'o':
			res = (int)(4*Math.pow(10, digitno)) + sum;
			break;
		}
		
		return res;
	}
	
	
	
}
