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
		TreeNode treeNode=new TreeNode();
		TreeItem<TreeNode> node=Globals.tree.addChild(treeNode);
		
		int i = 0;
		String process = "";
/*		for (ConditionPanelController panel : panelControllers) {
			
			int index=panel.getSelectedFunctionIndex();
			System.out.println("index:"+index);
			
			if (index == 0) {

				process += "c = GraphicsIO.detectBoundaries(image);\nc[0].initShapeStatistics();\npl=c[0].getEdgePoints();\nobjectPerimeter=pl.length;\n";
			/*	System.out.println("perimeter:" + MeasureCalculationFrame
						.calculatePerimeter((BufferedImage) ArvasisSensorStudio.ip.getOriginalImage()));*/
/*			} else if(index==1){
				process += "c = GraphicsIO.detectBoundaries(image);c[0].initShapeStatistics();objectArea=c[0].getPoints().length;";
	/*			System.out.println("area:" + MeasureCalculationFrame
						.calculateArea((BufferedImage) ArvasisSensorStudio.ip.getOriginalImage()));*/

/*			}else if (index==2) {
				//Text
				process+="text=Packages.arvasis.sensor.studio.productAnalysis.ReadTextFrame.readImage(image);\ntext=text.trim();";
			}else if (index==3) {
				
				//Barcode
				process+="barcode=GraphicsIO.readBarcode(image);";
			}else if (index==4) {
				//Pharmacode
				process+="pharmacode=GraphicsIO.readPharmacode(image)"; 
			}
		}
*/		TreeNode treeNodePrevious=null;
		if (node.previousSibling()!=null) {
			treeNodePrevious= node.previousSibling().getValue();
		}else treeNodePrevious=node.getParent().getValue();
		Object image=Globals.copyObject(treeNodePrevious.getImage());
		
	/*	if (Globals.tree.getRootNode().getChildCount()>1) {
			System.out.println("child count>1");
			TreeNode node = (TreeNode) Globals.tree
					.getChild(Globals.tree.getRootNode(), (Globals.tree.getRootChildCount() - 1)).getUserObject();
			image = Globals.copyObject(node.getImage());
		}else {
			System.out.println("child count<1");
			TreeNode node = (TreeNode) Globals.tree.getRootNode().getUserObject();
			image = Globals.copyObject(node.getImage());

		}*/
		
		
		String nodeName = "";
		
		for (ConditionPanelController panel : panelControllers) {
			String processString = "";

			if (panel.isFilled()) {
				if (i == 0) {
				//	processString += process;
					processString += "if(";
				} else
					processString += "else if(";
				int index=panel.getSelectedFunctionIndex();
			
				if (index==0) {

					processString += "objectPerimeter";
					nodeName = "objectPerimeter";
				} else if (index==1) {

					processString += "objectArea";
					nodeName = "objectArea";
				}else if (index==2) {
					//Text
					processString+="res";
					nodeName="result";
				}else if (index==3) {
					//Barcode
					processString+="barcode";
					nodeName="barcode";
				}else if(index==4) {
					//Pharmacode
					processString+="pharmacode";
					nodeName="pharmacode";
				}
				nodeName += panel.getSelectedCondition() + "\""+panel.getValue()+ "\"";
				processString += panel.getSelectedCondition() +  "\""+panel.getValue() +"\"" + ")";
		/*		if (Globals.tree.getSelectedTreeNode() != null) {
					DefaultMutableTreeNode parent = Globals.tree.updateSelectedNode(nodeName, image,
							processString);
					DefaultMutableTreeNode grandParent = Globals.tree.getParent(parent);
					Globals.tree.addChild(parent, new TreeNode());
					Globals.tree.addChild(grandParent, new TreeNode());

				} else {*/
					//Globals.tree.addChild(new TreeNode(nodeName, image, processString));
				if (i==0) {
					Globals.tree.updateNode(treeNode, nodeName, image, processString);
					System.out.println(processString);
				}else {
					System.out.println();
					System.out.println(processString);

					Globals.tree.addChild(new TreeNode(nodeName,image,processString));
				}
				
					Globals.tree.addChild(new TreeNode(), true);
			//	}
				
			//	Globals.tree.addChild(grandParent, new TreeNode());

				i++;
							
		}
		if (hasElse.isSelected()) {
			processString = "else";
			/*Globals.tree.addChild(new TreeNode(processString, image, processString));
			Globals.tree.addChild(new TreeNode(), true);*/
		}
	}

/*		TreeNode treeNode = new TreeNode();
		TreeItem<TreeNode> node = Globals.tree.addChild(treeNode);

		TreeNode treeNodePrevious = node.previousSibling().getValue();
		Object image = Globals.copyObject(treeNodePrevious.getImage());

		int i = 1;
		String nodeName = "";
		for (ConditionPanelController conditionPanelController : panelControllers) {
			if (i != 1) {
				conditionString += "else ";
			}
			conditionString += "if(";
			conditionString += conditionPanelController.getSelectedFunction()
					+ conditionPanelController.getSelectedCondition() + conditionPanelController.getValue()
					+ "){\n\n\n}";
			nodeName = conditionPanelController.getSelectedFunction() + conditionPanelController.getSelectedCondition()
					+ conditionPanelController.getValue();
			System.out.println(nodeName);
			if (i == 1) {
				addconditionToTree(nodeName, image, null);

			} else
				addconditionToTree(nodeName, image, treeNode);

			i++;
		}
		if (hasElse.isSelected()) {
			conditionString += "else{\n\n\n}";
		}

		// TODO tree ye eklenecek

*/		cancel();
	}

	public void addconditionToTree(String nodeName, Object image, TreeNode treeNode) {
		/*
		 * if (Globals.tree.getSelectedTreeNode() != null) { TreeItem<TreeNode> parent =
		 * Globals.tree.updateSelectedNode(nodeName, image, conditionString);
		 * TreeItem<TreeNode> grandParent =
		 * parent.getParent();//Globals.tree.getParent(parent);
		 * Globals.tree.addChild(parent, new TreeNode());
		 * Globals.tree.addChild(grandParent, new TreeNode());
		 * 
		 * } else {
		 */
		// Globals.tree.addChild(new TreeNode(nodeName, image, processString));
		if (treeNode!=null) {
			Globals.tree.updateNode(treeNode, nodeName, image, conditionString);
			/*treeNode.setNodeName(nodeName);
			treeNode.setImage(image);
			treeNode.setProcessString(conditionString);
			treeNode.setEmpty(false);*/
		}else Globals.tree.addChild(new TreeNode(nodeName,image,conditionString));
		
		Globals.tree.addChild(new TreeNode(), true);
		// }
	}

	@FXML
	public void cancel() {
		((Stage) conditionsContent.getScene().getWindow()).close();
	}

}
