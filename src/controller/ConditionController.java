package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConditionController implements Initializable {
	@FXML
	private VBox conditionsContent;
	@FXML
	private CheckBox hasElse;
	private String conditionString="";
	private ArrayList<ConditionPanelController> panelControllers = new ArrayList<ConditionPanelController>();

	@FXML
	public void addElseIf() {
		panelControllers.add(addConditionPanel());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		panelControllers.add(addConditionPanel());

	}

	public ConditionPanelController addConditionPanel() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ApplicationSteps/ConditionPanel.fxml"));
			GridPane grdPane = loader.load();
			conditionsContent.getChildren().add(grdPane);
			return loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@FXML
	public void addCondition() {
		System.out.println("add condition..");
		int i=1;
		for (ConditionPanelController conditionPanelController : panelControllers) {
			if (i!=1) {
				conditionString+="else ";
			}
			conditionString += "if(";
			conditionString+=conditionPanelController.getSelectedFunction()+conditionPanelController.getSelectedCondition()+conditionPanelController.getValue()+"){\n\n\n}";
			i++;
		}
		if (hasElse.isSelected()) {
			conditionString+="else{\n\n\n}";
		}
		System.out.println(panelControllers.size());
		System.out.println("condition string:\n"+conditionString);
		// TODO tree ye eklenecek
		
		cancel();
	}

	@FXML
	public void cancel() {
		((Stage) conditionsContent.getScene().getWindow()).close();
	}

}
