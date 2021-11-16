module com.anramirez.reproductorMusica {
    requires javafx.controls;
    requires javafx.fxml;
	requires transitive java.sql;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.base;
	requires java.desktop;
	requires java.xml.bind;
	requires java.xml;
	requires transitive javafx.swing;

    opens com.anramirez.reproductorMusica to javafx.fxml;
    opens com.anramirez.modelo to javafx.base;
    opens com.anramirez.utils to java.xml.bind;

    exports com.anramirez.reproductorMusica;
    exports com.anramirez.utils;
    exports com.anramirez.modelo;

}
