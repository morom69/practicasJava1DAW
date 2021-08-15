/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Plaza;

/**
 * FXML Controller class
 *
 * @author Miguel Moro
 */
public class ParkingListadoVistaController implements Initializable {

    @FXML
    private TableView<Plaza> tblListadoPlazas;
    @FXML
    private TableColumn colPlaza;
    @FXML
    private TableColumn colOcupada;
    @FXML
    private TableColumn colInquilino;
    @FXML
    private TableColumn colCuota;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private Label lblListadoPlazas;
    @FXML
    private Button btnVolverMenuPrincipal;

    private ObservableList<Plaza> plazas;
    @FXML
    private Button btnVerListado;
    @FXML
    private RadioButton rdbTodasLasPlazas;
    @FXML
    private RadioButton rdbPlazasLibres;
    @FXML
    private RadioButton rdbPlazasOcupadas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // asociamos radio button de pago al día al mismo grupo
        ToggleGroup tipoListado = new ToggleGroup();
        this.rdbTodasLasPlazas.setToggleGroup(tipoListado);
        this.rdbPlazasLibres.setToggleGroup(tipoListado);
        this.rdbPlazasOcupadas.setToggleGroup(tipoListado);

        this.rdbTodasLasPlazas.setSelected(true);

        plazas = FXCollections.observableArrayList();
        this.tblListadoPlazas.setItems(plazas);

        this.colPlaza.setCellValueFactory(new PropertyValueFactory("idPlaza"));
        this.colOcupada.setCellValueFactory(new PropertyValueFactory("ocupada"));
        this.colInquilino.setCellValueFactory(new PropertyValueFactory("inquilino"));
        this.colCuota.setCellValueFactory(new PropertyValueFactory("cuota"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory("inquilinoTelf"));
    }

    @FXML
    private void verListado(ActionEvent event) {

        ArrayList<Plaza> listaPlazas = Main.getListaPlazas();

        this.tblListadoPlazas.getItems().clear();
        
        for (Plaza p : listaPlazas) {
            if ((p.isOcupada() && (this.rdbTodasLasPlazas.isSelected() || this.rdbPlazasOcupadas.isSelected())) || (!p.isOcupada() && (this.rdbTodasLasPlazas.isSelected() || this.rdbPlazasLibres.isSelected()))) {
                this.plazas.add(p);
                this.tblListadoPlazas.setItems(plazas);
            }
        }
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        Stage stage = (Stage) this.btnVolverMenuPrincipal.getScene().getWindow();
        stage.close();
    }
}
