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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modelo.Operaciones;
import modelo.Suma;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class SumaControlador implements Initializable {

    @FXML
    private TextField txtOp1;
    @FXML
    private TextField txtOp2;
    @FXML
    private TextField txtResultado;
    @FXML
    private Button btnOperar;
    @FXML
    private RadioButton rdbSuma;
    @FXML
    private RadioButton rdbDivision;
    @FXML
    private RadioButton rdbResta;
    @FXML
    private RadioButton rdbMultiplica;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // asociamos radio button al mismo grupo
        ToggleGroup tg = new ToggleGroup();
        this.rdbSuma.setToggleGroup(tg);
        this.rdbResta.setToggleGroup(tg);
        this.rdbMultiplica.setToggleGroup(tg);
        this.rdbDivision.setToggleGroup(tg);

    }

    private void sumar(ActionEvent event) {

        try {
            int op1 = Integer.parseInt(this.txtOp1.getText());
            int op2 = Integer.parseInt(this.txtOp2.getText());

            Suma s = new Suma(op1, op2);
            int resultado = s.suma();
            this.txtResultado.setText(resultado + "");

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }

    @FXML
    private void hacerOperacion(ActionEvent event) {

        try {
            int op1 = Integer.parseInt(this.txtOp1.getText());
            int op2 = Integer.parseInt(this.txtOp2.getText());

            Operaciones op = new Operaciones(op1, op2);
            if (this.rdbSuma.isSelected()) {
                this.txtResultado.setText(op.suma() + "");
            }
            if (this.rdbSuma.isSelected()) {
                this.txtResultado.setText(op.suma() + "");
            } else if (this.rdbResta.isSelected()) {
                this.txtResultado.setText(op.resta() + "");
            } else if (this.rdbMultiplica.isSelected()) {
                this.txtResultado.setText(op.mult() + "");
            } else if (this.rdbDivision.isSelected()) {
                if (op2 == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El segundo operando no puede ser cero");
                    alert.showAndWait();
                } else {
                    this.txtResultado.setText(op.division() + "");
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }

}
