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
public class U4RefuerzoTrimestre1ClasesYObjetos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Pruebas clase Punto:");
        System.out.println("...");
        Punto p1 = new Punto(); // constructor por defecto
        Punto p2 = new Punto(3, 2); // constructor 2
        Punto p3 = new Punto(p2); // constructor 3

        // verificación constructores y métodos getter:
        assert p1.getX() == 0 : "p1.getX(), esperado = 0; actual = " + p1.getX();
        assert p1.getY() == 0 : "p1.getY(), esperado = 0; actual = " + p1.getY();
        assert p2.getX() == 3 : "p2.getX(), esperado = 3; actual = " + p2.getX();
        assert p2.getY() == 2 : "p2.getY(), esperado = 2; actual = " + p2.getY();
        assert p3.getX() == 3 : "p3.getX(), esperado = 3; actual = " + p3.getX();
        assert p3.getY() == 2 : "p3.getY(), esperado = 2; actual = " + p3.getY();

        // métodos setter
        p1.setX(5);
        p1.setY(7);
        assert p1.getX() == 5 : "p1.getX(), esperado = 5; actual = " + p1.getX();
        assert p1.getY() == 7 : "p1.getY(), esperado = 7; actual = " + p1.getY();

        // métodos toString y equals
        assert p1.toString().equals("X = 5.0; Y = 7.0") : p1.toString();

        //fin pruebas clase Punto
        System.out.println("pruebas clase Punto finalizadas, si no has visto un error la prueba ha sido correcta");

        System.out.println("");

        System.out.println("Pruebas clase Circulo:");
        System.out.println("...");
        Circulo c1 = new Circulo(); // constructor por defecto
        Circulo c2 = new Circulo(p1, 2); // constructor 2
        Circulo c3 = new Circulo(c2); // constructor 3
        Circulo c4 = new Circulo(2, 3, 8); // constructor 4

        // verificación constructores y métodos getter:
        assert c1.getCentro().getX() == 0 : "c1.getCentro().getX() = 0; actual = " + c1.getCentro().getX();
        assert c1.getCentro().getY() == 0 : "c1.getCentro().getY() = 0; actual = " + c1.getCentro().getY();
        assert c1.getRadio() == 0 : "c1.getRadio() = 0; actual = " + c1.getRadio();
        assert c2.getCentro().getX() == 5 : "c2.getCentro().getX() = 5; actual = " + c2.getCentro().getX();
        assert c2.getCentro().getY() == 7 : "c2.getCentro().getY() = 7; actual = " + c2.getCentro().getY();
        assert c2.getRadio() == 2 : "c2.getRadio() = 2; actual = " + c2.getRadio();
        assert c3.getCentro().getX() == 5 : "c3.getCentro().getX() = 5; actual = " + c3.getCentro().getX();
        assert c3.getCentro().getY() == 7 : "c3.getCentro().getY() = 7; actual = " + c3.getCentro().getY();
        assert c3.getRadio() == 2 : "c3.getRadio() = 2; actual = " + c3.getRadio();
        assert c4.getCentro().getX() == 2 : "c4.getCentro().getX() = 2; actual = " + c4.getCentro().getX();
        assert c4.getCentro().getY() == 3 : "c4.getCentro().getY() = 3; actual = " + c4.getCentro().getY();
        assert c4.getRadio() == 8 : "c4.getRadio() = 8; actual = " + c4.getRadio();

        // métodos setter 
        c1.setRadio(10);
        c1.setCentro(p3);
        assert c1.getRadio() == 10 : "c1.getRadio() = 10; actual = " + c1.getRadio();
        assert c1.getCentro().getX() == 3 : "c1.getCentro().getX() = 3; actual = " + c1.getCentro().getX();
        assert c1.getCentro().getY() == 2 : "c1.getCentro().getY() = 2; actual = " + c1.getCentro().getY();

        // métodos getter adicionales
        assert c2.getArea() == 12.566370614359172 : "c2.getArea() == 12.566370614359172; actual = " + c2.getArea();  // pi * r cuadrado
        assert c2.getCircunferencia() == 12.566370614359172 : "c2.getCircunferencia() == 12.566370614359172; actual = " + c2.getCircunferencia(); // 2 * pi * r
        
        // métodos toString y equals
        assert c1.toString().equals("Centro = (3.0,2.0); Radio = 10.0") : "c1.toString() = Centro = (3.0,2.0); Radio = 10.0; actual = " + c1.toString();

        //fin pruebas clase Circulo
        System.out.println("pruebas clase Circulo finalizadas, si no has visto un error la prueba ha sido correcta");
    }
}
