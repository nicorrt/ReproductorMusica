package com.anramirez.modelo.interfaces;

import java.util.ArrayList;

import com.anramirez.modelo.Song;
import com.anramirez.modelo.dao.SongDAO;

public interface ISongDAO extends IDAO<Song, Long> {
	

	public ArrayList<Song> getSongsByGender();
	public ArrayList<Song> getSongsByName();
	public ArrayList<Song> getSongsbyArtist();
	public ArrayList<Song> getSongsbyAlbum();	

	


}
