package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConditionPanelController implements Initializable {
	@FXML private ComboBox<String> cbFunction;
	@FXML private ComboBox<String> cbCondition;
	@FXML private TextField txtValue;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> functions=FXCollections.observableArrayList(
				"Perimeter","Area","Text","Barcode","Pharmacode");
			cbFunction.setItems(functions);
			ObservableList<String> condition=FXCollections.observableArrayList(
					">","<",">=","<=","==","!=");
			cbCondition.setItems(condition);
			
			
	}
	public String getSelectedFunction() {
		return cbFunction.getSelectionModel().getSelectedItem();
	}
	public String getSelectedCondition() {
		return cbCondition.getSelectionModel().getSelectedItem();
	}
	public String getValue() {
		return txtValue.getText();
	}
}
