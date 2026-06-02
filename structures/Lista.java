package structures;

public class Lista {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public void agregar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        } 
        tamanio++;
    }

    public void agregarAlInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void agregarEnPosicion(int indice, Object dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        if (indice == tamanio) {
            agregar(dato);
            return;
        }
        Nodo nuevo = new Nodo(dato);
        Nodo actual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.getSiguiente();
        }
        Nodo siguiente = actual.getSiguiente();
        nuevo.setAnterior(actual);
        nuevo.setSiguiente(siguiente);
        actual.setSiguiente(nuevo);
        if (siguiente != null) {
            siguiente.setAnterior(nuevo);
        }
        tamanio++;
    }

    public Object eliminarPrimero() {
        if (cabeza == null) {
            throw new RuntimeException("La lista está vacía.");
        }
        Object dato = cabeza.getDato();
        cabeza = cabeza.getSiguiente();
        if (cabeza != null) {
            cabeza.setAnterior(null);
        } else {
            cola = null;
        }
        tamanio--;
        return dato;
    }

    public Object eliminarUltimo() {
        if (cola == null) {
            throw new RuntimeException("La lista está vacía.");
        }
        Object dato = cola.getDato();
        cola = cola.getAnterior();
        if (cola != null) {
            cola.setSiguiente(null);
        } else {
            cabeza = null;
        }
        tamanio--;
        return dato;
    }

    public Object eliminarEnPosicion(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        if (indice == 0) return eliminarPrimero();
        if (indice == tamanio - 1) return eliminarUltimo();

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        Object dato = actual.getDato();
        actual.getAnterior().setSiguiente(actual.getSiguiente());
        actual.getSiguiente().setAnterior(actual.getAnterior());
        tamanio--;
        return dato;
    }

    public Object buscarDato(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public Object buscarDato(Object dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    public int cuentaElementos() {
        return tamanio;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void mostrarAdelante() {
        if (cabeza == null) {
            System.out.println("  [Lista vacía]");
            return;
        }
        Nodo actual = cabeza;
        int i = 1;
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.getDato());
            actual = actual.getSiguiente();
            i++;
        }
    }

    public void mostrarAtras() {
        if (cola == null) {
            System.out.println("  [Lista vacía]");
            return;
        }
        Nodo actual = cola;
        int i = tamanio;
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.getDato());
            actual = actual.getAnterior();
            i--;
        }
    }

    public boolean esVacia() {
        return tamanio == 0;
    }
}
