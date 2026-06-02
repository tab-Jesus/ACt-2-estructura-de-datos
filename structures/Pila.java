package structures;

/**
 * Clase Pila - Estructura LIFO (Last In, First Out) basada en nodos enlazados.
 * Almacena el historial de tickets procesados.
 * El último ticket procesado es el primero en poder deshacerse.
 * Tope = elemento más reciente (cima de la pila).
 */
public class Pila {
    private Nodo tope;   // Elemento más recientemente apilado
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    /**
     * Apila un elemento en la cima (LIFO).
     */
    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamanio++;
    }

    /**
     * Desapila y retorna el elemento de la cima.
     */
    public Object desapilar() {
        if (tope == null) {
            throw new RuntimeException("El historial está vacío. No hay operaciones que deshacer.");
        }
        Object dato = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return dato;
    }

    /**
     * Consulta el elemento en la cima sin eliminarlo.
     */
    public Object peek() {
        if (tope == null) {
            throw new RuntimeException("La pila está vacía.");
        }
        return tope.getDato();
    }

    /**
     * Retorna la cantidad de elementos en la pila.
     */
    public int tamanio() {
        return tamanio;
    }

    /**
     * Verifica si la pila está vacía.
     */
    public boolean esVacia() {
        return tamanio == 0;
    }

    /**
     * Verifica si la pila contiene el elemento dado.
     */
    public boolean contiene(Object dato) {
        Nodo actual = tope;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Limpia completamente la pila.
     */
    public void limpiar() {
        tope = null;
        tamanio = 0;
    }

    /**
     * Muestra todos los elementos (tope → base).
     */
    public void mostrar() {
        if (tope == null) {
            System.out.println("  [Historial vacío]");
            return;
        }
        Nodo actual = tope;
        int pos = 1;
        while (actual != null) {
            System.out.println("  [" + pos + "] " + actual.getDato());
            actual = actual.getSiguiente();
            pos++;
        }
    }

    /**
     * Busca un elemento en la pila. Retorna su posición (1=tope) o -1 si no existe.
     */
    public int buscar(Object dato) {
        Nodo actual = tope;
        int pos = 1;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return pos;
            }
            actual = actual.getSiguiente();
            pos++;
        }
        return -1;
    }
}
