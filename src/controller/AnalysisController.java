package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import arvasis.drawing.GraphicsIO;
import arvasis.drawing.objects.BoundaryCluster;
import arvasis.drawing.objects.PixelLocation;
import arvasis.sensor.studio.tree.TreeNode;
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
		//calculatePerimeter((BufferedImage) img);
		String process="objectPerimeter=Packages.controller.AnalysisController.calculatePerimeter(image);\r\n";
		Globals.runScript(process);
		objectPerimeter=(double) Globals.engine.getVar("objectPerimeter");
		new Alert(AlertType.INFORMATION,"Perimeter: " + String.valueOf(objectPerimeter)).show();
		Globals.tree.addChild(new TreeNode("Calculate Perimeter: "+objectPerimeter,img,process));
	}

	@FXML
	public void calculateArea() {
		Object img = Globals.tree.getImageForProcess();
		//calculateArea((BufferedImage) img);
		String process="objectArea=Packages.controller.AnalysisController.calculateArea(image);\r\n";
		Globals.runScript(process);
		objectArea=(double) Globals.engine.getVar("objectArea");
		new Alert(AlertType.INFORMATION,"Area: " + String.valueOf(objectArea)).show();
		/*Globals.runScript(process);
		new Alert(AlertType.INFORMATION,"Area: " + String.valueOf(Globals.engine.getVar("area"))).show();*/
		Globals.tree.addChild(new TreeNode("Calculate Area: "+objectArea,img,process));
		
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
