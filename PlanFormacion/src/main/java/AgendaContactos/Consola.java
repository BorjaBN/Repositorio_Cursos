package AgendaContactos;

import java.util.List;

import java.util.List;

public class Consola {
    public static void main(String[] args) {
        ContactoApp app = new ContactoApp();

        // Crear contactos de prueba
        app.crearContacto(new Contacto("Pepe", "111222333", "pepe@mail.com", "Sevilla", "Amigos"));
        app.crearContacto(new Contacto("Ana", "123456789", "ana@mail.com", "Madrid", "Trabajo"));

        // Exportar a JSON
        app.exportarContactos("contactos.json");

        // Buscar por nombre
        System.out.println("Buscando 'Pepe':");
        List<Contacto> encontrados = app.buscarContactosPorNombre("Pepe");
        for (Contacto c : encontrados) {
            System.out.println(c.getNombre() + " - " + c.getEmail());
        }

        // Ordenar A-Z
        System.out.println("Contactos ordenados por nombre (A-Z):");
        List<Contacto> ordenados = app.ordenarContactosPorNombre(true);
        for (Contacto c : ordenados) {
            System.out.println(c.getNombre());
        }
    }
}
