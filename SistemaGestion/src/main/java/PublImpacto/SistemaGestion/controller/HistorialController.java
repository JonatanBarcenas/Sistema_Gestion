package PublImpacto.SistemaGestion.controller;

import java.time.LocalDateTime;

import PublImpacto.SistemaGestion.dao.RegistroActividadDAO;
import PublImpacto.SistemaGestion.model.RegistroActividad;

public class HistorialController {
	
	private RegistroActividadDAO registroActividadDAO;

    public HistorialController() {
        this.registroActividadDAO = new RegistroActividadDAO();
    }

    public boolean createRegistroActividad(String accion, int usuarioId, String detalles) {
        RegistroActividad actividad = new RegistroActividad(0, accion, LocalDateTime.now(), usuarioId, detalles);
        return registroActividadDAO.createRegistroActividad(actividad);
    }

}
