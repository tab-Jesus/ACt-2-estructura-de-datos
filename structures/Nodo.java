package structures;

/**
 * Clase Nodo - Nodo doblemente enlazado genérico.
 * Almacena un dato y referencias al nodo anterior y siguiente.
 */
public class Nodo {
    private Object dato;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(Object dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}
