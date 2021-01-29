package arvasis.ocr;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.List;

import com.sun.jna.Pointer;

import arvasis.drawing.GraphicsIO;
import arvasis.drawing.objects.BoundaryCluster;
import net.sourceforge.tess4j.ITessAPI.TessBaseAPI;
import net.sourceforge.tess4j.ITessAPI.TessPageIterator;
import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITessAPI.TessResultIterator;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TessAPI1;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

public class ArvasisOCR {

	public String getText(BufferedImage image, int x, int y, int width, int height) {
		String result = null;
		try {
			BufferedImage partedImg = GraphicsIO.getPartOfImage(image, new BoundaryCluster(x, y, width, height));
			GraphicsIO.saveImage(partedImg, "jpg", "/home/arvasis/Masaüstü/ocr.jpg");
			try {
				result = doOCR("/home/arvasis/Masaüstü/ocr.jpg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getText(String imageFilePath) {

		String result = null;
		try {
			result = doOCR(imageFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static String doOCRWithCoordinates(BufferedImage img) throws Exception {
		ITesseract instance = new Tesseract();
		//instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
		instance.setDatapath("C:\\OCR");
		instance.setLanguage("tur");
		String res1 = instance.doOCR(img);
		System.out.println("res1 "+res1);
		String res="";
		System.out.println(img.getWidth());
		for(Word word : instance.getWords(img, ITessAPI.TessPageIteratorLevel.RIL_WORD)) {
			  Rectangle rect = word.getBoundingBox();

			 res+= word.getText()+"\t"+ "X\t"+rect.x+"\tY\t"+rect.y+"\tW\t"+rect.width+
						"\tH\t"+rect.height+"\t";
			 res+="\n";
			}
		System.out.println(res);
		return res;
	}
	
	public static String doOCR(BufferedImage img) throws Exception {
		ITesseract instance = new Tesseract();
		//instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
		instance.setDatapath("C:\\OCR");

		instance.setLanguage("tur");
		String res = instance.doOCR(img);

		return res;
	}
	
	public static  List<Word> getWord(BufferedImage bi) {
		List<Word> words = null;
		try {
			//File imageFile = new File(path);
			//BufferedImage bi = GraphicsIO.readBufferedImage(path);
			ITesseract instance = new Tesseract();
			//instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
			instance.setDatapath("C:\\OCR");

			
			instance.setLanguage("tur");
			String a = instance.doOCR(bi);
			System.out.println(a);
			words =instance.getWords(bi, ITessAPI.TessPageIteratorLevel.RIL_WORD);
			System.out.println(words.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return words;
	}
	
	public static Rectangle[] getRect(List<Word> words ) {
		Rectangle[] rects = new Rectangle[words.size()];
		for (int i = 0; i < words.size(); i++) {
			 rects[i] = words.get(i).getBoundingBox();
			 
		}
		return rects;
		
	}
	public  static String doOCR(String path) throws Exception {
		File imageFile = new File(path);
		BufferedImage bi = GraphicsIO.readBufferedImage(path);
		ITesseract instance = new Tesseract();
		//instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
		instance.setDatapath("C:\\OCR");
		instance.setLanguage("tur");
		String result = instance.doOCR(imageFile);
		
		String res="";
		for(Word word : instance.getWords(bi, ITessAPI.TessPageIteratorLevel.RIL_WORD)) {
			  Rectangle rect = word.getBoundingBox();

			 res+= word.getText() +"\t"+ "X\t"+rect.x+"\tY\t"+rect.y+"\tW\t"+rect.width+
						"\tH\t"+rect.height+"\t";
			}
		System.out.println(res);
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		
	//	BufferedImage bi = GraphicsIO.readBufferedImage("/home/arvasis/Masaüstü/alfabe.jpg");
		//ArvasisOCR.getWords(bi);
		BufferedImage bi = GraphicsIO.readBufferedImage("C:\\Users\\Arvasis\\Documents\\KT\\image1525237902023699.jpg");
		ArvasisOCR.doOCR(bi);
	}

}
