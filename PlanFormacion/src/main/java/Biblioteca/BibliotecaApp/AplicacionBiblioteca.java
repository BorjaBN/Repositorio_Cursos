package Biblioteca.BibliotecaApp;


import Biblioteca.BibliotecaDAO.PrestamoDAO;
import Biblioteca.BibliotecaModelo.Libro;
import Biblioteca.BibliotecaModelo.Prestamo;
import Biblioteca.BibliotecaModelo.Usuario;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AplicacionBiblioteca {

    private static Scanner scanner = new Scanner(System.in);
    private static PrestamoDAO prestamoDAO = new PrestamoDAO();

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal de la Biblioteca ---");
            System.out.println("1. Registrar nuevo préstamo");
            System.out.println("2. Consultar todos los préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    registrarNuevoPrestamo();
                    break;
                case 2:
                    consultarTodosLosPrestamos();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next();
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    private static void registrarNuevoPrestamo() {
        System.out.println("\n--- Registrar Nuevo Préstamo ---");

        String isbn = solicitarISBN();
        if (isbn == null) return;

        int idUsuario = solicitarIdUsuario();
        if (idUsuario == -1) return;

        String fechaPrestamo = solicitarFechaPrestamo();
        if (fechaPrestamo == null) return;

        Prestamo nuevoPrestamo = new Prestamo(isbn, idUsuario, fechaPrestamo);

        if (prestamoDAO.registrarPrestamo(nuevoPrestamo)) {
            System.out.println("¡Préstamo registrado con éxito!");
        } else {
            System.out.println("Error al registrar el préstamo. Verifique los datos e intente de nuevo.");
        }
    }

    private static String solicitarISBN() {
        String isbn;
        do {
            System.out.print("Ingrese el ISBN del libro: ");
            isbn = scanner.nextLine().trim();

            if (isbn.isEmpty()) {
                System.out.println("El ISBN no puede estar vacío. Inténtelo de nuevo.");
            } else if (!prestamoDAO.verificarLibroExiste(isbn)) {
                System.out.println("Error: No existe ningún libro con el ISBN '" + isbn + "'.");
                System.out.print("¿Desea buscar el libro por título? (s/n): ");
                String opcionBusqueda = scanner.nextLine().trim().toLowerCase();
                if (opcionBusqueda.equals("s")) {
                    List<Libro> librosEncontrados = buscarLibroPorTitulo();
                    if (librosEncontrados.isEmpty()) {
                        System.out.println("No se encontraron libros. Volviendo al menú principal.");
                        return null;
                    } else {
                        System.out.println("Libros encontrados:");
                        for (int i = 0; i < librosEncontrados.size(); i++) {
                            System.out.println((i + 1) + ". " + librosEncontrados.get(i).getTitulo() + " (ISBN: " + librosEncontrados.get(i).getIsbn() + ")");
                        }
                        System.out.print("Ingrese el número del libro deseado o 0 para cancelar: ");
                        int seleccion = leerOpcion();
                        if (seleccion > 0 && seleccion <= librosEncontrados.size()) {
                            isbn = librosEncontrados.get(seleccion - 1).getIsbn();
                            return isbn;
                        } else {
                            System.out.println("Selección inválida o cancelada. Volviendo al menú principal.");
                            return null;
                        }
                    }
                } else {
                    return null;
                }
            } else {
                return isbn;
            }
        } while (true);
    }

    private static List<Libro> buscarLibroPorTitulo() {
        System.out.print("Ingrese parte del título del libro a buscar: ");
        String tituloParcial = scanner.nextLine().trim();
        return prestamoDAO.buscarLibrosPorTitulo(tituloParcial);
    }

    private static int solicitarIdUsuario() {
        int idUsuario;
        do {
            System.out.print("Ingrese el ID del usuario: ");
            try {
                idUsuario = Integer.parseInt(scanner.nextLine().trim());
                if (!prestamoDAO.verificarUsuarioExiste(idUsuario)) {
                    System.out.println("Error: No existe ningún usuario con el ID '" + idUsuario + "'.");
                    System.out.print("¿Desea buscar el usuario por nombre? (s/n): ");
                    String opcionBusqueda = scanner.nextLine().trim().toLowerCase();
                    if (opcionBusqueda.equals("s")) {
                        List<Usuario> usuariosEncontrados = buscarUsuarioPorNombre();
                        if (usuariosEncontrados.isEmpty()) {
                            System.out.println("No se encontraron usuarios. Volviendo al menú principal.");
                            return -1;
                        } else {
                            System.out.println("Usuarios encontrados:");
                            for (int i = 0; i < usuariosEncontrados.size(); i++) {
                                System.out.println((i + 1) + ". " + usuariosEncontrados.get(i).getNombre() + " (ID: " + usuariosEncontrados.get(i).getIdUsuario() + ")");
                            }
                            System.out.print("Ingrese el número del usuario deseado o 0 para cancelar: ");
                            int seleccion = leerOpcion();
                            if (seleccion > 0 && seleccion <= usuariosEncontrados.size()) {
                                idUsuario = usuariosEncontrados.get(seleccion - 1).getIdUsuario();
                                return idUsuario;
                            } else {
                                System.out.println("Selección inválida o cancelada. Volviendo al menú principal.");
                                return -1;
                            }
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return idUsuario;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número para el ID del usuario.");
            }
        } while (true);
    }

    private static List<Usuario> buscarUsuarioPorNombre() {
        System.out.print("Ingrese parte del nombre del usuario a buscar: ");
        String nombreParcial = scanner.nextLine().trim();
        return prestamoDAO.buscarUsuariosPorNombre(nombreParcial);
    }

    private static String solicitarFechaPrestamo() {
        String fechaStr;
        do {
            System.out.print("Ingrese la fecha del préstamo (YYYY-MM-DD): ");
            fechaStr = scanner.nextLine().trim();
            if (fechaStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return fechaStr;
            } else {
                System.out.println("Formato de fecha inválido. Use YYYY-MM-DD. Inténtelo de nuevo.");
            }
        } while (true);
    }

    private static void consultarTodosLosPrestamos() {
        System.out.println("\n--- Listado de Todos los Préstamos ---");
        List<String> prestamos = prestamoDAO.obtenerTodosLosPrestamosDetallados();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados actualmente.");
        } else {
            for (String prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }
}