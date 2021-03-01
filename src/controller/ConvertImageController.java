package controller;
import java.awt.image.BufferedImage;

import arvasis.drawing.GraphicsIO;
import arvasis.sensor.studio.tree.TreeNode;
import globals.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ConvertImageController {
	@FXML
	private Button btnCancel;
	@FXML 
	private Button btnConvert;
	@FXML
	private Label lblInfo;

	@FXML
	public RadioButton rbBufferedImage;
	@FXML
	public RadioButton rbIntegerImage;
	@FXML
	public RadioButton rbBooleanImage;

	
	@FXML
	public void convert() {
		String processString = null;
		if (rbBufferedImage.isSelected() == true && Globals.imageType == Globals.ImageType.Integer) {
			//BufferedImage image = GraphicsIO.convertArrayToImage((int[][]) Globals.tree.getImageForProcess());
			
			processString = "image=Packages.arvasis.drawing.GraphicsIO.convertArrayToImage(image);";
			Globals.runScript(processString);
			Object obj = Globals.engine.getVar("image");
			Globals.tree.addChild(new TreeNode("Convert BufferedImage Image: Image",obj,processString));
			
			Globals.imageType = Globals.ImageType.BufferedImage;
			lblInfo.setText("Converted to "+Globals.ImageType.Integer+" -> "+rbBufferedImage.getText());
		}
		if (rbBufferedImage.isSelected() == true && Globals.imageType == Globals.ImageType.Boolean) {
			//BufferedImage image = GraphicsIO.convertMapToImage((boolean[][])Globals.tree.getImageForProcess());
			
			processString = "image=Packages.arvasis.drawing.GraphicsIO.convertMapToImage(image);";
			Globals.runScript(processString);
			Object obj = Globals.engine.getVar("image");
			Globals.tree.addChild(new TreeNode("Convert BufferedImage Image: Image",obj,processString));
			
			Globals.imageType = Globals.ImageType.BufferedImage;
			lblInfo.setText("Converted to "+Globals.ImageType.Boolean+" -> "+rbBufferedImage.getText());
		}
		if (rbIntegerImage.isSelected() == true && Globals.imageType == Globals.ImageType.BufferedImage) {
			//int[][] image = GraphicsIO.convertToIntegerArray((BufferedImage)Globals.tree.getImageForProcess());
			
			processString = "image=Packages.arvasis.drawing.GraphicsIO.convertToIntegerArray(image);";
			Globals.runScript(processString);
			Object obj = Globals.engine.getVar("image");
			Globals.tree.addChild(new TreeNode("Convert Integer Image: Image",obj,processString));
			
			Globals.imageType = Globals.ImageType.Integer;
			lblInfo.setText("Converted to "+Globals.ImageType.BufferedImage+" -> "+rbIntegerImage.getText());
		}
		if (rbIntegerImage.isSelected() == true && Globals.imageType == Globals.ImageType.Boolean) {
			//int[][] image = GraphicsIO.convertMapToImageArray((boolean[][]) Globals.tree.getImageForProcess());
			
			processString = "image=Packages.arvasis.drawing.GraphicsIO.convertMapToImageArray(image);";
			Globals.runScript(processString);
			Object obj = Globals.engine.getVar("image");
			Globals.tree.addChild(new TreeNode("Convert Integer Image: Image",obj,processString));
			
			Globals.imageType = Globals.ImageType.Integer;
			lblInfo.setText("Converted to "+Globals.ImageType.Boolean+" -> "+rbIntegerImage.getText());
		}
		if (rbBooleanImage.isSelected() == true && Globals.imageType == Globals.ImageType.BufferedImage) {
			//boolean[][] image = GraphicsIO.convertImageToMap((BufferedImage) Globals.tree.getImageForProcess());
			
			processString = "image=Packages.arvasis.drawing.GraphicsIO.convertImageToMap(image);";
			Globals.runScript(processString);
			Object obj = Globals.engine.getVar("image");
			Globals.tree.addChild(new TreeNode("Convert Boolean Image: Image",obj,processString));
			
			Globals.imageType = Globals.ImageType.Boolean;
			lblInfo.setText("Converted to "+Globals.ImageType.BufferedImage+" -> "+rbBooleanImage.getText());
		}
		if (rbBooleanImage.isSelected() == true && Globals.imageType == Globals.ImageType.Integer) {
			//boolean[][] image = GraphicsIO.convertImageToMap((int[][]) Globals.tree.getImageForProcess());
			
			processString = "image=Packages.arvasis.drawing.GraphicsIO.convertImageToMap(image);";
			Globals.runScript(processString);
			Object obj = Globals.engine.getVar("image");
			Globals.tree.addChild(new TreeNode("Convert Boolean Image: Image",obj,processString));
			
			Globals.imageType = Globals.ImageType.Boolean;
			lblInfo.setText("Converted to "+Globals.ImageType.Integer+" -> "+rbBooleanImage.getText());
		}
		//TODO tree add child eklenecek
		// Globals.tree.addChild(new TreeNode(btnConvert.getText(),processString));
	}
	
	@FXML
	public void cancel() {
		((Stage) btnCancel.getScene().getWindow()).close();
	}

}
