/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4refuerzotrimestre1clasesyobjetosasignaturaestudiante;

import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class U4RefuerzoTrimestre1ClasesYObjetosAsignaturaEstudiante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("Pruebas clase Asignatura:");
        System.out.println("...");
        Asignatura a1 = new Asignatura("Mates", 5); // constructor por defecto
        Asignatura a2 = new Asignatura("Lengua", 4);
        Asignatura a3 = new Asignatura(a1); // constructor 2

        // verificación constructores y métodos getter:
        assert a1.getNombre().equals("Mates") : "a1.getNombre(), esperado = Mates; actual = " + a1.getNombre();
        assert a1.getHoras() == 5 : "a1.getHoras(), esperado = 5; actual = " + a1.getHoras();
        assert a2.getNombre().equals("Lengua") : "a2.getNombre(), esperado = Lengua; actual = " + a2.getNombre();
        assert a2.getHoras() == 4 : "a2.getHoras(), esperado = 4; actual = " + a2.getHoras();
        assert a3.getNombre().equals("Mates") : "a3.getNombre(), esperado = Mates; actual = " + a3.getNombre();
        assert a3.getHoras() == 5 : "a3.getHoras(), esperado = 5; actual = " + a3.getHoras();

        // métodos setter
        a1.setNombre("Inglés");
        a1.setHoras(3);
        assert a1.getNombre().equals("Inglés") : "a1.getNombre(), esperado = Inglés; actual = " + a1.getNombre();
        assert a1.getHoras() == 3 : "a1.getHoras(), esperado = 3; actual = " + a1.getHoras();

        // métodos toString y equals
        assert a1.toString().equals("Asignatura = Inglés; Número de horas = 3") : "a1.toString() = Asignatura = Inglés; Número de horas = 3; actual = " + a1.toString();

        //fin pruebas clase Asignatura
        System.out.println("pruebas clase Asignatura finalizadas, si no has visto un error la prueba ha sido correcta");

        System.out.println("");

        System.out.println("Pruebas clase Estudiante:");
        System.out.println("...");

        Estudiante e1 = new Estudiante("Miguel", "Moro", "Álvarez"); // constructor 1
        Estudiante e2 = new Estudiante("John", "Smith", "Thompson"); // 
        Estudiante e3 = new Estudiante(e1); // constructor 2

        // verificación constructores y métodos getter:
        assert e1.getNombre().equals("Miguel") : "e1.getNombre() = Miguel; actual = " + e1.getNombre();
        assert e1.getApellido1().equals("Moro") : "e1.getApellido1 = Moro; actual = " + e1.getApellido1();
        assert e1.getApellido2().equals("Álvarez") : "e1.getApellido2 = Álvarez; actual = " + e1.getApellido2();
        assert e1.getListaAsignaturas().isEmpty() : "e1.getListaAsignaturas() = []; actual = " + e1.getListaAsignaturas();
        assert e2.getNombre().equals("John") : "e2.getNombre() = John; actual = " + e2.getNombre();
        assert e2.getApellido1().equals("Smith") : "e2.getApellido1 = Smith; actual = " + e2.getApellido1();
        assert e2.getApellido2().equals("Thompson") : "e2.getApellido2 = Thompson; actual = " + e2.getApellido2();
        assert e2.getListaAsignaturas().isEmpty() : "e2.getListaAsignaturas() = []; actual = " + e2.getListaAsignaturas();
        assert e3.getNombre().equals("Miguel") : "e3.getNombre() = Miguel; actual = " + e3.getNombre();
        assert e3.getApellido1().equals("Moro") : "e3.getApellido1 = Moro; actual = " + e3.getApellido1();
        assert e3.getApellido2().equals("Álvarez") : "e3.getApellido2 = Álvarez; actual = " + e3.getApellido2();
        assert e3.getListaAsignaturas().isEmpty() : "e3.getListaAsignaturas() = []; actual = " + e3.getListaAsignaturas();

        // métodos setter 
        e1.setNombre("Marcus");
        e1.setApellido1("Schmidt");
        e1.setApellido2("Fischer");
        Asignatura as1 = new Asignatura("Mates", 5);
        Asignatura as2 = new Asignatura("Lengua", 6);
        ArrayList<Asignatura> lista = new ArrayList<Asignatura>();
        lista.add(as1);
        lista.add(as2);
        e1.setListaAsignaturas(lista);
        assert e1.getNombre().equals("Marcus") : "e1.getNombre() = Marcus; actual = " + e1.getNombre();
        assert e1.getApellido1().equals("Schmidt") : "e1.getApellido1() = Schmidt; actual = " + e1.getApellido1();
        assert e1.getApellido2().equals("Fischer") : "e1.getApellido2() = Fischer; actual = " + e1.getApellido2();
        ArrayList<Asignatura> lista2 = new ArrayList<Asignatura>();
        lista2 = e1.getListaAsignaturas();
        Asignatura as3 = lista2.get(0);
        Asignatura as4 = lista2.get(1);
        assert as3.getNombre().equals("Mates") : "as3.getNombre() = Mates; actual = " + as3.getNombre();
        assert as3.getHoras() == 5 : "as3.getHoras() = 5; actual = " + as3.getHoras();
        assert as4.getNombre().equals("Lengua") : "as4.getNombre() = Lengua; actual = " + as4.getNombre();
        assert as4.getHoras() == 6 : "as4.getHoras() = 6; actual = " + as4.getHoras();

        // métodos getter adicionales
        //añadeAsignatura()
        Asignatura as5 = new Asignatura("Inglés", 3);
        e1.añadeAsignatura(as5);
        assert as5.getNombre().equals("Inglés") : "as5.getNombre() = Inglés; actual = " + as5.getNombre();
        assert as5.getHoras() == 3 : "as5.getHoras() = 3; actual = " + as5.getHoras();

        //getNumeroAsignaturasMatriculadas()
        assert e1.getNumeroAsignaturasMatriculadas() == 3 : "e1.getNumeroAsignaturasMatriculadas() = 3; actual = " + e1.getNumeroAsignaturasMatriculadas();

        //getNumeroHorasMatriculadas()
        assert e1.getNumeroHorasMatriculadas() == 14 : "e1.getNumeroHorasMatriculadas() = 14; actual = " + e1.getNumeroHorasMatriculadas();

        //getAsignatura()   
        as5 = e1.getAsignatura(0);
        assert as5.getNombre().equals("Mates") : "as5.getNombre() = Mates; actual = " + as5.getNombre();
        assert as5.getHoras() == 5 : "as5.getHoras() = 5; actual = " + as5.getHoras();
      
        // métodos toString y equals
        assert e1.toString().equals("Nombre: Marcus; Apellido1: Schmidt; Apellido2: Fischer;/n"
                + "Asignatura: Mates, horas: 5/nAsignatura: Lengua, horas: 6/nAsignatura: Inglés, horas: 3/n") : "e1.toString() = Nombre: Marcus; Apellido1: Schmidt; Apellido2: Fischer;/n"
                + "Asignatura: Mates, horas: 5/nAsignatura: Lengua, horas: 6/nAsignatura: Inglés, horas: 3/n  ; actual = " + e1.toString();
    
        //fin pruebas clase Estudiante
        System.out.println("pruebas clase Estudiante finalizadas, si no has visto un error la prueba ha sido correcta");
    }

}
