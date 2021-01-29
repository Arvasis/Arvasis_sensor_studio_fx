package controller;
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
		String processString = "";
		if (rbBufferedImage.isSelected() == true && Globals.imageType == Globals.ImageType.Integer) {
			processString = "image=package arvasis.drawing.GraphicsIO.convertArrayToImage(image);";
			Globals.imageType = Globals.ImageType.BufferedImage;
			lblInfo.setText("Converted to "+Globals.ImageType.Integer+" -> "+rbBufferedImage.getText());
		}
		if (rbBufferedImage.isSelected() == true && Globals.imageType == Globals.ImageType.Boolean) {
			processString = "image=package arvasis.drawing.GraphicsIO.convertMapToImage(image);";
			Globals.imageType = Globals.ImageType.BufferedImage;
			lblInfo.setText("Converted to "+Globals.ImageType.Boolean+" -> "+rbBufferedImage.getText());
		}
		if (rbIntegerImage.isSelected() == true && Globals.imageType == Globals.ImageType.BufferedImage) {
			processString = "image=package arvasis.drawing.GraphicsIO.convertToIntegerArray(image);";
			Globals.imageType = Globals.ImageType.Integer;
			lblInfo.setText("Converted to "+Globals.ImageType.BufferedImage+" -> "+rbIntegerImage.getText());
		}
		if (rbIntegerImage.isSelected() == true && Globals.imageType == Globals.ImageType.Boolean) {
			processString = "image=package arvasis.drawing.GraphicsIO.convertMapToImageArray(image);";
			Globals.imageType = Globals.ImageType.Integer;
			lblInfo.setText("Converted to "+Globals.ImageType.Boolean+" -> "+rbIntegerImage.getText());
		}
		if (rbBooleanImage.isSelected() == true && Globals.imageType == Globals.ImageType.BufferedImage) {
			processString = "image=package arvasis.drawing.GraphicsIO.convertImageToMap(image);";
			Globals.imageType = Globals.ImageType.Boolean;
			lblInfo.setText("Converted to "+Globals.ImageType.BufferedImage+" -> "+rbBooleanImage.getText());
		}
		if (rbBooleanImage.isSelected() == true && Globals.imageType == Globals.ImageType.Integer) {
			processString = "image=package arvasis.drawing.GraphicsIO.convertImageToMap(image);";
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
