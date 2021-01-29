package test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableTest extends Application {
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TableView<String> table=new TableView<String>();
		ObservableList<String> deneme=FXCollections.observableArrayList("aaa","bbb");
		TableColumn nameColumn = new TableColumn("Name");
	//	nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		table.setEditable(true);
		TableColumn surnameColumn = new TableColumn("Surname");
	//	surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		table.getColumns().addAll(nameColumn, surnameColumn);
	
		table.setItems(deneme);
		
		Scene scene=new Scene(table,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
