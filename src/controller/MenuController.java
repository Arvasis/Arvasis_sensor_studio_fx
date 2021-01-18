package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import globals.Globals;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController implements Initializable{
	private String lang;
	@FXML private ComboBox<String> cbLang;
	@FXML
	public Button btnClose;
	@FXML
	public void newJob() {
		Main main=new Main();
		main.start(new Stage());
	}

	@FXML
	public void takePhoto() {
		
	}
	@FXML
	public void clearHistory() {
		//TODO Tree Clear fonksiyonu eklenecek
		
		Globals.jsCode="";
		Globals.mainController.setImage(Globals.image);
	}
	@FXML
	public void convertImage() {
		try {
			VBox convertImageFr=FXMLLoader.load(getClass().getResource("/Menu/ConvertImageFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Convert Image");
			stage.setScene(new Scene(convertImageFr));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	public void openEditor() {

	}
	@FXML
	public void cameraPreview() {
		try {
			Pane cameraPrevFr=FXMLLoader.load(getClass().getResource("/Menu/CameraPreviewFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Camera Preview");
			stage.setScene(new Scene(cameraPrevFr));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void exit() {
		Stage stage=(Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbLang.setItems(FXCollections.observableArrayList("EN","TR"));
		lang=cbLang.getItems().get(0);
		Globals.setLanguage(lang);
		
	
	}
}
