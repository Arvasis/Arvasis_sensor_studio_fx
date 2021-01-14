package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConditionController implements Initializable {
	@FXML
	private VBox conditionsContent;

	@FXML
	public void addElseIf() {
		try {
			GridPane grdPane = FXMLLoader.load(getClass().getResource("/ApplicationSteps/ConditionPanel.fxml"));
			conditionsContent.getChildren().add(grdPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		try {
		
			GridPane grdPane = FXMLLoader.load(getClass().getResource("/ApplicationSteps/ConditionPanel.fxml"));
			conditionsContent.getChildren().add(grdPane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@FXML
	public void cancel() {
		((Stage)conditionsContent.getScene().getWindow()).close();
	}

}
