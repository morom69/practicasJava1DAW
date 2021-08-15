/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modelo.Plaza;
import static modelo.Plaza.esNumero;
import static modelo.Plaza.existePlaza;
import static modelo.Plaza.getListaPlazas;
import static modelo.Plaza.recuperarPlaza;
import static modelo.Plaza.serializaFicheroInput;
import static modelo.Plaza.serializaFicheroOutput;
import modelo.PlazaCoche;
import modelo.PlazaMinusvalido;
import modelo.PlazaMoto;

/**
 * FXML Controller class
 *
 * @author Miguel Moro
 */
public class ParkingBajaVistaController implements Initializable {

    @FXML
    private Button btnEliminarBajas;
    @FXML
    private TextField txtNumeroDePlazaBajas;
    @FXML
    private TextField txtNombreYApellidosBajas;
    @FXML
    private TextField txtCuotaBajas;
    @FXML
    private TextField txtTelefonoBajas;
    @FXML
    private Label lblNumeroDePlazaBajas;
    @FXML
    private Label lblNombreYApellidosBajas;
    @FXML
    private Label lblCuotaBajas;
    @FXML
    private Label lblEurosBajas;
    @FXML
    private Label lblTelefonoBajas;
    @FXML
    private Label lblPagoAlDiaBajas;
    @FXML
    private RadioButton rdbPagoAlDia_Si_Bajas;
    @FXML
    private RadioButton rdbPagoAlDia_No_Bajas;
    @FXML
    private Label lblOcupadaBajas;
    @FXML
    private RadioButton rdbOcupada_Si_Bajas;
    @FXML
    private RadioButton rdbOcupada_No_Bajas;
    @FXML
    private Label lblAnchoBajas;
    @FXML
    private Label lblLargoBajas;
    @FXML
    private TextField txtAnchoBajas;
    @FXML
    private TextField txtLargoBajas;
    @FXML
    private Label lblAnchoCmsBajas;
    @FXML
    private Label lblLargoCmsBajas;
    @FXML
    private Label lblTipoCocheBajas;
    @FXML
    private Label lblAccesoPasilloBajas;
    @FXML
    private Label lblNumeroMotos_Bajas;
    @FXML
    private RadioButton rdbAccesoPasillo_Si_Bajas;
    @FXML
    private RadioButton rdbAccesoPasillo_No_Bajas;
    @FXML
    private TextField txtNumeroMotos_Bajas;
    @FXML
    private Button btnLimpiarBajas;
    @FXML
    private RadioButton rdbTipoCoche_Pequeño_Bajas;
    @FXML
    private RadioButton rdbTipoCoche_Estandar_Bajas;
    @FXML
    private RadioButton rdbTipoCoche_Grande_Bajas;
    @FXML
    private Button btnVolverBajas;
    @FXML
    private Button btnBuscarBajas;
    @FXML
    private Label lblTituloBajas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // asociamos radio button de plaza ocupada al mismo grupo
        ToggleGroup plazaOcupadaAltas = new ToggleGroup();
        this.rdbOcupada_Si_Bajas.setToggleGroup(plazaOcupadaAltas);
        this.rdbOcupada_No_Bajas.setToggleGroup(plazaOcupadaAltas);

        // asociamos radio button de pago al día al mismo grupo
        ToggleGroup pagoAlDiaAltas = new ToggleGroup();
        this.rdbPagoAlDia_Si_Bajas.setToggleGroup(pagoAlDiaAltas);
        this.rdbPagoAlDia_No_Bajas.setToggleGroup(pagoAlDiaAltas);
        // asociamos radio button de tipo de coche al mismo grupo
        ToggleGroup tipoCoche = new ToggleGroup();
        this.rdbTipoCoche_Pequeño_Bajas.setToggleGroup(tipoCoche);
        this.rdbTipoCoche_Estandar_Bajas.setToggleGroup(tipoCoche);
        this.rdbTipoCoche_Grande_Bajas.setToggleGroup(tipoCoche);

        // asociamos radio button de acceso pasillo al mismo grupo
        ToggleGroup accesoPasilloAltas = new ToggleGroup();
        this.rdbAccesoPasillo_Si_Bajas.setToggleGroup(accesoPasilloAltas);
        this.rdbAccesoPasillo_No_Bajas.setToggleGroup(accesoPasilloAltas);

        //y ocultamos la información del tipo de plaza específico
        this.lblTipoCocheBajas.setVisible(false);
        this.rdbTipoCoche_Pequeño_Bajas.setVisible(false);
        this.rdbTipoCoche_Estandar_Bajas.setVisible(false);
        this.rdbTipoCoche_Grande_Bajas.setVisible(false);
        this.lblAccesoPasilloBajas.setVisible(false);
        this.rdbAccesoPasillo_Si_Bajas.setVisible(false);
        this.rdbAccesoPasillo_No_Bajas.setVisible(false);
        this.lblNumeroMotos_Bajas.setVisible(false);
        this.txtNumeroMotos_Bajas.setVisible(false);
    }

    @FXML
    private void eliminarBajas() {
        Plaza plaza = null;
        //verificamos primero que se hayan recuperado los datos, no se debería dar la baja sin verificar los datos
        if (this.rdbOcupada_Si_Bajas.isSelected() || this.rdbOcupada_No_Bajas.isSelected()) { //si se han mostrado los datos (se ha buscado y recuperado la plaza) podemos proceder con la baja
            ArrayList<Plaza> listaPlazas = new ArrayList<>();
            listaPlazas = getListaPlazas();
            if (existePlaza(listaPlazas, Integer.parseInt(this.txtNumeroDePlazaBajas.getText()))) {

                // validamos el número de plaza
                // pedimos confirmación
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("Se dará de baja la plaza ¿Estás seguro?");
                Optional<ButtonType> action = alert.showAndWait();

                // evaluamos la confirmación
                if (action.get() == ButtonType.OK) { // hemos pulsado aceptar, realizamos la baja

                    //recuperamos la lista de plazas
                    listaPlazas = serializaFicheroInput(listaPlazas, "plazas.dat");
                    plaza = recuperarPlaza(listaPlazas, Integer.parseInt(this.txtNumeroDePlazaBajas.getText()));
                    //eliminamos nuestra plaza
                    listaPlazas.remove(plaza);
                    // guardamos los datos
                    serializaFicheroOutput(listaPlazas, "plazas.dat");

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Modificación realizada");
                    alert.setContentText("Se ha realizado la modificación. Los datos se han actualizado");
                    alert.showAndWait();
                    
                    limpiarBajas();

                } else { // hemos pulsado cancelar, no hacemos nada
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Baja cancelada");
                    alert.setContentText("Se ha cancelado la baja de la plaza. Los datos NO se han actualizado");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Plaza inexistente");
                alert.setContentText("La plaza no existe, no se puede dar de baja");
                alert.showAndWait();
            }
        } else { // si no se han mostrado los datos (si no se ha buscado y recuperado la plaza previamente) no se puede dar la baja
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Debe buscar y recuperar previamente los datos de la plaza");
            alert.showAndWait();
        }
    }

    public static Plaza buscarPlaza(int p) {
        Plaza plaza = null;
        ArrayList<Plaza> listaPlazas = new ArrayList<>();
        listaPlazas = getListaPlazas();
        if (existePlaza(listaPlazas, p)) {
            plaza = recuperarPlaza(listaPlazas, p);
        }
        return plaza;
    }

    private void mostrarPlaza(Plaza p) {
        if (p != null) {
            // mostramos sus datos genéricos
            this.txtNumeroDePlazaBajas.setText(p.getIdPlaza() + "");
            if (p.isOcupada()) {
                this.rdbOcupada_Si_Bajas.setSelected(true);
            } else {
                this.rdbOcupada_No_Bajas.setSelected(true);
            }
            this.txtNombreYApellidosBajas.setText(p.getInquilino());
            this.txtCuotaBajas.setText(p.getCuota() + "");
            this.txtTelefonoBajas.setText(p.getInquilinoTelf());
            if (p.isInquilinoPagado()) {
                this.rdbPagoAlDia_Si_Bajas.setSelected(true);
                this.rdbPagoAlDia_No_Bajas.setSelected(false);
            } else {
                this.rdbPagoAlDia_Si_Bajas.setSelected(false);
                this.rdbPagoAlDia_No_Bajas.setSelected(true);
            }
            this.txtAnchoBajas.setText(p.getAncho() + "");
            this.txtLargoBajas.setText(p.getLargo() + "");

            // y mostramos sus datos específicos
            if (p instanceof PlazaCoche) {
                this.lblTipoCocheBajas.setVisible(true);
                this.rdbTipoCoche_Pequeño_Bajas.setVisible(true);
                this.rdbTipoCoche_Estandar_Bajas.setVisible(true);
                this.rdbTipoCoche_Grande_Bajas.setVisible(true);
                switch (((PlazaCoche) p).getTipoCoche()) {
                    case 1: { // coche pequeño
                        this.rdbTipoCoche_Pequeño_Bajas.setSelected(true);
                        break;
                    }
                    case 2: { // coche estándar
                        this.rdbTipoCoche_Estandar_Bajas.setSelected(true);
                        break;
                    }
                    default: // = 3, coche grande
                        this.rdbTipoCoche_Grande_Bajas.setSelected(true);
                }
            } else if (p instanceof PlazaMoto) {
                this.lblNumeroMotos_Bajas.setVisible(true);
                this.txtNumeroMotos_Bajas.setVisible(true);
                this.txtNumeroMotos_Bajas.setText(((PlazaMoto) p).getNumeroMotos() + "");
            } else if (p instanceof PlazaMinusvalido) { // es de tipo PlazaMinusvalido
                this.lblAccesoPasilloBajas.setVisible(true);
                this.rdbAccesoPasillo_Si_Bajas.setVisible(true);
                this.rdbAccesoPasillo_No_Bajas.setVisible(true);
                if (((PlazaMinusvalido) p).getAccesoPasillo()) {
                    this.rdbAccesoPasillo_Si_Bajas.setSelected(true);
                } else {
                    this.rdbAccesoPasillo_Si_Bajas.setSelected(false);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Plaza no encontrada");
            alert.setContentText("La Plaza no existe");
            alert.showAndWait();
        }
    }

    @FXML
    private void limpiarBajas() {
        this.txtNumeroDePlazaBajas.setText(null);
        this.rdbOcupada_Si_Bajas.setSelected(false);
        this.rdbOcupada_No_Bajas.setSelected(false);
        this.txtNombreYApellidosBajas.setText(null);
        this.txtCuotaBajas.setText(null);
        this.txtTelefonoBajas.setText(null);
        this.rdbPagoAlDia_Si_Bajas.setSelected(false);
        this.rdbPagoAlDia_No_Bajas.setSelected(false);
        this.txtAnchoBajas.setText(null);
        this.txtLargoBajas.setText(null);

        this.lblTipoCocheBajas.setVisible(false);
        this.rdbTipoCoche_Pequeño_Bajas.setVisible(false);
        this.rdbTipoCoche_Estandar_Bajas.setVisible(false);
        this.rdbTipoCoche_Grande_Bajas.setVisible(false);

        this.lblAccesoPasilloBajas.setVisible(false);
        this.rdbAccesoPasillo_Si_Bajas.setVisible(false);
        this.rdbAccesoPasillo_No_Bajas.setVisible(false);

        this.lblNumeroMotos_Bajas.setVisible(false);
        this.txtNumeroMotos_Bajas.setVisible(false);
    }

    @FXML
    private void volverMenuPrincipal_DesdeBajas(ActionEvent event) {
        Stage stage = (Stage) this.btnVolverBajas.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buscarBajas(ActionEvent event) {
        Plaza plaza = null;
        if (!esNumero(this.txtNumeroDePlazaBajas.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Plaza debe ser un número");
            alert.showAndWait();
        } else {
            plaza = buscarPlaza(Integer.parseInt(this.txtNumeroDePlazaBajas.getText()));
            if (plaza == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Plaza no encontrada");
                alert.setContentText("La Plaza no existe");
                alert.showAndWait();
            } else {
                mostrarPlaza(plaza);
            }
        }
    }
}
