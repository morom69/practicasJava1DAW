/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tresenraya;

import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class TresEnRaya {

    /**
     * @param args the command line arguments
     */
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        char player1 = 'X';
        char player2 = 'O';
        char vacio = '-';
        boolean turno = true; // gestiona los turnos de los jugadores
        char tablero[][] = new char[3][3];
        int fila, columna;
        boolean posicionValida; // para evaluar si una posición es válida
        boolean correcto; // para validar si la posición elegida es correcta

        rellenarMatriz(tablero, vacio);
        while (!finPartida(tablero, vacio)) {
            do {
                mostrarTurnoActual(turno);
                mostrarMatriz(tablero); // dibujamos el tablero
                correcto = false; // valida si la posición elegida es correcta. Se inicializa a false
                fila = pedirInteger("Introduce fila: ");
                columna = pedirInteger("Introduce columna: ");
                posicionValida = validarPosicion(tablero, fila, columna); // validamos si la posición elegida está dentro de las dimensiones del tablero
                if (posicionValida) {
                    // si la posición es válida hay que ver si está vacía 
                    if (hayValorPosicion(tablero, fila, columna, vacio)) {
                        correcto = true; // si la posición está vacía se puede elegir
                    } else {
                        System.out.println("La posición ya está ocupada");
                    }
                } else {
                    System.out.println("La posición no es válida");
                }
            } while (!correcto); // se realiza el bucle mientras no se seleccione una posición disponible

            // si la posición elegida es posible (correcto) insertamos "ficha" según el turno
            if (turno) {
                insertarEn(tablero, fila, columna, player1);
            } else {
                insertarEn(tablero, fila, columna, player2);
            }
            turno = !turno; // cambiamos el turno
        }

        mostrarMatriz(tablero);
        mostrarGanador(tablero, player1, player2, vacio);

    }

    public static void mostrarGanador(char[][] matriz, char J1, char J2, char simboloDefecto) {
        char simbolo = tresEnLinea(matriz, simboloDefecto);
        if (simbolo != simboloDefecto) {
            if (simbolo == J1) {
                System.out.println("Ha ganado Jugador 1 por línea");
            } else {
                System.out.println("Ha ganado Jugador 2 por línea");
            }
            return; // se ha terminado
        }

        simbolo = tresEnColumna(matriz, simboloDefecto);
        if (simbolo != simboloDefecto) {
            if (simbolo == J1) {
                System.out.println("Ha ganado Jugador 1 por columna");
            } else {
                System.out.println("Ha ganado Jugador 2 por columna");
            }
            return; // se ha terminado
        }

        simbolo = tresEnDiagonal(matriz, simboloDefecto);
        if (simbolo != simboloDefecto) {
            if (simbolo == J1) {
                System.out.println("Ha ganado Jugador 1 por diagonal");
            } else {
                System.out.println("Ha ganado Jugador 2 por diagonal");
            }
            return; // se ha terminado
        }
        System.out.println("Empate técnico :) ");
    }

    public static void insertarEn(char[][] matriz, int fila, int columna, char simbolo) {
        matriz[fila][columna] = simbolo;
    }

    public static void mostrarTurnoActual(boolean turno) {
        if (turno) {
            System.out.println("Turno del jugador 1");
        } else {
            System.out.println("Turno del jugador 2");
        }
    }

    public static int pedirInteger(String texto) {
        System.out.println(texto);
        int entero = teclado.nextInt();
        return entero;

    }

    public static void rellenarMatriz(char[][] matriz, char simbolo) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = simbolo;
            }
        }
    }

    public static boolean validarPosicion(char[][] tablero, int fila, int columna) {
        // valida si la posición elegida está dentro de las dimensiones del tablero
        return (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length);
    }

    public static boolean hayValorPosicion(char[][] matriz, int fila, int columna, char simbolo) {
        // validamos si la posición contiene el símbolo pasado por parámetro
        return (matriz[fila][columna] == simbolo);
    }

    public static void mostrarMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "");
            }
            System.out.println("");
        }

    }

    public static boolean matrizLlena(char[][] matriz, char simboloDefecto) {
        boolean resultado = true; // si toda la matriz está llena de símbolos diferentes de vacío 
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if ((matriz[i][j]) == simboloDefecto) { // en cuanto encuentra un símbolo vacío, no está llena
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public static boolean finPartida(char[][] matriz, char simboloDefecto) {
        // es el fin de la partida si toda la matriz está llena o hay tres en línea en una línea, columna o diagonal
        if (matrizLlena(matriz, simboloDefecto)
                || tresEnLinea(matriz, simboloDefecto) != simboloDefecto
                || tresEnColumna(matriz, simboloDefecto) != simboloDefecto
                || tresEnDiagonal(matriz, simboloDefecto) != simboloDefecto) {
            return true;

        }
        return false;
    }

    public static char tresEnLinea(char[][] matriz, char simboloDefecto) {
        char simbolo; 
        boolean coincidencia;
        for (int i = 0; i < matriz.length; i++) { // i => recorremos las filas
            coincidencia = true;
            simbolo = matriz[i][0]; // cogemos el símbolo de la primera posición de la fila
            if (simbolo != simboloDefecto) { // evaluamos si no está vacío
                for (int j = 1; j < matriz[0].length; j++) { // para el resto de símbolos de la misma fila
                    if (simbolo != matriz[i][j]) {  // evaluamos si es igual que el primero
                        coincidencia = false;
                    }
                }
                if (coincidencia) { // si hay una línea de símbolos iguales
                    return simbolo; // devolvemos el símbolo, será el ganador
                }
            }
        }
        return simboloDefecto;

    }

    public static char tresEnColumna(char[][] matriz, char simboloDefecto) {
        char simbolo;
        boolean coincidencia;
        for (int j = 0; j < matriz.length; j++) { // j => recorremos las columnas
            coincidencia = true;
            simbolo = matriz[0][j]; // cogemos el símbolo de la primera posición de la columna
            if (simbolo != simboloDefecto) { // evaluamos si no está vacío
                for (int i = 1; i < matriz[0].length; i++) { // para el resto de símbolos de la misma columna
                    if (simbolo != matriz[i][j]) { // evaluamos si es igual que el primero
                        coincidencia = false;
                    }
                }
                if (coincidencia) { // si hay una columna de símbolos iguales
                    return simbolo; // devolvemos el símbolo, será el ganador
                }
            }
        }
        return simboloDefecto;
    }

    public static char tresEnDiagonal(char[][] matriz, char simboloDefecto) {
        char simbolo;
        boolean coincidencia = true;
        //diagonal primera de [0,0] a [2,2]
        simbolo = matriz[0][0]; // guardamos el símbolo en la primera posición de la diagonal
        if (matriz[0][0] != simboloDefecto) { // si el primer símbolo de la diagonal no es vacío
            for (int i = 1; i < matriz.length; i++) { // recorremos la diagonal
                if (simbolo != matriz[i][i]) { // evaluamos si es igual que el primero 
                    coincidencia = false;
                }
            }
            if (coincidencia) { // si hay una diagonal de símbolos iguales
                return simbolo; // devolvemos el símbolo, será el ganador
            }
        }

        //diagonal segunda de [0,2] a [2,0]
        simbolo = matriz[0][2]; // guardamos el símbolo en la primera posición de la diagonal
        if (matriz[0][0] != simboloDefecto) { // si el primer símbolo de la diagonal no es vacío
            for (int i = 1, j = 1; i < matriz.length; i++, j--) { // recorremos la diagonal
                if (simbolo != matriz[i][j]) { // evaluamos si es igual que el primero
                    coincidencia = false;
                }
            }
            if (coincidencia) { // si hay una diagonal de símbolos iguales
                return simbolo; // devolvemos el símbolo, será el ganador
            }
        }
        return simboloDefecto;

    }
}
