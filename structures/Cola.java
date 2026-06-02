package structures;

public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

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

    public Object peek() {
        if (frente == null) {
            throw new RuntimeException("La cola está vacía.");
        }
        return frente.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

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

    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

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
