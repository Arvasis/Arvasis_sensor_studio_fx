package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AnalysisController {
	public void show(Parent root,String title) {
		Stage stage=new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(title);
		stage.show();
	}
	@FXML
	public void readBarcode(){
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/AnalysisAndMeasurement/ReadBarcodeFrame.fxml"));
			show(root, "Read Barcode");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void readText(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/AnalysisAndMeasurement/ReadBarcodeFrame.fxml"));
			show(root, "Read Text");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void edgeParameters(){
		
	}
	@FXML
	public void imageCleaning(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/AnalysisAndMeasurement/ImageCleaningFrame.fxml"));
			show(root, "Image Cleaning");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void presenceAbsenceAnalysis(){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/AnalysisAndMeasurement/PresenceAbsenceAnalysis.fxml"));
			show(root, "Presence Absence Analysis");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void calculatePerimeter(){
		
	}
	@FXML
	public void calculateArea(){
		
	}

}
