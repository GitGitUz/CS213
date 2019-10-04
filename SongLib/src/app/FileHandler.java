package app;

import app.Song;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileHandler {
	
	private static ObservableList<Song> readFromFile(String fileName){
		ObservableList<Song> songs = FXCollections.observableArrayList();
		Path path = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(path,
                StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while(line != null) {
				String[] songProperties = line.split(",");
				Song s = new Song(songProperties[0],songProperties[1],songProperties[2],songProperties[3]);
				songs.add(s);
				line = br.readLine();
			}
		}catch (IOException ioe) {
            ioe.printStackTrace();
		}
		return songs;
	}
	
	public static void writetoFile(ObservableList<Song> songs) throws IOException {
		FileWriter creator = new FileWriter("songs.csv");
		creator.append("Name");
		creator.append(",");
		creator.append("Artist");
		creator.append(",");
		creator.append("Album");
		creator.append(",");
		creator.append("Year");
		creator.append("\n");
		
		for(Song s : songs) {
			creator.append(s.toFileString());
			creator.append("\n");
		}
		
		creator.flush();
		creator.close();
		
	}
}
