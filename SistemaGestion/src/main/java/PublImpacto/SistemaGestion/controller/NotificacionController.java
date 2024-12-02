package PublImpacto.SistemaGestion.controller;

import java.time.LocalDateTime;
import java.util.List;

import PublImpacto.SistemaGestion.dao.NotificacionDAO;
import PublImpacto.SistemaGestion.model.Notificacion;

public class NotificacionController {
	
	private NotificacionDAO notificacionDAO;

    public NotificacionController() {
        this.notificacionDAO = new NotificacionDAO();
    }

    public boolean createNotificacion(String titulo, String mensaje, String tipo, int usuarioId) {
        Notificacion notificacion = new Notificacion(0, titulo, mensaje, LocalDateTime.now(), tipo, false, usuarioId);
        return notificacionDAO.createNotificacion(notificacion);
    }

    public List<Notificacion> getNotificacionesByUsuario(int usuarioId) {
        return notificacionDAO.getNotificacionesByUsuario(usuarioId);
    }

    public boolean markAsRead(int id) {
        return notificacionDAO.markAsRead(id);
    }

}
