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

    static final String FICHERO = "plazas.dat"; // nombre del fichero donde se guarda y almacena la información

    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<Plaza> listaPlazas = new ArrayList<>(); // estructura utilizada para el manejo de las plazas de aparcamiento
        int opcion;
        /*
        // este bloque se puede habilitar para cargar datos de prueba desde
        // la clase CargaDatosPrueba.java en lugar de cargar datos de fichero
        // en tal caso se debe habilitar también el import correspondiente en la cabecera
        // ************************* INICIO *************************************
        Scanner lectura = new Scanner(System.in);
        System.out.println("Desea cargar datos de prueba?");
        System.out.println("Entrar 1 para cargar datos de prueba, cualquier otro entero para saltar la carga: ");
        while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
            lectura.next();
            System.out.println("Debe introducir un valor entero: ");
        }
        opcion = lectura.nextInt();
        lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterio
        if (opcion == 1) { // si queremos cargar los datos de prueba
            cargaDatosPrueba(listaPlazas);
            System.out.println("Datos de prueba cargados");
            // y guardamos los datos en fichero - ******** SERIALIZACIÓN ***********************
            serializaFicheroOutput(listaPlazas, FICHERO);
        }
        // fin carga datos de prueba
        // ************************* FIN DEL BLOQUE DE CARGA DE DATOS DE CLASE CargaDatosPrueba.java *************************************
         */
        // cargamos los datos de fichero en el arrayList de plazas - ******** SERIALIZACIÓN ***********************
        listaPlazas = serializaFicheroInput(listaPlazas, FICHERO);
        do {
            System.out.println();
            System.out.println("GESTIÓN DE PLAZAS DE PARKING");
            System.out.println("============================");
            System.out.println("1.Alta de alquiler de plaza");
            System.out.println("2.Baja de alquiler de plaza");
            System.out.println("3.Modificar alquiler de plaza");
            System.out.println("4.Gestión de plazas");
            System.out.println("5.Salir del programa");
            System.out.println("Introduzca opción: ");

            // leemos y validamos la opción seleccionada
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
                    System.out.println("4.Gestión de plazas");
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
     * un número de plaza, evalúa si la plaza está disponible y en caso
     * afirmativo llama al método que asignará la plaza. Si la plaza no existe o
     * no está disponible muestra un mensaje informativo y volvemos al menú.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento y de la que vamos a seleccionar la plaza a asignar.
     */
    public static void altaPlaza(ArrayList<Plaza> listaPlaza) {
        int idPlaza;
        // comenzamos el proceso de alta
        System.out.print("Hace falta un número de plaza. ");
        idPlaza = leerEnteroTexto("número de plaza");

        switch (plazaEstaLibre(listaPlaza, idPlaza)) {
            case -1 -> // la plaza no existe
                System.out.println("La plaza no existe.");
            case 0 -> // la plaza existe pero no está libre
                System.out.println("La plaza no se encuentra disponible.");
            case 1 -> { // la plaza existe y está libre
                System.out.println("La plaza está disponible, procedemos a asignarla.");
                asignarPlaza(listaPlaza, idPlaza);
            }
        }
    }

    /**
     * Asigna el nuevo alquiler para la plaza facilitada por parámetro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de plaza que vamos a asignar.
     */
    public static void asignarPlaza(ArrayList<Plaza> listaPlazas, int idPlaza) {
        // variables comunes de Plaza
        int indice;
        Plaza plaza = recuperarPlaza(listaPlazas, idPlaza);

        plaza.setInquilino(leerStringTexto("nombre y apellidos de la persona que alquila la plaza"));
        plaza.setCuota(leerEnteroTexto("cuota "));
        plaza.setInquilinoTelf(leerTelefono());
        plaza.setInquilinoPagado(leerSiNo("confirmar si se encuentra al día del pago"));
        plaza.setOcupada(true);
        // en un alquiler no modificamos las características de la plaza
        // ni ancho ni largo se modifican
        // en un alquiler no se pueden modificar los datos propios de la plaza
        // por lo tanto los datos específicos de cada subclase no se modifican
        // tipo de coche, número de motos y acceso desde pasillo quedan como están
        // guardamos el fichero con los cambios
        
        indice = recuperarIndicePlaza(listaPlazas, idPlaza);
        System.out.println("Información con los nuevos datos de la plaza:\n" + listaPlazas.get(indice).toString());
        serializaFicheroOutput(listaPlazas, FICHERO);
    }

    /**
     * Gestiona la baja de alquiler de plazas de parking. Solicita por teclado
     * un número de plaza, evalúa si la plaza existe y si está ocupada y en caso
     * afirmativo, con confirmación, llama al método que realizará la baja del
     * alquiler de la plaza. Si la plaza no existe o ya está disponible muestra
     * un mensaje informativo y volvemos al menú.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento y de la que vamos a seleccionar la plaza a desasignar.
     */
    public static void bajaPlaza(ArrayList<Plaza> listaPlaza) {
        int idPlaza, indexPlaza;
        boolean confirmacion = false;
        // comenzamos el proceso de alta
        System.out.print("Hace falta un número de plaza. ");
        idPlaza = leerEnteroTexto("número de plaza");

        switch (plazaEstaLibre(listaPlaza, idPlaza)) {
            case -1 -> // la plaza no existe
                System.out.println("La plaza no existe.");
            case 1 -> // la plaza existe pero está libre
                System.out.println("La plaza no se encuentra ocupada.");
            case 0 -> { // la plaza existe y está ocupada, tiene sentido dar la baja
                System.out.println("La plaza está ocupada, procedemos a liberarla.");
                confirmacion = leerSiNo("confirmación de baja");
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
     * Realiza la baja del alquiler para la plaza facilitada por parámetro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de plaza que vamos a desasignar.
     */
    public static void bajaAlquilerPlaza(ArrayList<Plaza> listaPlazas, int idPlaza) {
        int indexPlaza = recuperarIndicePlaza(listaPlazas, idPlaza);
        if (plazaEstaLibre(listaPlazas, idPlaza) != 0) { // si la plaza no está resultado no podemos liberarla
            System.out.println("La plaza " + listaPlazas.get(idPlaza).getIdPlaza() + " no está ocupada");
        } else { // la plaza está resultado, la liberamos
            Plaza p = listaPlazas.get(indexPlaza);
            p.setInquilinoPagado(true);
            p.setInquilinoTelf("");
            p.setInquilino("");
            p.setOcupada(false);
            // la cuota y dimensiones de la plaza no se actualiza
            // los datos específicos de subclase no es necesario actualizarlos
            // guardamos la modificación en fichero
            serializaFicheroOutput(listaPlazas, FICHERO);
            System.out.println("Información con los nuevos datos de la plaza:\n" + listaPlazas.get(indexPlaza).toString());
        }
    }

    /**
     * Gestión de alquiler de plazas de parking. Según la opción de menú
     * seleccionada llama al método correspondiente.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     */
    public static void gestionPlazas(ArrayList<Plaza> listaPlaza) {
        Scanner lectura = new Scanner(System.in);
        int opcion, idPlaza;
        do {
            System.out.println();
            System.out.println("GESTIÓN DE PLAZAS");
            System.out.println("=================");
            System.out.println("1.- Listado total de plazas");
            System.out.println("2.- Listado de plazas alquiladas");
            System.out.println("3.- Listado de plazas libres");
            System.out.println("4.- Modificar la plaza");
            System.out.println("5.- Volver");
            System.out.println("Introduzca opción: ");

            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 5: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior

            switch (opcion) {
                case 1 -> // tipo 1 = listado total de plazas
                    listarArrayListPlaza(listaPlaza, 1);
                case 2 -> // tipo 2 = listado de plazas alquiladas
                    listarArrayListPlaza(listaPlaza, 2);
                case 3 -> // tipo 3 = listado de plazas libres
                    listarArrayListPlaza(listaPlaza, 3);
                case 4 -> // modificar la información de la propia plaza, no modifica la información del alquiler
                {
                    idPlaza = leerEnteroTexto("plaza a modificar");
                    modificaPlaza(listaPlaza, idPlaza);
                    break;
                }
                case 5 -> // volver atrás
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
     * Realiza la modificación de la información propia de la plaza (datos
     * físicos de la plaza - no se realiza ninguna modificación referente al
     * alquiler de ésta) pasada por parámetro. Se muestra la información actual
     * y se piden los nuevos datos. Se permite que los nuevos datos sean los
     * mismos que los actuales.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de plaza de la que vamos a modificar la
     * información.
     */
    public static void modificaPlaza(ArrayList<Plaza> listaPlaza, int idPlaza) {
        int indice = recuperarIndicePlaza(listaPlaza, idPlaza);
        Plaza p = null;
        if (indice == -1) { // la plaza no existe
            System.out.println("Plaza no encontrada");
        } else { //modificamos la plaza
            System.out.println("Modificaremos los datos de la plaza " + idPlaza);
            System.out.println("Información actual de la plaza:\n" + listaPlaza.get(indice).toString());
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
            // guardamos la modificación en el fichero de datos
            serializaFicheroOutput(listaPlaza, FICHERO);
            System.out.println("Información con los nuevos datos de la plaza:\n" + listaPlaza.get(indice).toString());
        }
    }

    /**
     * Realiza la modificación de la información del alquiler de la plaza (no se
     * realiza ninguna modificación referente a los datos físicos propios ésta)
     * pasada por parámetro. Se muestra la información actual y se piden los
     * nuevos datos. Se permite que los nuevos datos sean los mismos que los
     * actuales.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de plaza de la que vamos a modificar la
     * información.
     */
    public static void modificaAlquiler(ArrayList<Plaza> listaPlaza) {
        int idPlaza, cuota, indice;
        boolean ocupada, inquilinoPagado;
        String inquilino, inquilinoTelf;

        System.out.print("Hace falta un número de plaza. ");
        idPlaza = leerEnteroTexto("número de plaza");
        indice = recuperarIndicePlaza(listaPlaza, idPlaza);
        Plaza plaza = recuperarPlaza(listaPlaza, idPlaza);

        switch (plazaEstaLibre(listaPlaza, idPlaza)) {
            case -1 -> // la plaza no existe
                System.out.println("La plaza no existe.");
            case 1 -> // la plaza existe pero está libre
                System.out.println("La plaza no se encuentra ocupada.");
            case 0 -> // la plaza existe y está alquilada, modificamos los datos del alquiler
            {
                System.out.println("Modificaremos los datos del alquiler de la plaza " + idPlaza);
                System.out.println("Información actual de la plaza:\n" + listaPlaza.get(indice).toString());
                System.out.println("Introduce nuevos datos.");

                plaza.setInquilino(leerStringTexto("nombre y apellidos de la persona que alquila la plaza"));
                plaza.setCuota(leerEnteroTexto("cuota "));
                plaza.setInquilinoTelf(leerTelefono());
                plaza.setInquilinoPagado(leerSiNo("confirmar si se encuentra al día del pago"));

                // en un alquiler no modificamos las características de la plaza
                // ni ancho ni largo se modifican
                // en un alquiler no se pueden modificar los datos propios de la plaza
                // por lo tanto los datos específicos de cada subclase no se modifican
                // tipo de coche, número de motos y acceso desde pasillo quedan como están
                
                // guardamos el fichero con los cambios
                serializaFicheroOutput(listaPlaza, FICHERO);
                System.out.println("Información con los nuevos datos de la plaza:\n" + listaPlaza.get(indice).toString());
                break;
            }
        }
    }

    /**
     * Modifica el número de plaza para la plaza cuyo índice dentro del
     * arrayList facilitado por parámetro se facilita también por parámetro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param indice: indice dentro el arrayList de la plaza para la que vamos a
     * modificar la información.
     */
    public static void modificarIdPlaza(ArrayList<Plaza> listaPlaza, int indice) {
        int nuevoId;
        boolean modificado = false;
        System.out.println("Número de plaza actual " + listaPlaza.get(indice).getIdPlaza());
        System.out.println("Si no desea modificarlo puede introducir el mismo dato:");
        do {
            nuevoId = leerEnteroTexto("nuevo número de plaza");
            if ((existePlaza(listaPlaza, nuevoId)) && (nuevoId != listaPlaza.get(indice).getIdPlaza())) {
                // si la plaza ya existe y es diferente a la actual
                System.out.println("La plaza " + listaPlaza.get(indice).getIdPlaza() + " ya existe, introduzca otro número de plaza no ocupado");
                System.out.println("Recuerde que si no quiere modificarla puede volver a introducir el mismo número de plaza");
            } else {
                listaPlaza.get(indice).setIdPlaza(nuevoId);
                modificado = true;
            }
        } while (!modificado);
    }

    /**
     * Modifica el largo en centímetros para la plaza cuyo índice dentro del
     * arrayList facilitado por parámetro se facilita también por parámetro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param indice: indice dentro el arrayList de la plaza para la que vamos a
     * modificar la información.
     */
    public static void modificarLargoPlaza(ArrayList<Plaza> listaPlaza, int indice) {
        listaPlaza.get(indice).setLargo(leerEnteroTexto("largo de la plaza a modificar"));
    }

    /**
     * Modifica el ancho en centímetros para la plaza cuyo índice dentro del
     * arrayList facilitado por parámetro se facilita también por parámetro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param indice: indice dentro el arrayList de la plaza para la que vamos a
     * modificar la información.
     */
    public static void modificarAnchoPlaza(ArrayList<Plaza> listaPlaza, int indice) {
        listaPlaza.get(indice).setAncho(leerEnteroTexto("ancho de la plaza a modificar"));
    }

    /**
     * Modifica el tipo de coche recomendado (pequeño, estándar o grande) para
     * la plaza que se facilita por parámetro. Llama al método que lee el tipo
     * de coche recomendado.
     *
     * @param p: objeto de tipo Plaza para la cual vamos a modificar la
     * información.
     */
    public static void modificarTipoCoche(Plaza p) {
        if (p instanceof PlazaCoche) {
            ((PlazaCoche) p).setTipoCoche(leerTipoCoche());
        } else {
            throw new IllegalArgumentException("Tipo de plaza incorrecto");
        }
    }

    /**
     * Devuelve como entero la clase específica que queremos seleccionar para un
     * objeto de tipo Plaza (pequeño, estándar o grande).
     *
     * @return devuelve un entero con el tipo de coche seleccionado, 1 = coche
     * pequeño, 2 = coche estándar, 3 = coche grande.
     */
    public static int leerTipoCoche() {
        boolean primero;
        int tipoCoche = 0;
        //leer tipoCoche (pequeño, estándar o grande)
        primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir un entero:");
                System.out.println("1 para coche pequeño");
                System.out.println("2 para coche estándar");
                System.out.println("3 para coche grande");
            }
            System.out.println("Si se trata de una plaza para coche pequeño introduzca 1");
            System.out.println("Si se trata de una plaza para coche estándar introduzca 2");
            System.out.println("Si se trata de una plaza para coche grande introduzca 3");
            tipoCoche = leerEnteroTexto("tipo");
            primero = false;
        } while (tipoCoche < 1 || tipoCoche > 3);
        return tipoCoche;
    }

    /**
     * Modifica el número máximo recomendado de motos a estacionar en la plaza
     * que se facilita por parámetro. Para ello llama al método que lee un
     * entero por teclado.
     *
     * @param p: objeto de tipo Plaza para la cual vamos a modificar la
     * información.
     */
    public static void modificarNumeroMotos(Plaza p) {
        if (p instanceof PlazaMoto) {
            ((PlazaMoto) p).setNumeroMotos(leerEnteroTexto("numero de motos"));
        } else {
            throw new IllegalArgumentException("Tipo de plaza incorrecto");
        }
    }

    /**
     * Devuelve como entero la clase específica que queremos seleccionar para un
     * objeto de tipoCoche Plaza (PlazaMinusvalido, PlazaMoto o PlazaCoche).
     *
     * @return devuelve un entero con la inicial de la elegida, 1 = coche, 2 =
     * moto, 3 = minusválido.
     */
    public static int leerTipoPlaza() {
        boolean primero;
        int tipoPlaza = 0;
        //leer tipoCoche de plaza (coche, moto o minusválido)
        primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir un entero:");
                System.out.println("1 para coche");
                System.out.println("2 para moto");
                System.out.println("3 para minusválido");
            }
            System.out.println("Si se trata de una plaza para coche introduzca 1");
            System.out.println("Si se trata de una plaza para moto introduzca 2");
            System.out.println("Si se trata de una plaza para minusválido introduzca 3");
            tipoPlaza = leerEnteroTexto("tipo");
            primero = false;
        } while (tipoPlaza < 1 || tipoPlaza > 3);
        return tipoPlaza;
    }

    /**
     * Devuelve como String un número de teléfono válido. Para ello utiliza el
     * método validarTelefono().
     *
     * @return devuelve una cadena con un número de teléfono válido.
     */
    public static String leerTelefono() {
        boolean primero = true;
        String inquilinoTelf;
        do {
            System.out.print("Teléfono, ");
            if (!primero) {
                System.out.println("Debe introducir uno válido");
            }
            inquilinoTelf = leerString();
            primero = false;
        } while (!validarTelefono(inquilinoTelf));
        return inquilinoTelf;
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
     * Pide al usuario por teclado introducir una cadena de texto mostrando al
     * preguntar el texto facilitado como parámetro y la devuelve.
     *
     * @param texto se muestra al pedir al usuario el dato a introducir para
     * saber qué le estamos pidiendo.
     * @return devuelve la cadena de texto leída por teclado
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
     * realiza una validación hasta que el tipo de dato introducido es correcto.
     *
     * @return devuelve un entero.
     */
    public static int leerEntero() {
        Scanner teclado = new Scanner(System.in);
        int lectura;
        System.out.println("Introduzca un número entero: ");
        while (!teclado.hasNextInt()) {
            teclado.next(); //si lo que se ha introducido no es un número lo sacamos del buffer
            System.out.println("El valor introducido no es un entero, vuelva a intentarlo:");
        }
        lectura = teclado.nextInt();
        return lectura;
    }

    /**
     * Pide al usuario por teclado introducir un entero mostrando al preguntar
     * el texto facilitado como parámetro y lo devuelve. Se realiza una
     * validación hasta que el tipo de dato introducido es correcto.
     *
     * @param texto: se muestra al pedir al usuario el dato a introducir para
     * saber qué le estamos pidiendo.
     * @return devuelve un entero.
     */
    public static int leerEnteroTexto(String texto) {
        Scanner teclado = new Scanner(System.in);
        int lectura;
        System.out.println("Introduzca " + texto + ": ");
        while (!teclado.hasNextInt()) {
            teclado.next(); //si lo que se ha introducido no es un número lo sacamos del buffer
            System.out.println("El valor introducido no es un número de " + texto + ", vuelva a intentarlo:");
        }
        lectura = teclado.nextInt();
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
     * Devuelve una copia del arrayList de elementos de clase Plaza facilitado
     * como parámetro.
     *
     * @param listaPlaza: arrayList de elementos de tipo Plaza.
     * @return devuelve una copia del arrayList de elementos de clase Plaza
     * facilitado como parámetro.
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
     * Devuelve si una plaza de aparcamiento está libre (sin alquilar).
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de la plaza que vamos a evaluar si está libre.
     * @return devuelve un entero: -1 si la plaza no existe, 1 si la plaza
     * existe y está libre y 0 si la plaza existe pero no está libre.
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
                        resultado = 0; // la plaza está resultado
                    } else {
                        resultado = 1; // la plaza está libre
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
     * Devuelve como entero el índice de una plaza de aparcamiento al facilitar
     * como parámetro el número de ésta.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de la plaza de la que vamoa obtener su índice en
     * el arrayList de plazas.
     * @return devuelve -1 si la plaza no existe y su índice si existe.
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
     * como parámetro al facilitar también como parámetro el número de ésta.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de la plaza de la que vamoa obtener su índice en
     * el arrayList de plazas.
     * @return devuelve el objeto de clase Plaza correspondiente al número de
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
     * Devuelve como boolean si en el arrayList facilitado como parámetro existe
     * la plaza cuyo número es también facilitado como parámetro.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de la plaza de la que vamoa obtener su índice en
     * el arrayList de plazas.
     * @return devuelve true si la plaza con número idPlaza existe en el
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
     * Valida si la cadena pasada por parámetro contiene un número de teléfono
     * válido debe comenzar por 6, 8 ó 9 y tener 9 dígitos
     *
     * @param telefono: recibe el teléfono a validar.
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario.
     */
    public static boolean validarTelefono(String telefono) {
        // Debe empezar por 6, 8 ó 9 y tener 9 dígitos
        return telefono.matches("(^[689][0-9]{8}$)");
    }

    /**
     * Realiza el paso a fichero mediante serialización de un arrayList.
     *
     * @param listaPlaza: contiene el arrayList que se guardará en fichero.
     * @param fichero: contiene la ruta y nombre del fichero donde se guardará
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
     * serialización.
     *
     * @param listaPlaza: contiene el ArrayList donde se cargarán los datos del
     * fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizará la carga de datos.
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
            System.out.println("Error: falló la lectura del fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return listaPlazas;
    }
}
