package org.ieszaidinvergeles.juegosdecartas.blackjack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import org.ieszaidinvergeles.juegosdecartas.Baraja;
import org.ieszaidinvergeles.juegosdecartas.Carta;

/**
 *
 * @author Miguel Moro
 */
public class BlackJack {

    private static Baraja baraja;
    // utilizamos un conjunto de tipo LinkedHashSet para poder ordenar el orden de intervención de los jugadores
    private static LinkedHashSet<Jugador> jugadores = new LinkedHashSet<Jugador>(); // entre 1 y 7 jugadores
    static final String FICHERO_BANCA = "banca.dat"; // nombre del fichero donde se guarda y almacena la información
    static final String FICHERO_JUGADORES = "jugadores.dat"; // nombre del fichero donde se guarda y almacena la información

    /**
     * Pregunta el número de jugadores que participarán en el juego y los datos
     * (nombre y bolsa) de cada uno de ellos. Crea cada jugador
     */
    public static void inicializarJugadores() {
        Jugador jugador;
        int numJugadores, bolsa;
        String nombre;
        System.out.println("Introduce el número de jugadores,");
        numJugadores = leerEnteroPositivo(7);
        for (int i = 0; i < numJugadores; i++) {
            nombre = leerString("Introduce el nombre del jugador " + (i + 1) + ":");
            bolsa = leerEnteroPositivo("Introduce la cantidad de dinero con la que empieza a jugar el jugador " + (i + 1) + ":");
            jugador = new Jugador(nombre, bolsa);
            jugadores.add(jugador);
        }
        System.out.println("");
        System.out.println("Empieza la partida:");
        System.out.println("");
        for (Jugador j : jugadores) {
            j.toString();
        }
    }

    /**
     * inicializa el estado de los jugadores, a lo largo del juego: si su valor
     * es 0 siguen jugando. Si su valor es -1 se han pasado. Si su valor es 1 se
     * han plantado
     *
     * @param estadoJugadores contiene un array de enteros con el estado de cada
     * uno de los jugadores
     */
    public static void inicializarEstadoJugadores(int[] estadoJugadores) {
        for (int i = 0; i < jugadores.size(); i++) {
            estadoJugadores[i] = 0;
        }
    }

    /**
     * Se pregunta a cada jugador la cantidad que desea apostar en esta ronda.
     */
    public static void rondaApuestas() {
        int apuesta;
        System.out.println("Ronda de apuestas, cada jugador hace su apuesta");
        for (Jugador i : jugadores) {
            System.out.print("Apuesta de " + i.getNombre() + ". ");
            apuesta = leerEnteroPositivo(i.getBolsa());
            i.apostar(apuesta);
        }
        System.out.println("");
    }

    /**
     * Se reparten dos cartas a cada jugador que ha realizado una apuesta y a la
     * banca (en este caso una es oculta). Además se mostrará por pantalla las
     * cartas obtenidas por cada uno de ellos
     */
    public static void repartir(Baraja baraja, Banca banca) {
        Carta c;
        System.out.println("Repartimos cartas, dos por jugador");
        for (Jugador i : jugadores) {
            for (int j = 1; j <= 2; j++) {
                asignarCartaJugador(i, baraja);
            }
            i.mostrarMano();
            System.out.println("");
        }
        System.out.println("Repartimos dos cartas a la banca, una oculta");
        for (int j = 1; j <= 2; j++) {
            asignarCartaJugador(banca, baraja);
        }
        banca.mostrarManoBanca();
        System.out.println("");
    }

    /**
     * Se asigna una carta de la baraja a un jugador
     *
     * @param jugador es el jugador al que se le asigna una carta
     * @param baraja es el mazo del que se saca la carta
     */
    public static void asignarCartaJugador(Jugador jugador, Baraja baraja) {
        Carta c;
        c = baraja.sacarCarta();
        jugador.pedirCarta(c);
    }

    /**
     * Se realiza el turno de juego de los jugadores. Uno por uno se les irá
     * preguntando si desean pedir carta o plantarse. El turno de un jugador
     * acaba cuando éste se pasa de 21 puntos o se planta. Los jugadores no
     * compiten entre ellos, solo tiene que superar la puntuación de la banca.
     * Cuando acaba el turno del último jugador, comenzará el juego de la banca.
     *
     * @param baraja contiene el mazo del que se sacan las cartas.
     * @param estadoJugadores almacena el estado de cada jugador: si su valor es
     * 0 siguen jugando. Si su valor es -1 se han pasado. Si su valor es 1 se
     * han plantado
     */
    public static void turnoJugadores(Baraja baraja, int[] estadoJugadores) {
        boolean pideCarta;
        int i = 0;
        for (Jugador j : jugadores) {
            System.out.println("");
            j.mostrarMano();
            System.out.println("Puntuación = " + j.getPuntuacion());
            jugadorResultado(j);
            if (estadoJugadores[i] == 0 && j.getPuntuacion() != 21) { // si el jugador no ha ganado ya o no se ha pasado se le pregunta
                System.out.println(j.getNombre() + ": ¿Pide carta (S) o se planta (N)?");
                pideCarta = leerSiNo();
                if (pideCarta) {
                    asignarCartaJugador(j, baraja);
                    j.mostrarMano();
                    System.out.println(j.getNombre() + " puntuación = " + j.getPuntuacion());
                    if (j.getPuntuacion() > 21) {
                        estadoJugadores[i] = -1; // se ha pasado
                        System.out.println("Se ha pasado, pierde");
                    } else if (j.getPuntuacion() == 21) {
                        estadoJugadores[i] = 1; // ***Black Jack*** nos plantamos
                    }
                } else { // se planta
                    estadoJugadores[i] = 1;
                    System.out.println("Se ha plantado");
                }
            }
            if (j.getPuntuacion() == 21) {
                estadoJugadores[i] = 1; // ***Black Jack*** nos plantamos
            }
            i++;
        }
    }

    /**
     * Nos muestra el estado de un jugador según su puntuación
     *
     * @param j es el jugador del que evaluamos su estado
     */
    public static void jugadorResultado(Jugador j) {
        int resultado = 0;
        if (j.getPuntuacion() == 21) {
            resultado = 1; // ***BlackJack***
        } else if (j.getPuntuacion() > 21) {
            resultado = -1; // se ha pasado
        }
        switch (resultado) {
            case 1 -> {
                System.out.println("***BlackJack*** Ha ganado la partida");
            }
            case -1 -> {
                System.out.println("Se ha pasado. Ha perdido la partida");
            }
        }
    }

    /**
     * En este método se realizará el juego de la banca. Según las reglas del
     * BlackJack la banca debe pedir hasta alcanzar una puntuación mínima de 17.
     * Momento en el que se plantará.
     * http://www.casino.es/blackjack/reglas-crupier-blackjack/ En nuestro caso
     * contamos siempre el as con un valor de 11
     *
     * @param baraja es el mazo del que sacaremos carta si es necesario.
     * @param banca representa a la banca
     */
    public static void turnoBanca(Baraja baraja, Banca banca) {
        if (banca.getSigueJugando()) {
            asignarCartaJugador(banca, baraja);
            System.out.println("");
            banca.mostrarManoBanca();
        }
        if (banca.getPuntuacion() > 21) { // se ha pasado
            System.out.println("La Banca se ha pasado. Ha perdido la partida. Puntuación = " + banca.getPuntuacion());
            banca.setEstado(-1);
        } else if (banca.getPuntuacion() >= 17) { // se planta
            System.out.println("La Banca se planta. Puntuación = " + banca.getPuntuacion());
            banca.setEstado(1);
        }
    }

    /**
     * Evalúa si la partida sigue en curso
     *
     * @param bancaSigueJugando recibe si la banca sigue jugando o no
     * @param estadoJugadores recibe un array con el estado de todos los
     * jugadores: 0 si siguen jugando, -1 se han pasado o 1 si se han plantado
     * @return se devuelve true si la partida se sigue jugando. False en caso
     * contrario
     */
    public static boolean partidaEnCurso(Boolean bancaSigueJugando, int[] estadoJugadores) {
        // la partida está en curso si algún jugador o la banca no se ha plantado ni pasado
        // si el valor es cero siguen jugando
        boolean jugadorActivo = false; // evalúa si alguno de los jugadores sigue activo, es decir, si no se ha pasado ni plantado
        for (int i = 0; i < estadoJugadores.length && !jugadorActivo; i++) {
            if (estadoJugadores[i] == 0) {
                jugadorActivo = true;
            }
        }
        return (bancaSigueJugando || jugadorActivo);
    }

    /**
     * Muestra el estado de los jugadores y de la banca al final de una ronda
     *
     * @param banca representa a la banca
     * @param jugadores contiene la lista ordenada de jugadores
     */
    public static void mostrarEstadoFinalRonda(Banca banca, LinkedHashSet<Jugador> jugadores) {
        System.out.println("");
        System.out.println("Final de ronda");
        for (Jugador j : jugadores) {
            System.out.println("");
            j.mostrarMano();
            System.out.println("Puntuación: " + j.getPuntuacion());
        }
        System.out.println("");
        banca.mostrarManoBanca();
        if (banca.getPuntuacion() > 21) {
            System.out.println("Se ha pasado");
            System.out.println("Puntuación: " + banca.getPuntuacion());
        } else if (banca.getPuntuacion() >= 17) {
            System.out.println("Se ha plantado");
        } else {
            System.out.println("Sigue jugando");
        }
    }

    /**
     * Muestra el estado inicial de los jugadores y de la banca
     *
     * @param banca representa a la banca
     * @param jugadores contiene la lista ordenada de jugadores
     */
    public static void mostrarEstadoInicial(Banca banca, LinkedHashSet<Jugador> jugadores) {
        System.out.println("");
        System.out.println("Estado inicial");
        System.out.println("");
        for (Jugador j : jugadores) {
            System.out.println(j.getNombre() + ", bolsa: " + j.getBolsa());
        }
        System.out.println("Banca, bolsa: " + banca.getBolsa());
    }

    /**
     * Al final de una partida evalúa quienes son los ganadores de entre los
     * jugadores y la banca
     *
     * @param banca representa a la banca
     * @param jugadores es la lista ordenada que contiene los jugadores
     */
    public static void evaluaGanadores(Banca banca, LinkedHashSet<Jugador> jugadores) {
        // un jugador gana si tiene BlackJack o si la banca no tiene BlackJack y el jugador tiene mayor puntuación que la banca sin pasarse de 21
        ArrayList<Jugador> ganadores = new ArrayList();
        int bolsaBanca = 0;
        // evaluamos a los jugadores
        for (Jugador j : jugadores) {
            if (j.getPuntuacion() <= 21) { // si el jugador se ha pasado de 21 nunca será ganador
                if (j.getPuntuacion() == 21) { // si el jugador tiene BlackJack es ganador
                    ganadores.add(j);
                } else if ((j.getPuntuacion() > banca.getPuntuacion()) && (banca.getPuntuacion() < 21)) { // si tiene mayor puntuación que la banca y la banca no se ha pasado entonces es ganador
                    ganadores.add(j);
                } else if ((banca.getPuntuacion() > 21) && (j.getPuntuacion() <= 21)){ // si la banca se ha pasado y él no, entonces es ganador
                    ganadores.add(j);
                }
            }
        }
        // evaluamos la banca
        banca.mostrarMano();
        if (banca.getPuntuacion() == 21) {
            banca.setEstado(2); // la banca es ganadora
        } else { // si no hay ningún ganador y la banca no se ha pasado, la banca es ganadora
            if (banca.getPuntuacion() < 21 && ganadores.isEmpty()) {
                banca.setEstado(2);
            }
        }
        // mostramos ganadores y actualizamos la bolsa de cada uno
        System.out.println("");
        System.out.println("Ganadores:");
        if (banca.getEstado() == 2) { // mostramos si la banca es ganadora
            System.out.println("Banca, puntuación: " + banca.getPuntuacion());
        }
        if (ganadores.size() > 0) { // mostramos si algún jugador es ganador
            for (Jugador j : ganadores) {
                System.out.println(j.getNombre() + ", puntuación:  " + j.getPuntuacion());
            }
        }
        bolsaBanca = banca.getBolsa();
        System.out.println("");
        System.out.println("Estado de la bolsa de los jugadores:");
        for (Jugador j : jugadores) { // actualizamos el estado de la bolsa de cada jugador
            System.out.println("");
            System.out.println(j.getNombre() + " bolsa inicial " + (j.getBolsa() + j.getApuesta()));
            if (ganadores.contains(j)) {
                banca.setBolsa(banca.getBolsa() - j.getApuesta());
                j.apuestaGanada();
            } else {
                banca.setBolsa(banca.getBolsa() + j.getApuesta());
                j.apuestaPerdida();
            }
            System.out.println("bolsa final " + j.getBolsa());
        }
        System.out.println("");
        System.out.println("Banca bolsa inicial " + bolsaBanca);
        System.out.println("bolsa final " + banca.getBolsa());
        // vaciamos la mano de cartas de cada jugador
        for(Jugador j: jugadores){
            j.soltarCartas();
        }
        banca.soltarCartas();
    }

    /**
     * Lee por teclado un número entero positivo con control de errores,
     * mostrando un texto
     *
     * @param texto muestra un texto para indicar el tipo de dato que se espera
     * recibir
     * @return devuelve un entero positivo introducido por teclado tras superar
     * el control de errores
     */
    public static int leerEnteroPositivo(String texto) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println(texto);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num <= 0);
        return num;
    }

    /**
     * Lee por teclado un número entero entre cero y el límite pasado por
     * parámetro, con control de errores
     *
     * @param limite es el límite superior que se validará (inclusivo)
     * @return devuelve un entero positivo introducido por teclado tras superar
     * el control de errores
     */
    public static int leerEnteroPositivo(int limite) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre 1 y " + limite + ": ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Debe ser un entero: ");
            }
            num = teclado.nextInt();
        } while (num <= 0 || num > limite);
        return num;
    }

    public static int leerEnteroPositivoCero(int limite) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre 0 y " + limite + ": ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Debe ser un entero: ");
            }
            num = teclado.nextInt();
        } while (num < 0 || num > limite);
        return num;
    }

    /**
     * Lee por teclado una cadena con control de errores, mostrando un texto
     *
     * @param texto muestra un texto para indicar el tipo de dato que se espera
     * recibir
     * @return devuelve una cadena introducida por teclado
     */
    public static String leerString(String texto) {
        String cadena;
        Scanner teclado = new Scanner(System.in);
        System.out.println(texto);
        cadena = teclado.nextLine();
        return cadena;
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
    public static boolean leerSiNo() {
        char caracter;
        boolean primero = true, resultado = false;
        do {
            if (!primero) {
                System.out.println("Debe introducir S o N");
            }
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
     * Realiza el paso a fichero mediante serialización de un LinkedHashSet.
     *
     * @param jugadores: contiene el conjunto que se guardará en fichero.
     * @param fichero: contiene la ruta y nombre del fichero donde se guardará
     * el conjunto.
     */
    public static void serializaFicheroOutput(LinkedHashSet<Jugador> jugadores, String fichero) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(fichero));
            escribiendo_fichero.writeObject(jugadores);
            escribiendo_fichero.close();
        } catch (IOException ioe) {
            System.out.println("Error de escritura en fichero");
        }
    }

    /**
     * Realiza el paso a fichero mediante serialización de un objeto de tipo
     * Banca.
     *
     * @param banca: representa al objeto de tipo Banca que se guardará en
     * fichero.
     * @param fichero: contiene la ruta y nombre del fichero donde se guardará
     * el objeto de tipo Banca.
     */
    public static void serializaFicheroOutput(Banca banca, String fichero) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(fichero));
            escribiendo_fichero.writeObject(banca); //// aquí da el error
            escribiendo_fichero.close();
        } catch (IOException ioe) {
            System.out.println("Error de escritura en fichero");
        }
    }

    /**
     * Realiza la carga en un LinkedHashSet del contenido de un fichero mediante
     * serialización.
     *
     * @param listaPlaza: contiene el LinkedHashSet donde se cargarán los datos
     * del fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizará la carga de datos.
     * @return devuelve un LinkedHashSet con la lista de plazas cargadas desde
     * el fichero
     */
    public static LinkedHashSet<Jugador> serializaFicheroInput(LinkedHashSet<Jugador> jugadores, String fichero) {
        try {
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            LinkedHashSet<Jugador> jugadoresRecuperados = (LinkedHashSet<Jugador>) recuperando_fichero.readObject();
            jugadores = jugadoresRecuperados;
            recuperando_fichero.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe ");
        } catch (IOException ioe) {
            System.out.println("Error: falló la lectura del fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return jugadores;
    }

    /**
     * Realiza la carga en un objeto de tipo Banca del contenido de un fichero
     * mediante serialización.
     *
     * @param banca: contiene el objeto de tipo Banca donde se cargarán los
     * datos del fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizará la carga de datos.
     * @return devuelve un objeto de tipo Banca cargado desde el fichero
     */
    public static Banca serializaFicheroInput(Banca banca, String fichero) {
        try {
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            Banca bancaRecuperada = (Banca) recuperando_fichero.readObject();
            banca = bancaRecuperada;
            recuperando_fichero.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe ");
        } catch (IOException ioe) {
            System.out.println("Error: falló la lectura del fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return banca;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 0;
        System.out.println("Partida de black jack");
        Baraja baraja = new Baraja();
        // barajamos el mazo
        baraja.barajar();

        // comienza el juego, mostramos las reglas
        System.out.println("BLACKJACK");
        System.out.println("==========");
        System.out.println("  Reglas: ");
        System.out.println("	-Cada jugador tras hacer su apuesta recibe dos cartas iniciales boca arriba");
        System.out.println("	-La banca recibe una carta boca arriba y otra boca abajo");
        System.out.println("	-El valor de cada carta es su número, excepto las figuras, que valen 10 y el as, que vale 11");
        System.out.println("	-Cada nuevo turno los jugadores pueden pedir nueva carta o plantarse");
        System.out.println("	-La banca está obligada a pedir carta hasta sumar al menos 17. Si llega a este valor se plantará");
        System.out.println("	-Gana el jugador con más puntos sin pasarse de 21");
        System.out.println("	-Si un jugador o la banca llega a 21 puntos gana la partida");
        System.out.println("	-El ganador recupera el dinero de su apuesta y gana una cantidad adicional igual");
        System.out.println("");
        System.out.println("");

        Banca banca = new Banca();
        System.out.println("¿Desea iniciar una nueva partida (0) o cargar una desde fichero (1)?");
        opcion = leerEnteroPositivoCero(1);
        if (opcion == 0) {
            inicializarJugadores();
        } else {
            jugadores = serializaFicheroInput(jugadores, FICHERO_JUGADORES);
            banca = serializaFicheroInput(banca, FICHERO_BANCA);
            mostrarEstadoInicial(banca, jugadores);
        }
        int[] estadoJugadores = new int[jugadores.size()];
        inicializarEstadoJugadores(estadoJugadores);
        rondaApuestas();
        repartir(baraja, banca);
        do {
            turnoJugadores(baraja, estadoJugadores);
            turnoBanca(baraja, banca);
            System.out.println("");
            mostrarEstadoFinalRonda(banca, jugadores);
        } while (partidaEnCurso(banca.getSigueJugando(), estadoJugadores));
        System.out.println("");
        System.out.println("Final de la partida");
        evaluaGanadores(banca, jugadores);
        serializaFicheroOutput(jugadores, FICHERO_JUGADORES);
        serializaFicheroOutput(banca, FICHERO_BANCA);
    }
}
