package test;

import java.awt.image.BufferedImage;

import arvasis.drawing.GraphicsIO;
import arvasis.sensor.studio.tree.ArvasisTree;
import arvasis.sensor.studio.tree.TreeNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TreeViewTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		  
       /* TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        TreeView<String> tree = new TreeView<String> (rootItem);    
       TreeItem<String> node= tree.getRoot().getChildren().get(0);
       System.out.println(node.getValue());
*/
		TreeNode root=new TreeNode("Deneme");
		ArvasisTree tree=new ArvasisTree(root);
		BufferedImage buff=GraphicsIO.readBufferedImage("C:\\Users\\Arvasis\\Documents\\KT\\img\\642969956309746.jpg");
      /* TreeItem<String> rootItem = new TreeItem<String> ("Inbox");*/
       tree.getRoot().setExpanded(true);
       for (int i = 1; i < 6; i++) {
           tree.addChild(new TreeNode("Message" + i,buff));
       }        
    
        StackPane pane = new StackPane();
        pane.getChildren().add(tree);
        primaryStage.setScene(new Scene(pane, 300, 250));
        primaryStage.show();		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
