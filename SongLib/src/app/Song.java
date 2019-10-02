package app;

public class Song implements Comparable<Song> {
	
	private String name;
	private String artist;
	private String year;
	private String album;
	
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
		this.year = "";
		this.album = "";
	}
	
	public void setName(String nme) {
		name = nme;
	}
	public void setArtist(String artst) {
		artist = artst;
	}
	public void setYear(String yr) {
		year = yr;
	}
	public void setAlbum(String albm) {
		album = albm;
	}

	public String getName(){
		return name;
	}
	
	public String getArtist(){
		return artist;
	}

	public String getYear(){
		return year;
	}

	public String getAlbum(){
		return album;
	}

	@Override
	public int compareTo(Song o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
