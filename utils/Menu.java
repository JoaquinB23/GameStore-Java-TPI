package utils;

import entidades.Videojuego;
import servicios.ServicioVideojuego;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ServicioVideojuego servicio = new ServicioVideojuego();

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {

                case 1 -> agregarVideojuego();
                case 2 -> buscarVideojuego();
                case 3 -> modificarVideojuego();
                case 4 -> eliminarVideojuego();
                case 5 -> servicio.listarVideojuegos();
                case 6 -> ordenarPorTitulo();
                case 7 -> ordenarPorPrecio();
                case 8 -> filtrarPorGenero();
                case 9 -> listarDisponibles();
                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    scanner.close();   //  ✔ CIERRE REAL DEL SCANNER
                }
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===== MENÚ DE VIDEOJUEGOS =====");
        System.out.println("1. Agregar videojuego");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Modificar videojuego");
        System.out.println("4. Eliminar videojuego");
        System.out.println("5. Listar todos");
        System.out.println("6. Ordenar por título");
        System.out.println("7. Ordenar por precio");
        System.out.println("8. Filtrar por género");
        System.out.println("9. Listar disponibles");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private void agregarVideojuego() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Género: ");
            String genero = scanner.nextLine();

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Disponible? (true/false): ");
            boolean disponible = Boolean.parseBoolean(scanner.nextLine());

            Videojuego v = new Videojuego(id, titulo, genero, precio, disponible);

            if (servicio.agregarVideojuego(v)) {
                System.out.println("Videojuego agregado correctamente.");
            }

        } catch (Exception e) {
            System.out.println("Error al ingresar datos. Intente nuevamente.");
        }
    }

    private void buscarVideojuego() {
        System.out.print("Ingrese ID a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Videojuego v = servicio.buscarPorId(id);

        if (v != null) {
            System.out.println("Encontrado: " + v);
        }
    }

    private void modificarVideojuego() {
        try {
            System.out.print("ID del videojuego a modificar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Nuevo título (enter para omitir): ");
            String titulo = scanner.nextLine();
            if (titulo.isEmpty()) titulo = null;

            System.out.print("Nuevo género (enter para omitir): ");
            String genero = scanner.nextLine();
            if (genero.isEmpty()) genero = null;

            System.out.print("Nuevo precio (0 para omitir): ");
            double precio = Double.parseDouble(scanner.nextLine());
            Double precioObj = precio > 0 ? precio : null;

            System.out.print("Disponible? (true/false, vacío para omitir): ");
            String disp = scanner.nextLine();
            Boolean disponible = disp.isEmpty() ? null : Boolean.parseBoolean(disp);

            if (servicio.modificarVideojuego(id, titulo, genero, precioObj, disponible)) {
                System.out.println("Modificado correctamente.");
            }

        } catch (Exception e) {
            System.out.println("Error al modificar.");
        }
    }

    private void eliminarVideojuego() {
        System.out.print("ID del videojuego a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (servicio.eliminarVideojuego(id)) {
            System.out.println("Eliminado correctamente.");
        }
    }

    private void ordenarPorTitulo() {
        servicio.ordenarPorTitulo();
        System.out.println("Ordenado por título.");
    }

    private void ordenarPorPrecio() {
        servicio.ordenarPorPrecio();
        System.out.println("Ordenado por precio.");
    }

    private void filtrarPorGenero() {
        System.out.print("Género a filtrar: ");
        String genero = scanner.nextLine();
        servicio.filtrarPorGenero(genero);
    }

    private void listarDisponibles() {
        System.out.println("Videojuegos disponibles:");
        servicio.listarDisponibles();
    }
}
