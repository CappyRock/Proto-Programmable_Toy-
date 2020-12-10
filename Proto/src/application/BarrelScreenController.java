package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;

import Background.String_reader;
import Background.executer;
import Background.seperator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BarrelScreenController{
	
	@FXML
	private Label L1;
	@FXML
	private Label L2;
	@FXML
	private Label L3;
	@FXML
	private Label L4;
	@FXML
	private Label L5;
	@FXML
	private Label L6;
	
	@FXML
	private TextArea WritingArea;
	
	public static String WholeText;
	
	private boolean open = false;
	
	public void texter(ActionEvent event) {
		ArrayList<Key> keyz = new ArrayList<Key>();
		
		String_reader str = new String_reader();
		
		seperator spr = new seperator();
		
	
		try{
		    FileInputStream fstream = new FileInputStream("tab.txt");
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		          
		   while ((strLine = br.readLine()) != null)   {
			   String[] tokens = strLine.split(" ");
			   keyz.add(new Key(tokens[0], Integer.parseInt(tokens[1]), tokens[2].charAt(0)));

		   }
		   
		   in.close();
		   }catch (Exception e){
		     System.err.println("Error: " + e.getMessage());
		   }
		WholeText = WritingArea.getText();
		executer ex = new executer(keyz);
		System.out.println(WholeText);
		BarrelScreenController.WholeText = BarrelScreenController.WholeText.trim();
		//System.out.println(ex.execute(BarrelScreenController.WholeText));
		ex.execute(BarrelScreenController.WholeText);
		updateBarrels(ex.sat);
	}
	
	public void updateBarrels(ArrayList<Integer> d) {
		//System.out.println("settext : : : " + d);
		L1.setText(Integer.toString(d.get(0)));
		L2.setText(Integer.toString(d.get(1)));
		L3.setText(Integer.toString(d.get(2)));
		L4.setText(Integer.toString(d.get(3)));
		L5.setText(Integer.toString(d.get(4)));
		L6.setText(Integer.toString(d.get(5)));
		
	}
	
	Stage barrelstage = new Stage();

	public void StartServer() throws IOException{
		
		ServerSocket ss = new ServerSocket(9090);
		
		try {
			while(true) {
				Socket socket = ss.accept();
				socket.setKeepAlive(true);
				System.out.println("Client Connected..");
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println("Client Responds : " + in.readLine());
					
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    System.out.println("Sending Message...");
                    out.write("Hello\n from Java!\n");
                    out.flush();
				} finally {
					socket.close();
					// TODO: handle finally clause
				}
			}
		} finally {
			ss.close();
			// TODO: handle finally clause
		}
	}
	
	public void TextOpen() {
		
		open = !open;
		
		if(open) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("TextScreen.fxml"));
				
				barrelstage.initStyle(StageStyle.DECORATED.UNDECORATED);
				
				
				
				barrelstage.setScene(new Scene(root));
				barrelstage.setY(0);
				barrelstage.setX(1000);
				barrelstage.show();
			}
			catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
		else {
			try {
				barrelstage.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
			
		}
		
	}



}
