package com.anramirez.reproductorMusica;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.Artist;
import com.anramirez.modelo.Gender;
import com.anramirez.modelo.PlayList;
import com.anramirez.modelo.Song;
import com.anramirez.modelo.User;
import com.anramirez.modelo.UserSesion;
import com.anramirez.modelo.dao.AlbumDAO;
import com.anramirez.modelo.dao.ArtistDAO;
import com.anramirez.modelo.dao.GenderDAO;
import com.anramirez.modelo.dao.PlayListDAO;
import com.anramirez.modelo.dao.SongDAO;
import com.anramirez.modelo.dao.UserDAO;
import com.anramirez.modelo.interfaces.DAOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<SongDAO> tablaCancionesGeneral;

    @FXML
    private TableColumn<SongDAO, String> ColCancionGeneral;

    @FXML
    private TableColumn<SongDAO, String> ColNombreGeneral;

    @FXML
    private TableColumn<SongDAO, String> ColDuracionGeneral;

    @FXML
    private TableColumn<SongDAO, String> ColAlbumGeneral;

    @FXML
    private TableColumn<SongDAO, String> ColGeneroGeneral;

    @FXML
    private TableView<PlayListDAO> tablaTodasPlayList;
    
    @FXML
    private TableColumn<PlayListDAO, String> colIdPlayLIST;

    @FXML
    private TableColumn<PlayListDAO, String> ColNombrePlayLIST;

    @FXML
    private TableColumn<PlayListDAO, String> ColidCreatorplayLIST;
    
    @FXML
    private TableView<PlayListDAO> tablaTodasPlayList1;
    
    @FXML
    private TableColumn<PlayListDAO, String> colIdPlayLIST1;

    @FXML
    private TableColumn<PlayListDAO, String> ColNombrePlayLIST1;

    @FXML
    private TableColumn<PlayListDAO, String> ColidCreatorplayLIST1;
    
    

    @FXML
    private TextField barraBuscarCamcionesPor;

    @FXML
    private MenuButton MenuBoton;

    @FXML
    private MenuItem BotBuscarPorDisco;

    @FXML
    private MenuItem BotonBuscarPorArtista;

    @FXML
    private MenuItem BotonBuscarPorCancion;

    @FXML
    private MenuItem BotBuscarPorGenero;

    @FXML
    private Button botSalirPrograma;

    @FXML
    private ImageView botSalir;

    @FXML
    private Button BotonPlay;

    @FXML
    private ImageView botSalir1;
    
    @FXML
    private ComboBox<User> ComboUsuario;

    @FXML
    private TableView<PlayListDAO> TablaPlayListUser;

    @FXML
    private TableColumn<PlayListDAO, String> ColIdPlayListUser;

    @FXML
    private TableColumn<PlayListDAO, String> ColNombrePlayListUser;

    @FXML
    private TableColumn<PlayListDAO, String> ColDescripciónPlayListUser;

    @FXML
    private TableColumn<PlayListDAO, String> ColidUsuarioPlayListUser;

    @FXML
    private Button AddNewPlayList;

    @FXML
    private Button DelPlayListUser;

    @FXML
    private Button UpPlayListUser;

    @FXML
    private Button botDelUsuario;

    @FXML
    private TextArea txtDescripcionPlayList;

    @FXML
    private TextField txtNombrePlayList;

    @FXML
    private TableView<SongDAO> TablaCancionesAgregar;

    @FXML
    private TableColumn<SongDAO, String> ColIdSong;

    @FXML
    private TableColumn<SongDAO, String> ColNombreSong;

    @FXML
    private TableColumn<SongDAO, String> ColDuracionSong;

    @FXML
    private TableColumn<SongDAO, String> ColDiscoSong;

    @FXML
    private TableColumn<SongDAO, String> ColGenreSong;

    @FXML
    private Button AddSongToPlayList;

    @FXML
    private Button DelSongFromPlayList;

    @FXML
    private TableView<PlayListDAO> TablaPlayListSubcripciones;

    @FXML
    private TableColumn<PlayListDAO, String> ColidListaSubcripcion;

    @FXML
    private TableColumn<PlayListDAO, String> ColNombreSubcripcion;

    @FXML
    private TableColumn<PlayListDAO, String> ColDescripcionSubcripcion;

    @FXML
    private TableColumn<PlayListDAO,String > ColidSusuarioSubcripcion;

    @FXML
    private TableColumn<PlayListDAO, String> ColCancionesSubcripcion;

    @FXML
    private Button botSubcripcion;

    @FXML
    private ImageView botDelSubscripcion;
    
    private SongDAO songOfPlayList;
    private ObservableList<SongDAO> songsPlayListy;
    private final SongDAO modelSongsPlayList = new SongDAO();
    
    private PlayListDAO playListUser1;
    private ObservableList<PlayListDAO> playLists1;
    private final PlayListDAO modelPlayList1 = new PlayListDAO();
    
    private PlayListDAO playListUser2;
    private ObservableList<PlayListDAO> playLists2;
    private final PlayListDAO modelPlayList2 = new PlayListDAO();
    
    private PlayListDAO playListSubscribe;
    private ObservableList<PlayListDAO> playSubscribe;
    private final PlayListDAO modelSubscribe = new PlayListDAO();
    
    private ObservableList<SongDAO> playListsSong;
    private final SongDAO modelPlaySong = new SongDAO();
    
    private ObservableList<PlayListDAO> playLists3;
    private final PlayListDAO modelPlayList3 = new PlayListDAO();
    
    protected User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		UserSesion userS = UserSesion.getInstance();
		user = userS.getUser();


		
		//PrimeraPestaña
		
		//Tabla de PlayList
		playLists3 = FXCollections.observableArrayList();
		ObservableList<PlayListDAO> plays = (ObservableList<PlayListDAO>) modelPlayList3.getAllPlayListSubcribedUser(user.getIdUser());
		
		this.colIdPlayLIST.setCellValueFactory(modelPlayList3->new SimpleStringProperty(modelPlayList3.getValue().getIdList()+""));
		this.ColNombrePlayLIST.setCellValueFactory(modelPlayList3->new SimpleStringProperty(modelPlayList3.getValue().getName()));
		this.ColidCreatorplayLIST.setCellValueFactory(modelPlayList3->new SimpleStringProperty(modelPlayList3.getValue().getUser()+""));
		playLists3=plays;
		this.tablaTodasPlayList.setItems(playLists3);
		
		//Segunda Pestaña
		
		playLists2 = FXCollections.observableArrayList();
		ObservableList<PlayListDAO> plays2 = (ObservableList<PlayListDAO>) modelPlayList2.getAllPlayList2();
		//ObservableList<UserDAO> lista = (ObservableList<UserDAO>) modelUser.getAllUsers();
		ComboUsuario.getItems().add(user);
		
		this.ColIdPlayListUser.setCellValueFactory(modelPlayList2->new SimpleStringProperty(modelPlayList2.getValue().getIdList()+""));
		this.ColNombrePlayListUser.setCellValueFactory(modelPlayList2->new SimpleStringProperty(modelPlayList2.getValue().getName()));
		this.ColDescripciónPlayListUser.setCellValueFactory(modelPlayList2->new SimpleStringProperty(modelPlayList2.getValue().getDescripcion()));
		this.ColidUsuarioPlayListUser.setCellValueFactory(modelPlayList2->new SimpleStringProperty(modelPlayList2.getValue().getUser()+""));
		playLists2=plays2;
		this.TablaPlayListUser.setItems(playLists2);
		
		playListsSong = FXCollections.observableArrayList();
		ObservableList<SongDAO> sonns = (ObservableList<SongDAO>) modelPlaySong.getAllSongs();
		
		this.ColIdSong.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getIdSong()+""));
		this.ColNombreSong.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getName()));
		this.ColDuracionSong.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getTimeSong()+""));
		this.ColDiscoSong.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getAlbumSong()+""));
		this.ColGenreSong.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getGenderSong()+""));
		
		playListsSong = sonns;
		this.TablaCancionesAgregar.setItems(playListsSong);
		
		//Tercera Pestaña
		
		playLists1 = FXCollections.observableArrayList();
		ObservableList<PlayListDAO> playss = (ObservableList<PlayListDAO>) modelPlayList1.getAllPlayListSubcribed();
		
		this.colIdPlayLIST1.setCellValueFactory(modelPlayList1->new SimpleStringProperty(modelPlayList1.getValue().getIdList()+""));
		this.ColNombrePlayLIST1.setCellValueFactory(modelPlayList1->new SimpleStringProperty(modelPlayList1.getValue().getName()));
		this.ColidCreatorplayLIST1.setCellValueFactory(modelPlayList1->new SimpleStringProperty(modelPlayList1.getValue().getUser()+""));
		playLists1=playss;
		this.tablaTodasPlayList1.setItems(playLists1);
		
		playSubscribe = FXCollections.observableArrayList();
		ObservableList<PlayListDAO> pSubscribe = (ObservableList<PlayListDAO>) modelSubscribe.getAllPlayList2();
		
		this.ColidListaSubcripcion.setCellValueFactory(modelSubscribe->new SimpleStringProperty(modelSubscribe.getValue().getIdList()+""));
		this.ColNombreSubcripcion.setCellValueFactory(modelSubscribe->new SimpleStringProperty(modelSubscribe.getValue().getName()));
		this.ColDescripcionSubcripcion.setCellValueFactory(modelSubscribe->new SimpleStringProperty(modelSubscribe.getValue().getDescripcion()));
		this.ColidSusuarioSubcripcion.setCellValueFactory(modelSubscribe->new SimpleStringProperty(modelSubscribe.getValue().getUser()+""));
		
		playSubscribe = pSubscribe;
		this.TablaPlayListSubcripciones.setItems(pSubscribe);
		
		
		
	}
	
	   @FXML
	    void AddListUser(ActionEvent event) {
	    	
	    	String nombre = this.txtNombrePlayList.getText();
	    	String descripcion = this.txtDescripcionPlayList.getText();
	    	User usuario = this.ComboUsuario.getValue();

	    	if(this.txtNombrePlayList.getText().length()>1&&this.txtDescripcionPlayList.getText().length()>1&&
	    			this.ComboUsuario.getValue()!=null) {
	    		PlayList a = null;
	    		if(this.playListUser2!=null&&this.playListUser2.getIdList()>0) {
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("La lista ya existe");
					alert2.showAndWait();
	    		}else {
	    			a = new PlayList(nombre,descripcion,usuario);
	    		}
	    		PlayListDAO ad = new PlayListDAO(a);
	    		if(this.playListUser2!=null&&this.playListUser2.getIdList()>0) {
	    			this.playListUser2.setName(nombre);
	    			this.playListUser2.setDescripcion(descripcion);
	    			this.playListUser2.setUser(usuario);
	    		}else {
	    			this.playListUser2=ad;
	    			try {
						ad.create(playListUser2);

					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			refreshAll();
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setHeaderText(null);
					alert2.setTitle("GUARDADO");
					alert2.setContentText("La Lista se ha incluido en la base de Datos");
					alert2.showAndWait();
	    			
	    		}
	    	}else {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setHeaderText(null);
				alert2.setTitle("ERROR");
				alert2.setContentText("Debe rellenar todos los campos");
				alert2.showAndWait();
	    	}
	   }
	   
	    @FXML
	    private void DeletePlayList(ActionEvent event) {
	    	
	    	PlayList c = this.TablaPlayListUser.getSelectionModel().getSelectedItem();
	    	
	    	if (c==null) {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Debes seleccionar un Disco");
				alert.showAndWait();
	    	}else {
	    		PlayListDAO cd = (PlayListDAO) c;
	    		this.playLists2.remove(cd);
	    		cd.delete();
	    		refreshAll();
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha borrado el Disco");
				alert.showAndWait();

	    	}

	    }
	    
	    @FXML
	    private void UpdatePlayList(ActionEvent event) {
	    	String nombre = this.txtNombrePlayList.getText();
	    	String descripcion = this.txtDescripcionPlayList.getText();
	    	User usuario = this.ComboUsuario.getValue();
	    	
	     	PlayList a = this.TablaPlayListUser.getSelectionModel().getSelectedItem(); 
	     	this.playListUser2=(PlayListDAO) a;
	     	

	         if (a == null) {         	
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setTitle("Error");
	             alert.setContentText("Debe seleccionar una obra");
	             alert.showAndWait();
	         } else {
	    			a = new PlayList(nombre,descripcion,usuario);
		    		PlayListDAO ad = this.playListUser2;		    		
		    		
		    		if(this.playListUser2.getIdList()!= null && this.playListUser2.getIdList() > 0) {
		    			
		    			this.playListUser2.setName(nombre);
		    			this.playListUser2.setDescripcion(descripcion);
		    			this.playListUser2.setUser(usuario);
	    		
		    			ad.update();
		    			refreshAll();
		                Alert alert = new Alert(Alert.AlertType.INFORMATION);
		                alert.setHeaderText(null);
		                alert.setTitle("Info");
		                alert.setContentText("Se ha modificado conrrectamente");
		                alert.showAndWait();
		    		}else {
			             Alert alert = new Alert(Alert.AlertType.ERROR);
			             alert.setHeaderText(null);
			             alert.setTitle("Error");
			             alert.setContentText("No se ha podido modificar correctamente");
			             alert.showAndWait();
		    		}
	         }

	    }
	    
		@FXML
		void addSongToList(ActionEvent event) throws DAOException {

			PlayListDAO a = this.TablaPlayListUser.getSelectionModel().getSelectedItem();
			SongDAO s = this.TablaCancionesAgregar.getSelectionModel().getSelectedItem();

			if (a == null || s == null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Debes seleccionar una lista");
				alert.showAndWait();
			} else {
				a.getIdList();
				s.getIdSong();
				a.addSong(s,a);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("La canción se ha añadido a la Lista");
				alert.showAndWait();
				this.TablaCancionesAgregar.refresh();
				this.TablaPlayListUser.refresh();

			}

		}
		
		@FXML
		void deleteSongFromList(ActionEvent event) throws DAOException {

			PlayListDAO a = this.TablaPlayListUser.getSelectionModel().getSelectedItem();
			SongDAO s =  this.TablaCancionesAgregar.getSelectionModel().getSelectedItem();
			SongDAO comparation=new SongDAO();
			ObservableList<SongDAO> lista = FXCollections.observableArrayList(comparation.getAllSongs());
			
			if (s == null || a == null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Debes seleccionar una canción y una lista");
				alert.showAndWait();
			} else if(lista.contains(s)) {
				a.removeSong(s, a);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Canción eliminada con éxito");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("La canción seleccionada no está en la lista");
				alert.showAndWait();
			}
			refreshAll();
		}
		
		@FXML
		private void seleccionar(MouseEvent event) throws DAOException{
	        PlayListDAO l = (PlayListDAO) this.tablaTodasPlayList.getSelectionModel().getSelectedItem();
	        songsPlayListy= FXCollections.observableArrayList();
	        if(l!=null) {
	        this.tablaCancionesGeneral.setItems(FXCollections.observableList(PlayListDAO.getsongsbyid(l.getIdList())));
	        ObservableList<SongDAO> items = FXCollections.observableList(SongDAO.getSongsofPlayList(l.getIdList()));

	    	this.ColCancionGeneral.setCellValueFactory(modelSongPlayList->new SimpleStringProperty(modelSongPlayList.getValue().getIdSong()+""));
			this.ColNombreGeneral.setCellValueFactory(modelSongPlayList->new SimpleStringProperty(modelSongPlayList.getValue().getName()));
			this.ColDuracionGeneral.setCellValueFactory(modelSongPlayList->new SimpleStringProperty(modelSongPlayList.getValue().getTimeSong()+""));
			this.ColAlbumGeneral.setCellValueFactory(modelSongPlayList->new SimpleStringProperty(modelSongPlayList.getValue().getAlbumSong()+""));
			this.ColGeneroGeneral.setCellValueFactory(modelSongPlayList->new SimpleStringProperty(modelSongPlayList.getValue().getGenderSong()+""));

			this.tablaCancionesGeneral.setItems(items);
	        }else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Algo no ha ido correctamente");
				alert.showAndWait();
	        }
		}
		
	    @FXML
	    void handleBack(ActionEvent event) {
			Stage stage = (Stage) this.botSalirPrograma.getScene().getWindow();
			try {
				App.setRoot("login");
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
		
	    @FXML
	    private void DeleteUser(ActionEvent event) {

	    	UserDAO u = new UserDAO();

	    		try {
					u.delete(user);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha borrado el Usuario");
				alert.showAndWait();
				try {
					App.setRoot("login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    
		@FXML
		void addSubription(ActionEvent event) throws DAOException {
			
			PlayListDAO pl = new PlayListDAO();
			
			pl = this.TablaPlayListSubcripciones.getSelectionModel().getSelectedItem();
			
			
			
			if (pl == null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Debes seleccionar una lista");
				alert.showAndWait();
			} else {
				
				pl.addSubcription(user, pl);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Se ha subscrito con éxito");
				alert.showAndWait();
				refreshAll();
			}

		}
	    
		@FXML
		void removeSubription(ActionEvent event) throws DAOException {
			
			PlayListDAO pl = new PlayListDAO();

			pl = this.TablaPlayListSubcripciones.getSelectionModel().getSelectedItem();

			if (pl == null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Debes seleccionar una lista");
				alert.showAndWait();
			} else {
				pl.removeSubcription(user, pl);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Se ha borrado la subscripción con éxito");
				alert.showAndWait();
			}
			refreshAll();

		}
		
		void refreshAll() {
			TablaCancionesAgregar.refresh();
			TablaPlayListSubcripciones.refresh();
			TablaPlayListUser.refresh();
			tablaTodasPlayList.refresh();
			tablaTodasPlayList1.refresh();
		}

}
