/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * @author miguel
 *
 */
package cifradorcesar;

import java.util.InputMismatchException;
import java.util.Scanner;


public class CifradorCesar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String alfabeto = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789"; 
        /* al no encontrar que se detalle en el enunciado, consideramos el alfabeto
        como la sucesión ordenada de los caracteres en minúscula, en mayúscula y números.
        La variable alfabeto se utiliza para calcular la posición del caracter a transformar
        */
        Scanner lectura = new Scanner(System.in);
        String texto = ""; //contiene el mensaje a codificar
        String textoCodificado = "";//contiene el mensaje a descodificar
        int codigo, codigoAux; //se utilizan para calcular la distancia del cada caracter a transformar dentro del texto a codificar o descodificar
        int opcion; //Guardaremos la opcion del usuario

        do {
            System.out.println(" ");
            System.out.println("C I F R A D O   C E S A R");
            System.out.println("*************************");
            System.out.println("1. Cifrar un mensaje.");
            System.out.println("2. Descifrar un mensaje.");
            System.out.println("3. Ataque de fuerza bruta.");
            System.out.println("4. Salir");
            System.out.println("Escribe una de las opciones: ");
            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 4: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la opcion 1, cifrar un mensaje.");
                    do {
                        System.out.println("Introduce el mensaje a cifrar, no puede contener espacios: ");
                        texto = lectura.nextLine();
                    } while (texto.contains(" ")); //validamos que el texto no tenga espacios, de lo contrario se vuelve a pedir

                    do {
                        System.out.println("Introduce el código para cifrar, entre 1 y 64: ");
                        while (!lectura.hasNextInt()) { //se valida que el valor sea numérico
                            System.out.println("Debe introducir un número. Por favor introduzca uno: ");
                            lectura.next();
                        }
                        codigo = lectura.nextInt();
                    } while (codigo < 1 || codigo > 64); //se valida que el valor numérico sea correcto
                    for (int i = 0; i < texto.length(); i++) { //se recorre la cadena caracter a caracter transformando cada uno
                        int posicion = alfabeto.indexOf(texto.charAt(i));
                        //usamos la operación módulo para volver al principio del alfabeto si se llega al final
                        textoCodificado += alfabeto.charAt((posicion + codigo) % alfabeto.length());
                    }
                    System.out.println("Texto cifrado: " + textoCodificado);
                    texto = "";
                    textoCodificado = "";
                    break;
                case 2:
                    System.out.println("Has seleccionado la opcion 2, descifrar un mensaje.");
                    do {
                        System.out.println("Introduce el mensaje a descifrar, no puede contener espacios: ");
                        textoCodificado = lectura.nextLine();
                    } while (textoCodificado.contains(" ")); //validamos que el texto no tenga espacios, de lo contrario se vuelve a pedir                 
                    do {
                        System.out.println("Introduce el código para descifrar, entre 1 y 64: ");
                        while (!lectura.hasNextInt()) { //se valida que el valor sea numérico
                            System.out.println("Debe introducir un número. Por favor introduzca uno: ");
                            lectura.next();
                        }
                        codigo = lectura.nextInt();
                    } while (codigo < 1 || codigo > 64); //se valida que el valor numérico sea correcto

                    for (int i = 0; i < textoCodificado.length(); i++) { //se recorre la cadena caracter a caracter transformando cada uno
                        int posicion = alfabeto.indexOf(textoCodificado.charAt(i));
                        //si el código hace que nos "salgamos" del alfabeto por el límite inferior hay volver desde el limite superior
                        if ((posicion - codigo) < 0) {
                            codigoAux = 64 + (posicion - codigo);
                        } else {
                            codigoAux = (posicion - codigo);
                        }
                        texto += alfabeto.charAt(codigoAux);
                    }
                    System.out.println("Texto descifrado: " + texto);
                    texto = "";
                    textoCodificado = "";
                    break;
                case 3:
                    System.out.println("Has seleccionado la opcion 3, fuerza bruta.");
                    System.out.println("Introduce el mensaje a descifrar, no puede contener espacios: ");
                    textoCodificado = lectura.next();

                    for (int j = 1; j < alfabeto.length(); j++) { //bucle para mostrar todas las posibles combinaciones

                        for (int i = 0; i < textoCodificado.length(); i++) {
                            int posicion = alfabeto.indexOf(textoCodificado.charAt(i));
                            if ((posicion - j) < 0) { 
                                codigoAux = 64 + (posicion - j); //si el código hace que nos "salgamos" del alfabeto por el límite inferior hay volver desde el limite superior
                                texto += alfabeto.charAt(codigoAux % alfabeto.length());
                            } else { //usamos la operación módulo para volver al principio del alfabeto si se llega al final
                                texto += alfabeto.charAt((posicion - j) % alfabeto.length());
                            }
                        }
                        System.out.println("Opción " + j + ": " + texto);
                        texto = "";
                    }
                    break;
                default:
                    System.out.println("El número debe ser entre 1 y 4: ");
            }
        } while (opcion != 4);
    }
}
