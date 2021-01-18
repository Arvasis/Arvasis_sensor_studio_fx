package globals;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.sun.jna.platform.win32.Winnetwk.RESOURCEDISPLAYTYPE;

import arvasis.camera.Camera;
import arvasis.drawing.objects.PixelLocation;
import arvasis.io.net.SocketManager;
import arvasis.script.ArvasisJavaScriptEngine;

import arvasis.tool.Label;
import controller.MainController;

public class Globals {

	public static String jsCode;
	public static Locale localeTR = new Locale("tr", "TR");
	public static Locale localeEN = new Locale("en", "EN");
	public final static String en = "en_EN";
	public final static String tr = "tr_TR";

	public static ResourceBundle rb;

	public static MainController mainController;
	
    public static ArrayList<Camera> arrCam = new ArrayList<>();
   // public static ArrayList<Object> arrImage = new ArrayList<>();
    public static LinkedList<Object> llImage =  new LinkedList<>();
    public  static LinkedList<Object> llProcessList = new LinkedList<>();
    public static Object processString;
    public static Camera cam;
	
	public static ArvasisJavaScriptEngine engine = new ArvasisJavaScriptEngine();

	public static BufferedImage image;
	

	public enum ImageType {
		BufferedImage, Integer, Boolean
	}

	// public static ArrayList<BufferedImage> arrTargetImage = new
	// ArrayList<BufferedImage>();
	// public static ArrayList<BufferedImage> arrSourceImage = new
	// ArrayList<BufferedImage>();
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

	public static TitledBorder getBorder(String titleName) {
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title = BorderFactory.createTitledBorder(loweredetched, titleName);
		title.setTitleFont(new Font(" Serif", Font.BOLD | Font.PLAIN, 13));
		title.setTitleColor(Color.black);
		return title;
	}

	public static void setLabelValidator(Label label, String text) {
		label.setText(text);
		ActionListener listener1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label.setText("");
			}
		};
		Timer timer1 = new Timer(3000, listener1);
		timer1.setRepeats(false);
		timer1.start();
	}

	public static void setDialogValidator(Component parentComp, String text) {
		JOptionPane.showMessageDialog(parentComp, text, "UyarÄ±", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void setDialogError(Component parentComp, String text) {
		JOptionPane.showMessageDialog(parentComp, text, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void setDialogValidatorTimer(Component parentComp, String text) {
		JOptionPane pane = new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(parentComp, "UyarÄ±");
		dialog.setModal(false);
		dialog.setVisible(true);

		new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		}).start();

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
			setDialogValidator(c, s);
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
			Globals.setDialogError(null, "Connection Failed.");
		}

	}

	public static BufferedImage applyAllFilters(Object image, String process) {

		engine.putVar("image", image);
		try {
			engine.runScript(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (BufferedImage) engine.getVar("image");
	}
	
	public static void refreshArrCam() {
	/*	Object[] o =Globals.arrCam.toArray();
		MenuPanel.data = new Object[o.length][1];
		for (int i = 0; i < o.length; i++) {
			//System.out.println("arr "+o[i]);
			
			MenuPanel.data[i][0]= o[i];
			MenuPanel.cbTakePhoto.setData(MenuPanel.data, MenuPanel.columnNames);*/
		}
		
	
}
