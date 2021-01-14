package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoopController implements Initializable {
	@FXML
	private ComboBox<String> cbCondition;
	@FXML
	private TextField txtVariable;
	@FXML
	private Label lblVariable;
	
	@FXML
	public void add() {
		
	}

	@FXML
	public void cancel() {
		Stage stage = (Stage) cbCondition.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> condition = FXCollections.observableArrayList(">", "<", ">=", "<=", "==", "!=");
		cbCondition.setItems(condition);
		
		txtVariable.textProperty().addListener((observable, oldValue, newValue) -> {
		  lblVariable.setText(newValue);
		    
		});
	}
}
