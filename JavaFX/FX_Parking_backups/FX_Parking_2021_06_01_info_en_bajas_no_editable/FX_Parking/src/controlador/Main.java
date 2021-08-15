/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.CargaDatosPrueba;
import modelo.Plaza;
import static modelo.Plaza.serializaFicheroInput;
import static modelo.Plaza.serializaFicheroOutput;
import modelo.PlazaCoche;
import modelo.PlazaMinusvalido;
import modelo.PlazaMoto;

public class Main extends Application {

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

        ArrayList<Plaza> listaPlazas = new ArrayList<>();
        
        // ****************** PARA REALIZAR CARGA DESDE CLASE CargaDatosPrueba DESCOMENTAR ESTAS DOS LÍNEAS ************
        //CargaDatosPrueba.cargaDatosPrueba(listaPlazas);
        //serializaFicheroOutput(listaPlazas, "plazas.dat");
        
        // ****************** PARA REALIZAR CARGA DESDE CLASE CargaDatosPrueba COMENTAR ESTA LÍNEA *********************
        listaPlazas = serializaFicheroInput(listaPlazas, "plazas.dat");

        launch(args);

    }

}
