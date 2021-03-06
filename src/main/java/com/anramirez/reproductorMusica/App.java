package com.anramirez.reproductorMusica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.anramirez.reproductorMusica.App;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
	public static Stage roostage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 750, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
    	scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}