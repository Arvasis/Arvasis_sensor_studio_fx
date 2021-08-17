package test;

import arvasis.drawing.GraphicsIO;
import interfacebuilder.FiltersFrame;
import interfacebuilder.InterfaceBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BuilderTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*InterfaceBuilder builder=new InterfaceBuilder(GraphicsIO.class);
	
		ListView<String> methods=new ListView<String>();
		methods.setPrefWidth(200);
		methods.setPrefHeight(200);
		methods.setItems(builder.getFunctionsName());
		
		primaryStage.setScene(new Scene(methods));
		primaryStage.show();*/
		
		Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/ApplicationSteps/BasicCVFunctionsFrame3.fxml")));
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
