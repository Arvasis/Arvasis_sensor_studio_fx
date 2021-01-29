package controller;

import java.net.URL;
import java.util.ResourceBundle;

import arvasis.camera.ArvasisDirectCaptureCamera;
import arvasis.camera.ArvasisInspectClient;
import arvasis.camera.Camera;
import arvasis.camera.adapters.CameraCommunication;
import arvasis.drawing.GraphicsIO;
import globals.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FlashSettingsController implements Initializable{
	@FXML private ComboBox<String> cbLedID;
	@FXML private ColorPicker cpColor;
	@FXML private Button btnSend;
	@FXML private Button btnCancel;
	
	@FXML
	public void send() {
		if (cbLedID.getSelectionModel().getSelectedIndex() == 0) {
			for (int i = 0; i < 24; i++) {
				CameraCommunication comm = new CameraCommunication();
				comm.setHeader((byte) 9, System.currentTimeMillis());
				
				int red = (int) cpColor.getValue().getRed();
				int green =(int) cpColor.getValue().getGreen();
				int blue = (int) cpColor.getValue().getBlue();

				comm.addData(GraphicsIO.getARGB(i, red, green, blue));
		        Globals.sendData(((ArvasisInspectClient)Globals.cam).targetIp, ((ArvasisInspectClient)Globals.cam).targetPort, comm.getBytes());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		 else if (cbLedID.getSelectionModel().getSelectedIndex() == 1) {
				for (int i = 0; i < 24; i += 2) {
					CameraCommunication comm = new CameraCommunication();
					comm.setHeader((byte) 9, System.currentTimeMillis());
					int red = (int) cpColor.getValue().getRed();
					int green =(int) cpColor.getValue().getGreen();
					int blue = (int) cpColor.getValue().getBlue();

					comm.addData(GraphicsIO.getARGB(i, red, green, blue));
					Globals.sendData(((ArvasisInspectClient)Globals.cam).targetIp, ((ArvasisInspectClient)Globals.cam).targetPort, comm.getBytes());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (cbLedID.getSelectionModel().getSelectedIndex() == 2) {
				for (int i = 1; i < 24; i += 2) {
					CameraCommunication comm = new CameraCommunication();
					comm.setHeader((byte) 9, System.currentTimeMillis());
					
					int red = (int) cpColor.getValue().getRed();
					int green =(int) cpColor.getValue().getGreen();
					int blue = (int) cpColor.getValue().getBlue();

					comm.addData(GraphicsIO.getARGB(i, red, green, blue));
					Globals.sendData(((ArvasisInspectClient)Globals.cam).targetIp, ((ArvasisInspectClient)Globals.cam).targetPort, comm.getBytes());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				CameraCommunication comm = new CameraCommunication();
				comm.setHeader((byte) 9, System.currentTimeMillis());
				
				int red = (int) cpColor.getValue().getRed();
				int green =(int) cpColor.getValue().getGreen();
				int blue = (int) cpColor.getValue().getBlue();

				comm.addData(GraphicsIO.getARGB(Integer.parseInt(cbLedID.getValue()), red, green, blue));
				Globals.sendData(((ArvasisInspectClient)Globals.cam).targetIp, ((ArvasisInspectClient)Globals.cam).targetPort, comm.getBytes());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
	
	@FXML
	public void cancel() {
		((Stage)cpColor.getScene().getWindow()).close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options=FXCollections.observableArrayList(
				"All","Odd","Even"	);
		int k = 3;
		for (int i = 0; i < 24; i++) {
			options.add(""+i);
			k++;

		}
		cbLedID.setItems(options);
	}
}
