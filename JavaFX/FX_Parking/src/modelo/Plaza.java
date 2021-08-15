/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Miguel Moro
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

public abstract class Plaza implements Comparable, Serializable {

    // Comparable es necesario para poder usar ordenación
    // Serializable es necesario para poder serializar ficheros
    private int idPlaza; // número de plaza. El primer dígito corresponde con la planta
    private boolean ocupada; // true = la plaza está alquilada o no disponible
    private int cuota; // cuota de alquiler asignada a la plaza
    private String inquilino; // nombre y apellidos de la persona que tiene la plaza alquilada
    protected String inquilinoTelf; // 9 dígitos
    private boolean inquilinoPagado; // true si la plaza no está alquilada o si el pago está al día
    private int largo; // largo de la plaza en cms
    private int ancho; // ancho de la plaza en cms

    // constructor
    public Plaza(int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        if (cuota < 0) {
            throw new IllegalArgumentException("Cuota negativa");
        }
        if (!inquilinoTelf.equals("")) {
            if (!validarTelefono(inquilinoTelf)) {
                throw new IllegalArgumentException("Teléfono incorrecto");
            }
        }
        this.idPlaza = idPlaza;
        this.ocupada = ocupada;
        this.cuota = cuota;
        this.inquilino = inquilino;
        this.inquilinoTelf = inquilinoTelf;
        this.inquilinoPagado = inquilinoPagado;
        this.largo = largo;
        this.ancho = ancho;
    }

    // métodos getter y setter
    public int getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(int idPlaza) {
        this.idPlaza = idPlaza;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public String getInquilino() {
        return inquilino;
    }

    public void setInquilino(String inquilino) {
        this.inquilino = inquilino;
    }

    public void setInquilinoTelf(String inquilinoTelf) {
        if (!inquilinoTelf.equals("")) {
            if (!validarTelefono(inquilinoTelf)) {
                throw new IllegalArgumentException("Teléfono incorrecto");
            }
        }
        this.inquilinoTelf = inquilinoTelf;
    }

    public String getInquilinoTelf() {
        return this.inquilinoTelf;
    }

    public boolean isInquilinoPagado() {
        return inquilinoPagado;
    }

    public void setInquilinoPagado(boolean inquilinoPagado) {
        this.inquilinoPagado = inquilinoPagado;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * Sobreescribimos el método toString() para la clase.
     *
     * @return devuelve un texto con los atributos de la plaza.
     */
    @Override
    public String toString() {
        String resultado = "";
        resultado += ("Número de plaza: ") + (this.idPlaza) + ("; ");
        resultado += ("Ocupada: ");
        if (this.ocupada) {
            resultado += ("Sí; ");
        } else {
            resultado += ("No; ");
        }
        resultado += ("Cuota: ") + (this.cuota) + (" euros \n");
        if (this.ocupada == true) {
            resultado += ("Inquilino: ") + (this.inquilino) + ("\n");
            resultado += ("Teléfono: ") + (this.inquilinoTelf) + ("; ");
            resultado += ("Pago al día: ") + (this.inquilinoPagado) + ("\n");
        }
        resultado += ("Dimensiones de la plaza: ") + (this.largo) + ("cms de largo X ") + (this.ancho) + ("cms de ancho");
        return resultado;
    }

    /**
     * Comprueba si dos inquilinos se consideran iguales, para lo cual usamos
     * como criterio la comparación de sus nombres y apellidos
     *
     * @param inquilino: nombre y apellidos del inquilino de la plaza que
     * queremos comparar
     * @return devuelve true si el nombre y apellidos facilitado como parámetro
     * es el mismo que el asignado a la plaza instanciada, false en caso
     * contrario.
     */
    public boolean equals(String inquilino) {
        return this.inquilino.equalsIgnoreCase(inquilino);
    }

    /**
     * Para cumplir con los requerimientos de la iterfaz Comparable. Compara si
     * una plaza es mayor, menor o igual que otra pasada por parámetro, teniendo
     * en cuenta el nombre y apellidos del inquilino de cada una de ellas.
     *
     * @param miObjeto: plaza con la que compararemos la plaza actual.
     * @return devuelve lo siguiente: -1 si el nombre y apellidos del inquilino
     * de la plaza actual es anterior alfabéticamente que el nombre y apellidos
     * del inquilino de la plaza pasada por parámetro, 1 si el inquilino de la
     * plaza actual es posterior alfabéticamente que el inquilino de la plaza
     * pasada por parámetro, 0 si ambos inquilinos son iguales alfabéticamente.
     */
    @Override
    public int compareTo(Object miObjeto) {
        Plaza p = (Plaza) miObjeto;
        Collator resultado = Collator.getInstance(); // lo utilizamos para comparar la cadena según estén ordenados los caracteres en la tabla de caracteres que se usen y poder decir que la misma letra maýuscula, minúscula y con acento es la misma letra
        resultado.setStrength(Collator.PRIMARY); // Consideramos que es la misma letra si la letra base es la misma. A, a y á así sería la misma letra para nosotros
        return resultado.compare(p.getInquilino(), this.getInquilino());
    }

    /**
     * Devuelve una copia del arrayList de elementos de clase Plaza facilitado
     * como parámetro.
     *
     * @param listaPlaza: arrayList de elementos de tipo Plaza.
     * @return devuelve una copia del arrayList de elementos de clase Plaza
     * facilitado como parámetro.
     */
    public static ArrayList<Plaza> copiaArrayListPlaza(ArrayList<Plaza> listaPlaza) {
        ArrayList<Plaza> copiaListaPlaza = new ArrayList<>();
        if (listaPlaza.size() > 0) {
            for (int i = 0; i < listaPlaza.size(); i++) {
                copiaListaPlaza.add(listaPlaza.get(i));
            }
        }
        return copiaListaPlaza;
    }

    /**
     * Devuelve el objeto de clase Plaza incluido en el arrayList facilitado
     * como parámetro al facilitar también como parámetro el número de ésta.
     *
     * @param listaPlaza: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de la numPlaza de la que vamoa obtener su índice
     * en el arrayList de plazas.
     * @return devuelve el objeto de clase Plaza correspondiente al número de
     * numPlaza facilitado. Si la numPlaza no existe devuelve null.
     */
    public static Plaza recuperarPlaza(ArrayList<Plaza> listaPlaza, int idPlaza) {
        Plaza p = null;
        int indice = -1, i = 0;
        boolean encontrado = false;
        if (listaPlaza.size() > 0) { // si hay elementos
            do {
                p = listaPlaza.get(i);
                if (p.getIdPlaza() == idPlaza) { // la numPlaza existe
                    encontrado = true;
                    indice = i;
                } else {
                    i++;
                }
            } while (i < listaPlaza.size() && encontrado == false);
        }
        return p;
    }

    /**
     * Devuelve como boolean si en el arrayList facilitado como parámetro existe
     * la numPlaza cuyo número es también facilitado como parámetro.
     *
     * @param listaPlazas: arrayList que contiene todas las plazas de
     * aparcamiento.
     * @param idPlaza: número de la numPlaza de la que vamoa obtener su índice
     * en el arrayList de plazas.
     * @return devuelve true si la numPlaza con número idPlaza existe en el
     * arrayList. False en caso contrario.
     */
    public static boolean existePlaza(ArrayList<Plaza> listaPlazas, int idPlaza) {
        // para no modificar la lista existente usamos una copia
        ArrayList<Plaza> copiaListaPlaza = new ArrayList<>();
        copiaListaPlaza = copiaArrayListPlaza(listaPlazas);
        Plaza p;
        int i = 0;
        boolean encontrado = false;
        if (listaPlazas.size() > 0) { // si hay elementos
            do {
                p = copiaListaPlaza.get(i);
                if (p.getIdPlaza() == idPlaza) { // la numPlaza existe
                    encontrado = true;
                } else {
                    i++;
                }
            } while (i < copiaListaPlaza.size() && encontrado == false);
        }
        return encontrado;
    }

    /**
     * Valida si la cadena pasada por parámetro contiene un número de teléfono
     * válido debe comenzar por 6, 8 ó 9 y tener 9 dígitos
     *
     * @param telefono: recibe el teléfono a validar.
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario.
     */
    public static boolean validarTelefono(String telefono) {
        // Debe empezar por 6, 8 ó 9 y tener 9 dígitos
        return telefono.matches("(^[689][0-9]{8}$)");
    }

    /**
     * Realiza el paso a fichero mediante serialización de un arrayList.
     *
     * @param listaPlaza: contiene el arrayList que se guardará en fichero.
     * @param fichero: contiene la ruta y nombre del fichero donde se guardará
     * el arrayList.
     */
    public static void serializaFicheroOutput(ArrayList<Plaza> listaPlazas, String fichero) {
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(fichero));
            escribiendo_fichero.writeObject(listaPlazas);
            escribiendo_fichero.close();
        } catch (IOException ioe) {
            System.out.println("Error de escritura en fichero");
        }
    }

    /**
     * Realiza la carga en un ArrayList del contenido de un fichero mediante
     * serialización.
     *
     * @param listaPlaza: contiene el ArrayList donde se cargarán los datos del
     * fichero.
     * @param fichero: contiene la ruta y nombre del fichero desde donde se
     * realizará la carga de datos.
     * @return devuelve un ArrayList con la lista de plazas cargadas desde el
     * fichero
     */
    public static ArrayList<Plaza> serializaFicheroInput(ArrayList<Plaza> listaPlazas, String fichero) {
        try {
            //ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(fichero));
            ArrayList<Plaza> plazasRecuperadas = (ArrayList<Plaza>) recuperando_fichero.readObject();
            listaPlazas = plazasRecuperadas;
            recuperando_fichero.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe ");
        } catch (IOException ioe) {
            System.out.println("Error: falló la lectura del fichero ");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: clase no encontrada ");
        }
        return listaPlazas;
    }

    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
