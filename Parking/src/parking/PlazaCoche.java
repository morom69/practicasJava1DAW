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
public class PlazaCoche extends Plaza {

    private int tipoCoche; // tipo de coche recomendado para utilizar la plaza; 1 = peque�o, 2 = est�ndar y 3 = grande

    // constructor
    public PlazaCoche(int tipoCoche, int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        super(idPlaza, ocupada, cuota, inquilino, inquilinoTelf, inquilinoPagado, largo, ancho);
        if (tipoCoche < 1 || tipoCoche > 3) {
            throw new IllegalArgumentException("El tipo de coche no es correcto");
        }
        this.tipoCoche = tipoCoche;
    }

    // m�todos getter y setter
    public int getTipoCoche() {
        return tipoCoche;
    }

    public void setTipoCoche(int tipoCoche) {
        this.tipoCoche = tipoCoche;
    }

    /**
     * Sobreescribimos el m�todo toString() para la clase.
     *
     * @return a la ejecuci�n del m�todo por parte de la superclase se le a�ade
     * aqu� otro texto indicando para qu� tipo de coche es �ptima la plaza.
     */
    @Override
    public String toString() {
        String resultado = super.toString();
        String tipo = "";
        switch (this.getTipoCoche()){
            case 1 -> tipo = "peque�o";
            case 2 -> tipo = "est�ndar";
            case 3 -> tipo = "grande";
        }
        resultado += ("\nPlaza �ptima para coche de tipo " + tipo);
        resultado += ("\n===================\n");
        return resultado;
    }

}
