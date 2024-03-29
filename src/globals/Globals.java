package globals;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import arvasis.camera.Camera;
import arvasis.io.net.SocketManager;
import arvasis.script.ArvasisJavaScriptEngine;
import arvasis.sensor.studio.tree.ArvasisTree;
import controller.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import scriptEngine.ArvasisJavaScriptEngine2;

public class Globals {

	public static String jsCode;
	public static Locale localeTR = new Locale("tr", "TR");
	public static Locale localeEN = new Locale("en", "EN");
	public final static String en = "en_EN";
	public final static String tr = "tr_TR";

	public static ResourceBundle rb;

	public static MainController mainController;
	public static boolean[][] mapParameter;

	
    public static ArrayList<Camera> arrCam = new ArrayList<>();
   // public static ArrayList<Object> arrImage = new ArrayList<>();
    public static LinkedList<Object> llImage =  new LinkedList<>();
    public  static LinkedList<Object> llProcessList = new LinkedList<>();
    public static Object processString;
    public static Camera cam;
	
    public static ArvasisTree tree;
	public static ArvasisJavaScriptEngine engine = new ArvasisJavaScriptEngine();

	// public static boolean[][] mapParameter;
	 public static boolean[][] trainResultParameter;

	public static Object image;
	public static ImageType imageType=ImageType.BufferedImage;
	public static int forIndex=0; //Loop Controller
	public enum ImageType {
		BufferedImage, Integer, Boolean
	}
	
	public static void setResourceBundle(String lang) {

		if (lang.equals("TR")) {
			rb = ResourceBundle.getBundle(tr, localeTR);
		}
		if (lang.equals("EN")) {
			rb = ResourceBundle.getBundle(en, localeEN);
		}
	}

	public static void setLanguage(String lang) {
		setResourceBundle(lang);
	}

	public static ResourceBundle getLanguage() {
		return rb;
	}

	public static boolean getImageType(Object image, String type, Component c) {
		String val = "";
		val = image.getClass().getSimpleName();

		if (!type.equals("BufferedImage")) {
			val = image.getClass().getCanonicalName().split("\\[")[0];
			if (val.equals("int")) {
				val = "Integer";
			} else if (val.equals("boolean")) {
				val = "Boolean";
			}
		}

		System.out.println("image type val:" + val);
		String s = "Görüntü " + type + " türünde olmalıdır. Filtre uygulanamaz. �?uanki tür: " + val;
		System.out.println("val-type:" + val + " - " + type);
		if (!val.equals(type)) {
			//setDialogValidator(c, s);
			return false;
		} else
			return true;
	}

	public static void sendData(String ip, int port, byte[] data) {
		sendData(ip, port, data, false);
	}

	private static void sendData(final String ip, final int port, final byte[] data, final boolean hasResponse) {
		System.out.println("sending data...");
		final SocketManager sm = new SocketManager(ip, port, false);
		sm.setAddSizeInfo(true);
		try {
			sm.sendData(data, hasResponse);
			sm.closeConnection();
		} catch (Exception e) {
			//Globals.setDialogError(null, "Connection Failed.");
		}

	}
	public static Object copyObject(Object obj) {
		Object image = null;
		if (obj instanceof BufferedImage) {
			BufferedImage img = (BufferedImage) obj;
			BufferedImage saveImage = copyImage(img);
			image = saveImage;
		}

		if (obj instanceof int[][]) {
			int[][] intImage = (int[][]) obj;
			int[][] saveIntImage = Arrays.copyOf(intImage, intImage.length);
			image = saveIntImage;
		}
		if (obj instanceof boolean[][]) {
			boolean[][] mapImage = (boolean[][]) obj;
			boolean[][] saveMapImage = Arrays.copyOf(mapImage, mapImage.length);
			image = saveMapImage;
		}
		return image;
	}
	public static BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}
	public static Object runScript(Object obj,String process) {
		engine.putVar("image", obj);
		try {
			engine.runScript(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Globals.copyObject(engine.getVar("image"));
	}
	public static Object runScript(Object obj,String varName,String process) {
		engine.putVar(varName, obj);
		try {
			engine.runScript(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return engine.getVar(varName);
	}
	
	public static Object runScript(String process) {
		try {
			engine.runScript(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return engine.getVar("image");
	}
	public static void addObjectToEngine(Object obj,String name) {
		engine.putVar(name, obj);
	}
	public static Object applyAllFilters(Object obj, String process) {
		return runScript(obj,process);
	}
	
	public static void refreshArrCam() {
	/*	Object[] o =Globals.arrCam.toArray();
		MenuPanel.data = new Object[o.length][1];
		for (int i = 0; i < o.length; i++) {
			//System.out.println("arr "+o[i]);
			
			MenuPanel.data[i][0]= o[i];
			MenuPanel.cbTakePhoto.setData(MenuPanel.data, MenuPanel.columnNames);*/
		}
	
	public static void setAlertInformation(String content) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(content);
		alert.showAndWait();

	}
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
		
	
}
