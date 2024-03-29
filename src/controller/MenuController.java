package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ArvasisSensorStudioFx;
import arvasis.camera.ArvasisInspectClient;
import arvasis.camera.Camera;
import arvasis.camera.VirtualCamera;
import arvasis.drawing.GraphicsIO;
import arvasis.sensor.studio.tree.TreeNode;
import arvasis.tool.RadioButton;
import arvasis.tool.visualization.DataVisualizer;
import globals.Globals;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	@FXML
	public Button btnTakePhoto;
	@FXML
	public ComboBox<Object> cbCamera;

	private String lang;
	@FXML
	private ComboBox<String> cbLang;
	@FXML
	public Button btnClose;

	public CameraPreviewController cameraPreviewController;
	public CameraPreviewController getCameraPreviewController() {
		return cameraPreviewController;
	}

	public void setCameraPreviewController(CameraPreviewController cameraPreviewController) {
		this.cameraPreviewController = cameraPreviewController;
	}

	@FXML
	public void newJob() {

		ArvasisSensorStudioFx main = new ArvasisSensorStudioFx();
		main.start(new Stage());
	}

	@FXML
	public void takePhoto() {

		Camera camera = (Camera) cbCamera.getSelectionModel().getSelectedItem();

		if (camera instanceof VirtualCamera) {
			try {
				BufferedImage image = (BufferedImage) Globals.image;
				Globals.mainController.setImage(image);
				Globals.tree.addChild(new TreeNode(btnTakePhoto.getText(), image));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			BufferedImage image = (BufferedImage) Globals.applyAllFilters(cameraPreviewController.getImage(),
					Globals.tree.getProcessString(Globals.tree.getRootNode()));
			Globals.tree.addChild(new TreeNode(btnTakePhoto.getText(), image));

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

		// TODO Tree Clear fonksiyonu eklenecek

		Globals.jsCode = "";
		Globals.mainController.setImage(Globals.image);
		Globals.tree.getRoot().getChildren().removeAll(Globals.tree.getRoot().getChildren());
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
		System.out.println(Globals.tree.getProcessString(Globals.tree.getRoot()));
	}

	@FXML
	public void cameraPreview() {
		try {
			FXMLLoader cameraPreviewLoader = new FXMLLoader(
					getClass().getResource("/Menu/CameraPreviewFrame.fxml"));
			Pane cameraPrevFr = cameraPreviewLoader.load();
			cameraPreviewController = cameraPreviewLoader.getController();
			
			//Pane cameraPrevFr = FXMLLoader.load(getClass().getResource("/Menu/CameraPreviewFrame.fxml"));
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
		Globals.setLanguage(lang);
		cbLang.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				initText();
			}
		});
	}

	public ComboBox<Object> getCbCamera() {
		return cbCamera;
	}

	public void setCbCamera(ComboBox<Object> cbCamera) {
		this.cbCamera = cbCamera;
		cbLang.setItems(FXCollections.observableArrayList("EN", "TR"));
		lang = cbLang.getItems().get(0);
		Globals.setLanguage(lang);

	}
	private void initText() {
		btnClose.setText(Globals.getLanguage().getString("Menu.Exit"));
		btnTakePhoto.setText(Globals.getLanguage().getString("Menu.TakePhoto"));
	}
}
