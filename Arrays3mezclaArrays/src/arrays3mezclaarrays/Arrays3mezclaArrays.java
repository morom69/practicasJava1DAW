/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays3mezclaarrays;

import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class Arrays3mezclaArrays {

    /**
     * Crea una función que reciba por parámetro dos arrays y devuelva uno que
     * sea mezcla de ambos. El array resultante debe contener los elementos de
     * cada uno de los arrays de forma alternativa. Los arrays pasados por
     * parámetro pueden tener diferente número de elementos, en tal caso, si al
     * realizar la mezcla se llega al final de los elementos de un array, se
     * añadirán seguidamente los restantes elementos del otro
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int posiciones1, posiciones2;

        System.out.println("Vamos a definir el tamaño del array 1");
        posiciones1 = leeEntero();
        System.out.println();

        System.out.println("Vamos a definir el tamaño del array 2");
        posiciones2 = leeEntero();
        System.out.println();

        // creamos los arrrays
        int array1[] = crearArray(posiciones1);
        int array2[] = crearArray(posiciones2);
        int arrayResultante[] = crearArray(posiciones1 + posiciones2);

        // cargamos los arrays desde teclado
        System.out.println("Vamos a cargar el array 1");
        cargarArrayTeclado(array1);
        System.out.println();
        System.out.println("Vamos a cargar el array 2");
        cargarArrayTeclado(array2);
        System.out.println();

        // mezclamos el contenido de los dos arrays
        arrayResultante = mezclaDosArrays(array1, array2);

        //visualizamos el contenido los arrays
        System.out.println("Mostramos el contenido del array1");
        mostrarArrayPantalla(array1);
        System.out.println();
        System.out.println("Mostramos el contenido del array2");
        mostrarArrayPantalla(array2);
        System.out.println();
        System.out.println("Mostramos el contenido del array mezclado");
        mostrarArrayPantalla(arrayResultante);
        System.out.println();
    }

    public static int leeEntero() {
        Scanner teclado = new Scanner(System.in);
        int entero;
        do {
            System.out.println("Introduzca un valor entero positivo: ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("El valor introducido no es un entero positivo, vuelva a intentarlo: ");
            }
            entero = teclado.nextInt();
        } while (entero < 0);
        return entero;
    }

    public static int[] crearArray(int n) { //devuelve un array de n elementos
        int array[] = new int[n];
        return array;
    }

    public static void cargarArrayTeclado(int[] array) { //recibe por parámetro el array que se cargará con los datos solicitados al usuario
        for (int i = 0; i < array.length; i++) {
            array[i] = leeEntero();
        }
    }

    public static void mostrarArrayPantalla(int[] array) {//muestra en pantalla el contenido del array pasado por parámetro
        for (int i = 0; i < array.length; i++) {
            System.out.println("Elemento[" + i + "] = " + array[i]);
        }
    }

    /*
    public static int[] mezclaDosArrays(int[] array1, int[] array2) {
        int indiceArrayResultante = 0;
        int indiceArrayMayor = 0;
        int[] arrayResultante = new int[array1.length + array2.length];
        if (array1.length > array2.length) {
            indiceArrayMayor = array1.length;
        } else {
            indiceArrayMayor = array2.length;
        }
        for (int i = 0; i < indiceArrayMayor; i++) {
            if (array1.length > i && array2.length > i) { // si hay contenido para el índice en los dos arrays
                //intercalamos los elementos
                arrayResultante[indiceArrayResultante] = array1[i];
                indiceArrayResultante++;
                arrayResultante[indiceArrayResultante] = array2[i];
                indiceArrayResultante++;
            } else {
                if (array1.length - 1 < i) { // array1 fuera de límites, solo añadimos array2
                    arrayResultante[indiceArrayResultante] = array2[i];
                    indiceArrayResultante++;
                } else { // array2 fuera de límites, solo añadimos array1
                    arrayResultante[indiceArrayResultante] = array1[i];
                    indiceArrayResultante++;
                }
            }
        }
        return arrayResultante;
    }*/
    
    //FALTA INCLUIR EL ÚLTIMO ELEMENTO DE CADA ARRAY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static int[] mezclaDosArrays(int[] array1, int[] array2) {
        int[] arrayResultante = new int[array1.length + array2.length];
        int indice1 = 0;
        int indice2 = 0;
        for (int i = 0; i < arrayResultante.length; i++){
            //si es elemento par
            if ((i%2 == 0 && indice1 <= array1.length - 1)||(indice2 > array2.length -1)){
                arrayResultante[i] = array1[indice1];
                indice1++;
            } else if(indice2 <= array2.length -1){
                arrayResultante[i] = array2[indice2];
                indice2++;
            }
            
        }
        return arrayResultante;
    }
    
}
