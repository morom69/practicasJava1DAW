package usofunciones;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MisFunciones {

    
    /**
     * Lee un entero mostrando el tipo de dato que queremos leer:
     *
     * @param texto: al pedir el entero mostrará el texto como el tipo de dato
     * esperado
     * @return devuelve un entero
     */
    public static int leerEntero(String texto) {
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

    public static int leerEnteroPositivo(int limite) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre 1 y " + limite);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num <= 0 || num > limite);
        return num;
    }
    
    /**
     * Lee un entero y valida que se encuentra entre los dos límites pasados por
     * parámetro
     *
     * @param inferior: valor inferior. Se validará que el número introducido no
     * sea menor que este valor
     * @param superior: valor superior. Se validará que el número introducido no
     * sea mayor que este valor esperado
     * @return devuelve un entero entre los dos límites pasados como parámetros
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
     * Lee un carácter. En el caso de que
     *
     * @return devuelve un carácterse introduzca un string se toma solo el
     * primer carácter. Devuelve siempre el carácter en mayúsculas
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
    
    public static String leerString(String texto){
        String cadena;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca " + texto + ": ");
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
     * Pide al usuario una fecha
     *
     * @return devuelve la fecha leída por teclado
     */
    public static Calendar leerFecha() {
        Calendar fecha = Calendar.getInstance();
        int dia, mes, año;
        boolean primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir una fecha válida");
            }
            // leemos día
            dia = leerEntero("día");
            // leemos mes
            mes = leerEntero("mes");
            // leemos año
            año = leerEntero("año");
            primero = false;
        } while (!validarFecha(dia, mes, año));
        // al guardar la fecha, el formato usado por Calendar empieza por cero (enero = 0, diciembre = 11)
        fecha.set(año, mes, dia);
        return fecha;
    }
    
    /**
     * Valida si una fecha es correcta
     *
     * @param dia: día a validar, entre 1 y 31 dependiendo del mes
     * @param mes: mes a validar, comenzando en 1 (enero = 1)
     * @param anio: año a validar, se considera válido cualquier entero
     * @return devuelve true si se trata de una fecha válida, false en caso
     * contrario
     */
    public static boolean validarFecha(int dia, int mes, int anio) {
        if (dia < 0 || mes < 0 || anio < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean fechaCorrecta = false;
        boolean diaok = false, mesok = false;

        if (mes <= 12) {
            mesok = true;
            switch (mes) {
                case 1, 3, 5, 7, 8, 10, 12 -> {
                    if (dia <= 31) {
                        diaok = true;
                    }
                }
                case 4, 6, 9, 11 -> {
                    if (dia <= 30) {
                        diaok = true;
                    }
                }
                default -> {
                    // febrero
                    if (dia > 29) {
                        diaok = false;
                    } else if (dia == 29) {
                        //ver si es bisiesto
                        if (esBisiesto(anio)) {
                            diaok = true;
                        }
                    } else {
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
    
    /**
     * Valida si el año facilitado es bisiesto
     * Un año es bisiesto si es divisible por 4, excepto el último de cada
     * siglo, aquellos divisibles por 100, que para ser bisiestos, también deben
     * ser divisibles por 400
     * @param año: año a validar si es bisiesto. Damos por válido cualquier número entero
     * @return devuelve true si el año facilitado es bisiesto
     */
    public static boolean esBisiesto(int año) {
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

    /**
     * Valida si una fecha es correcta
     * @param dia: día a validar, entre 1 y 31 dependiendo del mes
     * @param mes: mes a validar, comenzando en 1 (enero = 1)
     * @param anio: año a validar, se considera válido cualquier entero
     * @return devuelve true si se trata de una fecha válida, false en caso contrario
     */
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

    public static boolean compruebaIsbn10(String isbn){
        
        Pattern p = Pattern.compile("84[0-9]{8}");
        Matcher m = p.matcher(isbn);
        if(!m.matches()){
            return false;
        }
        
        int suma = 0;
        int digito;
        for(int i=0;i<isbn.length();i++){
            digito = Integer.valueOf(isbn.charAt(i)+"")*(i+1);
            suma += digito;
        }
        
        return (suma%11 == 0);
    }
    
    public static boolean compruebaIsbn13(String isbn){
        Pattern p = Pattern.compile("97[8|9][0-9]{10}");
        Matcher m = p.matcher(isbn);
        if(!m.matches()){
            return false;
        }
        
        int suma = 0;
        int digito;
        for(int i=0;i<isbn.length()-1;i++){
            digito = Integer.valueOf(isbn.charAt(i)+"");
            if(i%2!=0){
                digito=digito*3;
            }
            suma += digito;
        }   
        
        int control = 10 - (suma%10);
        digito = Integer.valueOf(isbn.charAt(isbn.length()-1)+"");
        
        return (digito==control);
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
    * Valida el formato de un Dni. 9 dígitos y una letra
    * 
    * @param dni: recibe el dni a validar
    * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
    */
    public static boolean validarDni(String dni) {
        return dni.matches("([0-9]{8})([A-Za-z])");
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
    * Valida si la cadena pasada por parámetro contiene un código postal válido entre 01000 y 52999
    * 
    * @param codigoPostal: recibe el código postal a validar
    * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
    */
    public static boolean validarCodigoPostal(String codigoPostal) {
        // Número entre 01000 y 52999
        return codigoPostal.matches("(^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$)");
    }
    
    /**
    * Valida si la cadena pasada por parámetro contiene un número de teléfono válido
    * debe comenzar por 6, 8 ó 9 y tener 9 dígitos
    * 
    * @param telefono: recibe el teléfono a validar
    * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
    */
    public static boolean validarTelefono(String telefono) {
        // Debe empezar por 6, 8 ó 9 y tener 9 dígitos
        return telefono.matches("(^[689][1-9]{8}$)");
    }
    
    /**
     * Devuelve la diferencia en años entre la fecha facilitada y la fecha
     * actual (edad)
     *
     * @param fechaNacimiento: recibe la fecha de nacimiento con la que se
     * calcula la diferencia con la fecha actual para obtener la diferencia en
     * años. Se valida que sea una fecha válida y que no sea una fecha futura
     * @return devuelve un entero con la diferencia en años entre la fecha
     * facilitada como parámetro y la fecha actual
     */
    private int calculaEdad(Calendar fechaNacimiento) {
        if (!fechaCorrecta(fechaNacimiento.get(Calendar.DATE), fechaNacimiento.get(Calendar.MONTH) + 1, fechaNacimiento.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de nacimiento incorrecta");
        }
        Calendar today = Calendar.getInstance();
        if (fechaNacimiento.after(today)){
            throw new IllegalArgumentException("No se puede nacer en el futuro");
        }

        int diff_year = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int diff_month = today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        //Si está en ese año pero todavía no los ha cumplido
        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1; //no aparecían los dos guiones del postincremento :|
        }
        return diff_year;
    }
    
    public static int factorial(int n){
        if(n<0){
            throw new IllegalArgumentException("No se puede calcular el factorial de un número negativo");
        }
        int factorial = 1;
        for (int i = 2; i <= n; i++){
            factorial = factorial * i;
        }        
        return factorial;
    }
}
