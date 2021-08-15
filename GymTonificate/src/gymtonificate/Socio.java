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
public class Socio extends Persona {

    private int sesionesSemanales; // mínimo 2, máximo 6
    private double cuota; // a razón de 8 euros por sesión
    private boolean pagado; // indica si se encuentra al día de los pagos
    private String lesiones; // registra si tiene algún tipo de lesión
    public static final double PRECIO_SESION = 8;

    public Socio(int sesionesSemanales, boolean pagado, String lesiones, String nombre, String dni, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo) {
        super(nombre, dni, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
        if (sesionesSemanales < 2 || sesionesSemanales > 6) {
            throw new IllegalArgumentException("Las sesiones semanales deben ser entre 2 y 6");
        }
        this.sesionesSemanales = sesionesSemanales;
        this.cuota = this.sesionesSemanales * PRECIO_SESION;
        this.pagado = pagado;
        this.lesiones = lesiones;
    }

    public int getSesionesSemanales() {
        return this.sesionesSemanales;
    }

    public double getCuota() {
        return this.cuota;
    }

    public boolean getPagado() {
        return this.pagado;
    }

    public String getLesiones() {
        return this.lesiones;
    }

    public void setSesionesSemanales(int sesionesSemanales) {
        if (sesionesSemanales < 2 || sesionesSemanales > 6) {
            throw new IllegalArgumentException("Las sesiones semanales deben ser entre 2 y 6");
        }
        this.sesionesSemanales = sesionesSemanales;
        this.cuota = this.sesionesSemanales * PRECIO_SESION;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setLesiones(String lesiones) {
        this.lesiones = lesiones;
    }

    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += ("Tipo: " + getClass().getSimpleName() + "\n");
        resultado += ("Sesiones semanales: ") + (this.sesionesSemanales) + ("; ");
        resultado += ("Cuota: ") + (this.sesionesSemanales * PRECIO_SESION) + ("; ");
        resultado += ("Pagado: ");
        if (this.pagado) {
            resultado += ("Sí");
        } else {
            resultado += ("No");
        }
        resultado += ("; ");
        resultado += ("Lesiones: ");
        if (this.lesiones.equals("")) {
            resultado += ("Sin lesiones");
        } else {
            resultado += (this.lesiones);
        }
        resultado += ("\n===================\n");
        return resultado;
    }

   

}
