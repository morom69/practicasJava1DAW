/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Miguel Moro
 */
public class Libro {

    private String titulo;
    private String[] autores;
    private String isbn;

    public Libro(String titulo, String isbn, String[] autores) {
        if (!compruebaIsbn10(isbn) && !compruebaIsbn13(isbn)) {
            throw new IllegalArgumentException("ISBN incorrecto");
        }
        this.titulo = titulo;
        this.isbn = isbn;
        this.autores = autores;
    }

    
    public Libro(Libro l){
        this(l.getTitulo(), l.getIsbn(), l.getAutor());
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String[] getAutor() {
        // para no modificar el contenido original creamos una copia del array que es lo que devolvemos
        String [] autores = (String []) this.autores.clone();
        return autores;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutores(String[] autores) {
        // para no modificar el contenido original creamos una copia del array que es lo que asignamos
        String [] autoresNew = (String []) autores.clone();
        this.autores = autoresNew;
    }

    public void setIsbn(String isbn) {
        if (!compruebaIsbn10(isbn) && !compruebaIsbn13(isbn)) {
            throw new IllegalArgumentException("ISBN incorrecto");
        }
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        String datos = "Titulo: " + this.titulo + "; ISBN: " + this.isbn + "; Autor(es):";
        for (int i = 0; i < this.autores.length; i++) {
            datos += this.autores[i] + " ";
            if (i > 0){
                datos += "- ";
            }
        }
        return datos;
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
    
}
