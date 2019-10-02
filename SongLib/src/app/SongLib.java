package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.Controller;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;

public class SongLib extends Application {
	
	private Stage primaryStage;
	private AnchorPane root;
	//private Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UI.fxml"));
			root = (AnchorPane) loader.load();
			
			//controller = loader.getController();
			//controller.start(primaryStage);
			
			Scene scene = new Scene(root, 500, 500);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
