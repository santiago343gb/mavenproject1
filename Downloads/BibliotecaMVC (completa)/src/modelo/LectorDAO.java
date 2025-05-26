package modelo;

import java.sql.*;
import java.util.*;

public class LectorDAO {
    public void agregarLector(Lector lector) {
        String sql = "INSERT INTO lectores (nombre, email) VALUES (?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lector.getNombre());
            ps.setString(2, lector.getEmail());
            ps.executeUpdate();
            System.out.println("✅ Lector agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar lector: " + e.getMessage());
        }
    }

    public List<Lector> listarLectores() {
        List<Lector> lectores = new ArrayList<>();
        String sql = "SELECT * FROM lectores";
        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lectores.add(new Lector(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar lectores: " + e.getMessage());
        }
        return lectores;
    }

    public void actualizarLector(Lector lector) {
        String sql = "UPDATE lectores SET nombre = ?, email = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lector.getNombre());
            ps.setString(2, lector.getEmail());
            ps.setInt(3, lector.getId());
            ps.executeUpdate();
            System.out.println("✅ Lector actualizado.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar lector: " + e.getMessage());
        }
    }

    public void eliminarLector(int id) {
        String sql = "DELETE FROM lectores WHERE id = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Lector eliminado.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar lector: " + e.getMessage());
        }
    }
}
