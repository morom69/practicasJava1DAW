/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

import static imagenes.ImagenPGM.convertirPBM;
import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class GestionImagenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println();
            System.out.println("GESTION DE IMAGENES");
            System.out.println("==================");
            System.out.println("");
            System.out.println("1.Nueva imagen aleatoria");
            System.out.println("2.Cargar imagen desde archivo");
            System.out.println("3.Salir del programa");
            System.out.println("Introduzca opción: ");

            // leemos y validamos la opción seleccionada
            opcion = leerEnteroEntreLimites(1, 3);

            switch (opcion) {
                case 1 -> { // nueva imagen aleatoria
                    int ancho,
                            alto;
                    System.out.println("nueva imagen aleatoria");
                    System.out.println("Introduzca el ancho:");
                    ancho = leerEnteroPositivo();
                    System.out.println("Introduzca el alto:");
                    alto = leerEnteroPositivo();
                    ImagenPGM imagen = new ImagenPGM(alto, ancho);
                    imagen.randomImg();
                    System.out.println("imagen generada:");
                    System.out.println(imagen.toString());
                    break;
                }
                case 2 -> { // cargar imagen desde archivo
                    System.out.println("cargar imagen desde archivo");
                    String nombreArchivo = leerString();
                    ImagenPGM imagenCargar = new ImagenPGM(1, 1);
                    imagenCargar.cargar(nombreArchivo);
                    System.out.println("imagen generada:");
                    System.out.println(imagenCargar.toString());

                    do {
                        System.out.println();
                        System.out.println("OPERAR SOBRE LA IMAGEN");
                        System.out.println("=======================");
                        System.out.println("");
                        System.out.println("1.Reflejar");
                        System.out.println("2.Histograma");
                        System.out.println("3.Convertir a PBM");
                        System.out.println("4.Volver");
                        System.out.println("Introduzca opción: ");

                        // leemos y validamos la opción seleccionada
                        opcion = leerEnteroEntreLimites(1, 3);

                        switch (opcion) {
                            case 1 -> { // reflejar
                                imagenCargar = imagenCargar.reflejar();
                                System.out.println("Imagen reflejada: ");
                                System.out.println(imagenCargar.toString());
                            }

                            case 2 -> { // histograma
                                int histograma[] = imagenCargar.histograma();
                                System.out.println("Histograma de la imagen:");
                                for (int i = 0; i < histograma.length; i++) {
                                    System.out.println("Valor " + i + ": " + histograma[i]);
                                }
                            }

                            case 3 -> { // convertir a PBM
                                System.out.println("Convertimos a formato PBM");
                                System.out.println("Introduzca el nombre del archivo:");
                                String nombreArchivoPBM = leerString();
                                System.out.println("Introduzca el umbral (entre 0 y 255):");
                                int umbral = leerEnteroEntreLimites(0, 255);
                                convertirPBM(imagenCargar, umbral, nombreArchivoPBM);
                            }

                        }

                    } while (opcion != 4);
                    break;
                }

                case 3 -> {
                    System.out.println("Hasta la próxima");
                }
            }
        } while (opcion != 3);
    }

    public static int leerEnteroPositivo() {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo: ");
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Debe ser un entero: ");
            }
            num = teclado.nextInt();
        } while (num <= 0);
        return num;
    }

    public static String leerString() {
        Scanner teclado = new Scanner(System.in);
        String lectura;
        System.out.println("Introduzca nombre:");
        lectura = teclado.nextLine();
        return lectura;
    }

    public static int leerEnteroEntreLimites(int inferior, int superior) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre " + inferior + " y " + superior);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num < inferior || num > superior);
        return num;
    }
}
