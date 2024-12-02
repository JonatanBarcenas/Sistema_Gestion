package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import PublImpacto.SistemaGestion.model.Comentario;

public class ComentarioDAO {
	
	private Connection connection;

    public ComentarioDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createComentario(Comentario comentario) {
        String sql = "INSERT INTO Comentario (contenido, fecha, usuario_id, pedido_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comentario.getContenido());
            stmt.setTimestamp(2, Timestamp.valueOf(comentario.getFecha()));
            stmt.setInt(3, comentario.getUsuarioId());
            stmt.setInt(4, comentario.getPedidoId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comentario> getComentariosByPedido(int pedidoId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM Comentario WHERE pedido_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comentario comentario = new Comentario(
                        rs.getInt("id_comentario"),
                        rs.getString("contenido"),
                        rs.getTimestamp("fecha").toLocalDateTime(),
                        rs.getInt("usuario_id"),
                        rs.getInt("pedido_id")
                );
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    public boolean deleteComentario(int id) {
        String sql = "DELETE FROM Comentario WHERE id_comentario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
