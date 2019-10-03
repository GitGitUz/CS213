package app;

public class Song implements Comparable<Song> {
	
	private String name;
	private String artist;
	private String album;
	private String year;
	
	public Song(String name, String artist, String album, String year) {
		super();
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
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
	
	@Override
    public int hashCode() {
        return 11 + 7*name.hashCode() * artist.hashCode();
    }
	
	public String toString() {
		return name + " - " + artist;
	}
	
}
