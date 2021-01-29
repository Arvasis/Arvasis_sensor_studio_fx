package controller.identification;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicPanelUI;

import arvasis.drawing.GraphicsIO;
import arvasis.drawing.objects.BoundaryCluster;
import arvasis.drawing.objects.PixelLocation;
import arvasis.tool.visualization.DataVisualizer;
import globals.Globals;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class ShapeClassificationController implements Initializable {
	
	
	@FXML public Button btnTrain;
	@FXML public TextField txtTrainCSVFile;
	@FXML public Button btnTrainSelectCSV;
	@FXML public TextField txtObjectDefinition;
	
	@FXML public Button btnDetect;
	@FXML public TextField txtDetectCSVFile;
	@FXML public Button btnDetectSelectCSV;
	@FXML public ColorPicker cpDetectThreshold;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML public void train() {
		System.out.println("train");
		//input box çıksın ismi girsin
		
		String fileName = txtTrainCSVFile.getText();
	//	File file = chooser.getFile();
		String objectDefinition = txtObjectDefinition.getText();
		 // object i global bir yere yaz 
		BoundaryCluster object = GraphicsIO.detectBoundaries(Globals.mapParameter)[0];
		Rectangle r=object.getShapeRectangle();
		boolean[][] result=new boolean[r.height+1][r.width+1];
		for(PixelLocation p:object.getNormalizedPoints())
			result[p.y][p.x]=true;
		DataVisualizer.showImageInNewFrame(result);
		
		Globals.trainResultParameter = result;
		GraphicsIO.saveSimpleClassifierTrainingData(fileName, objectDefinition,  Globals.trainResultParameter);
	}
	
	@FXML public void detect() {
		System.out.println("detect");
		String fileName =txtDetectCSVFile.getText();
		//similarity
		int threshold =  GraphicsIO.getRGB(
				(int) cpDetectThreshold.getValue().getRed(),
				(int) cpDetectThreshold.getValue().getGreen(),
				(int) cpDetectThreshold.getValue().getBlue());

		
	
		BufferedImage i = (BufferedImage) Globals.mainController.getImage();
		boolean[][] map = GraphicsIO.convertImageToMap(i);
		
		BoundaryCluster object = GraphicsIO.detectBoundaries(map)[0];
		Rectangle r=object.getShapeRectangle();
		
		boolean[][] result=new boolean[r.height+1][r.width+1];
		for(PixelLocation p:object.getNormalizedPoints())
			result[p.y][p.x]=true;
		//DataVisualizer.showImageInNewFrame(result);

		
		GraphicsIO.loadSimpleClassifierTrainingData(fileName);
		
		String res = (String) GraphicsIO.detectObjectBySimpleClassifier(0, 1, result);
		//System.out.println(res);
		Globals.setAlertInformation(String.valueOf(res));
	}

}
