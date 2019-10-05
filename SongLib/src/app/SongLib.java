package app;

/*
 * Uzair Shaikh & Visshal Suresh 
 * CS213 - Assignment 1 - Song Library
 */

import app.FileHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import view.Controller;

import java.io.File;

public class SongLib extends Application {
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UI.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
	
			Controller controller = loader.getController();
			
			File file = new File("songs.csv");
			if(!(file.exists())) {
				file.createNewFile();
			}
		
			SongList sList = new SongList();
			sList.songList = FileHandler.readFromFile(file.getName());
			
			controller.start(root, sList);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent> () {
				@Override
				public void handle(WindowEvent event) {
					try {
						FileHandler.writeToFile(sList.songList);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
