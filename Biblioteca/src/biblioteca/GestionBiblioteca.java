/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Scanner;

/**
 *
 * @author deifont
 */
public class GestionBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pila pila = new Pila();
        Libro l;
        
        int opcion;
        
        do{
            opcion = menu();
            switch(opcion){
                //Nuevo libro
                case 1:
                    l = crearLibro();
                    pila.push(l);
                    break;
                //Sacar libro
                case 2:
                    if(!pila.isEmpty()){
                        l = pila.pop();
                        System.out.println(l);
                    } else {
                        System.out.println("No se puede mostrar ningún libro. Pila vacía");
                    }
                    break;
                case 3:
                    System.out.println("Hasta pronto!");
            }
            
        }while(opcion!=3);
    }

    private static int menu() {
        System.out.println("BIBLIOTECA DE LIBROS");
        System.out.println("1. Nuevo libro");
        System.out.println("2. Sacar libro");
        System.out.println("3. Salir");
        
        int opcion = leerNumeroEntero("Introduzca la opción de menú",1,3);
        return opcion;
    }

    public static int leerNumeroEntero(String mensaje, int minimo, int maximo) {
        Scanner sc = new Scanner(System.in);
        int numero;
        do {
            System.out.println(mensaje);
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("No ha introducido un número. Vuelva a introducirlo: ");
            }
            numero = sc.nextInt();
        } while (numero < minimo || numero > maximo);

        return numero;
    }      

    private static Libro crearLibro() {
        return new Libro("El señor de los anillos","J.R.R. Tolkien","9788447356027");
    }
}
