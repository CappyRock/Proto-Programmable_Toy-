package application;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController{

	@FXML
	private Label entrylabel;
	@FXML
	private TextField usernameent;
	@FXML
	private PasswordField passwordent;
	
	int a = 3;
	
	public void lbuttonAction(ActionEvent event) throws IOException {
		
		if(usernameent.getText().equals("b") && passwordent.getText().equals("b")) {
			Stage stage = (Stage) passwordent.getScene().getWindow();
			stage.close();
			Bars();
			
		}
		else {
			entrylabel.setText("Please TRY AGAIN!..");
		}
		
	}
	private double x = 0;
	private double y = 0;
	
	public void Bars() throws IOException{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("BarrelScreen.fxml"));
			Stage barrelstage = new Stage();
			barrelstage.initStyle(StageStyle.UNDECORATED);
			barrelstage.setScene(new Scene(root));
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					x = event.getSceneX();
					y = event.getSceneY();
				}
			});
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					barrelstage.setX(event.getScreenX() - x);
					barrelstage.setY(event.getScreenY() - y);
				}
			});
			barrelstage.setY(0);
			barrelstage.setX(a);
			barrelstage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		
		ServerSocket listener = new ServerSocket(9090);
       
        
	}


	
	
}
