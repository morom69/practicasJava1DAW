/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author migue
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numJugadores, bola, indice;
        boolean filaCantada = false, bingoCantado = false;
        ArrayList<Carton> jugadores = new ArrayList<>(); // estructura utilizada para el manejo de los cartones de los jugadores
        Carton c = null;
        System.out.println("Introduzca el n�mero de jugadores:");
        numJugadores = leerEnteroEntreLimites(2, 6);
        // generamos los cartones
        for (int i = 0; i < numJugadores; i++) {
            c = new Carton();
            jugadores.add(c);
        }
        // mostramos los cartones
        for (int i = 0; i < jugadores.size(); i++) {
            mostrarCarton(jugadores.get(i));
        }
        // y empieza el juego
        do {
            bola = sacarBola(); // sacamos bola
            System.out.println("Eeeeeel " + bola); // y la cantamos (se muestra por consola
            // marcamos las casillas en los cartones
            for (int i = 0; i < jugadores.size(); i++) {
                if (jugadores.get(i).marcarCasilla(bola)) { // si se ha marcado casilla se muestra el cart�n
                    mostrarCarton(jugadores.get(i));
                }
            }
            // evaluamos si hay que cantar fila si no se ha cantado todav�a
            indice = 0;
            if (!filaCantada) {
                do {
                    filaCantada = jugadores.get(indice).cantarFila();
                    indice++;
                    if (filaCantada) {
                        System.out.println("El jugador " + indice + " ��HA CANTADO L�NEA!!");
                        mostrarCarton(jugadores.get(indice - 1));
                    }
                } while (!filaCantada && indice < numJugadores);
            } else { // si ya se ha cantado fila evaluamos si hay bingo
                do {
                    bingoCantado = jugadores.get(indice).cantarBingo();
                    indice++;
                    if (bingoCantado) {
                        System.out.println("El jugador " + indice + " ��HA CANTADO BINGO!!");
                        mostrarCarton(jugadores.get(indice - 1));
                    }
                } while (!filaCantada && indice < numJugadores);
            }
        } while (!bingoCantado);
        System.out.println("��El jugador " + (indice + 1) + " ha cantado bingo!!");
    }

    /**
     * Muestra por pantalla el cart�n pasado como par�metro.
     *
     * @param recibe un cart�n para mostrarlo por pantalla
     */
    public static void mostrarCarton(Carton carton) {
        System.out.println(carton.toString());
        System.out.println("");
    }

    /**
     * Devuelve un n�mero aleatorio entre 1 y 50
     *
     * @return devuelve un n�mero aleatorio entre 1 y 50
     */
    public static int sacarBola() {
        return (int) (Math.random() * 50 + 1);
    }

    /**
     * Pide al usuario por teclado introducir un entero mostrando al preguntar
     * el texto facilitado como par�metro y lo devuelve. Se valida que el valor
     * introducido se encuentre entre los l�mites inferior y superior
     * facilitados como par�metros (ambos incluidos).
     *
     * @param inferior: valor inferior. Se validar� que el n�mero introducido no
     * sea menor que este valor.
     * @param superior: valor superior. Se validar� que el n�mero introducido no
     * sea mayor que este valor.
     * @return devuelve un entero entre los dos l�mites pasados como par�metros
     * (ambos incluidos).
     */
    public static int leerEnteroEntreLimites(int inferior, int superior) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre " + inferior + " y " + superior);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num < inferior || num > superior);
        return num;
    }
}
