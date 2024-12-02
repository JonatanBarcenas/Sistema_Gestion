package PublImpacto.SistemaGestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import PublImpacto.SistemaGestion.model.Prioridad;

public class PrioridadDAO {

	private Connection connection;

    public PrioridadDAO() {
        this.connection = ConexionDB.getConnection();
    }

    public boolean createPrioridad(Prioridad prioridad) {
        String sql = "INSERT INTO Prioridad (nivel, valor) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prioridad.getNivel());
            stmt.setInt(2, prioridad.getValor());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Prioridad> getAllPrioridades() {
        List<Prioridad> prioridades = new ArrayList<>();
        String sql = "SELECT * FROM Prioridad";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prioridad prioridad = new Prioridad(
                        rs.getInt("id_prioridad"),
                        rs.getString("nivel"),
                        rs.getInt("valor")
                );
                prioridades.add(prioridad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prioridades;
    }

    public boolean updatePrioridad(Prioridad prioridad) {
        String sql = "UPDATE Prioridad SET nivel = ?, valor = ? WHERE id_prioridad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, prioridad.getNivel());
            stmt.setInt(2, prioridad.getValor());
            stmt.setInt(3, prioridad.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePrioridad(int id) {
        String sql = "DELETE FROM Prioridad WHERE id_prioridad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
