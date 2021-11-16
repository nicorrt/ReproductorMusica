package com.anramirez.modelo.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Artist;
import com.anramirez.modelo.dao.ArtistDAO;


public interface IArtistDAO extends IDAO<ArtistDAO,Long>{

	public List<ArtistDAO> getArtistsByName();
	public Long delete() throws DAOException;
	public Long create() throws DAOException;
	public Long update() throws DAOException;
	public List<ArtistDAO> getAllArtists();


}
