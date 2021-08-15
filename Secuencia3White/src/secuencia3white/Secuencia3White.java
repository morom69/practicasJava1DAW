/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * @author miguel
 *
 */
package secuencia3white;

import java.util.Scanner;


public class Secuencia3White {

    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        int num;
        long num3;
        int numaux;
        String numtxt;
        String numtxtaux = "";
        String numero3white;

        for (num = 100; num < 2000; num++) { //evaluar si son números 3white desde el 100 hasta el 2000
            numaux=0;
            //Para calcular el cubo de N utilizaremos la función Math.pow() 
            //que devuelve un valor double. A este valor le haremos un casting para 
            //convertirlo a tipo long. Si se hace con int se perderá precisión.
            num3 = (long) (Math.pow(num, 3));

            numero3white = ", su cubo es " + num3 + "; ";
            //convertimos a tipo cadena
            numtxt = String.valueOf(num3);

            for (int i = numtxt.length() / 3; i > 0; i--) { //hacemos el bucle tantas veces como bloques completos de 3 dígitos tenga el número  
                switch (numtxt.length() % 3) {
                    case 0:
                        numtxtaux = numtxt.substring(i * 3 - 3, i * 3 - 1); //obtenemos como cadena las siguientes tres cifras del número empezando por el final
                        break;
                    case 1:
                        numtxtaux = numtxt.substring(i * 3 - 2, i * 3 + 1); //obtenemos como cadena las siguientes tres cifras del número empezando por el final
                        break;
                    case 2:
                        numtxtaux = numtxt.substring(i * 3 - 1, i * 3 + 2); //obtenemos como cadena las siguientes tres cifras del número empezando por el final
                        break;
                    default:
                        System.out.println("hemos entrado por default, error");
                }
                numero3white = numero3white + numtxtaux; // vamos componiendo el texto para presentar la descomposición del cuadrado en bloques
                numaux = numaux + Integer.parseInt(numtxtaux); // vamos añadiendo el valor de las tres cifras al acumulado para la comprobación final
                if (i == 0) { //si es el último bloque de 3 cifras
                    if (numtxt.length() % 3 == 0) { // no sobra ningún bloque de 1 ó 2 dígitos
                        numero3white = numero3white + " == ";
                    } else { // queda algún bloque adicional de 1 ó 2 dígitos
                        numero3white = numero3white + " ++ ";
                    }
                } else { // si quedan más bloques de 3 cifras
                    numero3white = numero3white + " + ";
                }
            }
            // calculamos el número de cifras que tiene el número al principio que no forman un bloque de 3 cifras
            int j = numtxt.length() % 3;
            if (j > 0) { // si queda algún bloque de 1 ó 2 dígitos
                // finalmente si hay algún bloque menor de 3 cifras al principio
                // obtenemos como cadena las primeras cifras del número que no forman un bloque de 3 cifras
                numtxtaux = numtxt.substring(0, j);
                numero3white = numero3white + numtxtaux + " === ";
                numaux = numaux + Integer.parseInt(numtxtaux); // vamos añadiendo el valor de las tres cifras al acumulado
            } else { //si no queda ningún bloque de 1 ó 2 dígitos
                numero3white = numero3white + " ==== ";
            }
            if (num == numaux) { // comprobamos si se cumple la condición
                System.out.print("El número " + num + " cumple 3White");
                numero3white = numero3white + numaux;
                System.out.println(numero3white);
            }
        }
    }

}
