package com.anramirez.modelo.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Artist;
import com.anramirez.modelo.PlayList;
import com.anramirez.modelo.User;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.modelo.interfaces.IDAO;

import com.anramirez.utils.Conection;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class UserDAO extends User implements IDAO<User,Long> {
	
	private static final String CREATE = "INSERT INTO usuario(correo, contraseña, nombre) VALUES (?,?,?)";
	private static final String MOSTRARTODOS = "SELECT idUsuario, correo, nombre FROM usuario";
	private static final String MOSTRARARTISTAPORDISCO = "SELECT artista.idArtista, artista.nombre,artista.nacionalidad,artista.foto FROM artista,disco WHERE artista.idArtista=disco.idArtista AND disco.idDisco=?";
	private static final String INICIOSESION = "SELECT idUsuario, correo, contraseña, nombre FROM usuario WHERE nombre=? AND contraseña=?";
	private static final String EDITAR = "UPDATE usuario SET correo=?,contraseña=?,nombre=? WHERE idUsuario=?";
	private static final String BORRAR = "DELETE FROM usuario WHERE idUsuario=?";
	private static final String GETONE = "SELECT idUsuario, correo, nombre FROM usuario WHERE idUsuario=?";
	private static final String SUSCRIPCION = "INSERT INTO usuariolista (idUsuario, idLista) VALUES(?,?)";
	private static final String BAJASUSCRIPCION = "DELETE FROM usuario WHERE idLista=? AND idUsuario=?";

	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDAO(Long idUser, String email, String password,String nombre, ArrayList<PlayList> playlistUser) {
		super(idUser, email, password,nombre,playlistUser);

	}
	
	public UserDAO(Long idUser, String email, String password, String nombre) {
		super(idUser, email, password, nombre);
	}
	
	public UserDAO(Long idUser, String email, String nombre) {
		super(idUser, email, nombre);
	}
	
	/*public UserDAO(String nombre, String password) {
		super(nombre, password);
	}*/
	
	public UserDAO (User user) {
		this.idUser = user.getIdUser();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.playlistUser = user.getPlaylistUser();
	}

	public UserDAO(String name, String pass, String email) {
		super(name, pass,email);
	}

	@Override
	public List<User> getAll() throws DAOException {
		// TODO Auto-generated method stub

		PreparedStatement stat=null;
		ResultSet rs = null;
		Connection con = Conection.getInstance();
		List<User> result = new ArrayList<>();

		try {
			stat = con.prepareStatement(MOSTRARTODOS);
			stat.setLong(1, idUser);
			rs = stat.executeQuery();
			UserDAO a = new UserDAO();
			while(rs.next()) {
	               a.setEmail(rs.getString(2));
	               a.setNombre(rs.getString(3));
	               result.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User get(Long id) throws DAOException {
		Connection con=null;
		ResultSet rs=null;
	    User result=new User();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(GETONE);
	            ps.setLong(1, idUser);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setIdUser(rs.getLong(1));
	               result.setEmail(rs.getString(2));
	               result.setNombre(rs.getString(3));              
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
	public void delete(User a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
	    Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(BORRAR);
			stat.setLong(1, a.getIdUser());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya borrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void create(User a) throws DAOException {
		PreparedStatement stat = null;
		Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(CREATE);
			stat.setString(1, a.getEmail());
			stat.setString(2, a.getPassword());
			stat.setString(3, a.getNombre());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(User a) throws DAOException {
		// TODO Auto-generated method stub
	      Connection con = Conection.getInstance();

	      if (con != null) {
	         try {
	            PreparedStatement stat=con.prepareStatement(EDITAR);
	            stat.setString(1, a.getEmail());
	            stat.setString(2, a.getPassword());
	            stat.setString(3, a.getNombre());
				if(stat.executeUpdate()==0) {
					throw new DAOException("Puede que no se haya modificado");
				}

	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
	}
	
	public int login (String user, String pass) {
		
		Connection conn=null;
		PreparedStatement ps;
		ResultSet rs;
		int state=-1;
		
		try {
		
			conn = Conection.getInstance();
		
		if(conn!=null) {
			String sql = "Select * from usuario where binary usuario.nombre=? and usuario.contraseña=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				state= 1;
			}else {
				state= 0;
			}
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Hubo un error al conectar con la base de datos");
			alert.showAndWait();
			}
		}catch(HeadlessException | SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Hubo un error en la ejecución aquí");
			alert.showAndWait();
		}
		
	return state;
	}
	
	public int loginAdmin (String user, String pass) {
		
		Connection conn=null;
		PreparedStatement ps;
		ResultSet rs;
		int state=-1;
		
		try {
		
			conn = Conection.getInstance();
		
		if(conn!=null) {
			String sql = "Select * from usuario where binary usuario.nombre=? AND usuario.contraseña=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);			
			rs = ps.executeQuery();
			if(user.equals("Nicolas")&&pass.equals("foto")) {
				if(rs.next()) {
					state= 1;
				}else {
					state= 0;
				}
			}

		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Hubo un error al conectar con la base de datos");
			alert.showAndWait();
			}
		}catch(HeadlessException | SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Hubo un error en la ejecución aquí");
			alert.showAndWait();
		}
		
	return state;
	}
	
	public long suscribe(UserDAO user, PlayListDAO playList) {
		int rs=0;
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SUSCRIPCION);
				q.setLong(1, user.getIdUser());
				q.setLong(2, playList.getIdList());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Hubo un error en alta de la suscripción");
				alert.showAndWait();
			}

		}
		return rs;
	}
	
	public long Unsubscribe(UserDAO user, PlayListDAO playList) {
		long result=0;
		Connection con = null;
		con = Conection.getInstance();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(BAJASUSCRIPCION);
				q.setLong(1, user.getIdUser());
				q.setLong(2, playList.getIdList());
				result = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Hubo un error en la baja de la suscripción");
				alert.showAndWait();
			}

		}
		return result;
	}

	public List<UserDAO> getAllUsers() {
		Connection con=null;
		ResultSet rs=null;
		List<UserDAO> users= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					UserDAO userAux = new UserDAO();
					userAux.setIdUser(rs.getLong("idUsuario"));
					userAux.setNombre(rs.getString("nombre"));				
					users.add(userAux);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los usuarios de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return users;
	}
	
	
	public User getUserByNamePass(String name, String pass) throws DAOException {

		User result = new User();
		Connection con=null;
		con = Conection.getInstance();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(INICIOSESION);
				ps.setString(1, name);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				if (rs.next()) {
					result.setIdUser(rs.getLong("idUsuario"));
					result.setEmail(rs.getString("correo"));
					result.setPassword(rs.getString("contraseña"));
					result.setNombre(rs.getString("nombre"));

				} else {
					throw new DAOException("No se ha encontrado el Usuario");
				}
			} catch (SQLException e) {
				throw new DAOException("Error", e);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						throw new DAOException("Error", e);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						throw new DAOException("Error SQL :", e);
					}
				}
			}
		}
		return result;
	}

	
	
	

}
