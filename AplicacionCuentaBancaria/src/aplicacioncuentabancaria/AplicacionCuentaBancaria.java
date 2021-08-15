/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

import static aplicacioncuentabancaria.CuentaBancaria.validarEnteroLongitud;
import java.util.Scanner;

/**
 * Esta clase inicializa una cuenta bancaria e incorpora un menú con opciones
 * para gestionarla
 *
 * @author Miguel Moro
 * @version 1.0, Created on November 28, 2020
 */
public class AplicacionCuentaBancaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lectura = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        double cantidad; //Cantidad a ingresar o retirar de la cuenta
        boolean ingreso; //define si hacemos un ingreso o una retirada
        String titular, entidad, oficina, DC, numCuenta;
        System.out.println("Vamos a proceder a crear su cuenta");
        //leer titular
        do {
            System.out.println("Introduzca el titular (entre 10 y 100 caracteres): ");
            while (!lectura.hasNext()) { //se valida que haya una entrada
                System.out.println("Debe introducir el titular entre 10 y 100 caracteres");
                lectura.nextLine();
            }
            titular = lectura.nextLine();
        } while (titular.length() < 10 || titular.length() > 100);

        //leer entidad
        do {
            System.out.println("Introduzca código de entidad (4 dígitos): ");
            entidad = lectura.nextLine();
        } while (entidad.length() != 4 || !validarEnteroLongitud(entidad, 4));

        //leer oficina
        do {
            System.out.println("Introduzca código de oficina (4 dígitos): ");
            oficina = lectura.nextLine();
        } while (oficina.length() != 4 || !validarEnteroLongitud(oficina, 4));

        //leer cuenta
        do {
            System.out.println("Introduzca código de cuenta bancaria (10 dígitos): ");
            numCuenta = lectura.nextLine();
        } while (numCuenta.length() != 10 || !validarEnteroLongitud(numCuenta, 10));

        //leer DC
        do {
            System.out.println("Introduzca dígitos de control válidos (2 dígitos): ");
            DC = lectura.nextLine();
        } while (DC.length() != 2 || !CuentaBancaria.comprobarCCC(entidad + oficina + DC + numCuenta));

        // inicializamos cuenta llamando al constructora
        CuentaBancaria cuenta = new CuentaBancaria(titular, entidad, oficina, DC, numCuenta);

        System.out.println("Su cuenta está lista para ser utilizada");
        System.out.println("=======================================");
        System.out.println();

        do {
            System.out.println();
            System.out.println("GESTIONA TU CUENTA");
            System.out.println("******************");
            System.out.println("1.Ver el número de cuenta completo (CCC – Código Cuenta Cliente)");
            System.out.println("2.Ver el titular de la cuenta");
            System.out.println("3.Ver el código de la entidad");
            System.out.println("4.Ver el código de la oficina");
            System.out.println("5.Ver el número de cuenta - 10 dígitos");
            System.out.println("6.Ver los dígitos de control de la cuenta");
            System.out.println("7.Realizar un ingreso solicitando por teclado la cantidad");
            System.out.println("8.Retirar efectivo solicitando por teclado la cantidad");
            System.out.println("9.Consultar saldo");
            System.out.println("10.Salir de la aplicación");
            System.out.println("Escribe una de las opciones: ");
            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 10: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            switch (opcion) {
                case 1: //1.Ver el número de cuenta completo (CCC – Código Cuenta Cliente)
                    System.out.println("Número de cuenta: " + cuenta.getEntidad() + "-" + cuenta.getOficina() + "-" + CuentaBancaria.obtenerDigitosControl(cuenta.getEntidad(), cuenta.getOficina(), cuenta.getNumCuenta()) + "-" + cuenta.getNumCuenta());
                    break;
                case 2: //2.Ver el titular de la cuenta
                    System.out.println("Titular de la cuenta: " + cuenta.getTitular());
                    break;
                case 3: //3.Ver el código de la entidad
                    System.out.println("Código de la entidad: " + cuenta.getEntidad());
                    break;
                case 4: //4.Ver el código de la oficina
                    System.out.println("Código de la oficina: " + cuenta.getOficina());
                    break;
                case 5: //5.Ver el número de cuenta - 10 dígitos
                    System.out.println("Número de cuenta (10 dígitos): " + cuenta.getNumCuenta());
                    break;
                case 6: //6.Ver los dígitos de control de la cuenta
                    System.out.println("Dígitos de control: " + cuenta.obtenerDigitosControl(cuenta.getEntidad(), cuenta.getOficina(), cuenta.getNumCuenta()));
                    break;
                case 7: //7.Realizar un ingreso solicitando por teclado la cantidad
                    ingreso = true;
                    operacionCantidad(ingreso, cuenta);
                    break;
                case 8: //8.Retirar efectivo solicitando por teclado la cantidad
                    ingreso = false;
                    operacionCantidad(ingreso, cuenta);
                    break;
                case 9: //9.Consultar saldo
                    System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());
                    break;
                case 10: // Salir
                    break;
                default:
                    System.out.println("El número debe ser entre 1 y 10: ");
            }
        } while (opcion != 10);

    }

    /**
     * Este método gestiona tanto los ingresos como las retiradas de cantidades
     * a realizar
     *
     * @param ingreso: variable booleana donde si se recibe un 1 significará un
     * ingreso y si se recibe un 0 significará una retirada
     * @param CuentaBancaria: cuenta bancaria en la que se realizará el ingreso
     * o retirada de la cantidad indicada
     */
    public static void operacionCantidad(boolean ingreso, CuentaBancaria cuenta) {
        double cantidad;
        Scanner lectura = new Scanner(System.in);
        if (ingreso == true) {
            System.out.println("Introduzca cantidad a ingresar: ");
        } else {
            System.out.println("Introduzca cantidad a retirar: ");
        }
        while (!lectura.hasNextDouble()) { //validación de que introducimos un valor double para la cantidad
            lectura.next();
            System.out.println("Debe introducir un valor double: ");
        }
        cantidad = lectura.nextDouble();
        if (ingreso == true) {
            cuenta.ingresar(cantidad);
        } else {

            cuenta.retirar(cantidad);
        }
        System.out.println("El saldo actualizado es de " + cuenta.getSaldo() + " euros");
    }
}
