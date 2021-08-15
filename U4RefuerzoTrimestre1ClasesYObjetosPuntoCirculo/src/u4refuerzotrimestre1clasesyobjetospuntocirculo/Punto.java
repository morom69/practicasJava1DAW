/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4refuerzotrimestre1clasesyobjetospuntocirculo;

/**
 *
 * @author Miguel Moro
 */
public class Punto {

    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Punto() {
        this(0, 0);
    }

    public Punto(Punto p) throws NullPointerException {
        this(p.getX(), p.getY());
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "X = " + this.getX() + "; Y = " + this.getY();
    }

    public boolean equals(Punto p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("Parámetro inválido");
        }
        return (this.getX() == p.getX() && this.getY() == p.getY());
    }
}
