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
public class Circulo {

    private Punto centro;
    private double radio; // debe ser mayor o igual que cero    

    public Circulo(double origen_x, double origen_y, double radio) throws IllegalArgumentException {
        if (radio < 0) {
            throw new IllegalArgumentException("Radio negativo inválido");
        }
        Punto p = new Punto(origen_x, origen_y);
        this.centro = p;
        this.radio = radio;
    }

    public Circulo() {
        this(0,0,0);
    }

    public Circulo(Punto p, double radio) {
        this(p.getX(), p.getY(), radio);
    }

    public Circulo(Circulo c) {
        this(c.centro.getX(), c.centro.getY(), c.getRadio());
    }

    public double getRadio() {
        return this.radio;
    }

    public Punto getCentro() {
        return this.centro;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public void setCentro(Punto c) throws NullPointerException {
        if (c == null) {
            throw new NullPointerException("Centro inválido");
        }
        this.centro = c;
    }

    public double getArea() { //pi * r cuadrado
        return Math.PI * Math.pow(this.getRadio(), 2);
    }

    public double getCircunferencia() { //2 *pi * r
        return 2 * Math.PI * this.getRadio();
    }

    @Override
    public String toString() {
        return "Centro = (" + this.centro.getX() + "," + this.centro.getY() + "); Radio = " + this.getRadio();
    }

    public boolean equals(Circulo c) throws NullPointerException, IllegalArgumentException {
        if (c == null) {
            throw new NullPointerException("Centro inválido");
        }
        return (this.getCentro() == c.getCentro() && this.getRadio() == c.getRadio());
    }

}
