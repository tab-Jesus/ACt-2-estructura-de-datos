package structures;

/**
 * Clase Lista - Lista doblemente enlazada genérica.
 * Permite gestionar una colección de elementos mediante nodos enlazados.
 * Cada nodo apunta al siguiente y al anterior, lo que permite recorrido
 * en ambas direcciones.
 */
public class Lista {
    private Nodo cabeza;   // Primer nodo de la lista
    private Nodo cola;     // Último nodo de la lista
    private int tamanio;

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     */
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

    /**
     * Agrega un elemento al inicio de la lista.
     */
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

    /**
     * Agrega un elemento en una posición específica (base 0).
     */
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

    /**
     * Elimina y retorna el primer elemento de la lista.
     */
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

    /**
     * Elimina y retorna el último elemento de la lista.
     */
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

    /**
     * Elimina el elemento en la posición indicada (base 0).
     */
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

    /**
     * Retorna el dato en la posición indicada (base 0).
     */
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

    /**
     * Busca un objeto en la lista por igualdad (equals).
     * Retorna el dato si lo encuentra, null si no.
     */
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

    /**
     * Verifica si la lista contiene el objeto dado.
     */
    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    /**
     * Retorna la cantidad de elementos en la lista.
     */
    public int cuentaElementos() {
        return tamanio;
    }

    /**
     * Limpia completamente la lista.
     */
    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Muestra los elementos de adelante hacia atrás (inicio → fin).
     */
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

    /**
     * Muestra los elementos de atrás hacia adelante (fin → inicio).
     */
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
