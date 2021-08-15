/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usofunciones;

import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class UsoFunciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //MisFunciones f = new MisFunciones();
        Scanner lectura = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        int num, dia, mes, anio;

        do {
            System.out.println(" ");
            System.out.println("O  P  C  I  O  N  E  S");
            System.out.println("**********************");
            System.out.println("1 - Para operaciones con un número:");
            System.out.println("    - Se calcula su cuadrado");
            System.out.println("    - Se verifica si es capicúa");
            System.out.println("    - Se verifica si es primo");
            System.out.println("    - Se verifica si es perfecto");
            System.out.println("2 - Para operaciones con fechas");
            System.out.println("    - Se verifica si la fecha es correcta");
            System.out.println("    - Se verifica si el año es bisiesto");
            System.out.println("3 - Para salir");
            System.out.println("Escribe una de las opciones: ");
            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 3: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            switch (opcion) {
                case 1:
                    //leer número
                    num = MisFunciones.leerEnteroPositivo();
                    //potencia
                    System.out.println("El cuadrado de " + num + " es " + MisFunciones.potencia(num, 2));
                    //evalúa si es capicúa
                    if (MisFunciones.esCapicua(num)) {
                        System.out.println(num + " es capicúa");
                    } else {
                        System.out.println(num + " no es capicúa");
                    }
                    //evalúa si es primo
                    if (MisFunciones.esPrimo(num)) {
                        System.out.println(num + " es primo");
                    } else {
                        System.out.println(num + " no es primo");
                    }
                    //evalúa si es perfecto
                    if (MisFunciones.esPerfecto(num)) {
                        System.out.println(num + " es perfecto");
                    } else {
                        System.out.println(num + " no es perfecto");
                    }
                    break;

                case 2:
                    //leer fecha
                    System.out.print("Necesitamos un día. ");
                    dia=MisFunciones.leerEnteroPositivo(31);
                    System.out.print("Necesitamos un mes. ");
                    mes=MisFunciones.leerEnteroPositivo(12);
                    System.out.print("Necesitamos un año. ");
                    anio=MisFunciones.leerEnteroPositivo();                    
                    //verificar si la fecha es correcta
                    if (MisFunciones.fechaCorrecta(dia,mes,anio)) {
                        System.out.println("La fecha " + dia + "/" + mes + "/" + anio +" es corecta");
                    } else {
                        System.out.println("La fecha " + dia + "/" + mes + "/" + anio +" no es corecta");
                    }                    
                    //verificar si el año es bisiesto
                    if (MisFunciones.esBisiesto(anio)) {
                        System.out.println("El año " + anio + " es bisiesto");
                    } else {
                        System.out.println("El año " + anio + " no es bisiesto");
                    }                    
                    break;
            }
        } while (opcion != 3);
    }
}
