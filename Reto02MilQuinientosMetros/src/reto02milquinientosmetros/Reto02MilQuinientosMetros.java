/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto02milquinientosmetros;
import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class Reto02MilQuinientosMetros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        /* Diseñar un algoritmo para calcular la velocidad (en m/s) de los corredores 
        de la carrera de 1500 metros. Se introducirán por teclado los minutos y segundos 
        que dan el tiempo del corredor. El algoritmo debe imprimir el tiempo en minutos y 
        segundos y la velocidad en m/s. */
  
        // TODO code application logic here
        double velocidad = 0;

        //leer tiempo
        int minutos = leerEntero("minutos");
        int segundos = leerEntero("segundos");

        //calcular velocidad
        velocidad = calcularVelocidad(minutos, segundos);
        
        //mostrar resultado
        mostrarResultado(minutos, segundos, velocidad);
        
    }

    //función leer cantidad
    public static int leerEntero(String texto) {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Introduce el número de " + texto + ": ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Introduce un entero");
        }
        num = sc.nextInt();
        return num;
    }

    //función calcular velocidad para 1.500m según el tiempo introducido
    public static double calcularVelocidad(int minutos, int segundos) {
        
        //realizar cálculo. Velocidad = distancia / tiempo
        int velocidad = 1500 / (minutos*60 + segundos);
        return velocidad;
    }
    
    //función mostrar resultado
    public static void mostrarResultado(int minutos, int segundos, double velocidad){
        System.out.println("Tiempo = " + minutos + " minutos, " + segundos + " segundos.");
        System.out.println("Distancia = 1.500 metros.");
        System.out.println("Velocidad = " + velocidad + " metros por segundo.");
    }
}
