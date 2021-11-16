package com.anramirez.modelo;

public class Subcription {
	
	protected Long idSubcription;
	protected User user;
	protected PlayList playList;
	
	protected Subcription(Long idSubcription, User user, PlayList playList) {
		super();
		this.idSubcription = idSubcription;
		this.user = user;
		this.playList = playList;
	}

	protected Subcription() {
		super();
	}

	public Long getIdSubcription() {
		return idSubcription;
	}

	public void setIdSubcription(Long idSubcription) {
		this.idSubcription = idSubcription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSubcription == null) ? 0 : idSubcription.hashCode());
		result = prime * result + ((playList == null) ? 0 : playList.hashCode());
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
		Subcription other = (Subcription) obj;
		if (idSubcription == null) {
			if (other.idSubcription != null)
				return false;
		} else if (!idSubcription.equals(other.idSubcription))
			return false;
		if (playList == null) {
			if (other.playList != null)
				return false;
		} else if (!playList.equals(other.playList))
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
		return "Subcription [idSubcription=" + idSubcription + ", user=" + user + ", playList=" + playList + "]";
	}
	
	
	
	

}
