/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemassencillos2capicua;

import java.util.Scanner;

/**
 *
 * @author miguel Pedir un número positivo y decir si es capicúa. No se pueden
 * usar métodos de la clase String
 */
public class ProblemasSencillos2Capicua {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int numero, oremun = 0, pendiente = 0; //oremun es número al revés :)
        boolean capicua = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("Vamos a averiguar si un número es capicúa");

        do {
            System.out.println("Introduce un entero positivo: ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Por favor, introduce un entero positivo: ");
            }
            numero = sc.nextInt();
        } while (numero <= 0);

        pendiente = numero;
        do {
            oremun = oremun + pendiente % 10;
            pendiente = pendiente / 10;
            if (pendiente > 0) {
                oremun = oremun * 10;
            }
        } while (pendiente > 0);
        if(numero == oremun){
            System.out.println("El número " + numero + " es capicúa.");
        }else{
            System.out.println("El número " + numero + " NO es capicúa.");
        }

        System.out.println(numero);
        System.out.println(oremun);

    }

}
