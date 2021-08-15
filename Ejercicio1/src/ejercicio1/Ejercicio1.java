/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.Scanner;


/**
 *
 * @author miguel
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        
        do {
            System.out.println("Introduce un entero positivo mayor que 1: ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Por favor, introduce un entero positivo mayor que 1: ");
            }
            numero = sc.nextInt();
        } while (numero <= 1);
      do {
        System.out.print(numero + ", ");
        
        if (numero%2 ==0){ // es par
            numero = numero / 2;
        } else {// es impar
            numero = numero * 3 + 1;
        }
      } while (numero>1);
      System.out.println(numero);
    }
}
