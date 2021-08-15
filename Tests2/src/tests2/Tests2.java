/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests2;

import jdk.javadoc.doclet.Doclet;
import static jdk.javadoc.doclet.Doclet.Option.Kind.EXTENDED;

/**
 *
 * @author migue
 */
public class Tests2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //metodo1();

        char i;
        i = 2665;
        System.out.println("♥♦♣♠");
        PaloNaipe palo = PaloNaipe.PICAS;
        System.out.println(palo);
        //palo.toString();
        //System.out.printf("%c%n", -x EXTENDED[A69 - 0x7F]);
    }

    public enum PaloNaipe {
        PICAS("Picas"), CORAZONES("Corazones"), TREBOLES("Tréboles"), DIAMANTES("Diamantes");

        private String miPalo;

        private PaloNaipe(String miPalo) {
            this.miPalo = miPalo;
        }

        public String getName() {
            return this.miPalo;
        }

        @Override
        public String toString() {
            String simbolo = " ";
            switch (this.getName()) {
                case "Picas": {
                    simbolo = "♠";
                    break;
                }
                case "Corazones": {
                    simbolo = "♥";
                    break;
                }
                case "Tréboles": {
                    simbolo = "♣";
                    break;
                }
                case "Diamantes": {
                    simbolo = "♦";
                    break;
                }
            }
            return "El palo es " + simbolo;
        }
    }

    /**
     *
     */
    public static void metodo1() {
        String[] palabras = new String[4];
        palabras[1] = "palabra1";
        palabras[2] = "palabra2";
        palabras[3] = "palabra3";
        try {
            System.out.println(" antes del for ");
            for (int i = 1; i < palabras.length; i++) {
                System.out.println(palabras[i % 3]);
            }
            System.out.println(" despues del for ");
        } catch (NullPointerException npe) {
            System.out.println(" null pointer ");
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            System.out.println(" array index out ");
        } catch (Exception e) {
            System.out.println(" exception ");
        } finally {
            System.out.println(" todo bien? ");
        }
    }

}
