package com.anramirez.modelo.dao;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.anramirez.modelo.Reproduction;
import com.anramirez.modelo.Song;
import com.anramirez.modelo.User;

public class ReproductionDAO extends Reproduction {

	protected ReproductionDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected ReproductionDAO(Long idReproducction, User user, Song song, Timestamp reproducction) {
		super(idReproducction, user, song, reproducction);
		// TODO Auto-generated constructor stub
	}

	protected ReproductionDAO(Long idReproducction, User user, Song song) {
		super(idReproducction, user, song);
		// TODO Auto-generated constructor stub
	}
	
	protected ReproductionDAO(Reproduction reproduction) {
		this.idReproducction = reproduction.getIdReproducction();
		this.user = reproduction.getUser();
		this.song = reproduction.getSong();
		this.reproducction = reproduction.getReproducction();
	}
	
	
}
