package interfacebuilder;

import java.io.IOException;
import java.lang.reflect.Method;

import arvasis.sensor.studio.tree.TreeNode;
import arvasis.tool.grid.GridView;
import controller.identification.FiltersFrameController;
import globals.Globals;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class FiltersFrame {
	public Button btnApply;
	private Button btnUndoAllChanges;
	private Button btnOk;
	private Button btnReject;
	private GridPane grd;
	private Pane contentPane;
	private Stage stage;
	public Method selectedMethod;
	public String processString;

	public FiltersFrame(String title) {
		try {
			
			
			grd = FXMLLoader.load(getClass().getResource("/Main/FiltersFrame.fxml"));
			VBox box = (VBox) grd.getChildren().get(0);
			contentPane = (Pane) box.getChildren().get(0);
			btnApply = (Button) box.getChildren().get(1);
			btnApplyAddMouseListener();

			btnUndoAllChanges = (Button) grd.getChildren().get(1);
			HBox hbox = (HBox) grd.getChildren().get(2);
			btnOk=(Button) hbox.getChildren().get(0);
			btnOk.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					addFilter(Globals.mainController.getImage());
					
				}
			});
			
			
			btnReject=(Button) hbox.getChildren().get(1);
			btnReject.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deleteFilter();
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		show(title);
	}
	public void addFilter(Object image) {
		//TODO add node with image to tree
		Globals.tree.addChild(new TreeNode(selectedMethod.getName(),image,processString));
		((Stage)grd.getScene().getWindow()).close();
	}
	public void deleteFilter() {
		//TODO set previous node image to imageview
	}
	public void show(String title) {
		stage = new Stage();
		stage.setTitle(title);
		stage.setScene(new Scene(grd));
		stage.show();
	}

	public void setTitle(String title) {
		stage.setTitle(title);
	}

	public abstract void btnApplyAddMouseListener();

	public Pane getContentPane() {
		return contentPane;
	}

	public void setContentPane(GridPane contentPane) {
		this.contentPane.getChildren().add(contentPane);
	}
	public void setContentPane(Parent content) {
		this.contentPane.getChildren().add(content);
	}

	public Button getBtnApply() {
		return btnApply;
	}

	public void setBtnApply(Button btnApply) {
		this.btnApply = btnApply;
	}
}
