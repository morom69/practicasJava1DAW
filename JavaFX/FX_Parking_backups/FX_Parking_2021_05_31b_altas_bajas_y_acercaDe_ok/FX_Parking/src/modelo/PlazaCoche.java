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
public class PlazaCoche extends Plaza {

    private int tipoCoche; // tipo de coche recomendado para utilizar la plaza; 1 = pequeño, 2 = estándar y 3 = grande

    // constructor
    public PlazaCoche(int tipoCoche, int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        super(idPlaza, ocupada, cuota, inquilino, inquilinoTelf, inquilinoPagado, largo, ancho);
        if (tipoCoche < 1 || tipoCoche > 3) {
            throw new IllegalArgumentException("El tipo de coche no es correcto");
        }
        this.tipoCoche = tipoCoche;
    }

    // métodos getter y setter
    public int getTipoCoche() {
        return tipoCoche;
    }

    public void setTipoCoche(int tipoCoche) {
        this.tipoCoche = tipoCoche;
    }

    /**
     * Sobreescribimos el método toString() para la clase.
     *
     * @return a la ejecución del método por parte de la superclase se le añade
     * aquí otro texto indicando para qué tipo de coche es óptima la plaza.
     */
    @Override
    public String toString() {
        String resultado = super.toString();
        String tipo = "";
        switch (this.getTipoCoche()){
            case 1: {
                tipo = "pequeño";
                break;
            }
            case 2: {
                tipo = "estándar";
                break;
            }
            case 3: {
                tipo = "grande";
                break;
            }
        }
        resultado += ("\nPlaza óptima para coche de tipo " + tipo);
        resultado += ("\n===================\n");
        return resultado;
    }
}
