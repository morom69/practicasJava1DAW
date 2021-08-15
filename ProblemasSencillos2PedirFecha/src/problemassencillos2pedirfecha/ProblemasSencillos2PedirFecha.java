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
     * fecha e indicar si la fecha es correcta. Con meses de 28/29, 30 y 31 días
     * (habrá que comprobar si el año es bisiesto).
     */
    /**
     * Entendemos que cualquier año es válido mientras sea entero positivo
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int dia, mes, anio;
        boolean diaok = false, mesok = false;

        dia = leerEnteroPositivo("día");
        mes = leerEnteroPositivo("mes");
        anio = leerEnteroPositivo("año");

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
                        //ver si es bisiesto
                        if (esBisiesto(anio)) {
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

    public static boolean esBisiesto(int year) {
        /**
         * Un año es bisiesto si es divisible por 4, excepto el último de cada
         * siglo, aquellos divisibles por 100, que para ser bisiestos, también
         * deben ser divisibles por 400
         */
        boolean bisiesto = false;
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            bisiesto = true;
        }
        return bisiesto;
    }

    public static int leerEnteroPositivo(String texto) {
        int num = 0;

        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce " + texto);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num <= 0);
        return num;
    }

}
