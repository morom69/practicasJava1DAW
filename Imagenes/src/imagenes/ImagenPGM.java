/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Miguel Moro
 */
public class ImagenPGM extends Imagen {

    private int[][] img; // ancho x alto, rango de valores entre 0 y 255

    public ImagenPGM(int ancho, int alto) {
        super(ancho, alto);
        this.img = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                this.img[i][j] = 0;
            }
        }
    }

    public void randomImg() {
        // asigna valores random a cada uno de los puntos de la imagen
        for (int i = 0; i < this.getAncho(); i++) {
            for (int j = 0; j < this.getAlto(); j++) {
                this.img[i][j] = (int) (Math.random() * (257) + 0);
            }
        }
    }

    public int[] histograma() {
        int histograma[] = new int[255];
        for (int i = 0; i < this.getAncho(); i++) {
            for (int j = 0; j < this.getAlto(); j++) {
                histograma[this.img[i][j]]++;
            }
        }
        return histograma;
    }

    public ImagenPGM reflejar() {
        ImagenPGM imagen = new ImagenPGM(this.getAncho(), this.getAlto());
        for (int i = 0; i < this.getAncho(); i++) {
            for (int j = 0; j < this.getAlto(); j++) {
                imagen.img[i][j] = this.img[(this.getAncho() - i)][(this.getAlto() - j)];
            }
        }
        return imagen;
    }

    @Override
    public String toString() {
        String resultado = "";
        resultado += ("Ancho: ") + (this.getAncho()) + ("; ");
        resultado += ("Alto: ") + (this.getAlto()) + ("; \n");
        for (int i = 0; i < this.getAncho(); i++) {
            for (int j = 0; j < this.getAlto(); j++) {
                resultado += this.img[i][j];
                resultado += " ";
            }
            resultado += "\n";
        }
        return resultado;
    }

    public static ImagenPGM cargar(String nombreArchivo) {
        // lee un archivo en formato PGM-P2 y devuelve un objeto ImagenPGM
        //ImagenPGM imagen = new ImagenPGM(1,1);

        ImagenPGM imagen = null;
        //File fichero = new File(nombreArchivo);
        //if (fichero.exists() && fichero.isFile()) {

            FileReader fr = null;
            BufferedReader br = null;

            try {
                //fr = new FileReader(fichero);
                fr = new FileReader(nombreArchivo);
                br = new BufferedReader(fr);
                String tipoFichero; // para guardar si el fichero es de tipo P2 o P5
                String anchoAlto; // para guardar la línea que contiene el ancho y el alto
                String[] partes; // para dividir la línea que contiene ancho y alto en dos valores diferentes
                int ancho, alto, profundidad;
                String linea;
                while ((linea = br.readLine()) != null) {
                    tipoFichero = linea;
                    anchoAlto = br.readLine();
                    partes = anchoAlto.split(" ");
                    ancho = Integer.parseInt(partes[0]);
                    alto = Integer.parseInt(partes[1]);
                    imagen = new ImagenPGM(ancho, alto);
                    profundidad = Integer.parseInt(br.readLine());
                    for (int i = 0; i < ancho; i++) {
                        linea = br.readLine();
                        partes = linea.split(" ");
                        for (int j = 0; j < alto; j++) {
                            imagen.img[i][j] = Integer.parseInt(partes[j]);
                        }
                    }
                    return imagen;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // En el finally cerramos el nombreFichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta 
                // una excepcion.
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }/*
        } else {
            System.out.println("nombre de fichero no válido: " + nombreArchivo);
        }*/
        return imagen;
    }

    public static void guardar(ImagenPGM img, String nombreArchivo) {
        // salva un objeto ImagenPGM en un archivo con formato PGM-P2
        System.out.println("Método vacío, no se ha guardado el archivo");
    }

    public static void convertirPBM(ImagenPGM img, int umbral, String nombreArchivo) {
        // convierte un objeto ImagenPGM en formato PBM-P1 y lo guarda en fichero
        File fichero = new File(nombreArchivo);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));

            String tipoFichero = "P1";
            String ancho = img.getAncho() + "";
            String alto = img.getAlto() + "";
            int valor;
            String linea = "";
            //Escribimos los datos 
            pw.println(tipoFichero);
            pw.println(ancho + " " + alto);
            for (int i = 0; i < img.getAncho(); i++) {
                for (int j = 0; j < img.getAlto(); j++) {
                    valor = img.img[i][j]; 
                    if (valor >= umbral){
                        linea += "0";
                    } else {
                        linea += "1";
                    }
                }
                pw.println(linea);
            }
            //cerramos el fichero
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
