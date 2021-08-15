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
public class Estudiante {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private ArrayList<Asignatura> listaAsignaturas;

    public Estudiante(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.listaAsignaturas = new ArrayList<Asignatura>();
    }

    public Estudiante(Estudiante e) {
        this(e.getNombre(), e.getApellido1(), e.getApellido2());
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public ArrayList<Asignatura> getListaAsignaturas() {
        return this.listaAsignaturas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setListaAsignaturas(ArrayList<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public boolean añadeAsignatura(Asignatura a) {
        boolean añadida = false;
        // el alumno no puede estar matriculado en más de 30 horas
        if (this.getNumeroHorasMatriculadas() + a.getHoras() <= 30){
            this.listaAsignaturas.add(a);
            añadida = true;
        }
        return añadida;
    }

    public int getNumeroAsignaturasMatriculadas() {
        return this.listaAsignaturas.size();
    }

    public int getNumeroHorasMatriculadas() {
        int horas = 0;
        Asignatura a;
        for (int i = 0; i < this.listaAsignaturas.size(); i++) {
            a = listaAsignaturas.get(i);
            horas += a.getHoras();
        }
        return horas;
    }

    public Asignatura getAsignatura(int posicion) // devuelve la asignatura que ocupa la posición indicada
            throws NullPointerException {
        if (posicion >= this.listaAsignaturas.size()) {
            throw new NullPointerException("Posición no ocupada");
        }
        return listaAsignaturas.get(posicion);
    }

    @Override
    public String toString() {
        Asignatura a;
        String texto = "Nombre: " + this.getNombre() + "; Apellido1: " + this.getApellido1() + "; Apellido2: " + this.getApellido2() + ";/n";
        for (int i = 0; i < this.listaAsignaturas.size(); i++) {
            a = listaAsignaturas.get(i);
            texto = texto + "Asignatura: " + a.getNombre() + ", horas: " + a.getHoras() + "/n";
        }
        return texto;
    }

    public boolean equals(Estudiante e)throws NullPointerException {
        if (e == null) {
            throw new NullPointerException("Estudiante inválido");
        }
        return (this.getNombre().equalsIgnoreCase(e.getNombre()) && this.getApellido1().equalsIgnoreCase(e.getApellido1())
                && this.getApellido2().equalsIgnoreCase(e.getApellido2()) && this.getListaAsignaturas() == e.getListaAsignaturas());
    }
}
