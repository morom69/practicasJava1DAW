/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test3;


import java.util.Scanner;

/**
 *
 * @author c
 */
public class Test3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final char distancia = 'a' - 'A';

        String nombre, ape1, ape2;

        //boolean nombresAptos = true, nombreApto = true, ape1Apto = true, ape2Apto = true;
        //MM
        boolean nombresAptos = false, nombreApto = false, ape1Apto = false, ape2Apto = false;
        
        Scanner teclado = new Scanner(System.in);
        System.out.println(" Escribe tu nombre");
        nombre = teclado.nextLine();

        System.out.println(" escribe tu primer apellido");
        ape1 = teclado.nextLine();

        System.out.println("escribe tu segundo apellido");
        ape2 = teclado.nextLine();

        //if (nombreApto) {
            char vocal = nombre.charAt(nombre.length() - 1);
            // MM

            //if (vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == '0' || vocal == 'u') {
            //MM
            if (vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'u') {
                nombreApto = true;
            }

        //} else if (ape1Apto) {
            if (ape1.length() % 2 == 0) {
                ape1Apto = true;
            }

        //} else if (ape2Apto) {
            if (ape2.length() % 2 == 0) {
                ape2Apto = true;
            }
        //} else if (nombresAptos) {
            //if (nombresAptos == nombreApto && ape1Apto && ape2Apto) {
            //MM
            if(nombreApto && ape1Apto && ape2Apto){
                //nombresAptos = true;
            //}

            System.out.println(" es apto");

        } else {
            System.out.println(" no es apto ");
        }
    }

}
