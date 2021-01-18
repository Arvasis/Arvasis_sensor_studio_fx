package application;
	
import controller.MenuController;
import globals.Globals;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/ArvasisSensorStudio.fxml"));
		
			BorderPane mainPanel=loader.load();
			Globals.mainController=loader.getController();

			Scene scene = new Scene(mainPanel,1280,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Arvasis Sensor Studio");
			primaryStage.setScene(scene);
			primaryStage.show();
			this.primaryStage=primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		primaryStage.close();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
