package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import PublImpacto.SistemaGestion.model.RegistroActividad;

public class RegistroActividadDAO {
	
	private Connection connection;

    public RegistroActividadDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createRegistroActividad(RegistroActividad registroActividad) {
        String sql = "INSERT INTO Registro_actividad (accion, fecha_hora, usuario_id, detalles) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, registroActividad.getAccion());
            stmt.setTimestamp(2, Timestamp.valueOf(registroActividad.getFechaHora()));
            stmt.setInt(3, registroActividad.getUsuarioId());
            stmt.setString(4, registroActividad.getDetalles());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RegistroActividad> getAllRegistros() {
        List<RegistroActividad> registros = new ArrayList<>();
        String sql = "SELECT * FROM Registro_actividad";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                RegistroActividad registro = new RegistroActividad(
                        rs.getInt("id_actividad"),
                        rs.getString("accion"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getInt("usuario_id"),
                        rs.getString("detalles")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

}
