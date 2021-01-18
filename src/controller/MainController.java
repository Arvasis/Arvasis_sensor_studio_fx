package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import arvasis.drawing.GraphicsIO;
import globals.Globals;
import globals.Globals.ImageType;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {

	@FXML private ImageView imagePanel;
	
	public void setImage(Object image) {
		
		imagePanel.setImage(convertObjectToImage(image));
		
	}
	public Object getImage() {
		Image image=imagePanel.getImage();
		return convertImageToObject(image);
	}

	public Image convertObjectToImage(Object obj) {
		Image image;
		BufferedImage buff =null;
		if (Globals.imageType==ImageType.BufferedImage) {
			buff=(BufferedImage)obj;
		}
		else if (Globals.imageType==ImageType.Integer) {
			buff=GraphicsIO.convertArrayToImage((int[][])obj);

		}else if (Globals.imageType==ImageType.Boolean) {
			buff=GraphicsIO.convertMapToImage((boolean[][])obj);
		}
		image=SwingFXUtils.toFXImage(buff, null);
		return image;
	}
	public Object convertImageToObject(Image image) {
		Object obj=null;
		BufferedImage buff=SwingFXUtils.fromFXImage(image, null);
		obj=buff;
		if (Globals.imageType==ImageType.Integer) {
			obj=GraphicsIO.convertToIntegerArray(buff);

		}else if (Globals.imageType==ImageType.Boolean) {
			obj=GraphicsIO.convertImageToMap(buff);
		}
		
		return obj;
	}
}
