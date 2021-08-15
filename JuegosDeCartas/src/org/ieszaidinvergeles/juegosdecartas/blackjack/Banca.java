/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieszaidinvergeles.juegosdecartas.blackjack;

import java.io.Serializable;
import java.util.LinkedHashSet;
import org.ieszaidinvergeles.juegosdecartas.Carta;

/**
 *
 * @author Miguel Moro
 */
public class Banca extends Jugador implements Comparable, Serializable {

    private int estado;
    // si su valor es 0 siguen jugando
    // si su valor es -1 se han pasado
    // si su valor es 1 se han plantado
    // si su valor es 2 es ganador

    public Banca() {
        super("Banca", 0);
        this.bolsa = 100000;
        this.apuesta = 0;
        this.cartas = new LinkedHashSet<>();
        this.estado = 0;
    }

    /**
     * Se devuelve si la banca sigue jugando, es decir si no se ha pasado ni se
     * ha plantado
     *
     * @return devuelve true si la banca sigue jugando, false en caso contrario
     */
    public boolean getSigueJugando() {
        return (this.estado == 0);
    }

    /**
     * Se establece el estado de la banca. Cero si sigue jugando, uno si se ha
     * plantado y menos uno si se ha pasado.
     *
     * @param estado es el estado que toma la banca
     */
    public void setEstado(int estado) {
        if ((estado < -1) || (estado > 1)) {
            throw new IllegalArgumentException("Estado no válido para la banca");
        }
        this.estado = estado;
    }

    /**
     * Se obtiene el estado de la banca. Cero si sigue jugando, uno si se ha
     * plantado y menos uno si se ha pasado.
     *
     * @return devuelve el estado que tiene la banca
     */
    public int getEstado() {
        return this.estado;
    }

    /**
     * Muestra la mano de cartas de la banca ocultando la primera carta
     */
    public void mostrarManoBanca() {
        Carta manoBanca[] = new Carta[cartas.size()];
        int i = 0;
        System.out.println(this.getNombre());
        for (Carta c : cartas) {
            manoBanca[i] = c;
            i++;
        }
        System.out.println("*********************");
        for (i = 1; i < manoBanca.length; i++) {
            System.out.println(manoBanca[i].getValor() + " - " + manoBanca[i].getPalo());
            manoBanca[i].toString();
        }
    }

    @Override
    public String toString() {
        String jugador = "";
        jugador += nombre + "; Cartas: ";
        for (Carta c : cartas) {
            jugador += c.toString() + " ";
        }
        return jugador;
    }

    /**
     * Para cumplir con los requerimientos de la iterfaz Comparable. Compara si
     * un jugador es mayor, menor o igual que otr pasado por parámetro, teniendo
     * en cuenta la puntuación de cada jugador.
     *
     * @param miObjeto: jugador con el que compararemos al jugador actual.
     * @return devuelve lo siguiente: -1 si la puntuación del jugador actual es
     * inferior a la del jugador pasado por parámetro, 1 la puntuación del
     * jugador actual mayor a la puntuación del jugador pasado por parámetro, 0
     * si ambos jugadores tienen igual puntuación.
     */
    @Override
    public int compareTo(Object miObjeto) {
        int resultado = 0;
        Banca j = (Banca) miObjeto;
        if (this.getPuntuacion() < j.getPuntuacion()) {
            resultado = -1;
        } else if (this.getPuntuacion() > j.getPuntuacion()) {
            resultado = 1;
        }
        return resultado;
    }
}
