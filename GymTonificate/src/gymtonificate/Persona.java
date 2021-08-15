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
    protected String codigoPostal; // 5 dígitos
    protected String telefono; // 9 dígitos
    protected Calendar fechaAlta;
    protected Calendar fechaNacimiento; // edad < 100
    protected char sexo; // H para hombre y M para mujer
    public static final double SMI = 950; // actualizado con valor de 2021

    public Persona(String nombre, String dni, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo) {
        if (!validarDni(dni)) {
            throw new IllegalArgumentException("DNI incorrecto");
        }
        if (!validarCodigoPostal(codigoPostal)) {
            throw new IllegalArgumentException("Código Postal incorrecto");
        }
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("Teléfono incorrecto");
        }
        if (!validarFecha(fechaAlta.get(Calendar.DATE), fechaAlta.get(Calendar.MONTH)+1, fechaAlta.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de alta incorrecta");
        }
        if (!validarFecha(fechaNacimiento.get(Calendar.DATE), fechaNacimiento.get(Calendar.MONTH)+1, fechaNacimiento.get(Calendar.YEAR))) {
            throw new IllegalArgumentException("Fecha de nacimiento incorrecta");
        }
        if (getEdad(fechaNacimiento) > 99) {
            throw new IllegalArgumentException("Edad mayor de 99 años");
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
            throw new IllegalArgumentException("Código Postal incorrecto");
        }
        this.codigoPostal = codigoPostal;
    }

    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("Teléfono incorrecto");
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
     * Devuelve la diferencia en años entre la fecha facilitada y la fecha
     * actual (edad)
     *
     * @param fecha: fecha que compararemos con la fecha actual para determinar
     * la edad en años
     * @return devuelve un entero con la diferencia en años entre la fecha
     * facilitada como parámetro y la fecha actual
     */
    public static int getEdad(Calendar fecha) {
        Calendar today = Calendar.getInstance();
        int diff_year = today.get(Calendar.YEAR) - fecha.get(Calendar.YEAR);
        int diff_month = today.get(Calendar.MONTH) - fecha.get(Calendar.MONTH);
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - fecha.get(Calendar.DAY_OF_MONTH);

        //Si está en ese año pero todavía no los ha cumplido
        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1; 
        }
        return diff_year;
    }

    /**
     * Sobreescribimos el método toString() para la clase. No es necesario
     * utilizar @override
     *
     * @return devuelve un texto con los atributos de la persona. Un atributo
     * por línea
     */
    @Override
    public String toString() {
        String resultado = "";
        resultado += ("Nombre: ") + (this.nombre) + ("; ");
        resultado += ("DNI: ") + (this.dni) + ("\n");
        resultado += ("Dirección: ") + (this.direccion) + ("; ");
        resultado += ("Localidad: ") + (this.localidad) + ("; ");
        resultado += ("Provincia: ") + (this.provincia) + ("; ");
        resultado += ("Código Postal: ") + (this.codigoPostal) + ("\n");
        resultado += ("Teléfono: ") + (this.telefono) + ("; ");
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
     * @return devuelve true si el dni facilitado como parámetro es el mismo que
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
     * una persona es mayor, menor o igual que otra pasada por parámetro,
     * teniendo en cuenta la fecha de nacimiento de ambos
     *
     * @param miObjeto: persona con la que compararemos la persona actual
     * @return devuelve lo siguiente: -1 si la persona actual tiene menos años
     * que la persona pasada por parámetro, 1 si la persona actual tiene más años
     * que la persona pasada por parámetro, 0 si ambas personas tienen el mismo
     * número de años
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
     * Valida el formato de un Dni. 9 dígitos y una letra
     *
     * @param dni: recibe el dni a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarDni(String dni) {
        return dni.matches("([0-9]{8})([A-Za-z])");
    }

    /**
     * Valida si la cadena pasada por parámetro contiene un código postal válido
     * entre 01000 y 52999
     *
     * @param codigoPostal: recibe el código postal a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarCodigoPostal(String codigoPostal) {
        // Número entre 01000 y 52999
        return codigoPostal.matches("(^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$)");
    }

    /**
     * Valida si la cadena pasada por parámetro contiene un número de teléfono
     * válido debe comenzar por 6, 8 ó 9 y tener 9 dígitos
     *
     * @param telefono: recibe el teléfono a validar
     * @return devuelve true si la cadena de texto facilitada tiene el formato
     * requerido. Devuelve false en caso contrario
     */
    public static boolean validarTelefono(String telefono) {
        // Debe empezar por 6, 8 ó 9 y tener 9 dígitos
        return telefono.matches("(^[689][0-9]{8}$)");
    }

    /**
     * Valida si una fecha es correcta
     *
     * @param dia: día a validar, entre 1 y 31 dependiendo del mes
     * @param mes: mes a validar, comenzando en 1 (enero = 1)
     * @param anio: año a validar, se considera válido cualquier entero
     * @return devuelve true si se trata de una fecha válida, false en caso
     * contrario
     */
    public static boolean validarFecha(int dia, int mes, int anio) {
        if (dia < 0 || mes < 0 || anio < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
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
     * Valida si el año facilitado es bisiesto Un año es bisiesto si es
     * divisible por 4, excepto el último de cada siglo, aquellos divisibles por
     * 100, que para ser bisiestos, también deben ser divisibles por 400
     *
     * @param año: año a validar si es bisiesto. Damos por válido cualquier
     * número entero
     * @return devuelve true si el año facilitado es bisiesto
     */
    public static boolean esBisiesto(int año) {
        if (año < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        boolean bisiesto = false;
        if ((año % 4 == 0) && ((año % 100 != 0) || (año % 400 == 0))) {
            bisiesto = true;
        }
        return bisiesto;
    }

}
