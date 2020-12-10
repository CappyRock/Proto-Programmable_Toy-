package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.AnnotatedArrayType;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.xml.ws.AsyncHandler;

import Background.String_reader;
import Background.executer;
import Background.seperator;


public class Main extends Application {
	
	
	public static void main(String[] args) throws IOException{
		
		
		launch(args);	
			
	}

	@Override
	public void start(Stage ent) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		ent.setTitle("PLR");
		ent.initStyle(StageStyle.UNDECORATED);
		ent.setScene(new Scene(root));
		ent.show();
		 
		
	}
	
	
}
