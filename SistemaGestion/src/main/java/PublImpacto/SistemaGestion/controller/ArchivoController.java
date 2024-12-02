package PublImpacto.SistemaGestion.controller;

import PublImpacto.SistemaGestion.dao.ArchivoDAO;
import PublImpacto.SistemaGestion.model.Archivo;

public class ArchivoController {
	
	private ArchivoDAO archivoDAO;

    public ArchivoController() {
        this.archivoDAO = new ArchivoDAO();
    }

    public boolean createArchivo(Archivo archivo) {
        return archivoDAO.createArchivo(archivo);
    }

    public boolean deleteArchivo(int id) {
        return archivoDAO.deleteArchivo(id);
    }

}
