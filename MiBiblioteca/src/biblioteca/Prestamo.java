/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Calendar;

/**
 * un préstamo consiste en el dni del usuario y la lista de libros que se lleva
 * prestados (y demás atributos). En este caso, por simplicidad solo guardamos
 * los isbn de los libros que se prestan. En el programa principal, se creará un
 * objeto de este tipo cada vez que se realice un préstamo, que se guarda en el
 * ArrayList de préstamos.
 *
 * @author Miguel Moro
 */
public class Prestamo {

    private String dni; //dni del usuario al que se le presta el libro
    private String[] isbn; //lista de libros prestados al usuario
    private Calendar fechaPrestamo = Calendar.getInstance(); //fecha en que se realiza el préstamo
    private int diasPrestamo; //número de días que se podrán tener prestados los libros  
    public static final int DIAS_PRESTAMO_DEFAULT = 15; //días por defecto que se realiza el préstamo

    public Prestamo(String dni, String[] isbn, int diasPrestamo, int dia, int mes, int año) {
        //constructor que establece los días del préstamo a lo indicado por parámetro así como el resto de atributos
        if (!validarNumDoc(dni)) {
            throw new IllegalArgumentException("DNI incorrecto");
        }
        if (!fechaCorrecta(dia, mes, año)) {
            throw new IllegalArgumentException("Fecha de préstamo incorrecta");
        }
        if (diasPrestamo < 0) {
            throw new IllegalArgumentException("Días de préstamo incorrectos");
        }
        if (isbn == null) {
            throw new IllegalArgumentException("No se incluyen libros que prestar");
        }
        this.dni = dni;
        // para no modificar el contenido original creamos una copia del array que es lo que asignamos
        String[] isbnNew = (String[]) isbn.clone();
        this.isbn = isbnNew;
        this.fechaPrestamo.set(año, mes, dia);
        this.diasPrestamo = diasPrestamo;
    }

    public Prestamo(String dni, Libro[] libros, int diasPrestamo, int dia, int mes, int año) {
        //constructor que establece los días del préstamo a lo indicado por parámetro.
        //la lista de isbn de préstamos se obtiene a través de un array de objetos Libro
        this(dni, librosToIsbn(libros), diasPrestamo, dia, mes, año);
    }

    public Prestamo(String dni, String[] isbn, int dia, int mes, int año) {
        //constructor que establece los días del préstamo al valor por defecto, deberá hacer useo de this()
        this(dni, isbn, DIAS_PRESTAMO_DEFAULT, dia, mes, año);
    }
////
    public Prestamo(String dni, Libro[] libros, int dia, int mes, int año) {
        this(dni, librosToIsbn(libros), DIAS_PRESTAMO_DEFAULT, dia, mes, año);
    }

    public String getDni() {
        return this.dni;
    }

    public String[] getIsbn() {
        // para no modificar el contenido original creamos una copia del array que es lo que devolvemos
        String[] isbn = (String[]) this.isbn.clone();
        return isbn;
    }

    public Calendar getFechaPrestamo() {
        return this.fechaPrestamo;
    }

    public int getDiasPrestamo() {
        return this.diasPrestamo;
    }

    public void setDiasPrestamo(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Días de préstamo incorrectos");
        }
        this.diasPrestamo = dias;
    }

    public boolean prestamoCaducado() {
        //devuelve verdadero si han pasado los días establecidos para el préstamo
        Calendar today = Calendar.getInstance();
        Calendar finPrestamo = Calendar.getInstance();
        finPrestamo = this.fechaPrestamo;
        finPrestamo.add(Calendar.DAY_OF_YEAR, this.diasPrestamo);

        return finPrestamo.before(today);
    }

    public int diasCaducaPrestamo() {
        //Devuelve el número de días que quedan para que caduque el préstamo. 
        //Devolverá un número negativo indicando cuántos días han transcurrido desde que caducó el préstamo.
        Calendar today = Calendar.getInstance();
        Calendar finPrestamo = Calendar.getInstance();
        finPrestamo = this.fechaPrestamo;
        int dias = (int) (finPrestamo.getTimeInMillis() - today.getTimeInMillis()) / 86400000;
        return dias;
    }

    @Override
    public String toString() {
        //Representación del objeto en cadena de caracteres.
        String datos = "DNI: " + this.dni + "; Fecha Préstamo: " + this.fechaPrestamo.get(Calendar.DATE) +"/" + this.fechaPrestamo.get(Calendar.MONTH) + "/" + this.fechaPrestamo.get(Calendar.YEAR) + "; Días Préstamo: " + this.diasPrestamo;
        for (int i = 0; i < this.isbn.length; i++) {
            datos += "; ISBN" + (i + 1) + ": " + this.isbn[i];
        }
        return datos;
    }

    public static String[] librosToIsbn(Libro[] libros) {
        //función que recibe un array de libros y devuelve un array con sus correspondientes isbn
        if (libros == null) {
            throw new IllegalArgumentException("No se incluyen libros");
        }
        String isbn[] = new String[libros.length];
        for (int i = 0; i < libros.length; i++) {
            isbn[i] = libros[i].getIsbn();
        }
        return isbn;
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
        return numDoc.matches("([XY]?)([0-9]{1,9})([A-Za-z])");
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
}
