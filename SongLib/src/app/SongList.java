package app;

import javafx.collections.ObservableList;

public class SongList {
	
	public ObservableList<Song> songList;
	
	public boolean add(String name, String artist, String album, String year) {
		
		if((name.isEmpty() || artist.isEmpty()) || (name.isBlank() || artist.isBlank())) {
			return false;
		}
		
		Song sng = new Song(name,artist,album,year);
		for(Song s : songList) {
			if(s.isDuplicate(sng)) {
				return false;
			}
			/*if(s.getName() == name) {
				
			}*/
		}
		return songList.add(sng);
	}
	
	public boolean edit(Song sng, String name, String artist, String album, String year) {
		songList.remove(sng);
		if(!add(name,artist,album,year)) {
			add(sng.getName(),sng.getArtist(),sng.getAlbum(),sng.getYear());
			return false;
		}
		return true;
	}
	
	public boolean delete(Song sng) {
		return songList.remove(sng);
	}
	
}
