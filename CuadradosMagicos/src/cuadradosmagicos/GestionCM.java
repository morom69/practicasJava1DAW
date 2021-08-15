/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuadradosmagicos;

import static cuadradosmagicos.CuadradoMagico.comprobar;
import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class GestionCM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CuadradoMagico cm;
        int numero;
        int N;
        System.out.println("CUADRADO MÁGICO DE N x N");
        System.out.println("Introduce el valor de N. N debe ser mayor o igual a 3");
        do {
            N = leerEnteroPositivo();
        } while (N < 3);
        if (N == 3){
            cm = new CuadradoMagico();
        }else{
            cm = new CuadradoMagico(N);
        }
        
        System.out.println("");
        System.out.println("Vamos a introducir los números para el cuadrado mágico de " + N + " x " + N);
        System.out.println("Los números deben ser mayores que cero");
        System.out.println("==============================================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("Posición [" + (i+1) + "],["+ (j+1) + "]: ");
                numero = leerEnteroPositivo();
                while (!cm.ponerNumero(i, j, numero)){
                    System.out.println("Error, el número ya está en el cuadrado, introducir otro: ");
                }
                System.out.println("Construcción del Cuadrado Mágico hasta el momento:");
                System.out.println(cm.toString());
                System.out.println("");
            }
        }
        if (comprobar(cm)){
            System.out.println("El cuadrado mágico es correcto, la constante mágica es " + cm.getConstanteMagica());
        } else {
            System.out.println("El cuadrado mágico no es correcto.");
        }
        
    }
            
    public static int leerEnteroPositivo() {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("El número no es correcto, pruebe de nuevo: ");
            }
            num = teclado.nextInt();
        } while (num < 0);
        return num;
    }


}
