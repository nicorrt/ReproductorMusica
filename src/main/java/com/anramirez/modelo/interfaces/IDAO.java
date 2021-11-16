package com.anramirez.modelo.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Artist;

public interface IDAO<T,k> {
	
	List<T> getAll() throws DAOException;
	
	T get(Long id) throws DAOException;
	
	void delete(T a) throws DAOException;
	
	void create(T a) throws DAOException;
	
	void update(T a) throws DAOException;

}
