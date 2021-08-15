/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking;

/**
 *
 * @author Miguel Moro
 */
public class PlazaMoto extends Plaza {

    private int numeroMotos; // n�mero de motos que es posible estacionar en esta plaza. Debe ser mayor que cero

    // constructor
    public PlazaMoto(int numeroMotos, int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        super(idPlaza, ocupada, cuota, inquilino, inquilinoTelf, inquilinoPagado, largo, ancho);
        if (numeroMotos < 0) {
            throw new IllegalArgumentException("El n�mero de motos que es posible estacionar debe ser mayor que cero");
        }
        this.numeroMotos = numeroMotos;
    }

    // m�todos getter y setter
    public int getNumeroMotos() {
        return numeroMotos;
    }

    public void setNumeroMotos(int numeroMotos) {
        this.numeroMotos = numeroMotos;
    }

    /**
     * Sobreescribimos el m�todo toString() para la clase.
     *
     * @return a la ejecuci�n del m�todo por parte de la superclase se le a�ade
     * aqu� otro texto indicando hasta qu� n�mero de moticicletas se pueden
     * estacionar en la plaza.
     */
    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += ("\nPlaza habilitada para estacionar hasta " + this.getNumeroMotos() + " motos");
        resultado += ("\n===================\n");
        return resultado;
    }
}
