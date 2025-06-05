package AgendaContactos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    private final String url = "jdbc:mysql://localhost:3306/agendacontactos";
    private final String usuario = "root";
    private final String password = "Root1234+";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }

//----------------------------------------------------------------------------------------------------------------------
    // Crear contacto.
//----------------------------------------------------------------------------------------------------------------------

    public void crearContacto(Contacto contacto) throws SQLException {
        String sql = "INSERT INTO contactos (nombre, telefono, email, ciudad, grupo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contacto.getNombre());
            stmt.setString(2, contacto.getTelefono());
            stmt.setString(3, contacto.getEmail());
            stmt.setString(4, contacto.getCiudad());
            stmt.setString(5, contacto.getGrupo());
            stmt.executeUpdate();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    // Leer los contactos.
//----------------------------------------------------------------------------------------------------------------------

    public Contacto leerContacto(String nombre) throws SQLException {
        String sql = "SELECT * FROM contactos WHERE nombre = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Contacto(
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("ciudad"),
                        rs.getString("grupo")
                );
            }
            return null;
        }
    }


    public List<Contacto> leerTodosContactos() throws SQLException {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Contacto(
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("ciudad"),
                        rs.getString("grupo")
                ));
            }
        }
        return lista;
    }

//----------------------------------------------------------------------------------------------------------------------
    // Actualizar contacto.
//----------------------------------------------------------------------------------------------------------------------

    public void actualizarContacto(Contacto contacto) throws SQLException {
        String sql = "UPDATE contactos SET telefono = ?, email = ?, ciudad = ?, grupo = ? WHERE nombre = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contacto.getTelefono());
            stmt.setString(2, contacto.getEmail());
            stmt.setString(3, contacto.getCiudad());
            stmt.setString(4, contacto.getGrupo());
            stmt.setString(5, contacto.getNombre()); // condición WHERE
            stmt.executeUpdate();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    // Borrar contactos.
//----------------------------------------------------------------------------------------------------------------------

    public void borrarContacto(String nombre) throws SQLException {
        String sql = "DELETE FROM contactos WHERE nombre = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    // Buscar contactos.
//----------------------------------------------------------------------------------------------------------------------

    private Contacto mapearFila(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String telefono = rs.getString("telefono");
        String email = rs.getString("email");
        String ciudad = rs.getString("ciudad");
        String grupo = rs.getString("grupo");

        return new Contacto(nombre, telefono, email, ciudad, grupo);
    }

    public List<Contacto> buscarPorNombre(String nombre) {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE nombre LIKE ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Contacto> ordenarPorNombre(boolean ascendente) {
        List<Contacto> lista = new ArrayList<>();
        String orden = ascendente ? "ASC" : "DESC";
        String sql = "SELECT * FROM contactos ORDER BY nombre " + orden;
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Contacto> buscarPorCiudad(String ciudad) {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE ciudad LIKE ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + ciudad + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Contacto> buscarPorGrupo(String grupo) {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE grupo LIKE ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + grupo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


}
