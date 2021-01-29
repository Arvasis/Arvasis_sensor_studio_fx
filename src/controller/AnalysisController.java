package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import arvasis.drawing.GraphicsIO;
import arvasis.drawing.objects.BoundaryCluster;
import arvasis.drawing.objects.PixelLocation;
import controller.analysis.ReadController;
import controller.analysis.ReadController.ReadType;
import globals.Globals;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AnalysisController {
	private static double objectPerimeter,objectArea;
	public void show(Parent root, String title) {
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(title);
		stage.show();
	}

	@FXML
	public void readBarcode() {
		try {
			FXMLLoader barcodeLoader = new FXMLLoader(
					getClass().getResource("/AnalysisAndMeasurement/ReadBarcodeFrame.fxml"));
			Parent root = barcodeLoader.load();
			ReadController controller = barcodeLoader.getController();
			controller.setType(ReadType.Barcode);
			show(root, "Read Barcode");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void readText() {
		try {
			FXMLLoader textLoader = new FXMLLoader(
					getClass().getResource("/AnalysisAndMeasurement/ReadBarcodeFrame.fxml"));
			Parent root = textLoader.load();
			ReadController controller = textLoader.getController();
			controller.setType(ReadType.Text);
			show(root, "Read Text");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void edgeParameters() {

	}

	@FXML
	public void imageCleaning() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/AnalysisAndMeasurement/ImageCleaningFrame.fxml"));
			show(root, "Image Cleaning");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void presenceAbsenceAnalysis() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/AnalysisAndMeasurement/PresenceAbsenceAnalysis.fxml"));
			show(root, "Presence Absence Analysis");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void calculatePerimeter() {
		Object img = globals.Globals.mainController.getImage();
		calculatePerimeter((BufferedImage) img);
		new Alert(AlertType.INFORMATION,"Perimeter: " + String.valueOf(objectPerimeter)).show();
		String process="perimeter=Packages.controller.AnalysisController.calculatePerimeter(image);\r\n";
		//TODO add node to tree

	}

	@FXML
	public void calculateArea() {
		Object img = globals.Globals.mainController.getImage();
		calculateArea((BufferedImage) img);
		new Alert(AlertType.INFORMATION,"Area: " + String.valueOf(objectArea)).show();
		String process="area=Packages.controller.AnalysisController.calculateArea(image);\r\n";
		
		//TODO add node to tree
	}
	public static double calculateArea(BufferedImage image) {
		BoundaryCluster[] c = GraphicsIO.detectBoundaries(image);
		c[0].initShapeStatistics();
		objectArea = c[0].getPoints().length;
		return objectArea;
	}

	public static double calculatePerimeter(BufferedImage image) {
		BoundaryCluster[] c = GraphicsIO.detectBoundaries(image);

		c[0].initShapeStatistics();
		PixelLocation[] pl = c[0].getEdgePoints();
		objectPerimeter = pl.length;
		return objectPerimeter;
	}
}
