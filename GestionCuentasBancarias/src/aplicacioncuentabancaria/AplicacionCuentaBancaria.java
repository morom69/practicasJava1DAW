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
        int opcion, posicion; //Guardaremos la opcion del usuario
        double cantidad; //Cantidad a ingresar o retirar de la cuenta
        boolean ingreso; //define si hacemos un ingreso o una retirada
        String numDoc, confirmacion; // número de documento identificativo para buscar cuentas asociadas y confirmación
        // inicializamos el array de cuentas bancarias, inicialmente vacío
        CuentaBancaria cuentas[] = new CuentaBancaria[10];

        do {
            System.out.println();
            System.out.println("APLICACIÓN DE GESTIÓN DE CUENTAS BANCARIAS");
            System.out.println("==========================================");
            System.out.println();
            System.out.println("1.Crear cuenta bancaria");
            System.out.println("2.Eliminar cuenta bancaria");
            System.out.println("3.Gestionar cuenta bancaria");
            System.out.println("4.Consultar depósitos");
            System.out.println("5.Salir del programa");

            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 10: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            switch (opcion) {
                case 1: // 1.Crear cuenta bancaria
                    // verificamos si hay alguna posición libre en el array de cuentas
                    posicion = verificarCuentasLibres(cuentas);
                    // si hay alguna posición libre creamos la cuenta en la última posición libre
                    if (posicion != -1) {
                        cuentas[posicion] = crearCuentaBancaria();
                    } else {
                        System.out.println("No hay cuentas libres, debe eliminar una si desea crear otra nueva");
                    }
                    break;
                case 2: // 2.Eliminar cuenta bancaria
                    do {
                        System.out.println("Introduzca número de documento válido (NIF, CIF o NIE): ");
                        numDoc = lectura.nextLine();
                        numDoc = numDoc.toUpperCase();
                    } while (!CuentaBancaria.validarNumDoc(numDoc));
                    posicion = buscarCuenta(cuentas, numDoc);
                    if (posicion != -1) {
                        System.out.println("Se va a eliminar la cuenta, por favor pulse S o s para confirmar: ");
                        confirmacion = lectura.nextLine();
                        if (confirmacion.equals("S") || confirmacion.equals("s")) {
                            //eliminamos la cuenta si hay confirmación
                            eliminarCuentaBancaria(cuentas, posicion);
                            //reordenamos el array dejando las posiciones vacías si las hay al final del mismo
                            agruparCuentas(cuentas, posicion);
                        } else { //no ha habido confirmación positiva
                            System.out.println("Se ha cancelado la eliminación de la cuenta");
                        }
                    } else { //el número de documento no se encuentra asociado a ninguna cuenta
                        System.out.println("No existe ninguna cuenta asociada a ese documento");
                    }
                    break;
                case 3: // 3.Gestionar cuenta bancaria
                    System.out.println("Introduzca el número de documento asociado a la cuenta que desea gestionar: ");
                    numDoc = lectura.nextLine();
                    numDoc = numDoc.toUpperCase();
                    posicion = buscarCuenta(cuentas, numDoc);
                    if (posicion != -1) {
                        gestionarCuentaBancaria(cuentas[posicion]);//gestionar cuentas
                    } else {
                        System.out.println("No existe ninguna cuenta asociada a ese documento");
                    }
                    break;
                case 4: // 4.Consultar depósitos
                    System.out.println("La cantidad guardada incluyendo todas las cuentas es de " + consultarDepositos(cuentas) + " euros");
                    break;
                case 5: // 5.Salir del programa
                    break;
            }

        } while (opcion
                != 5);
    }

    /**
     * Este método gestiona las diferentes opciones para operar sobre una cuenta
     * bancaria
     *
     * @param cuenta: cuenta bancaria a gestionar
     */
    public static void gestionarCuentaBancaria(CuentaBancaria cuenta) {
        Scanner lectura = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        boolean ingreso; //define si hacemos un ingreso o una retirada
        String pass; // para definir la nueva contraseña
        do {
            System.out.println();
            System.out.println("GESTIONA TU CUENTA");
            System.out.println("******************");
            System.out.println("1.Ver el número de cuenta completo (CCC – Código Cuenta Cliente)");
            System.out.println("2.Ver el titular de la cuenta");
            System.out.println("3.Ver el número de documento de la cuenta");
            System.out.println("4.Modificar la contraseña de la cuenta");
            System.out.println("5.Realizar un ingreso solicitando por teclado la cantidad");
            System.out.println("6.Retirar efectivo solicitando por teclado la cantidad");
            System.out.println("7.Consultar saldo");
            System.out.println("8.Volver al menú principal");
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
                case 3: //3.Ver el número de documento de la cuenta
                    System.out.println("Número de documento de la cuenta: " + cuenta.getNumDoc());
                    break;
                case 4: //4.Modificar la contraseña
                    System.out.println("Va a modificar la contraseña de la cuenta");
                    System.out.println("Para ello debe facilitar la contraseña actual: ");
                    while (!lectura.hasNext()) {
                        lectura.next();
                    }
                    pass = lectura.nextLine();
                    if (pass.equals(cuenta.getPass())) {
                        System.out.println("Contraseña correcta, incluya la nueva contraseña");
                        do {
                            System.out.println("La nueva contraseña deberá tener una longitud mínima de 8 caracteres, con al menos una letra mayúscula, una minúscula y dos dígitos");
                            while (!lectura.hasNext()) {
                                lectura.nextLine();
                            }
                            pass = lectura.nextLine();
                        } while (!CuentaBancaria.validarPass(pass));
                        cuenta.setPass(pass);
                        System.out.println("La contraseña se ha actualizado correctamente");
                    } else {
                        System.out.println("Contraseña incorrecta, no se permite modificar la contraseña");
                    }
                    break;
                case 5: //5.Realizar un ingreso solicitando por teclado la cantidad
                    ingreso = true;
                    operacionCantidad(ingreso, cuenta);
                    break;
                case 6: //6.Retirar efectivo solicitando por teclado la cantidad
                    ingreso = false;
                    operacionCantidad(ingreso, cuenta);
                    break;
                case 7: //7.Consultar saldo
                    System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());
                    break;
                case 8: //8.Volver al menú principal
                    break;
                default:
                    System.out.println("El número debe ser entre 1 y 8: ");
            }
        } while (opcion != 8);
    }

    /**
     * Este método recore nuestro array de cuentas bancarias para evaluar si hay
     * espacios libres. En caso afirmativo nos devuelve la última posición libre
     * del array
     *
     * @param c: array de cuentas bancarias a evaluar si tiene alguna posición
     * libre
     * @return devuelve un código del 0 al 9 con la primera posición libre en el
     * array de cuentas bancarias. Si no hay ninguna posición libre devuelve -1
     */
    public static int verificarCuentasLibres(CuentaBancaria[] c) {
        int codigo = -1;
        //recorremos el array para evaluar si hay espacios libres
        int i = c.length - 1;
        do {
            if (c[i] == null) {
                codigo = i;
            }
            i--;
        } while (i >= 0);
        return codigo;
    }

    /**
     * Este método recore nuestro array de cuentas bancarias para evaluar si
     * existe una cuenta asociada al número de documento (NIF, CIF o NIE)
     * facilitado.
     *
     * @param CuentaBancaria[]: array de cuentas bancarias a evaluar si contiene
     * la cuenta
     * @param numDoc: número de documento (NIF, CIF o NIE) del que hay que
     * evaluar si existe una cuenta asociada a él
     * @return devuelve -1 si no existe alguna cuenta asociada al número de
     * documento, la posición de la cuenta en el array en caso contrario
     */
    public static int buscarCuenta(CuentaBancaria[] c, String numDoc) {
        int posicion = -1;
        int i = -1;
        do {
            i++;
            if ((c[i] != null) && (numDoc.equals(c[i].getNumDoc()))) {
                posicion = i;
            }
        } while (c[i] != null && (posicion == -1 && i < c.length - 1));
        // el bucle termina cuando el siguiente elemento del array está vacío (el array está ordenado)
        // o se ha encontrado un elemento
        // o se termina de recorrer el array
        return posicion;
    }

    /**
     * Este método realiza la eliminación de una cuenta bancaria dada su posción
     * en el array
     *
     * @param CuentaBancaria[]: array de cuentas bancarias de entre los que hay
     * que eliminar la cuenta. A continuación se agrupan las cuentas bancarias
     * compactando el array dejando las posiciones vacías al final
     * @param posicion: posición de la cuenta bancaria a eliminar dentro del
     * array de cuentas bancarias
     *
     */
    public static void eliminarCuentaBancaria(CuentaBancaria[] c, int posicion) {
        //public static void eliminarCuentaBancaria(CuentaBancaria[] c, String numDoc) {
        if (c[posicion] != null) {
            c[posicion] = null;
            System.out.println("Cuenta eliminada");
        } else {
            System.out.println("No existe cuenta a eliminar");
        }
        agruparCuentas(c, posicion);
    }

    /**
     * Este método agrupa un array de cuentas bancarias dejando al final del
     * mismo el elemento vacío pasado como parámetro
     *
     * @param c: array de cuentas bancarias en el que se realizará la
     * reagrupación de cuentas
     * @param posicion: posición que se encuentra vacía y tenemos que mover al
     * final del array
     */
    public static void agruparCuentas(CuentaBancaria[] c, int posicion) {
        int vacio = -1;
        int siguienteOcupado = -1;
        //agrupamos el array moviendo un espacio vacío (pasado como posición) al final del mismo 
        // los anteriores ya lo estarán también
        for (int i = posicion; i <= c.length - 2; i++) {
            if (c[i] == null) {
                vacio = i;
                if (c[i + 1] != null) { // Hay elementos que ordenar, hay que mover el elemento siguiente adelante
                    c[i] = c[i + 1];
                }
            }
        }
    }

    /**
     * Este método gestiona tanto los ingresos como las retiradas de cantidades
     * a realizar
     *
     * @param ingreso: variable booleana donde si se recibe un 1 significará un
     * ingreso y si se recibe un 0 significará una retirada
     * @param cuenta: cuenta bancaria en la que se realizará el ingreso o
     * retirada de la cantidad indicada
     */
    public static void operacionCantidad(boolean ingreso, CuentaBancaria cuenta) {
        double cantidad;
        Scanner lectura = new Scanner(System.in);
        if (ingreso == true) {
            System.out.println("Introduzca cantidad a ingresar: ");
        } else {
            System.out.println("Introduzca cantidad a retirar: ");
        }

        do {
            System.out.println("Debe introducir un valor numérico positivo: ");
            while (!lectura.hasNextDouble()) { //validación de que introducimos un valor double para la cantidad
                lectura.next();
            }
            cantidad = lectura.nextDouble();
        } while (cantidad < 0);

        if (ingreso == true) {
            cuenta.ingresar(cantidad);
        } else {

            cuenta.retirar(cantidad);
        }
        System.out.println("El saldo actualizado es de " + cuenta.getSaldo() + " euros");
    }

    /**
     * Este método realiza la creación de una cuenta bancaria mediante la
     * introducción por teclado, con sus correspondientes validaciones del
     * titular, entidad, oficina y dígitos de control
     *
     */
    public static CuentaBancaria crearCuentaBancaria() {
        Scanner lectura = new Scanner(System.in);
        String titular, numDocumento, entidad, oficina, DC, numCuenta, pass;
        System.out.println("Vamos a proceder a crear su cuenta bancaria");
        //leer titular
        do {
            System.out.println("Introduzca el titular (entre 10 y 100 caracteres): ");
            while (!lectura.hasNext()) { //se valida que haya una entrada
                System.out.println("Debe introducir el titular entre 10 y 100 caracteres: ");
                lectura.nextLine();
            }
            titular = lectura.nextLine();
        } while (!CuentaBancaria.validarTitular(titular));

        //leer número de documento
        do {
            System.out.println("Introduzca número de documento válido (NIF, CIF o NIE): ");
            numDocumento = lectura.nextLine();
            numDocumento = numDocumento.toUpperCase();
        } while (!CuentaBancaria.validarNumDoc(numDocumento));

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

        //leer contraseña
        //debe tener una longitud mínima de 8 caracteres, con al menos una letra mayúscula, una minúscula y dos dígitos
        do {
            System.out.println("Introduzca contraseña válida ");
            System.out.println("longitud mínima de 8 caracteres, con al menos una letra mayúscula, una minúscula y dos dígitos: ");
            pass = lectura.nextLine();
        } while (!CuentaBancaria.validarPass(pass));

        // inicializamos cuenta llamando al constructora
        CuentaBancaria cuenta = new CuentaBancaria(titular, numDocumento, pass, entidad, oficina, DC, numCuenta);

        System.out.println("Su cuenta ha sido creada con el siguiente código:");
        System.out.println(entidad + "-" + oficina + "-" + DC + "-" + numCuenta);
        return cuenta;

    }

    /**
     * Este método realiza la consulta de depósitos
     *
     */
    public static double consultarDepositos(CuentaBancaria[] cuentas) {
        double acumulado = 0;
        if (cuentas[0] != null) {
            int i = 0;
            do {
                acumulado = acumulado + cuentas[i].getSaldo();
                i++;
            } while (i <= cuentas.length - 1 && cuentas[i] != null);
        }
        return acumulado;
    }
}
