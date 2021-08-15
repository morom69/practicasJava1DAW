/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherossecuenciales;

import java.io.*;

/**
 *
 * @author migue
 */
public class FicherosSecuenciales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*File fichero_entrada = new File("fichero_entrada.txt");
        if (!fichero_entrada.exists()) {
            System.out.println("No existe el fichero " + fichero_entrada);
        } else {

            try {
                File fichero_salida = new File("fichero_salida.txt");
                FileInputStream flujo_entrada = new FileInputStream(fichero_entrada);
                FileOutputStream flujo_salida = new FileOutputStream(fichero_salida); */
        //copiaFicheroByte();
        //copiaFicheroByteBuffer(256);
        leeTecladoAFicheroCaracterBuffer();
        /* } catch (Exception e) {
            }

        } */
    }

    

    public static void copiaFicheroByteBuffer(int tamanio_buffer) {
        // COPIA FICHERO BYTE A BYTE CON BUFFER
        File fichero_entrada = new File("fichero_entrada.txt");
        if (!fichero_entrada.exists()) {
            System.out.println("No existe el fichero " + fichero_entrada);
        } else {
            try {
                int lecturaByte = 0;
                File fichero_salida = new File("fichero_salida.txt");
                FileInputStream flujo_entrada = new FileInputStream(fichero_entrada);
                FileOutputStream flujo_salida = new FileOutputStream(fichero_salida);
                byte[] buffer = new byte[tamanio_buffer];
                while ((lecturaByte = flujo_entrada.read(buffer)) >= 0) {
                    flujo_salida.write(buffer, 0, lecturaByte);
                }
                flujo_entrada.close();
                flujo_salida.close();
            } 
            catch (EOFException e) { // FIN DE ARCHIVO
                System.out.println("Final de archivo");
            }
            catch (FileNotFoundException e) { // FICHERO NO ENCONTRADO
                System.out.println("Fichero no encontrado");
            }
            catch (IOException e) { // FIN DE ARCHIVO
                System.out.println("Error de lectura");
            }
            // FIN COPIA FICHERO BYTE A BYTE CON BUFFER
        }
    }

    public static void leeTecladoAFicheroCaracterBuffer() {
        // LEER DE TECLADO A FICHERO COMO CARACTER
        // lee líneas por teclado y las pasa a fichero hasta que se introduce una línea vacía
        File fichero_salida = new File("fichero_salida.txt");
        try {
            FileWriter fw = new FileWriter(fichero_salida);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String texto = " ";
            while (texto.length() > 0) {
                texto = br.readLine();
                fw.write(texto + "\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // FIN LEER DE TECLADO A FICHERO COMO CARACTER
    }
    
    public static void grabaYLeeRegistrosFicheroCaracter(){
        // ESCRIBIR TOKENS EN UN ARCHIVO CARACTER Y LEER Y SACAR POR PANTALLA
        // Declarar un objeto de tipo archivo
        DataOutputStream archivo = null ;
        DataInputStream fich = null ;
        String nombre = null ;
        int edad = 0 ;
        try {
            // Creando o abriendo para añadir el archivo
            archivo = new DataOutputStream( new FileOutputStream("secuencial.dat",true) );

            // Escribir el nombre y los apellidos
            archivo.writeUTF( "Antonio López Pérez     " );
            archivo.writeInt(33) ;
            archivo.writeUTF( "Pedro Piqueras Peñaranda" );
            archivo.writeInt(45) ;
            archivo.writeUTF( "José Antonio Ruiz Pérez " ) ;
            archivo.writeInt(51) ;
            // Cerrar fichero
            archivo.close();

            // Abrir para leer
            fich = new DataInputStream( new FileInputStream("secuencial.dat") );
            nombre = fich.readUTF() ;
            System.out.println(nombre) ;
            edad = fich.readInt() ;
            System.out.println(edad) ;
            nombre = fich.readUTF() ;
            System.out.println(nombre) ;
            edad = fich.readInt() ;
            System.out.println(edad) ;
            nombre = fich.readUTF() ;
            System.out.println(nombre) ;
            edad = fich.readInt() ;
            System.out.println(edad) ;
            fich.close();

      } catch(FileNotFoundException fnfe) { /* Archivo no encontrado */ }
        catch (IOException ioe) { /* Error al escribir */ }
        catch (Exception e) { /* Error de otro tipo*/
          System.out.println(e.getMessage());}
        // FIN ESCRIBIR TOKENS EN UN ARCHIVO CARACTER Y LEER Y SACAR POR PANTALLA
    }
    
    

}
