/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieszaidinvergeles.juegosdecartas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import org.ieszaidinvergeles.juegosdecartas.Carta.PaloNaipe;
import org.ieszaidinvergeles.juegosdecartas.Carta.RangoNaipe;

/**
 *
 * @author Miguel Moro
 */
public class Baraja {

    // utilizamos un conjunto de tipo LinkedHashSet para poder tener la baraja ordenada
    private LinkedHashSet<Carta> mazo = new LinkedHashSet<Carta>(); // baraja

    public Baraja(){
        for(PaloNaipe palo: PaloNaipe.values()){ // para cada palo
            for(RangoNaipe valor: RangoNaipe.values()){ // para cada valor
                mazo.add(new Carta(valor, palo)); // generamos una carta
            }
        }
    }

    /**
     * Saca una carta de la baraja de forma aleatoria. Se verifica que la carta
     * exista en el mazo
     *
     * @return carta: carta que se saca del mazo
     */
    public Carta sacarCarta() {
        Carta carta;
        boolean encontrada = false;
        do {
            int randomValor = (int) (Math.random() * 13 + 1); // generamos un número aleatorio
            int randomPalo = (int) (Math.random() * 4 + 1); // generamos un palo aleatorio
            carta = crearCarta(randomValor, randomPalo); // creamos la carta llamando al constructor 
            if(mazo.contains(carta)){
                encontrada = true;
            }
        } while (!encontrada); // si la carta no existe en el mazo se repite el proceso
        this.mazo.remove(carta);
        return carta;
    }

    /**
     * Se encarga de desordenar la baraja para colocar las cartas en posiciones
     * aleatorias. Barajará el mazo con las cartas que haya en ella
     */
    public void barajar() {
        // para desordenar el mazo pasamos el contenido desde nuestra instancia 
        // de tipo LinkedHashSet (mantiene sus elementos ordenados) a un ArrayList
        // el cual vamos a desordenar usando el método suffle. A continuación
        // la pasamos de vuelta, una vez desordenada, a nuestra instancia LinkedHashSet
        ArrayList<Carta> listaAuxiliar = new ArrayList<Carta>(); // vamos a utilizar una lista auxiliar para pasarle el mazo y desordenarlo
        listaAuxiliar.addAll(this.mazo); // pasamos las cartas del mazo a la lista auxiliar para desordenarla
        Collections.shuffle(listaAuxiliar); // desordena los elementos de la lista
        mazo.removeAll(mazo); // vaciamos el mazo para volver a rellenarlo, ahora desordenado
        for (Carta carta : listaAuxiliar) {
            this.mazo.add(carta);
        }
    }

    /**
     * Muestra un mazo pasado por parámetro y cuenta el número de cartas que contiene
     */
    public void mostrarMazo(){
        // Iterator<Carta> it = this.mazo.iterator(); // utilizamos un iterador para recorrer nuestro mazo
        int contador = 0;
        for(Carta i: this.mazo)
        {
            System.out.println(i.toString());
            contador++;
        }
        System.out.println("El mazo tiene " + contador + " cartas");
    }
    
    /**
     * A partir de valores numéricos para el valor y el palo de la carta, genera
     * un objeto de tipo Carta
     *
     * @param valor: recibe un valor numérico para el valor de la carta. Entre 1
     * y 13, asignará: 1 = AS, 2 = 2, ..., 10 = 10, 11 = Jack, 12 = Queen, 13 =
     * King.
     * @param palo: recibe un valor numérico para el palo. Entre 1 y 4,
     * asignará: 1 = Picas, 2 = Corazones, 3 = Tréboles, 4 = Diamantes.
     * @return: devuelve un objeto de tipo Carta tras transformar los valores
     * enteros recibidos para su valor y palo.
     */
    public static Carta crearCarta(int valor, int palo) {
        if (valor < 1 || valor > 13) {
            throw new IllegalArgumentException("Valor de la carta erróneo");
        }
        if (palo < 1 || palo > 4) {
            throw new IllegalArgumentException("Palo de la carta erróneo");
        }
        PaloNaipe cartaPalo = null;
        RangoNaipe cartaValor = null;

        switch (palo) {
            case 1 -> cartaPalo = PaloNaipe.PICAS;
            case 2 -> cartaPalo = PaloNaipe.CORAZONES;
            case 3 -> cartaPalo = PaloNaipe.TREBOLES;
            case 4 -> cartaPalo = PaloNaipe.DIAMANTES;
        }

        switch (valor) {
            case 1 -> cartaValor = RangoNaipe.AS;
            case 2 -> cartaValor = RangoNaipe.DOS;
            case 3 -> cartaValor = RangoNaipe.TRES;
            case 4 -> cartaValor = RangoNaipe.CUATRO;
            case 5 -> cartaValor = RangoNaipe.CINCO;
            case 6 -> cartaValor = RangoNaipe.SEIS;
            case 7 -> cartaValor = RangoNaipe.SIETE;
            case 8 -> cartaValor = RangoNaipe.OCHO;
            case 9 -> cartaValor = RangoNaipe.NUEVE;
            case 10 -> cartaValor = RangoNaipe.DIEZ;
            case 11 -> cartaValor = RangoNaipe.JACK;
            case 12 -> cartaValor = RangoNaipe.QUEEN;
            case 13 -> cartaValor = RangoNaipe.KING;
        }
        Carta carta = new Carta(cartaValor, cartaPalo);
        return carta;
    }   
}