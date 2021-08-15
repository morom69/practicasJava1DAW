package parking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
// import static parking.CargaDatosPrueba.cargaDatosPrueba; // habilitar si se desea cargar datos de prueba desde la clase CargaDatosPrueba.java en lugar de hacerlo desde fichero

/**
 *
 * @author Miguel Moro
 */
public class Parking {

    static final String FICHERO = "plazas.dat"; // nombre del fichero donde se guarda y almacena la informaci�n

    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<Plaza> listaPlazas = new ArrayList<>(); // estructura utilizada para el manejo de las plazas de aparcamiento
        int opcion;
        /*
        // este bloque se puede habilitar para cargar datos de prueba desde
        // la clase CargaDatosPrueba.java en lugar de cargar datos de fichero
        // en tal caso se debe habilitar tambi�n el import correspondiente en la cabecera
        // ************************* INICIO *************************************
        Scanner lectura = new Scanner(System.in);
        System.out.println("Desea cargar datos de prueba?");
        System.out.println("Entrar 1 para cargar datos de prueba, cualquier otro entero para saltar la carga: ");
        while (!lectura.hasNextInt()) { //validaci�n de que para la opci�n de men� se introduce un valor num�rico
            lectura.next();
            System.out.println("Debe introducir un valor entero: ");
        }
        opcion = lectura.nextInt();
        lectura.skip("\n"); //se utiliza para evitar que la pr�xima vez salte la l�nea al interpretar el enter de la lectura anterio
        if (opcion == 1) { // si queremos cargar los datos de prueba
            cargaDatosPrueba(listaPlazas);
            System.out.println("Datos de prueba cargados");
            // y guardamos los datos en fichero - ******** SERIALIZACI�N ***********************
            serializaFicheroOutput(listaPlazas, FICHERO);
        }
        // fin carga datos de prueba
        // ************************* FIN DEL BLOQUE DE CARGA DE DATOS DE CLASE CargaDatosPrueba.java *************************************
         */
        // cargamos los datos de fichero en el arrayList de plazas - ******** SERIALIZACI�N ***********************
        listaPlazas = serializaFicheroInput(listaPlazas, FICHERO);
        do {
            System.out.println();
            System.out.println("GESTI�N DE PLAZAS DE PARKING");
            System.out.println("============================");
            System.out.println("1.Alta de alquiler de plaza");
            System.out.println("2.Baja de alquiler de plaza");
            System.out.println("3.Modificar alquiler de plaza");
            System.out.println("4.Gesti�n de plazas");
            System.out.println("5.Salir del programa");
            System.out.println("Introduzca opci�n: ");

            // leemos y validamos la opci�n seleccionada
            opcion = leerEnteroEntreLimites(1, 5);

            switch (opcion) {
                case 1 -> { // alta de alquiler de plazas
                    System.out.println("1.Alta de alquiler de plazas de parking");
                    altaPlaza(listaPlazas);
                    break;
                }
                case 2 -> {
                    System.out.println("2.Baja de alquiler de plazas de parking");
                    bajaPlaza(listaPlazas);
                    break;
                    //bajaPersonas(listaPlazas);
                }
                case 3 -> {
                    System.out.println("3.Modificar alquiler de plaza"); // modifica los datos del alquiler, no modifica los datos de la plaza
                    modificaAlquiler(listaPlazas);
                    break;
                }
                case 4 -> {
                    System.out.println("4.Gesti�n de plazas");
                    gestionPlazas(listaPlazas);
                    break;
                }
                case 5 -> {
                    System.out.println("5.Salir del programa");
                }
            }
        } while (opcion != 5);
    }

    /**
     * Gestiona el alta de alquiler de plazas de parking. Solicita por teclado
     * un n�mero de plaza, eval�a si la plaza est� disponible y en caso
     * afirmativo llama al m�todo que asignar� la plaza. Si la plaza no existe o
     * no est� disponible muestra un mensaje informativo y volvemos al men�.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento y de la que vamos a seleccionar la plaza a asignar.
     */
    public static void altaPlaza(ArrayList<Plaza> listaPlaza) {
        int idPlaza;
        // comenzamos el proceso de alta
        System.out.print("Hace falta un n�mero de plaza. ");
        idPlaza = leerEnteroTexto("n�mero de plaza");

        switch (plazaEstaLibre(listaPlaza, idPlaza)) {
            case -1 -> // la plaza no existe
                System.out.println("La plaza no existe.");
            case 0 -> // la plaza existe pero no est� libre
                System.out.println("La plaza no se encuentra disponible.");
            case 1 -> { // la plaza existe y est� libre
                System.out.println("La plaza est� disponible, procedemos a asignarla.");
                asignarPlaza(listaPlaza, idPlaza);
            }
        }
    }

    /**
     * Asigna el nuevo alquiler para la plaza facilitada por par�metro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de plaza que vamos a asignar.
     */
    public static void asignarPlaza(ArrayList<Plaza> listaPlazas, int idPlaza) {
        // variables comunes de Plaza
        int indice;
        Plaza plaza = recuperarPlaza(listaPlazas, idPlaza);

        plaza.setInquilino(leerStringTexto("nombre y apellidos de la persona que alquila la plaza"));
        plaza.setCuota(leerEnteroTexto("cuota "));
        plaza.setInquilinoTelf(leerTelefono());
        plaza.setInquilinoPagado(leerSiNo("confirmar si se encuentra al d�a del pago"));
        plaza.setOcupada(true);
        // en un alquiler no modificamos las caracter�sticas de la plaza
        // ni ancho ni largo se modifican
        // en un alquiler no se pueden modificar los datos propios de la plaza
        // por lo tanto los datos espec�ficos de cada subclase no se modifican
        // tipo de coche, n�mero de motos y acceso desde pasillo quedan como est�n
        // guardamos el fichero con los cambios
        
        indice = recuperarIndicePlaza(listaPlazas, idPlaza);
        System.out.println("Informaci�n con los nuevos datos de la plaza:\n" + listaPlazas.get(indice).toString());
        serializaFicheroOutput(listaPlazas, FICHERO);
    }

    /**
     * Gestiona la baja de alquiler de plazas de parking. Solicita por teclado
     * un n�mero de plaza, eval�a si la plaza existe y si est� ocupada y en caso
     * afirmativo, con confirmaci�n, llama al m�todo que realizar� la baja del
     * alquiler de la plaza. Si la plaza no existe o ya est� disponible muestra
     * un mensaje informativo y volvemos al men�.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento y de la que vamos a seleccionar la plaza a desasignar.
     */
    public static void bajaPlaza(ArrayList<Plaza> listaPlaza) {
        int idPlaza, indexPlaza;
        boolean confirmacion = false;
        // comenzamos el proceso de alta
        System.out.print("Hace falta un n�mero de plaza. ");
        idPlaza = leerEnteroTexto("n�mero de plaza");

        switch (plazaEstaLibre(listaPlaza, idPlaza)) {
            case -1 -> // la plaza no existe
                System.out.println("La plaza no existe.");
            case 1 -> // la plaza existe pero est� libre
                System.out.println("La plaza no se encuentra ocupada.");
            case 0 -> { // la plaza existe y est� ocupada, tiene sentido dar la baja
                System.out.println("La plaza est� ocupada, procedemos a liberarla.");
                confirmacion = leerSiNo("confirmaci�n de baja");
                if (confirmacion) { // damos de baja el alquiler de la plaza
                    indexPlaza = recuperarIndicePlaza(listaPlaza, idPlaza);
                    bajaAlquilerPlaza(listaPlaza, idPlaza);
                    System.out.println("La plaza " + idPlaza + " ha quedado libre");
                } else {
                    System.out.println("Baja de plaza anulada por el usuario");
                }
            }
        }
    }

    /**
     * Realiza la baja del alquiler para la plaza facilitada por par�metro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de plaza que vamos a desasignar.
     */
    public static void bajaAlquilerPlaza(ArrayList<Plaza> listaPlazas, int idPlaza) {
        int indexPlaza = recuperarIndicePlaza(listaPlazas, idPlaza);
        if (plazaEstaLibre(listaPlazas, idPlaza) != 0) { // si la plaza no est� resultado no podemos liberarla
            System.out.println("La plaza " + listaPlazas.get(idPlaza).getIdPlaza() + " no est� ocupada");
        } else { // la plaza est� resultado, la liberamos
            Plaza p = listaPlazas.get(indexPlaza);
            p.setInquilinoPagado(true);
            p.setInquilinoTelf("");
            p.setInquilino("");
            p.setOcupada(false);
            // la cuota y dimensiones de la plaza no se actualiza
            // los datos espec�ficos de subclase no es necesario actualizarlos
            // guardamos la modificaci�n en fichero
            serializaFicheroOutput(listaPlazas, FICHERO);
            System.out.println("Informaci�n con los nuevos datos de la plaza:\n" + listaPlazas.get(indexPlaza).toString());
        }
    }

    /**
     * Gesti�n de alquiler de plazas de parking. Seg�n la opci�n de men�
     * seleccionada llama al m�todo correspondiente.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     */
    public static void gestionPlazas(ArrayList<Plaza> listaPlaza) {
        Scanner lectura = new Scanner(System.in);
        int opcion, idPlaza;
        do {
            System.out.println();
            System.out.println("GESTI�N DE PLAZAS");
            System.out.println("=================");
            System.out.println("1.- Listado total de plazas");
            System.out.println("2.- Listado de plazas alquiladas");
            System.out.println("3.- Listado de plazas libres");
            System.out.println("4.- Modificar la plaza");
            System.out.println("5.- Volver");
            System.out.println("Introduzca opci�n: ");

            while (!lectura.hasNextInt()) { //validaci�n de que para la opci�n de men� se introduce un valor num�rico
                lectura.next();
                System.out.println("Debe introducir un valor num�rico entre 1 y 5: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la pr�xima vez salte la l�nea al interpretar el enter de la lectura anterior

            switch (opcion) {
                case 1 -> // tipo 1 = listado total de plazas
                    listarArrayListPlaza(listaPlaza, 1);
                case 2 -> // tipo 2 = listado de plazas alquiladas
                    listarArrayListPlaza(listaPlaza, 2);
                case 3 -> // tipo 3 = listado de plazas libres
                    listarArrayListPlaza(listaPlaza, 3);
                case 4 -> // modificar la informaci�n de la propia plaza, no modifica la informaci�n del alquiler
                {
                    idPlaza = leerEnteroTexto("plaza a modificar");
                    modificaPlaza(listaPlaza, idPlaza);
                    break;
                }
                case 5 -> // volver atr�s
                {
                    break;
                }
            }
        } while (opcion != 5);
    }

    /**
     * Lista el contenido de un arrayList de plazas de aparcamiento.
     *
     * @param lista: lista cuyo contenido vamos a listar.
     * @param tipo: tipo de listado que queremos obtener. 1 = todas, las plazas;
     * 2 = plazas alquiladas; 3 = plazas libres.
     */
    public static void listarArrayListPlaza(ArrayList<Plaza> listaPlaza, int tipo) {
        for (int i = 0; i < listaPlaza.size(); i++) {
            if (listaPlaza.get(i) != null) {
                if ((tipo == 1 || tipo == 2) && (listaPlaza.get(i).isOcupada() == true)) {  // listado de todas las plazas o plazas alquiladas
                    System.out.println(listaPlaza.get(i).toString());
                } else if ((tipo == 1 || tipo == 3) && (listaPlaza.get(i).isOcupada() == false)) { // listado de todas las plazas o plazas libres
                    System.out.println(listaPlaza.get(i).toString());
                }
            } else {
                System.out.println("No hay plazas que mostrar");
            }
        }
    }

    /**
     * Realiza la modificaci�n de la informaci�n propia de la plaza (datos
     * f�sicos de la plaza - no se realiza ninguna modificaci�n referente al
     * alquiler de �sta) pasada por par�metro. Se muestra la informaci�n actual
     * y se piden los nuevos datos. Se permite que los nuevos datos sean los
     * mismos que los actuales.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de plaza de la que vamos a modificar la
     * informaci�n.
     */
    public static void modificaPlaza(ArrayList<Plaza> listaPlaza, int idPlaza) {
        int indice = recuperarIndicePlaza(listaPlaza, idPlaza);
        Plaza p = null;
        if (indice == -1) { // la plaza no existe
            System.out.println("Plaza no encontrada");
        } else { //modificamos la plaza
            System.out.println("Modificaremos los datos de la plaza " + idPlaza);
            System.out.println("Informaci�n actual de la plaza:\n" + listaPlaza.get(indice).toString());
            System.out.println("Introduce nuevos datos.");
            modificarIdPlaza(listaPlaza, indice);
            idPlaza = listaPlaza.get(indice).getIdPlaza();
            modificarLargoPlaza(listaPlaza, indice);
            modificarAnchoPlaza(listaPlaza, indice);
            p = recuperarPlaza(listaPlaza, idPlaza);
            if (p instanceof PlazaCoche) {
                modificarTipoCoche(p);
            }
            if (p instanceof PlazaMoto) {
                modificarNumeroMotos(p);
            }
            if (p instanceof PlazaMinusvalido) {
                ((PlazaMinusvalido) p).setAccesoPasillo(leerSiNo("acceso desde pasillo"));
            }
            // guardamos la modificaci�n en el fichero de datos
            serializaFicheroOutput(listaPlaza, FICHERO);
            System.out.println("Informaci�n con los nuevos datos de la plaza:\n" + listaPlaza.get(indice).toString());
        }
    }

    /**
     * Realiza la modificaci�n de la informaci�n del alquiler de la plaza (no se
     * realiza ninguna modificaci�n referente a los datos f�sicos propios �sta)
     * pasada por par�metro. Se muestra la informaci�n actual y se piden los
     * nuevos datos. Se permite que los nuevos datos sean los mismos que los
     * actuales.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de plaza de la que vamos a modificar la
     * informaci�n.
     */
    public static void modificaAlquiler(ArrayList<Plaza> listaPlaza) {
        int idPlaza, cuota, indice;
        boolean ocupada, inquilinoPagado;
        String inquilino, inquilinoTelf;

        System.out.print("Hace falta un n�mero de plaza. ");
        idPlaza = leerEnteroTexto("n�mero de plaza");
        indice = recuperarIndicePlaza(listaPlaza, idPlaza);
        Plaza plaza = recuperarPlaza(listaPlaza, idPlaza);

        switch (plazaEstaLibre(listaPlaza, idPlaza)) {
            case -1 -> // la plaza no existe
                System.out.println("La plaza no existe.");
            case 1 -> // la plaza existe pero est� libre
                System.out.println("La plaza no se encuentra ocupada.");
            case 0 -> // la plaza existe y est� alquilada, modificamos los datos del alquiler
            {
                System.out.println("Modificaremos los datos del alquiler de la plaza " + idPlaza);
                System.out.println("Informaci�n actual de la plaza:\n" + listaPlaza.get(indice).toString());
                System.out.println("Introduce nuevos datos.");

                plaza.setInquilino(leerStringTexto("nombre y apellidos de la persona que alquila la plaza"));
                plaza.setCuota(leerEnteroTexto("cuota "));
                plaza.setInquilinoTelf(leerTelefono());
                plaza.setInquilinoPagado(leerSiNo("confirmar si se encuentra al d�a del pago"));

                // en un alquiler no modificamos las caracter�sticas de la plaza
                // ni ancho ni largo se modifican
                // en un alquiler no se pueden modificar los datos propios de la plaza
                // por lo tanto los datos espec�ficos de cada subclase no se modifican
                // tipo de coche, n�mero de motos y acceso desde pasillo quedan como est�n
                
                // guardamos el fichero con los cambios
                serializaFicheroOutput(listaPlaza, FICHERO);
                System.out.println("Informaci�n con los nuevos datos de la plaza:\n" + listaPlaza.get(indice).toString());
                break;
            }
        }
    }

    /**
     * Modifica el n�mero de plaza para la plaza cuyo �ndice dentro del
     * arrayList facilitado por par�metro se facilita tambi�n por par�metro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param indice: indice dentro el arrayList de la plaza para la que vamos a
     * modificar la informaci�n.
     */
    public static void modificarIdPlaza(ArrayList<Plaza> listaPlaza, int indice) {
        int nuevoId;
        boolean modificado = false;
        System.out.println("N�mero de plaza actual " + listaPlaza.get(indice).getIdPlaza());
        System.out.println("Si no desea modificarlo puede introducir el mismo dato:");
        do {
            nuevoId = leerEnteroTexto("nuevo n�mero de plaza");
            if ((existePlaza(listaPlaza, nuevoId)) && (nuevoId != listaPlaza.get(indice).getIdPlaza())) {
                // si la plaza ya existe y es diferente a la actual
                System.out.println("La plaza " + listaPlaza.get(indice).getIdPlaza() + " ya existe, introduzca otro n�mero de plaza no ocupado");
                System.out.println("Recuerde que si no quiere modificarla puede volver a introducir el mismo n�mero de plaza");
            } else {
                listaPlaza.get(indice).setIdPlaza(nuevoId);
                modificado = true;
            }
        } while (!modificado);
    }

    /**
     * Modifica el largo en cent�metros para la plaza cuyo �ndice dentro del
     * arrayList facilitado por par�metro se facilita tambi�n por par�metro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param indice: indice dentro el arrayList de la plaza para la que vamos a
     * modificar la informaci�n.
     */
    public static void modificarLargoPlaza(ArrayList<Plaza> listaPlaza, int indice) {
        listaPlaza.get(indice).setLargo(leerEnteroTexto("largo de la plaza a modificar"));
    }

    /**
     * Modifica el ancho en cent�metros para la plaza cuyo �ndice dentro del
     * arrayList facilitado por par�metro se facilita tambi�n por par�metro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param indice: indice dentro el arrayList de la plaza para la que vamos a
     * modificar la informaci�n.
     */
    public static void modificarAnchoPlaza(ArrayList<Plaza> listaPlaza, int indice) {
        listaPlaza.get(indice).setAncho(leerEnteroTexto("ancho de la plaza a modificar"));
    }

    /**
     * Modifica el tipo de coche recomendado (peque�o, est�ndar o grande) para
     * la plaza que se facilita por par�metro. Llama al m�todo que lee el tipo
     * de coche recomendado.
     *
     * @param p: objeto de tipo Plaza para la cual vamos a modificar la
     * informaci�n.
     */
    public static void modificarTipoCoche(Plaza p) {
        if (p instanceof PlazaCoche) {
            ((PlazaCoche) p).setTipoCoche(leerTipoCoche());
        } else {
            throw new IllegalArgumentException("Tipo de plaza incorrecto");
        }
    }

    /**
     * Devuelve como entero la clase espec�fica que queremos seleccionar para un
     * objeto de tipo Plaza (peque�o, est�ndar o grande).
     *
     * @return devuelve un entero con el tipo de coche seleccionado, 1 = coche
     * peque�o, 2 = coche est�ndar, 3 = coche grande.
     */
    public static int leerTipoCoche() {
        boolean primero;
        int tipoCoche = 0;
        //leer tipoCoche (peque�o, est�ndar o grande)
        primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir un entero:");
                System.out.println("1 para coche peque�o");
                System.out.println("2 para coche est�ndar");
                System.out.println("3 para coche grande");
            }
            System.out.println("Si se trata de una plaza para coche peque�o introduzca 1");
            System.out.println("Si se trata de una plaza para coche est�ndar introduzca 2");
            System.out.println("Si se trata de una plaza para coche grande introduzca 3");
            tipoCoche = leerEnteroTexto("tipo");
            primero = false;
        } while (tipoCoche < 1 || tipoCoche > 3);
        return tipoCoche;
    }

    /**
     * Modifica el n�mero m�ximo recomendado de motos a estacionar en la plaza
     * que se facilita por par�metro. Para ello llama al m�todo que lee un
     * entero por teclado.
     *
     * @param p: objeto de tipo Plaza para la cual vamos a modificar la
     * informaci�n.
     */
    public static void modificarNumeroMotos(Plaza p) {
        if (p instanceof PlazaMoto) {
            ((PlazaMoto) p).setNumeroMotos(leerEnteroTexto("numero de motos"));
        } else {
            throw new IllegalArgumentException("Tipo de plaza incorrecto");
        }
    }

    /**
     * Devuelve como entero la clase espec�fica que queremos seleccionar para un
     * objeto de tipoCoche Plaza (PlazaMinusvalido, PlazaMoto o PlazaCoche).
     *
     * @return devuelve un entero con la inicial de la elegida, 1 = coche, 2 =
     * moto, 3 = minusv�lido.
     */
    public static int leerTipoPlaza() {
        boolean primero;
        int tipoPlaza = 0;
        //leer tipoCoche de plaza (coche, moto o minusv�lido)
        primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir un entero:");
                System.out.println("1 para coche");
                System.out.println("2 para moto");
                System.out.println("3 para minusv�lido");
            }
            System.out.println("Si se trata de una plaza para coche introduzca 1");
            System.out.println("Si se trata de una plaza para moto introduzca 2");
            System.out.println("Si se trata de una plaza para minusv�lido introduzca 3");
            tipoPlaza = leerEnteroTexto("tipo");
            primero = false;
        } while (tipoPlaza < 1 || tipoPlaza > 3);
        return tipoPlaza;
    }

    /**
     * Devuelve como String un n�mero de tel�fono v�lido. Para ello utiliza el
     * m�todo validarTelefono().
     *
     * @return devuelve una cadena con un n�mero de tel�fono v�lido.
     */
    public static String leerTelefono() {
        boolean primero = true;
        String inquilinoTelf;
        do {
            System.out.print("Tel�fono, ");
            if (!primero) {
                System.out.println("Debe introducir uno v�lido");
            }
            inquilinoTelf = leerString();
            primero = false;
        } while (!validarTelefono(inquilinoTelf));
        return inquilinoTelf;
    }

    /**
     * Pide al usuario por teclado introducir una cadena de texto y la devuelve.
     *
     * @return devuelve la cadena de texto le�da por teclado.
     */
    public static String leerString() {
        Scanner teclado = new Scanner(System.in);
        String lectura;
        System.out.println("Introduzca un dato:");
        lectura = teclado.nextLine();
        return lectura;
    }

    /**
     * Pide al usuario por teclado introducir una cadena de texto mostrando al
     * preguntar el texto facilitado como par�metro y la devuelve.
     *
     * @param texto se muestra al pedir al usuario el dato a introducir para
     * saber qu� le estamos pidiendo.
     * @return devuelve la cadena de texto le�da por teclado
     */
    public static String leerStringTexto(String texto) {
        Scanner teclado = new Scanner(System.in);
        String lectura;
        System.out.println("Introduzca " + texto + ":");
        lectura = teclado.nextLine();
        return lectura;
    }

    /**
     * Pide al usuario por teclado introducir un entero y lo devuelve. Se
     * realiza una validaci�n hasta que el tipo de dato introducido es correcto.
     *
     * @return devuelve un entero.
     */
    public static int leerEntero() {
        Scanner teclado = new Scanner(System.in);
        int lectura;
        System.out.println("Introduzca un n�mero entero: ");
        while (!teclado.hasNextInt()) {
            teclado.next(); //si lo que se ha introducido no es un n�mero lo sacamos del buffer
            System.out.println("El valor introducido no es un entero, vuelva a intentarlo:");
        }
        lectura = teclado.nextInt();
        return lectura;
    }

    /**
     * Pide al usuario por teclado introducir un entero mostrando al preguntar
     * el texto facilitado como par�metro y lo devuelve. Se realiza una
     * validaci�n hasta que el tipo de dato introducido es correcto.
     *
     * @param texto: se muestra al pedir al usuario el dato a introducir para
     * saber qu� le estamos pidiendo.
     * @return devuelve un entero.
     */
    public static int leerEnteroTexto(String texto) {
        Scanner teclado = new Scanner(System.in);
        int lectura;
        System.out.println("Introduzca " + texto + ": ");
        while (!teclado.hasNextInt()) {
            teclado.next(); //si lo que se ha introducido no es un n�mero lo sacamos del buffer
            System.out.println("El valor introducido no es un n�mero de " + texto + ", vuelva a intentarlo:");
        }
        lectura = teclado.nextInt();
        return lectura;
    }

    /**
     * Pide al usuario por teclado introducir un entero mostrando al preguntar
     * el texto facilitado como par�metro y lo devuelve. Se valida que el valor
     * introducido se encuentre entre los l�mites inferior y superior
     * facilitados como par�metros (ambos incluidos).
     *
     * @param inferior: valor inferior. Se validar� que el n�mero introducido no
     * sea menor que este valor.
     * @param superior: valor superior. Se validar� que el n�mero introducido no
     * sea mayor que este valor.
     * @return devuelve un entero entre los dos l�mites pasados como par�metros
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
     * Pide al usuario por teclado introducir un car�cter y lo devuelve
     * convertido a may�scula. Se realiza una validaci�n hasta que el tipo de
     * dato introducido es correcto.
     *
     * @return devuelve un car�cter. Devuelve siempre el car�cter en may�scula.
     */
    public static char leerCaracter() {
        char caracter;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un car�cter: ");
        caracter = teclado.next().charAt(0);
        if (Character.isLowerCase(caracter)) { //convertimos a may�sculas
            caracter = Character.toUpperCase(caracter);
        }
        return caracter;
    }

    /**
     * Pide al usuario por teclado elegir una opci�n entre S� o No y lo devuelve
     * como boolean. Si se introduce una S se devolver� true y si se introduce
     * una N se devolver� false, independientemente para ambas si el car�cter es
     * en may�scula o min�scula. Se realiza una validaci�n hasta que el dato
     * introducido es v�lido.
     *
     * @return devuelve un booleano, true si se ha seleccionado "S�" o false si
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
     * Devuelve una copia del arrayList de elementos de clase Plaza facilitado
     * como par�metro.
     *
     * @param listaPlaza: arrayList de elementos de tipo Plaza.
     * @return devuelve una copia del arrayList de elementos de clase Plaza
     * facilitado como par�metro.
     */
    public static ArrayList<Plaza> copiaArrayListPlaza(ArrayList<Plaza> listaPlaza) {
        ArrayList<Plaza> copiaListaPlaza = new ArrayList<>();
        if (listaPlaza.size() > 0) {
            for (int i = 0; i < listaPlaza.size(); i++) {
                copiaListaPlaza.add(listaPlaza.get(i));
            }
        }
        return copiaListaPlaza;
    }

    /**
     * Devuelve si una plaza de aparcamiento est� libre (sin alquilar).
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de la plaza que vamos a evaluar si est� libre.
     * @return devuelve un entero: -1 si la plaza no existe, 1 si la plaza
     * existe y est� libre y 0 si la plaza existe pero no est� libre.
     */
    public static int plazaEstaLibre(ArrayList<Plaza> listaPlaza, int idPlaza) {
        int resultado = -1;
        // para no modificar la lista existente usamos una copia
        ArrayList<Plaza> copiaListaPlaza = new ArrayList<>();
        copiaListaPlaza = copiaArrayListPlaza(listaPlaza);
        Plaza p;
        int i = 0;
        boolean encontrado = false;
        if (listaPlaza.size() > 0) { // si hay elementos
            do {
                p = copiaListaPlaza.get(i);
                if (p.getIdPlaza() == idPlaza) { // la plaza existe
                    if (p.isOcupada()) {
                        resultado = 0; // la plaza est� resultado
                    } else {
                        resultado = 1; // la plaza est� libre
                    }
                    encontrado = true;
                } else {
                    i++;
                }
            } while (i < copiaListaPlaza.size() && encontrado == false);
        }
        return resultado;
    }

    /**
     * Devuelve como entero el �ndice de una plaza de aparcamiento al facilitar
     * como par�metro el n�mero de �sta.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de la plaza de la que vamoa obtener su �ndice en
     * el arrayList de plazas.
     * @return devuelve -1 si la plaza no existe y su �ndice si existe.
     */
    public static int recuperarIndicePlaza(ArrayList<Plaza> listaPlaza, int idPlaza) {
        // para no modificar la lista existente usamos una copia
        ArrayList<Plaza> copiaListaPlaza = new ArrayList<>();
        copiaListaPlaza = copiaArrayListPlaza(listaPlaza);
        Plaza p;
        int indice = -1, i = 0;
        boolean encontrado = false;
        if (listaPlaza.size() > 0) { // si hay elementos
            do {
                p = copiaListaPlaza.get(i);
                if (p.getIdPlaza() == idPlaza) { // la plaza existe
                    encontrado = true;
                    indice = i;
                } else {
                    i++;
                }
            } while (i < copiaListaPlaza.size() && encontrado == false);
        }
        return indice;
    }

    /**
     * Devuelve el objeto de clase Plaza incluido en el arrayList facilitado
     * como par�metro al facilitar tambi�n como par�metro el n�mero de �sta.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de la plaza de la que vamoa obtener su �ndice en
     * el arrayList de plazas.
     * @return devuelve el objeto de clase Plaza correspondiente al n�mero de
     * plaza facilitado. Si la plaza no existe devuelve null.
     */
    public static Plaza recuperarPlaza(ArrayList<Plaza> listaPlaza, int idPlaza) {
        Plaza p = null;
        int indice = -1, i = 0;
        boolean encontrado = false;
        if (listaPlaza.size() > 0) { // si hay elementos
            do {
                p = listaPlaza.get(i);
                if (p.getIdPlaza() == idPlaza) { // la plaza existe
                    encontrado = true;
                    indice = i;
                } else {
                    i++;
                }
            } while (i < listaPlaza.size() && encontrado == false);
        }
        return p;
    }

    /**
     * Devuelve como boolean si en el arrayList facilitado como par�metro existe
     * la plaza cuyo n�mero es tambi�n facilitado como par�metro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: n�mero de la plaza de la que vamoa obtener su �ndice en
     * el arrayList de plazas.
     * @return devuelve true si la plaza con n�mero idPlaza existe en el
     * arrayList. False en caso contrario.
     */
    public static boolean existePlaza(ArrayList<Plaza> listaPlaza, int idPlaza) {
        // para no modificar la lista existente usamos una copia
        ArrayList<Plaza> copiaListaPlaza = new ArrayList<>();
        copiaListaPlaza = copiaArrayListPlaza(listaPlaza);
        Plaza p;
        int i = 0;
        boolean encontrado = false;
        if (listaPlaza.size() > 0) { // si hay elementos
            do {
                p = copiaListaPlaza.get(i);
                if (p.getIdPlaza() == idPlaza) { // la plaza existe
                    encontrado = true;
                } else {
                    i++;
                }
            } while (i < copiaListaPlaza.size() && encontrado == false);
        }
        return encontrado;
    }

    /**
     * Valida si la cadena pasada por par�metro contiene un n�mero de tel�fono
     * v�lido debe comenzar por 6, 8 � 9 y tener 9 d�gitos
     *
     * @param telefono: recibe el tel�fono a validar.
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario.
     */
    public static boolean validarTelefono(String telefono) {
        // Debe empezar por 6, 8 � 9 y tener 9 d�gitos
        return telefono.matches("(^[689][0-9]{8}$)");
    }

    /**
     * Realiza el paso a fichero mediante serializaci�n de un arrayList.
     *
     * @param listaPlaza: contiene el arrayList que se guardar� en fichero.
     * @param fichero: contiene la ruta y nombre del fichero donde se guardar�
     * el arrayList.
     */
    public static void serializaFicheroOutput(ArrayList<Plaza> listaPlazas, String fichero) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(fichero));
            escribiendo_fichero.writeObject(listaPlazas);
            escribiendo_fichero.close();
        } catch (IOException ioe) {
            System.out.println("Error de escritura en fichero");
        }
    }

    /**
     * Realiza la carga en un ArrayList del contenido de un fichero mediante
     * serializaci�n.
     *
     * @param listaPlaza: contiene el ArrayList donde se cargar�n los datos del
     * fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizar� la carga de datos.
     * @return devuelve un ArrayList con la lista de plazas cargadas desde el
     * fichero
     */
    public static ArrayList<Plaza> serializaFicheroInput(ArrayList<Plaza> listaPlazas, String fichero) {
        try {
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            ArrayList<Plaza> plazasRecuperadas = (ArrayList<Plaza>) recuperando_fichero.readObject();
            listaPlazas = plazasRecuperadas;
            recuperando_fichero.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe ");
        } catch (IOException ioe) {
            System.out.println("Error: fall� la lectura del fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return listaPlazas;
    }
}
