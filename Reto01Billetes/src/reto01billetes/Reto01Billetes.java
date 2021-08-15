/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto01billetes;

import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class Reto01Billetes {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        int quinientos = 0, doscientos = 0, cien = 0, cincuenta = 0, veinte = 0, diez = 0, cantidad = 0, resto = 0;

        //leer cantidad
        cantidad = leerEntero();
        resto = cantidad;

        //calcular billetes
        quinientos = calcularBilletes(resto,500);
        resto = resto - quinientos * 500;
        
        doscientos = calcularBilletes(resto,200);
        resto = resto - doscientos * 200;

        cien = calcularBilletes(resto,100);
        resto = resto - cien * 100;
        
        cincuenta = calcularBilletes(resto,50);
        resto = resto - cincuenta * 50;

        veinte = calcularBilletes(resto,20);
        resto = resto - veinte * 20;
        
        diez = calcularBilletes(resto,10);
        resto = resto - diez * 10;
        
        //mostrar resultado
        mostrarResultado(cantidad, quinientos,doscientos,cien,cincuenta,veinte,diez, resto);
        
    }

    //función leer cantidad
    public static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Introduce un número para calcular la cantidad de billetes de cada tipo: ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Introduce un entero");
        }
        num = sc.nextInt();
        return num;
    }

    //función calcular billetes
    public static int calcularBilletes(int cantidad, int valor) {
        int numBilletes = 0;
        
        //realizar cálculo;
        numBilletes = cantidad / valor;
        return numBilletes;
    }
    
    //función mostrar resultado
    public static void mostrarResultado(int cantidad, int quinientos, int doscientos, int cien, int cincuenta, int veinte, int diez, int resto) {
        System.out.println("Cantidad = " + cantidad);
        System.out.println("Billetes de 500 = " + quinientos);
        System.out.println("Billetes de 200 = " + doscientos);
        System.out.println("Billetes de 100 = " + cien);
        System.out.println("Billetes de 50 = " + cincuenta);
        System.out.println("Billetes de 20 = " + veinte);
        System.out.println("Billetes de 10 = " + diez);
        System.out.println("Candidad sobrante = " + resto);
    }

}
