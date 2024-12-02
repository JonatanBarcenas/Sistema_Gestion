package PublImpacto.SistemaGestion.controller;

import java.util.List;

import PublImpacto.SistemaGestion.dao.UsuarioDAO;
import PublImpacto.SistemaGestion.model.Usuario;
import PublImpacto.SistemaGestion.utils.PasswordUtils;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean createUsuario(String nombre, String email, String password, int rolId) {
        String passwordHash = PasswordUtils.encryptPassword(password); 
        Usuario usuario = new Usuario(0, nombre, email, passwordHash, rolId);
        return usuarioDAO.createUsuario(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.getAllUsuarios();
    }

    public boolean updateUsuario(int id, String nombre, String email, String password, int rolId) {
        String passwordHash = PasswordUtils.encryptPassword(password);
        Usuario usuario = new Usuario(id, nombre, email, passwordHash, rolId);
        return usuarioDAO.updateUsuario(usuario);
    }

    public boolean deleteUsuario(int id) {
        return usuarioDAO.deleteUsuario(id);
    }

    public Usuario authenticate(String email, String password) {
        Usuario usuario = usuarioDAO.authenticate(email, password);
        if (usuario != null && PasswordUtils.verifyPassword(password, usuario.getPassword())) {
            return usuario; // 
        }
        return null; // 
    }

}
