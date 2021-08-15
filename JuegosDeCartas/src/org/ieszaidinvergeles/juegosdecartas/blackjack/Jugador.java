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
public class Jugador implements Comparable, Serializable {

    protected String nombre;
    protected int bolsa; // cantidad de dinero del jugador
    protected int apuesta; // valor de la última apuesta realizada por el jugador
    protected LinkedHashSet<Carta> cartas = new LinkedHashSet<Carta>(); // mano de cartas del jugador

    public Jugador(String nombre, int bolsa) {
        if (bolsa < 0) {
            throw new IllegalArgumentException("No se puede jugar con una bolsa negativa");
        }
        this.nombre = nombre;
        this.bolsa = bolsa;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getBolsa() {
        return this.bolsa;
    }

    public void setBolsa(int bolsa) {
        this.bolsa = bolsa;
    }

    public int getApuesta() {
        return this.apuesta;
    }

    public int getPuntuacion() {
        //devuelve la puntuación del jugador según sus cartas
        int puntuacion = 0;
        for (Carta c : cartas) {
            puntuacion += c.getValor().getValue();
        }
        return puntuacion;
    }

    public void pedirCarta(Carta c) { // añade una carta a su lista de cartas
        this.cartas.add(c);
    }

    public void soltarCartas() { // limpia la mano de cartas del jugador
        this.cartas.clear();
    }

    /**
     * Muestra la mano de cartas del jugador
     */
    public void mostrarMano() {
        System.out.println(this.getNombre());
        for (Carta i : cartas) {
            System.out.println(i.getValor() + " - " + i.getPalo());
            i.toString();
        }
    }

    /**
     * Realiza una apuesta. Si no hay dinero suficiente no se realiza la apuesta
     *
     * @param apuesta es la cantidad a apostar por el jugador
     * @return devuelve true si la apuesta se realiza. False en caso contrario
     */
    public boolean apostar(int apuesta) { // apuesta la cantidad de dinero indicada. Devuelve true si hay dinero suficiente. Falso en caso contrario
        if (this.getBolsa() >= apuesta) {
            this.bolsa -= apuesta;
            this.apuesta = apuesta;
            System.out.println("Apuesta realizada: " + apuesta + "; bolsa restante: " + this.bolsa);
            System.out.println("");
        } else {
            System.out.println("Bolsa insuficiente, no se realiza la apuesta");
        }
        return (this.getBolsa() >= apuesta);
    }

    /**
     * Se le concede al jugador su apuesta como ganada, se le paga la cantidad
     * apostada
     */
    public void apuestaGanada() {
        this.bolsa += 2 * this.apuesta;
        this.apuesta = 0;
    }

    /**
     * El jugador pierde la apuesta, se le retira la cantidad apostada
     */
    public void apuestaPerdida() {
        this.apuesta = 0;
    }

    @Override
    public String toString() {
        String jugador = "";
        jugador += nombre + "; bolsa = " + bolsa + "; apuesta = " + apuesta + "/nCartas: ";
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
        Jugador j = (Jugador) miObjeto;
        if (this.getPuntuacion() < j.getPuntuacion()) {
            resultado = -1;
        } else if (this.getPuntuacion() > j.getPuntuacion()) {
            resultado = 1;
        }
        return resultado;
    }
}
