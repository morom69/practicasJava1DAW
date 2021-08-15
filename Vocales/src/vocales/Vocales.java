/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocales;

import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class Vocales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String texto = "";
        int numVocales = 0;
        int ocurrencias;
        String vocales = "AEIOU";
        String vocalesBuscadas;
        char c;
        System.out.println("Vamos a contar el número de vocales en un texto, Introduce el texto ¿Quiere probar? ");
        while (leerSiNo()) {
            vocalesBuscadas = "";
            ocurrencias = 0;
            texto = leerString().toUpperCase();
            System.out.println("¿Cuántas vocales quieres contar (entre 1 y 5)?");
            numVocales = leerEnteroEntreLimites(1, 5);
            for (int i = 0; i < numVocales; i++) {
                System.out.println("Introduce la vocal " + (i + 1) + ": ");
                do {
                    c = leerCaracter();
                } while (!vocalEncontrada(vocales, c) || vocalEncontrada(vocalesBuscadas, c));
                // repetimos mientras la letra introducida no se encuentre entre la lista de vocales
                // o se encuentre entre las ya introducidas
                vocalesBuscadas += c;
            }
            ocurrencias = contarVocales(texto, vocalesBuscadas);
            System.out.println("Se han encontrado " + ocurrencias + " ocurrencias.");
            System.out.println("Desea volver a contar vocales en otro texto?");
        }
        System.out.println("Fin del programa");
    }

    public static int contarVocales(String cadena, char vocal) {
        int cuenta = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == vocal) {
                cuenta++;
            }
        }
        return cuenta;
    }

    public static int contarVocales(String cadena, String listaVocales) {
        int acumulado = 0;
        for (int i = 0; i < listaVocales.length(); i++) {
            acumulado += contarVocales(cadena, listaVocales.charAt(i));
        }
        return acumulado;
    }

    public static boolean vocalEncontrada(String lista, Character c) {
        boolean encontrada = false;
        for (int i = 0; i < lista.length() && !encontrada; i++) {
            if (lista.charAt(i) == c) {
                encontrada = true;
            }
        }
        return encontrada;
    }

    public static String leerString() {
        Scanner teclado = new Scanner(System.in);
        String lectura;
        System.out.println("Introduzca un texto donde buscar las vocales:");
        lectura = teclado.nextLine();
        return lectura;
    }

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

    public static char leerCaracter() {
        char caracter;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un carácter: ");
        caracter = teclado.next().charAt(0);
        if (Character.isLowerCase(caracter)) { //convertimos a mayúsculas
            caracter = Character.toUpperCase(caracter);
        }
        return caracter;
    }

    /**
     * Pide al usuario por teclado elegir una opción entre Sí o No y lo devuelve
     * como boolean. Si se introduce una S se devolverá true y si se introduce
     * una N se devolverá false, independientemente para ambas si el carácter es
     * en mayúscula o minúscula. Se realiza una validación hasta que el dato
     * introducido es válido.
     *
     * @return devuelve un booleano, true si se ha seleccionado "Sí" o false si
     * se ha seleccionado "No".
     */
    public static boolean leerSiNo() {
        char caracter;
        boolean primero = true, resultado = false;
        do {
            if (!primero) {
                System.out.println("Debe introducir S o N");
            }
            System.out.println("(S/N)?");
            caracter = leerCaracter();
            primero = false;
            if (caracter == 's' || caracter == 'S') {
                resultado = true;
            } else if (caracter == 'n' || caracter == 'N') {
                resultado = false;
            }
        } while (caracter != 's' && caracter != 'S' && caracter != 'n' && caracter != 'N');
        return resultado;
    }

}
