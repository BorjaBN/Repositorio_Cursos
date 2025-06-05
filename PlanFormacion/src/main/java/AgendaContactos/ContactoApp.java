package AgendaContactos;

import java.sql.SQLException;
import java.util.List;

public class ContactoApp {
    private ContactoDAO dao = new ContactoDAO();

    public void crearContacto(Contacto contacto) {
        try {
            dao.crearContacto(contacto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contacto leerContacto(String nombre) {
        try {
            return dao.leerContacto(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contacto> leerTodosContactos() {
        try {
            return dao.leerTodosContactos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarContacto(Contacto contacto) {
        try {
            dao.actualizarContacto(contacto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarContacto(String nombre) {
        try {
            dao.borrarContacto(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exportarContactos(String rutaArchivo) {
        try {
            List<Contacto> contactos = dao.leerTodosContactos();
            ExportadorContactos exportador = new ExportadorContactos();
            exportador.exportarContactosJson(contactos, rutaArchivo);
            System.out.println("Exportación completada: " + rutaArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contacto> buscarContactosPorNombre(String nombre) {
        return dao.buscarPorNombre(nombre);
    }

    public List<Contacto> ordenarContactosPorNombre(boolean ascendente) {
        return dao.ordenarPorNombre(ascendente);
    }

    public List<Contacto> buscarContactosPorCiudad(String ciudad) {
        return dao.buscarPorCiudad(ciudad);
    }

    public List<Contacto> buscarContactosPorGrupo(String grupo) {
        return dao.buscarPorGrupo(grupo);
    }

}
