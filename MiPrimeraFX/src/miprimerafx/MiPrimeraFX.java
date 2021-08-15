/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miprimerafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Aplicación sencilla que crea una ventana y pone una etiqueta en el medio de 
 * la ventana con el texto "Hola JavaFX"
 * 
 * @author Profesor
 */
public class MiPrimeraFX extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Hola JavaFX") ;
        // Crear un panel y añadirlo a la escena
        Scene scene = new Scene(new StackPane(label), 640, 480) ;
        // Añadir la escena al escenario
        stage.setScene(scene);
        // Mostrar el escenario
        stage.show(); 
    }  
}
