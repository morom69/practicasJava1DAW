/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Plaza;
import static modelo.Plaza.esNumero;
import static modelo.Plaza.existePlaza;
import static modelo.Plaza.getListaPlazas;
import static modelo.Plaza.recuperarPlaza;
import static modelo.Plaza.serializaFicheroOutput;
import static modelo.Plaza.validarTelefono;
import modelo.PlazaCoche;
import modelo.PlazaMinusvalido;
import modelo.PlazaMoto;

/**
 * FXML Controller class
 *
 * @author Miguel Moro
 */
public class ParkingVistaController implements Initializable {

    @FXML
    private TextField txtNumeroDePlaza;
    @FXML
    private TextField txtNombreYApellidos;
    @FXML
    private TextField txtCuota;
    @FXML
    private TextField txtTelefono;
    @FXML
    private RadioButton rdbPagoAlDia_Si;
    @FXML
    private RadioButton rdbPagoAlDia_No;
    @FXML
    private RadioButton rdbOcupada_Si;
    @FXML
    private RadioButton rdbOcupada_No;
    @FXML
    private TextField txtAncho;
    @FXML
    private TextField txtLargo;
    @FXML
    private Label lblTipoCoche;
    @FXML
    private Label lblAccesoPasillo;
    @FXML
    private Label lblNumeroMotos;
    @FXML
    private RadioButton rdbAccesoPasillo_Si;
    @FXML
    private RadioButton rdbAccesoPasillo_No;
    @FXML
    private TextField txtNumeroMotos;
    @FXML
    private RadioButton rdbTipoCoche_Pequeño;
    @FXML
    private RadioButton rdbTipoCoche_Estandar;
    @FXML
    private RadioButton rdbTipoCoche_Grande;
    @FXML
    private Button btnListadosPlazas;
    @FXML
    private Button btnModificarPlazas;
    @FXML
    private Label lblNumeroDePlaza;
    @FXML
    private Label lblNombreYApellidos;
    @FXML
    private Label lblCuota;
    @FXML
    private Label lblEuros;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblPagoAlDia;
    @FXML
    private Label lblOcupada;
    @FXML
    private Label lblAncho;
    @FXML
    private Label lblLargo;
    @FXML
    private Label lblAnchoCms;
    @FXML
    private Label lblLargoCms;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lblTitulo;
    @FXML
    private MenuBar mnbMenuBar;
    @FXML
    private Menu mnAcciones;
    @FXML
    private MenuItem mniAltasPlazas;
    @FXML
    private MenuItem mniBajasPlazas;
    @FXML
    private Menu mnAcercaDe;
    @FXML
    private MenuItem mniAcercaDe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // asociamos radio button de plaza ocupada al mismo grupo
        ToggleGroup plazaOcupada = new ToggleGroup();
        this.rdbOcupada_Si.setToggleGroup(plazaOcupada);
        this.rdbOcupada_No.setToggleGroup(plazaOcupada);

        // asociamos radio button de pago al día al mismo grupo
        ToggleGroup pagoAlDia = new ToggleGroup();
        this.rdbPagoAlDia_Si.setToggleGroup(pagoAlDia);
        this.rdbPagoAlDia_No.setToggleGroup(pagoAlDia);

        // asociamos radio button de tipo de coche al mismo grupo
        ToggleGroup tipoCoche = new ToggleGroup();
        this.rdbTipoCoche_Pequeño.setToggleGroup(tipoCoche);
        this.rdbTipoCoche_Estandar.setToggleGroup(tipoCoche);
        this.rdbTipoCoche_Grande.setToggleGroup(tipoCoche);

        // asociamos radio button de acceso pasillo al mismo grupo
        ToggleGroup accesoPasillo = new ToggleGroup();
        this.rdbAccesoPasillo_Si.setToggleGroup(accesoPasillo);
        this.rdbAccesoPasillo_No.setToggleGroup(accesoPasillo);

        //Inicialmente ocultamos la información del tipo de plaza específico
        this.lblTipoCoche.setVisible(false);
        this.rdbTipoCoche_Pequeño.setVisible(false);
        this.rdbTipoCoche_Estandar.setVisible(false);
        this.rdbTipoCoche_Grande.setVisible(false);

        this.lblAccesoPasillo.setVisible(false);
        this.rdbAccesoPasillo_Si.setVisible(false);
        this.rdbAccesoPasillo_No.setVisible(false);

        this.lblNumeroMotos.setVisible(false);
        this.txtNumeroMotos.setVisible(false);

    }

    @FXML
    private void listadosPlazas(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ParkingListadoVista.fxml"));

            Parent root = loader.load();

            ParkingListadoVistaController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ParkingVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modificarPlazas(ActionEvent event) {
        boolean validacion = true; // para validar tipos de datos
        String error = ""; // guardamos en qué campo se produce el error

        // validamos campos
        if (!esNumero(this.txtNumeroDePlaza.getText())) {
            error = "Número de Plaza";
            validacion = false;
        } else if (!esNumero(this.txtCuota.getText())) {
            error = "Cuota";
            validacion = false;
        } else if (!validarTelefono(this.txtTelefono.getText())) {
            error = "Teléfono";
            validacion = false;
        } else if (!esNumero(this.txtAncho.getText())) {
            error = "Ancho";
            validacion = false;
        } else if (!esNumero(this.txtLargo.getText())) {
            error = "Largo";
            validacion = false;
        }

        if (!validacion) { // mostramos error si algún dato no es válido. Se muestra el error del primer dato no válido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Dato no válido");
            if (error.equalsIgnoreCase("Teléfono")) {
                alert.setContentText("El teléfono no es válido");
            } else {
                alert.setContentText(error + " debe ser un número");
            }
            alert.showAndWait();
        } else { // validamos el número de plaza
            int numPlaza = Integer.parseInt(this.txtNumeroDePlaza.getText());
            ArrayList<Plaza> listaPlazas = new ArrayList<>();
            listaPlazas = getListaPlazas();

            if (!existePlaza(listaPlazas, numPlaza)) { // si no existe la plaza
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La plaza no existe");
                alert.showAndWait();
            } else { // guardamos los datos
                // pedimos confirmación
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("Se actualizarán los datos ¿Estás seguro?");
                Optional<ButtonType> action = alert.showAndWait();

                // evaluamos la confirmación
                if (action.get() == ButtonType.OK) { // hemos pulsado aceptar, realizamos la modificación
                    Plaza plaza = recuperarPlaza(listaPlazas, numPlaza);

                    plaza.setIdPlaza(Integer.parseInt(this.txtNumeroDePlaza.getText()));
                    if (this.rdbOcupada_Si.isSelected()) {
                        plaza.setOcupada(true);
                    } else {
                        plaza.setOcupada(false);
                    }
                    plaza.setInquilino(this.txtNombreYApellidos.getText());
                    plaza.setCuota(Integer.parseInt(this.txtCuota.getText()));
                    plaza.setInquilinoTelf(this.txtTelefono.getText());
                    if (this.rdbPagoAlDia_Si.isSelected()) {
                        plaza.setInquilinoPagado(true);
                    } else {
                        plaza.setInquilinoPagado(false);
                    }
                    plaza.setAncho(Integer.parseInt(this.txtAncho.getText()));
                    plaza.setLargo(Integer.parseInt(this.txtLargo.getText()));

                    if (plaza instanceof PlazaCoche) {
                        if (this.rdbTipoCoche_Pequeño.isSelected()) {
                            ((PlazaCoche) plaza).setTipoCoche(1);
                        } else if (this.rdbTipoCoche_Estandar.isSelected()) {
                            ((PlazaCoche) plaza).setTipoCoche(2);
                        } else if (this.rdbTipoCoche_Grande.isSelected()) {
                            ((PlazaCoche) plaza).setTipoCoche(3);
                        }
                    } else if (plaza instanceof PlazaMoto) {
                        ((PlazaMoto) plaza).setNumeroMotos(Integer.parseInt(this.txtNumeroMotos.getText()));
                    } else if (plaza instanceof PlazaMinusvalido) { // es de tipo PlazaMinusvalido
                        if (this.rdbAccesoPasillo_Si.isSelected()) {
                            ((PlazaMinusvalido) plaza).setAccesoPasillo(true);
                        } else {
                            ((PlazaMinusvalido) plaza).setAccesoPasillo(false);
                        }
                    }

                    // guardamos los datos
                    serializaFicheroOutput(listaPlazas, "plazas.dat");

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Modificación realizada");
                    alert.setContentText("Se ha realizado la modificación. Los datos se han actualizado");
                    alert.showAndWait();

                } else { // hemos pulsado cancelar, no hacemos nada
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Modificación cancelada");
                    alert.setContentText("Se ha cancelado la modificación. Los datos NO se han actualizado");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void buscarMostrarPlaza() {
        Plaza plaza = null;
        if (!esNumero(this.txtNumeroDePlaza.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Plaza debe ser un número");
            alert.showAndWait();
        } else {
            plaza = buscarPlaza(Integer.parseInt(this.txtNumeroDePlaza.getText()));
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
            limpiar();
            // mostramos sus datos genéricos
            this.txtNumeroDePlaza.setText(p.getIdPlaza() + "");
            if (p.isOcupada()) {
                this.rdbOcupada_Si.setSelected(true);
            } else {
                this.rdbOcupada_Si.setSelected(false);
            }
            this.txtNombreYApellidos.setText(p.getInquilino());
            this.txtCuota.setText(p.getCuota() + "");
            this.txtTelefono.setText(p.getInquilinoTelf());
            if (p.isInquilinoPagado()) {
                this.rdbPagoAlDia_Si.setSelected(true);
                this.rdbPagoAlDia_No.setSelected(false);
            } else {
                this.rdbPagoAlDia_Si.setSelected(false);
                this.rdbPagoAlDia_No.setSelected(true);
            }
            this.txtAncho.setText(p.getAncho() + "");
            this.txtLargo.setText(p.getLargo() + "");

            // y mostramos sus datos específicos
            if (p instanceof PlazaCoche) {
                this.lblTipoCoche.setVisible(true);
                this.rdbTipoCoche_Pequeño.setVisible(true);
                this.rdbTipoCoche_Estandar.setVisible(true);
                this.rdbTipoCoche_Grande.setVisible(true);
                switch (((PlazaCoche) p).getTipoCoche()) {
                    case 1: { // coche pequeño
                        this.rdbTipoCoche_Pequeño.setSelected(true);
                        break;
                    }
                    case 2: { // coche estándar
                        this.rdbTipoCoche_Estandar.setSelected(true);
                        break;
                    }
                    default: // = 3, coche grande
                        this.rdbTipoCoche_Grande.setSelected(true);
                }
            } else if (p instanceof PlazaMoto) {
                this.lblNumeroMotos.setVisible(true);
                this.txtNumeroMotos.setVisible(true);
                this.txtNumeroMotos.setText(((PlazaMoto) p).getNumeroMotos() + "");
            } else if (p instanceof PlazaMinusvalido) { // es de tipo PlazaMinusvalido
                this.lblAccesoPasillo.setVisible(true);
                this.rdbAccesoPasillo_Si.setVisible(true);
                this.rdbAccesoPasillo_No.setVisible(true);
                if (((PlazaMinusvalido) p).getAccesoPasillo()) {
                    this.rdbAccesoPasillo_Si.setSelected(true);
                } else {
                    this.rdbAccesoPasillo_Si.setSelected(false);
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
    private void limpiar() {
        this.txtNumeroDePlaza.setText(null);
        this.rdbOcupada_Si.setSelected(false);
        this.rdbOcupada_No.setSelected(false);
        this.txtNombreYApellidos.setText(null);
        this.txtCuota.setText(null);
        this.txtTelefono.setText(null);
        this.rdbPagoAlDia_Si.setSelected(false);
        this.rdbPagoAlDia_No.setSelected(false);
        this.txtAncho.setText(null);
        this.txtLargo.setText(null);

        this.lblTipoCoche.setVisible(false);
        this.rdbTipoCoche_Pequeño.setVisible(false);
        this.rdbTipoCoche_Estandar.setVisible(false);
        this.rdbTipoCoche_Grande.setVisible(false);

        this.lblAccesoPasillo.setVisible(false);
        this.rdbAccesoPasillo_Si.setVisible(false);
        this.rdbAccesoPasillo_No.setVisible(false);

        this.lblNumeroMotos.setVisible(false);
        this.txtNumeroMotos.setVisible(false);
    }

    @FXML
    private void altasPlazas() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ParkingAltaVista.fxml"));

            Parent root = loader.load();

            ParkingAltaVistaController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ParkingVistaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void bajasPlazas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ParkingBajaVista.fxml"));

            Parent root = loader.load();

            ParkingBajaVistaController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ParkingVistaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void sobreParking() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ParkingAcercaDeVista.fxml"));

            Parent root = loader.load();

            ParkingAcercaDeVistaController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ParkingVistaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
