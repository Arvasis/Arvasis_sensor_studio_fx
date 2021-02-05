package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import arvasis.drawing.GraphicsIO;
import controller.identification.FiltersFrameController;
import controller.identification.ShapeClassificationController;
import globals.Globals;
import interfacebuilder.FiltersFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IdentificationController {
	FiltersFrame frame;
	public FiltersFrameController filtersFrameController;
	public ShapeClassificationController shapeClassificationController;

	public ShapeClassificationController getShapeClassificationController() {
		return shapeClassificationController;
	}

	public void setShapeClassificationController(ShapeClassificationController shapeClassificationController) {
		this.shapeClassificationController = shapeClassificationController;
	}

	public FiltersFrameController getFiltersFrameController() {
		return filtersFrameController;
	}

	public void setFiltersFrameController(FiltersFrameController filtersFrameController) {
		this.filtersFrameController = filtersFrameController;
	}

	public FilterType filterType;

	public enum FilterType {
		FixedColorExtraction, ThresholdForMap, BlueThresholdForMap, GreenThresholdForMap, RedThresholdForMap

	}

	public void showFilter(Parent content, String title) {
		frame = new FiltersFrame(title) {

			@Override
			public void btnApplyAddMouseListener() {
				// TODO Auto-generated method stub
				btnApply.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						// GridPane pane = (GridPane) content;

						if (filterType.equals(FilterType.FixedColorExtraction)) {

							if (Globals.mainController.getImage() != null) {
								if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
										.equals("0x00000000")
										&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
												.equals("0x00000000")
										&& filtersFrameController.cpBackgroundColor.getValue().toString()
												.equals("0x00000000")
										&& filtersFrameController.cbInverse.isSelected() == false
										&& filtersFrameController.isChangedRgbDiff == false) {

									int lowerRGB = GraphicsIO.getRGB(
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());

									int upperRGB = GraphicsIO.getRGB(
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());

									boolean[][] map = GraphicsIO.applyThresholdForMap(
											(BufferedImage) Globals.mainController.getImage(), lowerRGB, upperRGB);
									boolean[][] b = GraphicsIO.not(map);
									// DataVisualizer.showImageInNewFrame(map);
									Globals.processString = "image  = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image, "
											+ lowerRGB + ", " + upperRGB + ");"
											+ "image =  Packages.arvasis.drawing.GraphicsIO.not(image)";

									Globals.runScript(content, (String) Globals.processString);
									// Globals.engine.getVar("image");

								} else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
										.equals("0x00000000")
										&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
												.equals("0x00000000")
										&& !filtersFrameController.cpBackgroundColor.getValue().toString()
												.equals("0x00000000")
										&& filtersFrameController.isChangedRgbDiff == false) {

									int lowerRGB = GraphicsIO.getRGB(
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());

									int upperRGB = GraphicsIO.getRGB(
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());

									int backgroundColor = GraphicsIO.getRGB(
											(int) filtersFrameController.cpBackgroundColor.getValue().getRed(),
											(int) filtersFrameController.cpBackgroundColor.getValue().getGreen(),
											(int) filtersFrameController.cpBackgroundColor.getValue().getBlue());

									boolean inverse = filtersFrameController.cbInverse.isSelected();
									int[][] intImage = GraphicsIO
											.convertToIntegerArray((BufferedImage) Globals.mainController.getImage());
									boolean[][] img = GraphicsIO.applyThresholdForMap(intImage, lowerRGB, upperRGB,
											backgroundColor, inverse);
									boolean[][] b = GraphicsIO.not(img);

									Globals.processString = "image = Packages.arvasis.drawing.GraphicsIO.convertToIntegerArray(image);"
											+ "image  = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image, "
											+ lowerRGB + ", " + upperRGB + "," + backgroundColor + "," + inverse + ");"
											+ "image =  Packages.arvasis.drawing.GraphicsIO.not(image)";

								} else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
										.equals("0x00000000")
										&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
												.equals("0x00000000")
										&& filtersFrameController.cpBackgroundColor.getValue().toString().equals(
												"0x00000000")
										&& filtersFrameController.isChangedRgbDiff == true) {

									int lowerRGB = GraphicsIO.getRGB(
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());

									int upperRGB = GraphicsIO.getRGB(
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());

									int rgbDiff = (int) filtersFrameController.spRGBDiff.getValue();
									boolean inverse = filtersFrameController.cbInverse.isSelected();

									int[][] intImage = GraphicsIO
											.convertToIntegerArray((BufferedImage) Globals.mainController.getImage());
									boolean[][] img = GraphicsIO.applyThresholdForMap(intImage,
											filtersFrameController.pixel, lowerRGB, upperRGB, rgbDiff, inverse);
									boolean[][] b = GraphicsIO.not(img);

									Globals.processString = "image = Packages.arvasis.drawing.GraphicsIO.convertToIntegerArray(image);"
											+ "image  = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image, "
											+ filtersFrameController.pixel + "," + lowerRGB + ", " + upperRGB + ","
											+ rgbDiff + "," + inverse + ");"
											+ "image =  Packages.arvasis.drawing.GraphicsIO.not(image)";
								} else {

									Globals.setAlertInformation(
											"{Lower Threshold RGB - Upper Threshold RGB - Background Color - Inverse}\n"
													+ "{Points - Lower Threshold RGB - Upper Threshold RGB-Rgb Diff - Is Inverse}");
								}

							}
							if (Globals.mainController.getImage() == null) {

								Globals.setAlertInformation("Görüntü Yüklenmedi");

							}

						}
						if (filterType.equals(FilterType.ThresholdForMap)) {
							if (Globals.mainController.getImage() != null) {
								boolean val = Globals.getImageType(Globals.mainController.getImage(),
										Globals.ImageType.BufferedImage.toString(), null);
								if (val == true) {
									if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cpBackgroundColor.getValue().toString().equals(
													"0x00000000")
											&& filtersFrameController.isChangedRgbDiff == true) {

										int lowerRGB = GraphicsIO.getRGB(
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());

										int upperRGB = GraphicsIO.getRGB(
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());

										boolean[][] map = GraphicsIO.applyThresholdForMap(
												(BufferedImage) Globals.mainController.getImage(), lowerRGB, upperRGB);

										Globals.processString = "map = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image,"
												+ lowerRGB + "," + upperRGB + ")";

									} else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.isChangedRgbDiff == false
											&& filtersFrameController.lowerThreshold.length != 0
											&& filtersFrameController.upperThreshold.length != 0) {

										boolean[][] map = GraphicsIO.applyThresholdForMap(
												(BufferedImage) Globals.mainController.getImage(),
												filtersFrameController.lowerThreshold,
												filtersFrameController.upperThreshold);

										Globals.processString = "map = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image,"
												+ filtersFrameController.lowerThreshold + ","
												+ filtersFrameController.upperThreshold + ")";
									}

									else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& !filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cbInverse.isSelected() == true
											&& filtersFrameController.isChangedRgbDiff == false) {

										int lowerRGB = GraphicsIO.getRGB(
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());

										int upperRGB = GraphicsIO.getRGB(
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());

										int backgroundColor = GraphicsIO.getRGB(
												(int) filtersFrameController.cpBackgroundColor.getValue().getRed(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getGreen(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getBlue());

										boolean inverse = filtersFrameController.cbInverse.isSelected();

										int[][] i = GraphicsIO.convertToIntegerArray(
												(BufferedImage) Globals.mainController.getImage());
										boolean[][] map = GraphicsIO.applyThresholdForMap(i, lowerRGB, upperRGB,
												backgroundColor, inverse);

										Globals.processString = "image = Packages.arvasis.drawing.GraphicsIO.convertToIntegerArray(image);"
												+ "image = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image,"
												+ lowerRGB + "," + upperRGB + "," + backgroundColor + "," + inverse
												+ ")";

									} else if (filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& !filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cbInverse.isSelected() == true
											&& filtersFrameController.isChangedRgbDiff == false
											&& filtersFrameController.lowerThreshold.length != 0
											&& filtersFrameController.upperThreshold.length != 0) {

										int backgroundColor = GraphicsIO.getRGB(
												(int) filtersFrameController.cpBackgroundColor.getValue().getRed(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getGreen(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getBlue());

										boolean inverse = filtersFrameController.cbInverse.isSelected();

										int[][] i = GraphicsIO.convertToIntegerArray(
												(BufferedImage) Globals.mainController.getImage());
										boolean[][] map = GraphicsIO.applyThresholdForMap(i,
												filtersFrameController.lowerThreshold,
												filtersFrameController.upperThreshold, backgroundColor, inverse);

										Globals.processString = "image = Packages.arvasis.drawing.GraphicsIO.convertToIntegerArray(image);"
												+ "image = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image,"
												+ filtersFrameController.lowerThreshold + ","
												+ filtersFrameController.upperThreshold + "," + backgroundColor + ","
												+ inverse + ")";

									} else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cbInverse.isSelected() == true
											&& filtersFrameController.isChangedRgbDiff == false
											&& filtersFrameController.pixel.length != 0) {

										int lowerRGB = GraphicsIO.getRGB(
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());

										int upperRGB = GraphicsIO.getRGB(
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());

										int rgbDiff = (int) filtersFrameController.spRGBDiff.getValue();
										boolean inverse = filtersFrameController.cbInverse.isSelected();

										int[][] i = GraphicsIO.convertToIntegerArray(
												(BufferedImage) Globals.mainController.getImage());
										boolean[][] map = GraphicsIO.applyThresholdForMap(i,
												filtersFrameController.pixel, lowerRGB, upperRGB, rgbDiff, inverse);
										
										Globals.processString = "image = Packages.arvasis.drawing.GraphicsIO.convertToIntegerArray(image);"
												+ "image = Packages.arvasis.drawing.GraphicsIO.applyThresholdForMap(image,"
												+ filtersFrameController.pixel + "," + lowerRGB + "," + upperRGB + ","
												+ rgbDiff + "," + inverse + ")";

									} else {

										Globals.setAlertInformation("Uygun Parametreler:\n"
												+ "{Lower Threshold RGB - Upper Threshold RGB}\n"
												+ "{Lower Threshold RGB - Upper Threshold RGB - Background Color - Inverse}\n"
												+ "{Points - Lower Threshold RGB - Upper Threshold RGB-Rgb Diff - Is Inverse}");
									}

								}
							}

						}

						if (filterType.equals(FilterType.BlueThresholdForMap)) {
							if (Globals.mainController.getImage() != null) {

								boolean val = Globals.getImageType(Globals.mainController.getImage(),
										Globals.ImageType.Integer.toString(), null);

								if (val == true) {

									if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& !filtersFrameController.cpBrigtness.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.isChangedRgbDiff == true
											&& !filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")) {
										int lowerBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());
										int upperBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());
										int brightnessThreshold = GraphicsIO.getRGB(
												(int) filtersFrameController.cpBrigtness.getValue().getRed(),
												(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
												(int) filtersFrameController.cpBrigtness.getValue().getBlue());
										int diffThreshold = (int) filtersFrameController.spRGBDiff.getValue();
										int backgroundColor =GraphicsIO.getRGB(
												(int) filtersFrameController.cpBackgroundColor.getValue().getRed(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getGreen(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getBlue());
										boolean isInverse = filtersFrameController.cbInverse.isSelected();

										boolean[][] map = GraphicsIO.applyBlueThresholdForMap((int[][]) Globals.mainController.getImage(),
												lowerBound, upperBound, brightnessThreshold, diffThreshold,
												backgroundColor, isInverse);
										Globals.processString = "map=Packages.arvasis.drawing.GraphicsIO.applyBlueThresholdForMap(image,"+lowerBound+","+ upperBound+","+brightnessThreshold+","+ diffThreshold+"," +backgroundColor+","+ isInverse+")";
										// Globals.arrImage.add(GraphicsIO.convertMapToImage(bImage));
										

									} else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
													.equals("0x00000000")
											&& !filtersFrameController.cpBrigtness.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.isChangedRgbDiff == true
											&& filtersFrameController.cpBackgroundColor.getValue().toString()
											.equals("0x00000000")) {
										int lowerBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());
										int upperBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());
										int brightnessThreshold = GraphicsIO.getRGB(
												(int) filtersFrameController.cpBrigtness.getValue().getRed(),
												(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
												(int) filtersFrameController.cpBrigtness.getValue().getBlue());
										int diffThreshold = (int) filtersFrameController.spRGBDiff.getValue();
										

										boolean[][] map = GraphicsIO.applyBlueThresholdForMap((int[][]) Globals.mainController.getImage(),
												lowerBound, upperBound, brightnessThreshold, diffThreshold);
										Globals.processString = "map=Packages.arvasis.drawing.GraphicsIO.applyBlueThresholdForMap(image, "+lowerBound+","+upperBound+","+brightnessThreshold+","+diffThreshold+")";

										
										
									} else {
										Globals.setAlertInformation("Uygun Parametreler:\n"
														+ "{LowerBound-UpperBound-Brightness Threshold-Diff Threshold-Background Color-Inverse}\n"
														+ "{LowerBound-UpperBound-Brightness Threshold-Diff Threshold}");
									}
								}
							} else {
								Globals.setAlertInformation("Görüntü Yüklenemedi.");
							}

						}
						if (filterType.equals(FilterType.GreenThresholdForMap)) {

							if (Globals.mainController.getImage() != null) {

								boolean val = Globals.getImageType(Globals.mainController.getImage(), Globals.ImageType.Integer.toString(),
										null);

								if (val == true) {
								
								//int[][] image = GraphicsIO.convertToIntegerArray(i);

								if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
										.equals("0x00000000")
										&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
												.equals("0x00000000")
										&& !filtersFrameController.cpBrigtness.getValue().toString()
												.equals("0x00000000")
										&& filtersFrameController.isChangedRgbDiff == true
										&& !filtersFrameController.cpBackgroundColor.getValue().toString()
										.equals("0x00000000")) {

									int lowerBound = GraphicsIO.getRGB(
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());
									int upperBound = GraphicsIO.getRGB(
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());
									int brightnessThreshold = GraphicsIO.getRGB(
											(int) filtersFrameController.cpBrigtness.getValue().getRed(),
											(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
											(int) filtersFrameController.cpBrigtness.getValue().getBlue());
									int diffThreshold = (int) filtersFrameController.spRGBDiff.getValue();
									int background =  GraphicsIO.getRGB(
											(int) filtersFrameController.cpBackgroundColor.getValue().getRed(),
											(int) filtersFrameController.cpBackgroundColor.getValue().getGreen(),
											(int) filtersFrameController.cpBackgroundColor.getValue().getBlue());
									boolean isInverse = filtersFrameController.cbInverse.isSelected();

									boolean[][] map = GraphicsIO.applyGreenThresholdForMap((int[][])Globals.mainController.getImage(), lowerBound, upperBound,
											brightnessThreshold, diffThreshold, background, isInverse);
									Globals.processString = " map = Packages.arvasis.drawing.GraphicsIO.applyGreenThresholdForMap(image, "+lowerBound+","+ upperBound+","+brightnessThreshold+","+diffThreshold+","+ background+","+isInverse+")";
							
								} else if (!filtersFrameController.cpLowerThresholdRGB.getValue().toString()
										.equals("0x00000000")
										&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
												.equals("0x00000000")
										&& !filtersFrameController.cpBrigtness.getValue().toString()
												.equals("0x00000000")
										&& filtersFrameController.isChangedRgbDiff == true
										&& filtersFrameController.cpBackgroundColor.getValue().toString()
										.equals("0x00000000")) {
									int lowerBound = GraphicsIO.getRGB(
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());
									int upperBound = GraphicsIO.getRGB(
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
											(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());
									int brightnessThreshold = GraphicsIO.getRGB(
											(int) filtersFrameController.cpBrigtness.getValue().getRed(),
											(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
											(int) filtersFrameController.cpBrigtness.getValue().getBlue());
									int diffThreshold = (int) filtersFrameController.spRGBDiff.getValue();
									
									boolean[][] map = GraphicsIO.applyGreenThresholdForMap((int[][]) Globals.mainController.getImage(), lowerBound, upperBound,
											brightnessThreshold, diffThreshold);
									Globals.processString = " map = GraphicsIO.applyGreenThresholdForMap(image, "+lowerBound+","+ upperBound+","+brightnessThreshold+","+diffThreshold+")";
									
								} else {

									Globals.setAlertInformation("Uygun Parametreler:\n"
											+ "{Lower Bound - Upper Bound - Brightness Threshold - Diff Threshold - Background Color - Inverse}\n"
											+ "{LowerBound - Upper Bound - Brightness Threshold -Diff Threshold}");
								}
							} 
							}else {

								Globals.setAlertInformation("Görüntü Yüklenmedi.");

							}
							
							
						}
						if (filterType.equals(FilterType.RedThresholdForMap)) {
							if (Globals.mainController.getImage() != null) {

								boolean val = Globals.getImageType(Globals.mainController.getImage(), Globals.ImageType.Integer.toString(),
										null);

								if (val == true) {

									// int[][] image = GraphicsIO.convertToIntegerArray((BufferedImage) i);
									if (!filtersFrameController.cpRedLower.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpRedUpper.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpUpperThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpBrigtness.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.isChangedRedDiff== false
											&& filtersFrameController.isChangedRgbDiff == false 
											&& filtersFrameController.cpBackgroundColor.getValue().toString()
											.equals("0x00000000")) {

										int redLowerBound =GraphicsIO.getRGB(
												(int) filtersFrameController.cpRedLower.getValue().getRed(),
												(int) filtersFrameController.cpRedLower.getValue().getGreen(),
												(int) filtersFrameController.cpRedLower.getValue().getBlue());
										int redUpperBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpRedUpper.getValue().getRed(),
												(int) filtersFrameController.cpRedUpper.getValue().getGreen(),
												(int) filtersFrameController.cpRedUpper.getValue().getBlue());

										boolean[][] map = GraphicsIO.applyRedThresholdForMap((int[][]) Globals.mainController.getImage(), redLowerBound,
												redUpperBound);
										Globals.processString = " image = Packages.arvasis.drawing.GraphicsIO.applyRedThresholdForMap(image,"+ redLowerBound+","+ redUpperBound+")";
										
									} else if (!filtersFrameController.cpRedLower.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpRedUpper.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpUpperThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpBrigtness.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.isChangedRedDiff== false
											&& filtersFrameController.isChangedRgbDiff == false 
											&& filtersFrameController.cpBackgroundColor.getValue().toString()
											.equals("0x00000000")) {

										int redLowerBound =GraphicsIO.getRGB(
												(int) filtersFrameController.cpRedLower.getValue().getRed(),
												(int) filtersFrameController.cpRedLower.getValue().getGreen(),
												(int) filtersFrameController.cpRedLower.getValue().getBlue());
										int redUpperBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpRedUpper.getValue().getRed(),
												(int) filtersFrameController.cpRedUpper.getValue().getGreen(),
												(int) filtersFrameController.cpRedUpper.getValue().getBlue());
										double brightnessThreshold =  GraphicsIO.getRGB(
												(int) filtersFrameController.cpBrigtness.getValue().getRed(),
												(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
												(int) filtersFrameController.cpBrigtness.getValue().getBlue());

										boolean[][] map = GraphicsIO.applyRedThresholdForMap((int[][]) Globals.mainController.getImage(), redLowerBound,
												redUpperBound, brightnessThreshold);
										Globals.processString = " image = Packages.arvasis.drawing.GraphicsIO.applyRedThresholdForMap(image,"+ redLowerBound+","+ redUpperBound+","+brightnessThreshold+")";
										

									} else if (!filtersFrameController.cpRedLower.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpRedUpper.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpUpperThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&&  !filtersFrameController.cpBrigtness.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.isChangedRedDiff== true
													&& filtersFrameController.isChangedRgbDiff == false 
													&& filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")) {

										int redLowerBound =GraphicsIO.getRGB(
												(int) filtersFrameController.cpRedLower.getValue().getRed(),
												(int) filtersFrameController.cpRedLower.getValue().getGreen(),
												(int) filtersFrameController.cpRedLower.getValue().getBlue());
										int redUpperBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpRedUpper.getValue().getRed(),
												(int) filtersFrameController.cpRedUpper.getValue().getGreen(),
												(int) filtersFrameController.cpRedUpper.getValue().getBlue());
										double brightnessThreshold =  GraphicsIO.getRGB(
												(int) filtersFrameController.cpBrigtness.getValue().getRed(),
												(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
												(int) filtersFrameController.cpBrigtness.getValue().getBlue());
										int redDiffThreshold = filtersFrameController.spRedDiff.getValue();

										boolean[][] map = GraphicsIO.applyRedThresholdForMap((int[][]) Globals.mainController.getImage(), redLowerBound,
												redUpperBound, brightnessThreshold, redDiffThreshold);
										Globals.processString = " image = Packages.arvasis.drawing.GraphicsIO.applyRedThresholdForMap(image,"+ redLowerBound+","+ redUpperBound+","+brightnessThreshold+","+redDiffThreshold+")";

		
									} else if (filtersFrameController.cpRedLower.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.cpRedUpper.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpLowerThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&& !filtersFrameController.cpUpperThresholdRGB.getValue().toString()
											.equals("0x00000000")
											&&  !filtersFrameController.cpBrigtness.getValue().toString()
											.equals("0x00000000")
											&& filtersFrameController.isChangedRedDiff== false
													&& filtersFrameController.isChangedRgbDiff == true 
													&& !filtersFrameController.cpBackgroundColor.getValue().toString()
													.equals("0x00000000")
											&& filtersFrameController.cbInverse.isSelected() == true) {

										int lowerBound =GraphicsIO.getRGB(
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpLowerThresholdRGB.getValue().getBlue());
										int upperBound = GraphicsIO.getRGB(
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getRed(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getGreen(),
												(int) filtersFrameController.cpUpperThresholdRGB.getValue().getBlue());
										double brightnessThreshold =  GraphicsIO.getRGB(
												(int) filtersFrameController.cpBrigtness.getValue().getRed(),
												(int) filtersFrameController.cpBrigtness.getValue().getGreen(),
												(int) filtersFrameController.cpBrigtness.getValue().getBlue());
										int diffThreshold = (int) filtersFrameController.spRGBDiff.getValue();
										boolean isInverse = filtersFrameController.cbInverse.isSelected();
										int background = GraphicsIO.getRGB(
												(int) filtersFrameController.cpBackgroundColor.getValue().getRed(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getGreen(),
												(int) filtersFrameController.cpBackgroundColor.getValue().getBlue());
										
										boolean[][] map = GraphicsIO.applyRedThresholdForMap((int[][]) Globals.mainController.getImage(), lowerBound,
												upperBound, brightnessThreshold, diffThreshold, background, isInverse);
										Globals.processString = " image = GraphicsIO.applyRedThresholdForMap(image, "+lowerBound+","+ upperBound+","+brightnessThreshold+","+diffThreshold+","+ background+","+ isInverse+")";

									} else {
										Globals.setAlertInformation("Uygun Parametreler:\n"
												+ "{Red Lower Bound - Red Upper Bound}\n"
												+ "{Red Lower Bound - Red Upper Bound - Brightness Threshold}"
												+ "{Red Lower Bound - Red Upper Bound - Brightness Threshold - Red Diff Threshold}\n"
												+ "{Lower Bound - Upper Bound - Brightness Threshold - Diff Threshold - Background Color - Inverse}\n");
									}
								}
							} else {
								Globals.setAlertInformation("Görüntü Yüklenmedi.");
							}

						}
					}
				});

			}
		};
		// GridPane gridPane = (GridPane) content;
		frame.setContentPane(content);
		frame.getBtnApply().setTranslateY(((GridPane) content).getRowConstraints().size() * 30);

		/*
		 * GridPane root; try { root =
		 * FXMLLoader.load(getClass().getResource("/Main/FiltersFrame.fxml")); VBox
		 * box=(VBox) root.getChildren().get(0); Pane pane=(Pane)
		 * box.getChildren().get(0); Button btn=(Button) box.getChildren().get(1);
		 * pane.getChildren().add(content); btn.setTranslateY(((Region)
		 * content).getPrefHeight());
		 * 
		 * show(root,title); } catch (IOException e) { e.printStackTrace(); }
		 */

	}

	public void show(Parent root, String title) {
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(title);
		stage.show();
	}

	@FXML
	public void openFixedColorExtraction() {
		try {
			filterType = FilterType.FixedColorExtraction;

			FXMLLoader filtersFrameLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			GridPane content = filtersFrameLoader.load();
			filtersFrameController = filtersFrameLoader.getController();
			// filtersFrameController.cpLowerThresholdRGB.setValue(new Color(0, 0, 0, 0));

			// GridPane content =
			// FXMLLoader.load(getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			/// cpLowerThresholdRGB = (ColorPicker) content.getChildren().get(2);
			// cpLowerThresholdRGB.setValue(new Color(0, 0, 0, 0));

			// cpUpperThresholdRGB = (ColorPicker) content.getChildren().get(4);
			// cpUpperThresholdRGB.setValue(new Color(0, 0, 0, 0));

			// cpBackgroundColor = (ColorPicker) content.getChildren().get(14);
			// cpBackgroundColor.setValue(new Color(0, 0, 0, 0));

			showFilter(content, "Fixed Color Extraction");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void openBackgroundExtraction() {

	}

	@FXML
	public void openShapeClassification() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/ShapeClassification.fxml"));
			loader.setController(this);
			Parent root = loader.load();

			show(root, "Shape Classification");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void openKeyPointAlg() {

	}

	@FXML
	public void thresholdForMap() {
		try {

			filterType = FilterType.ThresholdForMap;
			FXMLLoader filtersFrameLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			GridPane content = filtersFrameLoader.load();
			filtersFrameController = filtersFrameLoader.getController();

			showFilter(content, "Threshold For Map");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void rgbThresholdSetComp(Parent content) {
		filtersFrameController.btnPoints.setVisible(false);
		filtersFrameController.btnLower.setVisible(false);
		filtersFrameController.btnUpper.setVisible(false);

		filtersFrameController.cpBrigtness = new ColorPicker();
		filtersFrameController.cpBrigtness.setMinHeight(30);
		filtersFrameController.cpBrigtness.setValue(new Color(0, 0, 0, 0));
		((GridPane) content).add(filtersFrameController.cpBrigtness, 2, 2);
		filtersFrameController.cpBrigtness.setPrefSize(150, 30);

		filtersFrameController.lblLower.setText("Lower Bounds:");
		filtersFrameController.lblUpper.setText("Upper Bounds:");
		filtersFrameController.lblPoints.setText("Brightness Threshold:");
		filtersFrameController.lblRgbDiff.setText("Diff Threshold:");

		if (filterType.equals(FilterType.RedThresholdForMap)) {
			filtersFrameController.btnLower.setMinHeight(20);

			filtersFrameController.lblRedLower = new Label("Red Lower Bound:");
			filtersFrameController.cpRedLower = new ColorPicker();
			filtersFrameController.cpRedLower.setMinHeight(30);
			filtersFrameController.cpRedLower.setValue(new Color(0, 0, 0, 0));
			filtersFrameController.cpRedLower.setPrefSize(150, 30);
			((GridPane) content).add(filtersFrameController.cpRedLower, 2, 6);
			((GridPane) content).add(filtersFrameController.lblRedLower, 0, 6);

			filtersFrameController.lblRedUpper = new Label("Red Upper Bound:");
			filtersFrameController.cpRedUpper = new ColorPicker();
			filtersFrameController.cpRedUpper.setMinHeight(30);
			filtersFrameController.cpRedUpper.setValue(new Color(0, 0, 0, 0));
			filtersFrameController.cpRedUpper.setPrefSize(150, 30);
			((GridPane) content).add(filtersFrameController.cpRedUpper, 2, 7);
			((GridPane) content).add(filtersFrameController.lblRedUpper, 0, 7);

			filtersFrameController.lblRgbDiff = new Label("Red Diff Threshold:");
			filtersFrameController.spRedDiff = new Spinner<Integer>();
			filtersFrameController.spRedDiff.setMinHeight(30);
			filtersFrameController.spRedDiff.setEditable(true);
			filtersFrameController.spRedDiff.setValueFactory(new IntegerSpinnerValueFactory(0, 255));

			((GridPane) content).add(filtersFrameController.spRedDiff, 2, 8);
			((GridPane) content).add(filtersFrameController.lblRgbDiff, 0, 8);

			GridPane.setMargin(((GridPane) content).getChildren().get(16), new Insets(0, 0, 0, 10));
			GridPane.setMargin(((GridPane) content).getChildren().get(18), new Insets(0, 0, 0, 10));
			GridPane.setMargin(((GridPane) content).getChildren().get(20), new Insets(0, 0, 0, 10));
		}

	}

	@FXML
	public void blueThresholdForMap() {
		try {
			filterType = FilterType.BlueThresholdForMap;
			FXMLLoader filtersFrameLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			GridPane content = filtersFrameLoader.load();
			filtersFrameController = filtersFrameLoader.getController();
			showFilter(content, "Blue Threshold For Map");

			rgbThresholdSetComp(content);

			// content.add(filtersFrameController.lblBrightness,0,2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void greenThresholdForMap() {
		try {
			filterType = FilterType.GreenThresholdForMap;
			FXMLLoader filtersFrameLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			GridPane content = filtersFrameLoader.load();
			filtersFrameController = filtersFrameLoader.getController();
			showFilter(content, "Green Threshold For Map");

			rgbThresholdSetComp(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void redThresholdForMap() {
		try {
			filterType = FilterType.RedThresholdForMap;
			FXMLLoader filtersFrameLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/FixedColorExtractionFrame.fxml"));
			GridPane content = filtersFrameLoader.load();
			filtersFrameController = filtersFrameLoader.getController();

			rgbThresholdSetComp(content);
			// VBox root =
			// FXMLLoader.load(getClass().getResource("/Identification&Locate/RedThresholdForMapFrame.fxml"));
			showFilter(content, "Red Threshold For Map");

			/*
			 * FiltersFrame frame = new FiltersFrame("Red Threshold For Map") {
			 * 
			 * @Override public void btnApplyAddMouseListener() { // TODO Auto-generated
			 * method stub
			 * 
			 * } }; VBox vBox = root; frame.setContentPane(vBox);
			 * frame.getBtnApply().setTranslateY(vBox.getPrefHeight());
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openTrain() {
		try {
			FXMLLoader trainingLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/Shape/TrainingFrame.fxml"));
			Parent root = trainingLoader.load();
			shapeClassificationController = trainingLoader.getController();
			
			//Parent root = FXMLLoader.load(getClass().getResource("/Identification&Locate/Shape/TrainingFrame.fxml"));
			show(root, "Train");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openDetection() {
		try {
			FXMLLoader trainingLoader = new FXMLLoader(
					getClass().getResource("/Identification&Locate/Shape/DetectionFrame.fxml"));
			Parent root = trainingLoader.load();
			shapeClassificationController = trainingLoader.getController();
			//Parent root = FXMLLoader.load(getClass().getResource("/Identification&Locate/Shape/DetectionFrame.fxml"));
			show(root, "Detection");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
