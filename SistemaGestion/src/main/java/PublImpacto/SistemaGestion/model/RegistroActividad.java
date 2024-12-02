package PublImpacto.SistemaGestion.model;

import java.time.LocalDateTime;

public class RegistroActividad {
	private int id;
    private String accion;
    private LocalDateTime fechaHora;
    private int usuarioId;
    private String detalles;

    public RegistroActividad(int id, String accion, LocalDateTime fechaHora, int usuarioId, String detalles) {
        this.id = id;
        this.accion = accion;
        this.fechaHora = fechaHora;
        this.usuarioId = usuarioId;
        this.detalles = detalles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
