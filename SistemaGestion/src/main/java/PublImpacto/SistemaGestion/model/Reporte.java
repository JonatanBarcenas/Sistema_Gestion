package PublImpacto.SistemaGestion.model;

import java.time.LocalDateTime;

public class Reporte {
	
	private int id;
    private String tipo;
    private LocalDateTime fechaCreacion;
    private String contenido;

    public Reporte(int id, String tipo, LocalDateTime fechaCreacion, String contenido) {
        this.id = id;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
