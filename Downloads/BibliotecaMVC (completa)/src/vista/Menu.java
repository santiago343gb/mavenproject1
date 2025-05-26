package vista;

import controlador.LibroControlador;
import controlador.LectorControlador;
import java.util.Scanner;

public class Menu {
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        LibroControlador libroCtrl = new LibroControlador();
        LectorControlador lectorCtrl = new LectorControlador();

        int opcion;
        do {
            System.out.println("\n=== Sistema de Gestión de Biblioteca ===");
            System.out.println("1. Gestionar Libros");
            System.out.println("2. Gestionar Lectores");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> libroCtrl.menu();
                case 2 -> lectorCtrl.menu();
                case 3 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 3);
    }
}
