package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class CameraPreviewController implements Initializable {

	@FXML public ImageView ivCameraPreview;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public Object getImage() {
		return ivCameraPreview.getImage();
	}

}
