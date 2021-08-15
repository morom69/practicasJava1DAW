/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking;

import java.io.Serializable;
import java.text.Collator;
import static parking.Parking.validarTelefono;

/**
 *
 * @author Miguel Moro
 */
public abstract class Plaza implements Comparable, Serializable {
    // Comparable es necesario para poder usar ordenaci�n => debe implementar compareTo()
    // Serializable es necesario para poder serializar ficheros. No requiere ning�n m�todo
    
    private int idPlaza; // n�mero de plaza. El primer d�gito corresponde con la planta
    private boolean ocupada; // true = la plaza est� alquilada o no disponible
    private int cuota; // cuota de alquiler asignada a la plaza
    private String inquilino; // nombre y apellidos de la persona que tiene la plaza alquilada
    protected String inquilinoTelf; // 9 d�gitos
    private boolean inquilinoPagado; // true si la plaza no est� alquilada o si el pago est� al d�a
    private int largo; // largo de la plaza en cms
    private int ancho; // ancho de la plaza en cms

    // constructor
    public Plaza(int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        if (cuota < 0) {
            throw new IllegalArgumentException("Cuota negativa");
        }
        if (!inquilinoTelf.equals("")) {
            if (!validarTelefono(inquilinoTelf)) {
                throw new IllegalArgumentException("Tel�fono incorrecto");
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

    // m�todos getter y setter
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
                throw new IllegalArgumentException("Tel�fono incorrecto");
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
     * Sobreescribimos el m�todo toString() para la clase.
     *
     * @return devuelve un texto con los atributos de la plaza.
     */
    @Override
    public String toString() {
        String resultado = "";
        resultado += ("N�mero de plaza: ") + (this.idPlaza) + ("; ");
        resultado += ("Ocupada: ");
        if (this.ocupada) {
            resultado += ("S�; ");
        } else {
            resultado += ("No; ");
        }
        resultado += ("Cuota: ") + (this.cuota) + (" euros \n");
        if (this.ocupada == true) {
            resultado += ("Inquilino: ") + (this.inquilino) + ("\n");
            resultado += ("Tel�fono: ") + (this.inquilinoTelf) + ("; ");
            resultado += ("Pago al d�a: ") + (this.inquilinoPagado) + ("\n");
        }
        resultado += ("Dimensiones de la plaza: ") + (this.largo) + ("cms de largo X ") + (this.ancho) + ("cms de ancho");
        return resultado;
    }

    /**
     * Comprueba si dos inquilinos se consideran iguales, para lo cual usamos
     * como criterio la comparaci�n de sus nombres y apellidos
     *
     * @param inquilino: nombre y apellidos del inquilino de la plaza que
     * queremos comparar
     * @return devuelve true si el nombre y apellidos facilitado como par�metro
     * es el mismo que el asignado a la plaza instanciada, false en caso
     * contrario.
     */
    public boolean equals(String inquilino) {
        return this.inquilino.equalsIgnoreCase(inquilino);
    }

    /**
     * Para cumplir con los requerimientos de la iterfaz Comparable. Compara si
     * una plaza es mayor, menor o igual que otra pasada por par�metro, teniendo
     * en cuenta el nombre y apellidos del inquilino de cada una de ellas.
     *
     * @param miObjeto: plaza con la que compararemos la plaza actual.
     * @return devuelve lo siguiente: -1 si el nombre y apellidos del inquilino
     * de la plaza actual es anterior alfab�ticamente que el nombre y apellidos
     * del inquilino de la plaza pasada por par�metro, 1 si el inquilino de la
     * plaza actual es posterior alfab�ticamente que el inquilino de la plaza
     * pasada por par�metro, 0 si ambos inquilinos son iguales alfab�ticamente.
     */
    @Override
    public int compareTo(Object miObjeto) {
        Plaza p = (Plaza) miObjeto;
        Collator resultado = Collator.getInstance(); // lo utilizamos para comparar la cadena seg�n est�n ordenados los caracteres en la tabla de caracteres que se usen y poder decir que la misma letra ma�uscula, min�scula y con acento es la misma letra
        resultado.setStrength(Collator.PRIMARY); // Consideramos que es la misma letra si la letra base es la misma. A, a y � as� ser�a la misma letra para nosotros
        return resultado.compare(p.getInquilino(), this.getInquilino());
    }
}
