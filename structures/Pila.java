package structures;

public class Pila {
    private Nodo tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }
    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(tope);
        tope = nuevo;
         tamanio++;
    }

    public Object desapilar() {
        if (tope == null) {
            throw new RuntimeException("El historial está vacío. No hay operaciones que deshacer.");
        }
        Object dato = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return dato;
    }

    public Object peek() {
        if (tope == null) {
            throw new RuntimeException("La pila está vacía.");
        }
        return tope.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

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

    public void limpiar() {
        tope = null;
        tamanio = 0;
    }

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
