/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 *
 * @author migue
 */
public class Empleado {

    public Empleado(String nom, String dpto, int sueldo) {
        this.nombre = nom;
        this.dpto = "Ventas";
        this.sueldo = 1000;
        this.id = idSiguiente;
        idSiguiente++;
    }

    public Empleado(String nom) {
        this(nom, "Ventas", 2500);
    }

    public Empleado(String nom, int sueldo) {
        this.nombre = nom;
        this.dpto = "Ventas";
        this.sueldo = sueldo;
        this.id = idSiguiente;
        idSiguiente++;
    }

    public double dameSueldo() {
        return this.sueldo;
    }

    public static final double PI = 3.1416; // constante
    private final String nombre; //si no queremos que se modifique después de darlo de alta
    private String dpto;
    private double sueldo;
    private int id;  // identificador que se va incrementando secuencialmente
    private static int idSiguiente = 1; // valor inicial, luego se va actualizando

    public static String dameIdSiguiente() {
        return "El IdSiguiente es: " + idSiguiente;
    }
}

// si una clase es final detiene la cadena de la herencia 
// no se podría heredar de ella:
// final class Director extends Jefatura{}

// final sobre un método no permite que una subclase lo use

// interfaces solo contienen métodos abstractos (public abstract) y constantes
// class Jefatura extends Empleado implements Int1, Int2

// API clase Arrays ==> método sort ==> necesita implementar interface (uso sería Arrays.sort(Object[] a))
        // all elements in array must implement the Comparable interface
            // la interface pide implementar el método compareTo(T o) - T es el tipo del objeto que se incluye en la comparación (Gym=>Persona)

// if (monitor1 instanceOf Monitor)
// se puede hacer Persona nombrePersona = new Monitor(parametros_constructor);
// y también Comparable ejemplo = new Empleado(constructor) - Comparable = interfaz implementada en la clase Empleado
    // y podemos usar: 
        if (ejemplo instanceOf Comparable){System.out.println("implementa la interfaz comparable");
// la interfaz se declara en un fichero aparte (como una clase) como public interface NombreInterfaz {}
// los métodos de una interface deben ser public abstract nombreMetodo(<parametros>); sin contenido
// y pueden tener constantes para sus métodos: public static final double BONUS_BASE = 1500;
// las interfaces pueden tener jerarquía de herencia: public interface Jefes extends Trabajadores {} // Jefes y Trabajadores son interfaces ambas
 

// metodo abstracto - public abstract <tipo> nombreMetodo(); // sin contenido - la clase debe ser abstracta - el método debe ser sobreescrito por todas las clases hijas como public <tipo> nombreMetodo();
public class Jefe extends Empleado{

    public Jefe(String nom, String dpto, int sueldo, double incentivo);
    super(nom, dpto, sueldo);
     
    this.incentivo  = incentivo;

    public double dameSueldo() {
        double sueldoJefe = super.dameSueldo();
        return sueldoJefe + incentivo;
    }
}

public class Tests {

    public static void main(String[] args) {

        Empleados trabajador1 = new Empleados("Paco");
        System.out.println(Empleados.dameIdSiguiente());

        String password = "";
        Scanner lectura = new Scanner(System.in);
        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();

        System.out.println("fecha de fin:");
        fecha2 = leerFecha();

        do {
            System.out.print("Fecha fin: ");
            mostrarFecha(fecha2);
            System.out.println("fecha a obtener edad: ");
            fecha1 = leerFecha();
            System.out.println(getEdad(fecha1));
        } while (!fecha1.equals(fecha2));

    }

    public static void mostrarFecha(Calendar fecha) {
        System.out.println("Fecha fin: " + fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR));
    }

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
        } while (!validarFecha(dia, mes + 1, año));
        fecha.set(año, mes, dia);
        return fecha;
    }

    public static int getEdad(Calendar fecha) {
        Calendar today = Calendar.getInstance();
        int diff_year = today.get(Calendar.YEAR) - fecha.get(Calendar.YEAR);
        int diff_month = today.get(Calendar.MONTH) - fecha.get(Calendar.MONTH);
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - fecha.get(Calendar.DAY_OF_MONTH);

        //Si está en ese año pero todavía no los ha cumplido
        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1; //no aparecían los dos guiones del postincremento :|
        }
        return diff_year;
    }

    public static int getEdad2(Calendar fecha) {
        Calendar today = Calendar.getInstance();
        int dias = (int) (fecha.getTimeInMillis() - today.getTimeInMillis()) / 86400000;
        int years = dias / 365;
        return years;
    }

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
        if (año < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean bisiesto = false;
        if ((año % 4 == 0) && ((año % 100 != 0) || (año % 400 == 0))) {
            bisiesto = true;
        }
        return bisiesto;
    }

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

    
    
    // BORRAR DESDE AQUÍ *****************************************************************************************************
    // ***********************************************************************************************************************
    //enum Talla {MINI, MEDIANO, GRANDE, MUY_GRANDE};
    public enum Talla{
        MINI("S"), MEDIANO("M"), GRANDE("L"), MUY_GRANDE("XL");
        private Talla(String abreviatura){
            this.abreviatura = abreviatura;
        }
        
        private String abreviatura;
        
        public String dameAbreviatura(){
            return abreviatura;
        }
    }
    
    Talla s = Talla.GRANDE;
    Talla m = Talla.MEDIANO;
    Talla l = Talla.GRANDE;
    Talla xl = Talla.MUY_GRANDE;
    // BORRAR HASTA AQUÍ *****************************************************************************************************
    // ***********************************************************************************************************************
    
    
    // BORRAR DESDE AQUÍ *****************************************************************************************************
        // ***********************************************************************************************************************
        //USO ENUMERADOS 
        /*
        Scanner entrada = new Scanner(System.in);
        System.out.println("Escribe una talla: MINI, MEDIANO, GRANDE, MUY_GRANDE");
        String entrada_datos = entrada.next().toUpperCase();
        Talla la_talla = Enum.valueOf(Talla.class, entrada_datos);
        System.out.println("");
        System.out.println("Talla = " + la_talla);
        System.out.println("Abreviatura = " + la_talla.dameAbreviatura());
         */
        // BORRAR HASTA AQUÍ *****************************************************************************************************
        // ***********************************************************************************************************************
    
    
    
    
}
