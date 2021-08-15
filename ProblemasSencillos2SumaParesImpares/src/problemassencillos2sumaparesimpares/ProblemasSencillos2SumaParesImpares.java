/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemassencillos2sumaparesimpares;

import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class ProblemasSencillos2SumaParesImpares {

    /**
     * @param args the command line arguments Calcular la suma de todos los
     * números pares por un lado, y por otro, la de los impares, desde 1 hasta
     * N, siendo N un entero positivo introducido por teclado.
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int pares = 0, impares = 0;
        int num = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Introduce un número entero positivo: ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Introduce un entero");
            }
            num = sc.nextInt();
        } while(num<=0);
        
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 0) { //es par
                pares += i;
            } else {  //es impar
                impares += i;
            }
        }
        System.out.println("Suma de pares: " + pares);
        System.out.println("Suma de impares: " + impares);
    }
}
