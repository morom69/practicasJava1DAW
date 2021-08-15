/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

/**
 * Clase que crea una cuenta bancaria y los procedimientos necesarios para
 * gestionarla
 *
 * @author Miguel Moro
 * @version 1.0, Created on November 28, 2020
 */
public class CuentaBancaria {

    private static final int TAM_MIN_TITULAR = 10;
    private static final int TAM_MAX_TITULAR = 100;

    private String titular; //deberá tener min 10 caracteres, max 100 caracteres
    private double saldo;
    private String entidad; //4 digitos entidad
    private String oficina; //4 digitos oficina
    private String numCuenta; //10 digitos cód.cuenta
    //2 digitos control calculados a partir de entidad, oficina y cuenta

    /**
     * Constructor a partir del titular y cada componente de la cuenta
     * (detallados en los parámetros). Se permite saldo negativo
     *
     * @param titular: titular de la cuenta, debe tener un tamaño entre 10 y 100
     * caracteres
     * @param entidad: código de entidad (4 dígitos)
     * @param oficina: código de oficina (4 dígitos)
     * @param DC: dígitos de control (2 dígitos). Deben cumplir los criterios de
     * validación necesarios
     * @param numCuenta: número de cuenta (10 dígitos)
     */
    public CuentaBancaria(String titular, String entidad, String oficina, String DC, String numCuenta) {
        if (titular.length() < 10 || titular.length() > 100) {
            throw new IllegalArgumentException("Titular de tamaño incorrecto");
        }
        System.out.println("Dc "+DC);
        System.out.println("obtenerDigitosControl(entidad, oficina, numCuenta) "+obtenerDigitosControl(entidad, oficina, numCuenta));
        if (!obtenerDigitosControl(entidad, oficina, numCuenta).equals(DC)) {
            throw new IllegalArgumentException("Dígito de control incorrecto");
        }
        this.saldo = 0;
        this.titular = titular;
        this.entidad = entidad;
        this.oficina = oficina;
        this.numCuenta = numCuenta;
    }

    /**
     * Segundo constructor llamado a partir del titular y los 20 dígitos del
     * código de cuenta corriente. Llama al primer constructor descomponiendo el
     * código de cuenta corriente en sus componentes.
     *
     * @param titular: titular de la cuenta, debe tener un tamaño entre 10 y 100
     * caracteres
     * @param ccc: código de cuenta corriente (20 dígitos), compuesto por código
     * de entidad (4 dígitos), código de oficina (4 dígitos), dígitos de control
     * (2 dígitos, deben cumplir los criterios de validación necesarios) y
     * número de cuenta (10 dígitos)
     */
    public CuentaBancaria(String titular, String ccc) {
        this(titular, ccc.substring(0, 4), ccc.substring(4, 8), ccc.substring(8, 10), ccc.substring(10, 20));
    }

    /**
     * Método getter para el titular de la cuenta
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Método getter para el saldo de la cuenta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Método getter para el código de entidad de la cuenta
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Método getter para el código de oficina de la cuenta
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Método getter para el número de cuenta
     */
    public String getNumCuenta() {
        return numCuenta;
    }

    /**
     * Método setter para establecer el titular de la cuenta
     *
     * @param titular: titular de la cuenta, entre 10 y 100 caracteres
     */
    public void setTitular(String titular) {
        if (titular.length() < 10 || titular.length() > 100) {
            throw new IllegalArgumentException("Titular de tamaño incorrecto");
        }
        this.titular = titular;
    }

    /**
     * Método para ingresar una cantidad en la cuenta. La cantidad ingresada (de
     * tipo double) se sumará al saldo de la cuenta). La cantidad debe ser
     * positiva
     *
     * @param cantidad: cantidad (de tipo double) a ingresar en la cuenta
     */
    public void ingresar(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("Cantidad negativa");
        }
        this.saldo += cantidad;
    }

    /**
     * Método para retirar una cantidad de la cuenta. La cantidad retirada (de
     * tipo double) se restará del saldo de la cuenta. Se permite que el saldo
     * sea negativo
     *
     * @param cantidad: cantidad (de tipo double) a retirar de la cuenta
     */
    public void retirar(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("Cantidad negativa");
        }
        this.saldo -= cantidad;
    }

    /**
     * Método que comprueba si los dígitos de control de la cuenta son correctos
     * a partir de el código de cuenta bancaria completo. Se descompone el
     * código de cuenta bancaria en sus componentes y se llama al método
     * obtenerDigitosControl()
     *
     * @param CCC: código de cuenta corriente (20 dígitos), compuesto por código
     * de entidad (4 dígitos), código de oficina (4 dígitos), dígitos de control
     * (2 dígitos, deben cumplir los criterios de validación necesarios) y
     * número de cuenta (10 dígitos)
     * @return devuelve true si los dígitos de control son correctos, false en
     * caso contrario
     */
    public static boolean comprobarCCC(String CCC) {
        if (CCC.length() != 20) {
            throw new IllegalArgumentException("Número de dígitos incorrectos");
        }
        if (CCC.equals(CCC.substring(0, 4)+CCC.substring(4, 8)+obtenerDigitosControl(CCC.substring(0, 4), CCC.substring(4, 8), CCC.substring(10, 20))+CCC.substring(10, 20))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que calcula los dígitos de control de una cuenta bancaria a partir
     * del código de entidad, código de oficina y número de cuenta
     *
     * @param entidad: código de entidad (4 dígitos)
     * @param oficina: código de oficina (4 dígitos)
     * @param numCuenta: número de cuenta (10 dígitos)
     * @return devuelve los dos dígitos de control para la cuenta con los datos
     * facilitados
     */
    public static String obtenerDigitosControl(String entidad, String oficina, String numCuenta) {
        if (entidad.length() != 4|| oficina.length() != 4 || numCuenta.length() != 10) {
            throw new IllegalArgumentException("Algún parámetro con dígitos incorrectos");
        }
        String pesos = "01020408051009070306"; //configuramos cada peso para que esté compuesto por dos dígitos
        String digito1 = "";
        String digito2 = "";
        int acumulador = 0;
        String entidadOficina = "00" + entidad + oficina;
        String entidadOficnaDobleDigito = "";
        String numCuentaDobleDigito = "";
        for (int k = 0; k <= 9; k++) { //para poder operar con los pesos que tiene 2 dígitos asignamos también dos dígitos a cada caracter de entidad, oficina y cuenta
            entidadOficnaDobleDigito = entidadOficnaDobleDigito + "0";
            entidadOficnaDobleDigito = entidadOficnaDobleDigito + entidadOficina.substring(k, k + 1);
            numCuentaDobleDigito = numCuentaDobleDigito + "0";
            numCuentaDobleDigito = numCuentaDobleDigito + numCuenta.substring(k, k + 1);
        }
        for (int j = 1; j <= 2; j++) {//para cada uno de los dos dígitos de control
            if (j == 2) {
                acumulador = 0;
                entidadOficnaDobleDigito = numCuentaDobleDigito;
            }
            for (int i = 0; i < 20; i = i + 2) {//para cada caracter de la cadena de generación
                acumulador += Integer.parseInt(pesos.substring(i, i + 2)) * Integer.parseInt(entidadOficnaDobleDigito.substring(i, i + 2));
            }
            if (j == 1) {
                digito1 = calcularDigito(acumulador);
            } else {
                digito2 = calcularDigito(acumulador);
            }
        }
        return digito1 + digito2;
    }

    /**
     * Método que facilita la lógica final para el cálculo de un dígito de
     * control de una cuenta bancaria una vez se ha realizado el cálculo de
     * multiplicación de cada dígito por su peso correspondiente y la suma de
     * éstos
     *
     * @param digito: contiene el acumulado de multiplicar cada dígito por su
     * peso y realizar la suma de todos ellos
     * @return devuelve el dígito de control correspondiente (un único dígito)
     */
    public static String calcularDigito(int digito) {
        digito = Integer.valueOf(digito) % 11;
        digito = 11 - digito;
        if (digito == 10) {
            digito = 1;
        } else if (digito == 11) {
            digito = 0;
        }
        return String.valueOf(digito);
    }

    /**
     * Método que valida que una cadena de texto está compuesta únicamente por
     * dígitos numéricos y tiene una longitud indicada
     *
     * @param numero: cadena de texto a validar
     * @param longitud: longitud que se va a validar si es igual a la de la
     * cadena de texto facilitada en el parámetro "numero"
     * @return devuelve true si la cadena de texto facilitada está compuesta
     * solo por dígitos y si coincide con la longitud facilitada. Devuelve false
     * en cualquier otro caso
     */
    public static boolean validarEnteroLongitud(String numero, int longitud) {
        boolean longitudOk = false;
        boolean esEntero = true;
        int largo = numero.length();
        int i = 0;
        String numeros = "0123456789";
        if (longitud == numero.length()) {
            longitudOk = true;
        }
        while (i < largo && esEntero) {
            //System.out.println("subcadena "+numero.substring(i, i+1));
            if (numeros.indexOf(numero.substring(i, i+1)) == -1) {
                esEntero = false;
            }
            i++;
        }
        return (esEntero && longitudOk);
    }
}
