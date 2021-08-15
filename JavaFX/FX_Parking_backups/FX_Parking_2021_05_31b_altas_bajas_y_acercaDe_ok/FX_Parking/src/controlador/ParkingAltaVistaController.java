/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.ParkingVistaController.buscarPlaza;
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
import static modelo.Plaza.validarTelefono;
import modelo.PlazaCoche;
import modelo.PlazaMinusvalido;
import modelo.PlazaMoto;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class ParkingAltaVistaController implements Initializable {

    @FXML
    private Label lblTituloAltas;
    @FXML
    private Button btnGuardarAltas;
    @FXML
    private TextField txtNumeroDePlazaAltas;
    @FXML
    private TextField txtNombreYApellidosAltas;
    @FXML
    private TextField txtCuotaAltas;
    @FXML
    private TextField txtTelefonoAltas;
    @FXML
    private Label lblNumeroDePlazaAltas;
    @FXML
    private Label lblNombreYApellidosAltas;
    @FXML
    private Label lblCuotaAltas;
    @FXML
    private Label lblEurosAltas;
    @FXML
    private Label lblTelefonoAltas;
    @FXML
    private Label lblPagoAlDiaAltas;
    @FXML
    private RadioButton rdbPagoAlDia_Si_Altas;
    @FXML
    private RadioButton rdbPagoAlDia_No_Altas;
    @FXML
    private Label lblOcupadaAltas;
    @FXML
    private RadioButton rdbOcupada_Si_Altas;
    @FXML
    private RadioButton rdbOcupada_No_Altas;
    @FXML
    private Label lblAnchoAltas;
    @FXML
    private Label lblLargoAltas;
    @FXML
    private TextField txtAnchoAltas;
    @FXML
    private TextField txtLargoAltas;
    @FXML
    private Label lblAnchoCmsAltas;
    @FXML
    private Label lblLargoCmsAltas;
    @FXML
    private Label lblTipoCocheAltas;
    @FXML
    private Label lblAccesoPasilloAltas;
    @FXML
    private Label lblNumeroMotos_Altas;
    @FXML
    private RadioButton rdbAccesoPasillo_Si_Altas;
    @FXML
    private RadioButton rdbAccesoPasillo_No_Altas;
    @FXML
    private TextField txtNumeroMotos_Altas;
    @FXML
    private Button btnLimpiarAltas;
    @FXML
    private RadioButton rdbTipoCoche_Pequeño_Altas;
    @FXML
    private RadioButton rdbTipoCoche_Estandar_Altas;
    @FXML
    private RadioButton rdbTipoCoche_Grande_Altas;
    @FXML
    private Label lblTipoPlazaAltas;
    @FXML
    private RadioButton rdbTipoPlaza_Coche_Altas;
    @FXML
    private RadioButton rdbTipoPlaza_Moto_Altas;
    @FXML
    private RadioButton rdbTipoPlaza_Minusvalido_Altas;
    @FXML
    private Button btnVolverAltas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // asociamos radio button de plaza ocupada al mismo grupo
        ToggleGroup plazaOcupadaAltas = new ToggleGroup();
        this.rdbOcupada_Si_Altas.setToggleGroup(plazaOcupadaAltas);
        this.rdbOcupada_No_Altas.setToggleGroup(plazaOcupadaAltas);

        // asociamos radio button de pago al día al mismo grupo
        ToggleGroup pagoAlDiaAltas = new ToggleGroup();
        this.rdbPagoAlDia_Si_Altas.setToggleGroup(pagoAlDiaAltas);
        this.rdbPagoAlDia_No_Altas.setToggleGroup(pagoAlDiaAltas);

        // asociamos radio button de tipo de coche al mismo grupo
        ToggleGroup tipoCoche = new ToggleGroup();
        this.rdbTipoCoche_Pequeño_Altas.setToggleGroup(tipoCoche);
        this.rdbTipoCoche_Estandar_Altas.setToggleGroup(tipoCoche);
        this.rdbTipoCoche_Grande_Altas.setToggleGroup(tipoCoche);

        // asociamos radio button de acceso pasillo al mismo grupo
        ToggleGroup accesoPasilloAltas = new ToggleGroup();
        this.rdbAccesoPasillo_Si_Altas.setToggleGroup(accesoPasilloAltas);
        this.rdbAccesoPasillo_No_Altas.setToggleGroup(accesoPasilloAltas);

        // asociamos radio button de tipo de plaza al mismo grupo
        ToggleGroup tipoPlaza = new ToggleGroup();
        this.rdbTipoPlaza_Coche_Altas.setToggleGroup(tipoPlaza);
        this.rdbTipoPlaza_Moto_Altas.setToggleGroup(tipoPlaza);
        this.rdbTipoPlaza_Minusvalido_Altas.setToggleGroup(tipoPlaza);

        //Inicialmente seleccionamos las opciones de radio button por defecto
        this.rdbOcupada_No_Altas.setSelected(true);
        this.rdbPagoAlDia_Si_Altas.setSelected(true);
        this.rdbTipoPlaza_Coche_Altas.setSelected(true);
        this.rdbTipoCoche_Pequeño_Altas.setSelected(true);
        //y ocultamos la información del tipo de plaza específico para el tipo no seleccionado por defecto
        this.lblAccesoPasilloAltas.setVisible(false);
        this.rdbAccesoPasillo_Si_Altas.setVisible(false);
        this.rdbAccesoPasillo_No_Altas.setVisible(false);
        this.lblNumeroMotos_Altas.setVisible(false);
        this.txtNumeroMotos_Altas.setVisible(false);
    }

    @FXML
    private void altaPlaza() {
        Plaza plaza;
        if (!esNumero(this.txtNumeroDePlazaAltas.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Plaza debe ser un número");
            alert.showAndWait();
        } else {
            plaza = buscarPlaza(Integer.parseInt(this.txtNumeroDePlazaAltas.getText()));
            if (plaza != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Plaza ya existente");
                alert.setContentText("No se puede dar de alta la Plaza, la plaza ya existe");
                alert.showAndWait();
            } else {
                // validamos y damos el alta
                boolean validacion = true; // para validar tipos de datos
                String error = ""; // guardamos en qué campo se produce el error
                // validamos campos
                if (!esNumero(this.txtNumeroDePlazaAltas.getText())) {
                    error = "Número de Plaza";
                    validacion = false;
                } else if (!esNumero(this.txtCuotaAltas.getText())) {
                    error = "Cuota";
                    validacion = false;
                } else if (!validarTelefono(this.txtTelefonoAltas.getText())) {
                    error = "Teléfono";
                    validacion = false;
                } else if (!esNumero(this.txtAnchoAltas.getText())) {
                    error = "Ancho";
                    validacion = false;
                } else if (!esNumero(this.txtLargoAltas.getText())) {
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
                    // pedimos confirmación
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Confirmación");
                    alert.setContentText("Se dará el alta de la plaza ¿Estás seguro?");
                    Optional<ButtonType> action = alert.showAndWait();

                    // evaluamos la confirmación
                    if (action.get() == ButtonType.OK) { // hemos pulsado aceptar, realizamos el alta
                        int idPlaza = Integer.parseInt(this.txtNumeroDePlazaAltas.getText());
                        boolean ocupada = this.rdbOcupada_Si_Altas.isSelected();
                        String inquilino = this.txtNombreYApellidosAltas.getText();
                        int cuota = Integer.parseInt(this.txtCuotaAltas.getText());
                        String telefono = this.txtTelefonoAltas.getText();
                        boolean pagoAlDia = this.rdbPagoAlDia_Si_Altas.isSelected();
                        int ancho = Integer.parseInt(this.txtAnchoAltas.getText());
                        int largo = Integer.parseInt(this.txtLargoAltas.getText());
                        if (this.rdbTipoPlaza_Coche_Altas.isSelected()) {
                            int tipoCoche = 0;
                            if (this.rdbTipoCoche_Pequeño_Altas.isSelected()) {
                                tipoCoche = 1;
                            } else if (this.rdbTipoCoche_Estandar_Altas.isSelected()) {
                                tipoCoche = 2;
                            } else if (this.rdbTipoCoche_Grande_Altas.isSelected()) {
                                tipoCoche = 3;
                            }
                            plaza = new PlazaCoche(tipoCoche, idPlaza, ocupada, cuota, inquilino, telefono, pagoAlDia, largo, ancho);
                        } else if (this.rdbTipoPlaza_Moto_Altas.isSelected()) {
                            int numeroMotos = Integer.parseInt(this.txtNumeroMotos_Altas.getText());
                            plaza = new PlazaMoto(numeroMotos, idPlaza, ocupada, cuota, inquilino, telefono, pagoAlDia, largo, ancho);
                        } else if (this.rdbTipoPlaza_Minusvalido_Altas.isSelected()) { // es de tipo PlazaMinusvalido
                            boolean accesoPasillo = this.rdbAccesoPasillo_Si_Altas.isSelected();
                            plaza = new PlazaMinusvalido(accesoPasillo, idPlaza, ocupada, cuota, inquilino, telefono,  pagoAlDia, largo, ancho);
                        }
                        
                        //recuperamos la lista de plazas
                        ArrayList<Plaza> listaPlazas = new ArrayList<>();
                        listaPlazas = serializaFicheroInput(listaPlazas, "plazas.dat");
                        //añadimos nuestra plaza
                        listaPlazas.add(plaza);
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
                        alert.setTitle("Alta cancelada");
                        alert.setContentText("Se ha cancelado el alta de la plaza. Los datos NO se han actualizado");
                        alert.showAndWait();
                    }
                }
            }
        }
    }

    @FXML
    private void limpiarAlta() {
        this.txtNumeroDePlazaAltas.setText(null);
        this.rdbOcupada_Si_Altas.setSelected(false);
        this.rdbOcupada_No_Altas.setSelected(true);
        this.txtNombreYApellidosAltas.setText(null);
        this.txtCuotaAltas.setText(null);
        this.txtTelefonoAltas.setText(null);
        this.rdbPagoAlDia_Si_Altas.setSelected(true);
        this.rdbPagoAlDia_No_Altas.setSelected(false);
        this.txtAnchoAltas.setText(null);
        this.txtLargoAltas.setText(null);
        this.rdbTipoPlaza_Coche_Altas.setSelected(true);

        this.lblTipoCocheAltas.setVisible(true);
        this.rdbTipoCoche_Pequeño_Altas.setVisible(true);
        this.rdbTipoCoche_Estandar_Altas.setVisible(true);
        this.rdbTipoCoche_Grande_Altas.setVisible(true);
        this.rdbTipoCoche_Pequeño_Altas.setSelected(true);

        this.lblAccesoPasilloAltas.setVisible(false);
        this.rdbAccesoPasillo_Si_Altas.setVisible(false);
        this.rdbAccesoPasillo_No_Altas.setVisible(false);

        this.lblNumeroMotos_Altas.setVisible(false);
        this.txtNumeroMotos_Altas.setVisible(false);
    }

    @FXML
    private void volverMenuPrincipal_DesdeAlta() {
        Stage stage = (Stage) this.btnVolverAltas.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void tipoPlazaCoche() {
        //mostrar elementos para tipo coche y esconder tipo moto y minusválidos
        this.lblTipoCocheAltas.setVisible(true);
        this.rdbTipoCoche_Pequeño_Altas.setVisible(true);
        this.rdbTipoCoche_Estandar_Altas.setVisible(true);
        this.rdbTipoCoche_Grande_Altas.setVisible(true);
        this.rdbTipoCoche_Pequeño_Altas.setSelected(true);
        this.lblAccesoPasilloAltas.setVisible(false);
        this.rdbAccesoPasillo_Si_Altas.setVisible(false);
        this.rdbAccesoPasillo_No_Altas.setVisible(false);
        this.lblNumeroMotos_Altas.setVisible(false);
        this.txtNumeroMotos_Altas.setVisible(false);
    }

    @FXML
    private void tipoPlazaMoto() {
        //mostrar elementos para tipo moto y esconder tipo coche y minusválidos
        this.lblTipoCocheAltas.setVisible(false);
        this.rdbTipoCoche_Pequeño_Altas.setVisible(false);
        this.rdbTipoCoche_Estandar_Altas.setVisible(false);
        this.rdbTipoCoche_Grande_Altas.setVisible(false);
        this.lblAccesoPasilloAltas.setVisible(false);
        this.rdbAccesoPasillo_Si_Altas.setVisible(false);
        this.rdbAccesoPasillo_No_Altas.setVisible(false);
        this.lblNumeroMotos_Altas.setVisible(true);
        this.txtNumeroMotos_Altas.setVisible(true);
    }

    @FXML
    private void tipoPlazaMinusvalido() {
        //mostrar elementos para tipo minusválidos y esconder tipo coche y moto
        this.lblTipoCocheAltas.setVisible(false);
        this.rdbTipoCoche_Pequeño_Altas.setVisible(false);
        this.rdbTipoCoche_Estandar_Altas.setVisible(false);
        this.rdbTipoCoche_Grande_Altas.setVisible(false);
        this.lblAccesoPasilloAltas.setVisible(true);
        this.rdbAccesoPasillo_Si_Altas.setVisible(true);
        this.rdbAccesoPasillo_No_Altas.setVisible(true);
        this.lblNumeroMotos_Altas.setVisible(false);
        this.txtNumeroMotos_Altas.setVisible(false);
        this.rdbAccesoPasillo_No_Altas.setSelected(true);
    }
}
