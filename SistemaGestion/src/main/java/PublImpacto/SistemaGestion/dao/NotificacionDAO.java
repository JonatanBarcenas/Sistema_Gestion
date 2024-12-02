package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import PublImpacto.SistemaGestion.model.Notificacion;

public class NotificacionDAO {
	
	private Connection connection;

    public NotificacionDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createNotificacion(Notificacion notificacion) {
        String sql = "INSERT INTO Notificacion (titulo, mensaje, fecha, tipo, leida, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, notificacion.getTitulo());
            stmt.setString(2, notificacion.getMensaje());
            stmt.setTimestamp(3, Timestamp.valueOf(notificacion.getFecha()));
            stmt.setString(4, notificacion.getTipo());
            stmt.setBoolean(5, notificacion.isLeida());
            stmt.setInt(6, notificacion.getUsuarioId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Notificacion> getNotificacionesByUsuario(int usuarioId) {
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Notificacion WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion(
                        rs.getInt("id_notificacion"),
                        rs.getString("titulo"),
                        rs.getString("mensaje"),
                        rs.getTimestamp("fecha").toLocalDateTime(),
                        rs.getString("tipo"),
                        rs.getBoolean("leida"),
                        rs.getInt("usuario_id")
                );
                notificaciones.add(notificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificaciones;
    }

    public boolean markAsRead(int id) {
        String sql = "UPDATE Notificacion SET leida = TRUE WHERE id_notificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
