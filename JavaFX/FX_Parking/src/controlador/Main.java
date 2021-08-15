/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Plaza;

/**
 * FXML Controller class
 *
 * @author Miguel Moro
 */
public class Main extends Application {
    private static String fichero = "";
    private static ArrayList<Plaza> listaPlazas = new ArrayList<>();
    
    public static String getFichero(){
        return fichero;
    }
    
    public static void setFichero(String fichero){
        Main.fichero = fichero;
    }
    
    public static ArrayList<Plaza> getListaPlazas(){
        return listaPlazas;
    }
    
    public static void setListaPlazas(ArrayList<Plaza> listaPlazas){
        Main.listaPlazas = listaPlazas;
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/ParkingVista.fxml"));
            Pane ventana = (Pane) loader.load();

            // show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);

    }

}
