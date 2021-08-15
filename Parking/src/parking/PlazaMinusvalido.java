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
public class PlazaMinusvalido extends Plaza {

    private boolean accesoPasillo; // en el caso de la plaza se encuentre situada directamente contigua a un pasillo (acceso m�s f�cil) su valor ser� true. En caso contrario ser� false

    // constructor
    public PlazaMinusvalido(boolean accesoPasillo, int idPlaza, boolean ocupada, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho) {
        super(idPlaza, ocupada, cuota, inquilino, inquilinoTelf, inquilinoPagado, largo, ancho);
        this.accesoPasillo = accesoPasillo;
    }

    // m�todos getter y setter
    public boolean getAccesoPasillo() {
        return accesoPasillo;
    }

    public void setAccesoPasillo(boolean accesoPasillo) {
        this.accesoPasillo = accesoPasillo;
    }

    /**
     * Sobreescribimos el m�todo toString() para la clase.
     *
     * @return a la ejecuci�n del m�todo por parte de la superclase se le a�ade
     * aqu� otro texto indicando si la plaza es adyacente a un pasillo.
     */
    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += "\nEsta plaza ";
        if (this.accesoPasillo) {
            resultado += ("s� ");
        } else {
            resultado += ("no ");
        }
        resultado += "se encuentra adyacente a un pasillo.";
        resultado += ("\n===================\n");
        return resultado;
    }
}
