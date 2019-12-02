package application;

import Model.*;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	public static Stage stage;
	public static AnchorPane layout;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("G9 Grocers");
			initLayout();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initLayout() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("../View/Main.fxml") );
			layout = (AnchorPane) loader.load();
			Scene scene = new Scene( layout );
			stage.setScene( scene );
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
