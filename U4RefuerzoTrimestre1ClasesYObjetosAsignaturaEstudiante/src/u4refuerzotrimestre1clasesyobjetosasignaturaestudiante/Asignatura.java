/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4refuerzotrimestre1clasesyobjetosasignaturaestudiante;

/**
 *
 * @author migue
 */
public class Asignatura {

    private String nombre;
    private int horas;

    Asignatura(String nombre, int horas) throws IllegalArgumentException {
        if (horas < 0) {
            throw new IllegalArgumentException("Horas negativas inválido");
        }
        this.nombre = nombre;
        this.horas = horas;
    }

    Asignatura(Asignatura a) {
        this(a.getNombre(), a.getHoras());
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getHoras() {
        return this.horas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Asignatura = " + this.getNombre() + "; Número de horas = " + this.getHoras();
    }

    public boolean equals(Asignatura a) throws NullPointerException {
        if (a == null) {
            throw new NullPointerException("Asignatura inválida");
        }
        return (this.getNombre().equalsIgnoreCase(a.getNombre()) && this.getHoras() == a.getHoras());
    }
}
