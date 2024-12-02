package PublImpacto.SistemaGestion.model;

import java.time.LocalDateTime;

public class Comentario {
	
	private int id;
    private String contenido;
    private LocalDateTime fecha;
    private int usuarioId;
    private int pedidoId;

    public Comentario(int id, String contenido, LocalDateTime fecha, int usuarioId, int pedidoId) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
        this.pedidoId = pedidoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

}
