/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegotrivial;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class JuegoTrivial {

    private static ArrayList<String> listaJugadores = new ArrayList<>();
    private static ArrayList<Pregunta> listaPreguntas = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println();
            System.out.println("JUEGO DE PREGUNTAS");
            System.out.println("==================");
            System.out.println("");
            System.out.println("1.Administrar preguntas");
            System.out.println("2.Datos serializados");
            System.out.println("3.Importación y exportación");
            System.out.println("4.Partida de Trivial");
            System.out.println("5.Salir del programa");
            System.out.println("Introduzca opción: ");

            // leemos y validamos la opción seleccionada
            opcion = leerEnteroEntreLimites(1, 5);

            switch (opcion) {
                case 1 -> {
                    administrarPreguntas(listaPreguntas);
                    break;
                }
                case 2 -> {
                    datosSerializados(listaPreguntas);
                    break;
                }
                case 3 -> {
                    importacionExportacion(listaPreguntas);
                    break;
                }
                case 4 -> {
                    partidaTrivial(listaPreguntas, listaJugadores);
                    break;
                }
                case 5 -> {
                    System.out.println("Hasta la próxima");
                }
            }
        } while (opcion != 5);
    }

    public static void administrarPreguntas(ArrayList<Pregunta> listaPreguntas) {
        System.out.println("Administrador de preguntas");
        int opcion;

        do {
            System.out.println();
            System.out.println("1.Listar preguntas");
            System.out.println("2.Añadir pregunta");
            System.out.println("3.Modificar pregunta");
            System.out.println("4.Eliminar pregunta");
            System.out.println("5.Volver");
            System.out.println("Introduzca opción: ");

            // leemos y validamos la opción seleccionada
            opcion = leerEnteroEntreLimites(1, 5);

            switch (opcion) {
                case 1 -> {
                    System.out.println("Listar preguntas");
                    listarPreguntas(listaPreguntas);
                    break;
                }
                case 2 -> {
                    System.out.println("Añadir pregunta");
                    anadirPregunta(listaPreguntas);
                    break;
                }
                case 3 -> {
                    System.out.println("Modificar pregunta");
                    modificarPregunta(listaPreguntas);
                    break;
                }
                case 4 -> {
                    System.out.println("Eliminar pregunta");
                    eliminarPregunta(listaPreguntas);
                    break;
                }
                case 5 -> { // volver
                    break;
                }
            }
        } while (opcion != 5);
    }

    public static void listarPreguntas(ArrayList<Pregunta> listaPreguntas) {
        if (listaPreguntas.size() == 0) {
            System.out.println("No hay preguntas que mostrar");
        } else {
            for (Pregunta p : listaPreguntas) { // recorremos la lista de preguntas
                System.out.println(p.toString());
            }
        }
    }

    public static void anadirPregunta(ArrayList<Pregunta> listaPreguntas) {
        System.out.println("Introduzca el enunciado:");
        String enunciado = leerString();
        String[] respuestas = new String[4];
        int correcta = 0;
        System.out.println("Necesitamos 4 respuestas para elegir:");
        for (int i = 0; i < 4; i++) {
            System.out.println("Introduzca la respuesta " + (i + 1) + ": ");
            respuestas[i] = leerString();
        }
        System.out.println("¿Cuál es la respuesta correcta?");
        correcta = leerEnteroEntreLimites(1, 4) - 1;
        /*  En el array no se almacenan los caracteres iniciales = o ~
        for (int i = 0; i < 4; i++) {
            if (i == correcta) {
                respuestas[i] = "=" + respuestas[i];
            } else {
                respuestas[i] = "~" + respuestas[i];
            }
        }*/
        Pregunta p = new Pregunta(enunciado, respuestas, correcta);
        listaPreguntas.add(p);
    }

    public static void modificarPregunta(ArrayList<Pregunta> listaPreguntas) {
        int maximo = listaPreguntas.size();
        if (maximo > 0) {
            boolean enunciadoModificado = false;
            boolean respuestasModificadas = false;
            int respuestaAModificar = 0, correcta;
            String enunciado = "--vacio--";
            String[] respuestas = new String[4];
            System.out.println("Modificamos una pregunta de las siguientes:");
            listarPreguntas(listaPreguntas);
            System.out.println("Introduzca el número de la pregunta a modificar, entre 1 y " + maximo);
            int indice = leerEnteroEntreLimites(1, maximo) - 1;
            enunciado = listaPreguntas.get(indice).getEnunciado();
            respuestas = listaPreguntas.get(indice).getRespuestas();
            System.out.println("La pregunta actualmente tiene el siguiente contenido: " + listaPreguntas.get(indice).toString());
            if (leerSiNo("¿Desea modificar el enunciado?")) {
                enunciadoModificado = true;
                System.out.println("Introduzca el nuevo enunciado:");
                enunciado = leerString();
            }
            while (leerSiNo("¿Desea modificar alguna respuesta?")) {
                respuestasModificadas = true;
                System.out.println("¿Número de la respuesta a modificar (1 a 4)?");
                respuestaAModificar = leerEnteroEntreLimites(1, 4) - 1;
                System.out.println("Introduzca la nueva respuesta " + (respuestaAModificar + 1) + ": ");
                respuestas[respuestaAModificar] = leerString();
            }
            System.out.println("¿Nuevo número de la respuesta correcta?");
            correcta = leerEnteroEntreLimites(1, 4) - 1;
            if (!enunciado.equals("--vacio--") || respuestaAModificar != 0) {
                Pregunta p = new Pregunta(enunciado, respuestas, correcta);
                listaPreguntas.remove(indice);
                listaPreguntas.add(p);
                System.out.println("Así queda la pregunta: " + p.toString());
            }
        } else {
            System.out.println("No hay preguntas que modificar");
        }
    }

    public static void eliminarPregunta(ArrayList<Pregunta> listaPreguntas) {
        boolean confirmacion = false;
        int indice = 0;
        int maximo = listaPreguntas.size();
        if (maximo > 0) { // si hay preguntas
            System.out.println("Listado de preguntas:");
            listarPreguntas(listaPreguntas);
            System.out.println("Introduzca el número de la pregunta a eliminar, entre 1 y " + maximo);
            indice = leerEnteroEntreLimites(1, maximo) - 1;
            System.out.println("Se va a eliminar la pregunta siguiente: " + listaPreguntas.get(indice).toString());
            confirmacion = leerSiNo("confirmación");
            if (confirmacion) { // eliminamos
                listaPreguntas.remove(indice);
                System.out.println("Pregunta " + indice + " eliminada");
            } else {
                System.out.println("Se ha cancelado por parte del usuario, no se ha eliminado la pregunta");
            }
        } else {
            System.out.println("No hay preguntas que eliminar");
        }
    }

    public static void datosSerializados(ArrayList<Pregunta> listaPreguntas) {
        System.out.println("Gestor de datos serializados");
        int opcion;

        do {
            System.out.println();
            System.out.println("1.Cargar");
            System.out.println("2.Guardar");
            System.out.println("3.Volver");
            System.out.println("Introduzca opción: ");

            // leemos y validamos la opción seleccionada
            opcion = leerEnteroEntreLimites(1, 3);

            switch (opcion) {
                case 1 -> {
                    System.out.println("Cargar");
                    cargar(listaPreguntas);
                    break;
                }
                case 2 -> {
                    System.out.println("Guardar");
                    guardar(listaPreguntas);
                    break;
                }
                case 3 -> { // volver
                    break;
                }
            }
        } while (opcion != 3);
    }

    public static void cargar(ArrayList<Pregunta> listaPreguntas) {
        System.out.println("Carga de preguntas, introduzca el nombre del fichero desde donde cargar las preguntas: ");
        String nombreFichero = leerString();
        serializaFicheroInput(listaPreguntas, nombreFichero); // cargamos los datos, el fichero tiene que existir
        System.out.println("Se han cargado " + listaPreguntas.size() + " preguntas del fichero " + nombreFichero);
        /*
        File fichero = new File(nombreFichero);
        if (fichero.exists() && fichero.isFile()) { // si existe y es un fichero (podría ser un directorio)
            serializaFicheroInput(listaPreguntas, nombreFichero); // cargamos los datos
            System.out.println("Se han cargado " + listaPreguntas.size() + " preguntas del fichero " + nombreFichero);
        } else {
            System.out.println("No se pueden cargar los datos, el fichero no existe");
        }*/
    }

    public static void guardar(ArrayList<Pregunta> listaPreguntas) {
        boolean confirmacion = false;
        System.out.println("Se van a guardar las preguntas, introduzca el nombre del fichero donde desea guardarlas: ");
        String nombreFichero = leerString(); // el fichero tiene que existir
        serializaFicheroOutput(listaPreguntas, nombreFichero);
        /*
        File fichero = new File(nombreFichero);
        if (!fichero.exists() || !fichero.isFile()) { // si el fichero no existe o si no es un fichero (podría ser un directorio) lo creamos
            try {
                // A partir del objeto File creamos el fichero físicamente
                if (!fichero.createNewFile()) {
                    System.out.println("No ha podido ser creado el fichero");
                } else {
                    System.out.println("Se ha creado el fichero " + nombreFichero);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (fichero.exists() && fichero.isFile()) { // si ya existía o si lo hemos podido crear guardamos la información
            serializaFicheroOutput(listaPreguntas, nombreFichero);
            System.out.println("Se han guardado los datos en el fichero " + nombreFichero);
        } else {
            System.out.println("Ha habido algún problema con el fichero, no se ha podido crear o guardar");
        }*/
    }

    public static void importacionExportacion(ArrayList<Pregunta> listaPreguntas) {
        System.out.println("Gestión de importaciones y exportaciones");
        int opcion;

        do {
            System.out.println();
            System.out.println("1.Importar GIFT");
            System.out.println("2.Exportar GIFT");
            System.out.println("3.Volver");
            System.out.println("Introduzca opción: ");

            // leemos y validamos la opción seleccionada
            opcion = leerEnteroEntreLimites(1, 3);

            switch (opcion) {
                case 1 -> {
                    System.out.println("Importar");
                    importar(listaPreguntas);
                    break;
                }
                case 2 -> {
                    System.out.println("Exportar");
                    exportar(listaPreguntas);
                    break;
                }
                case 3 -> { // volver
                    break;
                }
            }
        } while (opcion != 3);
    }

    public static void importar(ArrayList<Pregunta> listaPreguntas) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del nombreFichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            System.out.println("Importación de preguntas, introduzca el nombre del fichero desde donde importar las preguntas: ");
            String nombreFichero = leerString();
            fr = new FileReader(nombreFichero);
            br = new BufferedReader(fr);
            String enunciado = "";
            int correcta = 0;
            String[] respuestas = new String[4];
            // Lectura del nombreFichero
            String linea;
            while ((linea = br.readLine()) != null) {
                enunciado = linea;
                enunciado = enunciado.substring(0, (enunciado.length() - 1)); // eliminamos el caracter llave del final
                for (int i = 0; i < 4; i++) {
                    respuestas[i] = br.readLine();
                    if (respuestas[i].charAt(0) == '=') {
                        correcta = i;
                    }
                    respuestas[i] = respuestas[i].substring(1, respuestas[i].length()); // eliminamos el caracter inicial
                }
                br.readLine(); // leemos la llave final
                Pregunta p = new Pregunta(enunciado, respuestas, correcta); //creamos la pregunta
                listaPreguntas.add(p); // y la añadimos a las ya existentes
            }
            System.out.println("Se han importado " + listaPreguntas.size() + " preguntas del fichero " + nombreFichero);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el nombreFichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public static void exportar(ArrayList<Pregunta> listaPreguntas) {
        String respuesta = "";
        FileWriter fw = null;
        PrintWriter pw = null;

        System.out.println("Exportación de preguntas, introduzca el nombre del fichero donde guardar las preguntas: ");
        String nombreFichero = leerString();

        try {
            fw = new FileWriter(nombreFichero);
            pw = new PrintWriter(fw);

            for (Pregunta p : listaPreguntas) { // recorremos la lista de preguntas
                // para cada pregunta
                pw.println(p.getEnunciado() + "{");
                for (int j = 0; j < 4; j++) {
                    if (p.getCorrecta() == j) {
                        respuesta = "=";
                    } else {
                        respuesta = "~";
                    }
                    respuesta += p.getRespuestas()[j] + ("");
                    pw.println(respuesta);
                }
                pw.println("}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void partidaTrivial(ArrayList<Pregunta> listaPreguntas, ArrayList<String> listaJugadores) {
        if (JuegoTrivial.listaPreguntas.size() > 0) {
            System.out.println("Vamos a jugar una partida para un único jugador");
            System.out.print("Número de preguntas, entre 1 y " + JuegoTrivial.listaPreguntas.size() + ": ");
            int numPreguntas = leerEnteroEntreLimites(1, JuegoTrivial.listaPreguntas.size());
            ArrayList<Pregunta> listaPreguntasEnJuego = new ArrayList<>();
            ArrayList<Integer> listaRespuestas = new ArrayList();
            int numeroRandom, respuesta, numeroRespuestasCorrectas = 0;
            for (int i = 0; i < numPreguntas; i++) {
                do {
                    // sacar número random
                    numeroRandom = (int) (Math.random() * numPreguntas) + 1;
                    // verificar si la pregunta no está ya añadida
                } while (listaPreguntasEnJuego.contains(JuegoTrivial.listaPreguntas.get(numeroRandom)));
                // añadir la pregunta
                listaPreguntasEnJuego.add(JuegoTrivial.listaPreguntas.get(numeroRandom));
                // preguntar
                System.out.println(JuegoTrivial.listaPreguntas.get(numeroRandom).toString());
                // registrar respuesta
                System.out.println("Introducir respuesta ");
                listaRespuestas.add(leerEnteroEntreLimites(1, 4) - 1);
            }
            System.out.println("Resultados:");
            System.out.println("==========");
            // mostrar respuestas correctas y acertadas
            for (int i = 0; i < numPreguntas; i++) {
                System.out.println("Pregunta " + (i + 1) + ": ");
                // mostramos pregunta
                System.out.println(listaPreguntasEnJuego.get(i));
                // evaluamos si la respuesta es correcta
                if (listaRespuestas.get(i) == listaPreguntasEnJuego.get(i).getCorrecta()) {
                    System.out.println("Respuesta correcta: " + listaPreguntasEnJuego.get(i).getRespuestas()[listaRespuestas.get(i)]);
                    numeroRespuestasCorrectas++;
                } else {
                    System.out.println("Tu respuesta: " + listaPreguntasEnJuego.get(i).getRespuestas()[listaRespuestas.get(i)]);
                    System.out.println("Respuesta correcta: " + listaPreguntasEnJuego.get(i).getRespuestas()[listaPreguntasEnJuego.get(i).getCorrecta()]);
                }
                System.out.println("-------------------------");
            }
            System.out.println("Respuestas correctas: " + numeroRespuestasCorrectas + " de " + numPreguntas);
        } else {
            System.out.println("No hay preguntas para jugar");
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
        lectura = teclado.nextLine();
        return lectura;
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
            System.out.println(texto + " (S/N)");
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

    /**
     * Realiza el paso a nombreFichero mediante serialización
     */
    public static void serializaFicheroOutput(ArrayList<Pregunta> listaPreguntas, String fichero) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(fichero));
            escribiendo_fichero.writeObject(listaPreguntas);
            escribiendo_fichero.close();
            System.out.println(listaPreguntas.size() + " preguntas guardadas");
        } catch (IOException ioe) {
            System.out.println("Error de escritura en fichero");
        }
    }

    /**
     * Realiza la carga de datos de un nombreFichero mediante serialización.
     */
    public static ArrayList<Pregunta> serializaFicheroInput(ArrayList<Pregunta> listaPreguntas, String fichero) {
        try {
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            ArrayList<Pregunta> preguntasRecuperadas = (ArrayList<Pregunta>) recuperando_fichero.readObject();
            listaPreguntas.addAll(preguntasRecuperadas);
            System.out.println(listaPreguntas.size() + " preguntas recuperadas");
            recuperando_fichero.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe ");
        } catch (IOException ioe) {
            System.out.println("Error: falló la lectura del fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return listaPreguntas;
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
     * Lee un entero mostrando el tipo de dato que queremos leer:
     *
     * @return devuelve un entero positivo
     */
    public static int leerEnteroPositivo() {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num <= 0);
        return num;
    }
}
