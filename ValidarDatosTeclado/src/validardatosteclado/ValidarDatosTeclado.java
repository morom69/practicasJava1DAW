/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validardatosteclado;

import java.util.Scanner;

/**
 *
 * @author jose
 */
public class ValidarDatosTeclado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        int valor, min=-5, max=5;
        
        do{
            System.out.println("Introduzca un valor entre -5 y 5:");
            //Antes de leer hay que comprobar que el tipo de valor introducido se corresponde con el solicitado
            //Si no lo hacemos y leemos directamente un valor no válido se lanzaría la excepción InputMismatchException
            while(!teclado.hasNextInt()){
                teclado.next(); //si lo que se ha introducido no es un número lo sacamos del buffer
                System.out.println("El valor introducido no es un número, vuelva a intentarlo:");
            }
                
            valor = teclado.nextInt();
        }while(valor>=max || valor<=min);
        
        System.out.println("Muy bien. El número introducido es correcto.");
    }
}

