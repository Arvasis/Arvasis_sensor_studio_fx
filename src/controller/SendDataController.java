package controller;

import java.sql.Timestamp;
import java.util.function.ToDoubleBiFunction;

import javax.swing.tree.DefaultMutableTreeNode;

import arvasis.camera.adapters.CameraCommunication;
import arvasis.sensor.studio.tree.TreeNode;
import globals.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SendDataController {

	@FXML
	private TextField txtIP;
	@FXML
	private TextField txtPort;
	@FXML
	private TextField txtMessage;
	@FXML
	private Button btnSend;
	@FXML
	private Button btnCancel;

	@FXML
	public void send() {
		if (!txtIP.getText().equals("") || !txtPort.getText().equals("")
				|| !txtMessage.getText().equals("")) {
			CameraCommunication com = new CameraCommunication();
			com.setHeader((byte) 0, new Timestamp(System.currentTimeMillis()));
			com.addData(txtMessage.getText());
			byte[] byteArray = com.getBytes();
			
			
			Globals.engine.putVar("byteArray", byteArray);
			Globals.engine.putVar("com", com);
			Globals.sendData(txtIP.getText(), Integer.parseInt(txtPort.getText()), byteArray);

			
			String processString = "com.addHeader(0," + System.currentTimeMillis()+ ");" 
					+ "com.addData(" + txtMessage.getText() + ");"
					+ "Packages.arvasis.globals.Globals.sendData(" + txtIP.getText()
					+ "," + Integer.parseInt(txtPort.getText()) + " , byteArray);"
							+ "a=typeof(byteArray);";
			//System.out.println("typeof a:"+Globals.engine.getVar("byteArray"));
			Globals.tree.addChild(new TreeNode("Send Data", processString));
			 
			
		}
	}

	@FXML
	public void cancel() {
		((Stage) btnCancel.getScene().getWindow()).close();
	}

}
