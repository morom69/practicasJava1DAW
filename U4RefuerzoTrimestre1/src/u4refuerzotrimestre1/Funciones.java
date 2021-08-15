/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4refuerzotrimestre1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author migue
 */
public class Funciones {

    public static int leerEnteroPositivo() {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo: ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Debe ser un entero: ");
            }
            num = teclado.nextInt();
        } while (num <= 0);
        return num;
    }

    public static double leerDoublePositivo() {
        double num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un número: ");
            while (!teclado.hasNextDouble()) {
                teclado.next();
                System.out.println("No válido. Debe ser un número: ");
            }
            num = teclado.nextDouble();
        } while (num < 0);
        return num;
    }

    //pedir por teclado los valores y guardarlos en List
    //la lectura acaba cuando se introduzca -1
    public static List<Double> leerValoresLista() {
        List<Double> valores = new ArrayList();
        double n;
        System.out.println("Introduce notas, cero para terminar");
        do {
            n = leerDoublePositivo();
            if (n != 0) {
                valores.add(n);
            }
        } while (n != 0);
        return valores;
    }

    //recorrer List y sumar todos sus elementos
    public static double calcularSumaDouble(List<Double> valores) {
        double suma = 0;
        Iterator it = valores.iterator();
        while (it.hasNext()) {
            suma = suma + (double) it.next();
            //next() devuelve un dato de tipo Object. Hay que convertirlo a Double
        }
        return suma;
    }

}
