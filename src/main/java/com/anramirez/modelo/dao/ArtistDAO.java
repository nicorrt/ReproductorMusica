package com.anramirez.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.Artist;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.modelo.interfaces.IArtistDAO;
import com.anramirez.utils.Conection;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class ArtistDAO extends Artist implements IArtistDAO {
	
	private static final String CREATE = "INSERT INTO artista(nombre, nacionalidad, foto) VALUES (?,?,?)";
	private static final String MOSTRARTODOS = "SELECT * FROM artista";
	private static final String MOSTRARARTISTAPORDISCO = "SELECT artista.idArtista, artista.nombre,artista.nacionalidad,artista.foto FROM artista,disco WHERE artista.idArtista=disco.idArtista AND disco.idDisco=?";
	private static final String MOSTRARPORNOMBRE = "SELECT idArtista, nombre, nacionalidad, foto FROM artista WHERE nombre=?";
	private static final String EDITAR = "UPDATE artista SET nombre=?,nacionalidad=?,foto=? WHERE idArtista=?";
	private static final String BORRAR = "DELETE FROM artista WHERE idArtista=?";
	private static final String GETONE = "SELECT * FROM artista WHERE idArtista=?";
	

	
	public ArtistDAO(Long idArtist, String name, String nation, String photoUrl, ArrayList<Album> albumArtist) {
		super(idArtist, name, nation, photoUrl, albumArtist);
	}
	
	public ArtistDAO() {
		super();
	}
	
	public ArtistDAO (Artist artist) {		
		this.idArtist = artist.getIdArtist();
		this.name = artist.getName();
		this.nation = artist.getNation();
		this.photoUrl = artist.getPhotoUrl();
		this.albumArtist=artist.getAlbumArtist();
	}
	

	public ArtistDAO(String artist) {
	}
	public ArtistDAO(long idArtist) {
	}

	public ArtistDAO(String name, String nation, String photoUrl) {
		super(name, nation, photoUrl);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param idArtist
	 * @return Artist
	 */
	public static Artist getArtistByID(Long idArtist) {
		Connection con=null;
		ResultSet rs=null;
	    Artist result=new Artist();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(MOSTRARARTISTAPORDISCO);
	            ps.setLong(1, idArtist);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setIdArtist(rs.getLong(1));
	               result.setName(rs.getString(2));
	               result.setNation(rs.getString(3));
	               result.setPhotoUrl(rs.getString(4));	               
	            }
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los artistas de la base de datos");
				alert.showAndWait();
	         }
	      }   	
	      return result;
	}

	@Override
	public ArrayList<ArtistDAO> getArtistsByName() {
		Connection con=null;
		ResultSet rs=null;
		ArrayList<ArtistDAO> artists=new ArrayList<>();
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement ps=con.prepareStatement(MOSTRARPORNOMBRE);
				ps.setString(1, name);
	            rs =ps.executeQuery();	
				while (rs.next()&&rs!=null) {
					Artist artistAux = new Artist();
					artistAux.setIdArtist(rs.getLong(1));
					artistAux.setName(rs.getString(2));
					artistAux.setNation(rs.getString(3));
					artistAux.setPhotoUrl(rs.getString(4));
					artists.add((ArtistDAO) artistAux);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los artistas de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return artists;
	
	}

	
	public Long delete() throws DAOException {
		int rs=0;
		Connection con = Conection.getInstance();

		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(BORRAR);
				q.setLong(1, this.idArtist);
				rs =q.executeUpdate();
				this.name="";
				this.nation="";
				this.photoUrl="";
				this.albumArtist=null;
				
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se han podido mostrar los artistas de la base de datos");
					alert.showAndWait();
			}
		}
		return idArtist;
	}


	public Long create() throws DAOException {

		Connection con = Conection.getInstance();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, this.name);
				ps.setString(2, this.nation);
	            ps.setString(3, this.photoUrl);;
				
				ps.executeUpdate();
				// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					this.idArtist = rs.getLong(1);
				}
				// fin de extraer el id generado automaticamente en la db
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				}catch (SQLException e) {
					// TODO: handle exception
		 			Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se ha podido crear el Disco en la base de datos");
					alert.showAndWait();
				}
			}
		}
	
		return idArtist;
		}
	

	public Long update() throws DAOException {

	      Connection con = Conection.getInstance();
	   		int rs = 0;
	      if (con != null) {
	         try {
	            PreparedStatement q=con.prepareStatement(EDITAR);
	            q.setString(1, this.getName());
	            q.setString(2, this.getNation());
	            q.setString(3, this.getPhotoUrl());
	            q.setLong(4, getIdArtist());
	            
	            rs =q.executeUpdate();
	         	
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		return idArtist;

	}

	public List<ArtistDAO> getAllArtists() {
		Connection con=null;
		ResultSet rs=null;
		List<ArtistDAO> artists= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					ArtistDAO artistAux = new ArtistDAO();
					artistAux.setIdArtist(rs.getLong("idArtista"));
					artistAux.setName(rs.getString("nombre"));
					artistAux.setNation(rs.getString("nacionalidad"));
					artistAux.setPhotoUrl(rs.getString("foto"));
					artists.add(artistAux);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los artistas de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return artists;
	}
	
	

	@Override
	public List<ArtistDAO> getAll() throws DAOException {
		// TODO Auto-generated method stub

		PreparedStatement stat=null;
		ResultSet rs = null;
		Connection con = Conection.getInstance();
		List<ArtistDAO> result = new ArrayList<>();

		try {
			stat = con.prepareStatement(MOSTRARTODOS);
			stat.setLong(1, idArtist);
			rs = stat.executeQuery();
			Artist a = new Artist();
			while(rs.next()) {
	               a.setIdArtist(rs.getLong(2));
	               a.setName(rs.getString(3));
	               a.setNation(rs.getString(4));
	               a.setPhotoUrl(rs.getString(5));	 
	               result.add((ArtistDAO) a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public ArtistDAO get(Long id) throws DAOException {
		Connection con=null;
		ResultSet rs=null;
	    Artist result=new Artist();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(GETONE);
	            ps.setLong(1, idArtist);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setIdArtist(rs.getLong(1));
	               result.setName(rs.getString(2));
	               result.setNation(rs.getString(3));
	               result.setPhotoUrl(rs.getString(4));	               
	            }
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los artistas de la base de datos");
				alert.showAndWait();
	         }
	      }
	   	
	      return (ArtistDAO) result;
	}

	@Override
	public void delete(ArtistDAO a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
	    Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(BORRAR);
			stat.setLong(1, a.getIdArtist());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya borrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void create(ArtistDAO a) throws DAOException {	
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(CREATE);
			stat.setLong(1, a.getIdArtist());
			stat.setString(2, a.getName());
			stat.setString(3, a.getNation());
			stat.setString(4, a.getPhotoUrl());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void update(ArtistDAO a) throws DAOException {	
		// TODO Auto-generated method stub
	      Connection con = Conection.getInstance();

	      if (con != null) {
	         try {
	            PreparedStatement stat=con.prepareStatement(EDITAR);
	            stat.setString(1, a.getName());
	            stat.setString(2, a.getNation());
	            stat.setString(3, a.getPhotoUrl());
	            stat.setLong(4, a.getIdArtist());
				if(stat.executeUpdate()==0) {
					throw new DAOException("Puede que no se haya modificado");
				}

	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
	}

	

}
