/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author migue
 */
public class Funciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    //VALIDACION DEL PRIMER CARACTER DE UN STRING:
    // if (respuesta.charAt(0) == 's')
    
    public static int leerEnteroPositivo() {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo: ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Debe ser un entero: ");
            }
            num = teclado.nextInt();
        } while (num <= 0);
        return num;
    }

    public static double leerDoublePositivo() {
        double num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un número positivo: ");
            while (!teclado.hasNextDouble()) {
                teclado.next();
                System.out.println("Debe ser un número: ");
            }
            num = teclado.nextDouble();
        } while (num <= 0);
        return num;
    }

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

    public static boolean esBisiesto(int año) {
        /**
         * Un año es bisiesto si es divisible por 4, excepto el último de cada
         * siglo, aquellos divisibles por 100, que para ser bisiestos, también
         * deben ser divisibles por 400
         */
        if (año < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean bisiesto = false;
        if ((año % 4 == 0) && ((año % 100 != 0) || (año % 400 == 0))) {
            bisiesto = true;
        }
        return bisiesto;
    }

    public static double potencia(double base, double exponente) {
        if ((base < 0 || exponente < 0) || (base == 0 && exponente == 0)) {
            throw new IllegalArgumentException("Los números deben ser positivos");
        }
        double potencia = Math.pow(base, exponente);
        return potencia;
    }

    public static boolean fechaCorrecta(int dia, int mes, int anio) {
        if (dia < 0 || mes < 0 || anio < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean fechaCorrecta = false;
        boolean diaok = false, mesok = false;

        if (mes <= 12) {
            mesok = true;
            switch (mes) {
                case 1, 3, 5, 7, 8, 10, 12:
                    if (dia <= 31) {
                        diaok = true;
                    }
                    break;
                case 4, 6, 9, 11:
                    if (dia <= 30) {
                        diaok = true;
                    }
                    break;
                default:  // febrero
                    if (dia > 29) {
                        diaok = false;
                    } else if (dia == 29) {
                        //ver si es bisiesto
                        if (esBisiesto(anio)) {
                            diaok = true;
                        }
                    }
            }
        }
        if (diaok && mesok) {
            fechaCorrecta = true;
        }
        return fechaCorrecta;
    }

    public static boolean esCapicua(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean esCapicua = false;
        int oremun = 0;
        int pendiente = numero;
        do {
            oremun = oremun + pendiente % 10;
            pendiente = pendiente / 10;
            if (pendiente > 0) {
                oremun = oremun * 10;
            }
        } while (pendiente > 0);
        if (numero == oremun) {
            esCapicua = true;
        }
        return esCapicua;

    }

    public static boolean esPrimo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean esprimo = true;
        if (numero == 0 || numero == 1) {
            esprimo = false;
        }//consideramos que 0 y 1 no son primos
        else {
            for (int i = 2; i < numero && esprimo; i++) {
                if (numero % i == 0) {
                    esprimo = false;
                }
            }
        }
        return esprimo;
    }

    public static boolean esPerfecto(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean esPerfecto = false;
        int suma = 1;
        String demostracion = "número: " + numero + " = 1";
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0) {
                suma = suma + j;
                demostracion = demostracion + " + " + j;
            }
        }
        if (numero == suma) {
            esPerfecto = true;
        }
        return esPerfecto;
    }

    public static boolean compruebaIsbn10(String isbn) {

        Pattern p = Pattern.compile("84[0-9]{8}");
        Matcher m = p.matcher(isbn);
        if (!m.matches()) {
            return false;
        }

        int suma = 0;
        int digito;
        for (int i = 0; i < isbn.length(); i++) {
            digito = Integer.valueOf(isbn.charAt(i) + "") * (i + 1);
            suma += digito;
        }

        return (suma % 11 == 0);
    }

    public static boolean compruebaIsbn13(String isbn) {
        Pattern p = Pattern.compile("97[8|9][0-9]{10}");
        Matcher m = p.matcher(isbn);
        if (!m.matches()) {
            return false;
        }

        int suma = 0;
        int digito;
        for (int i = 0; i < isbn.length() - 1; i++) {
            digito = Integer.valueOf(isbn.charAt(i) + "");
            if (i % 2 != 0) {
                digito = digito * 3;
            }
            suma += digito;
        }

        int control = 10 - (suma % 10);
        digito = Integer.valueOf(isbn.charAt(isbn.length() - 1) + "");

        return (digito == control);
    }

    /**
     * Método que valida que el número de documento tiene un formato válido,
     * para NIF, CIF o NIE. Para ello es necesario que la cadena comience
     * opcionalmente por una letra, seguida de 9 caracteres y que termine
     * también en una letra
     *
     * @param numDoc: recibe la cadena de texto a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarNumDoc(String numDoc) {
        //return numDoc.matches("^[A-Z]?[0-9]{8}[A-Z]$");
        return numDoc.matches("([XY]?)([0-9]{1,9})([A-Za-z])");
    }

    /**
     * Método que valida que la expresión usada como contraseña la contraseña
     * tenga una longitud mínima de 8 caracteres, con al menos una letra
     * mayúscula, una minúscula y dos dígitos
     *
     * @param pass: recibe la cadena de texto a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarPass(String pass) {
        return pass.matches("^(?=.*[a-z]+)(?=.*[A-Z]+)(?=(.*\\d){2,}).{8,}$");
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
    public static void serializaFicheroOutput(ArrayList<Integer> listaPlazas, String fichero) {
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
     * @param listaPlazas: contiene el ArrayList donde se cargarán los datos del
     * fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizará la carga de datos.
     * @return devuelve un ArrayList con la lista de plazas cargadas desde el
     * fichero
     */
    public static ArrayList<Integer> serializaFicheroInput(ArrayList<Integer> listaPlazas, String fichero) {
        try {
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            ArrayList<Integer> plazasRecuperadas = (ArrayList<Integer>) recuperando_fichero.readObject();
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
    
    public static int[] crearArray(int n) { //devuelve un array de n elementos
        int array[] = new int[n];
        return array;
    }

    public static void cargarArrayTeclado(int[] array) { //recibe por parámetro el array que se cargará con los datos solicitados al usuario
        for (int i = 0; i < array.length; i++) {
            array[i] = leerEnteroPositivo();
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

   //pedir por teclado los valores y guardarlos en el ArrayList
   //la lectura acaba cuando se introduzca -99
   public static ArrayList<Integer> leerValores() {
        ArrayList<Integer> valores = new ArrayList();
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Introduce entero. -99 para acabar: ");
        n = sc.nextInt();
        while (n != -99) {
                 valores.add(n);
                 System.out.print("Introduce entero. -99 para acabar: ");
                 n = sc.nextInt();
        }
        return valores;
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
