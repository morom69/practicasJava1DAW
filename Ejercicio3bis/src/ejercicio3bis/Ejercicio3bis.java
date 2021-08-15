/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3bis;

import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class Ejercicio3bis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String alfabetoCifrado = "";
        String claveCifrado = "";
        final int codigo = 27; // es el número de caracteres adelante en el alfabeto que tenemos que mover los caracteres del texto original
        /* 
        La variable alfabetoNormal se utiliza para calcular la posición del caracter a transformar
         */
        Scanner lectura = new Scanner(System.in);
        String texto = ""; //contiene el mensaje a codificar
        String textoCodificado = "";//contiene el mensaje a descodificar
        int codigoAux; //se utilizan para calcular la distancia del cada caracter a transformar dentro del texto a codificar o descodificar
        int opcion; //Guardaremos la opcion del usuario

        do {
            System.out.println(" ");
            System.out.println("C I F R A D O   D E  S U S T I T U C I O N");
            System.out.println("******************************************");
            System.out.println("1. Cifrar un mensaje.");
            System.out.println("2. Descifrar un mensaje.");
            System.out.println("3. Salir");
            System.out.println("Escribe una de las opciones: ");
            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 3: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la opcion 1, cifrar un mensaje.");
                    do {
                        System.out.println("Introduce el mensaje a cifrar, no puede contener espacios: ");
                        texto = lectura.nextLine().toUpperCase();
                    } while (texto.contains(" ")); //validamos que el texto no tenga espacios, de lo contrario se vuelve a pedir

                    leeClaveCifrado(codigo);

                    alfabetoCifrado = alfabeto + claveCifrado;

                    textoCodificado = textoCifrado(texto, alfabetoCifrado, codigo);

                    System.out.println("Texto cifrado: " + textoCodificado);
                    texto = "";
                    textoCodificado = "";
                    break;
                case 2:
                    System.out.println("Has seleccionado la opcion 2, descifrar un mensaje.");
                    do {
                        System.out.println("Introduce el mensaje a descifrar, no puede contener espacios: ");
                        texto = lectura.nextLine().toUpperCase();
                    } while (texto.contains(" ")); //validamos que el texto no tenga espacios, de lo contrario se vuelve a pedir

                    leeClaveCifrado(codigo);

                    alfabetoCifrado = claveCifrado + alfabeto;
                    
                    textoCodificado = textoCifrado(texto, alfabetoCifrado, codigo);

                    System.out.println("Texto cifrado: " + textoCodificado);
                    texto = "";
                    textoCodificado = "";
                    break;
                case 3:
                    break;
                default:
                    System.out.println("El número debe ser entre 1 y 3: ");
            }
        } while (opcion != 3);

    }

    public static String textoCifrado(String texto2, String alfabetoCifrado2, int codigo2) {
        String textoCifrado = "";
        for (int i = 0; i < texto2.length(); i++) { //se recorre la cadena caracter a caracter transformando cada uno
            int posicion = alfabetoCifrado2.indexOf(texto2.charAt(i));
            //usamos la operación módulo para volver al principio del alfabeto si se llega al final
            textoCifrado += alfabetoCifrado2.charAt((posicion + codigo2) % alfabetoCifrado2.length());
        }
        return textoCifrado;
    }

    public static String leeClaveCifrado(int cod){
        Scanner lecturaClave = new Scanner(System.in);
        String claveCifrado = "";
        do {
                System.out.println("Introduce la clave");
                System.out.println("La clave debe ser una cadena que contenga todas las letra del alfabeto seguidas sin espacio y sin repetir:");
            while (!lecturaClave.hasNext()) { //se valida que haya una entrada
                System.out.println("Debe introducir la clave");
                lecturaClave.nextLine();
            }
            claveCifrado = lecturaClave.nextLine();
        } while (claveCifrado.length() != cod); //se valida que la clave de cifrado tenga tantas letras como el alfabeto
        return claveCifrado;
    }

}
