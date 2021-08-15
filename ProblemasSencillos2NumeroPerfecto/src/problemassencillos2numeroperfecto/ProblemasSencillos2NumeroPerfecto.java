/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemassencillos2numeroperfecto;

/**
 *
 * @author miguel Hacer un programa que calcule e imprima los números perfectos
 * menores de 1000. Un número es perfecto si la suma de sus divisores, excepto
 * él mismo, es igual al propio número. Ejemplo: Los divisores de 6 son 6, 3, 2,
 * 1 y la suma de todos su divisores excepto él mismo es 3+2+1 = 6. Luego 6 es
 * un número perfecto.
 */
public class ProblemasSencillos2NumeroPerfecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int suma;
        String demostracion;

        for ( int i = 2; i<=1000; i++) {
            suma = 1;
            demostracion = "número: " + i + " = 1";
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    suma = suma + j;
                    demostracion = demostracion + " + " + j;
                }
            }
            if (i == suma) {
                System.out.println(demostracion);
            }
        }
    }
}
