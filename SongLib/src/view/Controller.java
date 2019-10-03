package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	Text nmeText, artText, albText, yearText, nmeDetText, artDetText, albDetText, yearDetText;
	
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
		
		editBtn.setVisible(false);
		delBtn.setVisible(false);
		saveBtn.setVisible(false);
		cancBtn.setVisible(false);
		
		
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
	
	public void saveBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
		
		if(temp == saveBtn && whichBtn == 1) { // add button clicked
			String songName = nmeField.getText();
			String artistName = artField.getText();
			String albumName = albField.getText();
			String year = yearField.getText();
			
			Song tSong = new Song(songName, artistName, albumName, year);
			
			if(albumName.isEmpty()) {
				albumName = "Not Specified";
			}
			
			if(year.isEmpty()) {
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
				
				nmeDetText.setVisible(true);
				artDetText.setVisible(true);
				albDetText.setVisible(true);
				yearDetText.setVisible(true);
				
				nmeText.setVisible(false);
				artText.setVisible(false);
				albText.setVisible(false);
				yearText.setVisible(false);
				
				nmeField.setEditable(false);
				artField.setEditable(false);
				albField.setEditable(false);
				yearField.setEditable(false);
				
				nmeField.setVisible(false);
				artField.setVisible(false);
				albField.setVisible(false);
				yearField.setVisible(false);
				
				nmeField.clear();
				artField.clear();
				albField.clear();
				yearField.clear();
				
				sListView.getSelectionModel().select(sLib.songList.sorted().indexOf(tSong));
				
			}else { // alert the user
				addBtn.setVisible(false);

				Alert error = new Alert(AlertType.ERROR);
				error.initOwner(mainStage);
				error.setTitle("404 Error: 2 Possible Reasons");
				error.setHeaderText("1 - Invalid song addition, please provide both a Name and Artist\n2 - Song already in library");
				error.showAndWait();
				
				saveBtn.setVisible(true);
				cancBtn.setVisible(true);
				
			}
			
		}else if(temp == saveBtn && whichBtn == 2) { // edit button clicked
			String songName = nmeField.getText();
			String artistName = artField.getText();
			String albumName = albField.getText();
			String year = yearField.getText();
			
			if(albumName.isEmpty()) {
				albumName = "Not Specified";
			}
			
			if(year.isEmpty()) {
				year = "Not Specified";
			}
			
			boolean success = sLib.edit(currentSong, songName, artistName, albumName, year);
			
			whichBtn = 0;
			
			saveBtn.setVisible(false);
			cancBtn.setVisible(false);
			addBtn.setVisible(true);
			
			if(success == true) {
				sListView.setItems(sLib.songList.sorted());
				sListView.refresh();
				
				nmeText.setVisible(false);
				artText.setVisible(false);
				albText.setVisible(false);
				yearText.setVisible(false);
				
				nmeField.clear();
				artField.clear();
				albField.clear();
				yearField.clear();
				
				nmeField.setEditable(false);
				artField.setEditable(false);
				albField.setEditable(false);
				yearField.setEditable(false);
				
				nmeField.setVisible(false);
				artField.setVisible(false);
				albField.setVisible(false);
				yearField.setVisible(false);
				
				sListView.getSelectionModel().selectFirst();
				
			}else { // alert the user
				addBtn.setVisible(false);
				editBtn.setVisible(false);
				delBtn.setVisible(false);
				
				Alert error = new Alert(AlertType.ERROR);
				error.initOwner(mainStage);
				error.setTitle("404 Error: Invalid Edit");
				error.setHeaderText("Need both a Name and Artist");
				error.showAndWait();
				
				whichBtn = 2;
				
				
				saveBtn.setVisible(true);
				cancBtn.setVisible(true);
			}
			
		}else if(temp == saveBtn && whichBtn == 3) { // delete button clicked
			
			boolean success = sLib.delete(currentSong);
			
			whichBtn = 0;
			
			saveBtn.setVisible(false);
			cancBtn.setVisible(false);
			addBtn.setVisible(true);
			
			if(success == true) {
				sListView.setItems(sLib.songList.sorted());
				sListView.refresh();
				if(sListView.getSelectionModel().getSelectedIndex() == 0) {
					System.out.println("Test");
					sListView.getSelectionModel().selectNext();
				}
				
				if(sLib.songList.size() == 0) {
					
					nmeLabel.setText("");
					artLabel.setText("");
					albLabel.setText("");
					yearLabel.setText("");
					
					nmeLabel.setVisible(false);
					artLabel.setVisible(false);
					albLabel.setVisible(false);
					yearLabel.setVisible(false);
					
					nmeDetText.setVisible(false);
					artDetText.setVisible(false);
					albDetText.setVisible(false);
					yearDetText.setVisible(false);
					
				}
			}
		}else {
			
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
			
			nmeField.setText(nmeLabel.getText());
			artField.setText(artLabel.getText());
			albField.setText(albLabel.getText());
			yearField.setText(yearLabel.getText());
			
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

	public void cancelBtnClick(ActionEvent ae) {
		Button temp = (Button) ae.getSource();
		
		if(temp == cancBtn) {
			
			nmeField.clear();
			artField.clear();
			albField.clear();
			yearField.clear();
			
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
			editBtn.setVisible(true);
			delBtn.setVisible(true);
		}
	}
}
