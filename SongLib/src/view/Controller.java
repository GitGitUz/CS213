package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import app.*;

public class Controller {
	
	@FXML
	Label nmeLabel, artLabel, albLabel, yearLabel;
	
	@FXML
	Text nmeText, artText, albText, yearText;
	
	@FXML
	TextField nmeField, artField, albField, yearField;
	
	@FXML 
	ListView<Song> sListView;
	
	@FXML
	Button addBtn, editBtn, delBtn, saveBtn, cancBtn;
	
	private Stage mainStage;
	public ObservableList<Song> songsList;
	private Song currentSong;
	SongList sLib;
	private int whichBtn;
	
	public void start(AnchorPane root, SongList sLib) {
		this.sLib = sLib;
		
		saveBtn.setVisible(false);
		cancBtn.setVisible(false);
		editBtn.setVisible(false);
		delBtn.setVisible(false);
		
		nmeField.setVisible(false);
		artField.setVisible(false);
		albField.setVisible(false);
		yearField.setVisible(false);
		
		nmeField.setEditable(false);
		artField.setEditable(false);
		albField.setEditable(false);
		yearField.setEditable(false);
		
		nmeText.setVisible(false);
		artText.setVisible(false);
		albText.setVisible(false);
		yearText.setVisible(false);
		
		songsList = sLib.songList;
		sListView.setItems(songsList.sorted());
		
		whichBtn = 0;
		
		sListView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
			@Override
			public ListCell<Song> call(ListView<Song> p) {

				ListCell<Song> cell = new ListCell<Song>() {
					@Override
					protected void updateItem(Song s, boolean bln) {
						super.updateItem(s, bln);
						if (s != null) {
							setText(s.toString());
						} else {
							setText("");
						}
					}
				};
				return cell;
			}
			
			
		});
		
		sListView.setMaxWidth(50.0);
//		root.getChildren().add(sListView);
		sListView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldVal, newVal) -> showSongDetails(mainStage));

		sListView.getSelectionModel().select(0);
		
	}
	
	private void showSongDetails(Stage mainStage) {
		currentSong = sListView.getSelectionModel().getSelectedItem();
		if (currentSong != null) {
			
			nmeLabel.setText(currentSong.getName());
			artLabel.setText(currentSong.getArtist());
			albLabel.setText(currentSong.getAlbum());
			yearLabel.setText(currentSong.getYear());
			
			nmeLabel.setVisible(true);
			artLabel.setVisible(true);
			albLabel.setVisible(true);
			yearLabel.setVisible(true);
			
			delBtn.setVisible(true);
			editBtn.setVisible(true);

		}
	}
	
	public void addBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
		
		if(temp == addBtn) {
			whichBtn = 1;
			sListView.getSelectionModel().clearSelection();
			
			delBtn.setVisible(false);
			editBtn.setVisible(false);
			addBtn.setVisible(false);
			
			nmeField.setVisible(true);
			artField.setVisible(true);
			albField.setVisible(true);
			yearField.setVisible(true);
			
			nmeField.setEditable(true);
			artField.setEditable(true);
			albField.setEditable(true);
			yearField.setEditable(true);
			
			nmeText.setVisible(true);
			artText.setVisible(true);
			albText.setVisible(true);
			yearText.setVisible(true);
			
			saveBtn.setVisible(true);
			cancBtn.setVisible(true);
		}
	}
	
	public void editBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
		
		if(temp == editBtn) {
			whichBtn = 2;
			
			delBtn.setVisible(false);
			editBtn.setVisible(false);
			addBtn.setVisible(false);
			
			nmeField.setVisible(true);
			artField.setVisible(true);
			albField.setVisible(true);
			yearField.setVisible(true);
			
			nmeField.setEditable(true);
			artField.setEditable(true);
			albField.setEditable(true);
			yearField.setEditable(true);
			
			nmeText.setVisible(true);
			artText.setVisible(true);
			albText.setVisible(true);
			yearText.setVisible(true);
			
			saveBtn.setVisible(true);
			cancBtn.setVisible(true);
		}
	}
	public void deleteBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
			
		if(temp == delBtn) {
			whichBtn = 3;
				
			delBtn.setVisible(false);
			editBtn.setVisible(false);
			addBtn.setVisible(false);
				
			saveBtn.setVisible(true);
			cancBtn.setVisible(true);
		}
	}

	public int saveBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
		
		if(temp == saveBtn && whichBtn == 1) { //add button pressed
			String songName = nmeField.getText();
			String artistName = artField.getText();
			String albumName = albField.getText();
			String year = yearField.getText();
			
			Song tempSong = new Song(songName, artistName, albumName, year);
			
			if(albumName == "") {
				albumName = "Not Specified";
			}
			
			
			if(year == "") {
				year = "Not Specified";
			}
			
			boolean success = sLib.add(songName, artistName, albumName, year);
			
			whichBtn = 0;
			addBtn.setVisible(true);
			saveBtn.setVisible(false);
			cancBtn.setVisible(false);
			
			if(success == true) {
				sListView.setItems(sLib.songList.sorted());
				sListView.refresh();
				System.out.println(songsList.contains(tempSong));
				sListView.getSelectionModel().select(songsList.sorted().indexOf(tempSong));
				return 1;
			}else {
				return 0;
			}
		}else if(temp == saveBtn && whichBtn == 2) { //edit button pressed
			
		}else if(temp == saveBtn && whichBtn == 3) { //delete button pressed
			
		}else {
			return 0;
		}
		
		return 0;
	}

	public int cancelBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
		
		if(temp == cancBtn) {
			
			nmeField.setVisible(false);
			artField.setVisible(false);
			albField.setVisible(false);
			yearField.setVisible(false);
			
			nmeField.setEditable(false);
			artField.setEditable(false);
			albField.setEditable(false);
			yearField.setEditable(false);
			
			nmeText.setVisible(false);
			artText.setVisible(false);
			albText.setVisible(false);
			yearText.setVisible(false);
			
			cancBtn.setVisible(false);
			saveBtn.setVisible(false);
			addBtn.setVisible(true);
			
		}
		return 0;
	}
}
