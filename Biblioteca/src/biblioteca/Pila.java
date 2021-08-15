/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

/**
 *
 * @author deifont
 */
public class Pila {
    private Libro[] pila;
    private int cabecera;
    
    public Pila(){
        pila = new Libro[100];
        cabecera = 0;
    }
    
    public boolean push(Libro l){
        //comprobamos si la pila está llena
        if(this.cabecera == pila.length){
            return false;
        }
        //almacenamos en libro en la posición libre, apuntada por cabecera
        this.pila[cabecera] = l;
        cabecera++;
        return true;
    }
    
    public Libro pop(){
        //comprobamos si la pila está vacía
        if(this.isEmpty()){
            return null;
        }
        //saco el libro de la posición anterior a cabecera
        cabecera--;
        Libro l = this.pila[cabecera];
        this.pila[cabecera]=null;
        return l;
    }
    
    public boolean isEmpty(){
        
        return (cabecera==0);
    }
    
}
