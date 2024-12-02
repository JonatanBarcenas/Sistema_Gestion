package PublImpacto.SistemaGestion.controller;

import java.util.List;

import PublImpacto.SistemaGestion.dao.ComentarioDAO;
import PublImpacto.SistemaGestion.model.Comentario;

public class ComentarioController {
	
	private ComentarioDAO comentarioDAO;

    public ComentarioController() {
        this.comentarioDAO = new ComentarioDAO();
    }

    public boolean createComentario(Comentario comentario) {
        return comentarioDAO.createComentario(comentario);
    }

    public List<Comentario> getComentariosByPedido(int pedidoId) {
        return comentarioDAO.getComentariosByPedido(pedidoId);
    }

    public boolean deleteComentario(int id) {
        return comentarioDAO.deleteComentario(id);
    }

}
