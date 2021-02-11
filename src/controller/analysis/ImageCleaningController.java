package controller.analysis;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import arvasis.drawing.GraphicsIO;
import arvasis.drawing.objects.BoundaryCluster;
import arvasis.drawing.objects.PixelLocation;
import arvasis.sensor.studio.tree.TreeNode;
import globals.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;

public class ImageCleaningController {
	@FXML private Spinner<Integer> spSmaller;
	@FXML private Spinner<Integer> spLarger;
	private BufferedImage oldImage;
	private String process;
	private ArrayList<TreeNode> treeNodes=new ArrayList<TreeNode>();
	@FXML
	public void applyPixelSmaller() {
		int pixel = spSmaller.getValue();

		BufferedImage img = (BufferedImage) Globals.mainController.getImage();
		oldImage=img;
		boolean[][] map = GraphicsIO.convertImageToMap(img);

		BoundaryCluster[] cluster = GraphicsIO.detectBoundaries(map);
		for (BoundaryCluster c : cluster) {
			if (c.getPoints().length < pixel)
				for (PixelLocation p : c.getPoints())
					map[p.y][p.x] = false;
		}
		System.out.println("c " + cluster.length);
		Globals.mainController.setImage(GraphicsIO.convertMapToImage(map));
		//TODO create Tree node add to array
		
		process+="map = Packages.arvasis.drawing.GraphicsIO.convertImageToMap(image);\r\n"
				+ "cluster = Packages.arvasis.drawing.GraphicsIO.detectBoundaries(map);\r\n"
				+ "for (c in cluster) {\r\n"
				+ "		if (c.getPoints().length < pixel)\r\n"
				+ "			for (p in c.getPoints())\r\n"
				+ "				map[p.y][p.x] = false;\r\n"
				+ "		}\r\n";
		
		Globals.runScript(process);
		treeNodes.add(new TreeNode("Pixel Smaller Than: "+pixel,img,process));

		/// training kısmı
	}
	@FXML
	public void applyPixelLarger() {
		int pixel = (int) spLarger.getValue();

		BufferedImage img = (BufferedImage) Globals.tree.getImageForProcess();
		oldImage=img;
		boolean[][] map = GraphicsIO.convertImageToMap(img);

		BoundaryCluster[] cluster = GraphicsIO.detectBoundaries(map);
		for (BoundaryCluster c : cluster) {
			if (c.getPoints().length > pixel)
				for (PixelLocation p : c.getPoints())
					map[p.y][p.x] = false;
		}
		System.out.println("c " + cluster.length);
		Globals.mainController.setImage(GraphicsIO.convertMapToImage(map));
		
		process+="map = Packages.arvasis.drawing.GraphicsIO.convertImageToMap(image);\r\n"
				+ "cluster = Packages.arvasis.drawing.GraphicsIO.detectBoundaries(map);\r\n"
				+ "		for (c in cluster) {\r\n"
				+ "			if (c.getPoints().length > pixel)\r\n"
				+ "				for (p in c.getPoints())\r\n"
				+ "					map[p.y][p.x] = false;\r\n"
				+ "		}";
		treeNodes.add(new TreeNode("Pixel Larger Than: "+pixel,img,process));
	}
	@FXML
	public void selectLargestCluster() {
		int minSize = 0, size;
		BoundaryCluster object = null;

		BufferedImage img = (BufferedImage) Globals.tree.getImageForProcess();
		oldImage=img;

		Globals.mapParameter = GraphicsIO.convertImageToMap(img);

		BoundaryCluster[] cluster = GraphicsIO.detectBoundaries(Globals.mapParameter);
		for (BoundaryCluster c : cluster) {
			// System.out.println(obj[j].getPoints().size());
			size = c.getPoints().length;
			if (minSize < size) {
				if (object != null)
					for (PixelLocation pl : object.getPoints())
						Globals.mapParameter[pl.y][pl.x] = false;
				object = c;
				minSize = size;
			} else {
				for (PixelLocation pl : c.getPoints())
					Globals.mapParameter[pl.y][pl.x] = false;
			}
		}
		Globals.mainController.setImage(GraphicsIO.convertMapToImage(Globals.mapParameter));
		process+="mapParameter =Packages.arvasis.drawing.GraphicsIO.convertImageToMap(image);"
				+ "cluster = Packages.arvasis.drawing.GraphicsIO.detectBoundaries(mapParameter);\r\n"
				+ "		for ( c in cluster) {\r\n"
				+ "			// System.out.println(obj[j].getPoints().size());\r\n"
				+ "			size = c.getPoints().length;\r\n"
				+ "			if (minSize < size) {\r\n"
				+ "				if (object != null)\r\n"
				+ "					for ( pl in object.getPoints())\r\n"
				+ "						mapParameter[pl.y][pl.x] = false;\r\n"
				+ "				object = c;\r\n"
				+ "				minSize = size;\r\n"
				+ "			} else {\r\n"
				+ "				for ( pl in c.getPoints())\r\n"
				+ "					mapParameter[pl.y][pl.x] = false;\r\n"
				+ "			}\r\n"
				+ "		}";
		
		treeNodes.add(new TreeNode("Select Largerst Cluster",img,process));
	}
	@FXML
	public void selectSmallestCluster() {
		int minSize = 0, size;
		BoundaryCluster object = null;

		BufferedImage img = (BufferedImage) Globals.mainController.getImage();
		oldImage=img;

		Globals.mapParameter = GraphicsIO.convertImageToMap(img);

		BoundaryCluster[] cluster = GraphicsIO.detectBoundaries(Globals.mapParameter);
		for (BoundaryCluster c : cluster) {
			// System.out.println(obj[j].getPoints().size());
			size = c.getPoints().length;
			if (minSize > size) {
				if (object != null)
					for (PixelLocation pl : object.getPoints())
						Globals.mapParameter[pl.y][pl.x] = false;
				object = c;
				minSize = size;
			} else {
				for (PixelLocation pl : c.getPoints())
					Globals.mapParameter[pl.y][pl.x] = false;
			}
		}
		Globals.mainController.setImage(GraphicsIO.convertMapToImage(Globals.mapParameter));
		process+="mapParameter = Packages.arvasis.drawing.GraphicsIO.convertImageToMap(image);\r\n"
				+ "cluster = Packages.arvasis.drawing.GraphicsIO.detectBoundaries(mapParameter);\r\n"
				+ "		for (c in cluster) {\r\n"
				+ "			// System.out.println(obj[j].getPoints().size());\r\n"
				+ "			size = c.getPoints().length;\r\n"
				+ "			if (minSize > size) {\r\n"
				+ "				if (object != null)\r\n"
				+ "					for (pl in object.getPoints())\r\n"
				+ "						mapParameter[pl.y][pl.x] = false;\r\n"
				+ "				object = c;\r\n"
				+ "				minSize = size;\r\n"
				+ "			} else {\r\n"
				+ "				for (pl in c.getPoints())\r\n"
				+ "					mapParameter[pl.y][pl.x] = false;\r\n"
				+ "			}\r\n"
				+ "		}";
		treeNodes.add(new TreeNode("Select Smallest Cluster",img,process));
	}
	@FXML
	public void undoClearPixelSmaller() {
		Globals.mainController.setImage(oldImage);
	}
	@FXML
	public void undoClearPixelLarger() {
		Globals.mainController.setImage(oldImage);

	}
	@FXML
	public void undoSelectLargestCluster() {
		Globals.mainController.setImage(oldImage);

	}
	@FXML
	public void undoSelectSmallestCluster() {
		Globals.mainController.setImage(oldImage);

	}
	@FXML 
	public void apply() {
		
		for (TreeNode treeNode : treeNodes) {
			Globals.tree.addChild(treeNode);
		}
	}
}
