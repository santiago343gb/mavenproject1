package controlador;

import modelo.*;
import java.util.Scanner;

public class LectorControlador {
    private LectorDAO dao = new LectorDAO();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Lectores ---");
            System.out.println("1. Agregar Lector");
            System.out.println("2. Listar Lectores");
            System.out.println("3. Editar Lector");
            System.out.println("4. Eliminar Lector");
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
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        dao.agregarLector(new Lector(0, nombre, email));
    }

    private void listar() {
        var lectores = dao.listarLectores();
        if (lectores.isEmpty()) {
            System.out.println("No hay lectores.");
        } else {
            lectores.forEach(l -> System.out.println(l.getId() + ": " + l.getNombre() + " - " + l.getEmail()));
        }
    }

    private void editar() {
        System.out.print("ID del lector a editar: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();

        dao.actualizarLector(new Lector(id, nombre, email));
    }

    private void eliminar() {
        System.out.print("ID del lector a eliminar: ");
        int id = scanner.nextInt();
        dao.eliminarLector(id);
    }
}
