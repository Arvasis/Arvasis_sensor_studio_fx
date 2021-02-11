package test;

import java.awt.image.BufferedImage;

import arvasis.camera.Camera;
import arvasis.camera.VirtualCamera;
import arvasis.drawing.GraphicsIO;
import arvasis.sensor.studio.tree.ArvasisTree;
import arvasis.sensor.studio.tree.TreeNode;
import controller.ConditionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

public class ArvasisTreeTest extends Application {
	static ArvasisTree tree = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Camera cam = new VirtualCamera();
		BufferedImage image = GraphicsIO
				.readBufferedImage("C:\\Users\\Arvasis\\Documents\\KT\\img\\642969956309746.jpg");
		TreeNode root = new TreeNode(cam.toString(), image);

		tree = new ArvasisTree(root);
		BufferedImage image1 = GraphicsIO.applyCanny(image);
		BufferedImage image2 = GraphicsIO.applyDilation(image1, 3);
		BufferedImage image3 = GraphicsIO.applyErosion(image, 2);

		tree.addChild(new TreeNode("Canny", image1));
		tree.addChild(new TreeNode("Dilation", image2));
		// tree.addChild(new TreeNode("Erosion", image3));
		tree.getRoot().getChildren().add(0, new TreeItem<TreeNode>(new TreeNode("Erosion", image3)));
		
		addcondition(tree.getImageForProcess());
		Scene scene = new Scene(tree);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void addcondition(Object image ) {
		
		tree.setSelectedNode(condition(image));
		condition(tree.getImageForProcess());
	}
	public static TreeItem<TreeNode> condition(Object image) {
		tree.addChild(new TreeNode("Ýf",image,"dfgyhjýl"));
		 return tree.addChild(new TreeNode(),true);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
