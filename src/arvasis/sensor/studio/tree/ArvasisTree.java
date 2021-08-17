package arvasis.sensor.studio.tree;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import arvasis.drawing.GraphicsIO;
import arvasis.tool.visualization.DataVisualizer;
import globals.Globals;
import globals.Globals.ImageType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ArvasisTree extends TreeView<TreeNode> {
	private TreeItem<TreeNode> rootNode;
	private TreeItem<TreeNode> lastAddedNode = null;
	private TreeItem<TreeNode> selectedNode = null;
	
	private final Node rootIcon = 
	        new ImageView(new Image(new File("img/camera.png").toURI().toString()));
	public ArvasisTree(TreeNode treeNode) {
		init(treeNode);
	}

	public void init(TreeNode root) {
		setPrefSize(400, 400);
		  
		rootNode = new TreeItem<TreeNode>(root,rootIcon);
		setRoot(rootNode);
		rootNode.setExpanded(true);

		getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<TreeNode>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<TreeNode>> observable, TreeItem<TreeNode> oldValue,
					TreeItem<TreeNode> newValue) {

				if (newValue == null) {
					return;
				}

				 selectedNode = newValue;

				System.out.println("selected node:"+newValue);
				
				TreeNode nodeInfo = newValue.getValue();
			/*	if (nodeInfo.isEmpty()) {
					selectedNode = newValue;
				} else
					selectedNode = null;
				*/
				if (nodeInfo.getImage() != null) {
					Object image = nodeInfo.getImage();
					Globals.mainController.setImage(image);
				}
				System.out.println("selected node:"+selectedNode);

			}
		});

		final TreePopup treePopup = new TreePopup();
		/*
		 * setContextMenu(treePopup); addEventFilter(MouseEvent.MOUSE_PRESSED, new
		 * EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) {
		 * System.out.println(event.getTarget());
		 * 
		 * if (event.isSecondaryButtonDown()) { if (selectedNode != null) {
		 * System.out.println("rigth clicked....");
		 * 
		 * } event.consume(); }
		 * 
		 * } });
		 */
		setCellFactory(tc -> {

			TreeCell<TreeNode> cell = new TreeCell<TreeNode>() {

				@Override
				protected void updateItem(TreeNode item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(item.toString());
					}
				}

			};

			cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
				if (isNowEmpty) {
					cell.setContextMenu(null);
				} else {
					cell.setContextMenu(treePopup);
				}
			});

			return cell;

		});
		/*
		 * tree.addMouseListener(new MouseAdapter() { public void
		 * mouseReleased(MouseEvent e) { if (e.isPopupTrigger()) { int x = e.getX(); int
		 * y = e.getY(); TreePath pathForLocation = tree.getPathForLocation(x, y);// Get
		 * the path of the tree node where
		 * setSelectedNode((DefaultMutableTreeNode)pathForLocation.getLastPathComponent(
		 * )); // right click
		 * 
		 * tree.setSelectionPath(pathForLocation);
		 * 
		 * treePopup.show(e.getComponent(), e.getX(), e.getY()); } } });
		 */
	}

	public TreeItem<TreeNode> addChild(TreeNode child, boolean isChild) {
		TreeItem<TreeNode> parentNode = null;

		if (isChild) {
			parentNode = lastAddedNode;
		} else
			parentNode = rootNode;

		return addChild(parentNode, child);
	}

	public TreeItem<TreeNode> addChild(TreeNode child) {

		if (selectedNode != null) {
			if (selectedNode.getValue().isEmpty()) {
				return updateSelectedNode(child);
			}
		}
		return addChild(rootNode, child);
	}

	public TreeItem<TreeNode> addChild(TreeItem<TreeNode> parentNode, TreeNode child) {

		TreeItem<TreeNode> childNode = new TreeItem<TreeNode>(child);
		lastAddedNode = childNode;
		parentNode.getChildren().add(childNode);
		if (child.isCondition()) {
			addChild(new TreeNode(), true);
		}
		childNode.setExpanded(true);
		/*
		 * treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
		 * tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		 */

		return childNode;
	}

	public TreeItem<TreeNode> addChildToIndex(TreeItem<TreeNode> nextTo, TreeNode child) {
		TreeItem<TreeNode> childNode = new TreeItem<TreeNode>(child);
		TreeItem<TreeNode> parentNode = nextTo.getParent();
		int index = parentNode.getChildren().indexOf(nextTo) + 1;
		parentNode.getChildren().add(index, childNode);
		/*
		 * int index=parentNode.getIndex(nextTo)+1; treeModel.insertNodeInto(childNode,
		 * parentNode, index); tree.scrollPathToVisible(new
		 * TreePath(childNode.getPath()));
		 */

		return childNode;
	}

	public TreeItem<TreeNode> updateSelectedNode(TreeNode newNode) {
		TreeItem<TreeNode> node = selectedNode;
		TreeNode treeNode = selectedNode.getValue();
		// lastAddedNode=selectedNode;
		System.out.println("condition:" + treeNode.isCondition());

		updateNode(treeNode, newNode.getNodeName(), newNode.getImage(), newNode.getProcessString());
		System.out.println("condition:" + newNode.isCondition());
		if (!newNode.isCondition()) {
			addChild(node.getParent(), new TreeNode());
		} else {
			addChild(node, new TreeNode());
			// addChild(node.getParent(),new TreeNode());
			System.out.println(node.getParent().getValue().getNodeName());
		}
		// Globals.tree.addChild(new TreeNode(),true);
		return node;
	}

	public TreeItem<TreeNode> updateSelectedNode(String nodeName, Object image, String processString) {
		TreeItem<TreeNode> node = selectedNode;
		TreeNode treeNode = selectedNode.getValue();
		updateNode(treeNode, nodeName, image, processString);
		return node;
	}

	public void updateNode(TreeNode node, String nodeName, Object image, String processString) {
		System.out.println();
		System.out.println(node + " " + node.getNodeName() + " " + node.getImage() + " " + node.getProcessString() + " "
				+ node.isEmpty());
		node.setNodeName(nodeName);
		node.setImage(image);
		node.setProcessString(processString);
		node.setEmpty(false);
		if (getSelectedNode() != null && getSelectedNode().previousSibling() != null) {
			updateNextNodes(node);
		}
		System.out.println(
				node.getNodeName() + " " + node.getImage() + " " + node.getProcessString() + " " + node.isEmpty());

		selectedNode = null;

		refresh();
	}

	class TreePopup extends ContextMenu {
		public TreePopup() {
			MenuItem add = new MenuItem("Add New Node Next To");
			MenuItem delete = new MenuItem("Delete");
			MenuItem clear = new MenuItem("Clear");

			delete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					System.out.println("Delete child");
					TreeItem<TreeNode> node = getSelectedNode();
					System.out.println(getSelectedNode());
					updateNextNodes(node);
					node.getParent().getChildren().remove(node);
				}
			});

			clear.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TreeItem<TreeNode> node = getSelectedNode();
					updateNextNodes(node);
					clearNode(node);
					refresh();
				}

			});
			add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TreeItem<TreeNode> node = getSelectedNode();
					System.out.println(node);
					addChildToIndex(node, new TreeNode());
				}
			});
			getItems().addAll(delete, clear, add);

		}
	}

	public void updateNextNodes(TreeItem<TreeNode> node) {
		TreeItem<TreeNode> nodeBefore = node.previousSibling();
		if (nodeBefore == null) {
			nodeBefore = node.getParent();

		}
		TreeNode treeNode = nodeBefore.getValue();
		updateNextNodes(treeNode);
	}

	public void updateNextNodes(TreeNode treeNode) {
		// BufferedImage image=(BufferedImage) ;
		//DataVisualizer.showImageInNewFrame((BufferedImage)treeNode.getImage());
		if (treeNode.getImage() != null) {
			updateImages(treeNode.getImage());

		}
	}

	public void updateImages(Object image) {
		// BufferedImage buff = (BufferedImage) image;
		// DataVisualizer.showImage(buff);
		Globals.engine.putVar("image", Globals.copyObject(image));
		TreeItem<TreeNode> node = getSelectedNode();
		TreeItem<TreeNode> nodeAfter = node.nextSibling();
		while (nodeAfter != null) {
			TreeNode treeNode = nodeAfter.getValue();

			try {

				String processString = treeNode.getProcessString();
				/*
				 * String packageName = "Packages.arvasis.drawing.GraphicsIO"; processString =
				 * processString.replaceAll("GraphicsIO", packageName);
				 */
				System.out.println(processString);
				Globals.engine.runScript(processString);
				Object img = Globals.copyObject(Globals.engine.getVar("image"));
				System.out.println();
				System.out.println("image class:" + img.getClass().getSimpleName());
				System.out.println();
				treeNode.setImage(img);

			} catch (Exception e) {
				e.printStackTrace();
			}

			nodeAfter = nodeAfter.nextSibling();
		}
	}

	public void clearNode(TreeItem<TreeNode> node) {
		TreeNode treeNode = node.getValue();
		treeNode.setNodeName("Empty Node");
		treeNode.setImage(null);
		treeNode.setProcessString(null);
		treeNode.setEmpty(true);
	}

	private String processString = "";
	private String process = "";

	public String getProcessString(TreeItem<TreeNode> node) {
		processString = getProcessStrings(node);
		process = "";
		return processString;
	}

	public String getProcessStrings(TreeItem<TreeNode> node) {
		TreeNode treeNode = node.getValue();
		if (treeNode.getProcessString() != null) {
			process += treeNode.getProcessString() + "\n";

		}
		/*
		 * if (treeNode.getProcessString()!=null) { process+=node; }
		 */
		/// System.out.println("node: " + node);
		// process+=node;

		if (node.getChildren().size() > 0) {
			if (node != rootNode) {
				process += "{\n";
			}

			for (TreeItem<TreeNode> n : node.getChildren()) {
				getProcessStrings(n);
			}
			/*
			 * for (Enumeration e = (Enumeration) node.getChildren(); e.hasMoreElements();)
			 * {
			 * 
			 * TreeItem<TreeNode> n = (TreeItem<TreeNode>) e.nextElement(); // processString
			 * += n; getProcessStrings(n); }
			 */
			if (node != rootNode) {
				process += "} ";
			}
		}
		return process;
	}

	public Object getImageForProcess() {
		Object image = null;
		System.out.println("selected node: "+selectedNode);
		if (selectedNode != null) {
			if (selectedNode.previousSibling() != null) {
				image = selectedNode.previousSibling().getValue().getImage();
			} else {
				TreeItem<TreeNode> parentnode = getSelectedNodeParent();
				TreeNode parent = parentnode.getValue();
				image=parent.getImage();
			}
		}else {
			if (rootNode.getChildren().size()>0) {

				TreeItem<TreeNode> lastChild = rootNode.getChildren().get(rootNode.getChildren().size()-1);
				System.out.println("lastAdded child: "+lastChild.getValue().getNodeName());
				TreeNode lastTreeNode=lastChild.getValue();
				if (lastTreeNode.isCondition()&&!lastTreeNode.isConditionOrLoopHead()) {
					TreeItem<TreeNode> startNode=getConditionOrLoopStart(lastChild);
					System.out.println("start node:"+startNode.getValue().getNodeName());
					try {					
						Globals.engine.putVar("image", startNode.getValue().getImage());

						Globals.engine.runScript(Globals.tree.getProcessStrings(startNode));
						BufferedImage buff=(BufferedImage) Globals.engine.getVar("image");
						DataVisualizer.showImageInNewFrame(buff,"get ýmg f process");
						image= buff;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else image=lastAddedNode.getValue().getImage();
				
			}else image= Globals.image;
		}
		DataVisualizer.showImageInNewFrame((BufferedImage) image);
		image = Globals.copyObject(image);
		return image;
	}

/*	public Object getImageForProcess() {
		Object image = null;
		if (selectedNode != null) {
			TreeNode node = getSelectedTreeNode();
			if (node.isEmpty()) {
				if (selectedNode.previousSibling() != null) {
					image = selectedNode.previousSibling().getValue().getImage();
				} else {
					TreeItem<TreeNode> parentnode = getSelectedNodeParent();
					TreeNode parent = parentnode.getValue();
					if (parentnode != rootNode) {
						image = parent.getImage();

					} else
						image = getSelectedNode().previousSibling().getValue().getImage();
				}

			} else {
				if (rootNode.getChildren().size() > 0) {

					TreeItem<TreeNode> lastChild = rootNode.getChildren().get(rootNode.getChildren().size() - 1);
					System.out.println("lastAdded child: " + lastChild.getValue().getNodeName());
					TreeNode lastTreeNode = lastChild.getValue();

					if (lastTreeNode.isCondition() && !lastTreeNode.isConditionOrLoopHead()) {
						TreeItem<TreeNode> startNode = getConditionOrLoopStart(lastChild);
						System.out.println("start node:" + startNode.getValue().getNodeName());
						try {
							Globals.engine.putVar("image", startNode.getValue().getImage());

							Globals.engine.runScript(Globals.tree.getProcessStrings(startNode));
							BufferedImage buff = (BufferedImage) Globals.engine.getVar("image");
							DataVisualizer.showImageInNewFrame(buff, "get ýmg f process");
							image = buff;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else
						image = lastAddedNode.getValue().getImage();

				} else
					image = rootNode.getValue().getImage();
				// return lastAddedNode.getValue().getImage();

			}
		} else {
			if (rootNode.getChildren().size() > 0) {

				TreeItem<TreeNode> lastChild = rootNode.getChildren().get(rootNode.getChildren().size() - 1);
				System.out.println("lastAdded child: " + lastChild.getValue().getNodeName());
				TreeNode lastTreeNode = lastChild.getValue();
				if (lastTreeNode.isCondition() && !lastTreeNode.isConditionOrLoopHead()) {
					TreeItem<TreeNode> startNode = getConditionOrLoopStart(lastChild);
					System.out.println("start node:" + startNode.getValue().getNodeName());
					try {
						Globals.engine.putVar("image", startNode.getValue().getImage());

						Globals.engine.runScript(Globals.tree.getProcessStrings(startNode));
						BufferedImage buff = (BufferedImage) Globals.engine.getVar("image");
						DataVisualizer.showImageInNewFrame(buff, "get ýmg f process");
						image = buff;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else
					image = lastAddedNode.getValue().getImage();

			} else
				image = Globals.image;
			// return lastAddedNode.getValue().getImage();

		}
		DataVisualizer.showImageInNewFrame((BufferedImage) image);
		image = Globals.copyObject(image);
		return image;
	}
*/
	public TreeItem<TreeNode> getConditionOrLoopStart(TreeItem<TreeNode> lastRootChild) {
		while (lastRootChild.previousSibling() != null) {
			boolean isHead = lastRootChild.previousSibling().getValue().isConditionOrLoopHead();
			if (isHead) {
				return lastRootChild.previousSibling();
			}
			return getConditionOrLoopStart(lastRootChild.previousSibling());
		}
		return null;
	}

	public TreeItem<TreeNode> getSelectedNodeParent() {
		return selectedNode.getParent();
	}

	public TreeItem<TreeNode> getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeItem<TreeNode> rootNode) {
		this.rootNode = rootNode;
	}

	public TreeItem<TreeNode> getLastAddedNode() {
		return lastAddedNode;
	}

	public void setLastAddedNode(TreeItem<TreeNode> lastAddedNode) {
		this.lastAddedNode = lastAddedNode;
	}

	public TreeNode getSelectedTreeNode() {
		if (selectedNode == null) {
			return null;
		}
		return selectedNode.getValue();
	}

	public TreeItem<TreeNode> getSelectedNode() {
		if (selectedNode == null) {
			return null;
		}
		return selectedNode;
	}

	public void setSelectedNode(TreeItem<TreeNode> selectedNode) {
		this.selectedNode = selectedNode;
	}
}
