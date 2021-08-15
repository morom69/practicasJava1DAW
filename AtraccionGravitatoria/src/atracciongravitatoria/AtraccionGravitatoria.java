/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atracciongravitatoria;

import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class AtraccionGravitatoria {
    
    
    static final float CONSTANTE = (float) 000000000006.673;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        float objeto1, objeto2, distancia;

        System.out.println("Introduce la masa para el objeto 1:");
        objeto1 = leerFloatPositivo();
        System.out.println("Introduce la masa para el objeto 2:");
        objeto2 = leerFloatPositivo();
        System.out.println("Introduce la distancia entre ambos objetos:");
        distancia = leerFloatPositivo();
        
        System.out.println("La fuerza de atracción (F) entre ambos objetos es " + atraccionGravitatoria(objeto1, objeto2,distancia));
    }
    
    public static float atraccionGravitatoria(float objeto1, float objeto2, float distancia){
        return CONSTANTE * objeto1 * objeto2 / (int) (Math.pow(distancia, 2));
    }
    
    public static float leerFloatPositivo() {
        float num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un número positivo: ");
            while (!teclado.hasNextFloat()) {
                teclado.next();
                System.out.println("Debe ser un número positivo: ");
            }
            num = teclado.nextFloat();
        } while (num <= 0);
        return num;
    }
    
}
