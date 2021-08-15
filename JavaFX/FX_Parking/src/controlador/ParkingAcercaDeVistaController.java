/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Miguel Moro
 */
public class ParkingAcercaDeVistaController implements Initializable {

    @FXML
    private Label lblTituloAcercaDe;
    @FXML
    private ImageView imgAcercaDe;
    @FXML
    private Text txtAcercaDe;
    @FXML
    private Button btnVolverAcercaDe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void volverMenuPrincipal_DesdeAcercaDe(ActionEvent event) {
        Stage stage = (Stage) this.btnVolverAcercaDe.getScene().getWindow();
        stage.close();
    }
}
