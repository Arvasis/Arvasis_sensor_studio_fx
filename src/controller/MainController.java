package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {

	@FXML private ImageView imagePanel;
	
	public void setImage(Image image) {
		
		imagePanel.setImage(image);
		System.out.println("img:"+image.getWidth()+" - "+image.getHeight());
		System.out.println(imagePanel.getFitWidth()+" - "+imagePanel.getFitHeight());
	}
	public Image getImage() {
		return imagePanel.getImage();
	}
	
}
