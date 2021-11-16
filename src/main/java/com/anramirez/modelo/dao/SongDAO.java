package com.anramirez.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.Artist;
import com.anramirez.modelo.Gender;
import com.anramirez.modelo.PlayList;
import com.anramirez.modelo.Song;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.modelo.interfaces.IDAO;
import com.anramirez.utils.Conection;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class SongDAO extends Song implements IDAO<Song, Long> {
	
	private static final String CREATE = "INSERT INTO cancion (nombre, duracion, idDisco, idGenero) VALUES (?,?,?,?)";
	private static final String MOSTRARTODOS = "SELECT cancion.idCancion,cancion.nombre,cancion.duracion, cancion.idDisco, disco.nombre, cancion.idGenero, genero.nombre FROM cancion, genero, disco WHERE"
			+ " cancion.idDisco = disco.idDisco AND cancion.idGenero = genero.idGenero";
	private static final String MOSTRARCANCIONPORDISCO = "SELECT cancion.idCancion, cancion.nombre,cancion.duracion FROM cancion,disco WHERE cancion.idDisco=disco.idDisco AND disco.idDisco=?";
	private static final String MOSTRARPORNOMBRE = "SELECT nombre, duracion, idDisco, idGenero FROM cancion WHERE nombre=?";
	private static final String EDITAR = "UPDATE cancion SET nombre=?,duracion=?,idDisco=?,idGenero=? WHERE idCancion=?";
	private static final String BORRAR = "DELETE FROM cancion WHERE idCancion=?";
	private static final String GETONE = "SELECT * FROM cancion WHERE idCancion=?";

	public SongDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SongDAO(Long idSong, String name, int timeSong, Gender genderSong, Album albumSong, int reproduction) {
		super(idSong, name, timeSong, genderSong, albumSong, reproduction);
		// TODO Auto-generated constructor stub
	}


	public SongDAO(Song song) {
		this.idSong = song.getIdSong();
		this.name = song.getName();
		this.timeSong = song.getTimeSong();
		this.genderSong = song.getGenderSong();
		this.albumSong = song.getAlbumSong();
		this.reproduction = song.getReproduction();
	}
	
	
	public SongDAO(Long idSong, String name, int timeSong, Album albumSong, Gender genderSong) {
		// TODO Auto-generated constructor stub
	}

	public SongDAO(long idSong, String name, int timeSong, Gender genderSong, Album albumSong) {
		// TODO Auto-generated constructor stub
		this.idSong = getIdSong();
		this.name = getName();
		this.timeSong = getTimeSong();
		this.genderSong = getGenderSong();
		this.albumSong = getAlbumSong();
	}

	public static Song getAlbumByID(Long idSong) {
		Connection con=null;
		ResultSet rs=null;
	    Song result=new Song();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(MOSTRARCANCIONPORDISCO);
	            ps.setLong(1, idSong);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setIdSong(rs.getLong(1));
	               result.setName(rs.getString(2));
	               result.setTimeSong(rs.getInt(3));               
	            }
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los discos de la base de datos");
				alert.showAndWait();
	         }
	      }
	   	
	      return result;

	}
	
	public List<Song> getSongsByName(String filtro) {
		Connection con =null;
		PreparedStatement st=null;
		ResultSet rs=null;
		if(filtro!="") {
			filtro = " WHERE "+filtro;
		}
		String sql ="SELECT * FROM cancion ORDER BY nombre"+filtro+"";
		List<Song> songList = new ArrayList<Song>();
		
		con = Conection.getInstance();
		try {
			st=con.prepareStatement(sql);
			st.setString(1, filtro);
			rs=st.executeQuery();

			while(rs.next()) {
				Song s = new Song();
				s.setName(rs.getString("nombre"));
	            s.setTimeSong(rs.getInt("duracion"));
				Album a =  AlbumDAO.getAlbumByID(rs.getLong("idDisco"));
				Gender g = GenderDAO.getGenderByID(rs.getLong("idGenero"));
				s.setAlbumSong(a);
				s.setGenderSong(g);
				
				songList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return songList;
	}


	@Override
	public List<Song> getAll() throws DAOException {
		Statement st=null;
		ResultSet rs = null;
		Connection con = Conection.getInstance();
		List<Song> result = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery(MOSTRARTODOS);
			while(rs.next()) {
	               this.idSong=rs.getLong(1);
	               this.name=rs.getString(2);
	               this.timeSong=rs.getInt(3);
	               this.albumSong=AlbumDAO.getAlbumByID(idSong);
	               this.genderSong=GenderDAO.getGenderByID(idSong);
	               
	               SongDAO s = new SongDAO(idSong, name, timeSong,albumSong, genderSong);
	               result.add(s);
	               
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Error en SQL", e);
		}
		return result;
	}

	@Override
	public Song get(Long id) throws DAOException {
		Song song = new Song();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = Conection.getInstance();
			st = con.prepareStatement(GETONE);
			st.setLong(1, idSong);
			rs=st.executeQuery();
			if(rs.next()) {
				Album a =  AlbumDAO.getAlbumByID(rs.getLong("idDisco"));
				Gender g = GenderDAO.getGenderByID(rs.getLong("idGenero"));
				Song aux = new Song();
				aux.setIdSong(rs.getLong("idCancion"));
				aux.setName(rs.getString("nombre"));
				aux.setTimeSong(rs.getInt("duracion"));
				aux.setAlbumSong(a);
				aux.setGenderSong(g);
				song=aux;
			}else {
				throw new DAOException("No se ha encontrado registro");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}
		
		return song;
	}

	@Override
	public void delete(Song a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
	    Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(BORRAR);
			stat.setLong(1, a.getIdSong());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya borrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void create(Song a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(CREATE);
			
			stat.setString(1, a.getName());
			stat.setInt(2, a.getTimeSong());
			stat.setLong(3, a.getAlbumSong().getIdAlbum());
			stat.setLong(4, a.getGenderSong().getIdGender());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Song a) throws DAOException {
		// TODO Auto-generated method stub
	      Connection con = Conection.getInstance();

	      if (con != null) {
	         try {
	            PreparedStatement stat=con.prepareStatement(EDITAR);
	            stat.setString(1, a.getName());
	            stat.setInt(2, a.getTimeSong());
	            stat.setLong(3, a.getAlbumSong()!=null?this.albumSong.getIdAlbum():null);
	            stat.setLong(4, a.getGenderSong()!=null?this.getGenderSong().getIdGender():null);
	            stat.setLong(5, a.getIdSong());
				if(stat.executeUpdate()==0) {
					throw new DAOException("Puede que no se haya modificado");
				}

	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
	}
	
	public List<SongDAO> getAllSongs() {
		Connection con=null;
		ResultSet rs=null;
		List<SongDAO> songs= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					SongDAO songAux = new SongDAO();
					songAux.setIdSong(rs.getLong("idCancion"));
					songAux.setName(rs.getString("nombre"));
					songAux.setTimeSong(rs.getInt("duracion"));
					this.albumSong=new Album(rs.getLong("idDisco"));
					songAux.setAlbumSong(this.albumSong=new Album(rs.getLong("idDisco"),rs.getString("disco.nombre")));
					this.genderSong=new Gender(rs.getLong("idGenero"));
					songAux.setGenderSong(this.genderSong=new Gender(rs.getLong("idGenero"),rs.getString("genero.nombre")));

					
					songs.add(songAux);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar las canciones de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return songs;
	}
	
	public Long update() {

	      Connection con = Conection.getInstance();
	   		int rs = 0;
	      if (con != null) {
	         try {
	            PreparedStatement q=con.prepareStatement(EDITAR);
	            q.setString(1, this.getName());
	            q.setInt(2, this.getTimeSong());
	            q.setLong(3, this.getAlbumSong()!=null?this.albumSong.getIdAlbum():null);
	            q.setLong(4, this.getGenderSong()!=null?this.genderSong.getIdGender():null);
	            q.setLong(5,getIdSong());

	            rs =q.executeUpdate();
	         	
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		return idSong;

	}
	
	public Long delete() {
		int rs=0;
		Connection con = Conection.getInstance();

		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(BORRAR);
				q.setLong(1, this.idSong);
				rs =q.executeUpdate();
				this.name="";
				this.timeSong=0;
				this.albumSong=null;
				this.genderSong=null;
				
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se ha podido borrar la canción. ");
					alert.showAndWait();
			}
		}
		return idSong;
	}
	
	public static List<SongDAO> getSongsofPlayList(Long idLista) {
		Connection con = null;
		PreparedStatement st=null;
		ResultSet rs=null;
		List<SongDAO> songList = new ArrayList<SongDAO>();
		String sql ="SELECT cancion.idCancion, cancion.nombre,cancion.duracion, cancion.idDisco, cancion.idGenero FROM cancion,listacancion,lista WHERE lista.idLista=listacancion.idlista AND cancion.idCancion=listacancion.idCancion AND listacancion.idLista=? ";
		con = Conection.getInstance();
		if(con!=null) {
			try {
				st=con.prepareStatement(sql);
				st.setLong(1, idLista);
				rs=st.executeQuery();

				while(rs.next()) {
					Song s = new Song();
					s.setIdSong(rs.getLong("idCancion"));
					s.setName(rs.getString("nombre"));
		            s.setTimeSong(rs.getInt("duracion"));
					Album a =  AlbumDAO.getAlbumByID(rs.getLong("idDisco"));
					Gender g = GenderDAO.getGenderByID(rs.getLong("idGenero"));
					s.setAlbumSong(a);
					s.setGenderSong(g);
					
					songList.add(new SongDAO(s));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return songList;
		
	}
	
	
}
