/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymtonificate;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Miguel Moro
 */
public class CargaDatosPrueba {

    public static void cargaDatosPrueba(ArrayList<Persona> listaPersonal, ArrayList<String> listaEspecialidades, ArrayList<String> listaTrabajos) {

        // ESPECIALIDADES
        listaEspecialidades.add("Spinning");
        listaEspecialidades.add("Pilates");
        listaEspecialidades.add("Zumba");
        listaEspecialidades.add("Musculación");
        

        // TRABAJOS
        listaTrabajos.add("Administrativo");
        listaTrabajos.add("Limpieza");
        listaTrabajos.add("Mantenimiento");

        // DATOS AUX
        int dia1 = 3;
        int dia2 = 15;
        int mes1 = 7;
        int mes2 = 9;
        int anio1 = 1990;
        int anio2 = 2020;
        Calendar fechaNac1 = Calendar.getInstance();
        Calendar fechaNac2 = Calendar.getInstance();
        Calendar fechaNac3 = Calendar.getInstance();
        Calendar fechaAlta1 = Calendar.getInstance();
        Calendar fechaAlta2 = Calendar.getInstance();
        Calendar fechaAlta3 = Calendar.getInstance();
        fechaNac1.set(anio1, mes1, dia1);
        fechaNac2.set(anio1, mes2, dia2);
        fechaNac3.set(anio1, mes1, dia2);
        fechaAlta1.set(anio2, mes2, dia2);
        fechaAlta2.set(anio2, mes1, dia2);
        fechaAlta3.set(anio2, mes2, dia1);
        String[] listaEspecialidades1 = new String[]{"Spinning", "Pilates", "Zumba"};
        String[] listaEspecialidades2 = new String[]{"Musculación", "Pilates"};

        // PERSONAS
        //socios
        Socio socio1 = new Socio(2, true, "", "Miguel Moro", "89067897Y", "13 Rue del Percebe", "Armilla", "Granada", "18100", "606555555", fechaAlta1, fechaNac1, 'H');
        Socio socio2 = new Socio(5, false, "Clavícula derecha", "Luisa Martín", "74385121Q", "Mi Carro me lo Robaron", "Navalagamella", "Madrid", "28340", "858555335", fechaAlta2, fechaNac2, 'M');
        listaPersonal.add(socio1);
        listaPersonal.add(socio2);
        
        //monitores
        Monitor monitor1 = new Monitor(listaEspecialidades1, 1900, true, "Laura González", "08969523Y", "Camino de los Vinateros", "Los Barrios", "Cádiz", "11111", "923522555", fechaAlta3, fechaNac3, 'M');
        Monitor monitor2 = new Monitor(listaEspecialidades2, 1600, false, "John Smith", "06538503V", "Pozo del Tío Raimundo", "Vallecas Pueblo", "Madrid", "28123", "914550055", fechaAlta1, fechaNac1, 'H');
        listaPersonal.add(monitor1);
        listaPersonal.add(monitor2);
        
        //empleados
        Empleado empleado1 = new Empleado("Mantenimiento", 1750, "432", "Bud Spencer", "89083779H", "Camiño do Pazo", "Ribeiro", "Ourense", "44444", "666577555", fechaAlta2, fechaNac2, 'H');
        Empleado empleado2 = new Empleado("Administrativo", 1950, "111", "Carla Romero", "27416097M", "Sesamo Street", "Mataró", "Barcelona", "34555", "934555500", fechaAlta3, fechaNac3, 'M');
        listaPersonal.add(empleado1);
        listaPersonal.add(empleado2);

        // FIN INICIALIZACIÓN PARA PRUEBAS
    }

}
