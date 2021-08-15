/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metododenewton;

import java.util.Scanner;

/**
 *
 * @author deifont
 */
public class MetodoDeNewton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double z = 1;
        int x = 2, N = 1000;

        //Leer datos por teclado
        x = leerNumeroEntero("Introduzca el valor de x (mayor que 0): ", 1);
        N = leerNumeroEntero("Introduzca el número de iteraciones (mayor que 0): ", 1);
        z = leerNumeroDouble("Introduzca el valor inicial z: ");
        
        //Calculo la raiz cuadrada de x
        for (int i = 0; i < N; i++) {
            z = z - (z * z - x) / (2 * z);
        }

        System.out.println("La raiz cuadrada de " + x + " es: " + z);

    }

    public static int leerNumeroEntero(String mensaje, int minimo) {
        Scanner sc = new Scanner(System.in);
        int numero;
        do {
            System.out.println(mensaje);
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("No ha introducido un número. Vuelva a introducirlo: ");
            }
            numero = sc.nextInt();
        } while (numero < minimo);

        return numero;
    }

    public static double leerNumeroDouble(String mensaje, double minimo) {
        Scanner sc = new Scanner(System.in);
        double numero;
        do {
            System.out.println(mensaje);
            while (!sc.hasNextDouble()) {
                sc.next();
                System.out.println("No ha introducido un número. Vuelva a introducirlo: ");
            }
            numero = sc.nextDouble();
        } while (numero < minimo);

        return numero;
    }

    public static double leerNumeroDouble(String mensaje) {
        Scanner sc = new Scanner(System.in);
        double numero;
        System.out.println(mensaje);
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.println("No ha introducido un número. Vuelva a introducirlo: ");
        }
        numero = sc.nextDouble();

        return numero;
    }
}
