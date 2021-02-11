package controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.LoaderHandler;
import java.util.ResourceBundle;

import globals.Globals;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import arvasis.drawing.GraphicsIO;
import globals.Globals.ImageType;
import javafx.embed.swing.SwingFXUtils;
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

	@FXML
	public BorderPane mainPane;
	@FXML
	private ImageView imagePanel;

	public TitledPane applicationStepsPanel;
	public HBox menuPanel;

	public void setImage(Object image) {

		imagePanel.setImage(convertObjectToImage(image));

	}

	public Object getImage() {
		Image image = imagePanel.getImage();
		return convertImageToObject(image);
	}

	public Image convertObjectToImage(Object obj) {
		Image image;
		BufferedImage buff = null;
		if (Globals.imageType == ImageType.BufferedImage) {
			buff = (BufferedImage) obj;

		} else if (Globals.imageType == ImageType.Integer) {
			buff = GraphicsIO.convertArrayToImage((int[][]) obj);

		} else if (Globals.imageType == ImageType.Boolean) {
			buff = GraphicsIO.convertMapToImage((boolean[][]) obj);

		}
		image = SwingFXUtils.toFXImage(buff, null);
		return image;
	}

	public Object convertImageToObject(Image image) {
		Object obj = null;
		BufferedImage buff = SwingFXUtils.fromFXImage(image, null);
		obj = buff;
		if (Globals.imageType == ImageType.Integer) {
			obj = GraphicsIO.convertToIntegerArray(buff);

		} else if (Globals.imageType == ImageType.Boolean) {
			obj = GraphicsIO.convertImageToMap(buff);
		}

		return obj;
	}

	public HBox getMenuPanel() {
		return menuPanel;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader appliLoader = new FXMLLoader(getClass().getResource("/Main/ApplicationStepsPanel.fxml"));
			applicationStepsPanel = appliLoader.load();
			ApplicationStepsController applicationStepsController = appliLoader.getController();

			FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/Main/MenuPanel.fxml"));
			menuPanel = menuLoader.load();

			MenuController menuController = menuLoader.getController();
			applicationStepsController.setMenuController(menuController);

			mainPane.setTop(menuPanel);
			mainPane.setLeft(applicationStepsPanel);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageView getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImageView imagePanel) {
		this.imagePanel = imagePanel;
	}

}
