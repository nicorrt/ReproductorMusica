package com.anramirez.modelo;

import java.util.ArrayList;

public class Artist {
	
	protected Long idArtist;
	protected String name;
	protected String nation;
	protected String photoUrl;
	protected ArrayList<Album> albumArtist;
	
	
	public Artist(Long idArtist, String name, String nation, String photoUrl, ArrayList<Album> albumArtist) {
		super();
		this.idArtist = idArtist;
		this.name = name;
		this.nation = nation;
		this.photoUrl = photoUrl;
		this.albumArtist = albumArtist;
	}


	public Artist(long l) {
		super();
		this.idArtist=l;
	}
	
	public Artist() {
		super();
	}


	public Artist(Long idArtist, String name, String nation, String photoUrl) {
		super();
		this.idArtist = idArtist;
		this.name = name;
		this.nation = nation;
		this.photoUrl = photoUrl;
	}


	public Artist(String name, String nation, String photoUrl) {
		this.name = name;
		this.nation = nation;
		this.photoUrl = photoUrl;
	}


	public Artist(long idArtist, String name) {
		// TODO Auto-generated constructor stub
		this.idArtist = idArtist;
		this.name = name;
	}


	public Long getIdArtist() {
		return idArtist;
	}


	public void setIdArtist(Long idArtist) {
		this.idArtist = idArtist;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public ArrayList<Album> getAlbumArtist() {
		return albumArtist;
	}


	public void setAlbumArtist(ArrayList<Album> albumArtist) {
		this.albumArtist = albumArtist;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumArtist == null) ? 0 : albumArtist.hashCode());
		result = prime * result + ((idArtist == null) ? 0 : idArtist.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((photoUrl == null) ? 0 : photoUrl.hashCode());
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
		Artist other = (Artist) obj;
		if (albumArtist == null) {
			if (other.albumArtist != null)
				return false;
		} else if (!albumArtist.equals(other.albumArtist))
			return false;
		if (idArtist == null) {
			if (other.idArtist != null)
				return false;
		} else if (!idArtist.equals(other.idArtist))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nation == null) {
			if (other.nation != null)
				return false;
		} else if (!nation.equals(other.nation))
			return false;
		if (photoUrl == null) {
			if (other.photoUrl != null)
				return false;
		} else if (!photoUrl.equals(other.photoUrl))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return name;
		/*return "Artist [idArtist=" + idArtist + ", name=" + name + ", nation=" + nation + ", photoUrl=" + photoUrl
				+ "]";*/
	}

	
	
	

}
