package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IdentificationController {
	
	public void showFilter(Parent content,String title) {
		GridPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/Main/FiltersFrame.fxml"));
			VBox box=(VBox) root.getChildren().get(0);
			Pane pane=(Pane) box.getChildren().get(0);
			Button btn=(Button) box.getChildren().get(1);
			pane.getChildren().add(content);
			btn.setTranslateY(((Region) content).getPrefHeight());

			show(root,title);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void show(Parent root,String title) {
		Stage stage=new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(title);
		stage.show();
	}
	@FXML
	public void openFixedColorExtraction(){
		try {
			GridPane content = FXMLLoader.load(getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			showFilter(content,"Fixed Color Extraction");			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	@FXML
	public void openBackgroundExtraction(){
		
	}
	@FXML
	public void openShapeClassification(){
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("/Identification&Locate/ShapeClassification.fxml"));
			loader.setController(this);
			Parent root=loader.load();
			
			show(root,"Shape Classification");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void openKeyPointAlg(){
		
	}
	@FXML
	public void thresholdForMap(){
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			showFilter(root,"Threshold For Map");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@FXML
	public void blueThresholdForMap(){
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			showFilter(root,"Blue Threshold For Map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void greenThresholdForMap(){
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			showFilter(root,"Green Threshold For Map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void redThresholdForMap(){
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Identification&Locate/RedThresholdForMapFrame.fxml"));
			showFilter(root,"Red Threshold For Map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openTrain() {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Identification&Locate/Shape/TrainingFrame.fxml"));
			show(root,"Train");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openDetection() {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Identification&Locate/Shape/DetectionFrame.fxml"));
			show(root,"Train");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
