/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemassencillos2pedirhora;

import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class ProblemasSencillos2PedirHora {

    /**
     * @param args the command line arguments Pedir una hora de la forma hora,
     * minutos y segundos, y mostrar la hora en el siguiente segundo.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int horas, minutos, segundos;

        //leemos horas
        horas = leerEnteroPositivo("horas", 24);
        //leemos minutos
        minutos = leerEnteroPositivo("minutos", 60);
        //leemos segundos
        segundos = leerEnteroPositivo("segundos", 60);

        System.out.println("la hora leída es: " + horas + ":" + minutos + ":" + segundos);
        System.out.println("si sumamos 1 segundo, la nueva hora será:");

        //procesamiento
        segundos++;
        if (segundos == 60) {
            segundos = 0;
            minutos++;
        }
        if (minutos == 60) {
            minutos = 0;
            horas++;
        }
        if (horas == 24){
            horas = 0;
            System.out.println("ha empezado un nuevo día");
        }

        //imprimimos la nueva hora:
        System.out.println(horas + ":" + minutos + ":" + segundos);
    }

    public static int leerEnteroPositivo(String text, int limite) {
        int num = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Introduce " + text + " menor que " + limite + ": ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Debe ser mayor que cero y menor que " + limite + ": ");
            }
            num=sc.nextInt();
        } while (num <= 0 || num >= limite);
        return num;
    }
}
