package controller.analysis;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import arvasis.drawing.GraphicsIO;
import arvasis.ocr.ArvasisOCR;
import arvasis.script.ArvasisJavaScriptEngine;
import globals.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class ReadController {

	@FXML
	private RadioButton rbUseFile;
	@FXML
	private RadioButton rbUseRobot;
	@FXML
	private Button btnOpenFile;
	@FXML
	private TextArea txtArea;
	private ReadType type = ReadType.Barcode;

	public enum ReadType {
		Barcode, Text
	}

	public void setType(ReadType type) {
		this.type = type;
	}

	@FXML
	public void openFile() {
		String path = "";
		if (type == ReadType.Barcode) {
			path = "barcode.txt";
		} else
			path = "text.txt";
		try {
			if (!btnOpenFile.isDisabled()) {
				Desktop.getDesktop().open(new File(path));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void read() {
		BufferedImage img = (BufferedImage) Globals.mainController.getImage();

		if (img != null) {
			String result = "";
			String process = "";
			if (type == ReadType.Barcode) {
				process = "result =Packages.arvasis.drawing.GraphicsIO.readBarcode(image);";
			} else {
				process = "try {\r\n" + "result = Packages.arvasis.ocr.ArvasisOCR.doOCR(image);\r\n" + "} catch (e2) {\r\n"
						+ "	e2.printStackTrace();\r\n" + "}";
			}

			if (rbUseRobot.isSelected()) {
				btnOpenFile.setDisable(true);
				process+=readRobot();
			} else if (rbUseFile.isSelected()) {
				btnOpenFile.setDisable(false);
				if (type == ReadType.Barcode) {
					process += readFile("barcode.txt");
				} else
					process += readFile("text.txt");
			}
			// Globals.runScript(img,"image",process);
			
			Globals.runScript(img, "image", process);
			result = (String) Globals.engine.getVar("result");
			txtArea.setVisible(true);
			txtArea.setText(result);

			// TODO add to tree
			// Globals.tree.addChild(new TreeNode("Read Barcode:"+barcode,process));
		} else {
			// TODO add to tree
			// Globals.setDialogValidator(ReadBarcodeFrame.this, "Görüntü Yüklenmedi.");
		}
	}

	public String readFile(String filename) {
		String process = "try{\n" + "file = new Packages.java.io.File(\"" + filename + "\");\n"
				+ "Packages.controller.analysis.ReadController.fileClear(file);\n"
				+ "fw = new Packages.java.io.FileWriter(file,true);\n"
				+ "buf = new Packages.java.io.BufferedWriter(fw);\n" + "buf.write(result);\n" + "buf.close();\n"
				+ "}\n" + "catch (e1){\n" + "e1.printStackTrace();}";
		return process;
	}

	public String readRobot() {
		String process = "var builder = new Packages.java.lang.ProcessBuilder(\"gedit\"); \r\n" + "builder.start();"
				+ "var robot = new java.awt.Robot();\r\n" + "robot.delay(1000);" + "var res=result;\r\n"
				+ "if(!res.equals(\"\")){\r\n" + "var keys=res.toUpperCase().toCharArray();\r\n"
				+ "for (var i = 0; i < keys.length; i++) {\r\n" + "	robot.keyPress(keys[i]);\r\n"
				+ "robot.keyRelease(keys[i]);\r\n" + "robot.delay(40);\r\n" + "}\r\n"
				+ "robot.keyPress(KeyEvent.VK_ENTER);\r\n" + "robot.keyRelease(KeyEvent.VK_ENTER);\r\n"
				+ "java.lang.Thread.sleep(100);\r\n" + "}";
		return process;
	}

	public static void fileClear(File file) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.print("");
		writer.close();
	}
}
