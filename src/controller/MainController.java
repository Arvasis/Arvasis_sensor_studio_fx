package controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.LoaderHandler;
import java.util.ResourceBundle;

import globals.Globals;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

	@FXML public BorderPane mainPane;
	@FXML private ImageView imagePanel;
	
	public TitledPane applicationStepsPanel ;
	public HBox menuPanel;
	
	public void setImage(Image image) {
		
		imagePanel.setImage(image);
		System.out.println("img:"+image.getWidth()+" - "+image.getHeight());
		System.out.println(imagePanel.getFitWidth()+" - "+imagePanel.getFitHeight());
	}
	public Image getImage() {
		return imagePanel.getImage();
	}
	
	public HBox getMenuPanel() {
		return menuPanel;
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
				FXMLLoader appliLoader=new FXMLLoader(getClass().getResource("/Main/ApplicationStepsPanel.fxml"));
				applicationStepsPanel = appliLoader.load();
				ApplicationStepsController applicationStepsController=appliLoader.getController();
				
				FXMLLoader menuLoader=new FXMLLoader(getClass().getResource("/Main/MenuPanel.fxml"));
				menuPanel = menuLoader.load();
				
				MenuController menuController=menuLoader.getController();
				applicationStepsController.setMenuController(menuController);
				
				mainPane.setTop(menuPanel);
				mainPane.setLeft(applicationStepsPanel);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		 
		 
	}
	
}
