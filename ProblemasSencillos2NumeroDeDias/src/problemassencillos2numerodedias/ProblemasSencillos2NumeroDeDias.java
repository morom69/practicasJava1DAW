/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemassencillos2numerodedias;

import java.util.Scanner;

/**
 *
 * @author miguel Calcula el número de años, meses, semanas y días, dado un
 * determinado número de días introducido por teclado
 */
public class ProblemasSencillos2NumeroDeDias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int tiempoRestante, anios, meses, semanas, dias;

        do {
            System.out.println("Por favor introduce un número de días: ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Por favor introduce un entero: ");
            }
            tiempoRestante = sc.nextInt();
        } while (tiempoRestante <= 0);

        System.out.print(tiempoRestante + " días corresponden a ");
        anios= tiempoRestante/365;
        tiempoRestante = tiempoRestante - anios * 365;
        meses = tiempoRestante/30;
        tiempoRestante = tiempoRestante - meses * 30;
        semanas = tiempoRestante/7;
        dias = tiempoRestante%7;
        
        System.out.println(anios + " años, " + meses + " meses, " + semanas + " semanas y " + dias + " días");   
    }
}
