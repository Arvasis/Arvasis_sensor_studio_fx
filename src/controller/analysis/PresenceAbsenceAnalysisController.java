package controller.analysis;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import arvasis.data.XmlDataTable;
import arvasis.data.parser.Parser;
import arvasis.drawing.GraphicsIO;
import arvasis.drawing.objects.PixelLocation;
import globals.Globals;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PresenceAbsenceAnalysisController {
	boolean source = false, target = false;
	private int beginX, beginY, endX, endY;

	int realX, realY, realW, realH;
	private BufferedImage imagePart;
	private BufferedImage sourceImg;

	private int w; // en
	private int h; // boy
	Color thresholdValue;
	int referanceThreshold;
	int maxIncorrectPoints;
	String path = "files/sourceImage.png";

	@FXML private TextField txtMaxWrongPointNumber;
	@FXML private TextField txtReferenceThresholdValue;
	
	private MouseListener ml = new MouseAdapter() {
		BufferedImage i = (BufferedImage) Globals.mainController.getImage();

		@Override
		public void mousePressed(MouseEvent e) {
			setStartPoint(e.getX(), e.getY());
//			ArvasisSensorStudio.ip.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// System.out.println("rel");
			setEndPoint(e.getX(), e.getY());
//			ArvasisSensorStudio.ip.setImageCenterPanel(draw(i, beginX, beginY, endX, endY));
		}

	};
	private MouseMotionListener mml = new MouseMotionListener() {
		BufferedImage i = (BufferedImage) Globals.mainController.getImage();

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			// System.out.println("drag");
			setEndPoint(e.getX(), e.getY());
			Globals.mainController.setImage(draw(i, beginX, beginY, endX, endY));
//			ArvasisSensorStudio.ip.setImageCenterPanel(draw(i, beginX, beginY, endX, endY));
		}
	};
	
	@FXML
	public void selectSourceObject() {
		source = true;
		//setCursor(new Cursor(Cursor.HAND_CURSOR));
//		ArvasisSensorStudio.ip.addMouseListener(ml);
//		ArvasisSensorStudio.ip.addMouseMotionListener(mml);
	}
	@FXML
	public void confirmSelectedSorurceObject() {
		if (source == true) {
		//	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			BufferedImage originalImg = (BufferedImage) Globals.mainController.getImage();

			double ratioW = (double) originalImg.getWidth() / (double) 550;
			double ratioH = (double) originalImg.getHeight() / (double) 275;

			// System.out.println(beginX + " " + beginY + " " + w + " " + h);

			if (originalImg.getWidth() > originalImg.getHeight()) {
				realX = (int) Math.floor(beginX * ratioW);
				realY = (int) Math.floor(beginY * ratioW);
				realW = (int) Math.round(w * ratioW);
				realH = (int) Math.round(h * ratioW);

			} else {
				realX = (int) Math.floor(beginX * ratioH);
				realY = (int) Math.floor(beginY * ratioH);
				realW = (int) Math.round(w * ratioH);
				realH = (int) Math.round(h * ratioH);
			}
			imagePart = GraphicsIO.getPartOfImage(originalImg, realX, realY, realW, realH);

			if (source == true) {
//				initReview();

			}
			Globals.mainController.setImage(Globals.image);
//			ArvasisSensorStudio.ip.removeMouseListener(ml);
//			ArvasisSensorStudio.ip.removeMouseMotionListener(mml);

		} else {
			new Alert(AlertType.ERROR,"'Select Source Object' butonuna tıklamadan parça seçimi aktif olmamaktadır.").show();
			/*Globals.setDialogValidator(jd,
					"'Select Source Object' butonuna tıklamadan parça seçimi aktif olmamaktadır.");*/
		}
	}
	@FXML
	public void compare() {
		if (txtReferenceThresholdValue.getText().equals("")) {
			//Globals.setDialogValidator(jd, "Referans Threshold Alanı Boş Geçilemez.");
			new Alert(AlertType.WARNING,"Referans Threshold Alanı Boş Geçilemez.").show();
		}
		else {
		BufferedImage img = (BufferedImage) Globals.mainController.getImage();
		Parser p = new Parser();
		try {
			XmlDataTable dt = p.parseFromFile("files/source.xml");
			String path1 = dt.getDataAt(0, 0);
			int xData = Integer.valueOf(dt.getDataAt(1, 0));
			int yData = Integer.valueOf(dt.getDataAt(2, 0));
			int wData = Integer.valueOf(dt.getDataAt(3, 0));
			int hData = Integer.valueOf(dt.getDataAt(4, 0));
			String point = dt.getDataAt(5, 0);
			String[] pointArr = point.split(";");
			PixelLocation[] sourcePx = new PixelLocation[pointArr.length];
			for (int i = 0; i < pointArr.length; i++) {
				String[] s = pointArr[i].split(",");
				int x = Integer.valueOf(s[0]);
				int y = Integer.valueOf(s[1]);
				sourcePx[i] = new PixelLocation(x, y);
			}

			sourceImg = GraphicsIO.readBufferedImage(path);
			BufferedImage targetImg = GraphicsIO.getPartOfImage(img, xData, yData, wData, hData);
			GraphicsIO.saveImage(targetImg, "png", new File("files/targetImage.png"));
			
			int width = targetImg.getWidth(), height = targetImg.getHeight();
			PixelLocation[] targetPx = new PixelLocation[width * height];
			referanceThreshold = Integer.valueOf(txtReferenceThresholdValue.getText());
			maxIncorrectPoints = Integer.valueOf(txtMaxWrongPointNumber.getText());
			int cursor =0;
				for (int i = 0; i < targetPx.length && i<sourcePx.length; i++) {
					
					int r1 = GraphicsIO.getRed(sourceImg.getRGB(sourcePx[i].x, sourcePx[i].y));
					int g1 = GraphicsIO.getGreen(sourceImg.getRGB(sourcePx[i].x, sourcePx[i].y));
					int b1 = GraphicsIO.getBlue(sourceImg.getRGB(sourcePx[i].x, sourcePx[i].y));
					
					int r2 = GraphicsIO.getRed(targetImg.getRGB(sourcePx[i].x, sourcePx[i].y));
					int g2 = GraphicsIO.getGreen(targetImg.getRGB(sourcePx[i].x, sourcePx[i].y));
					int b2 = GraphicsIO.getBlue(targetImg.getRGB(sourcePx[i].x, sourcePx[i].y));
					
					int r = Math.abs(r2-r1);
					int g = Math.abs(g2-g1);
					int b = Math.abs(b2-b1);
					boolean is = Math.max(Math.max(r, g), b)>=referanceThreshold;
					if(is == false) {
						cursor++;
					}
				}
				
				if(maxIncorrectPoints< cursor) {
					//Globals.setDialogValidator(PresenceAbsenceFrame.this, "Eşleşme Sağlandı");
					new Alert(AlertType.CONFIRMATION,"Eşleşme Sağlandı");
				}
				
				else {
					new Alert(AlertType.CONFIRMATION, "Max Hatal� nokta say�s� aşıldı.");
					//Globals.setDialogValidator(PresenceAbsenceFrame.this, "Max Hatalı nokta sayısı aşıldı.");
				}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}

	public void setStartPoint(int x, int y) {
		this.beginX = x;
		this.beginY = y;
	}

	public void setEndPoint(int x, int y) {
		endX = (x);
		endY = (y);
	}
	public BufferedImage draw(BufferedImage image, int x, int y, int x2, int y2) {
		int px = Math.min(x, x2);
		int py = Math.min(y, y2);
		w = Math.abs(x - x2);
		h = Math.abs(y - y2);

		int[][] intImage = GraphicsIO.convertToIntegerArray(image);
		int[][] i = GraphicsIO.drawRectangle(intImage, Color.red.getRGB(), px, py, w, h);
		return GraphicsIO.convertArrayToImage(i);

	}
}
