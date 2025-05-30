package BibliotecaDAO;


import BibliotecaConexion.ConexionBD;
import BibliotecaModelo.Libro;
import BibliotecaModelo.Usuario;
import BibliotecaModelo.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    /**
     * Registra un nuevo préstamo en la base de datos.
     * @param prestamo El objeto Prestamo que queremos guardar.
     * @return 'true' si se guardó bien, 'false' si hubo un error.
     */
    public boolean registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO Prestamos (ISBN, IdUsuario, FechaPrestamo) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, prestamo.getIsbnLibro());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setString(3, prestamo.getFechaPrestamo());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar préstamo: " + e.getMessage());
            if (e.getSQLState().startsWith("23")) {
                System.err.println("Error: El ISBN del libro o el ID del usuario no existe en la base de datos.");
            }
            return false;
        } finally {
            ConexionBD.close(conn, stmt);
        }
    }

    /**
     * Obtiene una lista de todos los préstamos con detalles de libro y usuario.
     * @return Una lista de texto (String) con el resumen de cada préstamo.
     */
    public List<String> obtenerTodosLosPrestamosDetallados() {
        List<String> prestamosDetallados = new ArrayList<>();
        String sql = "SELECT p.IdPrestamo, l.Titulo, l.ISBN, u.Nombre, u.Telefono, p.FechaPrestamo " +
                "FROM Prestamos p " +
                "JOIN Libros l ON p.ISBN = l.ISBN " +
                "JOIN Usuarios u ON p.IdUsuario = u.IdUsuario " +
                "ORDER BY p.FechaPrestamo DESC";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idPrestamo = rs.getInt("IdPrestamo");
                String tituloLibro = rs.getString("Titulo");
                String isbnLibro = rs.getString("ISBN");
                String nombreUsuario = rs.getString("Nombre");
                String telefonoUsuario = rs.getString("Telefono");
                String fechaPrestamo = rs.getString("FechaPrestamo");

                String detalle = String.format(
                        "Préstamo ID: %d | Libro: '%s' (ISBN: %s) | Usuario: '%s' (Tel: %s) | Fecha: %s",
                        idPrestamo, tituloLibro, isbnLibro, nombreUsuario, telefonoUsuario, fechaPrestamo
                );
                prestamosDetallados.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener préstamos detallados: " + e.getMessage());
        } finally {
            ConexionBD.close(conn, stmt, rs);
        }
        return prestamosDetallados;
    }

    /**
     * Verifica si un ISBN de libro existe en la tabla 'Libros'.
     * @param isbn El ISBN a buscar.
     * @return 'true' si el libro existe, 'false' si no.
     */
    public boolean verificarLibroExiste(String isbn) {
        String sql = "SELECT COUNT(*) FROM Libros WHERE ISBN = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de libro: " + e.getMessage());
        } finally {
            ConexionBD.close(conn, stmt, rs);
        }
        return false;
    }

    /**
     * Verifica si un ID de usuario existe en la tabla 'Usuarios'.
     * @param idUsuario El ID a buscar.
     * @return 'true' si el usuario existe, 'false' si no.
     */
    public boolean verificarUsuarioExiste(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE IdUsuario = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de usuario: " + e.getMessage());
        } finally {
            ConexionBD.close(conn, stmt, rs);
        }
        return false;
    }

    /**
     * Busca usuarios por una parte de su nombre.
     * @param nombreParcial Parte del nombre a buscar.
     * @return Una lista de objetos Usuario que coinciden.
     */
    public List<Usuario> buscarUsuariosPorNombre(String nombreParcial) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT IdUsuario, Nombre, Telefono FROM Usuarios WHERE Nombre LIKE ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nombreParcial + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("IdUsuario"), rs.getString("Nombre"), rs.getString("Telefono")));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuarios: " + e.getMessage());
        } finally {
            ConexionBD.close(conn, stmt, rs);
        }
        return usuarios;
    }

    /**
     * Busca libros por una parte de su título.
     * @param tituloParcial Parte del título a buscar.
     * @return Una lista de objetos Libro que coinciden.
     */
    public List<Libro> buscarLibrosPorTitulo(String tituloParcial) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT ISBN, Titulo, Categoria FROM Libros WHERE Titulo LIKE ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + tituloParcial + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                libros.add(new Libro(rs.getString("ISBN"), rs.getString("Titulo"), rs.getString("Categoria")));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar libros: " + e.getMessage());
        } finally {
            ConexionBD.close(conn, stmt, rs);
        }
        return libros;
    }
}