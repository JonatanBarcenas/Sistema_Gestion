package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PublImpacto.SistemaGestion.model.Archivo;

public class ArchivoDAO {
	
	private Connection connection;

    public ArchivoDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createArchivo(Archivo archivo) {
        String sql = "INSERT INTO Archivo (nombre, tipo, ruta, tamano, pedido_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, archivo.getNombre());
            stmt.setString(2, archivo.getTipo());
            stmt.setString(3, archivo.getRuta());
            stmt.setLong(4, archivo.getTamano());
            stmt.setInt(5, archivo.getPedidoId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Archivo> getArchivosByPedido(int pedidoId) {
        List<Archivo> archivos = new ArrayList<>();
        String sql = "SELECT * FROM Archivo WHERE pedido_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Archivo archivo = new Archivo(
                        rs.getInt("id_archivo"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getString("ruta"),
                        rs.getLong("tamano"),
                        rs.getInt("pedido_id")
                );
                archivos.add(archivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return archivos;
    }

    public boolean deleteArchivo(int id) {
        String sql = "DELETE FROM Archivo WHERE id_archivo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
