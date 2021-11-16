package com.anramirez.modelo;

import java.util.ArrayList;

public class User {
	
	
	protected Long idUser;
	protected String email;
	protected String password;
	protected String nombre;
	protected ArrayList<PlayList> playlistUser;

	
	public User(Long idUser, String email, String password,String nombre, ArrayList<PlayList> playlistUser) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.nombre= nombre;
		this.playlistUser = playlistUser;
	}

	public User() {
		super();
	}

	public User(Long idUser, String email, String password, String nombre) {
		// TODO Auto-generated constructor stub
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.nombre= nombre;
	}
	
	public User(Long idUser, String email, String nombre) {
		// TODO Auto-generated constructor stub
		this.idUser = idUser;
		this.email = email;
		this.nombre= nombre;
	}

	private User(String nombre, String password) {
		// TODO Auto-generated constructor stub
		this.password = password;
		this.nombre= nombre;
	}
	
	
	public void cleanUserSession() {
		this.idUser=(long) -1;
		this.email = "";
		this.password = "";
		this.nombre= "";
		
	}

	public User(String nombre, String pass, String email) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.password= pass;
		this.email = email;
	}

	public User(long idUser, String name) {
		// TODO Auto-generated constructor stub
		this.idUser = idUser;
		this.nombre= name;
	}

	public User(long idUser) {
		// TODO Auto-generated constructor stub
		this.idUser = idUser;
	}

	public User(String nombre) {
		// TODO Auto-generated constructor stub
		this.nombre=nombre;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<PlayList> getPlaylistUser() {
		return playlistUser;
	}

	public void setPlaylistUser(ArrayList<PlayList> playlistUser) {
		this.playlistUser = playlistUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((playlistUser == null) ? 0 : playlistUser.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (playlistUser == null) {
			if (other.playlistUser != null)
				return false;
		} else if (!playlistUser.equals(other.playlistUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
		/*return "User [idUser=" + idUser + ", email=" + email + ", password=" + password + ", nombre=" + nombre
				+ ", playlistUser=" + playlistUser + "]";*/
	}



	
	
	

}
