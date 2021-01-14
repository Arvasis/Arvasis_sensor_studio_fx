package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FiltersFrameTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = FXMLLoader.load(getClass().getResource("/Main/FiltersFrame.fxml"));
		VBox content = FXMLLoader.load(getClass().getResource("/Identification&Locate/RedThresholdForMapFrame.fxml"));

		VBox box=(VBox) root.getChildren().get(0);
		Button btn=(Button) box.getChildren().get(1);
		Pane pane=(Pane) box.getChildren().get(0);
		pane.getChildren().add(content);
	/*	btn.setLayoutX(200);
		btn.setLayoutY(400);*/
		System.out.println(content.getPrefHeight());
		btn.setTranslateY(content.getPrefHeight());
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
