package modelo;

import java.sql.*;
import java.util.*;

public class LibroDAO {
    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getAnioPublicacion());
            ps.executeUpdate();
            System.out.println("✅ Libro agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar libro: " + e.getMessage());
        }
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                libros.add(new Libro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("anio_publicacion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar libros: " + e.getMessage());
        }
        return libros;
    }

    public void actualizarLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio_publicacion = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getAnioPublicacion());
            ps.setInt(4, libro.getId());
            ps.executeUpdate();
            System.out.println("✅ Libro actualizado.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Libro eliminado.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar libro: " + e.getMessage());
        }
    }
}
