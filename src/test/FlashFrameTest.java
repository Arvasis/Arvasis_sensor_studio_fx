package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FlashFrameTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent mainPanel=FXMLLoader.load(getClass().getResource("/ApplicationSteps/FlashSettingsFrame.fxml"));
			
			Scene scene = new Scene(mainPanel);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
