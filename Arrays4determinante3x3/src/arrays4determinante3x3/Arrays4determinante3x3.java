/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays4determinante3x3;

import java.util.Scanner;

/**
 * Escribe una función que calcule el determinante de una matriz de 3x3 (3 filas
 * y 3 columnas).
 *
 * @author Miguel Moro
 */
public class Arrays4determinante3x3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //creamos el array original
        int determinante = 0;
        int array[][] = new int[3][3];

        //rellenamos el array 
        System.out.println("Vamos a rellenar el array de 3 x 3:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Posición " + (i + 1) + "," + (j + 1) + ": ");
                array[i][j] = leeEntero();
            }
        }

        //mostramos el array 
        System.out.println();
        System.out.println("Array:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }

        //calculamos el determinante
        determinante = calculaDeterminante(array);
        System.out.println();
        System.out.println("Determinante = " + determinante);
    }

    // demasiado complejo, son operaciones fijas, mejor hacerlo sin bucle
    /*
    public static int calculaDeterminante(int[][] array) {
        int acumulador = 0;
        //y calculamos el determinante
        System.out.println();
        System.out.println("Sumas para el determinante:");
        int a = 1;
        int b = 1;
        int c, d, e, f;
        for (int i = 0; i < 3; i++) {
            c = a + 1;
            d = b + 1;
            e = a + 2;
            f = b + 2;
            if (d > 3) {
                d = d - 3;
            }
            if (f > 3) {
                f = f - 3;
            }
            System.out.println(array[a - 1][b - 1] + " x " + array[c - 1][d - 1] + " x " + array[e - 1][f - 1] + " = +" + (array[a - 1][b - 1] * array[c - 1][d - 1] * array[e - 1][f - 1]));
            acumulador = acumulador + (array[a - 1][b - 1] * array[c - 1][d - 1] * array[e - 1][f - 1]);
            b = b + 1;
        }
        System.out.println("Acumulador = " + acumulador);

        System.out.println();
        System.out.println("Restas para el determinante:");
        a = 1;
        b = 3;
        for (int i = 0; i < 3; i++) {
            c = a + 1;
            d = b - 1;
            e = a + 2;
            f = b - 2;
            if (d < 1) {
                d = 3 + d;
            }
            if (f < 1) {
                f = 3 + f;
            }
            System.out.println(array[a - 1][b - 1] + " x " + array[c - 1][d - 1] + " x " + array[e - 1][f - 1] + " = -" + (array[a - 1][b - 1] * array[c - 1][d - 1] * array[e - 1][f - 1]));
            acumulador = acumulador - (array[a - 1][b - 1] * array[c - 1][d - 1] * array[e - 1][f - 1]);
            b = b - 2;
            if (b < 0) {
                b = 3 + b;
            }
        }
        return acumulador;
    }*/
    public static int calculaDeterminante(int[][] array) {
        int determinante = 0;
        determinante = ((array[0][0] * array[1][1] * array[2][2]) + (array[0][1] * array[1][2] * array[2][0]) + (array[0][2] * array[1][0] * array[2][1])) - ((array[0][2] * array[1][1] * array[2][0]) + (array[0][0] * array[1][2] * array[2][1]) + (array[0][1] * array[1][0] * array[2][2]));
        return determinante;
    }

    public static int leeEntero() {
        Scanner teclado = new Scanner(System.in);
        int entero;
        do {
            //System.out.println("Introduzca un valor entero positivo: ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("El valor introducido no es un entero positivo, vuelva a intentarlo: ");
            }
            entero = teclado.nextInt();
        } while (entero < 0);
        return entero;
    }

}
