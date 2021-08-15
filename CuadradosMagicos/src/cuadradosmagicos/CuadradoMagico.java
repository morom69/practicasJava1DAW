/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuadradosmagicos;

/**
 *
 * @author Miguel Moro
 */
public class CuadradoMagico {

    private int[][] datos; // ancho de la plaza en cms

    // constructor por defecto
    public CuadradoMagico() {
        this.datos = new int[3][3];
    }

    // constructor de tamaño N x N
    public CuadradoMagico(int N) {
        if (N < 3) {
            throw new IllegalArgumentException("N debe ser mayor o igual que 3");
        }
        this.datos = new int[N][N];
    }

    // constructor copia
    public CuadradoMagico(CuadradoMagico cm) {
        boolean resultado = true;
        int N = cm.datos.length;
        int[][] copia = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!ponerNumero(i, j, getNumero(i, j))) {
                    System.out.println("Se ha intentado generar un cuadrado mágico erróneo, tiene al menos un número repetido");
                } else {
                    this.ponerNumero(i, j, cm.getNumero(i, j));
                }
            }
        }
    }

    public int getNumero(int fila, int columna) {
        return this.datos[fila][columna];
    }

    public boolean ponerNumero(int fila, int columna, int numero) {
        if (numero < 1 || numero > Math.pow(this.datos.length, 2)) {
            throw new IllegalArgumentException("El número está fuera del rango [1, n^2]");
        }
        boolean existe = false;
        for (int i = 0; i < this.datos.length && !existe; i++) {
            for (int j = 0; j < this.datos.length && !existe; j++) {
                if (numero == getNumero(i, j)) {
                    existe = true;
                }
            }
        }
        if (!existe) {
            this.datos[fila][columna] = numero;
        }
        return !existe;
    }

    public void quitarNumero(int fila, int columna) {
        if (fila < 0 || columna < 0 || fila > this.datos.length || columna > this.datos.length) {
            throw new IllegalArgumentException("La posición del número a eliminar no existe");
        }
        this.datos[fila][columna] = 0;
    }

    public static boolean comprobar(CuadradoMagico cm) {
        boolean resultado = false;
        if (cuadradoCompleto(cm) && (evaluarFilas(cm) != -1) && (evaluarColumnas(cm) != -1) && (evaluarFilas(cm) == evaluarColumnas(cm)) && (valorDiagonal1(cm) == valorDiagonal2(cm)) && (evaluarFilas(cm) == valorDiagonal1(cm))) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean cuadradoCompleto(CuadradoMagico cm) {
        boolean completo = true;
        for (int i = 0; i < cm.datos.length && completo; i++) {
            for (int j = 0; j < cm.datos.length && completo; j++) {
                if (cm.getNumero(i, j) == 0) {
                    completo = false;
                }
            }
        }
        return completo;
    }

    public static int evaluarFilas(CuadradoMagico cm) {
        int valor = 0;
        int valorAnterior = -1; // para evaluar si estamos en la primera fila
        boolean verdadero = true;
        for (int i = 0; i < cm.datos.length && verdadero; i++) { // para cada fila 
            for (int j = 0; j < cm.datos.length; j++) {
                valor += cm.getNumero(i, j);
            }
            if (valorAnterior == -1) {
                valorAnterior = valor;
            } else {
                if (valor != valorAnterior) {
                    verdadero = false;
                }
            }
            valor = 0;
        }
        if (!verdadero) {
            valor = -1;
        }
        return valorAnterior;
    }

    public static int evaluarColumnas(CuadradoMagico cm) {
        int valor = 0;
        int valorAnterior = -1; // para evaluar si estamos en la primera columna
        boolean verdadero = true;
        for (int j = 0; j < cm.datos.length && verdadero; j++) { // para cada columna
            for (int i = 0; i < cm.datos.length; i++) {
                valor += cm.getNumero(i, j);
            }
            if (valorAnterior == -1) {
                valorAnterior = valor;
            } else {
                if (valor != valorAnterior) {
                    verdadero = false;
                }
            }
            valor = 0;
        }
        if (!verdadero) {
            valor = -1;
        }
        return valorAnterior;
    }

    public static int valorDiagonal1(CuadradoMagico cm) {
        int valor = 0;
        for (int i = 0; i < cm.datos.length; i++) {
            valor += cm.getNumero(i, i);
        }
        return valor;
    }

    public static int valorDiagonal2(CuadradoMagico cm) {
        int valor = 0;
        for (int i = 0; i < cm.datos.length; i++) {
            valor += cm.getNumero(i, (cm.datos.length - (i+1)));
        }
        return valor;
    }

    public int getConstanteMagica() {
        int suma = 0;
        if (comprobar(this)) {
            for (int i = 0; i < this.datos.length; i++) {
                suma += this.datos[0][i];
            }
        }
        else {
            suma = -1;
        }
        return suma;
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < this.datos.length; i++) {
            for (int j = 0; j < this.datos.length; j++) {
                resultado += ("   ") + (getNumero(i, j));
            }
            resultado += ("\n");
        }
        return resultado;
    }

    public boolean equals(CuadradoMagico cm) {
        boolean resultado = true;
        for (int i = 0; i < this.datos.length && resultado; i++) {
            for (int j = 0; j < this.datos.length && resultado; j++) {
                if (this.datos[i][j] != cm.datos[i][j]) {
                    resultado = false;
                }
            }
        }
        return resultado;
    }
}
