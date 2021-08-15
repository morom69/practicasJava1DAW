/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

/**
 *
 * @author miguel
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int i = 1; i < 100; i++){
            if(esPrimo(i) && esPrimo(2*i+1)){
                System.out.print(i + "-");
            }
        }
    }

    public static boolean esPrimo(int numero) {
        boolean esprimo = true;
        if (numero == 0 || numero == 1) {
            esprimo = false;
        }//consideramos que 0 y 1 no son primos
        else {
            for (int i = 2; i < numero && esprimo; i++) {
                if (numero % i == 0) {
                    esprimo = false;
                }
            }
        }
        return esprimo;
    }
}
