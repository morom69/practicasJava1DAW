
import java.util.Scanner;

public class EjercicioMedidas {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        // Variables de entrada
        /* 
        Respecto al ejercicio original se modifican las variables para que tengan sentido
        se cambia la variable original metros por celsius
        se cambian las variables originales:
            decimetros, centimetros, milimetros,
            pulgadas, pies, yardas
        por:
            kelvin y fahrenheit
         */
        double celsius;

        // Variables de salida
        double kelvin;
        double fahrenheit;

        // Variables auxiliares
        // Clase Scanner para petición de datos
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("TRANSFORMACIÓN DE UNIDADES");
        System.out.println("--------------------------");
        System.out.println(" ");
        /* 
        Respecto al ejercicio original se modifica el texto de solicitud de datos de entrada para que tenga sentido
        en lugar de solicitar una longitud en metros se solicita una temperatura en grados Celsius
        También se introduce la comprobación de los datos de entrada para evitar errores.
        */
        do {
            System.out.print("Introduzca la temperatura en grados Celsius que desea transformar: ");
            while (!teclado.hasNextInt()){
                System.out.println("Debe introducir un número. Por favor introduzca uno: ");
                teclado.next();
            }
            celsius = teclado.nextDouble();
        } while (celsius < 0);
        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        /* 
        Respecto al ejercicio original se modifica el procesamiento de los datos segun el enunciado para que tenga sentido
        se procesa la conversion de grados Celsius a grados Kelvin y grados Fahrenheit
         */ // Grados Kelvin
         kelvin = celsius + 273.15;

        // Grados Fahrenheit
        fahrenheit = (celsius * 9 / 5) + 32;

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        /* 
        Respecto al ejercicio original se modifica la salida de datos segun el enunciado para que tenga sentido
        se muestra el resultado de la conversion  de grados Celsius a grados Kelvin y grados Fahrenheit
         */
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println();
        System.out.println("GRADOS CELSIUS A KELVIN");
        System.out.println("---------------------");
        System.out.println("La medida de " + celsius + " grados Celsius en grados Kelvin son:");
        System.out.println(kelvin + " grados Kelvin");

        System.out.println();
        System.out.println("GRADOS CELSIUS A FAHRENHEIT");
        System.out.println("------------------");
        System.out.println("La medida " + celsius + " grados Celsius en grados Fahrenheit son:");
        System.out.println(fahrenheit + " grados Fahrenheit");

        System.out.println();
        System.out.println("Fin del programa. Bye!");
    }
}
