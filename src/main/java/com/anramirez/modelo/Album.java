package com.anramirez.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album {
	
	protected Long idAlbum;
	protected String name;
	protected String photoAlbum;
	protected LocalDate albumRelease;
	protected Artist artistAlbum;
	protected ArrayList<Song> albumSongs;
	
	
	public Album() {
		super();
	}

	public Album(Long idAlbum, String name, String photoAlbum, LocalDate albumRelease, Artist artistAlbum,
			ArrayList<Song> albumSongs) {
		super();
		this.idAlbum = idAlbum;
		this.name = name;
		this.photoAlbum = photoAlbum;
		this.albumRelease = albumRelease;
		this.artistAlbum = artistAlbum;
		this.albumSongs = albumSongs;
	}

	public Album(Long idAlbum, String name, String photoAlbum, LocalDate albumRelease, Artist artistAlbum) {
		super();
		this.idAlbum = idAlbum;
		this.name = name;
		this.photoAlbum = photoAlbum;
		this.albumRelease = albumRelease;
		this.artistAlbum = artistAlbum;
	}


	public Album(Long idAlbum, String name, LocalDate albumRelease, Artist artistAlbum) {
		super();
		this.idAlbum = idAlbum;
		this.name = name;
		this.albumRelease = albumRelease;
		this.artistAlbum = artistAlbum;
	}


	public Album(String name, String photoAlbum, LocalDate albumRelease, Artist artistAlbum) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.albumRelease = albumRelease;
		this.photoAlbum = photoAlbum;
		this.artistAlbum = artistAlbum;
	}

	public Album(long idAlbum) {
		// TODO Auto-generated constructor stub
		this.idAlbum=idAlbum;
	}

	public Album(long idAlbum, String name) {
		// TODO Auto-generated constructor stub
		this.idAlbum = idAlbum;
		this.name = name;
	}

	public Long getIdAlbum() {
		return idAlbum;
	}


	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhotoAlbum() {
		return photoAlbum;
	}


	public void setPhotoAlbum(String photoAlbum) {
		this.photoAlbum = photoAlbum;
	}


	public LocalDate getAlbumRelease() {
		return albumRelease;
	}


	public void setAlbumRelease(LocalDate albumRelease) {
		this.albumRelease = albumRelease;
	}


	public Artist getArtistAlbum() {
		return artistAlbum;
	}


	public void setArtistAlbum(Artist artistAlbum) {
		this.artistAlbum = artistAlbum;
	}
	
	public ArrayList<Song> getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(ArrayList<Song> albumSongs) {
		this.albumSongs = albumSongs;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumRelease == null) ? 0 : albumRelease.hashCode());
		result = prime * result + ((albumSongs == null) ? 0 : albumSongs.hashCode());
		result = prime * result + ((artistAlbum == null) ? 0 : artistAlbum.hashCode());
		result = prime * result + ((idAlbum == null) ? 0 : idAlbum.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photoAlbum == null) ? 0 : photoAlbum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (albumRelease == null) {
			if (other.albumRelease != null)
				return false;
		} else if (!albumRelease.equals(other.albumRelease))
			return false;
		if (albumSongs == null) {
			if (other.albumSongs != null)
				return false;
		} else if (!albumSongs.equals(other.albumSongs))
			return false;
		if (artistAlbum == null) {
			if (other.artistAlbum != null)
				return false;
		} else if (!artistAlbum.equals(other.artistAlbum))
			return false;
		if (idAlbum == null) {
			if (other.idAlbum != null)
				return false;
		} else if (!idAlbum.equals(other.idAlbum))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photoAlbum == null) {
			if (other.photoAlbum != null)
				return false;
		} else if (!photoAlbum.equals(other.photoAlbum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
		/*return "Album [idAlbum=" + idAlbum + ", name=" + name + ", albumRelease=" + albumRelease + ", artistAlbum="
				+ artistAlbum + "]";*/
	}

	
	
	
	

}
