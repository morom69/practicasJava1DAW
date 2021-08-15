/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Miguel Moro
 */
public class CargaDatosPrueba {

    public static void cargaDatosPrueba(ModeloLibros biblioteca, ArrayList<Prestamo> prestamos) {

        // CÓDIGO AUXILIAR INICIAL PARA PRUEBAS Y CARGA INICIAL
        System.out.println("Cargando datos ... ");
        String autoresPrueba1[] = {"Miguel Delibes", "Antonio Machado", "Rafael Alberti"};
        String autoresPrueba2[] = {"Francisco de Quevedo"};
        String autoresPrueba3[] = {"Rosalia de Castro"};
        String autoresPrueba4[] = {"Tirso de Molina"};
        String autoresPrueba5[] = {"Luis de Gongora", "Laura Gallego"};
        String autoresPrueba6[] = {"Gloria Fuertes"};
        String autoresPrueba7[] = {"Ortega y Gasset"};
        String autoresPrueba8[] = {"Almudena Grandes", "Ana Merino"};
        String autoresPrueba9[] = {"Jacinto Benavente"};
        String autoresPrueba10[] = {"Elvira Lindo"};
        Libro li1 = new Libro("Don Quijote de la Mancha", "9788495761101", autoresPrueba1);
        Libro li2 = new Libro("El Lazarillo de Tormes", "8423988260", autoresPrueba2);
        Libro li3 = new Libro("Cinco Horas con Mario", "8460451429", autoresPrueba3);
        Libro li4 = new Libro("La Regenta", "8460451402", autoresPrueba4);
        Libro li5 = new Libro("El Pirata Garrapata", "9788423988006", autoresPrueba5);
        Libro li6 = new Libro("La Familia de Pascual Duarte", "8423986748", autoresPrueba6);
        Libro li7 = new Libro("La Regenta", "8423986977", autoresPrueba7);
        Libro li8 = new Libro("Diccionario Estrafalario", "9788467497199", autoresPrueba8);
        Libro li9 = new Libro("El Poema de Mio Cid", "8472541002", autoresPrueba9);
        Libro li10 = new Libro("La Celestina", "8472541088", autoresPrueba10);
        biblioteca.añadir(li1);
        biblioteca.añadir(li2);
        biblioteca.añadir(li3);
        biblioteca.añadir(li4);
        biblioteca.añadir(li5);
        biblioteca.añadir(li6);
        biblioteca.añadir(li7);
        biblioteca.añadir(li8);
        biblioteca.añadir(li9);
        biblioteca.añadir(li10);
        String[] isbn1 = new String[]{"9788495761101", };
        String[] isbn2 = new String[]{"8460451429", "8423986748"};
        String[] isbn3 = new String[]{"9788423988006", "8472541088", "8472541002"};
        
        Prestamo prestamo1 = new Prestamo("08969523Y", isbn1, 12, 1, 2021);
        Prestamo prestamo2 = new Prestamo("74385121Q", isbn2, 3, 1, 2021);
        Prestamo prestamo3 = new Prestamo("89083779H", isbn3, 9, 1, 2020);
        prestamos.add(prestamo1);
        prestamos.add(prestamo2);
        prestamos.add(prestamo3);
       
        System.out.println("Se ha realizado la carga de datos de prueba con " + biblioteca.getElementos() + " libros y " + prestamos.size() + " préstamos" );
    }
}
