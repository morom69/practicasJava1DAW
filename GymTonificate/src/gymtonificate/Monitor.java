/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymtonificate;

import java.util.Calendar;

/**
 *
 * @author Miguel Moro
 */
public class Monitor extends Persona {

    private String[] especialidad; // máximo 3 especialidades por monitor, que deben existir previamente en la lista de especialidades
    private float sueldo; // como mínimo el Salario Mínimo Interprofesional - SMI
    private boolean activo; // indica si el monitor se encuentra activo o no

    public Monitor(String[] especialidad, float sueldo, boolean activo, String nombre, String dni, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo) {
        super(nombre, dni, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
        if (sueldo < SMI) {
            throw new IllegalArgumentException("El sueldo no puede ser inferior al Salario Mínimo Interprofesional, actualmente " + SMI + " euros");
        }
        this.especialidad = especialidad;
        this.sueldo = sueldo;
        this.activo = activo;
    }

    public String[] getEspecialidad() {
        // devolvemos una copia para no modificar el original
        return copiaEspecialidades(this.especialidad);
    }

    public float getSueldo() {
        return this.sueldo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setEspecialidad(String[] especialidad) {
        // hacemos set utilizando una copia del parámetro para no modificar el original
        this.especialidad = copiaEspecialidades(especialidad);
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += ("Tipo: " + getClass().getSimpleName() + "\n");
        resultado += listadoEspecialidades();
        resultado += ("Sueldo: ") + (this.sueldo) + ("; ");
        resultado += ("Activo: ");
        if (this.activo) {
            resultado += ("Sí");
        } else {
            resultado += ("No");
        }
        resultado += ("\n===================\n");
        return resultado;
    }

    /**
     * Realiza una copia de las especialidades del monitor instanciado
     *
     * @return devuelve un array de cadenas con una copia del listado de
     * especialidades del monitor instanciado
     */
    public String[] copiaEspecialidades() {
        String[] resultado = null;
        if (this.especialidad.length > 0) {
            resultado = new String[this.especialidad.length];
            System.arraycopy(this.especialidad, 0, resultado, 0, this.especialidad.length);
        }
        return resultado;
    }

    /**
     * Realiza una copia de las especialidades pasadas como parámetro
     *
     * @param especialidad: array de especialidades del cual vamos a facilitar
     * la copia
     * @return devuelve un array de cadenas con una copia del listado de
     * especialidades pasadas como parámetro
     */
    public String[] copiaEspecialidades(String[] especialidad) {
        String[] resultado = null;
        if (especialidad.length > 0) {
            resultado = new String[especialidad.length];
            System.arraycopy(especialidad, 0, resultado, 0, especialidad.length);
        }
        return resultado;
    }

    /**
     * Listado de especialidades del monitor instanciado
     *
     * @return devuelve un string con el listado de especialidades del monitor
     * instanciado, una línea por especialidad
     */
    public String listadoEspecialidades() {
        String resultado = "";
        if (this.especialidad.length == 0) {
            resultado = "El monitor no tiene especialidades /n";
        } else {
            for (int i = 0; i < this.especialidad.length; i++) {
                resultado += "Especialidad " + (i + 1) + ": " + this.especialidad[i] + "; ";
            }
        }
        return resultado;
    }

    
}
