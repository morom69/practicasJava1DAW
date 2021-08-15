/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetokenizer;

import java.io.*;

/**
 *
 * @author migue
 */
public class FileTokenizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    // lee letra a letra de un fichero y las va escribiendo por pantalla
    public void lee_fichero() {
        try {
            FileReader entrada = new FileReader("miFichero.txt");
            int c = entrada.read();
            while (c != -1) {
                char letra = (char)c;
                System.out.println(letra);
                c = entrada.read();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
    }
    
    // de un texto va cogiendo carácter a carácter y escribiéndolo en fichero
    public void escribe_fichero() {
        String frase = "Prueba de escritura en fichero 2";
        try {
            FileWriter escritura = new FileWriter("miFichero.txt", true);

            for (int i = 0; i < frase.length(); i++) {
                escritura.write(frase.charAt(i));
            }
            escritura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void LeeEstandar() {
            // Cadena donde iremos almacenando los caracteres que se escriban
            StringBuilder str = new StringBuilder();
            char c;
            // Por si ocurre una excepci?n ponemos el bloque try-cath
            try {
                // Mientras la entrada de terclado no sea Intro
                while ((c = (char) System.in.read()) != '\n') {
                    // A?adir el character le?do a la cadena str
                    str.append(c);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            // Escribir la cadena que se ha ido tecleando
            System.out.println("Cadena introducida: " + str);
        }
    
    public void leerYEscribirFicheroSecuencial() {
            // Declarar un objeto de tipo archivo
            DataOutputStream archivo = null;
            DataInputStream fich = null;
            String nombre = null;
            int edad = 0;
            try {
                // Creando o abriendo para añadir el archivo
                archivo = new DataOutputStream(new FileOutputStream("secuencial.dat", true));

                // Escribir el nombre y los apellidos
                archivo.writeUTF("Antonio López Pérez     ");
                archivo.writeInt(33);
                archivo.writeUTF("Pedro Piqueras Peñaranda");
                archivo.writeInt(45);
                archivo.writeUTF("José Antonio Ruiz Pérez ");
                archivo.writeInt(51);
                // Cerrar fichero
                archivo.close();

                // Abrir para leer
                fich = new DataInputStream(new FileInputStream("secuencial.dat"));
                nombre = fich.readUTF();
                System.out.println(nombre);
                edad = fich.readInt();
                System.out.println(edad);
                nombre = fich.readUTF();
                System.out.println(nombre);
                edad = fich.readInt();
                System.out.println(edad);
                nombre = fich.readUTF();
                System.out.println(nombre);
                edad = fich.readInt();
                System.out.println(edad);
                fich.close();

            } catch (FileNotFoundException fnfe) {
                /* Archivo no encontrado */ } catch (IOException ioe) {
                /* Error al escribir */ } catch (Exception e) {
                /* Error de otro tipo*/
                System.out.println(e.getMessage());
            }
        }
    
    public class Token {

        public void contarPalabrasyNumeros(String nombreFichero) {

            StreamTokenizer sTokenizer = null;
            int contadorPalabras = 0, numeroDeNumeros = 0;

            try {

                sTokenizer = new StreamTokenizer(new FileReader(nombreFichero));

                while (sTokenizer.nextToken() != StreamTokenizer.TT_EOF) {

                    if (sTokenizer.ttype == StreamTokenizer.TT_WORD) {
                        contadorPalabras++;
                    } else if (sTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                        numeroDeNumeros++;
                    }
                }

                System.out.println("Número de palabras en el fichero: " + contadorPalabras);
                System.out.println("Número de números en el fichero: " + numeroDeNumeros);

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        

        /**
         * Copia el contenido de un fichero en otro - ejemplo 1 de 2
         */
        void copiaFicheroBytes(InputStream fentrada, OutputStream fsalida) {

            try {
                int n = 0;

                byte[] buffer = new byte[256];
                while ((n = fentrada.read(buffer)) >= 0) {
                    fsalida.write(buffer, 0, n);
                }

                fentrada.close();
                fsalida.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        /**
         * Copia el contenido de un fichero en otro - ejemplo 2 de 2
         */
        public void copiaFicheroByte() {
        // COPIA FICHERO BYTE A BYTE
        File fichero_entrada = new File("fichero_entrada.txt");
        if (!fichero_entrada.exists()) {
            System.out.println("No existe el fichero " + fichero_entrada);
        } else {
            File fichero_salida = new File("fichero_salida.txt");
            int lecturaByte = 0;
            try {
                FileInputStream flujo_entrada = new FileInputStream(fichero_entrada);
                FileOutputStream flujo_salida = new FileOutputStream(fichero_salida);
                while ((lecturaByte = flujo_entrada.read()) >= 0) { // leemos un byte de la entrada
                    flujo_salida.write(lecturaByte); // escribimos un byte en la salida
                }
                flujo_entrada.close();
                flujo_salida.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // FIN COPIA FICHERO BYTE A BYTE
        }
    }

        /**
         * En este ejemplo de código, se ve cómo podemos escribir la salida
         * estándar a un fichero. Cuando se teclee la palabra "salir", se dejará
         * de leer y entonces se saldrá del bucle de lectura.
         *
         * Podemos ver cómo se usa InputStreamReader que es un puente de flujos
         * de bytes a flujos de caracteres: lee bytes y los decodifica a
         * caracteres. BufferedReader lee texto de un flujo de entrada de
         * caracteres, permitiendo efectuar una lectura eficiente de caracteres,
         * vectores y líneas.
         */
        void copiaFicheroCaracteres() {
            try {
                PrintWriter out = null;
                out = new PrintWriter(new FileWriter("salida.txt", true));

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(System.in));
                String s;
                while (!(s = br.readLine()).equals("salir")) {
                    out.println(s);
                }
                out.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        /**
         * para un fichero existente, que en este caso es texto.txt, averiguamos
         * la codificación que posee, usando el método getEncoding()
         */
        public void main(String[] args) {
            FileInputStream fichero;
            try {
                // Elegimos fichero para leer flujos de bytes "crudos"
                fichero = new FileInputStream("datos.txt");
                // InputStreamReader sirve de puente de flujos de byte a caracteres
                InputStreamReader unReader = new InputStreamReader(fichero);
                // Vemos la codificación actual
                System.out.println(unReader.getEncoding());
            } catch (FileNotFoundException ex) {
                //la siguiente linea daba error, por eso está comentada
                //System.err.println(Codificacion.class.getName());
            }
        }

        /**
         * Leer fichero binario con buffer
         */
        public void leerFicheroBinarioBuffer() {
            int tama;

            try {
                // Creamos un nuevo objeto File, que es la ruta hasta el fichero desde
                File f = new File("test.bin");

                // Construimos un flujo de tipo FileInputStream (un flujo de entrada desde
                // fichero) sobre el objeto File. Estamos conectando nuestra aplicación
                // a un extremo del flujo, por donde van a salir los datos, y "pidiendo"
                // al Sistema Operativo que conecte el otro extremo al fichero que indica
                // la ruta establecida por el objeto File f que habíamos creado antes. De
                FileInputStream flujoEntrada = new FileInputStream(f);
                BufferedInputStream fEntradaConBuffer = new BufferedInputStream(flujoEntrada);

                // Escribimos el tamaño del fichero en bytes.
                tama = fEntradaConBuffer.available();
                System.out.println("Bytes disponibles: " + tama);

                // Indicamos que vamos a intentar leer 50 bytes del fichero.
                System.out.println("Leyendo 50 bytes....");

                // Creamos un array de 50 bytes para llenarlo con los 50 bytes
                // que leamos del flujo (realmente del fichero)*/
                byte bytearray[] = new byte[50];

                // El método read() de la clase FileInputStream recibe como parámetro un
                // array de byte, y lo llena leyendo bytes desde el flujo.
                // Devuelve un número entero, que es el número de bytes que realmente se
                // han leído desde el flujo. Si el fichero tiene menos de 50 bytes, no
                // podrá leer los 50 bytes, y escribirá un mensaje indicándolo.
                if (fEntradaConBuffer.read(bytearray) != 50) {
                    System.out.println("No se pudieron leer 50 bytes");
                }

                // Usamos un constructor adecuado de la clase String, que crea un nuevo
                // String a partir de los bytes leídos desde el flujo, que se almacenaron
                // en el array bytearray, y escribimos ese String.
                System.out.println(new String(bytearray, 0, 50));

                // Finalmente cerramos el flujo.Es importante cerrar los flujos
                // para liberar ese recurso. Al cerrar el flujo, se comprueba que no
                // haya quedado ningún dato en el flujo sin que se haya leído por la aplicación. */
                fEntradaConBuffer.close();

                // Capturamos la excepción de Entrada/Salida. El error que puede
                // producirse en este caso es que el fichero no esté accesible, y
                // es el mensaje que enviamos en tal caso.
            } catch (IOException e) {
                System.err.println("No se encuentra el fichero");
            }
        }
    }

}
