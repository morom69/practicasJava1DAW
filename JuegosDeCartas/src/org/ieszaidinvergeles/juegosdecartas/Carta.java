package org.ieszaidinvergeles.juegosdecartas;

import java.util.Objects;

/**
 *
 * @author Miguel Moro
 */
public class Carta implements Comparable {

    private RangoNaipe valor;
    private PaloNaipe palo;

    public Carta(RangoNaipe valor, PaloNaipe palo){ // throws IllegalArgumentException {
        this.valor = valor;
        this.palo = palo;
    }

    public PaloNaipe getPalo() { //Devuelve el palo de la carta
        return this.palo;
    }

    public RangoNaipe getValor() { // Devuelve el rango de la carta
        return this.valor;
    }

    @Override
    public String toString() {
        return this.getValor() + " - " + this.getPalo();
    }

    /**
     * Comprueba si dos cartas son iguales
     *
     * @param carta: carta que queremos comparar
     * @return devuelve true si ambas cartas son iguales, false en caso
     * contrario.
     */
    @Override
    public boolean equals(Object o) {
        boolean resultado = false;
        if(o instanceof Carta){
            Carta c = (Carta)o;
            if ((this.valor == c.valor) && (this.palo == c.palo)){
                resultado = true;
            }
        }
        return resultado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.valor);
        hash = 41 * hash + Objects.hashCode(this.palo);
        return hash;
    }

    /**
     * Para cumplir con los requerimientos de la iterfaz Comparable. Compara si
     * una carta es mayor, menor o igual que otra pasada por parámetro, teniendo
     * en cuenta el valor numérico de la carta.
     *
     * @param miObjeto: carta con la que compararemos la carta actual.
     * @return devuelve lo siguiente: -1 si el resultado de la carta actual es menor
 que el de la carta pasada por parámetro, 1 si el resultado de la carta actual
 es mayor que el de la carta pasada por parámetro, 0 si ambos valores son
 iguales.
     */
    @Override
    public int compareTo(Object miObjeto) {
        int resultado = 1;
        Carta c = (Carta) miObjeto;
        if (this.valor.getValue() < c.valor.getValue()) {
            resultado = -1;
        } else if (this.valor.getValue() == c.valor.getValue()) {
            resultado = -0;
        }
        return resultado;
    }

    public enum PaloNaipe {
        PICAS("Picas"), CORAZONES("Corazones"), TREBOLES("Tréboles"), DIAMANTES("Diamantes");

        private String miPalo;

        private PaloNaipe(String miPalo) {
            this.miPalo = miPalo;
        }

        public String getName() {
            return this.miPalo;
        }

        @Override
        public String toString() {
            String simbolo = " ";
            switch (this.getName()) {
                case "Picas" ->  {
                    //simbolo = "?";
                    simbolo = "PiCaS?";
                }
                case "Corazones" ->  {
                    //simbolo = "?";
                    simbolo = "CoRaZoNeS?";
                }
                case "Tréboles" ->  {
                    //simbolo = "?";
                    simbolo = "TrEbOlEs?";
                }
                case "Diamantes" ->  {
                    //simbolo = "?";
                    simbolo = "DiAmAnTeS?";
                }
            }
            return simbolo;
        }
    }

    public enum RangoNaipe {
        AS("As", 11), DOS("Dos", 2), TRES("Tres", 3), CUATRO("Cuatro", 4), CINCO("Cinco", 5), SEIS("Seis", 6), SIETE("Siete", 7), OCHO("Ocho", 8), NUEVE("Nueve", 9), DIEZ("Diez", 10), JACK("Jack", 10), QUEEN("Queen", 10), KING("King", 10);

        private String naipe;
        private int valor;

        private RangoNaipe(String naipe, int valor) {
            this.naipe = naipe;
            this.valor = valor;
        }

        public String getName() {
            return this.naipe;
        }

        public int getValue() {
            return this.valor;
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }
}
