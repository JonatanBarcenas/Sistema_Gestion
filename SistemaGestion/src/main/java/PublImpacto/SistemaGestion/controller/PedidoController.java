package PublImpacto.SistemaGestion.controller;

import java.time.LocalDateTime;
import java.util.List;

import PublImpacto.SistemaGestion.dao.ArchivoDAO;
import PublImpacto.SistemaGestion.dao.ComentarioDAO;
import PublImpacto.SistemaGestion.dao.EstadoDAO;
import PublImpacto.SistemaGestion.dao.NotificacionDAO;
import PublImpacto.SistemaGestion.dao.PedidoDAO;
import PublImpacto.SistemaGestion.dao.PrioridadDAO;
import PublImpacto.SistemaGestion.model.Archivo;
import PublImpacto.SistemaGestion.model.Comentario;
import PublImpacto.SistemaGestion.model.Pedido;

public class PedidoController {
	
	private PedidoDAO pedidoDAO;
    private ComentarioDAO comentarioDAO;
    private ArchivoDAO archivoDAO;
    private EstadoDAO estadoDAO;
    private PrioridadDAO prioridadDAO;
    private NotificacionDAO notificacionDAO;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAO();
        this.comentarioDAO = new ComentarioDAO();
        this.archivoDAO = new ArchivoDAO();
        this.estadoDAO = new EstadoDAO();
        this.prioridadDAO = new PrioridadDAO();
        this.notificacionDAO = new NotificacionDAO();
    }

    public boolean createPedido(String titulo, String descripcion, LocalDateTime fechaCreacion, 
                                LocalDateTime fechaEntrega, int estadoId, int prioridadId, 
                                int clienteId, int usuarioId) {
        Pedido pedido = new Pedido(0, titulo, descripcion, fechaCreacion, fechaEntrega, estadoId, prioridadId, clienteId, usuarioId);
        return pedidoDAO.createPedido(pedido);
    }

    public boolean updatePedido(int id, String titulo, String descripcion, LocalDateTime fechaEntrega, 
                                 int estadoId, int prioridadId) {
        Pedido pedido = new Pedido(id, titulo, descripcion, null, fechaEntrega, estadoId, prioridadId, 0, 0);
        return pedidoDAO.updatePedido(pedido);
    }

    public boolean deletePedido(int id) {
        return pedidoDAO.deletePedido(id);
    }

    public boolean changeEstado(int id, int estadoId) {
        Pedido pedido = pedidoDAO.getPedidoById(id);
        if (pedido != null) {
            pedido.setEstadoId(estadoId);
            return pedidoDAO.updatePedido(pedido);
        }
        return false;
    }

    public boolean updatePrioridad(int id, int prioridadId) {
        Pedido pedido = pedidoDAO.getPedidoById(id);
        if (pedido != null) {
            pedido.setPrioridadId(prioridadId);
            return pedidoDAO.updatePedido(pedido);
        }
        return false;
    }

    public boolean addComentario(int pedidoId, int usuarioId, String contenido) {
        Comentario comentario = new Comentario(0, contenido, LocalDateTime.now(), usuarioId, pedidoId);
        return comentarioDAO.createComentario(comentario);
    }

    public boolean addArchivo(int pedidoId, String nombre, String tipo, String ruta, long tamano) {
        Archivo archivo = new Archivo(0, nombre, tipo, ruta, tamano, pedidoId);
        return archivoDAO.createArchivo(archivo);
    }

    public void checkTiempoRestante(Pedido pedido) {
        LocalDateTime fechaEntrega = pedido.getFechaEntrega();
        LocalDateTime ahora = LocalDateTime.now();

        if (fechaEntrega.minusHours(48).isBefore(ahora)) {
            String mensaje = "El pedido '" + pedido.getTitulo() + "' está cerca de vencer. Fecha de entrega: " + fechaEntrega;
            NotificacionController notificacionController = new NotificacionController();
            notificacionController.createNotificacion("Urgente: Pedido cerca de vencer", mensaje, "alerta", pedido.getUsuarioId());
        }
    }

    public List<Pedido> getAllPedidos() {
        return pedidoDAO.getAllPedidos();
    }

    public Pedido getPedidoById(int id) {
        return pedidoDAO.getPedidoById(id);
    }

    public void checkEstadoAtrasado(Pedido pedido) {
        if (pedido.getFechaEntrega().isBefore(LocalDateTime.now()) && pedido.getEstadoId() != 4) { // 4 = Estado "Atrasado"
            changeEstado(pedido.getId(), 4); // Cambiar a "Atrasado"
            String mensaje = "El pedido '" + pedido.getTitulo() + "' está atrasado. Fecha de entrega: " + pedido.getFechaEntrega();
            NotificacionController notificacionController = new NotificacionController();
            notificacionController.createNotificacion("Pedido Atrasado", mensaje, "alerta", pedido.getUsuarioId());
        }
    }

}
