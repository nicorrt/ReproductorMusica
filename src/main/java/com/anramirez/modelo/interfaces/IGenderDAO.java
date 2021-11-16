package com.anramirez.modelo.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Gender;
import com.anramirez.modelo.dao.GenderDAO;

public interface IGenderDAO extends IDAO<Gender, Long> {
	
	String getName();
	Long getid();
	public Long create() throws DAOException;
	public Long delete() throws DAOException;

}
