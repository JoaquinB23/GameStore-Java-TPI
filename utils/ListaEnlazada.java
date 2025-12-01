package utils;

public class ListaEnlazada<T> {

    // ----- NODO INTERNO -----
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza; // primer nodo

    // ----- AGREGAR AL FINAL -----
    public void agregar(T dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {       // lista vacía
            cabeza = nuevo;
            return;
        }

        Nodo aux = cabeza;
        while (aux.siguiente != null) {
            aux = aux.siguiente;
        }
        aux.siguiente = nuevo;
    }

    // ----- ELIMINAR POR ÍNDICE -----
    public boolean eliminar(int indice) {
        if (cabeza == null || indice < 0) return false;

        // eliminar cabeza
        if (indice == 0) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo actual = cabeza;
        Nodo anterior = null;
        int i = 0;

        while (actual != null && i < indice) {
            anterior = actual;
            actual = actual.siguiente;
            i++;
        }

        if (actual == null) return false; // índice fuera de rango

        anterior.siguiente = actual.siguiente;
        return true;
    }

    // ----- MOSTRAR LISTA -----
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("(Historial vacío)");
            return;
        }

        Nodo aux = cabeza;
        int i = 0;

        System.out.println("\n=== HISTORIAL ===");
        while (aux != null) {
            System.out.println(i + " -> " + aux.dato);
            aux = aux.siguiente;
            i++;
        }
        System.out.println();
    }
}
