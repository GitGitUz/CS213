package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import view.Controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongLib extends Application {
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UI.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
	
			Controller controller = loader.getController();
			
			SongList sList = new SongList();
			
			Song tempSong = new Song("90210", "Travis Scott", "Rodeo", "2015");
			Song tempSong2 = new Song("Self Control", "Frank Ocean", "Blonde", "2016");
			Song tempSong3 = new Song("Numb", "Linkin Park", "Meteora", "2003");

			
			ArrayList<Song> tempAList = new ArrayList<Song>();
			tempAList.add(tempSong3);
			tempAList.add(tempSong2);
			tempAList.add(tempSong);
			
			ObservableList<Song> tempOList = FXCollections.observableArrayList();
			tempOList.addAll(tempAList);
			
			sList.songList = tempOList;
			
			controller.start(root, sList);
			
			Scene scene = new Scene(root);
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
