package test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import arvasis.drawing.GraphicsIO;
import arvasis.tool.visualization.DataVisualizer;
import globals.Globals;
import javafx.scene.control.ColorPicker;

public class EngineTest {
	public static void main(String[] args) {
		BufferedImage img=null;
		try {
			img = GraphicsIO.readBufferedImage("C:\\Users\\Arvasis\\Desktop\\ITFusAlt22x.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Globals.runScript(img,
		 * "var builder = new Packages.java.lang.ProcessBuilder(\"gedit\"); \r\n" +
		 * "                    builder.start();" +
		 * "  var robot = new java.awt.Robot();\r\n" +
		 * "                    robot.delay(1000);" + "var res=barcode;\r\n" +
		 * "		                    if(!res.equals(\"\")){\r\n" +
		 * "		                        var keys=res.toUpperCase().toCharArray();\r\n"
		 * + "		                        for (var i = 0; i < keys.length; i++) {\r\n"
		 * + "		                            \r\n" +
		 * "		                            robot.keyPress(keys[i]);\r\n" +
		 * "		                            robot.keyRelease(keys[i]);\r\n" +
		 * "		                            robot.delay(40);\r\n" +
		 * "		                        }\r\n" +
		 * "		                        robot.keyPress(KeyEvent.VK_ENTER);\r\n" +
		 * "		                        robot.keyRelease(KeyEvent.VK_ENTER);\r\n" +
		 * "		                        \r\n" +
		 * "		                        java.lang.Thread.sleep(100);\r\n" +
		 * "		                    }");
		 */
		//System.out.println(Globals.engine.getScript());
	//	Globals.addObjectToEngine(img, "image");
		//System.out.println(Globals.engine.getScript());
		//Globals.runScript("import arvasis.drawing.GraphicsIO as GraphicsIO;");
		Globals.runScript("image=GraphicsIO.applyCanny(image);");
		DataVisualizer.showImage((BufferedImage)Globals.engine.getVar("image"));
	
	}

}
