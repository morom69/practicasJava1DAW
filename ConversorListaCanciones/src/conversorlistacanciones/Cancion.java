/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversorlistacanciones;

import java.io.Serializable;

/**
 *
 * @author Miguel Moro
 */
public class Cancion implements Serializable {

    private String titulo;
    private String autor;
    private int duracion; // duraci�n en segundos de cada canci�n

    public Cancion(String titulo, String autor, int duracion) { // constructor// constructor
        if(!validarCancion(duracion)){
            throw new IllegalArgumentException("La duraci�n de una canci�n no puede ser negativa");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if(!validarCancion(duracion)){
            throw new IllegalArgumentException("La duraci�n de una canci�n no puede ser negativa");
        }
        this.duracion = duracion;
    }

    private boolean validarCancion(int duracion){
        return (duracion > 0);
    }
    
    /**
     * Sobreescribimos el m�todo toString() para la clase.
     *
     * @return devuelve un texto con los atributos de la canci�n.
     */
    @Override
    public String toString() {
        String resultado = "";
        resultado += ("T�tulo: ") + this.titulo + ", ";
        resultado += ("Autor: ") + this.autor + ", ";
        resultado += ("Duraci�n: ") + this.duracion + " \n";
        return resultado;
    }

    /**
     * Comprueba si dos inquilinos se consideran iguales, para lo cual usamos
     * como criterio la comparaci�n de sus nombres y apellidos
     *
     * @param inquilino: nombre y apellidos del inquilino de la plaza que
     * queremos comparar
     * @return devuelve true si el nombre y apellidos facilitado como par�metro
     * es el mismo que el asignado a la plaza instanciada, false en caso
     * contrario.
     */
    public boolean equals(Cancion cancion) {
        //para evaluar si el t�tulo y el autor de dos canciones son los mismos ignoramos may�sculas / min�sculas
        return ((this.titulo.equalsIgnoreCase(cancion.getTitulo()))
                && (this.autor.equalsIgnoreCase(autor))
                && (this.duracion == duracion));
    }
}

