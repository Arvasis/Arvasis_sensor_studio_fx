package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import arvasis.camera.Camera;
import globals.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	@FXML
	public Button btnTakeButton;
	@FXML
	public ComboBox<Object> cbCamera;

	private String lang;
	@FXML
	private ComboBox<String> cbLang;
	@FXML
	public Button btnClose;

	@FXML
	public void newJob() {

		Main main=new Main();
		main.start(new Stage());
	}

	@FXML
	public void takePhoto() {
		
		Camera camera = (Camera) cbCamera.getSelectionModel().getSelectedItem();
		try {
			
			BufferedImage image = camera.getImage();
			Globals.mainController.setImage(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * BufferedImage image=Globals.applyAllFilters(cameraPreview.getImage(),
		 * getProcessStringFromTree());
		 * 
		 * //ArvasisSensorStudio.ip.setImageCenterPanel(image);
		 * //ArvasisSensorStudio.ip.setOriginalImage(image);
		 * 
		 * Globals.tree.addChild(new TreeNode(btnTakePhoto.getText(),image));
		 * Globals.image = image; Globals.llImage.add(Globals.image);
		 */
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
			VBox convertImageFr = FXMLLoader.load(getClass().getResource("/Menu/ConvertImageFrame.fxml"));
			Stage stage = new Stage();
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
			Pane cameraPrevFr = FXMLLoader.load(getClass().getResource("/Menu/CameraPreviewFrame.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Camera Preview");
			stage.setScene(new Scene(cameraPrevFr));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void exit() {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbLang.setItems(FXCollections.observableArrayList("EN", "TR"));
		lang = cbLang.getItems().get(0);
		// lang = table.getValueAt(table.getSelectedRow(), 0).toString();
		Globals.setLanguage(lang);
		/*
		 * mp.initText(); ap.initText(); rp.init(); bp.init();
		 */
		// tabbedPane.setTitleAt(0, Globals.getLanguage().getString("ImagePanel"));
	}

	public ComboBox<Object> getCbCamera() {
		return cbCamera;
	}

	public void setCbCamera(ComboBox<Object> cbCamera) {
		this.cbCamera = cbCamera;
		cbLang.setItems(FXCollections.observableArrayList("EN","TR"));
		lang=cbLang.getItems().get(0);
		Globals.setLanguage(lang);
		
	
	}
	
}
