package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.midi.VoiceStatus;

import arvasis.camera.ArvasisDirectCaptureCamera;
import arvasis.camera.ArvasisIDSCamera;
import arvasis.camera.ArvasisIDSSocketCamera;
import arvasis.camera.ArvasisInspectClient;
import arvasis.camera.Camera;
import arvasis.camera.FsWebCamera;
import arvasis.camera.PiCamera;
import arvasis.camera.VirtualCamera;
import arvasis.camera.VirtualStereoCamera;
import arvasis.camera.VlcCam;
import arvasis.drawing.GraphicsIO;
import arvasis.sensor.studio.tree.ArvasisTree;
import arvasis.sensor.studio.tree.TreeNode;
import arvasis.tool.visualization.DataVisualizer;
import globals.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdapterController implements Initializable {

	@FXML
	private Button btnSelectFile;
	@FXML
	private TextField txtVirtualCam;
	
	private File imageFile;
	private Object image;

	private MenuController menuController;
	
	public void setMenuController(MenuController menuController) {
		this.menuController=menuController;
	}

	@FXML
	private ComboBox<Object> cbAdapter;
	ObservableList<Object> options = FXCollections.observableArrayList(new VirtualCamera(), new ArvasisInspectClient(),
			new ArvasisDirectCaptureCamera(), new ArvasisIDSCamera(), new ArvasisIDSSocketCamera(), new FsWebCamera(),
			new PiCamera(), new VirtualStereoCamera(), new VlcCam());

	
	
	@FXML
	public void addAdapter() {
		int index = cbAdapter.getSelectionModel().getSelectedIndex();
		switch (index) {
		case 0: {
			Stage stage = new Stage();
			try {
				stage.setTitle("Virtual Camera Adapter");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Adapter/VirtualCameraAdapter.fxml"));
				loader.setController(this);
				stage.setScene(new Scene(loader.load()));
				stage.show();
				((Stage)cbAdapter.getScene().getWindow()).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case 1: {
			Stage stage = new Stage();
			try {
				stage.setTitle("Inspect Camera Adapter");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Adapter/InspectCameraAdapter.fxml"));
				stage.setScene(new Scene(loader.load()));
				stage.show();
				((Stage)cbAdapter.getScene().getWindow()).close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
	}

	@FXML
	public void selectFile() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.jpeg","*.png"));
		imageFile = chooser.showOpenDialog(btnSelectFile.getScene().getWindow());
		if (imageFile != null) {
			txtVirtualCam.setText(imageFile.getPath());
			BufferedImage buff;
			try {
				image = GraphicsIO.readBufferedImage(imageFile.getAbsolutePath());
				

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	@FXML
	public void addImage() {
		
		((Stage)txtVirtualCam.getScene().getWindow()).close();
			try {
		
			Camera camera = (Camera) cbAdapter.getSelectionModel().getSelectedItem();
			Globals.cam = camera;
			Globals.arrCam.add(camera);
			//Globals.refreshArrCam();
			if (camera instanceof VirtualCamera) {
				Globals.image=image;
			}else 
				Globals.image = Globals.cam.getImage();
			
			menuController.getCbCamera().getItems().add(camera);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Globals.mainController.setImage(Globals.image);

			TreeNode rootNode=new TreeNode(cbAdapter.getSelectionModel().getSelectedItem().toString(),Globals.image);
			Globals.tree=new ArvasisTree(rootNode);
			Globals.mainController.mainPane.setRight(Globals.tree);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbAdapter.setPromptText("Select Adapter");
		cbAdapter.setItems(options);
		
	}

}
