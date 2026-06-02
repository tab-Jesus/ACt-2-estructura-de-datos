package models;

public class TicketSoporte {

    private String codigoTicket;
    private String nombreUsuario;
    private String descripcionFalla;
    private String prioridad;
    private String estado;

    public TicketSoporte(String codigoTicket, String nombreUsuario,
                         String descripcionFalla, String prioridad) {
        if (codigoTicket == null || codigoTicket.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del ticket no puede estar vacío.");
        }
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        }
        if (descripcionFalla == null || descripcionFalla.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la falla no puede estar vacía.");
        }
        if (!prioridad.equalsIgnoreCase("Alta") &&
            !prioridad.equalsIgnoreCase("Media") &&
            !prioridad.equalsIgnoreCase("Baja")) {
            throw new IllegalArgumentException("Prioridad inválida. Use: Alta, Media o Baja.");
        }
        this.codigoTicket = codigoTicket.toUpperCase().trim();
        this.nombreUsuario = nombreUsuario.trim();
        this.descripcionFalla = descripcionFalla.trim();
        this.prioridad = prioridad;
        this.estado = "PENDIENTE";
    }

    public String getCodigoTicket() {
        return codigoTicket;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigoTicket(String codigoTicket) {
        this.codigoTicket = codigoTicket.toUpperCase().trim();
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario.trim();
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla.trim();
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ticket[" + codigoTicket + "] " +
               "| Usuario: " + nombreUsuario +
               " | Falla: " + descripcionFalla +
               " | Prioridad: " + prioridad +
               " | Estado: " + estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TicketSoporte otro = (TicketSoporte) obj;
        return this.codigoTicket.equalsIgnoreCase(otro.codigoTicket);
    }

    @Override
    public int hashCode() {
        return codigoTicket.toUpperCase().hashCode();
    }
}
