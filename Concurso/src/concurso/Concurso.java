/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurso;

/**
 *
 * @author miguel
 */
import java.util.Scanner;

public class Concurso {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        
        /*
        No es necesaria la constante DISTANCIA, puesto que no se van a hacer
        validaciones entre mayúsculas o minúsculas, por lo que se elimina
        */
        // Variables de entrada
        String nombre, apellido1, apellido2;

        // Variables de salida
        String resultado;

        // Variables auxiliares
        boolean nombreApto, apellidosAptos, concursanteApto;
        /*
        Se añaden nuevas variables para la validación del numero de caracteres
        para o impar de los dos apellidos
        */
        boolean apellido1par, apellido2par;

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        /*
        No es necesario validación de datos, cualquier caracter introducido 
        se tomará como válido
        */
        System.out.println("CONCURSO DE TV");
        System.out.println("--------------");
        System.out.println();
        System.out.println("Nombre del concursante: ");
        nombre = teclado.nextLine();
        System.out.println("Primer apellido del concursante: ");
        apellido1 = teclado.nextLine();
        System.out.println("Segundo apellido del concursante: ");
        apellido2 = teclado.nextLine();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        //nombre apto si termina en vocal
        /*
        Verificamos que el último caracter del nombre es una vocal. 
        */
        if ((nombre.charAt(nombre.length() - 1) == 'a')
                || (nombre.charAt(nombre.length() - 1) == 'e')
                || (nombre.charAt(nombre.length() - 1) == 'i')
                || (nombre.charAt(nombre.length() - 1) == 'o')
                || (nombre.charAt(nombre.length() - 1) == 'u')) {
            nombreApto = true;
        } else {
            nombreApto = false;
        }
        //sus dos apellidos tienen a la vez un número de caracteres par o impar 
        //es decir, si un apellido tiene un número de caracteres par y el otro impar no sería apto
        apellido1par = apellido1.length() % 2 == 0;
        apellido2par = apellido2.length() % 2 == 0;
        apellidosAptos = apellido1par == apellido2par;
        concursanteApto = nombreApto && apellidosAptos;

        resultado = concursanteApto ? "APTA" : "NO APTA";

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------        
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println("La persona es " + resultado + " para el concurso.");

        System.out.println();
        System.out.println("Fin del programa. Bye!");
    }
}
