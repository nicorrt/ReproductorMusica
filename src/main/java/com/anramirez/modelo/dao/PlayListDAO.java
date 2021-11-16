package com.anramirez.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.Artist;
import com.anramirez.modelo.Gender;
import com.anramirez.modelo.PlayList;
import com.anramirez.modelo.Song;
import com.anramirez.modelo.User;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.modelo.interfaces.IDAO;
import com.anramirez.utils.Conection;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class PlayListDAO extends PlayList implements IDAO<PlayList,Long> {
	
	private static final String CREATE = "INSERT INTO lista(nombre, descripcion, idUsuario) VALUES (?,?,?)";
	private static final String MOSTRARTODOS = "SELECT lista.idLista, lista.nombre, lista.idUsuario, usuario.nombre FROM lista, usuario WHERE lista.idUsuario=usuario.idUsuario";
	private static final String MOSTRARTODOS2 = "SELECT lista.idLista, lista.nombre, lista.descripcion,lista.idUsuario, usuario.nombre FROM lista, usuario WHERE lista.idUsuario=usuario.idUsuario";
	private static final String INSERTARCANCION = "INSERT INTO listacancion (idCancion,idLista) VALUES (?,?)";
	private static final String BORRARCANCION = "DELETE FROM listacancion WHERE idCancion=? AND idLista=?";
	private static final String EDITAR = "UPDATE lista SET nombre=?,descripcion=?,idUsuario=? WHERE idLista=?";
	private static final String BORRAR = "DELETE FROM lista WHERE idLista=?";
	private static final String GETONE = "SELECT * FROM lista WHERE idLista=?";
	private static final String MOSTRARSUBCRIPCION = "SELECT lista.idLista, lista.nombre, lista.idUsuario, usuario.nombre FROM lista, usariolista,usuario WHERE usariolista.idUsuario=usuario.idUsuario AND usariolista.idLista=lista.idLista";
	private static final String MOSTRARSUBCRITAS = "SELECT lista.idLista, lista.nombre, lista.idUsuario, usuario.nombre FROM lista, usariolista,usuario WHERE usariolista.idUsuario=usuario.idUsuario AND usariolista.idLista=lista.idLista AND usuario.idUsuario=?" ;
	private final static String AÑADIRPLAYLIST = "INSERT INTO usariolista (idUsuario, idLista) VALUES (?,?)";
	private final static String BORRARPLAYLIST = "DELETE FROM usariolista WHERE idUsuario=? AND idLista=?";
	private static final String GETSONGS = "SELECT cancion.idCancion, cancion.nombre, cancion.duracion, cancion.idDisco,cancion.idGenero  FROM lista, listacancion, cancion WHERE "
			+ "lista.idLista=listacancion.idlista AND cancion.idCancion=listacancion.idCancion AND lista.idLista=?";

	public PlayListDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayListDAO(Long idList, String name, String descripcion, User user, ArrayList<Song> songsPlayList,
			ArrayList<User> userPlayList) {
		super(idList, name, descripcion, user, songsPlayList, userPlayList);
		// TODO Auto-generated constructor stub
	}

	public PlayListDAO(Long idList, String name, String descripcion, User user, ArrayList<Song> songsPlayList) {
		super(idList, name, descripcion, user, songsPlayList);
		// TODO Auto-generated constructor stub
	}
	
	public PlayListDAO(String name, User creator, ArrayList<Song> songsPlayList) {
		super(name, creator, songsPlayList);
		// TODO Auto-generated constructor stub
	}
	
	public PlayListDAO(PlayList playList) {
		
		this.idList = playList.getIdList();
		this.name = playList.getName();
		this.descripcion = playList.getDescripcion();
		this.user = playList.getUser();
		this.songsPlayList = playList.getSongsPlayList();
		this.userPlayList = playList.getUserPlayList();
	}
	

	@Override
	public List<PlayList> getAll() throws DAOException {
		PreparedStatement stat=null;
		ResultSet rs = null;
		Connection con = Conection.getInstance();
		List<PlayList> result = new ArrayList<>();

		try {
			stat = con.prepareStatement(MOSTRARTODOS);
			stat.setLong(1, idList);
			rs = stat.executeQuery();
			PlayList a = new PlayList();
			while(rs.next()) {
	               a.setIdList(rs.getLong(2));
	               a.setName(rs.getString(3));
	               a.setDescripcion(rs.getString(4));
	               result.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PlayList get(Long id) throws DAOException {
		Connection con=null;
		ResultSet rs=null;
	    PlayList result=new PlayList();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(GETONE);
	            ps.setLong(1, idList);
	             rs=ps.executeQuery();
	            while(rs.next()) {
		               result.setIdList(rs.getLong(2));
		               result.setName(rs.getString(3));
		               result.setDescripcion(rs.getString(4));
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
	public void delete(PlayList a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
	    Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(BORRAR);
			stat.setLong(1, a.getIdList());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya borrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void create(PlayList a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(CREATE);
			stat.setString(1, a.getName());
			stat.setString(2, a.getDescripcion());
			stat.setLong(3, this.getUser()!=null?this.user.getIdUser():null);
			
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(PlayList a) throws DAOException {
		// TODO Auto-generated method stub
	      Connection con = Conection.getInstance();

	      if (con != null) {
	         try {
	            PreparedStatement stat=con.prepareStatement(EDITAR);
	            stat.setString(1, a.getName());
	            stat.setString(2, a.getDescripcion());
				if(stat.executeUpdate()==0) {
					throw new DAOException("Puede que no se haya modificado");
				}

	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
	}
	

	public int addSong(SongDAO song, PlayListDAO playList) {
		int rs = 0;
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTARCANCION);
				q.setLong(1, song.getIdSong());
				q.setLong(2, playList.getIdList());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rs;

	}


	public int removeSong(SongDAO song,PlayListDAO playList) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(BORRARCANCION);
				q.setLong(1, song.getIdSong());
				q.setLong(2, playList.getIdList());
				rs = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		

	}
	
	public int addSubcription(User user, PlayListDAO playList) {
		int rs = 0;
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(AÑADIRPLAYLIST);
				q.setLong(1, user.getIdUser());
				q.setLong(2, playList.getIdList());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rs;

	}
	
	public int removeSubcription(User user,PlayListDAO playList) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(BORRARPLAYLIST);
				q.setLong(1, user.getIdUser());
				q.setLong(2, playList.getIdList());
				rs = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		

	}
	
	//USANDOSE
	
	public List<PlayListDAO> getAllPlayList() {
		Connection con=null;
		ResultSet rs=null;
		List<PlayListDAO> playLists= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					PlayListDAO playListAux = new PlayListDAO();
					playListAux.setIdList(rs.getLong("idLista"));
					playListAux.setName(rs.getString("nombre"));
					playListAux.setUser(this.user=new User(rs.getLong("idUsuario"), rs.getString("usuario.nombre")));
					
					playLists.add(playListAux);
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
		
		return playLists;
	}
	
	public List<PlayListDAO> getAllPlayList2() {
		Connection con=null;
		ResultSet rs=null;
		List<PlayListDAO> playLists= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS2);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					PlayListDAO playListAux = new PlayListDAO();
					playListAux.setIdList(rs.getLong("idLista"));
					playListAux.setName(rs.getString("nombre"));
					playListAux.setDescripcion(rs.getString("descripcion"));
					playListAux.setUser(this.user=new User(rs.getLong("idUsuario"), rs.getString("usuario.nombre")));
					//playListAux.setUser(this.user=new User(rs.getString("nombre")));

					
					playLists.add(playListAux);
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
		
		return playLists;
	}
	//USO
	public Long update() {

	      Connection con = Conection.getInstance();
	   		int rs = 0;
	      if (con != null) {
	         try {
	            PreparedStatement q=con.prepareStatement(EDITAR);
	            q.setString(1, this.getName());
	            q.setString(2, this.getDescripcion());
	            q.setLong(3, this.getUser()!=null?this.getUser().getIdUser():null);
	            q.setLong(4,getIdList());

	            rs =q.executeUpdate();
	         	
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		return idList;

	}
	//USO
	public Long delete() {
		int rs=0;
		Connection con = Conection.getInstance();

		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(BORRAR);
				q.setLong(1, this.idList);
				rs =q.executeUpdate();
				this.name="";
				this.descripcion="";
				this.user=null;
				
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se ha podido borrar el disco. ");
					alert.showAndWait();
			}
		}
		return idList;
	}
	
	
	public static List<SongDAO> getsongsbyid(Long idSong) throws DAOException {
		List<SongDAO> songs = new ArrayList<>();
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			PreparedStatement q;
			try {
				q = con.prepareStatement(GETSONGS);
				q.setLong(1, idSong);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					GenderDAO gd = new GenderDAO();
					Gender g = GenderDAO.getGenderByID(rs.getLong("idGenero"));

					AlbumDAO dd = new AlbumDAO();
					Album d = AlbumDAO.getAlbumByID(rs.getLong("idDisco"));

					songs.add(new SongDAO(rs.getLong("idCancion"), rs.getString("nombre"), rs.getInt("duracion"), g, d));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new DAOException(e);
			}

		}
		return songs;
	}
	
	public List<PlayListDAO> getAllPlayListSubcribed() {
		Connection con=null;
		ResultSet rs=null;
		List<PlayListDAO> playLists= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARSUBCRIPCION);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					PlayListDAO playListAux = new PlayListDAO();
					playListAux.setIdList(rs.getLong("idLista"));
					playListAux.setName(rs.getString("nombre"));
					playListAux.setUser(this.user=new User(rs.getLong("idUsuario"), rs.getString("usuario.nombre")));
					
					playLists.add(playListAux);
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
		return playLists;
	}
	
	public List<PlayListDAO> getAllPlayListSubcribedUser(Long idUser) {
		Connection con=null;
		ResultSet rs=null;
		List<PlayListDAO> playLists= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARSUBCRITAS);
				q.setLong(1, idUser);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					PlayListDAO playListAux = new PlayListDAO();
					playListAux.setIdList(rs.getLong("idLista"));
					playListAux.setName(rs.getString("nombre"));
					playListAux.setUser(this.user=new User(rs.getLong("idUsuario"), rs.getString("usuario.nombre")));
					
					playLists.add(playListAux);
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
		return playLists;
	}

}
