package com.anramirez.modelo;

import java.sql.Timestamp;



public class Reproduction {
	
	protected Long idReproducction;
	protected User user;
	protected Song song;
	protected Timestamp reproducction;
	
	protected Reproduction(Long idReproducction, User user, Song song, Timestamp reproducction) {
		super();
		this.idReproducction = idReproducction;
		this.user = user;
		this.song = song;
		this.reproducction = reproducction;
	}

	protected Reproduction() {
		super();
	}

	protected Reproduction(Long idReproducction, User user, Song song) {
		super();
		this.idReproducction = idReproducction;
		this.user = user;
		this.song = song;
	}

	public Long getIdReproducction() {
		return idReproducction;
	}

	public void setIdReproducction(Long idReproducction) {
		this.idReproducction = idReproducction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Timestamp getReproducction() {
		return reproducction;
	}

	public void setReproducction(Timestamp reproducction) {
		this.reproducction = reproducction;
	}

	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idReproducction == null) ? 0 : idReproducction.hashCode());
		result = prime * result + ((reproducction == null) ? 0 : reproducction.hashCode());
		result = prime * result + ((song == null) ? 0 : song.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Reproduction other = (Reproduction) obj;
		if (idReproducction == null) {
			if (other.idReproducction != null)
				return false;
		} else if (!idReproducction.equals(other.idReproducction))
			return false;
		if (reproducction == null) {
			if (other.reproducction != null)
				return false;
		} else if (!reproducction.equals(other.reproducction))
			return false;
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reproduction [idReproducction=" + idReproducction + ", user=" + user + ", song=" + song
				+ ", reproducction=" + reproducction + "]";
	}
	
	

}
