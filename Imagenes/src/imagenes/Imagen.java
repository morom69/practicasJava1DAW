/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

import java.io.Serializable;

/**
 *
 * @author Miguel Moro
 */
public abstract class Imagen implements Serializable {

    private int ancho;
    private int alto;

    public Imagen(int ancho, int alto) {
        if (ancho <= 0 || alto <= 0) {
            throw new IllegalArgumentException("el ancho y el alto de la imagen deben ser mayores que cero");
        }
        this.ancho = ancho;
        this.alto = alto;
    }
    
    abstract public int [] histograma();

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
}
