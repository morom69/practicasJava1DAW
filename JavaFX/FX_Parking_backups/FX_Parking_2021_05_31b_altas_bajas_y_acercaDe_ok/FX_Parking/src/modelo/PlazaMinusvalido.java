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
public class PlazaMinusvalido extends Plaza {

    private boolean accesoPasillo; // en el caso de la plaza se encuentre situada directamente contigua a un pasillo (acceso más fácil) su valor será true. En caso contrario será false

    // constructor
    public PlazaMinusvalido(boolean accesoPasillo, int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        super(idPlaza, ocupada, cuota, inquilino, inquilinoTelf, inquilinoPagado, largo, ancho);
        this.accesoPasillo = accesoPasillo;
    }

    // métodos getter y setter
    public boolean getAccesoPasillo() {
        return accesoPasillo;
    }

    public void setAccesoPasillo(boolean accesoPasillo) {
        this.accesoPasillo = accesoPasillo;
    }

    /**
     * Sobreescribimos el método toString() para la clase.
     *
     * @return a la ejecución del método por parte de la superclase se le añade
     * aquí otro texto indicando si la plaza es adyacente a un pasillo.
     */
    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += "\nEsta plaza ";
        if (this.accesoPasillo) {
            resultado += ("sí ");
        } else {
            resultado += ("no ");
        }
        resultado += "se encuentra adyacente a un pasillo.";
        resultado += ("\n===================\n");
        return resultado;
    }
}
