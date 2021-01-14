package controller;

import java.net.URL;
import java.util.ResourceBundle;

import arvasis.drawing.GraphicsIO;
import builder.InterfaceBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class BasicCVFunctionsController implements Initializable {

	// @FXML private VBox functionsPane;
	@FXML
	private ListView<String> functionList;
	private InterfaceBuilder builder;

	private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			builder.buildFrame(builder.getMethodFromIndex(functionList.getSelectionModel().getSelectedIndex()));
		}
	};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		builder = new InterfaceBuilder(GraphicsIO.class);
		/*
		 * for (String name : builder.getFunctionsNameAsArray()) { Button btn=new
		 * Button(); btn.setText(name); btn.setPrefWidth(325); btn.setOnAction(event);
		 * functionsPane.getChildren().add(btn); }
		 */
		functionList.setItems(builder.getFunctionsName());
		functionList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				// TODO Auto-generated method stub
				System.out.println("newValue");
				builder.buildFrame(builder.getMethodFromIndex(functionList.getSelectionModel().getSelectedIndex()));
			
			}
		});
		/*functionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("newValue");
				builder.buildFrame(builder.getMethodFromIndex(functionList.getSelectionModel().getSelectedIndex()));
			}

		});*/
	}

}
