/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4refuerzotrimestre1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author migue
 */
public class U4RefuerzoTrimestre1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner lectura = new Scanner(System.in);
        int opcion;
        // notas leídas por teclado
        double nota = 0, suma = 0, media = 0;
        List<Double> notas = new ArrayList();
        // múltiplos de 3
        int n;
        // estadísticas asignatura
        int suspensos, aprobados, notables, sobresalientes;
        double total;
        // tarificación telefónica
        int minutos, segundos, euros, centimos;
        boolean fraccion;
        // alquiler automóviles
        int kilometros;

        do {
            System.out.println();
            System.out.println("MENÚ DE OPCIONES:");
            System.out.println("=================");
            System.out.println();
            System.out.println("1.Calcula la media de varias notas leídas por teclado");
            System.out.println("2.Mostrar los N primeros múltiplos de 3. N será introducido por teclado y deberá ser un entero positivo");
            System.out.println("3.Datos estadísticos de una asignatura: % alumnos suspensos, aprobados, notables y sobresalientes de la asignatura");
            System.out.println("4.Calcular tarificación telefónica: 15 cent/min los 3 primeros minutos, 7 cent cada minuto o fracción restantes");
            System.out.println("5.Alquiler de automóviles, cobro por km. Menos de 300km = 500€; entre 300 y 1000 +2€/km; más de 1000 +1€km");
            System.out.println("6.Números perfectos menores de 1000");
            System.out.println("7.Salir del programa");

            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 7: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            switch (opcion) {
                case 1: // Calcula la media de varias notas leídas por teclado            
                    notas = Funciones.leerValoresLista();
                    suma = Funciones.calcularSumaDouble(notas);
                    System.out.println("Nota media: " + suma / notas.size());
                    break;
                case 2: // Mostrar los N primeros múltiplos de 3. N será introducido por teclado y deberá ser un entero positivo
                    System.out.println("N Primeros múltiplos de 3");
                    n = Funciones.leerEnteroPositivo();
                    for (int i = 1; i < n; i++) {
                        System.out.print(3 * i + " - ");
                    }
                    System.out.println(3 * n);
                    break;
                case 3: // Datos estadísticos de la asignatura
                    System.out.println("Cálculo de estadísticas de la asignatura (suspensos, aprobados, notables y sobresalientes)");
                    System.out.print(" Introduce el número de suspensos: ");
                    suspensos = Funciones.leerEnteroPositivo();
                    System.out.print(" Introduce el número de aprobados: ");
                    aprobados = Funciones.leerEnteroPositivo();
                    System.out.print(" Introduce el número de notables: ");
                    notables = Funciones.leerEnteroPositivo();
                    System.out.print(" Introduce el número de sobresalientes: ");
                    sobresalientes = Funciones.leerEnteroPositivo();
                    total = suspensos + aprobados + notables + sobresalientes;
                    System.out.println("Total de alumnos: " + (int) total);
                    System.out.printf("Han superado la asignatura el %.2f por ciento del total %n", (total - suspensos) * 100 / total);
                    System.out.printf("El total de suspensos es el %.2f por ciento del total %n", (suspensos * 100 / total));
                    System.out.printf("El total de aprobados es el %.2f por ciento del total %n", (aprobados * 100 / total));
                    System.out.printf("El total de notables es el %.2f por ciento del total %n", (notables * 100 / total));
                    System.out.printf("El total de sobresalientes es el %.2f por ciento del total %n", (sobresalientes * 100 / total));
                    break;
                case 4: // Calcular tarificación telefónica
                    fraccion = false;
                    System.out.println("Tarificación:");
                    System.out.println("15 céntimos / minuto los tres primeros minutos");
                    System.out.println("7 céntimos por cada minuto o fracción adicional");
                    System.out.println("Introduzca el número de segundos que ha durado la llamada: ");
                    segundos = Funciones.leerEnteroPositivo();
                    minutos = segundos / 60;
                    if (minutos * 60 != segundos) {
                        fraccion = true;
                    }
                    if (minutos >= 3) {
                        centimos = 45 + (minutos - 3) * 7;
                        if (fraccion) {
                            centimos += 7;
                        }
                    } else {
                        centimos = minutos * 15;
                        if (minutos < 3 && fraccion) {
                            centimos += 15;
                        }
                    }
                    euros = centimos / 100;
                    centimos -= centimos / 100;
                    System.out.println("El coste de una llamada de " + minutos + " minutos, " + (segundos - minutos * 60) + " segundos es de " + euros + " euros, " + centimos + " céntimos.");
                    break;
                case 5: // Alquiler de automóviles, cobro por km
                    System.out.println("Alquiler vehículos, cobro por Km:");
                    System.out.println("Si no supera los 300 Km se deberá cobrar 500€");
                    System.out.println("Entre 300 Km y 1000 Km se cobra 500€ más el kilometraje excedente a los 300 a 2€/Km");
                    System.out.println("Para más de 1000 Km se cobra 500€ más el kilometraje excedente a los 300 a 1€/Km");
                    System.out.println("Introduzca el número de Kms recorridos: ");
                    kilometros = Funciones.leerEnteroPositivo();
                    euros = 500;
                    if (kilometros > 1000) {
                        euros += (kilometros - 300);
                    } else if (kilometros > 300) {
                        euros += (kilometros - 300) * 2;
                    }
                    System.out.println("El coste del alquiler por " + kilometros + " kilómetros es de " + euros + " euros.");
                    break;
                case 6: // Números perfectos menores de 1000
                    suma = 0;
                    System.out.println("Números perfectos entre 1 y 1000: ");
                    for (int i = 1; i <= 1000; i++) {      // número a validar
                        suma = 0;
                        for (int j = 1; j < i; j++) {    // divisores, desde 1 hasta i -1                          
                            if (i % j == 0) {
                                suma = suma + j; 
                            }
                        }
                        if (i == suma) {             // el numero es perfecto              
                            System.out.print(i + " - ");
                        }
                    }
                    break;
                case 7: // Salir del programa
                    break;
            }

        } while (opcion
                != 7);
    }

}
