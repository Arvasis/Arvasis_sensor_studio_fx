package controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import arvasis.sensor.studio.tree.TreeNode;
import arvasis.tool.visualization.DataVisualizer;
import globals.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class LoopController implements Initializable {
	@FXML
	private ComboBox<String> cbCondition;
	@FXML
	private TextField txtVariable;
	@FXML
	private Label lblVariable;
	@FXML
	private TextField txtNumberOfTurns;
	@FXML
	private TextField txtStartNumber;
	@FXML
	private TextField txtCondition;
	@FXML
	private TextField txtIncreaseAmount;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCancel;

	@FXML
	public void add() {
		//TreeNode treeNode = new TreeNode();
		//TreeItem<TreeNode> node = Globals.tree.addChild(treeNode);

		//TreeNode treeNodePrevious = null;
		//if (node.previousSibling() != null) {
			//treeNodePrevious = node.previousSibling().getValue();
		//} else
			//treeNodePrevious = node.getParent().getValue();
		//Object image = Globals.copyObject(treeNodePrevious.getImage());
		Object image  = Globals.tree.getImageForProcess();
		
		System.out.println(image);
		//BufferedImage buff = (BufferedImage) image;
		//DataVisualizer.showImage(buff);
		
		String nodeName = null;

		String processString = "";
		System.out.println(Globals.forIndex);
		if (txtNumberOfTurns.getText().isEmpty()==false) {
			processString = "for (var i"+Globals.forIndex+"= 0; i"+Globals.forIndex+" <" + txtNumberOfTurns.getText() + "; i"+Globals.forIndex+"++)";
			nodeName = "for (var i"+Globals.forIndex+"= 0; i"+Globals.forIndex+" <" + txtNumberOfTurns.getText() + "; i"+Globals.forIndex+"++)";
			
			Globals.forIndex=Globals.forIndex + 1;
			// Globals.tree.getProcessString(new TreeItem<TreeNode>(treeNode));

		}
		if (!txtVariable.getText().isEmpty() && !txtIncreaseAmount.getText().isEmpty()
				&& !txtStartNumber.getText().isEmpty() && !txtCondition.getText().isEmpty()
				&& cbCondition.getSelectionModel().getSelectedIndex() != -1) {
			
			processString = "for (var " + txtVariable.getText() + "=" + txtStartNumber.getText() + "; "
					+ txtVariable.getText() + getSelectedCondition() + txtCondition.getText() + "; "
					+ txtVariable.getText() + "=" + txtVariable.getText() + "+" + txtIncreaseAmount.getText() + ";)";
			nodeName = "for (var " + txtVariable.getText() + "=" + txtStartNumber.getText() + "; "
					+ txtVariable.getText() + getSelectedCondition() + txtCondition.getText() + "; "
					+ txtVariable.getText() + "=" + txtVariable.getText() + "+" + txtIncreaseAmount.getText() + ";)";
		}

		
	//	Globals.tree.updateNode(treeNode, nodeName, image, processString);
		TreeNode node = new TreeNode(nodeName, image, processString);
		Globals.tree.addChild(node);
		Globals.tree.addChild(new TreeNode(), true);//empyt node
		
		
	}

	@FXML
	public void cancel() {
		Stage stage = (Stage) cbCondition.getScene().getWindow();
		stage.close();
	}

	public String getSelectedCondition() {
		return cbCondition.getSelectionModel().getSelectedItem();
	}
	
	public String getVariable() {
		return txtVariable.getText();
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
