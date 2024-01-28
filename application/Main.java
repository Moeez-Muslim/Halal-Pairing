package application;

import businessLogic.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage stg;
	public void start(Stage primaryStage) throws IOException {
	        stg = primaryStage;
	        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	        primaryStage.setTitle("Halal Pairing");
	        primaryStage.setScene(new Scene(root, 1350, 700));
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		HalalPairing.Initialize();
		launch(args);
		
	}

	public void changeScene(String fxml)  throws IOException {
	        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	        stg.getScene().setRoot(pane);
	        stg.show();
	}
}
