/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploarraylist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author deifont
 */
public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    
    public Libro(String titulo, String autor, String isbn){
        if(!compruebaIsbn10(isbn) && !compruebaIsbn13(isbn)){
            throw new IllegalArgumentException("No se puede crear el objeto. ISBN no válido");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(!compruebaIsbn10(isbn) && !compruebaIsbn13(isbn)){
            throw new IllegalArgumentException("No se puede modificar el ISBN, no es válido");
        }
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + '}';
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
