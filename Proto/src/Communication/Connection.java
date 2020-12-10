package Communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Connection {

	
	public static void Chronos() throws IOException{
		
		 ServerSocket listener = new ServerSocket(9090);
	        try{
	            while(true){
	                Socket socket = listener.accept();
	                socket.setKeepAlive(true);
	                System.out.println("Client Connected");
	                try{
	                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                    System.out.println("Client response: " + in.readLine());

	                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	                    System.out.println("Sending Message...");
	                    //out.write();
	                    out.flush();
	                } finally {
	                    socket.close();
	                }
	            }
	        } finally {
	            listener.close();
	        }
	} 
}	

