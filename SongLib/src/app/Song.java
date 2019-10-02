package app;

public class Song implements Comparable<Song> {
	
	private String name;
	private String artist;
	private String year;
	private String album;
	
	public Song(String name, String artist, String year, String album) {
		super();
		this.name = name;
		this.artist = artist;
		this.year = year;
		this.album = album;
	}
	
	public void setName(String nme) {
		this.name = nme;
	}
	
	public void setArtist(String artst) {
		this.artist = artst;
	}
	
	public void setAlbum(String albm) {
		this.album = albm;
	}

	public void setYear(String yr) {
		this.year = yr;
	}
	
	public String getName(){
		return name;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public String getAlbum(){
		return album;
	}

	public String getYear(){
		return year;
	}

	@Override
	public int compareTo(Song s) {
		return this.name.compareTo(s.name);
	}
	
	public boolean isDuplicate(Song s) {
		if(s.name.equalsIgnoreCase(this.name) && s.artist.equalsIgnoreCase(this.artist)) {
			return true;
		}
		return false;
	}
}
