
import java.util.Scanner;

public class NumerosPrimos {

    public static void main(String[] args) {

        /*Para saber si un número es primo (divisible sólo por el mismo y por uno), 
    lo dividimos sucesivamente por los primeros números primos: 2, 3, 5, 7, 11, .. 
    ¿Cuándo paramos de dividir? 113 no es divisible por 3 (divisor: 3 , cociente: 37' ..)
         */
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Introduce un entero para comprobar si es primo: ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Introduce un entero");
        }
        num = sc.nextInt();
        System.out.print("El número " + num);
        if (esPrimo(num) == true) {
            System.out.println(" es primo");
        } else {
            System.out.println(" no es primo");
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
