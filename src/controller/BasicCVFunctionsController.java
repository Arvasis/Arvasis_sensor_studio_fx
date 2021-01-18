package controller;

import java.lang.reflect.Method;
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

	@FXML
	private VBox functionsPane;
	// @FXML private ListView<String> functionList;
	private InterfaceBuilder builder;

	private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button clickedBtn=(Button) event.getSource();
			builder.buildFrame((Method)clickedBtn.getUserData());
		}
	};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		builder = new InterfaceBuilder(GraphicsIO.class);

		for (Method method : builder.getMethods()) {
			Button btn = new Button();
			btn.setUserData(method);
			btn.setText(method.getName());
			btn.setPrefWidth(325);
			btn.setOnAction(event);
			functionsPane.getChildren().add(btn);
		}
	}

}
