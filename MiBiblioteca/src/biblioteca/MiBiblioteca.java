/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import static biblioteca.Libro.compruebaIsbn10;
import static biblioteca.Libro.compruebaIsbn13;
import static biblioteca.Prestamo.validarNumDoc;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class MiBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ModeloLibros biblioteca = new ModeloLibros(); // contiene los libros de nuestra biblioteca
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>(); // contiene los préstamos activos de libros de nuestra biblioteca
        Scanner lectura = new Scanner(System.in);
        int opcion, posicion; //Guardaremos la opcion del usuario
        Libro libro;
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        String[] listaIsdn = null;
        String isbn, dni;
        Prestamo prestamo;
        boolean encontrado;

        // para facilitar las pruebas damos la opción de cargar datos de prueba
        System.out.println("Desea cargar datos de prueba?");
        System.out.println("Entrar 1 para cargar datos de prueba, cualquier otro entero para saltar la carga: ");
        while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
            lectura.next();
            System.out.println("Debe introducir un valor entero: ");
        }
        opcion = lectura.nextInt();
        lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterio
        if (opcion == 1) {
            CargaDatosPrueba.cargaDatosPrueba(biblioteca, prestamos);
        }
        // fin carga datos de prueba

        do {
            System.out.println();
            System.out.println("APLICACIÓN DE GESTIÓN DE BIBLIOTECA");
            System.out.println("===================================");
            System.out.println();
            System.out.println("1.Añadir Nuevo Libro");
            System.out.println("2.Eliminar Libro");
            System.out.println("3.Buscar Libro ==>");
            System.out.println("4.Prestar Libros");
            System.out.println("5.Devolver Libros");
            System.out.println("6.Prorrogar Préstamo");
            System.out.println("7.Consultar Préstamos ==>");
            System.out.println("8.Salir");
            System.out.println("Introduzca opción: ");

            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 8: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior

            switch (opcion) {
                case 1: // 1.Añadir nuevo libro                    

                    if (biblioteca.getElementos() >= biblioteca.getMaxElementos()) { //no hay espacio para añadir libro
                        System.out.println("La biblioteca está llena, no se pueden añadir más libros");
                    } else { //hay espacio, insertamos el libro
                        if (biblioteca.añadir(leerLibro())) {
                            System.out.println("Libro añadido ok:");
                        } else {
                            System.out.println("Ha habido algún problema, no se pudo añadir el libro");
                        }
                    }
                    break;
                case 2: // 2.Eliminar libro
                    String mensaje = "Libro ";
                    if (biblioteca.getElementos() > 0) { //comprobamos si hay libros antes de intentar eliminar
                        System.out.println("Se va a eliminar el libro");
                        System.out.println("Introduzca un ISBN correcto de 10 o 13 caracteres: ");
                        isbn = lectura.nextLine();
                        if (!Libro.compruebaIsbn10(isbn) && !Libro.compruebaIsbn13(isbn)) {
                            System.out.println("ISBN incorrecto");
                        } else { //buscamos el libro y si existe lo eliminamos
                            if (!biblioteca.eliminar(isbn)) {
                                mensaje += "NO ";
                            }
                            System.out.println(mensaje + " eliminado");
                        }
                    } else {
                        System.out.println("La biblioteca está vacía, no se puede eliminar ningún libro");
                    }
                    break;
                case 3: // 3.buscar libro
                    System.out.println("Buscar libro");
                    buscarLibro(biblioteca, prestamos);
                    break;
                case 4: // 4.prestar libros
                    System.out.println("Prestar libros");

                    //leer lista de libros a prestar
                    int i = 0;
                    do {
                        System.out.println("Introduzca ISBN (0 para terminar): ");
                        isbn = lectura.nextLine();
                        if ((!compruebaIsbn10(isbn) && !compruebaIsbn13(isbn)) || (isbn.equals("0"))) {
                            if (!isbn.equals("0")) {
                                System.out.println("ISBN incorrecto");
                            }
                        } else {
                            if (!isbn.equals("0")) {
                                libro = biblioteca.buscar(isbn);
                                if (libro != null) {
                                    listaLibros.add(libro);
                                    //listaLibros[i] = libro;  ///  ******   listaLibros es null, no deja asignarlo
                                    i++;
                                } else {
                                    System.out.println("El ISBN introducido no existe");
                                }
                            } else if (listaLibros.size() == 0) {
                                System.out.println("Debe introducir al menos un libro");
                            }
                        }
                    } while (!isbn.equals("0") || listaLibros == null);
                    //convertimos listaLibros de ArrayList a array
                    Libro listaLibrosArray[] = new Libro[listaLibros.size()];
                    listaLibrosArray = listaLibros.toArray(listaLibrosArray);

                    //hacemos el préstamo
                    if (hacerPrestamo(biblioteca, listaLibrosArray, prestamos)) {
                        //préstamo ok
                        System.out.println("Préstamo realizado");
                    } else {
                        //préstamo fallido
                        System.out.println("Préstamo fallido");
                    }
                    break;
                case 5: // 5.devolver libros
                    System.out.println("Devolver libros");
                    System.out.println("Introduzca el dni del lector: ");
                    dni = lectura.nextLine();
                    if (!validarNumDoc(dni)) {
                        System.out.println("DNI incorrecto");
                    } else { //buscamos si tiene algún préstamo
                        i = 0;
                        encontrado = false;
                        do {
                            prestamo = prestamos.get(i);
                            if (prestamo.getDni().equalsIgnoreCase(dni)) {
                                //préstamo encontrado
                                if (prestamo.diasCaducaPrestamo() < 0) { //le corresponde sanción
                                    System.out.println("Préstamo caducado por " + (prestamo.diasCaducaPrestamo() * -1) + " días");
                                    System.out.println("Le corresponde una sanción de " + prestamo.getIsbn().length * (prestamo.diasCaducaPrestamo() * -2) + " días sin poder solicitar más libros");
                                }
                                //eliminamos el préstamo
                                prestamos.remove(prestamo);
                                System.out.println("Préstamo devuelto");
                                encontrado = true;
                            } else {
                                i++;
                            }
                        } while (!encontrado && i < prestamos.size());
                        if (!encontrado) {
                            System.out.println("Préstamo no encontrado");
                        }
                    }
                    break;

                case 6: // 6.prorrogar préstamo
                    System.out.println("Prorrogar préstamo");
                    System.out.println("Introduzca el dni del lector: ");
                    dni = lectura.nextLine();
                    if (!validarNumDoc(dni)) {
                        System.out.println("DNI incorrecto");
                    } else { //buscamos si tiene algún préstamo
                        i = 0;
                        encontrado = false;
                        do {
                            prestamo = prestamos.get(i);
                            if (prestamo.getDni().equalsIgnoreCase(dni)) {
                                //préstamo encontrado
                                if (prestamo.diasCaducaPrestamo() < 0) { //no puede prorrogar, préstamo caducado
                                    System.out.println("Préstamo caducado por " + (prestamo.diasCaducaPrestamo() * -1) + " días");
                                    System.out.println("No puede prorrogar");
                                } else { //comprobamos límite y si aplica prorrogamos
                                    if (prestamo.getDiasPrestamo() + 10 <= 60) { //podemos prorrogar 10 días más
                                        prestamo.setDiasPrestamo(prestamo.getDiasPrestamo() + 10);
                                    } else { //al ampliar 10 días pasamos el límite de 60 días, fijamos el tope en 60 días que es el máximo permitido
                                        prestamo.setDiasPrestamo(60);
                                    }
                                }
                                System.out.println("Préstamo prorrogado a un total de " + prestamo.getDiasPrestamo() + " dias");
                                encontrado = true;
                            } else {
                                i++;
                            }
                        } while (!encontrado && i < prestamos.size());
                        if (!encontrado) {
                            System.out.println("Préstamo no encontrado");
                        }
                    }
                    break;
                case 7: // 7.consultar préstamos
                    System.out.println("Consultar préstamos");
                    consultarPrestamos(prestamos);
                    break;
                case 8: // 8.salir
                    break;
            }

        } while (opcion
                != 8);
    }

    public static void buscarLibro(ModeloLibros biblioteca, ArrayList<Prestamo> prestamos) {
        Scanner lectura = new Scanner(System.in);
        int opcion, posicion; //Guardaremos la opcion del usuario
        String autores[] = null;
        Libro[] listaLibros = null;
        String isbn;
        do {
            System.out.println();
            System.out.println("BÚSQUEDA DE LIBROS");
            System.out.println("==================");
            System.out.println();
            System.out.println("1.Buscar libro por título");
            System.out.println("2.Buscar libro por autor");
            System.out.println("3.Buscar libro por ISBN");
            System.out.println("4.Volver");
            System.out.println("Introduzca opción: ");

            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 4: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior

            switch (opcion) {
                case 1: //buscar libro por título
                    System.out.println("Buscar libro por título, introduzca cadena a buscar:");
                    listaLibros = biblioteca.buscarTitulo(lectura.nextLine());
                    break;
                case 2: //buscar libro por autor
                    System.out.println("Buscar libro por autor, introduzca autor a buscar:");
                    listaLibros = biblioteca.buscarAutor(lectura.nextLine());
                    break;
                case 3: //buscar libro por ISBN
                    System.out.println("Buscar libro por ISBN, introduzca ISBN a buscar:");
                    isbn = lectura.nextLine();
                    if (!Libro.compruebaIsbn10(isbn) && !Libro.compruebaIsbn13(isbn)) {
                        System.out.println("ISBN no válido");
                    } else {
                        listaLibros = new Libro[1];
                        listaLibros[0] = biblioteca.buscar(isbn);
                    }
                    break;
                case 4:
                    break;
            }

            if (opcion != 4) {

                if (listaLibros != null) {

                    System.out.println("Libros encontrados: " + listaLibros.toString());

                    do {
                        System.out.println();
                        System.out.println("Qué desea hacer con los libros encontrados?");
                        System.out.println("===========================================");
                        System.out.println();
                        System.out.println("1.Eliminar libros encontrados");
                        System.out.println("2.Prestar libros encontrados");
                        System.out.println("3.Volver");
                        System.out.println("Introduzca opción: ");

                        while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                            lectura.next();
                            System.out.println("Debe introducir un valor numérico entre 1 y 3: ");
                        }
                        opcion = lectura.nextInt();
                        lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
                    } while (opcion < 1 && opcion > 3);

                    for (int i = 0; i < listaLibros.length; i++) {
                        switch (opcion) {
                            case 1:
                                biblioteca.eliminar(listaLibros[i].getIsbn());
                                break;
                            case 2:
                                if (hacerPrestamo(biblioteca, listaLibros, prestamos)) {
                                    //préstamo ok
                                    System.out.println("Préstamo realizado");
                                } else {
                                    //préstamo fallido
                                    System.out.println("Préstamo fallido");
                                }
                                break;
                            case 3:
                                break;
                        }

                    }
                } else {
                    System.out.println("No se encontraron resultados");
                }
            }
        } while (opcion != 4);
    }

    public static void consultarPrestamos(ArrayList<Prestamo> prestamos) {
        Scanner lectura = new Scanner(System.in);
        int opcion;
        int dias = 0; //Guardaremos la opcion del usuario
        Prestamo prestamo = null;

        do {
            System.out.println();
            System.out.println("CONSULTA DE PRÉSTAMOS");
            System.out.println("=====================");
            System.out.println();
            System.out.println("1.Consultar todos los préstamos");
            System.out.println("2.Consultar préstamos caducados");
            System.out.println("3.Consultar préstamos próximos a caducar");
            System.out.println("4.Volver");
            System.out.println("Introduzca opción: ");

            while (!lectura.hasNextInt()) { //validación de que para la opción de menú se introduce un valor numérico
                lectura.next();
                System.out.println("Debe introducir un valor numérico entre 1 y 4: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            if (opcion == 3) {
                System.out.println("Introducir el número de días: ");
                while (!lectura.hasNextInt()) {
                    lectura.next();
                    System.out.println("Debe introducir un valor entero: ");
                }
                dias = lectura.nextInt();
                lectura.skip("\n"); //se utiliza para evitar que la próxima vez salte la línea al interpretar el enter de la lectura anterior
            }
            //mostramos según la opción seleccionada
            //recorremos todos los préstamos
            for (int i = 0; i < prestamos.size(); i++) {
                prestamo = prestamos.get(i);
                if ((opcion == 1) || (opcion == 2 && prestamo.prestamoCaducado()) || (opcion == 3 && prestamo.diasCaducaPrestamo() < dias)) {
                    System.out.println(prestamo.toString());
                }
            }
        } while (opcion != 4);
    }

    public static Libro leerLibro() {// lee la información para crear un nuevo libro y lo crea
        Scanner lectura = new Scanner(System.in);
        String titulo, isbn, autor;
        ArrayList<String> arrayListAutores = new ArrayList();
        int contadorAutores;

        //título
        System.out.println("Introduzca el titulo del libro: ");
        titulo = lectura.nextLine();

        //isbn
        do {
            System.out.println("Introduzca un ISBN correcto de 10 o 13 caracteres: ");
            isbn = lectura.nextLine();
        } while (!Libro.compruebaIsbn10(isbn) && !Libro.compruebaIsbn13(isbn));

        //autores; pedimos que al menos se introduzca un autor 
        do {
            System.out.println("Introduzca autor (0 para terminar): ");
            autor = lectura.nextLine();
            if (!autor.equals("0")) {
                arrayListAutores.add(autor);
            } else if (arrayListAutores.size() == 0) {
                System.out.println("Debe introducir al menos un autor");
            }
        } while (!autor.equals("0") || arrayListAutores.size() == 0);
        String[] autores = arrayListAutores.toArray(new String[arrayListAutores.size()]);

        return new Libro(titulo, isbn, autores);
    }

    public static boolean hacerPrestamo(ModeloLibros biblioteca, Libro[] listaLibros, ArrayList<Prestamo> prestamos) {
        // lee la información para crear un nuevo préstamo y lo crea
        boolean resultado = false;
        Scanner lectura = new Scanner(System.in);
        String dni;
        Calendar diaActual = Calendar.getInstance();
        int año, mes, dia;
        String[] listaLibrosEnPrestamoActual; //lista de códigos ISBN del préstamo que se está evaluando
        Prestamo prestamoActual, nuevoPrestamo;

        //si quedan libros en la lista hacemos el préstamo
        if (listaLibros.length > 0) {
            //dni
            System.out.println("Introduzca el dni del lector: ");
            dni = lectura.nextLine();
            if (!validarNumDoc(dni)) {
                System.out.println("No se puede realizar el préstamo, DNI incorrecto");
            } else {
                // se dan las condiciones para realizar el préstamo
                año = diaActual.get(Calendar.YEAR);
                mes = diaActual.get(Calendar.MONTH);
                mes++; // los meses comienzan en cero, añadimos uno para cuadrarlo con nuestra cuenta "lógica"
                dia = diaActual.get(Calendar.DAY_OF_MONTH);
                nuevoPrestamo = new Prestamo(dni, listaLibros, dia, mes, año);
                prestamos.add(nuevoPrestamo);
                resultado = true;
            }
        } else {
            System.out.println("No hay libros que incluir en el préstamo, no se registra el préstamo");
        }
        return resultado;
    }

}
