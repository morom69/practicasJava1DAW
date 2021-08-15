/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversorlistacanciones;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class GestionConversor {
    
    //private static ArrayList<Cancion> listaCanciones; // puede ir aquí para evitar llamadas luego dentro del main!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) {

        ArrayList<Cancion> listaCanciones = new ArrayList<>(); // estructura utilizada para el manejo de las canciones
        ArrayList<Cancion> listaCancionesAux = new ArrayList<>();
        
        String fichero;
        int opcion, numeroCanciones = 0;
        boolean almacenar;
        System.out.println("Tipo de fichero a leer:");
        System.out.println("Introduce 1 si es un fichero en formato antiguo");
        System.out.println("Introduce 2 si es un fichero en formato actual");
        opcion = leerEnteroEntreLimites(1, 2);
        System.out.println("Introducir el nombre de fichero del que recuperar las canciones");
        fichero = leerString();
        File archivo = new File(fichero);
        if (!archivo.exists()) { // validamos si el fichero existe, si no existe finaliza el programa
            // 
            // if (archivo.exist() && archivo.isFile())  // se puede comprobar que existe y que es un archivo (podría ser un directorio, no un fichero)c!!!!!!!!!!!!!!!!
            System.out.println("El fichero " + fichero + " no existe");
        } else {
            // CUERPO DEL PROGRAMA
            switch (opcion) {
                case 1 -> { //fichero con formato antiguo
                    numeroCanciones = cargaCancionesFormatoAntiguo(listaCanciones, fichero);
                }
                case 2 -> { //fichero con formato nuevo
                    listaCanciones = serializaFicheroInput(fichero, listaCanciones);
                    numeroCanciones = listaCanciones.size();
                }
            }
            // mostramos los datos de las canciones leídas
            if (numeroCanciones > 0) {
                mostrarCanciones(listaCanciones);
                System.out.println("Desea guardar los datos en nuevo fichero usando serialización? (S/N)");
                almacenar = leerSiNo(" almacenar ");
                if (almacenar) {
                    System.out.println("Introducir el nombre del nuevo fichero donde almacenar las canciones");
                    System.out.println("Si el fichero ya existe se reescribirá");
                    fichero = leerString();
                    // guardamos la modificación en fichero
                    serializaFicheroOutput(listaCanciones, fichero);
                    System.out.println("Se almacenaron los datos en el fichero");
                } else {
                    System.out.println("No se almacenaron los datos en un nuevo fichero");
                }
            } else {
                System.out.println("El fichero no contenía canciones");
            }
        }
    }

    /**
     * Carga una lista de canciones de un fichero de texto secuencial .
     *
     * @param listaCanciones es el ArrayList donde se cargarán las canciones
     * @param fichero es el fichero del que se leerán las canciones
     * @return devuelve el número de canciones cargado.
     */
    public static int cargaCancionesFormatoAntiguo(ArrayList<Cancion> listaCanciones, String fichero) {
        String autor, titulo;
        int duracion, numeroCanciones = 0;
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(fichero));
            //Leer la primera línea, que corresponde al número de canciones
            numeroCanciones = Integer.valueOf(br.readLine());
            if (numeroCanciones > 0) {
                //Repetimos para cada canción mientras no se llegue al final del fichero
                for (int i = 0; i < numeroCanciones; i++) {
                    titulo = br.readLine();
                    autor = br.readLine();
                    duracion = Integer.valueOf(br.readLine());
                    Cancion c = new Cancion(titulo, autor, duracion);
                    listaCanciones.add(c);
                    //listaCanciones.add(new Cancion(titulo, autor, duracion)) // mejor opción !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        return numeroCanciones;
    }

    /**
     * Muestra una lista de canciones contenidas en un ArrayList
     *
     * @return devuelve el número de canciones cargado.
     */
    public static void mostrarCanciones(ArrayList<Cancion> listaCanciones) {
        // for(Cancion il: listaCanciones){ // mejor opcion !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (int i = 0; i < listaCanciones.size(); i++) {
            System.out.println(listaCanciones.get(i).toString());
        }
    }

    /**
     * Pide al usuario por teclado introducir una cadena de texto y la devuelve.
     *
     * @return devuelve la cadena de texto leída por teclado.
     */
    public static String leerString() {
        Scanner teclado = new Scanner(System.in);
        String lectura;
        System.out.println("Introduzca un dato:");
        lectura = teclado.nextLine();
        return lectura;
    }

    /**
     * Pide al usuario por teclado introducir un entero mostrando al preguntar
     * el texto facilitado como parámetro y lo devuelve. Se valida que el valor
     * introducido se encuentre entre los límites inferior y superior
     * facilitados como parámetros (ambos incluidos).
     *
     * @param inferior: valor inferior. Se validará que el número introducido no
     * sea menor que este valor.
     * @param superior: valor superior. Se validará que el número introducido no
     * sea mayor que este valor.
     * @return devuelve un entero entre los dos límites pasados como parámetros
     * (ambos incluidos).
     */
    public static int leerEnteroEntreLimites(int inferior, int superior) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre " + inferior + " y " + superior);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num < inferior || num > superior);
        return num;
    }

    /**
     * Realiza el paso a fichero mediante serialización de un arrayList.
     *
     * @param listaCanciones: contiene el ArrayList que se guardará en fichero.
     * @param fichero: contiene la ruta y nombre del fichero donde se guardará
     * el contenido del ArrayList.
     */
    public static void serializaFicheroOutput(ArrayList<Cancion> listaCanciones, String fichero) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(fichero));
            escribiendo_fichero.writeObject(listaCanciones);
            escribiendo_fichero.close();
        } catch (IOException ioe) {
            System.out.println("Error de escritura en fichero");
        }
    }

    /**
     * Realiza la carga en un ArrayList del contenido de un fichero mediante
     * serialización.
     *
     * @param listaCanciones: contiene el arrayList donde se cargarán los datos
     * del fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizará la carga de datos.
     */
    public static ArrayList<Cancion> serializaFicheroInput(String fichero, ArrayList<Cancion> cancionesRecuperadas) {
        try {
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            cancionesRecuperadas = (ArrayList<Cancion>) recuperando_fichero.readObject();
            recuperando_fichero.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe ");
        } catch (IOException ioe) {
            System.out.println("Error: falló la escritura en el fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return cancionesRecuperadas;
    }

    /**
     * Pide al usuario por teclado elegir una opción entre Sí o No y lo devuelve
     * como boolean. Si se introduce una S se devolverá true y si se introduce
     * una N se devolverá false, independientemente para ambas si el carácter es
     * en mayúscula o minúscula. Se realiza una validación hasta que el dato
     * introducido es válido.
     *
     * @return devuelve un booleano, true si se ha seleccionado "Sí" o false si
     * se ha seleccionado "No".
     */
    public static boolean leerSiNo(String texto) {
        char caracter;
        boolean primero = true, resultado = false;
        do {
            if (!primero) {
                System.out.println("Debe introducir S o N para el valor de " + texto);
            }
            System.out.println("Valor para " + texto + " (S/N)");
            caracter = leerCaracter();
            primero = false;
            if (caracter == 's' || caracter == 'S') {
                resultado = true;
            } else if (caracter == 'n' || caracter == 'N') {
                resultado = false;
            }
        } while (caracter != 's' && caracter != 'S' && caracter != 'n' && caracter != 'N');
        return resultado;
    }

    /**
     * Pide al usuario por teclado introducir un carácter y lo devuelve
     * convertido a mayúscula. Se realiza una validación hasta que el tipo de
     * dato introducido es correcto.
     *
     * @return devuelve un carácter. Devuelve siempre el carácter en mayúscula.
     */
    public static char leerCaracter() {
        char caracter;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un carácter: ");
        caracter = teclado.next().charAt(0);
        if (Character.isLowerCase(caracter)) { //convertimos a mayúsculas
            caracter = Character.toUpperCase(caracter);
        }
        return caracter;
    }
}
