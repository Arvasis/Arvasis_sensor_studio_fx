package test;

import java.awt.image.BufferedImage;

import arvasis.drawing.GraphicsIO;
import arvasis.sensor.studio.tree.ArvasisTree;
import arvasis.sensor.studio.tree.TreeNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class TreviewTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*TreeView<String> tree=new TreeView<String>();
		tree.setRoot(new TreeItem<String>("deneme"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));
		tree.getRoot().getChildren().add(new TreeItem<String>("aaaa"));

		Scene scene=new Scene(tree,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();*/
		BufferedImage buff=GraphicsIO.readBufferedImage("C:\\Users\\Arvasis\\Documents\\KT\\img\\642969956309746.jpg");
		TreeNode treeNode=new TreeNode("Denem node",buff);
		Scene scene=new Scene(new ArvasisTree(treeNode),400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
