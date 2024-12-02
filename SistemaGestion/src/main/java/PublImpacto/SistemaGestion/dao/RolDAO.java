package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import PublImpacto.SistemaGestion.model.Rol;

public class RolDAO {

	private Connection connection;

    public RolDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createRol(Rol rol) {
        String sql = "INSERT INTO Rol (nombre) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, rol.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Rol> getAllRoles() {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT * FROM Rol";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Rol rol = new Rol(
                        rs.getInt("id_rol"),
                        rs.getString("nombre")
                );
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public boolean updateRol(Rol rol) {
        String sql = "UPDATE Rol SET nombre = ? WHERE id_rol = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, rol.getNombre());
            stmt.setInt(2, rol.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRol(int id) {
        String sql = "DELETE FROM Rol WHERE id_rol = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Rol getRolById(int id) {
        String sql = "SELECT * FROM Rol WHERE id_rol = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Rol(
                        rs.getInt("id_rol"),
                        rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	
}
