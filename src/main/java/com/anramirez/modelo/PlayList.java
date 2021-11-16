package com.anramirez.modelo;

import java.util.ArrayList;
import java.util.List;

public class PlayList {

		protected Long idList;
		protected String name;
		protected String descripcion;
		protected User user;
		protected ArrayList<Song> songsPlayList;
		protected ArrayList<User> userPlayList;
		

		public PlayList(Long idList, String name, String descripcion, User user, ArrayList<Song> songsPlayList,
				ArrayList<User> userPlayList) {
			super();
			this.idList = idList;
			this.name = name;
			this.descripcion = descripcion;
			this.user = user;
			this.songsPlayList = songsPlayList;
			this.userPlayList = userPlayList;
		}

		public PlayList(Long idList, String name, String descripcion, User user, ArrayList<Song> songsPlayList) {
			super();
			this.idList = idList;
			this.name = name;
			this.descripcion = descripcion;
			this.user = user;
			this.songsPlayList = songsPlayList;
		}
		
		public PlayList(String name, User user, ArrayList<Song> songsPlayList, ArrayList<User> userPlayList) {
			super();
			this.name = name;
			this.user = user;
			this.songsPlayList = songsPlayList;
			this.userPlayList = userPlayList;
		}
		
		public PlayList(String name,User user) {
			this.name=name;
			this.user=user;
		}
		
		public PlayList(long idList, String name,User user) {
			super();
			this.idList = idList;
			this.name = name;
			this.user=user;
		}

		public PlayList() {
			super();
		}

		public PlayList(String name, User user, ArrayList<Song> songsPlayList) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.user = user;
			this.songsPlayList = songsPlayList;
		}

		public PlayList(String name, String descripcion, User user) {

			this.name = name;
			this.descripcion = descripcion;
			this.user=user;
		}

		public Long getIdList() {
			return idList;
		}

		public void setIdList(Long idList) {
			this.idList = idList;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}


		public ArrayList<Song> getSongsPlayList() {
			return songsPlayList;
		}

		public void setSongsPlayList(ArrayList<Song> songsPlayList) {
			this.songsPlayList = songsPlayList;
		}
		
		

		public ArrayList<User> getUserPlayList() {
			return userPlayList;
		}

		public void setUserPlayList(ArrayList<User> userPlayList) {
			this.userPlayList = userPlayList;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
			result = prime * result + ((idList == null) ? 0 : idList.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((songsPlayList == null) ? 0 : songsPlayList.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			result = prime * result + ((userPlayList == null) ? 0 : userPlayList.hashCode());
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
			PlayList other = (PlayList) obj;
			if (descripcion == null) {
				if (other.descripcion != null)
					return false;
			} else if (!descripcion.equals(other.descripcion))
				return false;
			if (idList == null) {
				if (other.idList != null)
					return false;
			} else if (!idList.equals(other.idList))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (songsPlayList == null) {
				if (other.songsPlayList != null)
					return false;
			} else if (!songsPlayList.equals(other.songsPlayList))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			if (userPlayList == null) {
				if (other.userPlayList != null)
					return false;
			} else if (!userPlayList.equals(other.userPlayList))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "PlayList [idList=" + idList + ", name=" + name + ", descripcion=" + descripcion + ", user=" + user
					+ ", songsPlayList=" + songsPlayList + ", userPlayList=" + userPlayList + "]";
		}

		
		
		
		
}
