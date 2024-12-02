package PublImpacto.SistemaGestion.controller;

import java.util.List;

import PublImpacto.SistemaGestion.dao.EstadoDAO;
import PublImpacto.SistemaGestion.model.Estado;

public class EstadoController {
	
	private EstadoDAO estadoDAO;

    public EstadoController() {
        this.estadoDAO = new EstadoDAO();
    }

    public boolean createEstado(String nombre, String color) {
        Estado estado = new Estado(0, nombre, color);
        return estadoDAO.createEstado(estado);
    }

    public List<Estado> getAllEstados() {
        return estadoDAO.getAllEstados();
    }

    public boolean updateEstado(int id, String nombre, String color) {
        Estado estado = new Estado(id, nombre, color);
        return estadoDAO.updateEstado(estado);
    }

    public boolean deleteEstado(int id) {
        return estadoDAO.deleteEstado(id);
    }

}
