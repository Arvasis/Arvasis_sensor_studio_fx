package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import application.Main;
import globals.Globals;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
		/*BorderPane mainPanel;
		try {
			mainPanel = FXMLLoader.load(getClass().getResource("/ArvasisSensorStudio.fxml"));
			Scene scene = new Scene(mainPanel, 1280, 720);
			Stage stage = new Stage();
			stage.setTitle("Arvasis Sensor Studio");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		Main main=new Main();
		main.start(new Stage());
	}

	@FXML
	public void takePhoto() {
		
	}
	@FXML
	public void clearHistory() {
		
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
	//	lang = table.getValueAt(table.getSelectedRow(), 0).toString();
		Globals.setLanguage(lang);
	/*	mp.initText();
		ap.initText();
		rp.init();
		bp.init();*/
		//tabbedPane.setTitleAt(0, Globals.getLanguage().getString("ImagePanel"));
	}
}
