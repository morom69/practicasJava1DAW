/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class Carton {

    public static final int FILAS = 3;
    public static final int COLUMNAS = 5;
    private int[][] datos; // cart�n, debe ser de 3 x 5

    public Carton() {
        // rellenar el cart�n de n�meros aleatorios entre 1 y 50
        int numero;
        ArrayList<Integer> listaNumeros = new ArrayList<>(); // array auxiliar para generar y guardar los n�meros
        datos = new int[3][5];
        // generamos la lista de n�meros a colocar en el cart�n
        for (int i = 0; i < FILAS * COLUMNAS; i++) {
            do {
                numero = (int) (Math.random() * 50 + 1); // generamos un n�mero aleatorio entre 1 y 50
            } while (listaNumeros.contains(numero));// validamos si ya existe
            listaNumeros.add(numero);
        }
        // rellenamos el cart�n
        numero = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                datos[i][j] = listaNumeros.get(numero);
                numero++;
            }
        }
    }

    public Carton(Carton carton) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                this.datos[i][j] = carton.getValor(FILAS, COLUMNAS);
            }
        }
    }

    public int getValor(int fila, int columna) {
        if ((fila < 0) || (fila > FILAS - 1) || (columna < 0) || (columna > COLUMNAS - 1)) {
            throw new IllegalArgumentException("fila o columna incorrecta");
        }
        return this.datos[fila][columna];
    }

    public void setValor(int fila, int columna, int valor) {
        if ((fila < 0) || (fila > FILAS - 1) || (columna < 0) || (columna > COLUMNAS - 1) || ((valor < 0) && (valor != -1)) || (valor > 50)) {
            throw new IllegalArgumentException("par�metro incorrecto");
        }
        this.datos[fila][columna] = valor;
    }

    /**
     * Revisa si la bola sacada (pasada como par�metro) est� en el cart�n. Si es
     * as� marca esa casilla con -1
     *
     * @param bola indica la bola que ha salido en del bombo y tenemos que
     * revisar si existe en el cart�n.
     * @return true si el cart�n se ha marcado, false en caso contrario.
     */
    public boolean marcarCasilla(int bola) {
        if (bola < 1 || bola > 50) {
            throw new IllegalArgumentException("la bola debe ser entre 1 y 50");
        }
        boolean marcado = false;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (this.getValor(i, j) == bola) {
                    this.setValor(i, j, -1);
                    marcado = true;
                }
            }
        }
        return marcado;
    }

    /**
     * eval�a si al menos una de las filas del cart�n est� completamente marcada
     * (valor -1).
     *
     * @return devuelve true si al menos una de las filas del cart�n est�
     * completamente marcada, false en caso contrario.
     */
    public boolean cantarFila() {
        boolean filaCompleta = true;
        filaCompleta = esFilaCompleta(filaCompleta);
        for (int i = 0; i < FILAS; i++) {
            if (!filaCompleta) {
                filaCompleta = esFilaCompleta(filaCompleta);
            }
        }
        return filaCompleta;
    }

    /**
     * Eval�a una fila del cart�n para ver si est� completamente marcada (valor
     * -1).
     *
     * @param filaCompleta recibe el valor actual de si ya hay alguna fila
     * completa. Actualiza el valor tras evaluar la fila actual.
     * @return devuelve true si la fila evaluada est� completamente marcada,
     * false en caso contrario.
     */
    public boolean esFilaCompleta(boolean filaCompleta) {
        for (int j = 0; j < COLUMNAS; j++) {
            if (this.getValor(0, j) != -1) {
                filaCompleta = false;
            }
        }
        return filaCompleta;
    }

    /**
     * Eval�a si el cart�n est� completamente marcado (valor -1).
     *
     * @return devuelve true si en todas las posiciones del array el contenido
     * es -1. False en caso contrario.
     */
    public boolean cantarBingo() {
        boolean cartonCompleto = true;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < FILAS; j++) {
                if (this.getValor(i, j) != -1) {
                    cartonCompleto = false;
                }
            }
        }
        return cartonCompleto;
    }

    /**
     * Sobreescribimos el m�todo toString() para la clase.
     *
     * @return devuelve un texto con los atributos de la plaza.
     */
    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                resultado += this.getValor(i, j) + "  ";
            }
            resultado += "\n";
        }
        return resultado;
    }
}
