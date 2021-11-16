package com.anramirez.modelo.interfaces;

import java.util.ArrayList;

import com.anramirez.modelo.User;
import com.anramirez.modelo.dao.UserDAO;

public interface IUserDAO extends IDAO<User, Long> {
	
	public ArrayList<UserDAO> getArtistsByName();
	


}
