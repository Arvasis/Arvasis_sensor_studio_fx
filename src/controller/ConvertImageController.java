package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConvertImageController {
	@FXML private Button btnCancel;
	@FXML
	public void cancel() {
		((Stage)btnCancel.getScene().getWindow()).close();
	}
	
}
