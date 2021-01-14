package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FlashSettingsController implements Initializable{
	@FXML private ComboBox<String> cbLedID;
	@FXML private ColorPicker cpColor;
	
	@FXML
	public void send() {
		
	}
	
	@FXML
	public void cancel() {
		((Stage)cpColor.getScene().getWindow()).close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options=FXCollections.observableArrayList(
				"All","Odd","Even"	);
		int k = 3;
		for (int i = 0; i < 24; i++) {
			options.add(""+i);
			k++;

		}
		cbLedID.setItems(options);
	}
}
