package controller.identification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.IntBinaryOperator;

import javax.swing.table.TableStringConverter;

import arvasis.drawing.objects.PixelLocation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FiltersFrameController implements Initializable {

	@FXML public Label lblLower;
	@FXML public Label lblUpper;
	@FXML public Label lblPoints;
	@FXML public Label lblRgbDiff;
	@FXML public Label lblBackground;
	@FXML public Label lblInverse;
	public Label lblBrightness;
	public Label lblRedLower;
	public Label lblRedUpper;
	public Label lblRedDiff;
	public ColorPicker cpBrigtness;
	
	public ColorPicker cpRedLower;
	public ColorPicker cpRedUpper;
	public Spinner<Integer> spRedDiff;
	@FXML
	public ColorPicker cpLowerThresholdRGB;
	@FXML
	public Button btnLower;

	@FXML
	public ColorPicker cpUpperThresholdRGB;
	@FXML
	public Button btnUpper;

	@FXML
	public Button btnPoints;
	
	@FXML
	public Button btnOk;
	@FXML
	public TableView<RGB> tableRGB;
	@FXML
	public TableColumn<RGB, String> rgb;
	@FXML
	public ContextMenu cmPopup;
	@FXML
	public MenuItem popupAddRow;

	@FXML
	public TableView<Pixel> tablePixel;
	@FXML
	public TableColumn<Pixel, String> colX;
	@FXML
	public TableColumn<Pixel, String> colY;
	@FXML
	public ContextMenu cmPopupPixel;
	@FXML
	public MenuItem popupPixelAddRow;

	@FXML
	public Spinner<Object> spRGBDiff;

	@FXML
	public ColorPicker cpBackgroundColor;

	@FXML
	public CheckBox cbInverse;

	public boolean isChangedRgbDiff = false;
	public boolean isChangedRedDiff = false;
	

	public PixelLocation[] pixel;
	public int[] lowerThreshold, upperThreshold;
	String type;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cpLowerThresholdRGB.setValue(new Color(0, 0, 0, 0));
		cpUpperThresholdRGB.setValue(new Color(0, 0, 0, 0));
		cpBackgroundColor.setValue(new Color(0, 0, 0, 0));

		spRGBDiff.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				isChangedRgbDiff = true;

			}
		});

	}

	public void showRGB(String title, String colName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Identification&Locate/PointsFrame.fxml"));
			loader.setController(this);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle(title);
			stage.show();

			rgb.setCellValueFactory(new PropertyValueFactory<>("rgb"));
			rgb.setCellFactory(TextFieldTableCell.forTableColumn());
			rgb.setText(colName);

			tableRGB.setEditable(true);
			tableRGB.setContextMenu(cmPopup);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showPixel(String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Identification&Locate/PixelFrame.fxml"));
			loader.setController(this);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle(title);
			stage.show();

			colX.setCellValueFactory(new PropertyValueFactory<>("colX"));
			colX.setCellFactory(TextFieldTableCell.forTableColumn());
			colY.setCellValueFactory(new PropertyValueFactory<>("colY"));
			colY.setCellFactory(TextFieldTableCell.forTableColumn());

			tablePixel.setEditable(true);
			tablePixel.setContextMenu(cmPopupPixel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void actionPoints() {
		showPixel("Points[]");
	}

	@FXML
	public void enterRGB() {
		int size = tableRGB.getItems().size();
		if (type.equals("lower")) {
			
			if (size != 0) {
				lowerThreshold = new int[size];
				for (int i = 0; i < size; i++) {
					String x = String.valueOf(tableRGB.getItems().get(i).getRgb());
					lowerThreshold[i] = Integer.valueOf(x);
					System.out.println(type+ "   "+x);
					btnLower.setStyle("-fx-background-color: #ff0000; ");
				}

			} else 
				btnLower.setStyle("-fx-background-color: #ffff00; ");

		}
		if (type.equals("upper")) {
			if (size != 0) {
				upperThreshold = new int[size];
				for (int i = 0; i < size; i++) {
					String x = String.valueOf(tableRGB.getItems().get(i).getRgb());
					System.out.println(type+"  "+x);
					upperThreshold[i] = Integer.valueOf(x);
					btnUpper.setStyle("-fx-background-color: #ff0000; ");
				}

			} else 
				btnUpper.setStyle("-fx-background-color: #ffff00; ");

		}
	}

	@FXML
	public void enterPixel() {

		int size = tablePixel.getItems().size();

		if (size != 0) {
			pixel = new PixelLocation[size];
			for (int i = 0; i < size; i++) {
				String x = String.valueOf(tablePixel.getItems().get(i).getColX());
				String y = String.valueOf(tablePixel.getItems().get(i).getColY());
				
				pixel[i] = new PixelLocation(Integer.valueOf(x), Integer.valueOf(y));
				btnPoints.setStyle("-fx-background-color: #ff0000; ");
			}

		} else {
			btnPoints.setStyle("-fx-background-color: #ffff00; ");
		}

	}

	@FXML
	public void onEditCommit(TableColumn.CellEditEvent<RGB, String> cellEditEvent) {
		RGB rgb = tableRGB.getSelectionModel().getSelectedItem();
		rgb.setRgb(cellEditEvent.getNewValue());

	}

	@FXML
	public void addRow() {
		tableRGB.getItems().add(new RGB("new Row"));
	}

	@FXML
	public void onEditCommitPixelX(TableColumn.CellEditEvent<RGB, String> cellEditEvent) {
		Pixel pixel = tablePixel.getSelectionModel().getSelectedItem();
		pixel.setColX(cellEditEvent.getNewValue());
		// pixel.setColY(cellEditEvent.getNewValue());

	}

	@FXML
	public void onEditCommitPixelY(TableColumn.CellEditEvent<RGB, String> cellEditEvent) {
		Pixel pixel = tablePixel.getSelectionModel().getSelectedItem();
		// pixel.setColX(cellEditEvent.getNewValue());
		pixel.setColY(cellEditEvent.getNewValue());

	}

	@FXML
	public void addRowPixel() {
		tablePixel.getItems().add(new Pixel("0", "0"));
	}

	@FXML
	public void lowerThresholdRGB() {
		type = "lower";
		showRGB("Lower Threshold RGB[]", "LowerThresholdRGB");
	}

	@FXML
	public void upperThresholdRGB() {
		type = "upper";
		showRGB("Upper Threshold RGB[]", "UpperThresholdRGB");
	}

}
