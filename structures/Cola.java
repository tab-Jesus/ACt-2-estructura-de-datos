package structures;

/**
 * Clase Cola - Estructura FIFO (First In, First Out) basada en nodos enlazados.
 * El primer ticket en entrar es el primero en ser atendido.
 * Frente = primer elemento a ser procesado.
 * Final  = último elemento encolado.
 */
public class Cola {
    private Nodo frente;   // Nodo a desencolar (más antiguo)
    private Nodo fin;      // Nodo recién encolado (más nuevo)
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un elemento al final de la cola (FIFO).
     */
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    /**
     * Elimina y retorna el elemento del frente de la cola.
     */
    public Object desencolar() {
        if (frente == null) {
            throw new RuntimeException("La cola de pendientes está vacía.");
        }
        Object dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    /**
     * Consulta el elemento del frente sin eliminarlo.
     */
    public Object peek() {
        if (frente == null) {
            throw new RuntimeException("La cola está vacía.");
        }
        return frente.getDato();
    }

    /**
     * Retorna la cantidad de elementos en la cola.
     */
    public int tamanio() {
        return tamanio;
    }

    /**
     * Verifica si la cola está vacía.
     */
    public boolean esVacia() {
        return tamanio == 0;
    }

    /**
     * Verifica si la cola contiene el elemento dado.
     */
    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Limpia completamente la cola.
     */
    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    /**
     * Muestra todos los elementos de la cola (frente → fin).
     */
    public void mostrar() {
        if (frente == null) {
            System.out.println("  [Cola vacía — no hay tickets pendientes]");
            return;
        }
        Nodo actual = frente;
        int pos = 1;
        while (actual != null) {
            System.out.println("  [" + pos + "] " + actual.getDato());
            actual = actual.getSiguiente();
            pos++;
        }
    }
}
