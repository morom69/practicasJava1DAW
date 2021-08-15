/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays1y2cargararrayportecladoyestadisticas;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Crea un programa que cargue un array numérico con información de teclado. En
 * primer lugar se solicitará el tamaño del array y posteriormente se pedirá al
 * usuario que introduzca los datos de cada posición
 *
 * @author Miguel Moro
 */
public class Arrays1y2cargarArrayPorTecladoYestadisticas {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int posiciones;

        System.out.println("Vamos a definir el tamaño del array");
        posiciones = leeEntero();
        System.out.println();

        // creamos el arrray
        int array[] = crearArray(posiciones);

        // cargamos el array desde teclado
        System.out.println("Vamos a cargar el array");
        cargarArrayTeclado(array);
        System.out.println();

        //visualizamos el contenido del array
        System.out.println("Mostramos el contenido del array");
        mostrarArrayPantalla(array);
        System.out.println();
        // o como alternativa podríamos usar la función con un bucle for each
        System.out.println("Mostramos el contenido del array con un bucle for each");
        mostrarArrayPantallaForEach(array);

        //mostramos los valores estadísticos, para algún valor necesitamos el array ordenado
        Arrays.sort(array);
        System.out.println();
        mostrarArrayPantalla(array);
        System.out.println();
        System.out.println("El número mínimo dentro del array es " + minimo(array));
        System.out.println("La media de los números contenidos en el array es " + media(array));
        System.out.println("La mediana de los números contenidos en el array es " + mediana(array));
        // moda(array);   //inicialmente usado para imprimir la moda (o multimoda)
        // reemplazado por moda2() que devuelve un array con la moda (o multimoda)
        //evaluamos si el resultado es multimodal y mostramos el resultado
        int[] arrayModa = moda2(array);
        System.out.println("Moda de los números contenidos en el array: " + arrayModa[0]);
        if (arrayModa[1] > 1) 
        {
            int i =1;
            do {
                System.out.println("Multimodal, otra moda: " + arrayModa[i]);
                i++;
            } while (arrayModa[i] > 0);
        }

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

    public static void mostrarArrayPantallaForEach(int[] array) {//muestra en pantalla el contenido del array pasado por parámetro
        for (int elemento : array) {
            System.out.println(elemento);
        }
    }

    public static int minimo(int[] array) {
        int minimo = array[0];
        for (int elemento : array) {
            if (minimo > elemento) {
                minimo = elemento;
            }
        }
        return minimo;
    }

    public static double media(int[] array) {
        double media = 0;
        for (double elemento : array) {
            media += elemento;
        }
        media = media / array.length;
        return media;
    }

    public static double mediana(int[] array) {
        //ordenamos los datos de menor a mayor
        //si la serie tiene un número impar de posiciones la mediana es la posición central
        //si la serie tiene un número par de posiciones la mediana es la media entre las dos centrales
        double mediana = 0;
        if (array.length % 2 == 0) {
            double sumaMedios = array[array.length / 2] + array[array.length / 2 - 1];
            mediana = sumaMedios / 2;
        } else {
            mediana = array[array.length / 2];
        }
        return mediana;
    }

    public static void moda(int[] array) {
        //la moda es el valor con mayor frecuencia en la muestra
        //el array recibido ya está ordenado
        //contamos con que el array contiene números enteros positivos
        int moda = -1;
        int actual = -1;
        int repeticiones = 0;
        int maxRepeticiones = 0;
        //recorremos una vez el array para identificar el valor que más se repite
        for (int i = 0; i < array.length; i++) {
            if (array[i] != actual) {
                actual = array[i];
                if (repeticiones > maxRepeticiones) {
                    moda = array[i - 1];
                    maxRepeticiones = repeticiones;
                }
                repeticiones = 1;
            } else {
                repeticiones++;
            }
        }
        System.out.println("Moda de los números contenidos en el array: " + moda);

        //recorremos el array una segunda vez para verificar si es multimodal
        for (int i = 0; i < array.length; i++) {
            if (array[i] != actual) {
                actual = array[i];
                if (repeticiones == maxRepeticiones && array[i - 1] != moda) {
                    System.out.println("Multimodal. Otra moda: " + array[i - 1]);
                }
                repeticiones = 1;
            } else {
                repeticiones++;
            }
        }
    }

    public static int[] moda2(int[] array) {
        //la moda es el valor con mayor frecuencia en la muestra
        //el array recibido ya está ordenado
        //contamos con que el array contiene números enteros positivos
        int moda = -1;
        int actual = -1;
        int repeticiones = 0;
        int maxRepeticiones = 0;
        //por si hay más de una moda (multimodal), es decir si hay más de un valor que se repitan el mismo número máximo de veces
        //devolvemos un array con los diferentes valores para la moda. El número máximo puede ser el total de elementos del array que recibimos como parámetro
        //declaramos el array
        int[] arrayModa = new int[array.length];

        //recorremos una vez el array para identificar el valor que más se repite
        for (int i = 0; i < array.length; i++) {
            if (array[i] != actual) {
                actual = array[i];
                if (repeticiones > maxRepeticiones) {
                    moda = array[i - 1];
                    maxRepeticiones = repeticiones;
                }
                repeticiones = 1;
            } else {
                repeticiones++;
            }
        }
        //lo asignamos a la primera posición del array a devolver
        arrayModa[0] = moda;
        //inicializamos el índice para guardar los valores multimoda si existen
        int indiceModa = 0;

        //recorremos el array una segunda vez para verificar si es multimodal
        int aux; // BORRAR**************************************************************!!!!!!
        for (int i = 0; i < array.length; i++) {
            aux = array[i];
            if (array[i] != actual) {
                actual = array[i];
                if (repeticiones == maxRepeticiones && array[i] != moda) {
                    indiceModa++;
                    arrayModa[indiceModa] = array[i];
                }
                repeticiones = 1;
            } else {
                repeticiones++;
            }
        }
        return arrayModa;
    }

}
