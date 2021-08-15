/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymtonificate;

import java.util.Calendar;

/**
 *
 * @author Miguel Moro
 */
public abstract class Persona implements Comparable {

    protected String nombre;
    protected String dni;
    protected String direccion;
    protected String localidad;
    protected String provincia;
    protected String codigoPostal; // 5 d�gitos
    protected String telefono; // 9 d�gitos
    protected Calendar fechaAlta;
    protected Calendar fechaNacimiento; // edad < 100
    protected char sexo; // H para hombre y M para mujer
    public static final double SMI = 950; // actualizado con valor de 2021

    public Persona(String nombre, String dni, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo) {
        if (!validarDni(dni)) {
            throw new IllegalArgumentException("DNI incorrecto");
        }
        if (!validarCodigoPostal(codigoPostal)) {
            throw new IllegalArgumentException("C�digo Postal incorrecto");
        }
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("Tel�fono incorrecto");
        }
        if (!validarFecha(fechaAlta.get(Calendar.DATE), fechaAlta.get(Calendar.MONTH)+1, fechaAlta.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de alta incorrecta");
        }
        if (!validarFecha(fechaNacimiento.get(Calendar.DATE), fechaNacimiento.get(Calendar.MONTH)+1, fechaNacimiento.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de nacimiento incorrecta");
        }
        if (getEdad(fechaNacimiento) > 99) {
            throw new IllegalArgumentException("Edad mayor de 99 a�os");
        }
        if (sexo != 'H' && sexo != 'M' && sexo != 'h' && sexo != 'm') {
            throw new IllegalArgumentException("El sexo es incorrecto");
        }
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.fechaAlta = Calendar.getInstance();
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = Calendar.getInstance();
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = Character.toUpperCase(sexo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        if (!validarDni(dni)) {
            throw new IllegalArgumentException("DNI incorrecto");
        }
        this.dni = dni.toUpperCase();
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCodigoPostal(String codigoPostal) {
        if (!validarCodigoPostal(codigoPostal)) {
            throw new IllegalArgumentException("C�digo Postal incorrecto");
        }
        this.codigoPostal = codigoPostal;
    }

    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("Tel�fono incorrecto");
        }
        this.telefono = telefono;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        if (!validarFecha(fechaAlta.get(Calendar.DATE), (fechaAlta.get(Calendar.MONTH)+1), fechaAlta.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de alta incorrecta");
        }
        this.fechaAlta = fechaAlta;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        if (!validarFecha(fechaNacimiento.get(Calendar.DATE), (fechaNacimiento.get(Calendar.MONTH)+1), fechaNacimiento.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de alta incorrecta");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setSexo(char sexo) {
        if (sexo != 'H' && sexo != 'M') {
            throw new IllegalArgumentException("El sexo es incorrecto");
        }
        this.sexo = sexo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Calendar getFechaAlta() {
        return this.fechaAlta;
    }

    public Calendar getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public char getSexo() {
        return this.sexo;
    }

    /**
     * Devuelve la diferencia en a�os entre la fecha facilitada y la fecha
     * actual (edad)
     *
     * @param fecha: fecha que compararemos con la fecha actual para determinar
     * la edad en a�os
     * @return devuelve un entero con la diferencia en a�os entre la fecha
     * facilitada como par�metro y la fecha actual
     */
    public static int getEdad(Calendar fecha) {
        Calendar today = Calendar.getInstance();
        int diff_year = today.get(Calendar.YEAR) - fecha.get(Calendar.YEAR);
        int diff_month = today.get(Calendar.MONTH) - fecha.get(Calendar.MONTH);
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - fecha.get(Calendar.DAY_OF_MONTH);

        //Si est� en ese a�o pero todav�a no los ha cumplido
        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1; 
        }
        return diff_year;
    }

    /**
     * Sobreescribimos el m�todo toString() para la clase. No es necesario
     * utilizar @override
     *
     * @return devuelve un texto con los atributos de la persona. Un atributo
     * por l�nea
     */
    @Override
    public String toString() {
        String resultado = "";
        resultado += ("Nombre: ") + (this.nombre) + ("; ");
        resultado += ("DNI: ") + (this.dni) + ("\n");
        resultado += ("Direcci�n: ") + (this.direccion) + ("; ");
        resultado += ("Localidad: ") + (this.localidad) + ("; ");
        resultado += ("Provincia: ") + (this.provincia) + ("; ");
        resultado += ("C�digo Postal: ") + (this.codigoPostal) + ("\n");
        resultado += ("Tel�fono: ") + (this.telefono) + ("; ");
        resultado += ("Fecha Alta: ") + (this.fechaAlta.get(Calendar.DAY_OF_MONTH)) + "/" + (this.fechaAlta.get(Calendar.MONTH)) + "/" + (this.fechaAlta.get(Calendar.YEAR)) + ("; ");
        resultado += ("Fecha Nacimiento: ") + (this.fechaNacimiento.get(Calendar.DAY_OF_MONTH)) + "/" + (this.fechaNacimiento.get(Calendar.MONTH)) + "/" + (this.fechaNacimiento.get(Calendar.YEAR)) + ("; ");
        resultado += ("Sexo: ");
        if (this.sexo == 'H') {
            resultado += ("Hombre\n");
        } else {
            resultado += ("Mujer\n");
        }
        return resultado;
    }

    /**
     * Comprueba si dos personas se consideran iguales, para lo cual evaluamos
     * si su DNI es el mismo
     *
     * @param dni: dni de la persona que queremos comparar
     * @return devuelve true si el dni facilitado como par�metro es el mismo que
     * el de la persona instanciada, false en caso contrario
     */
    public boolean equals(String dni) {
        if (!validarDni(dni)) {
            throw new IllegalArgumentException("DNI incorrecto");
        }
        return this.dni.equalsIgnoreCase(dni);
    }

    /**
     * Para cumplir con los requerimientos de la iterfaz Comparable. Compara si
     * una persona es mayor, menor o igual que otra pasada por par�metro,
     * teniendo en cuenta la fecha de nacimiento de ambos
     *
     * @param miObjeto: persona con la que compararemos la persona actual
     * @return devuelve lo siguiente: -1 si la persona actual tiene menos a�os
     * que la persona pasada por par�metro, 1 si la persona actual tiene m�s a�os
     * que la persona pasada por par�metro, 0 si ambas personas tienen el mismo
     * n�mero de a�os
     */
    //public int compareTo(Persona p) {
    @Override
    public int compareTo(Object miObjeto){
        Persona p = (Persona) miObjeto;
        int resultado = 0;
        if (Persona.getEdad(this.fechaNacimiento) < Persona.getEdad(p.fechaNacimiento)) {
            resultado = -1;
        } else if (Persona.getEdad(this.fechaNacimiento) > Persona.getEdad(p.fechaNacimiento)) {
            resultado = 1;
        }
        return resultado;
    }

    /**
     * Valida el formato de un Dni. 9 d�gitos y una letra
     *
     * @param dni: recibe el dni a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarDni(String dni) {
        return dni.matches("([0-9]{8})([A-Za-z])");
    }

    /**
     * Valida si la cadena pasada por par�metro contiene un c�digo postal v�lido
     * entre 01000 y 52999
     *
     * @param codigoPostal: recibe el c�digo postal a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarCodigoPostal(String codigoPostal) {
        // N�mero entre 01000 y 52999
        return codigoPostal.matches("(^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$)");
    }

    /**
     * Valida si la cadena pasada por par�metro contiene un n�mero de tel�fono
     * v�lido debe comenzar por 6, 8 � 9 y tener 9 d�gitos
     *
     * @param telefono: recibe el tel�fono a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarTelefono(String telefono) {
        // Debe empezar por 6, 8 � 9 y tener 9 d�gitos
        return telefono.matches("(^[689][0-9]{8}$)");
    }

    /**
     * Valida si una fecha es correcta
     *
     * @param dia: d�a a validar, entre 1 y 31 dependiendo del mes
     * @param mes: mes a validar, comenzando en 1 (enero = 1)
     * @param anio: a�o a validar, se considera v�lido cualquier entero
     * @return devuelve true si se trata de una fecha v�lida, false en caso
     * contrario
     */
    public static boolean validarFecha(int dia, int mes, int anio) {
        if (dia < 0 || mes < 0 || anio < 0) {
            throw new IllegalArgumentException("El n�mero debe ser positivo");
        }
        boolean fechaCorrecta = false;
        boolean diaok = false, mesok = false;

        if (mes <= 12) {
            mesok = true;
            switch (mes) {
                case 1, 3, 5, 7, 8, 10, 12 -> {
                    if (dia <= 31) {
                        diaok = true;
                    }
                }
                case 4, 6, 9, 11 -> {
                    if (dia <= 30) {
                        diaok = true;
                    }
                }
                default -> {
                    // febrero
                    if (dia > 29) {
                        diaok = false;
                    } else if (dia == 29) {
                        //ver si es bisiesto
                        if (esBisiesto(anio)) {
                            diaok = true;
                        }
                    } else {
                        diaok = true;
                    }
                }
            }
        }
        if (diaok && mesok) {
            fechaCorrecta = true;
        }
        return fechaCorrecta;
    }

    /**
     * Valida si el a�o facilitado es bisiesto Un a�o es bisiesto si es
     * divisible por 4, excepto el �ltimo de cada siglo, aquellos divisibles por
     * 100, que para ser bisiestos, tambi�n deben ser divisibles por 400
     *
     * @param a�o: a�o a validar si es bisiesto. Damos por v�lido cualquier
     * n�mero entero
     * @return devuelve true si el a�o facilitado es bisiesto
     */
    public static boolean esBisiesto(int a�o) {
        if (a�o < 0) {
            throw new IllegalArgumentException("El n�mero debe ser positivo");
        }
        boolean bisiesto = false;
        if ((a�o % 4 == 0) && ((a�o % 100 != 0) || (a�o % 400 == 0))) {
            bisiesto = true;
        }
        return bisiesto;
    }

}
