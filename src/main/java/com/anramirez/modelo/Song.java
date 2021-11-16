package com.anramirez.modelo;

public class Song {
	
	protected Long idSong;
	protected String name;
	protected int timeSong;
	protected Gender genderSong;
	protected Album albumSong;
	protected int reproduction;
	
	
	
		public Song(Long idSong, String name, int timeSong, Gender genderSong, Album albumSong, int reproduction) {
		super();
		this.idSong = idSong;
		this.name = name;
		this.timeSong = timeSong;
		this.genderSong = genderSong;
		this.albumSong = albumSong;
		this.reproduction = reproduction;
	}


	public Song(Long idSong, String name, int timeSong, Album albumSong, Gender genderSong) {
		super();
		this.idSong = idSong;
		this.name = name;
		this.timeSong = timeSong;
		this.genderSong = genderSong;
		this.albumSong = albumSong;
	}


	public Song() {
		super();
	}


	public Song(String name, int timeSong, Album albumSong, Gender genderSong) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.timeSong = timeSong;
		this.genderSong = genderSong;
		this.albumSong = albumSong;
	}



	public Song(Long idSong2, String name2, Gender genderSong2, Album albumSong2, int timeSong2) {
		// TODO Auto-generated constructor stub
	}


	public Long getIdSong() {
		return idSong;
	}


	public void setIdSong(Long idSong) {
		this.idSong = idSong;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTimeSong() {
		return timeSong;
	}


	public void setTimeSong(int timeSong) {
		this.timeSong = timeSong;
	}


	public Gender getGenderSong() {
		return genderSong;
	}


	public void setGenderSong(Gender genderSong) {
		this.genderSong = genderSong;
	}


	public Album getAlbumSong() {
		return albumSong;
	}


	public void setAlbumSong(Album albumSong) {
		this.albumSong = albumSong;
	}
	
	
	public int getReproduction() {
		return reproduction;
	}


	public void setReproduction(int reproduction) {
		this.reproduction = reproduction;
	}

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumSong == null) ? 0 : albumSong.hashCode());
		result = prime * result + ((genderSong == null) ? 0 : genderSong.hashCode());
		result = prime * result + ((idSong == null) ? 0 : idSong.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reproduction;
		result = prime * result + timeSong;
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
		Song other = (Song) obj;
		if (albumSong == null) {
			if (other.albumSong != null)
				return false;
		} else if (!albumSong.equals(other.albumSong))
			return false;
		if (genderSong == null) {
			if (other.genderSong != null)
				return false;
		} else if (!genderSong.equals(other.genderSong))
			return false;
		if (idSong == null) {
			if (other.idSong != null)
				return false;
		} else if (!idSong.equals(other.idSong))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reproduction != other.reproduction)
			return false;
		if (timeSong != other.timeSong)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Song [idSong=" + idSong + ", name=" + name + ", timeSong=" + timeSong + "]";
	}
	
	
	

}
