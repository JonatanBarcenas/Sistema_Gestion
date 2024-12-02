package PublImpacto.SistemaGestion.controller;

import java.util.List;

import PublImpacto.SistemaGestion.dao.RolDAO;
import PublImpacto.SistemaGestion.model.Rol;

public class RolController {
	
	private RolDAO rolDAO;

    public RolController() {
        this.rolDAO = new RolDAO();
    }

    public boolean createRol(String nombre) {
        Rol rol = new Rol(0, nombre);
        return rolDAO.createRol(rol);
    }

    public List<Rol> getAllRoles() {
        return rolDAO.getAllRoles();
    }

    public Rol getRolById(int id) {
        return rolDAO.getRolById(id);
    }

    public boolean updateRol(int id, String nombre) {
        Rol rol = new Rol(id, nombre);
        return rolDAO.updateRol(rol);
    }

    public boolean deleteRol(int id) {
        return rolDAO.deleteRol(id);
    }

}
