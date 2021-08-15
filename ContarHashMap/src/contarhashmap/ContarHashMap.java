package contarhashmap;

import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author Miguel Moro 
 * Generar una lista aleatoria de 20 números y cargarlos
 * luego en un mapa indicando como parejas cada número y veces que se repite en
 * la lista
 */
public class ContarHashMap {

    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>(); // ArrayList donde guardamos los números generados aleatoriamente
        HashMap<Integer, Integer> mapa = new HashMap<>(); // Mapa donde guardamos los pares <número><número de veces que aparece>
        generarLista(lista, 20);
        mostrarLista(lista);
        cargarMapaConLista(lista, mapa);
        System.out.println("Mapa: " + mapa);
    }

    public static void generarLista(ArrayList<Integer> lista, int elementos) {
        if (elementos > 0) {
            for (int i = 0; i < elementos; i++) {
                lista.add((int) (Math.random() * 10));
            }
        } else {
            System.out.println("No se puede generar una lista para " + elementos + " elementos");
        }
    }

    public static void mostrarLista(ArrayList<Integer> lista) {
        if (lista.size() > 0) {
            int i = 0;
            System.out.print("Lista: {");
            do {
                System.out.print(lista.get(i) + "-");
                i++;
            } while (i < lista.size() - 1);
            System.out.print(lista.get(i));
            System.out.println("}");
        } else {
            System.out.println("La lista está vacía, no se puede mostrar");
        }
    }

    public static void cargarMapaConLista(ArrayList<Integer> lista, HashMap<Integer, Integer> mapa) {
        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                if (!mapa.containsKey(lista.get(i))) {
                    mapa.put(lista.get(i), 1);
                } else {/*
                    for (HashMap.Entry<Integer, Integer> entry : mapa.entrySet()) {
                        if (entry.getKey() == lista.get(i)) {
                            mapa.put(lista.get(i), entry.getValue() + 1);
                        } 
                    }*/
                    mapa.put(lista.get(i), mapa.get(lista.get(i)) + 1);
                }
            }
        } else {
            System.out.println("La lista está vacía, no se puede cargar el mapa");
        }
    }
}
