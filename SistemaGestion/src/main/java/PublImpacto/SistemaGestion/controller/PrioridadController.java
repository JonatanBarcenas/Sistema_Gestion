package PublImpacto.SistemaGestion.controller;

import java.util.List;

import PublImpacto.SistemaGestion.dao.PrioridadDAO;
import PublImpacto.SistemaGestion.model.Prioridad;

public class PrioridadController {
	
	private PrioridadDAO prioridadDAO;

    public PrioridadController() {
        this.prioridadDAO = new PrioridadDAO();
    }

    public boolean createPrioridad(String nivel, int valor) {
        Prioridad prioridad = new Prioridad(0, nivel, valor);
        return prioridadDAO.createPrioridad(prioridad);
    }

    public List<Prioridad> getAllPrioridades() {
        return prioridadDAO.getAllPrioridades();
    }

    public boolean updatePrioridad(int id, String nivel, int valor) {
        Prioridad prioridad = new Prioridad(id, nivel, valor);
        return prioridadDAO.updatePrioridad(prioridad);
    }

    public boolean deletePrioridad(int id) {
        return prioridadDAO.deletePrioridad(id);
    }

}
