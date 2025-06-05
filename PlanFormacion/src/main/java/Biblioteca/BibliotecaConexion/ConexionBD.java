package Biblioteca.BibliotecaConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Credenciales de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; // Usuario de MySQL
    private static final String CLAVE = "Root1234+"; // Contraseña

    // Este bloque se ejecuta una vez cuando el programa empieza para cargar el "controlador"
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC de MySQL no encontrado. Asegúrate de haberlo añadido en pom.xml.");
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el driver JDBC de MySQL.");
        }
    }

    /**
     * Este método te da la "conexión" a la base de datos.
     * Es como abrir la puerta a la BD.
     * @return Un objeto Connection si se conecta bien.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Intenta conectar usando la URL, usuario y clave
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            // Si hay un error, lo vuelve a lanzar para que quien llamó se entere.
            throw e;
        }
    }

    /**
     * Cierra la conexión y otros "recursos" de forma segura.
     * Es como cerrar la puerta de la BD para no dejarla abierta.
     */
    public static void close(Connection conn, java.sql.Statement stmt, java.sql.ResultSet rs) {
        try {
            if (rs != null) rs.close(); // Si hay "resultados" abiertos, los cierra
            if (stmt != null) stmt.close(); // Si hay "sentencias" abiertas, las cierra
            if (conn != null) conn.close(); // Si la conexión está abierta, la cierra
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos de la base de datos: " + e.getMessage());
        }
    }

    public static void close(Connection conn, java.sql.Statement stmt) {
        close(conn, stmt, null); // Versión simplificada para cerrar solo conexión y sentencia
    }

    public static void close(Connection conn) {
        close(conn, null, null); // Versión más simple para cerrar solo la conexión
    }
}