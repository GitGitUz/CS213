package app;

/*
 * Uzair Shaikh & Visshal Suresh 
 * CS213 - Assignment 1 - Song Library
 */

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
	
	public static ObservableList<Song> readFromFile(String fileName) throws Exception{
		ObservableList<Song> songs = FXCollections.observableArrayList();
		Path path = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(path,
                StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			line = br.readLine();

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
	
	public static void writeToFile(ObservableList<Song> songs) throws IOException {
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
