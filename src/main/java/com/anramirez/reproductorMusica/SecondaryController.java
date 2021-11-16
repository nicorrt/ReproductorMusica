package com.anramirez.reproductorMusica;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.anramirez.modelo.Album;
import com.anramirez.modelo.Artist;
import com.anramirez.modelo.Gender;
import com.anramirez.modelo.Song;
import com.anramirez.modelo.dao.AlbumDAO;
import com.anramirez.modelo.dao.ArtistDAO;
import com.anramirez.modelo.dao.GenderDAO;
import com.anramirez.modelo.dao.SongDAO;
import com.anramirez.modelo.interfaces.DAOException;
import com.anramirez.utils.Validadores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class SecondaryController implements Initializable {
	
    	@FXML
    	private TableView<ArtistDAO> TabArtistas;
	
	    @FXML
	    private TableColumn<ArtistDAO, String> ColIdArtista;

	    @FXML
	    private TableColumn<ArtistDAO, String> ColNombreArtista;

	    @FXML
	    private TableColumn<ArtistDAO, String> ColNacionalidadArtista;

	    @FXML
	    private TableColumn<ArtistDAO, String> ColFotoArtista;

	    @FXML
	    private TextField txtNombreArtista;

	    @FXML
	    private TextField txtNacionalidadArtista;

	    @FXML
	    private TextField txtFotoArtista;

	    @FXML
	    private ImageView botAddArtista;

	    @FXML
	    private ImageView botDelArtista;

	    @FXML
	    private ImageView botUpArtista;
	    
	    @FXML
	    private Button BotonUpdate;
	    
	    @FXML
	    private Button BotonInsertar;
	    
	    @FXML
	    private Button BotDeleteArtist;

	    @FXML
	    private Button BotVolverSecondary;
	    
	    @FXML
	    private TableView<AlbumDAO> TablaDisco;

	    @FXML
	    private TableColumn<AlbumDAO, String> ColIdDisco;

	    @FXML
	    private TableColumn<AlbumDAO, String> ColNombreDisco;

	    @FXML
	    private TableColumn<AlbumDAO, String> ColPortadaDisco;

	    @FXML
	    private TableColumn<AlbumDAO, String> ColLanzamientoDisco;

	    @FXML
	    private TableColumn<AlbumDAO, String> ColArtistaDisco;
	    
	    @FXML
	    private TextField barraBuscarArtista;

	    @FXML
	    private TextField txtNombreDisco;

	    @FXML
	    private TextField txtPortadaDisco;

	    @FXML
	    private DatePicker TimePickerLanzamiento;

	    @FXML
	    private ComboBox<Artist> ComboArtista;

	    @FXML
	    private Button BotonDelDisco;

	    @FXML
	    private Button BotonUpDisco;

	    @FXML
	    private Button BotonAddDisco;
	    
	    @FXML
	    private TextField barraBuscarDisco;

	    @FXML
	    private TableView<SongDAO> TablaCanciones;

	    @FXML
	    private TableColumn<SongDAO, String> ColIdCancion;

	    @FXML
	    private TableColumn<SongDAO, String> ColNombreCancion;

	    @FXML
	    private TableColumn<SongDAO, String> ColDuracionCancion;

	    @FXML
	    private TableColumn<SongDAO, String> ColDiscoCancion;

	    @FXML
	    private TableColumn<SongDAO, String> ColGeneroCancion;
	    
	    @FXML
	    private TextField barraBuscarCancion;
	    
	    @FXML
	    private TextField txtNombreCancion;

	    @FXML
	    private TextField txtDuracionCancion;

	    @FXML
	    private ComboBox<Gender> ComboGeneroCancion;

	    @FXML
	    private Button BotonDelCancion;

	    @FXML
	    private Button BotonUpCancion;

	    @FXML
	    private Button BotonAddCancion;

	    @FXML
	    private ComboBox<Album> ComboDiscoCancion;
	    
	    @FXML
	    private TableView<GenderDAO> TablaGenero;

	    @FXML
	    private TableColumn<GenderDAO, String> ColIdGenero;

	    @FXML
	    private TableColumn<GenderDAO, String> ColNomGenero;

	    @FXML
	    private TextField txtNombreGenero;

	    @FXML
	    private Button botDelGenero;

	    @FXML
	    private Button botUpGenero;

	    @FXML
	    private Button BotAddGenero;
	    
	    private ArtistDAO artista;
	    private ObservableList<ArtistDAO> artists;
	    private ObservableList<ArtistDAO> artistsFilter;
	    private final ArtistDAO modelArtist = new ArtistDAO();
	    
	    private AlbumDAO album;
	    private ObservableList<AlbumDAO> albums;
	    private ObservableList<AlbumDAO> albumsFilter;
	    private final AlbumDAO modelAlbum = new AlbumDAO();
	    
	    private GenderDAO genero;
	    private ObservableList<GenderDAO> genders;
	    private ObservableList<GenderDAO> gendersFilter;
	    private final GenderDAO modelGender = new GenderDAO();
	    
	    private SongDAO cancion;
	    private ObservableList<SongDAO> songs;
	    private ObservableList<SongDAO> songsFilter;
	    private final SongDAO modelSong = new SongDAO();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//Tabla Artistas
		artists = FXCollections.observableArrayList();
		ObservableList<ArtistDAO> arts = (ObservableList<ArtistDAO>) modelArtist.getAllArtists();
		artistsFilter= FXCollections.observableArrayList();

		this.ColIdArtista.setCellValueFactory(modelArtist->new SimpleStringProperty(modelArtist.getValue().getIdArtist()+""));
		this.ColNombreArtista.setCellValueFactory(modelArtist->new SimpleStringProperty(modelArtist.getValue().getName()));
		this.ColNacionalidadArtista.setCellValueFactory(modelArtist->new SimpleStringProperty(modelArtist.getValue().getNation()));
		this.ColFotoArtista.setCellValueFactory(modelArtist->new SimpleStringProperty(modelArtist.getValue().getPhotoUrl()));
		artists = arts;
		this.TabArtistas.setItems(artists);
		
		
		//Tabla De Discos
		ObservableList<ArtistDAO> lista = (ObservableList<ArtistDAO>) modelArtist.getAllArtists();
		ComboArtista.getItems().addAll(lista);
		ComboArtista.setConverter(new artistConverter());
		albums = FXCollections.observableArrayList();
		albumsFilter= FXCollections.observableArrayList();
		ObservableList<AlbumDAO> albu = null;
		
		albu = (ObservableList<AlbumDAO>) modelAlbum.getAllAlbums();
		
		this.ColIdDisco.setCellValueFactory(modelAlbum->new SimpleStringProperty(modelAlbum.getValue().getIdAlbum()+""));
		this.ColNombreDisco.setCellValueFactory(modelAlbum->new SimpleStringProperty(modelAlbum.getValue().getName()));
		this.ColPortadaDisco.setCellValueFactory(modelAlbum->new SimpleStringProperty(modelAlbum.getValue().getPhotoAlbum()));
		this.ColLanzamientoDisco.setCellValueFactory(modelAlbum->new SimpleStringProperty(modelAlbum.getValue().getAlbumRelease().toString()));
		this.ColArtistaDisco.setCellValueFactory(modelAlbum->new SimpleStringProperty(modelAlbum.getValue().getArtistAlbum()+""));
		
		
		albums = albu;
		this.TablaDisco.setItems(albums);
		
		//Tabla de Generos
		
		genders = FXCollections.observableArrayList();
		ObservableList<GenderDAO> gend = (ObservableList<GenderDAO>)modelGender.getAllGenders();
		gendersFilter= FXCollections.observableArrayList();
		
		this.ColIdGenero.setCellValueFactory(modelGender->new SimpleStringProperty(modelGender.getValue().getIdGender()+""));
		this.ColNomGenero.setCellValueFactory(modelGender->new SimpleStringProperty(modelGender.getValue().getName()));
		
		genders = gend;
		this.TablaGenero.setItems(genders);
		
		//Tabla de Canciones
		songs = FXCollections.observableArrayList();
		ObservableList<AlbumDAO> lista2 = (ObservableList<AlbumDAO>)modelAlbum.getAllAlbums();		
		ComboDiscoCancion.getItems().addAll(lista2);
		ComboDiscoCancion.setConverter(new albumConverter());
		ObservableList<GenderDAO> lista3 = (ObservableList<GenderDAO>)modelGender.getAllGenders();
		ComboGeneroCancion.getItems().addAll(lista3);
		ComboGeneroCancion.setConverter(new genderConverter());
		ObservableList<SongDAO> sonngs = null;
		songsFilter= FXCollections.observableArrayList();
		sonngs = (ObservableList<SongDAO>) modelSong.getAllSongs();
		
		

		this.ColIdCancion.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getIdSong()+""));
		this.ColNombreCancion.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getName()));
		this.ColDuracionCancion.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getTimeSong()+""));
		this.ColDiscoCancion.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getAlbumSong()+""));
		this.ColGeneroCancion.setCellValueFactory(modelSong->new SimpleStringProperty(modelSong.getValue().getGenderSong()+""));
		
		songs = sonngs;
		this.TablaCanciones.setItems(songs);
		
		
	}
	
	   @FXML
	    void AddArtist(ActionEvent event) throws DAOException {
	    	
	    	String nombre = this.txtNombreArtista.getText();
	    	String nacionalidad = this.txtNacionalidadArtista.getText();
	    	String foto = this.txtFotoArtista.getText();

	    	if(this.txtNombreArtista.getText().length()>1&&this.txtNacionalidadArtista.getText().length()>1&&
	    			this.txtFotoArtista.getText().length()>1) {
	    		Artist a = null;
	    		if(this.artista!=null&&this.artista.getIdArtist()>0) {
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("El Artista ya existe");
					alert2.showAndWait();
	    		}else {
	    			a = new Artist(nombre, nacionalidad, foto);
	    		}
	    		ArtistDAO ad = new ArtistDAO(a);
	    		if(this.artista!=null&&this.artista.getIdArtist()>0) {
	    			this.artista.setName(nombre);
	    			this.artista.setNation(nacionalidad);
	    			this.artista.setPhotoUrl(foto);
	    		}else {
	    			this.artista=ad;
	    			ad.create();
		    		this.TabArtistas.refresh();
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("El Artista se ha incluido en la base de Datos");
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
	    void AddDisco(ActionEvent event) {
	    	
	    	String nombre = this.txtNombreDisco.getText();
	    	String portada = this.txtPortadaDisco.getText();
	    	LocalDate lanzamiento = this.TimePickerLanzamiento.getValue();
	    	Artist artista = this.ComboArtista.getValue();

	    	if(this.txtNombreDisco.getText().length()>1&&this.txtPortadaDisco.getText().length()>1&&
	    			this.TimePickerLanzamiento.getValue()!=null&&this.ComboArtista.getValue()!=null) {
	    		Album a = null;
	    		if(this.album!=null&&this.album.getIdAlbum()>0) {
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("El Disco ya existe");
					alert2.showAndWait();
	    		}else {
	    			a = new Album(nombre, portada,lanzamiento, artista);
	    		}
	    		AlbumDAO ad = new AlbumDAO(a);
	    		if(this.album!=null&&this.album.getIdAlbum()>0) {
	    			this.album.setName(nombre);
	    			this.album.setPhotoAlbum(portada);
	    			this.album.setAlbumRelease(lanzamiento);
	    			this.album.setArtistAlbum(artista);
	    		}else {
	    			this.album=ad;
	    			try {
						ad.create(album);
						this.TablaDisco.refresh();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setHeaderText(null);
					alert2.setTitle("GUARDADO");
					alert2.setContentText("El Disco se ha incluido en la base de Datos");
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
	    void AddGender(ActionEvent event) throws DAOException {
	    	
	    	String nombre = this.txtNombreGenero.getText();


	    	if(this.txtNombreGenero.getText().length()>1) {
	    		Gender a = null;
	    		if(this.genero!=null&&this.genero.getIdGender()>0) {
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("El Artista ya existe");
					alert2.showAndWait();
	    		}else {
	    			a = new Gender(nombre);
	    		}
	    		GenderDAO ad = new GenderDAO(a);
	    		if(this.genero!=null&&this.genero.getIdGender()>0) {
	    			this.genero.setName(nombre);

	    		}else {
	    			this.genero=ad;
	    			ad.create();
		    		this.TablaGenero.refresh();
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setHeaderText(null);
					alert2.setTitle("INFORMACION");
					alert2.setContentText("El Género se ha incluido en la base de Datos");
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
	    void AddSong(ActionEvent event) {
	    	
	    	String nombre = this.txtNombreCancion.getText();
	    	int duracion = Validadores.checkNumericoInt(this.txtDuracionCancion.getText()+"");
	    	Album album = this.ComboDiscoCancion.getValue();
	    	Gender genero = this.ComboGeneroCancion.getValue();

	    	if(this.txtNombreCancion.getText().length()>1&&this.txtDuracionCancion.getText().length()>1&&
	    			this.ComboDiscoCancion.getValue()!=null&&this.ComboGeneroCancion.getValue()!=null) {
	    		Song a = null;
	    		if(this.cancion!=null&&this.cancion.getIdSong()>0) {
					Alert alert2 = new Alert(Alert.AlertType.ERROR);
					alert2.setHeaderText(null);
					alert2.setTitle("ERROR");
					alert2.setContentText("La Canción ya existe");
					alert2.showAndWait();
	    		}else {
	    			a = new Song(nombre, duracion,album, genero);
	    		}
	    		SongDAO ad = new SongDAO(a);
	    		if(this.cancion!=null&&this.cancion.getIdSong()>0) {
	    			this.cancion.setName(nombre);
	    			this.cancion.setTimeSong(duracion);
	    			this.cancion.setAlbumSong(album);;
	    			this.cancion.setGenderSong(genero);
	    		}else {
	    			this.cancion=ad;
	    			try {
						ad.create(cancion);
						this.TablaCanciones.refresh();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setHeaderText(null);
					alert2.setTitle("GUARDADO");
					alert2.setContentText("La canción se ha incluido en la base de Datos");
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
	    private void DeleteArtist(ActionEvent event) throws DAOException {
	    	
	    	Artist c = this.TabArtistas.getSelectionModel().getSelectedItem();
	    	
	    	if (c==null) {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Debes seleccionar un Artista");
				alert.showAndWait();
	    	}else {
	    		ArtistDAO cd = (ArtistDAO) c;
	    		this.artists.remove(cd);
	    		cd.delete();
	    		this.TabArtistas.refresh();
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha borrado el Artista");
				alert.showAndWait();

	    	}

	    }
	    
	    @FXML
	    private void DeleteAlbum(ActionEvent event) {
	    	
	    	Album c = this.TablaDisco.getSelectionModel().getSelectedItem();
	    	
	    	if (c==null) {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Debes seleccionar un Disco");
				alert.showAndWait();
	    	}else {
	    		AlbumDAO cd = (AlbumDAO) c;
	    		this.albums.remove(cd);
	    		try {
					cd.delete();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		this.TablaDisco.refresh();
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha borrado el Disco");
				alert.showAndWait();

	    	}

	    }
	    
	    @FXML
	    private void DeleteGender(ActionEvent event) throws DAOException {
	    	
	    	Gender c = this.TablaGenero.getSelectionModel().getSelectedItem();
	    	
	    	if (c==null) {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Debes seleccionar un Genero");
				alert.showAndWait();
	    	}else {
	    		GenderDAO cd = (GenderDAO) c;
	    		this.genders.remove(cd);
	    		cd.delete();
	    		this.TablaGenero.refresh();
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha borrado el Genero");
				alert.showAndWait();

	    	}

	    }
	    
	    @FXML
	    private void DeleteSong(ActionEvent event) {
	    	
	    	Song c = this.TablaCanciones.getSelectionModel().getSelectedItem();
	    	
	    	if (c==null) {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Debes seleccionar una Canción");
				alert.showAndWait();
	    	}else {
	    		SongDAO cd = (SongDAO) c;
	    		this.songs.remove(cd);
	    		cd.delete();
	    		this.TablaDisco.refresh();
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
				alert.setTitle("INFORMACION");
				alert.setContentText("Se ha borrado la Canción");
				alert.showAndWait();

	    	}

	    }
	    
	    
	    
	    @FXML
	    private void UpdateArtist(ActionEvent event) throws DAOException {
	    	String nombre = this.txtNombreArtista.getText();
	    	String nacionalidad = this.txtNacionalidadArtista.getText();
	    	String foto = this.txtFotoArtista.getText();
	    	
	     	Artist a = this.TabArtistas.getSelectionModel().getSelectedItem(); 
	     	this.artista=(ArtistDAO) a;
	     	

	         if (a == null) {         	
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setTitle("Error");
	             alert.setContentText("Debe seleccionar una obra");
	             alert.showAndWait();
	         } else {
	    			a = new Artist(this.artista.getIdArtist(), nombre, nacionalidad, foto);
		    		ArtistDAO ad = this.artista;		    		
		    		
		    		if(this.artista.getIdArtist() != null && this.artista.getIdArtist() > 0) {

		    			this.artista.setName(nombre);
		    			this.artista.setNation(nacionalidad);
		    			this.artista.setPhotoUrl(foto);
	    		
		    			ad.update();
			    		this.TabArtistas.refresh();
			    		this.txtNombreArtista.clear();
			    		this.txtNacionalidadArtista.clear();
			    		this.txtFotoArtista.clear();;
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
	    private void UpdateAlbum(ActionEvent event) {
	    	String nombre = this.txtNombreDisco.getText();
	    	String portada = this.txtPortadaDisco.getText();
	    	LocalDate lanzamiento = this.TimePickerLanzamiento.getValue();
	    	Artist artista = this.ComboArtista.getValue();
	    	
	     	Album a = this.TablaDisco.getSelectionModel().getSelectedItem(); 
	     	this.album=(AlbumDAO) a;
	     	

	         if (a == null) {         	
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setTitle("Error");
	             alert.setContentText("Debe seleccionar una obra");
	             alert.showAndWait();
	         } else {
	    			a = new Album(this.album.getIdAlbum(), nombre, portada, lanzamiento,artista);
		    		AlbumDAO ad = this.album;		    		
		    		
		    		if(this.album.getIdAlbum() != null && this.album.getIdAlbum() > 0) {
		    			
		    			this.album.setName(nombre);
		    			this.album.setPhotoAlbum(portada);
		    			this.album.setAlbumRelease(lanzamiento);
		    			this.album.setArtistAlbum(artista);    		
		    			try {
							ad.update();
							this.txtNombreArtista.clear();
							this.txtPortadaDisco.clear();
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    		this.TablaDisco.refresh();
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
	    private void UpdateGender(ActionEvent event) {
	    	String nombre = this.txtNombreGenero.getText();

	    	
	     	Gender a = this.TablaGenero.getSelectionModel().getSelectedItem(); 
	     	this.genero=(GenderDAO) a;
	     	

	         if (a == null) {         	
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setTitle("Error");
	             alert.setContentText("Debe seleccionar un genero");
	             alert.showAndWait();
	         } else {
	    			a = new Gender(this.genero.getIdGender(), nombre);
		    		GenderDAO ad = this.genero;		    		
		    		
		    		if(this.genero.getIdGender() != null && this.genero.getIdGender() > 0) {

		    			this.genero.setName(nombre);
	    		
		    			ad.update();
			    		this.TablaGenero.refresh();
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
	    private void UpdateSong(ActionEvent event) {
	    	
	    	String nombre = this.txtNombreCancion.getText();
	    	int duracion = Validadores.checkNumericoInt(this.txtDuracionCancion.getText()+"");
	    	Album album = this.ComboDiscoCancion.getValue();
	    	Gender genero = this.ComboGeneroCancion.getValue();
	    	
	     	Song a = this.TablaCanciones.getSelectionModel().getSelectedItem(); 
	     	this.cancion=(SongDAO) a;
	     	

	         if (a == null) {         	
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setTitle("Error");
	             alert.setContentText("Debe seleccionar una Canción");
	             alert.showAndWait();
	         } else {
	    			a = new Song(this.cancion.getIdSong(), nombre, duracion, album, genero);
		    		SongDAO ad = this.cancion;		    		
		    		
		    		if(this.cancion.getIdSong()!= null && this.cancion.getIdSong() > 0) {
		    			
		    			this.cancion.setName(nombre);
		    			this.cancion.setTimeSong(duracion);
		    			this.cancion.setAlbumSong(album);
		    			this.cancion.setGenderSong(genero);

	    		
		    			ad.update();
			    		this.TablaCanciones.refresh();
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
	    
	    public void initAtr(ObservableList<ArtistDAO> artists, ArtistDAO a) {
	    	this.artists= artists;
	    	this.artista = a;
	    	this.txtNombreArtista.setText(a.getName());
	    	this.txtNacionalidadArtista.setText(a.getNation());
	    	this.txtFotoArtista.setText(a.getPhotoUrl());

	    }
	    
		public class artistConverter extends StringConverter<Artist>{

			@Override
			public String toString(Artist artist) {
				// TODO Auto-generated method stub
				return artist == null ? null :artist.getName()+" "+artist.getNation()+" "+artist.getIdArtist();
			}

			@Override
			public Artist fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
		public class albumConverter extends StringConverter<Album>{

			@Override
			public String toString(Album album) {
				// TODO Auto-generated method stub
				return album == null ? null :album.getName()+" "+album.getIdAlbum();
			}

			@Override
			public Album fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
		public class genderConverter extends StringConverter<Gender>{

			@Override
			public String toString(Gender gender) {
				// TODO Auto-generated method stub
				return gender == null ? null :gender.getName()+" "+gender.getIdGender();
			}

			@Override
			public Gender fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
	    @FXML
	    private void buscarArtista(KeyEvent event) {


	    	String filtroArtists = this.barraBuscarArtista.getText();
	    	
	    	if(filtroArtists.isEmpty()) {
	    		this.TabArtistas.setItems(artists);
	    	}else {
	    		this.artistsFilter.clear();
	    		
	    		for(ArtistDAO c:this.artists) {
	    			if(c.getName().toLowerCase().contains(filtroArtists.toLowerCase())) {
	    				this.artistsFilter.add(c);
	    			}
	    		}
	    		this.TabArtistas.setItems(artistsFilter);
	    	}
	    }
	    
	    @FXML
	    private void buscarDisco(KeyEvent event) {


	    	String filtroAlbums = this.barraBuscarDisco.getText();
	    	
	    	if(filtroAlbums.isEmpty()) {
	    		this.TablaDisco.setItems(albums);
	    	}else {
	    		this.albumsFilter.clear();
	    		
	    		for(AlbumDAO c:this.albums) {
	    			if(c.getName().toLowerCase().contains(filtroAlbums.toLowerCase())) {
	    				this.albumsFilter.add(c);
	    			}
	    		}
	    		this.TablaDisco.setItems(albumsFilter);
	    	}
	    }
	    
	    @FXML
	    private void buscarCancion(KeyEvent event) {


	    	String filtroSongs = this.barraBuscarCancion.getText();
	    	
	    	if(filtroSongs.isEmpty()) {
	    		this.TablaCanciones.setItems(songs);
	    	}else {
	    		this.songsFilter.clear();
	    		
	    		for(SongDAO c:this.songs) {
	    			if(c.getName().toLowerCase().contains(filtroSongs.toLowerCase())) {
	    				this.songsFilter.add(c);
	    			}
	    		}
	    		this.TablaCanciones.setItems(songsFilter);
	    	}
	    }
	    
	    @FXML
	    void handleBack(ActionEvent event) {
			Stage stage = (Stage) this.BotVolverSecondary.getScene().getWindow();
			try {
				App.setRoot("login");
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
}