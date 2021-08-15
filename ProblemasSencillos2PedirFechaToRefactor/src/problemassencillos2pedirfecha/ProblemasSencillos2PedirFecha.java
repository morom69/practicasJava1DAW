/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemassencillos2pedirfecha;

import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class ProblemasSencillos2PedirFecha {

    /**
     * @param args the command line arguments Pedir el día, mes y año de una
     * fecha e indicar si la fecha es correcta.
     * (habrá que comprobar si el año es bisiesto).
     */
    // Entendemos que cualquier año es válido mientras sea entero positivo
    
    public static void main(String[] args) {
        // TODO code application logic here
        int dia, mes, anio;
        boolean diaok = false, mesok = false;
        
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("Introduce día");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            dia = teclado.nextInt();
        } while (dia <= 0);
        
        do {
            System.out.println("Introduce mes");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            mes = teclado.nextInt();
        } while (mes <= 0);

        do {
            System.out.println("Introduce año");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            anio = teclado.nextInt();
        } while (anio <= 0);

        if (mes <= 12) {
            mesok = true;
            switch (mes) {
                case 1, 3, 5, 7, 8, 10, 12:
                    if (dia <= 31) {
                        diaok = true;
                    }
                    break;
                case 4, 6, 9, 11:
                    if (dia <= 30) {
                        diaok = true;
                    }
                    break;
                default: // febrero
                    if (dia > 29) {
                        diaok = false;
                    } else if (dia == 29) {
                        if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
                            diaok = true;
                        }
                    }
            }
        }
        if (diaok && mesok) {
            System.out.println("La fecha " + dia + "/" + mes + "/" + anio + " es correcta");
        } else {
            System.out.println("La fecha " + dia + "/" + mes + "/" + anio + " no es correcta");
        }
    }

}
