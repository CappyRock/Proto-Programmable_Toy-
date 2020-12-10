package Communication;

public class Sender {

	public static int Capsulate(int turntimes, int index) {
		
		int[] btt = new int[8];
		
		
		
		
		
		
		return 10000000;
		
	}
	
	public static int vals(int turn, int index) {
		
		index = index*32;
		
		if(turn < 0) {
			turn = turn*-1;
			turn = turn + 16;
		}
		
		return turn + index;
	}
	
	public static int[] converter(int value) {
		
		int[] bytie = new int[8];
		int index = 7;
	
		do {
			bytie[index] = value % 2;
			value = value/2;
			index--;
		}
		while(value > 0);
	
	
		return bytie; 
	}
	
	public static void run(int turntimes, int index) {
		
		System.out.println(Capsulate(turntimes, index));	
		
	}
	
}
