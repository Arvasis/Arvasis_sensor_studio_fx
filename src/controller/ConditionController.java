package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.tree.DefaultMutableTreeNode;

import arvasis.sensor.studio.tree.TreeNode;
import globals.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConditionController implements Initializable {
	@FXML
	private VBox conditionsContent;
	@FXML
	private CheckBox hasElse;
	private String conditionString = "";
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
		int i = 0;
		String process = "";
		Object image = Globals.tree.getImageForProcess();

		String nodeName = "";
		String processString = "";
		TreeItem<TreeNode> parent=null;
		for (ConditionPanelController panel : panelControllers) {
			processString="";

			if (panel.isFilled()) {
				if (i == 0) {
					// processString += process;
					processString += "if(";
				} else
					processString += "else if(";
				int index = panel.getSelectedFunctionIndex();

				if (index == 0) {

					processString += "objectPerimeter";
					nodeName = "objectPerimeter";
				} else if (index == 1) {

					processString += "objectArea";
					nodeName = "objectArea";
				} else if (index == 2) {
					// Text
					processString += "res";
					nodeName = "result";
				} else if (index == 3) {
					// Barcode
					processString += "barcode";
					nodeName = "barcode";
				} else if (index == 4) {
					// Pharmacode
					processString += "pharmacode";
					nodeName = "pharmacode";
				}
				nodeName += panel.getSelectedCondition() + "\"" + panel.getValue() + "\"";
				processString += panel.getSelectedCondition() + "\"" + panel.getValue() + "\"" + ")";

				TreeNode node=new TreeNode(nodeName,image,processString);
				node.setCondition(true);
				if (i==0) {
					node.setConditionOrLoopHead(true);
					parent=Globals.tree.addChild(node);

				}else Globals.tree.addChild(parent.getParent(),node);
				
				
				i++;

			}
		}
		if (hasElse.isSelected()) {
			processString = "else";
			TreeNode elseNode=new TreeNode(processString, image, processString);
			elseNode.setCondition(true);
			TreeItem<TreeNode> node=Globals.tree.addChild(parent.getParent(),elseNode);
		//	Globals.tree.addChild(new TreeNode(),true);
		}
		if (parent.getParent()!=Globals.tree.getRoot()) {
			Globals.tree.addChild(parent.getParent(),new TreeNode());

		}
	 cancel();
	}

	@FXML
	public void cancel() {
		((Stage) conditionsContent.getScene().getWindow()).close();
	}

}
