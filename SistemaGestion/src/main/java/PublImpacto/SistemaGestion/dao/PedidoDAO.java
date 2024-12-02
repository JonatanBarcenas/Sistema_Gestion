package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import PublImpacto.SistemaGestion.model.Pedido;

public class PedidoDAO {

	private Connection connection;

    public PedidoDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido (titulo, descripcion, fecha_creacion, fecha_entrega, estado_id, prioridad_id, cliente_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pedido.getTitulo());
            stmt.setString(2, pedido.getDescripcion());
            stmt.setTimestamp(3, Timestamp.valueOf(pedido.getFechaCreacion()));
            stmt.setTimestamp(4, Timestamp.valueOf(pedido.getFechaEntrega()));
            stmt.setInt(5, pedido.getEstadoId());
            stmt.setInt(6, pedido.getPrioridadId());
            stmt.setInt(7, pedido.getClienteId());
            stmt.setInt(8, pedido.getUsuarioId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pedido> getAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                        rs.getTimestamp("fecha_entrega").toLocalDateTime(),
                        rs.getInt("estado_id"),
                        rs.getInt("prioridad_id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("usuario_id")
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public boolean updatePedido(Pedido pedido) {
        String sql = "UPDATE Pedido SET titulo = ?, descripcion = ?, fecha_entrega = ?, estado_id = ?, prioridad_id = ? WHERE id_pedido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pedido.getTitulo());
            stmt.setString(2, pedido.getDescripcion());
            stmt.setTimestamp(3, Timestamp.valueOf(pedido.getFechaEntrega()));
            stmt.setInt(4, pedido.getEstadoId());
            stmt.setInt(5, pedido.getPrioridadId());
            stmt.setInt(6, pedido.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePedido(int id) {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pedido getPedidoById(int id) {
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                        rs.getTimestamp("fecha_entrega").toLocalDateTime(),
                        rs.getInt("estado_id"),
                        rs.getInt("prioridad_id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("usuario_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
