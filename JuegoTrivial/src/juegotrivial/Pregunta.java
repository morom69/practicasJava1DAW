/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegotrivial;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Miguel Moro
 */
public class Pregunta implements Serializable {

    private String enunciado;
    private String[] respuestas;
    private int correcta;

    // constructor
    public Pregunta(String enunciado, String[] respuestas, int correcta) {
        if (correcta < 0 || correcta > 3) {
            throw new IllegalArgumentException("El índice de la respuesta correcta debe estar entre 0 y 3");
        }
        if (respuestas.length != 4) {
            throw new IllegalArgumentException("Deben incluirse 4 respuestas");
        }
        this.enunciado = enunciado;
        this.respuestas = Arrays.copyOf(respuestas, respuestas.length);
        this.correcta = correcta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        if (enunciado.length() == 0) {
            throw new IllegalArgumentException("El enunciado no puede estar vacío");
        }
        this.enunciado = enunciado;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        if (respuestas.length != 4) {
            throw new IllegalArgumentException("Debe contener 4 opciones de respuesta");
        }
        this.respuestas = respuestas;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        if (correcta < 0 || correcta > 3) {
            throw new IllegalArgumentException("La respuesta correcta debe estar entre 0 y 3");
        }
        this.correcta = correcta;
    }

    public boolean esRespuestaCorrecta(int indice) {
        if (indice < 0 || indice > 3) {
            throw new IllegalArgumentException("El índice de la respuesta correcta debe estar entre 0 y 3");
        }
        boolean respuesta = false;
        if (this.correcta == indice) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public String toString() {
        String resultado = "";
        resultado += ("Pregunta: ") + getEnunciado() + ("\n");
        resultado += ("Posibles respuestas: \n");
        for (int i = 0; i < this.respuestas.length; i++) {
            resultado += (i + 1) + (" - ") + (this.getRespuestas()[i]) + ("\n");
        }
        return resultado;
    }
}
