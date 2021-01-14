package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CameraConnectionController {
	
	@FXML
	public void readInput() {
		
	}
	@FXML
	public void writeOutput() {
		
	}
	@FXML
	public void openSendData() {
		Stage stage=new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/Result/SendDataFrame.fxml"))));
			stage.setTitle("Send Data");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void socketListener() {
		
	}
}
