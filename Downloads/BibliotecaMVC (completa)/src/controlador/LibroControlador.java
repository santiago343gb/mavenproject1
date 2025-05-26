package controlador;

import modelo.*;
import java.util.Scanner;

public class LibroControlador {
    private LibroDAO dao = new LibroDAO();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Libros ---");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Editar Libro");
            System.out.println("4. Eliminar Libro");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregar();
                case 2 -> listar();
                case 3 -> editar();
                case 4 -> eliminar();
            }
        } while (opcion != 5);
    }

    private void agregar() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = scanner.nextInt();

        dao.agregarLibro(new Libro(0, titulo, autor, anio));
    }

    private void listar() {
        var libros = dao.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
        } else {
            libros.forEach(l -> System.out.println(l.getId() + ": " + l.getTitulo() + " - " + l.getAutor() + " (" + l.getAnioPublicacion() + ")"));
        }
    }

    private void editar() {
        System.out.print("ID del libro a editar: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nuevo título: ");
        String titulo = scanner.nextLine();
        System.out.print("Nuevo autor: ");
        String autor = scanner.nextLine();
        System.out.print("Nuevo año: ");
        int anio = scanner.nextInt();

        dao.actualizarLibro(new Libro(id, titulo, autor, anio));
    }

    private void eliminar() {
        System.out.print("ID del libro a eliminar: ");
        int id = scanner.nextInt();
        dao.eliminarLibro(id);
    }
}
