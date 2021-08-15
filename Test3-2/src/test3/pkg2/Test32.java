/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3.pkg2;

import java.util.Scanner;

/**
 *
 * @author migue
 */
public class Test32 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
         final char distancia = 'a' - 'A';

        String nombre, ape1, ape2;
        boolean nombreValido = false, ape1Valido = false, ape2Valido = false;
       
        String resultado;
        Scanner teclado = new Scanner(System.in);
        System.out.println(" Escribe tu nombre");
        nombre = teclado.nextLine();

        System.out.println(" escribe tu primer apellido");
        ape1 = teclado.nextLine();

        System.out.println("escrive tu segundo apellido");
        ape2 = teclado.nextLine();
        // desarrollo de los enunciados   ===> ¿ como podria hacer para que no saliera los resultados por pantalla
        // quiero que los datos aparescan solo en el resultado final???

        char vocal = nombre.charAt(nombre.length() - 1);
        if (vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'u') {
            System.out.println(" nombre apto");
            nombreValido = true;
        } else {
            System.out.println("nombre no apto");
        }

        if (ape1.length() % 2 == 0) {
            System.out.println(" apellido 1 apto");
        } else {
            System.out.println(" apellido 1 no apto");
        }

        if (ape2.length() % 2 == 0) {
            System.out.println("el segundo apellido es apto");
        } else {
            System.out.println(" apellido2 no es apto");
        }
        
        
        
    }
    
}
