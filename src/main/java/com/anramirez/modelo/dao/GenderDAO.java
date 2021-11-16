package com.anramirez.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.anramirez.modelo.Artist;
import com.anramirez.modelo.Gender;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.modelo.interfaces.IDAO;
import com.anramirez.modelo.interfaces.IGenderDAO;
import com.anramirez.utils.Conection;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class GenderDAO extends Gender implements IGenderDAO{
	
	private static final String CREATE = "INSERT INTO genero (nombre) VALUES (?)";
	private static final String MOSTRARTODOS = "SELECT * FROM genero";
	private static final String MOSTRARCANCIONESPORGENERO = "SELECT cancion.idCancion, cancion.nombre,cancion.duracion FROM cancion,genero WHERE genero.idGenero=cancion.idGenero AND genero.idGenero=?";
	private static final String MOSTRARPORNOMBRE = "SELECT nombre FROM genero WHERE nombre=?";
	private static final String EDITAR = "UPDATE genero SET nombre=? WHERE idGenero=?";
	private static final String BORRAR = "DELETE FROM genero WHERE idGenero=?";
	private static final String GETONE = "SELECT * FROM genero WHERE idGenero=?";
	
	public GenderDAO(Long idGender, String name) {
		super(idGender, name);
	}
	
	public GenderDAO() {
		super();
	}

	public GenderDAO(Gender gender) {
		super();
		this.idGender = gender.getIdGender() ;
		this.name = gender.getName();
	}
	
	
	public static Gender getGenderByID(Long idGender) {
		Connection con=null;
		ResultSet rs=null;
	    Gender result=new Gender();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(MOSTRARCANCIONESPORGENERO);
	            ps.setLong(1, idGender);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setIdGender(rs.getLong(1));
	               result.setName(rs.getString(2));
               
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
	
	public List<GenderDAO> getAllGenders() {
		Connection con=null;
		ResultSet rs=null;
		List<GenderDAO> genders= FXCollections.observableArrayList();
		
		con = Conection.getInstance();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(MOSTRARTODOS);
	            rs =q.executeQuery();		
				while (rs.next()&&rs!=null) {
					GenderDAO genderAux = new GenderDAO();
					genderAux.setIdGender(rs.getLong("idGenero"));
					genderAux.setName(rs.getString("nombre"));
					genders.add(genderAux);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se han podido mostrar los generos de la base de datos");
				alert.showAndWait();
			}
			
		}
		
		return genders;
	}

	@Override
	public List<Gender> getAll() throws DAOException {
		PreparedStatement stat=null;
		ResultSet rs = null;
		Connection con = Conection.getInstance();
		List<Gender> result = new ArrayList<>();

		try {
			stat = con.prepareStatement(MOSTRARTODOS);
			stat.setLong(1, idGender);
			rs = stat.executeQuery();
			Gender a = new Gender();
			while(rs.next()) {
	               a.setName(rs.getString(2));
	               result.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Gender get(Long id) throws DAOException {
		Connection con=null;
		ResultSet rs=null;
	    Gender result=new Gender();
	    con = Conection.getInstance();
	      if (con != null) {
	         try {
	            PreparedStatement ps=con.prepareStatement(GETONE);
	            ps.setLong(1, idGender);
	             rs=ps.executeQuery();
	            while(rs.next()) {
	               result.setName(rs.getString(2));         
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
	public void delete(Gender a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
	    Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(BORRAR);
			stat.setLong(1, a.getIdGender());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya borrado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void create(Gender a) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		Connection con = Conection.getInstance();
		try {
			stat = con.prepareStatement(CREATE);
			stat.setLong(1, a.getIdGender());
			stat.setString(2, a.getName());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Gender a) throws DAOException {
		// TODO Auto-generated method stub
	      Connection con = Conection.getInstance();

	      if (con != null) {
	         try {
	            PreparedStatement stat=con.prepareStatement(EDITAR);
	            stat.setString(1, a.getName());
	            stat.setLong(4, a.getIdGender());
				if(stat.executeUpdate()==0) {
					throw new DAOException("Puede que no se haya modificado");
				}

	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		
	}

	@Override
	public Long getid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//USO
	public Long create() throws DAOException {

		Connection con = Conection.getInstance();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, this.name);

				
				ps.executeUpdate();
				// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					this.idGender = rs.getLong(1);
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
					alert.setContentText("No se ha podido crear el genero en la base de datos");
					alert.showAndWait();
				}
			}
		}
	
		return idGender;
		}
	
	//LO USO
	public Long delete() throws DAOException {
		int rs=0;
		Connection con = Conection.getInstance();

		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(BORRAR);
				q.setLong(1, this.idGender);
				rs =q.executeUpdate();
				this.name="";

				
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("No se ha podido borrar el genero de la base de datos");
					alert.showAndWait();
			}
		}
		return idGender;
	}
	
	
	public Long update() {

	      Connection con = Conection.getInstance();
	   		int rs = 0;
	      if (con != null) {
	         try {
	            PreparedStatement q=con.prepareStatement(EDITAR);
	            q.setString(1, this.getName());
	            q.setLong(2, getIdGender());
	            
	            rs =q.executeUpdate();
	         	
	         } catch (SQLException e) {
	         	// TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		return idGender;

	}
	
	

}
