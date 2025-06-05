import static org.junit.jupiter.api.Assertions.*;

import AgendaContactos.Contacto;
import org.junit.jupiter.api.*;
import AgendaContactos.ContactoApp;

import java.sql.*;

public class ContactoAppTest {

    private ContactoApp app;

    @BeforeEach
    public void setup() throws SQLException {
        app = new ContactoApp();
        limpiarTablaContactos();
    }

    private void limpiarTablaContactos() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/agendacontactos"; // Cambia aquí
        String usuario = "root";
        String password = "Root1234+";
        try (Connection conn = DriverManager.getConnection(url, usuario, password);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM contactos");
        }
    }

    @Test
    public void testCrearYLeerContacto() {
        Contacto c = new Contacto("Ana", "123456789", "ana@mail.com", "Madrid", "Amigos");
        app.crearContacto(c);

        Contacto encontrado = app.leerContacto("Ana");
        assertNotNull(encontrado);
        assertEquals("Ana", encontrado.getNombre());
        assertEquals("123456789", encontrado.getTelefono());
    }

    @Test
    public void testLeerTodosContactos() {
        app.crearContacto(new Contacto("Ana", "123456789", "ana@mail.com", "Madrid", "Amigos"));
        app.crearContacto(new Contacto("Luis", "987654321", "luis@mail.com", "Barcelona", "Trabajo"));

        var contactos = app.leerTodosContactos();
        assertEquals(2, contactos.size());
    }

    @Test
    public void testActualizarContacto() {
        Contacto c = new Contacto("Ana", "123456789", "ana@mail.com", "Madrid", "Amigos");
        app.crearContacto(c);

        Contacto actualizado = new Contacto("Ana", "111222333", "ana_new@mail.com", "Valencia", "Familia");
        app.actualizarContacto(actualizado);

        Contacto encontrado = app.leerContacto("Ana");
        assertEquals("111222333", encontrado.getTelefono());
        assertEquals("ana_new@mail.com", encontrado.getEmail());
        assertEquals("Valencia", encontrado.getCiudad());
        assertEquals("Familia", encontrado.getGrupo());
    }

    @Test
    public void testBorrarContacto() {
        Contacto c = new Contacto("Luis", "987654321", "luis@mail.com", "Barcelona", "Trabajo");
        app.crearContacto(c);

        app.borrarContacto("Luis");
        Contacto encontrado = app.leerContacto("Luis");
        assertNull(encontrado);
    }
}
