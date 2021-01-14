package interfacebuilder;

import java.io.IOException;

import arvasis.tool.grid.GridView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
	public FiltersFrame(String title) {
		try {
			grd=FXMLLoader.load(getClass().getResource("/Main/FiltersFrame.fxml"));
			VBox box=(VBox) grd.getChildren().get(0);
			contentPane=(Pane) box.getChildren().get(0);
			btnApply=(Button) box.getChildren().get(1);
			btnApplyAddMouseListener();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		show(title);
	}
	
	public void show(String title) {
			stage=new Stage();
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

	public Button getBtnApply() {
		return btnApply;
	}

	public void setBtnApply(Button btnApply) {
		this.btnApply = btnApply;
	}
}
