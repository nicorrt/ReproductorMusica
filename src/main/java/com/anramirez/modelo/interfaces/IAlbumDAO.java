package com.anramirez.modelo.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.dao.AlbumDAO;


public interface IAlbumDAO extends IDAO<AlbumDAO, Long> {
	/**
	 * Show all Albums of database
	 * @return List Album
	 */
	public List<AlbumDAO> getAllAlbums();
	
	/**
	 * Remove Album DataBase
	 * 
	 * @return Long
	 * @throws DAOException
	 */
	public Long delete() throws DAOException;
	
	/**
	 * Update Album Database
	 * @return Long
	 * @throws DAOException
	 */
	public Long update() throws DAOException;
	
	/**
	 * @return idAlbum
	 * @throws DAOException
	 */
	public AlbumDAO get(Long id) throws DAOException;
	/**
	 * @return Album
	 * @throws DAOException
	 */
	public void create(AlbumDAO a) throws DAOException;
	
	/**
	 * @param idArtist
	 * @return
	 */
	List<AlbumDAO> getAlbumsByArtist(Long idArtist);
	/**
	 * Search by filter Name
	 * @param filtro
	 * @return
	 */
	public List<AlbumDAO> getAlbumsByName(String filtro);

}
