/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymtonificate;

import static gymtonificate.Monitor.SMI;
import java.util.Calendar;

/**
 *
 * @author Miguel Moro
 */
public class Empleado extends Persona {
    private String tipoTrabajo; // tipo de trabajo que realiza, debe estar incluido en listaTrabajos
    private float sueldo;
    private String extension; // extensión telefónica

    public Empleado(String tipoTrabajo, float sueldo, String extension, String nombre, String dni, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo) {
        super(nombre, dni, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
        if (sueldo < SMI) {
            throw new IllegalArgumentException("El sueldo no puede ser inferior al Salario Mínimo Interprofesional, actualmente " + SMI + " euros");
        }

        this.tipoTrabajo = tipoTrabajo;
        this.sueldo = sueldo;
        this.extension = extension;
    }

    public float getSueldo() {
        return sueldo;
    }

    public String getExtension() {
        return extension;
    }
    
    public String getTipoTrabajo() {
        return tipoTrabajo;
    }
    
    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }
        
    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += ("Tipo: " + getClass().getSimpleName() + "\n");
        resultado += ("Trabajo: ") + (this.tipoTrabajo) + ("; ");
        resultado += ("Sueldo: ") + (this.sueldo) + ("; ");
        resultado += ("Extensión: ") + (this.extension) + ("; ");
        resultado += ("\n===================\n");
        return resultado;
    }
    
   
    
}
