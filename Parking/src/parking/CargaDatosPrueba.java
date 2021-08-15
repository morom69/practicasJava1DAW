/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking;

import java.util.ArrayList;

/**
 *
 * @author Miguel Moro
 */
public class CargaDatosPrueba {

    public static void cargaDatosPrueba(ArrayList<Plaza> listaPlaza) {

        // PLAZAS
        // coches
        // (int tipoCoche, boolean ocupada, int idPlaza, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho)
        PlazaCoche coche1 = new PlazaCoche(1, 7, true, 47, "Miguel Sparrow Smith", "666577555", true, 352, 302);
        PlazaCoche coche2 = new PlazaCoche(2, 4, false, 45, "", "", true, 350, 300);
        
        // motos
        // (int numeroMotos, boolean ocupada, int idPlaza, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho)
        PlazaMoto moto1 = new PlazaMoto(2, 9, true, 40, "Marta Connor Bilton", "644337659", false, 250, 200);
        
        // minusválidos
        // (boolean accesoPasillo, boolean ocupada, int idPlaza, int cuota, String inquilino, String inquilinoTelf, boolean inquilinoPagado, int largo, int ancho)
        PlazaMinusvalido minusvalido1 = new PlazaMinusvalido(true, 6, true, 52, "Miguel Rodríguez Smith", "611435333", true, 357, 320);
        
        listaPlaza.add(coche1);
        listaPlaza.add(coche2);
        listaPlaza.add(moto1);
        listaPlaza.add(minusvalido1);
        // FIN INICIALIZACIÓN PARA PRUEBAS
    }
}
