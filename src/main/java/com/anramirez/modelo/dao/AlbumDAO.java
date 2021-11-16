package com.anramirez.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.Artist;
import com.anramirez.modelo.Song;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.modelo.interfaces.IAlbumDAO;
import com.anramirez.utils.Conection;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class AlbumDAO extends Album implements IAlbumDAO{
	
	private static final String CREATE = "INSERT INTO disco (idDisco,nombre,foto,fecha,idArtista) VALUES (?,?,?,?)";
	private static final String MOSTRARTODOS = "SELECT disco.idDisco, disco.nombre,disco.foto, disco.fecha, disco.idArtista, artista.nombre FROM disco, artista WHERE disco.idArtista=artista.idArtista ";
	private static final String MOSTRARPORID = "SELECT idDisco,nombre,foto,fecha,idArtista FROM disco WHERE idDisco=?";
	private static final String EDITAR = "UPDATE disco SET nombre=?,foto=?,fecha=?,idArtista=? WHERE idDisco=?";
	private static final String BORRAR = "DELETE FROM disco WHERE idDisco=?";
	private static final String MOSTRARPORARTISTA = "SELECT * FROM disco, artista WHERE artista.idArtista = disco.idArtista AND disco.idArtista = ?";
	private static final String MOSTRARADISCOPORCANCION = "SELECT disco.idDisco, disco.nombre,disco.foto,disco.fecha FROM cancion,disco WHERE disco.idDisco=cancion.idDisco AND cancion.idCancion=?";
		
	public AlbumDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumDAO(Long idAlbum, String name, LocalDate albumRelease, Artist artistAlbum) {
		super(idAlbum, name, albumRelease, artistAlbum);
		// TODO Auto-generated constructor stub
	}

	public AlbumDAO(Long idAlbum, String name, String photoAlbum, LocalDate albumRelease, Artist artistAlbum,
			ArrayList<Song> albumSongs) {
		super(idAlbum, name, photoAlbum, albumRelease, artistAlbum, albumSongs);
		// TODO Auto-generated constructor stub
	}

	public AlbumDAO(Long idAlbum, String name, String photoAlbum, LocalDate albumRelease, Artist artistAlbum) {
		super(idAlbum, name, photoAlbum, albumRelease, artistAlbum);
		// TODO Auto-generated constructor stub
	}

	public AlbumDAO(String name, String photoAlbum, LocalDate albumRelease, Artist artistAlbum) {
		super(name, photoAlbum, albumRelease, artistAlbum);
		// TODO Auto-generated constructor stub
	}

	
	public AlbumDAO (Album album) {
		
		this.idAlbum = album.getIdAlbum();
		this.name = album.getName();
		this.photoAlbum = album.getPhotoAlbum();
		this.albumRelease = album.getAlbumRelease();
		this.artistAlbum=album.getArtistAlbum();
		this.albumSongs=album.getAlbumSongs();
	}
	
	
	public static Album getAlbumByID(Long idAlbum) {
		Connection con=null;
		ResultSet rs=null;
	    Album result=new Album();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(MOSTRARADISCOPORCANCION);
	            ps.setLong(1, idAlbum);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setIdAlbum(rs.getLong(1));
	               result.setName(rs.getString(2));
	               result.setPhotoAlbum(rs.getString(3));
	               result.setAlbumRelease(rs.getDate(4).toLocalDate());	               
	            }
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los artistas de la base de datos");
				alert.showAndWait();
	         }finally {
	        	 try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	      }
	   	
	      return result;

	}

	/**
	 * Método que trae todos los discos existentes y su artista con sus 
	 * atributos excepto la lista de discos para evitar bucle.
	 * @param
	 * @return Lista de Discos
	 */
	
	@Override
	public List<AlbumDAO> getAll() throws DAOException {
		List<AlbumDAO> albums = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		Connection con=null;
		con = Conection.getInstance();
		try {
			st = con.createStatement();
			rs=st.executeQuery(MOSTRARTODOS);
			while(rs.next()) {
				this.idAlbum=rs.getLong(1);
				this.name=rs.getString(2);
				this.photoAlbum=rs.getString(3);
				this.albumRelease=rs.getDate(4).toLocalDate();
				this.artistAlbum=ArtistDAO.getArtistByID(idAlbum);
				
				AlbumDAO b = new AlbumDAO(idAlbum,name,photoAlbum,albumRelease,artistAlbum);
				albums.add(b);
			}
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOException("Error en SQL", e);
			}

		}
		return albums;
	}
	

	@Override
	public List<AlbumDAO> getAllAlbums() {
		Connection con=null;
		ResultSet rs=null;
		List<AlbumDAO> albums= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					AlbumDAO albumAux = new AlbumDAO();
					albumAux.setIdAlbum(rs.getLong("idDisco"));
					albumAux.setName(rs.getString("nombre"));
					albumAux.setPhotoAlbum(rs.getString("foto"));
					albumAux.setAlbumRelease(rs.getDate("fecha").toLocalDate());
					this.artistAlbum=new Artist(rs.getLong("idArtista"));
					albumAux.setArtistAlbum(this.artistAlbum=new Artist(rs.getLong("idArtista"), rs.getString("artista.nombre")));
					
					albums.add(albumAux);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los artistas de la base de datos");
				alert.showAndWait();
			}finally {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return albums;
	}
	
	@Override
	public Long delete() throws DAOException  {
		int rs=0;
		Connection con = Conection.getInstance();

		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(BORRAR);
				q.setLong(1, this.idAlbum);
				rs =q.executeUpdate();
				this.name="";
				this.photoAlbum="";
				this.albumRelease=null;
				this.artistAlbum=null;
				
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se ha podido borrar el disco. ");
					alert.showAndWait();
			}
		}
		return idAlbum;
	}
	
	@Override
	public Long update() throws DAOException {

	      Connection con = Conection.getInstance();
	   		int rs = 0;
	      if (con != null) {
	         try {
	            PreparedStatement q=con.prepareStatement(EDITAR);
	            q.setString(1, this.getName());
	            q.setString(2, this.getPhotoAlbum());
	            q.setDate(3, Date.valueOf(this.getAlbumRelease()));
	            q.setLong(4, this.getArtistAlbum()!=null?this.artistAlbum.getIdArtist():null);
	            q.setLong(5,getIdAlbum());

	            rs =q.executeUpdate();
	         	
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	        	 throw new DAOException("No se ha encontrado registro");
	         }
	      }
		return idAlbum;

	}
	
	@Override
	public AlbumDAO get(Long id) throws DAOException {
		AlbumDAO album = new AlbumDAO();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = Conection.getInstance();
			st = con.prepareStatement(MOSTRARPORID);
			st.setLong(1, idAlbum);
			rs=st.executeQuery();
			if(rs.next()) {
				Artist a = ArtistDAO.getArtistByID(rs.getLong("idArtista"));
				AlbumDAO aux = new AlbumDAO();
				aux.setIdAlbum(rs.getLong("idAlbum"));
				aux.setName(rs.getString("nombre"));
				aux.setPhotoAlbum(rs.getString("foto"));
				aux.setAlbumRelease(rs.getDate("fecha").toLocalDate());
				aux.setArtistAlbum(a);
				album=aux;
			}else {
				throw new DAOException("No se ha encontrado registro");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}
		
		return album;
	}
	
	//Traiga el disco con las canciones aun no esta listo
	/*public Album getWithSongs(Long id) throws DAOException {
		AlbumDAO album = new AlbumDAO();
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = Conection.getInstance();
			st = con.prepareStatement(MOSTRARPORID);
			st.setLong(1, idAlbum);
			rs=st.executeQuery();
			if(rs.next()) {
				Artist a = ArtistDAO.getArtistByID(rs.getLong("idArtista"));
				AlbumDAO aux = new AlbumDAO();
				aux.setIdAlbum(rs.getLong("idAlbum"));
				aux.setName(rs.getString("nombre"));
				aux.setPhotoAlbum(rs.getString("foto"));
				aux.setAlbumRelease(rs.getDate("fecha").toLocalDate());
				aux.setArtistAlbum(a);
				album=aux;
			}else {
				throw new DAOException("No se ha encontrado registro");
			}
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}
		
		return album;
	}*/
	
	/**
	 * Método para borrar un Disco
	 * @param Disco
	 * @return
	 */
	@Override
	/*public void delete(AlbumDAO a) throws DAOException {
		PreparedStatement ps = null;
		Connection con=null;
		try {
			con = Conection.getInstance();
			ps = con.prepareStatement(BORRAR);
			ps.setLong(1, a.getIdAlbum());
			if(ps.executeUpdate()==0) {
				throw new DAOException("Puede que el Album no se haya borrado");
			}
		} catch (SQLException e) {
			throw new DAOException("Error de SQL", e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException("Error de SQL", e);
				}
			}
		}		
	}*/
	
	
	/***
	 * 	Método para crear un Album
	 * @param Album
	 * @return void
	 */
	public void create(AlbumDAO a) throws DAOException {
		Statement st = null;
		Connection con=null;
		String sql="INSERT INTO disco (`nombre`, `foto`, `fecha`, `idArtista`)  values ('"+this.getName()+"', "
				+ "'"+this.getPhotoAlbum()+"',' "+this.getAlbumRelease()+"', '"+this.getArtistAlbum().getIdArtist()+"')";
			try {
				con = Conection.getInstance();
				st= con.createStatement();
				st.executeUpdate(sql);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOException("Error en SQL", e);
			} finally {
				if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DAOException("Error en SQL", e);
				}
				}
			}	
	}
	
	/**
	 * Método para actualizar un Disco
	 * @param Album
	 * @return void
	 */

	/*public void update(AlbumDAO a) throws DAOException {
		PreparedStatement ps = null;
		Connection con=null;
		try {
			con = Conection.getInstance();
			ps = con.prepareStatement(EDITAR);
			ps.setString(1,a.getName());
			ps.setString(2,a.getPhotoAlbum());
			ps.setDate(3,Date.valueOf(a.getAlbumRelease()));
			ps.setLong(4, a.getArtistAlbum()!=null?this.artistAlbum.getIdArtist():null);
			ps.setLong(5, a.getIdAlbum());
			if (ps.executeUpdate()==0) {
				throw new DAOException ("Puede que no se haya guardado.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Error en SQL", e);
		} finally {
			if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DAOException("Error en SQL", e);
				}
			}
		}		
		
	}*/

	@Override
	public List<AlbumDAO> getAlbumsByArtist(Long idArtist) {
			List<AlbumDAO> result = new ArrayList<AlbumDAO>();
			Connection con = Conection.getInstance();
			
			if(con!=null) {
				try {
					PreparedStatement q = con.prepareStatement(MOSTRARPORARTISTA);
					q.setLong(1, idArtist);
					ResultSet rs = q.executeQuery();
					while(rs.next()) {
						AlbumDAO a = new AlbumDAO();
						a.setIdAlbum(rs.getLong("idDisco"));
						a.setName(rs.getString("nombre"));
						a.setPhotoAlbum(rs.getString("foto"));
						a.setAlbumRelease(rs.getDate("fecha").toLocalDate());		
						
						ArtistDAO b = new ArtistDAO();
						b.setIdArtist(rs.getLong("idArtista"));
						b.setName(rs.getString("nombre"));
						b.setNation(rs.getString("nacionalidad"));
						b.setPhotoUrl(rs.getString("foto"));
						
						a.setArtistAlbum(b);
						result.add(a);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return result;
	}

	@Override
	public List<AlbumDAO> getAlbumsByName(String filtro) {
		Connection con =null;
		PreparedStatement st=null;
		ResultSet rs=null;
		if(filtro!="") {
			filtro = " where "+filtro;
		}
		String sql ="SELECT * FROM disco ORDER BY nombre"+filtro+"";
		List<AlbumDAO> albumList = new ArrayList<AlbumDAO>();
		
		con = Conection.getInstance();
		try {
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()) {
				AlbumDAO a = new AlbumDAO();
				a.setName(rs.getString("nombre"));
				a.setPhotoAlbum(rs.getString("foto"));
				a.setAlbumRelease(rs.getDate("fecha").toLocalDate());
				this.artistAlbum=ArtistDAO.getArtistByID(rs.getLong("idArtista"));
				
				albumList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return albumList;
	}


	@Override
	public void delete(AlbumDAO a) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AlbumDAO a) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	


	
}
