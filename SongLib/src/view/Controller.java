package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.control.TableColumn;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import app.SongLib;
import app.Song;

public class Controller {
	
	@FXML
	private Label nmeLabel, artLabel, albLabel, yearLabel;
	
	@FXML
	private TextField nmeField, artField, albField, yearField;
	
	@FXML 
	private ListView<Song> songList;
	
	@FXML
	private Button addBtn, editBtn, delBtn, saveBtn, cancBtn;
	
	public void start(SongLib songlib) {}
}
