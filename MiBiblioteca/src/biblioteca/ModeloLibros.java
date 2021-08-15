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
public class ModeloLibros {

    private Libro[] lista; //array con los libros que contiene el modelo
    private int elementos; //número de elementos que se han guardado el array
    public static final int MAX_ELEMENTOS = 100; //número de elementos máximos que pueden almacenarse

    public ModeloLibros(int elementos) { // constructor que inicializa el array de elementos con la capacidad indicada por parámetro
        if (elementos < 0) {
            throw new IllegalArgumentException("No se puede tener un número negativo de libros");
        }
        lista = new Libro[elementos];
    }

    public ModeloLibros() { //constructor que inicializa el array de elementos con la capacidad por defecto
        this(MAX_ELEMENTOS);
    }

    public int getElementos() { //devuelve el número de libros que hay almacenado
        //debemos encontrar el primer elemento vacío para saber cuántos libros tenemos almacenados
        //boolean vacio = false;
        int i = 0;
        boolean esElFinal = false;
        do {
            if (this.lista[i] == null) {
                esElFinal = true;
            }
            i++;
        } while (i < this.lista.length && !esElFinal);
        return i - 1;
    }

    public boolean añadir(Libro l) { // añade un libro a la lista indiceArrayLibrosADevolver devuelve true si se añadio correctamente
        // devuelve false si no pudo añadirse por no haber más espacio
        boolean añadido = false;
        if (this.getElementos() < this.getMaxElementos()) { //se puede añadir
            this.lista[this.getElementos()] = new Libro(l.getTitulo(), l.getIsbn(), l.getAutor());;
            añadido = true;
        }
        return añadido;
    }

    public boolean eliminar(String isbn) { // se elimina el libro de la lista
        //tras la eliminación debe compactar los elementos
        if (!Libro.compruebaIsbn10(isbn) && !Libro.compruebaIsbn13(isbn)) {
            throw new IllegalArgumentException("ISBN incorrecto");
        }
        boolean eliminado = false;
        int elementos = this.getElementos();
        if (getElementos() > 0) {
            int i = 0;
            do {
                if (this.lista[i].getIsbn().equalsIgnoreCase(isbn)) {
                    this.lista[i] = null;
                    eliminado = true;
                    for (int j = i; j < elementos - 1; j++) { // compactamos el array
                        //el último elemento lo eliminamos aparte para que en caso de estar completa la lista
                        //el contador no desborde el límite del array
                        this.lista[j] = this.lista[j + 1];
                    }
                    //eliminamos el último elemento para finalizar la compactación
                    this.lista[elementos - 1] = null;
                }
                i++;
            } while (!eliminado && i < this.getElementos());
        }
        return eliminado;
    }

    public Libro buscar(String isbn) { //devuelve el objeto libro que coincide con el isbn
        //si la búsqueda no tiene éxito devuelve null
        if (!Libro.compruebaIsbn10(isbn) && !Libro.compruebaIsbn13(isbn)) {
            throw new IllegalArgumentException("ISBN incorrecto");
        }
        Libro encontrado = null;
        if (this.getElementos() > 0) { //si hay elementos buscamos el libro
            int i = 0;
            do {
                if (this.lista[i].getIsbn().equalsIgnoreCase(isbn)) {
                    //en este caso no devolvemos una copia, ya que asumimos 
                    //que una vez encontrado el libro se querrá hacer con él
                    //alguna operación
                    encontrado = this.lista[i];
                }
                i++;
            } while (encontrado == null && i < this.getElementos());
        }
        return encontrado;
    }

    public Libro[] buscarTitulo(String titulo) { //devuelve un array de libros cuyo título contiene la cadena de 
        //búsqueda indicada por parámetro. Si la búsqueda no tiene éxito devuelve null
        if (titulo.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("El título a buscar debe tener información");
        }
        //usamos un ArrayList para añadir las coincidencias por ser más cómodo su manejo que un array 
        ArrayList<Libro> resultadoAutores = new ArrayList<Libro>();
        if (this.getElementos() > 0) { //si hay elementos buscamos libros
            //recorremos toda la biblioteca buscando coincidencias
            for (int i = 0; i < this.getElementos(); i++) {
                if (this.lista[i].getTitulo().toUpperCase().contains(titulo.toUpperCase())) {
                    resultadoAutores.add(this.lista[i]);
                }
            }
        }
        //copiamos el contenido del ArrayList al array de libros
        Libro[] libros = new Libro[resultadoAutores.size()];
        libros = resultadoAutores.toArray(libros);
        return libros;
    }

    public Libro[] buscarAutor(String autor) { //devuelve un array de libros cuyo autor coincide con el parámetro buscado.
        //si la búsqueda no tiene éxito devuelve null   
          
        //crear mejor resultado como arraylist
        ArrayList<Libro> resultado = new ArrayList<Libro>(); // para añadir los resultados de los libros para los autores que coinciden
        String[] autoresLibroActual = null; //para almacenar los autores del libro que estamos revisando en cada iteración
        Libro[] resultadoArray = null; //aquí devolveremos el resultado de la función
        //bucle para recorrer biblioteca
        for (int i = 0; i < this.getElementos(); i++) {
            //para cada libro recuperar lista de autores en un array y recorrer lista de autores
            autoresLibroActual = new String[this.lista[i].getAutor().length];
            autoresLibroActual = this.lista[i].getAutor();
            //revisar la lista de autores
            for (int j = 0; j < autoresLibroActual.length; j++ ){
                if (autoresLibroActual[j].equalsIgnoreCase(autor)){ //si autor coincide
                    resultado.add(this.lista[i]); //añadir libro a resultado
                }
            }
        }

        
        if (resultado.size() > 0){ //si hay coincidencias
            //convertir resultado de arraylist a array
            resultadoArray = new Libro[resultado.size()];
            resultadoArray = resultado.toArray(resultadoArray);
        }
        
        return resultadoArray;
    }

    public int getMaxElementos() {//devuelve la capacidad máxima de libros que se pueden almacenar
        return this.lista.length;
    }

    public void recorrerMostrar() { //devuelve el número de libros que hay almacenado
        //debemos encontrar el primer elemento vacío para saber cuántos libros tenemos almacenados
        boolean vacio = false;
        int i = 0;
        do {
            if (this.lista[i] == null) {
                vacio = true;
                i++;
                System.out.println("" + this.lista.toString());
            }
        } while (i < this.getElementos() && !vacio);
    }

}
