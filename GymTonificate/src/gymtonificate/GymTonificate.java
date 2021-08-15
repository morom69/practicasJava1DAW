/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymtonificate;

import static gymtonificate.CargaDatosPrueba.cargaDatosPrueba;
import static gymtonificate.Persona.validarCodigoPostal;
import static gymtonificate.Persona.validarDni;
import static gymtonificate.Persona.validarFecha;
import static gymtonificate.Persona.validarTelefono;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Miguel Moro
 */
public class GymTonificate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Persona> listaPersonal = new ArrayList<>();
        ArrayList<String> listaEspecialidades = new ArrayList<>();
        ArrayList<String> listaTrabajos = new ArrayList<>();

        Scanner lectura = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        char tipoPersona;
        String tipo = "";

        // para facilitar las pruebas damos la opci�n de cargar datos de prueba
        System.out.println("Desea cargar datos de prueba?");
        System.out.println("Entrar 1 para cargar datos de prueba, cualquier otro entero para saltar la carga: ");
        while (!lectura.hasNextInt()) { //validaci�n de que para la opci�n de men� se introduce un valor num�rico
            lectura.next();
            System.out.println("Debe introducir un valor entero: ");
        }
        opcion = lectura.nextInt();
        lectura.skip("\n"); //se utiliza para evitar que la pr�xima vez salte la l�nea al interpretar el enter de la lectura anterio
        if (opcion == 1) {
            cargaDatosPrueba(listaPersonal, listaEspecialidades, listaTrabajos);
            System.out.println("Datos de prueba cargados");
        }
        // fin carga datos de prueba

        do {
            System.out.println();
            System.out.println("GYMNASIO TONIF�CATE");
            System.out.println("===================");
            System.out.println("1.Alta personas (socios, empleados, monitores");
            System.out.println("2.Baja personas");
            System.out.println("3.Modificar personas");
            System.out.println("4.Listar personas existentes");
            System.out.println("5.Listar personas por tipo");
            System.out.println("6.Gestionar especialidades");
            System.out.println("7.Gestionar trabajos");
            System.out.println("8.Salir del programa");
            System.out.println("Introduzca opci�n: ");

            while (!lectura.hasNextInt()) { //validaci�n de que para la opci�n de men� se introduce un valor num�rico
                lectura.next();
                System.out.println("Debe introducir un valor num�rico entre 1 y 8: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la pr�xima vez salte la l�nea al interpretar el enter de la lectura anterior

            switch (opcion) {
                case 1 -> {
                    System.out.println("1.Alta personas (socios, empleados y monitores)");
                    altaPersonas(listaPersonal, listaEspecialidades, listaTrabajos);
                }
                case 2 -> {
                    System.out.println("2.Baja personas");
                    bajaPersonas(listaPersonal);
                }
                case 3 -> {
                    System.out.println("3.Modificar personas");
                    modificarPersonas(listaPersonal, listaEspecialidades, listaTrabajos);
                }
                case 4 -> {
                    System.out.println("4.Listar personas existentes");
                    listarPersonas(listaPersonal);
                }
                case 5 -> {
                    System.out.println("5.Listar personas por tipo");
                    tipoPersona = leerTipoPersona();
                    switch (tipoPersona) {
                        case 'S' ->
                            tipo = "Socio";
                        case 'M' ->
                            tipo = "Monitor";
                        case 'E' ->
                            tipo = "Empleado";
                    }
                    listarPersonas(listaPersonal, tipo);
                }
                case 6 -> {
                    System.out.println("6.Gestionar especialidades");
                    gestionarListas(listaEspecialidades, listaTrabajos, 1);
                    //gestionarEspecialidades(listaEspecialidades);
                }
                case 7 -> {
                    System.out.println("7.Gestionar trabajos");
                    gestionarListas(listaEspecialidades, listaTrabajos, 2);
                    //gestionarTrabajos(listaTrabajos);
                }
                case 8 ->
                    System.out.println("8.Salir del programa");
            }

        } while (opcion != 8);
    }

    /**
     * Gestiona el alta de personal
     *
     * @param listaPersonal: lista de personal que vamos a modificar
     * introduciento un nuevo personal si se cumplen las condiciones
     * @param listaEspecialidades: lista de especialidades que existen dadas de
     * alta y se pueden utilizar en el alta de monitores. Se hace una copia para
     * no modificarla
     * @param listaTrabajos: lista de trabajos que existen dados de alta y se
     * pueden utilizar en el alta de empleados. Se hace una copia para no
     * modificarla
     */
    public static void altaPersonas(ArrayList<Persona> listaPersonal, ArrayList<String> listaEspecialidades, ArrayList<String> listaTrabajos) {
        String dni, nombre, direccion, localidad, provincia, codigoPostal, telefono; // datos comunes de Persona
        Calendar today = Calendar.getInstance(); // para comparar con la fecha actual
        Calendar fechaAlta = Calendar.getInstance(); // Persona 
        Calendar fechaNacimiento = Calendar.getInstance(); // Persona
        char sexo, tipoPersona, auxChar;
        boolean primero = true; // para evaluar cuando se ha entrado en un bucle m�s de una vez

        // para socios
        int socioSesionesSemanales;
        boolean socioPagado;
        String socioLesiones;

        // para monitores
        String[] monitorEspecialidad;
        float monitorSueldo;
        boolean monitorActivo;
        float floatSMI = (float) Persona.SMI;

        // para empleados
        String empleadoTrabajo, empleadoExtension;
        float empleadoSueldo;

        // para no modificar las listas existentes de especialidades y trabajos usamos copias de estos objetos
        ArrayList<String> copiaListaTrabajos = new ArrayList<>();
        copiaListaTrabajos = copiaArrayListString(listaTrabajos);

        // comenzamos el proceso de alta
        System.out.println("ALTA DE PERSONAS");
        System.out.print("Hace falta un DNI, ");
        dni = leerString();

        if (!validarDni(dni)) { // si el DNI no es v�lido no continuamos
            System.out.println("El DNI no tiene un formato v�lido, se cancela el alta");
        } else {
            if (existeDni(dni, listaPersonal) != -1) { // si el DNI ya existe no continuamos 
                System.out.println("La persona ya se encuentra dada de alta, no se puede repetir el alta");
            } else { // procedemos con el alta
                System.out.print("Nombre, ");
                nombre = leerString();
                System.out.print("Direcci�n, ");
                direccion = leerString();
                System.out.print("Localidad, ");
                localidad = leerString();
                System.out.print("Provincia, ");
                provincia = leerString();

                //leer c�digo postal con validacion
                primero = true;
                do {
                    System.out.print("C�digo Postal, ");
                    if (!primero) {
                        System.out.println("Debe introducir uno v�lido");
                    }
                    codigoPostal = leerString();
                    primero = false;
                } while (!validarCodigoPostal(codigoPostal));

                //leer tel�fono con validacion
                primero = true;
                do {
                    System.out.print("Tel�fono, ");
                    if (!primero) {
                        System.out.println("Debe introducir uno v�lido");
                    }
                    telefono = leerString();
                    primero = false;
                } while (!validarTelefono(telefono));

                //leer fecha de alta con validacion
                System.out.print("Fecha de Alta, ");
                fechaAlta = leerFecha();

                //leer fecha de nacimiento con validacion
                primero = true;
                do {
                    if (!primero) {
                        if (fechaNacimiento.after(today)) {
                            System.out.println("No se puede nacer en el futuro");
                        } else {
                            System.out.println("No se permite edad mayor de 99 a�os");
                        }
                    }
                    System.out.print("Fecha de Nacimiento, ");
                    fechaNacimiento = leerFecha();
                    primero = false;
                } while (fechaNacimiento.after(today) || Persona.getEdad(fechaNacimiento) > 99);

                //leer sexo con validacion
                primero = true;
                do {
                    if (!primero) {
                        System.out.println("Debe introducir H o M");
                    }
                    System.out.println("Sexo, (H/M) ");
                    sexo = leerCaracter();
                    primero = false;
                } while (sexo != 'H' && sexo != 'M');

                tipoPersona = leerTipoPersona();

                //pedir datos espec�ficos
                switch (tipoPersona) {
                    case 'S' -> {
                        // SOCIO

                        // leer sesiones semanales
                        System.out.println("�Cu�ntas sesiones semanales quiere contratar? ");
                        socioSesionesSemanales = leerEnteroEntreLimites(2, 6); // m�nimo 2, m�ximo 6

                        // leer si se encuentra al d�a de los pagos
                        System.out.print("�Est� al d�a de pagos?, ");
                        primero = true;
                        do {
                            if (!primero) {
                                System.out.println("Debe introducir S (s�) o N (no)");
                            }
                            System.out.println("�Se encuentra al d�a de pago (S/N)? ");
                            auxChar = leerCaracter();
                            primero = false;
                        } while (auxChar != 'S' && auxChar != 'N');
                        socioPagado = (auxChar == 'S');

                        // registra si tiene alg�n tipo de lesi�n
                        System.out.print("Lesiones que tiene, ");
                        socioLesiones = leerString();

                        // llamada al constructor para dar el alta
                        Persona socio = new Socio(socioSesionesSemanales, socioPagado, socioLesiones, nombre, dni, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
                        // mostrar datos del alta
                        System.out.println(socio.toString());

                        // le damos de alta en la lista de personas
                        listaPersonal.add(socio);
                    }

                    case 'M' -> {
                        // MONITOR

                        // si es monitor validar especialidades
                        //m�ximo 3 especialidades por monitor, que deben existir previamente en la lista de especialidades
                        monitorEspecialidad = modificarListaEspecialidadesMonitor(listaEspecialidades);

                        //leer si est� activo;
                        primero = true;
                        do {
                            if (!primero) {
                                System.out.println("Debe introducir S (s�) o N (no)");
                            }
                            System.out.println("�Se encuentra en activo (S/N)? ");
                            auxChar = leerCaracter();
                            primero = false;
                        } while (auxChar != 'S' && auxChar != 'N');
                        monitorActivo = (auxChar == 'S');

                        //leer sueldo, como m�nimo el Salario M�nimo Interprofesional - SMI
                        System.out.print("Sueldo, ");
                        monitorSueldo = leerFloatMinimo(floatSMI);

                        // llamada al constructor para dar el alta
                        Persona monitor = new Monitor(monitorEspecialidad, monitorSueldo, monitorActivo, nombre, dni, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
                        // mostrar datos del alta
                        System.out.println(monitor.toString());

                        // le damos de alta en la lista de personas
                        listaPersonal.add(monitor);
                    }

                    case 'E' -> {
                        // EMPLEADO

                        // si es empleado validar trabajo, debe estar incluido en listaTrabajos
                        do {
                            System.out.println("Introducir trabajo, debe estar registrad en la lista de trabajos del gimnasio: ");
                            System.out.println("Opciones disponibles: ");
                            listarArrayListString(copiaListaTrabajos);
                            empleadoTrabajo = leerString();
                        } while (!copiaListaTrabajos.contains(empleadoTrabajo));

                        //leer sueldo, como m�nimo el Salario M�nimo Interprofesional - SMI
                        System.out.print("Sueldo, ");
                        empleadoSueldo = leerFloatMinimo(floatSMI);

                        // leer extensi�n telef�nica
                        System.out.print("Extensi�n, ");
                        empleadoExtension = leerString();

                        // llamada al constructor para dar el alta
                        Persona empleado = new Empleado(empleadoTrabajo, empleadoSueldo, empleadoExtension, nombre, dni, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
                        // mostrar datos
                        System.out.println(empleado.toString());

                        // le damos de alta en la lista de personas
                        listaPersonal.add(empleado);
                    }
                }
            }
        }
    }

    /**
     * Gestiona las bajas de personal
     *
     * @param listaPersonal: lista de personal que vamos a modificar eliminando
     * personal si se cumplen las condiciones
     */
    public static void bajaPersonas(ArrayList<Persona> listaPersonal) {
        char auxChar; // para leer de teclado
        String dni;
        boolean confirmarBaja = false;
        boolean primero = true; // para validaciones en bucles

        // comenzamos el proceso de baja
        System.out.println("BAJA DE PERSONAS");
        System.out.print("DNI de la persona a dar de baja, ");
        dni = leerString();

        if (!validarDni(dni)) { // si el DNI no es v�lido no continuamos
            System.out.println("El DNI no tiene un formato v�lido, se cancela la baja");
        } else {
            if (existeDni(dni, listaPersonal) == -1) { // si el DNI no existe 
                System.out.println("El DNI no existe en nuestra base de datos, no se puede dar de baja");
            } else { // mostramos los datos
                // y pedimos confirmaci�n para dar de baja
                do {
                    if (!primero) {
                        System.out.println("Debe introducir S (s�) o N (no)");
                    }
                    System.out.println("Se va a dar de baja a la siguiente persona: ");
                    System.out.println(listaPersonal.get(existeDni(dni, listaPersonal)).toString());
                    System.out.println("�Quiere confirmar la baja (S/N)? ");
                    auxChar = leerCaracter();
                    primero = false;
                } while (auxChar != 'S' && auxChar != 'N');
                confirmarBaja = (auxChar == 'S');

                if (confirmarBaja) {
                    listaPersonal.remove(existeDni(dni, listaPersonal));
                    System.out.println("Baja realizada");
                } else {
                    System.out.println("Baja cancelada");
                }
            }
        }
    }

    /**
     * Gestiona las modificaciones de personal
     *
     * @param listaPersonal: lista de personal que vamos a modificar si se
     * cumplen las condiciones
     * @param listaEspecialidades: lista de especialidades que existen dadas de
     * alta y se pueden utilizar en las modificaciones de monitores. Se hace una
     * copia para no modificarla
     * @param listaTrabajos: lista de trabajos que existen dados de alta y se
     * pueden utilizar en las modificaciones de empleados. Se hace una copia
     * para no modificarla
     */
    public static void modificarPersonas(ArrayList<Persona> listaPersonal, ArrayList<String> listaEspecialidades, ArrayList<String> listaTrabajos) {

        String dni, tipoPersona;
        String menuModificacionesPersona = "1. Direcci�n\n2. Localidad\n3. Provincia\n4. C�digo postal\n5. Tel�fono\n6. Fecha alta\n7. Fecha nacimiento\n8. Sexo\n";
        String menuModificacionesSocio = menuModificacionesPersona + "9. Sesiones semanales\n10.Pagado\n11.Lesiones";
        String menuModificacionesMonitor = menuModificacionesPersona + "9. Especialidades\n10.Sueldo\n11.Activo";
        String menuModificacionesEmpleado = menuModificacionesPersona + "9. Tipo trabajo\n10.Sueldo\n11.Extensi�n";
        int auxInt;

        Persona persona;
        Socio socio;
        Monitor monitor;
        Empleado empleado;

        // para no modificar las listas existentes de especialidades y trabajos usamos copias de estos objetos
        ArrayList<String> copiaListaEspecialidades = new ArrayList<>();
        copiaListaEspecialidades = copiaArrayListString(listaEspecialidades);

        ArrayList<String> copiaListaTrabajos = new ArrayList<>();
        copiaListaTrabajos = copiaArrayListString(listaTrabajos);

        // comenzamos el proceso de modificaci�n
        System.out.println("MODIFICACI�N DE PERSONAS");
        System.out.print("DNI de la persona a modificar,  ");
        dni = leerString();

        if (!validarDni(dni)) { // si el DNI no es v�lido no continuamos
            System.out.println("El DNI no tiene un formato v�lido, se cancela la modificaci�n");
        } else {
            if (existeDni(dni, listaPersonal) == -1) { // si el DNI no existe no lo podemos modificar 
                System.out.println("La persona no se encuentra dada de alta, no se puede modificar");
            } else { // procedemos con la modificaci�n
                persona = listaPersonal.get(existeDni(dni, listaPersonal));
                System.out.println("Datos actuales:");
                System.out.println(persona.toString());

                tipoPersona = persona.getClass().getSimpleName() + ""; // obtenemos el tipo de persona

                switch (tipoPersona) { // actualizamos las opciones disponibles seg�n el tipo de persona
                    case "Socio" -> {
                        System.out.println(menuModificacionesSocio);
                    }
                    case "Monitor" -> {
                        System.out.println(menuModificacionesMonitor);
                    }
                    case "Empleado" -> {
                        System.out.println(menuModificacionesEmpleado);
                    }
                }

                System.out.println("Introduzca el n�mero correspondiente al atributo a modificar: ");
                auxInt = leerEnteroEntreLimites(1, 11);

                if (auxInt >= 9) {
                    // reasignamos la opci�n seg�n la clase: 
                    switch (auxInt) {
                        case 9 -> {
                            // Sesiones semanales (Socio, permanece con el mismo valor 9) 
                            if (tipoPersona.equals("Monitor")) {
                                auxInt = 12; // Especialidades (Monitor, pasa a ser la opci�n 12)
                            } else if (tipoPersona.equals("Empleado")) {
                                auxInt = 15; // Tipo trabajo (Empleado, pasa a ser la opci�n 15)
                            }
                        }
                        case 10 -> {
                            // Pagado (Socio, permanece con el mismo valor 10) 
                            if (tipoPersona.equals("Monitor")) {
                                auxInt = 13; // Sueldo (Monitor, pasa a ser la opci�n 13)
                            } else if (tipoPersona.equals("Empleado")) {
                                auxInt = 16; // Sueldo (Empleado, pasa a ser la opci�n 16)
                            }
                        }
                        case 11 -> {
                            // Lesiones (Socio, permanece con el mismo valor 9) 
                            if (tipoPersona.equals("Monitor")) {
                                auxInt = 14; // Activo (Monitor, pasa a ser la opci�n 14)
                            } else if (tipoPersona.equals("Empleado")) {
                                auxInt = 17; // Extensi�n (Empleado, pasa a ser la opci�n 17)
                            }
                        }
                    }
                }

                if (auxInt <= 8) { // es opci�n de Persona
                    modificarPersona(listaPersonal, persona, auxInt);
                    System.out.println("Datos modificados: \n" + persona.toString());
                } else if (auxInt >= 9 && auxInt <= 11) { // es opci�n de Socio
                    socio = (Socio) persona;
                    modificarSocio(listaPersonal, socio, auxInt);
                    System.out.println("Datos modificados: \n" + socio.toString());
                } else if (auxInt >= 12 && auxInt <= 14) { // es opci�n de Monitor
                    monitor = (Monitor) persona;
                    modificarMonitor(listaPersonal, copiaListaEspecialidades, monitor, auxInt, Persona.SMI);
                    System.out.println("Datos modificados: \n" + monitor.toString());
                } else { // es opci�n de Empleado
                    empleado = (Empleado) persona;
                    modificarEmpleado(listaPersonal, copiaListaTrabajos, empleado, auxInt, Persona.SMI);
                    System.out.println("Datos modificados: \n" + empleado.toString());
                }
            }
        }
    }

    /**
     * Gestiona las modificaciones espec�ficamente para la clase Persona
     *
     * @param listaPersonal: lista de personal que vamos a modificar si se
     * cumplen las condiciones
     * @param persona: es la persona concreta para la que vamos a modificar un
     * atributo. En este caso es de clase Persona
     * @param auxInt: contiene la opci�n (el atributo) que se va a modificar
     * para la persona
     */
    public static void modificarPersona(ArrayList<Persona> listaPersonal, Persona persona, int auxInt) {
        //modifica los atributos genericos de la clase Persona
        String auxString;
        char auxChar;
        Calendar auxFecha = Calendar.getInstance();
        switch (auxInt) {
            case 1 -> {
                // Direcci�n
                System.out.print("Nueva direcci�n, ");
                auxString = leerString();
                persona.setDireccion(auxString);
            }
            case 2 -> {
                // Localidad
                System.out.print("Nueva localidad, ");
                auxString = leerString();
                persona.setLocalidad(auxString);
            }
            case 3 -> {
                // Provincia
                System.out.print("Nueva provincia, ");
                auxString = leerString();
                persona.setProvincia(auxString);
            }
            case 4 -> {
                // C�digo postal
                System.out.print("Nuevo c�digo postal, ");
                do {
                    auxString = leerString();
                    if (validarCodigoPostal(auxString)) {
                        persona.setCodigoPostal(auxString);
                    } else {
                        System.out.println("Dato inv�lido, introducir uno correcto");
                    }
                } while (!validarCodigoPostal(auxString));
            }
            case 5 -> {
                // Tel�fono
                System.out.print("Nuevo tel�fono, ");
                do {
                    auxString = leerString();
                    if (validarTelefono(auxString)) {
                        persona.setTelefono(auxString);
                    } else {
                        System.out.println("Dato inv�lido, introducir uno correcto");
                    }
                } while (!validarTelefono(auxString));
            }
            case 6 -> {
                // Fecha alta
                System.out.print("Nueva fecha de alta, ");
                do {
                    auxFecha = leerFecha();
                    if (validarFecha(auxFecha.get(Calendar.DATE), auxFecha.get(Calendar.MONTH) + 1, auxFecha.get(Calendar.YEAR))) {
                        persona.setFechaAlta(auxFecha);
                    } else {
                        System.out.println("Dato inv�lido, introducir uno correcto");
                    }
                } while (!validarFecha(auxFecha.get(Calendar.DATE), auxFecha.get(Calendar.MONTH) + 1, auxFecha.get(Calendar.YEAR)));
            }
            case 7 -> {
                // Fecha nacimiento
                System.out.print("Nueva fecha de nacimiento, ");
                do {
                    auxFecha = leerFecha();
                    if (validarFecha(auxFecha.get(Calendar.DATE), auxFecha.get(Calendar.MONTH) + 1, auxFecha.get(Calendar.YEAR))) {
                        if (Persona.getEdad(auxFecha) > 99) {
                            System.out.println("Dato inv�lido, edad mayor de 99 a�os, introducir uno correcto");
                        } else {
                            persona.setFechaNacimiento(auxFecha);
                        }
                    } else {
                        System.out.println("Dato inv�lido, introducir uno correcto");
                    }
                } while ((!validarFecha(auxFecha.get(Calendar.DATE), auxFecha.get(Calendar.MONTH) + 1, auxFecha.get(Calendar.YEAR))) || (Persona.getEdad(auxFecha) > 99));
            }
            case 8 -> {
                // Sexo
                System.out.print("�De verdad quieres cambiar de sexo? �Cu�l quieres (H/M)?  ");
                do {
                    auxChar = leerCaracter();
                    if ((auxChar == 'H' || auxChar == 'M')) {
                        persona.setSexo(auxChar);
                    } else {
                        System.out.println("Dato inv�lido, Debe introducir H o M");
                    }
                } while (auxChar != 'H' && auxChar != 'M');
            }
        }
    }

    /**
     * Gestiona las modificaciones espec�ficamente para la clase Socio
     *
     * @param listaPersonal: lista de personal que vamos a modificar si se
     * cumplen las condiciones
     * @param socio: es la persona concreta para la que vamos a modificar un
     * atributo. En este caso es de clase Socio
     * @param auxInt: contiene la opci�n (el atributo) que se va a modificar
     * para la persona
     */
    public static void modificarSocio(ArrayList<Persona> listaPersonal, Socio socio, int auxInt) {
        //modifica los atributos genericos de la clase Persona
        String auxString;
        char auxChar;
        int entero;
        boolean auxBoolean;
        switch (auxInt) {
            case 9 -> {
                // Sesiones semanales
                System.out.print("Nuevo n�mero de sesiones semanales, entre 2 y 6 ");
                entero = leerEnteroEntreLimites(2, 6); // m�nimo 2, m�ximo 6
                socio.setSesionesSemanales(auxInt);
            }
            case 10 -> {
                // Pagado
                System.out.print("�Pagos al d�a (S/N)? ");
                do {
                    auxChar = leerCaracter();
                    if ((auxChar == 'S' || auxChar == 'N')) {
                        auxBoolean = (auxChar == 'S');
                        socio.setPagado(auxBoolean);
                    } else {
                        System.out.println("Dato inv�lido, Debe introducir S o N");
                    }
                } while (auxChar != 'S' && auxChar != 'N');
            }
            case 11 -> {
                // Lesiones
                System.out.print("Nuevo registro de lesiones, ");
                auxString = leerString();
                socio.setLesiones(auxString);
            }
        }
    }

    /**
     * Gestiona las modificaciones espec�ficamente para la clase Monitor
     *
     * @param listaPersonal: lista de personal que vamos a modificar si se
     * cumplen las condiciones
     * @param listaEspecialidades: lista de las especialidades disponibles. Si
     * vamos a modificar las especialidades de un monitor necesitamos saber
     * cu�les son v�lidas
     * @param monitor: es la persona concreta para la que vamos a modificar un
     * atributo. En este caso es de clase Monitor
     * @param auxInt: contiene la opci�n (el atributo) que se va a modificar
     * para la persona
     * @param smi: contiene el salario m�nimo que podemos definir en el caso de
     * que modifiquemos el salario
     */
    public static void modificarMonitor(ArrayList<Persona> listaPersonal, ArrayList<String> listaEspecialidades, Monitor monitor, int auxInt, double smi) {
        //modifica los atributos genericos de la clase Persona
        float auxFloat;
        char auxChar;
        String[] monitorEspecialidad;
        switch (auxInt) {
            case 12 -> {// Especialidades
                monitorEspecialidad = modificarListaEspecialidadesMonitor(listaEspecialidades);
                monitor.setEspecialidad(monitorEspecialidad);
            }

            case 13 -> {
                // Sueldo
                System.out.print("Nuevo sueldo a asignar ");
                auxFloat = leerFloatMinimo((float) smi); // al menos el Salario M�nimo Interprofesional
                monitor.setSueldo(auxFloat);
            }
            case 14 -> {
                // Activo
                System.out.print("�Monitor activo? (S/N)?  ");
                do {
                    auxChar = leerCaracter();
                    if ((auxChar == 'S' || auxChar == 'N')) {
                        monitor.setActivo(auxChar == 'S');
                    } else {
                        System.out.println("Dato inv�lido, Debe introducir S o N");
                    }
                } while (auxChar != 'S' && auxChar != 'N');
            }
        }
    }

    /**
     * Gestiona las modificaciones espec�ficamente para la clase Empleado
     *
     * @param listaPersonal: lista de personal que vamos a modificar si se
     * cumplen las condiciones
     * @param listaTrabajos: lista de los trabajos disponibles. Si vamos a
     * modificar el trabajo de un monitor necesitamos saber cu�les son v�lidos
     * @param empleado: es la persona concreta para la que vamos a modificar un
     * atributo. En este caso es de clase Empleado
     * @param auxInt: contiene la opci�n (el atributo) que se va a modificar
     * para la persona
     * @param smi: contiene el salario m�nimo que podemos definir en el caso de
     * que modifiquemos el salario
     */
    public static void modificarEmpleado(ArrayList<Persona> listaPersonal, ArrayList<String> listaTrabajos, Empleado empleado, int auxInt, double smi) {
        //modifica los atributos genericos de la clase Persona
        float auxFloat;
        String auxString;
        switch (auxInt) {
            case 15 -> {// Tipo trabajo
                // validar trabajo, debe estar incluido en listaTrabajos
                do {
                    System.out.println("Introducir nuevo trabajo, debe estar registrad en la lista de trabajos del gimnasio: ");
                    System.out.println("Opciones disponibles: ");
                    listarArrayListString(listaTrabajos);
                    auxString = leerString();
                } while (!listaTrabajos.contains(auxString));
                empleado.setTipoTrabajo(auxString);
            }
            case 16 -> { // Sueldo
                System.out.print("Nuevo sueldo a asignar ");
                auxFloat = leerFloatMinimo((float) smi); // al menos el Salario M�nimo Interprofesional
                empleado.setSueldo(auxFloat);
            }
            case 17 -> {
                // Extensi�n
                System.out.print("Nueva extensi�n, ");
                auxString = leerString();
                empleado.setExtension(auxString);
            }
        }
    }

    public static String[] modificarListaEspecialidadesMonitor(ArrayList<String> listaEspecialidades) {
        String auxString;
        String[] auxEspecialidades;
        auxEspecialidades = new String[3]; // usamos un array auxiliar para, si se introducen menos de tres especialidades, evitar introducir nulos
        String[] monitorEspecialidad;
        int i = 0;
        System.out.println("Introducir nuevas especialidades, deben estar registradas en la lista de especialidades del gimnasio (cero para teminar): ");
        do {
            System.out.println("Opciones disponibles: ");
            listarArrayListString(listaEspecialidades);
            auxString = leerString();
            if (listaEspecialidades.contains(auxString)) {
                auxEspecialidades[i] = auxString;
                i++;
                System.out.println("Especialidad registrada, se le asigna al monitor");
            } else if (!auxString.equals("0")) {
                System.out.println("Especialidad no registrada, no se le asigna al monitor");
            }
        } while (i < 3 && !auxString.equals("0"));
        monitorEspecialidad = new String[i];
        // copiamos los valores del array auxiliar
        System.arraycopy(auxEspecialidades, 0, monitorEspecialidad, 0, i);
        return monitorEspecialidad;
    }

    /**
     * Saca un listado de todas las personas existentes
     *
     * @param listaPersonal: lista de personal que vamos a mostrar
     */
    public static void listarPersonas(ArrayList<Persona> listaPersonal) {
        if (listaPersonal.size() > 0) { // si hay contenido lo mostramos
            System.out.println("Contenido = " + listaPersonal.size() + " personas: ");
            for (int i = 0; i < listaPersonal.size(); i++) {
                System.out.println(listaPersonal.get(i).toString());
            }
        } else { // no hay contenido
            System.out.println("No hay datos que mostrar");
        }
    }

    /**
     * Saca un listado de todas las personas existentes de una clase concreta
     *
     * @param listaPersonal: lista de personal que vamos a mostrar
     * @param clase: clase de la cual mostraremos las personas de entre todas
     * las contenidas en la lista
     */
    public static void listarPersonas(ArrayList<Persona> listaPersonal, String clase) {
        if (listaPersonal.size() > 0) { // si hay contenido lo mostramos
            for (int i = 0; i < listaPersonal.size(); i++) {
                if (listaPersonal.get(i).getClass().getSimpleName().equals(clase)) {
                    System.out.println(listaPersonal.get(i).toString());
                }
            }
        } else { // no hay contenido
            System.out.println("No hay datos que mostrar");
        }
    }

    /**
     * Gestiona las listas de datos, tanto para especialidades de monitores como
     * para trabajos de empleados. Gestiona altas, bajas y modificaciones de los
     * valores contenidos en cada una de las listas
     *
     * @param listaEspecialidades: lista de las especialidades para monitores
     * dadas de alta
     * @param listaTrabajos: lista de los trabajos para empleados dados de alta
     * @param tipo: indica si vamos a gestionar especialidades o trabajos
     */
    public static void gestionarListas(ArrayList<String> listaEspecialidades, ArrayList<String> listaTrabajos, int tipo) {
        // tipo = 1: gestiona especialidades
        // tipo = 2: gestiona trabajos
        if (tipo < 1 || tipo > 2) {
            throw new IllegalArgumentException("Opci�n de gesti�n de listas inv�lida");
        }

        ArrayList<String> lista = new ArrayList<>();
        Scanner lectura = new Scanner(System.in);
        int opcion; //Guardaremos la opcion del usuario
        String titulo = "";
        String tituloMin = "";

        switch (tipo) {
            case 1 -> {
                titulo = "ESPECIALIDADES";
                tituloMin = "especialidad";
                lista = listaEspecialidades;
            }
            case 2 -> {
                titulo = "TRABAJOS";
                tituloMin = "trabajo";
                lista = listaTrabajos;
            }
        }

        do {
            System.out.println();
            System.out.println(titulo);
            System.out.println("==============");
            System.out.println("1.Listar");
            System.out.println("2.Altas");
            System.out.println("3.Bajas");
            System.out.println("4.Modificaciones");
            System.out.println("5.Volver");
            System.out.println("Introduzca opci�n: ");

            while (!lectura.hasNextInt()) { //validaci�n de que para la opci�n de men� se introduce un valor num�rico
                lectura.next();
                System.out.println("Debe introducir un valor num�rico entre 1 y 5: ");
            }
            opcion = lectura.nextInt();
            lectura.skip("\n"); //se utiliza para evitar que la pr�xima vez salte la l�nea al interpretar el enter de la lectura anterior

            switch (opcion) {
                case 1 -> // listar
                    listarArrayListString(lista);
                case 2 -> // altas
                    gestionarLista(lista, 1, "Nuevo valor para " + tituloMin + ", ");
                case 3 -> // bajas
                    gestionarLista(lista, 2, "Valor a  eliminar de " + tituloMin + ", ");
                case 4 -> // modificaciones
                    gestionarLista(lista, 3, "Valor a modificar para " + tituloMin + ", ");
                case 5 -> {
                }
            }
        } while (opcion != 5);
    }

    /**
     * Para una lista pasada por par�metro muestra sus valores, llama a la
     * funci�n correspondiente seg�n queramos dar un alta, baja o modificaci�n,
     * y vuelve a mostrar los valores actualizados
     *
     * @param lista: lista que vamos a gestionar
     * @param tipo: indica si vamos a dar de alta, de baja o a modificar
     * @param texto: texto a mostrar antes de llamar a la operaci�n seleccionada
     * en el par�metro tipo
     */
    public static void gestionarLista(ArrayList<String> lista, int tipo, String texto) {
        listarArrayListString(lista, "Valores actuales:");
        System.out.print(texto);
        altaBajaArrayListString(lista, tipo);
        listarArrayListString(lista, "Valores actualizados:");
    }

    /**
     * Lista el contenido de un arrayList de cadenas
     *
     * @param lista: lista cuyo contenido vamos a listar
     */
    public static void listarArrayListString(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) != null) {
                System.out.println("- " + lista.get(i));
            }
        }
    }

    /**
     * Lista el contenido de un arrayList de cadenas mostrando previamente un
     * texto
     *
     * @param lista: lista cuyo contenido vamos a listar
     * @param texto: texto que se mostrar� antes de listar el contenido del
     * arrayList
     */
    public static void listarArrayListString(ArrayList<String> lista, String texto) {
        System.out.println(texto);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    /**
     * Gestiona el alta, baja o modificaci�n de elementos en un arrayList
     *
     * @param lista: arrayList que vamos a gestionar (alta, baja o modificaci�n
     * de los elementos que contiene)
     * @param tipo: tipo de operaci�n que realizaremos sobre el arrayList (1 =
     * alta, 2 = baja, 3 = modificaci�n)
     */
    public static void altaBajaArrayListString(ArrayList<String> lista, int tipo) {
        // tipo = 1: alta
        // tipo = 2: baja
        // tipo = 3: modificaci�n
        String auxString, backup;
        boolean existe = false;
        auxString = leerString();
        int indice = -1;
        int i = 0;
        do {
            if (lista.get(i).equalsIgnoreCase(auxString)) {
                existe = true;
                indice = i;
            }
            i++;
        } while (i < lista.size() && !existe);
        switch (tipo) {
            case 1 -> {
                // alta
                if (existe) {
                    System.out.println("El valor ya existe, no se puede dar de alta");
                } else {
                    lista.add(auxString);
                }
            }
            case 2 -> {
                // baja
                if (existe) {
                    lista.remove(indice);
                } else {
                    System.out.println("El valor no existe, no se puede dar de baja");
                }
            }
            case 3 -> {
                // modificar
                if (existe) { // lo modificamos
                    // verificamos si el nuevo valor no est� repetido
                    backup = lista.get(indice); // guardamos el valor por si debemos restaurarlo
                    lista.remove(indice);
                    i = 0;
                    existe = false;
                    System.out.println("Introduce nuevo valor");
                    auxString = leerString();
                    do {
                        if (lista.get(i).equalsIgnoreCase(auxString)) {
                            existe = true;
                            System.out.println("Ese valor ya existe, no se realiza la modificaci�n");
                        }
                        i++;
                    } while (i < lista.size() && !existe);
                    if (!existe) { // modificamos
                        lista.add(auxString);
                    } else { // restauramos el valor
                        lista.add(backup); // restauramos el valor que hab�amos borrado
                    }
                } else { // si no existe no se puede modificar
                    System.out.println("El valor no existe, no se puede modificar");
                }
            }
        }
    }

    /**
     * Lee la clase espec�fica que queremos seleccionar para un objeto de tipo
     * Persona (Socio, Monitor o Empleado)
     *
     * @return devuelve un car�cter con la inicial de la elegida
     */
    public static char leerTipoPersona() {
        boolean primero;
        char tipoPersona;
        //leer tipo de persona (socio, monitor o empleado)
        primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir S (socio), M (monitor) o E (empleado)");
            }
            System.out.println("�Se trata de un Socio, un Monitor o un Empleado (S/M/E)? ");
            tipoPersona = leerCaracter();
            primero = false;
        } while (tipoPersona != 'S' && tipoPersona != 'M' && tipoPersona != 'E');
        return tipoPersona;
    }

    /**
     * Pide al usuario una cadena de texto
     *
     * @return devuelve la cadena le�da por teclado
     */
    public static String leerString() {
        Scanner teclado = new Scanner(System.in);
        String lectura;
        System.out.println("Introduzca un dato:");
        lectura = teclado.nextLine();
        return lectura;
    }

    /**
     * Pide al usuario una fecha
     *
     * @return devuelve la fecha le�da por teclado
     */
    public static Calendar leerFecha() {
        Calendar fecha = Calendar.getInstance();
        int dia, mes, a�o;
        boolean primero = true;
        do {
            if (!primero) {
                System.out.println("Debe introducir una fecha v�lida");
            }
            // leemos d�a
            dia = leerEntero("d�a");
            // leemos mes
            mes = leerEntero("mes");
            // leemos a�o
            a�o = leerEntero("a�o");
            primero = false;
        } while (!validarFecha(dia, mes, a�o));
        // al guardar la fecha, el formato usado por Calendar empieza por cero (enero = 0, diciembre = 11)
        fecha.set(a�o, mes, dia);
        return fecha;
    }

    /**
     * Lee un entero mostrando el tipo de dato que queremos leer:
     *
     * @param texto: al pedir el entero mostrar� el texto como el tipo de dato
     * esperado
     * @return devuelve un entero
     */
    public static int leerEntero(String texto) {
        Scanner teclado = new Scanner(System.in);
        int lectura;
        System.out.println("Introduzca " + texto + ": ");
        while (!teclado.hasNextInt()) {
            teclado.next(); //si lo que se ha introducido no es un n�mero lo sacamos del buffer
            System.out.println("El valor introducido no es un n�mero de " + texto + ", vuelva a intentarlo:");
        }
        lectura = teclado.nextInt();
        return lectura;
    }

    /**
     * Lee un entero y valida que se encuentra entre los dos l�mites pasados por
     * par�metro
     *
     * @param inferior: valor inferior. Se validar� que el n�mero introducido no
     * sea menor que este valor
     * @param superior: valor superior. Se validar� que el n�mero introducido no
     * sea mayor que este valor esperado
     * @return devuelve un entero entre los dos l�mites pasados como par�metros
     */
    public static int leerEnteroEntreLimites(int inferior, int superior) {
        int num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un entero positivo entre " + inferior + " y " + superior);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.println("Introduce un entero");
            }
            num = teclado.nextInt();
        } while (num < inferior || num > superior);
        return num;
    }

    /**
     * Lee un float y valida que es mayor o igual que el par�metro facilitado
     *
     * @param minimo: valor m�nimo que debe tener el float introducido por
     * teclado
     * @return devuelve un float igual o mayor que el par�metro facilitado
     */
    public static float leerFloatMinimo(float minimo) {
        float num = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Introduce un n�mero igual o mayor que " + minimo);
            while (!teclado.hasNextFloat()) {
                teclado.next();
                System.out.println("Introduce un valor");
            }
            num = teclado.nextFloat();
        } while (num < minimo);
        return num;
    }

    /**
     * Lee un car�cter. En el caso de que
     *
     * @return devuelve un car�cterse introduzca un string se toma solo el
     * primer car�cter. Devuelve siempre el car�cter en may�sculas
     */
    public static char leerCaracter() {
        char caracter;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca un car�cter: ");
        caracter = teclado.next().charAt(0);
        if (Character.isLowerCase(caracter)) { //convertimos a may�sculas
            caracter = Character.toUpperCase(caracter);
        }
        return caracter;
    }

    /**
     * Verifica si el dni facilitado como par�metro se encuentra en la lista
     *
     * @param dni: dni el cual vamos a verificar si se encuentra en la lista
     * pasada como par�metro
     * @param listaPersonal: lista en la cual vamos a verificar si se encuentra
     * el dni. Se trata de una lista de objetos tipo Persona, el dni es un campo
     * de cada objeto
     * @return devuelve el entero correspondiente al �ndice igual a la posici�n
     * en la que el dni se encuentra en la lista de personas, -1 en caso de no
     * encontrarse
     */
    public static int existeDni(String dni, ArrayList<Persona> listaPersonal) {
        int resultado = -1;
        if (!validarDni(dni)) {
            System.out.println("El DNI no es v�lido");
        } else {
            if (listaPersonal.size() > 0) {
                int i = 0;
                do {
                    if (dni.equalsIgnoreCase(listaPersonal.get(i).getDni())) {
                        resultado = i;
                    }
                    i++;
                } while (resultado == -1 && i < listaPersonal.size());
            }
        }
        return resultado;
    }

    public static ArrayList<String> copiaArrayListString(ArrayList<String> lista) {
        ArrayList<String> copiaLista;
        copiaLista = new ArrayList<>();
        if (lista.size() > 0) {
            for (int i = 1; i < lista.size(); i++) {
                copiaLista.add(lista.get(i));
            }
        }
        return lista;
    }

}
