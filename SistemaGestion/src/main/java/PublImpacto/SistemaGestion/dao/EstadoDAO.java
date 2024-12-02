package PublImpacto.SistemaGestion.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import PublImpacto.SistemaGestion.model.Estado;

public class EstadoDAO {
	private Connection connection;

    public EstadoDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createEstado(Estado estado) {
        String sql = "INSERT INTO Estado (nombre, color) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estado.getNombre());
            stmt.setString(2, estado.getColor());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT * FROM Estado";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estado estado = new Estado(
                        rs.getInt("id_estado"),
                        rs.getString("nombre"),
                        rs.getString("color")
                );
                estados.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }

    public boolean updateEstado(Estado estado) {
        String sql = "UPDATE Estado SET nombre = ?, color = ? WHERE id_estado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estado.getNombre());
            stmt.setString(2, estado.getColor());
            stmt.setInt(3, estado.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEstado(int id) {
        String sql = "DELETE FROM Estado WHERE id_estado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
