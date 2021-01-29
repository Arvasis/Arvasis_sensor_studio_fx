package controller;

import java.io.IOException;

import controller.identification.FiltersFrameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationStepsController {
	//private AdapterController adapterController;
	private MenuController menuController;
	private IdentificationController identificationController;
	
	public void setMenuController(MenuController menuController) {
		this.menuController=menuController;
	}
	
	public void setIdentificationController(IdentificationController identificationController) {
		this.identificationController=identificationController;
	}
	@FXML
	public void openConnectToCamera() {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/ApplicationSteps/AdapterFrame.fxml"));
			Parent adapterFrame=loader.load();
			AdapterController adapterController=loader.getController();
			adapterController.setMenuController(menuController);
			Stage stage=new Stage();
			stage.setTitle("choose Your Adapter");
			stage.setScene(new Scene(adapterFrame));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openSetupCamera() {
		
	}
	@FXML
	public void openFlashSettings() {
		try {
			Parent flashSettings=FXMLLoader.load(getClass().getResource("/ApplicationSteps/FlashSettingsFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Flash Settings");
			stage.setScene(new Scene(flashSettings));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openBasicCVFunctions() {
		try {
			Parent basicCVFunc=FXMLLoader.load(getClass().getResource("/ApplicationSteps/BasicCVFunctionsFrame2.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Basic CV Functions");
			stage.setScene(new Scene(basicCVFunc));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openCondition() {
		try {
			Parent conditionFr=FXMLLoader.load(getClass().getResource("/ApplicationSteps/ConditionFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Add Conditions");
			stage.setScene(new Scene(conditionFr));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openLoop() {
		try {
			Parent loopFrame=FXMLLoader.load(getClass().getResource("/ApplicationSteps/LoopFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Add Loop");
			stage.setScene(new Scene(loopFrame));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openIdentification() {
		try {
			//Parent identification=FXMLLoader.load(getClass().getResource("/ImageProcessing/Identification&LocateFrame.fxml"));
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/ImageProcessing/Identification&LocateFrame.fxml"));
			Parent identificationFrame=loader.load();
			setIdentificationController(loader.getController());
		
			
			
			Stage stage=new Stage();
			stage.setTitle("Identification & Locate");
			stage.setScene(new Scene(identificationFrame));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void openAnalysis() {
		try {
			Parent analysis=FXMLLoader.load(getClass().getResource("/ImageProcessing/Analysis&MeasurementFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Analysis & Measurement");
			stage.setScene(new Scene(analysis));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML 
	public void openCameracommunication(){
		try {
			Parent cameraConnection=FXMLLoader.load(getClass().getResource("/Result/CameraConnectionFrame.fxml"));
			Stage stage=new Stage();
			stage.setTitle("Camera Communication");
			stage.setScene(new Scene(cameraConnection));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void run() {
		
	}
	}
