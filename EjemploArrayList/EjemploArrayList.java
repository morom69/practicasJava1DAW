/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploarraylist;

import java.util.ArrayList;

/**
 *
 * @author deifont
 */
public class EjemploArrayList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Libro> lista = new ArrayList<Libro>();
        Libro aux;
        
        Libro libro = new Libro("El señor de los anillos","J.R.R. Tolkien","9788447356027");
        lista.add(libro);
        lista.add(libro);
        
        lista.remove(0);
        
        
        /*for(Libro l:lista){
            System.out.println("Libro: "+l);
        }*/
        
        for(int i=0;i<lista.size();i++){
            aux=lista.get(i);
            System.out.println("Libro: "+aux);
        }
    }
    
}
