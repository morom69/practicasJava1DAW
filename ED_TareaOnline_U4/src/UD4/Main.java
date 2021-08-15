package UD4;

/**
 *
 * @author Miguel Moro
 * @version 2.0
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Variables:        
        Moto miMoto;
        int stockActual = 1000;
        int cantidadAcomprar = 100;

        //Establezco la cantidad del stock
        miMoto = new Moto("Ducati", "rojo", stockActual);

        comprar_motos(miMoto, cantidadAcomprar);

        //SE VISUALIZA RESULTADO       
        //-Se muestra resultado ...          
        System.out.println("\n- El stock actual de motos es: " + miMoto.getStock());
    }

    /**
     * El método comprar_motos realiza una compra de motos mediante la llamada
     * al método comprar(), pasando por parámetro la cantidad que a su vez es
     * recibida también por parámetro en el propio método comprar_motos() y a
     * continuación indica por pantalla el número de motos que se han comprado
     *
     * @param miMoto: objeto de clase Moto para la cual se realiza la compra
     * @param cantidad: cantidad de motos que se van a comprar
     * @return: no se utiliza return al devolver el método void
     */
    public static void comprar_motos(Moto miMoto, int cantidad) {
        //SE REALIZA COMPRA DE MOTOS
        //-Proceso de compra de motos...
        try {
            System.out.println("- Compra de motos:");
            miMoto.comprar(cantidad);
            System.out.println("\tSe han comprado: " + cantidad + " motos");
        } catch (Exception e) {
            System.out.println("\t" + e);
        }
    }
}
