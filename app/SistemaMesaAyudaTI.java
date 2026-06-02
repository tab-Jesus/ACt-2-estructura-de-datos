package app;

import models.TicketSoporte;
import structures.Cola;
import structures.Lista;
import structures.Pila;

import java.util.Scanner;

public class SistemaMesaAyudaTI {

    private static Lista  listaGeneral      = new Lista();
    private static Cola   colaPendientes    = new Cola();
    private static Pila   historialProcesados = new Pila();
    private static Scanner scanner = new Scanner(System.in);
    private static int contadorTickets = 1;

    public static void main(String[] args) {
        boolean salir = false;
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE MESA DE AYUDA TI              ║");
        System.out.println("║     Gestión de Tickets de Soporte Técnico    ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:  registrarTicket();          break;
                case 2:  verTodosLosTickets();       break;
                case 3:  verPendientes();            break;
                case 4:  procesarSiguiente();        break;
                case 5:  verHistorial();             break;
                case 6:  buscarPorCodigo();          break;
                case 7:  cancelarPendiente();        break;
                case 8:  deshacerProcesamiento();    break;
                case 9:  verCantidades();            break;
                case 10: salir = true;               break;
                default:
                    System.out.println("  ✖ Opción inválida. Ingrese un número del 1 al 10.");
            }
        }
        System.out.println("\n  Sistema cerrado. ¡Hasta pronto!");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n══════════════ MENÚ PRINCIPAL ════════════════");
        System.out.println("  1. Registrar ticket de soporte");
        System.out.println("  2. Ver todos los tickets registrados");
        System.out.println("  3. Ver tickets pendientes");
        System.out.println("  4. Procesar siguiente ticket pendiente");
        System.out.println("  5. Ver historial de tickets procesados");
        System.out.println("  6. Buscar ticket por código");
        System.out.println("  7. Cancelar ticket pendiente");
        System.out.println("  8. Deshacer último procesamiento");
        System.out.println("  9. Ver cantidad de tickets");
        System.out.println("  10. Salir");
        System.out.println("══════════════════════════════════════════════");
    }

    private static void registrarTicket() {
        System.out.println("\n── Registrar nuevo ticket ──");
        String codigo = "TKT-" + String.format("%04d", contadorTickets);
        System.out.println("  Código asignado automáticamente: " + codigo);

        System.out.print("  Nombre del usuario: ");
        String usuario = scanner.nextLine().trim();
        if (usuario.isEmpty()) {
            System.out.println("  ✖ El nombre del usuario no puede estar vacío.");
            return;
        }

        System.out.print("  Descripción de la falla: ");
        String falla = scanner.nextLine().trim();
        if (falla.isEmpty()) {
            System.out.println("  ✖ La descripción no puede estar vacía.");
            return;
        }

        System.out.print("  Prioridad (Alta / Media / Baja): ");
        String prioridad = scanner.nextLine().trim();

        try {
            TicketSoporte ticket = new TicketSoporte(codigo, usuario, falla, prioridad);

            listaGeneral.agregar(ticket);
            colaPendientes.encolar(ticket);
            contadorTickets++;

            System.out.println("  ✔ Ticket registrado exitosamente.");
            System.out.println("  " + ticket);
        } catch (IllegalArgumentException e) {
            System.out.println("  ✖ Error al registrar: " + e.getMessage());
        }
    }

    private static void verTodosLosTickets() {
        System.out.println("\n── Todos los tickets registrados (Lista General) ──");
        if (listaGeneral.esVacia()) {
            System.out.println("  [No hay tickets registrados aún]");
            return;
        }
        listaGeneral.mostrarAdelante();
    }

    private static void verPendientes() {
        System.out.println("\n── Tickets pendientes de atención (Cola FIFO) ──");
        colaPendientes.mostrar();
    }

    private static void procesarSiguiente() {
        System.out.println("\n── Procesar siguiente ticket ──");
        if (colaPendientes.esVacia()) {
            System.out.println("  ✖ No hay tickets pendientes para procesar.");
            return;
        }
        TicketSoporte procesado = (TicketSoporte) colaPendientes.desencolar();
        procesado.setEstado("PROCESADO");
        historialProcesados.apilar(procesado);

        actualizarEstadoEnLista(procesado);

        System.out.println("  ✔ Ticket procesado correctamente:");
        System.out.println("  " + procesado);
    }

    private static void verHistorial() {
        System.out.println("\n── Historial de tickets procesados (Pila LIFO) ──");
        historialProcesados.mostrar();
    }

    private static void buscarPorCodigo() {
        System.out.println("\n── Buscar ticket por código ──");
        System.out.print("  Ingrese el código del ticket (ej. TKT-0001): ");
        String codigo = scanner.nextLine().trim().toUpperCase();
        if (codigo.isEmpty()) {
            System.out.println("  ✖ Debe ingresar un código.");
            return;
        }
        TicketSoporte buscador = new TicketSoporte(codigo, "x", "x", "Baja");
        Object encontrado = listaGeneral.buscarDato(buscador);
        if (encontrado != null) {
            System.out.println("  ✔ Ticket encontrado:");
            System.out.println("  " + encontrado);
        } else {
            System.out.println("  ✖ No se encontró ningún ticket con código: " + codigo);
        }
    }

    private static void cancelarPendiente() {
        System.out.println("\n── Cancelar ticket pendiente ──");
        if (colaPendientes.esVacia()) {
            System.out.println("  ✖ La cola de pendientes está vacía.");
            return;
        }
        System.out.print("  Ingrese el código del ticket a cancelar (ej. TKT-0001): ");
        String codigo = scanner.nextLine().trim().toUpperCase();
        if (codigo.isEmpty()) {
            System.out.println("  ✖ Debe ingresar un código.");
            return;
        }

        Cola colaAuxiliar = new Cola();
        boolean encontrado = false;
        TicketSoporte cancelado = null;

        while (!colaPendientes.esVacia()) {
            TicketSoporte actual = (TicketSoporte) colaPendientes.desencolar();
            if (actual.getCodigoTicket().equalsIgnoreCase(codigo) && !encontrado) {
                encontrado = true;
                cancelado = actual;
            } else {
                colaAuxiliar.encolar(actual);
            }
        }

        while (!colaAuxiliar.esVacia()) {
            colaPendientes.encolar(colaAuxiliar.desencolar());
        }

        if (encontrado) {
            cancelado.setEstado("CANCELADO");
            actualizarEstadoEnLista(cancelado);
            System.out.println("  ✔ Ticket cancelado correctamente:");
            System.out.println("  " + cancelado);
        } else {
            System.out.println("  ✖ No se encontró el ticket '" + codigo + "' en la cola de pendientes.");
        }
    }

    private static void deshacerProcesamiento() {
        System.out.println("\n── Deshacer último procesamiento ──");
        if (historialProcesados.esVacia()) {
            System.out.println("  ✖ El historial está vacío. No hay operaciones que deshacer.");
            return;
        }
        TicketSoporte ultimo = (TicketSoporte) historialProcesados.desapilar();
        ultimo.setEstado("PENDIENTE");
        colaPendientes.encolar(ultimo);
        actualizarEstadoEnLista(ultimo);

        System.out.println("  ✔ Procesamiento deshecho. El ticket volvió a la cola de pendientes:");
        System.out.println("  " + ultimo);
    }

    private static void verCantidades() {
        System.out.println("\n── Cantidades de tickets en el sistema ──");
        System.out.println("  Total registrados (Lista):      " + listaGeneral.cuentaElementos());
        System.out.println("  Pendientes (Cola):              " + colaPendientes.tamanio());
        System.out.println("  Procesados en historial (Pila): " + historialProcesados.tamanio());
    }

    private static void actualizarEstadoEnLista(TicketSoporte ticket) {

    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            String linea = scanner.nextLine().trim();
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1; // Valor inválido para que el switch caiga en default
        }
    }
}
