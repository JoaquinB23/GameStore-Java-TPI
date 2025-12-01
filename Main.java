import servicios.ServicioVideojuego;
import servicios.ReporteService;
import entidades.Videojuego;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ServicioVideojuego servicioVideojuego = new ServicioVideojuego();
    private static final ReporteService reporteService = new ReporteService();

    public static void main(String[] args) {
        System.out.println("===== GAMESTORE - SISTEMA DE GESTIÓN =====");
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> submenuVideojuegos();
                case 2 -> mostrarReportes();
                case 3 -> cargarDatosDeEjemplo();
                case 0 -> System.out.println("Saliendo. ¡Hasta luego!");
                default -> System.out.println("Opción inválida. Intentá de nuevo.");
            }
        } while (opcion != 0);
    }

    // ---------- Menú principal ----------
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Gestión de Videojuegos");
        System.out.println("2. Reportes");
        System.out.println("3. Cargar datos de ejemplo");
        System.out.println("0. Salir");
    }

    // ---------- Submenú Videojuegos ----------
    private static void submenuVideojuegos() {
        int opcion;
        do {
            mostrarMenuVideojuegos();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> agregarVideojuego();
                case 2 -> listarVideojuegos();
                case 3 -> buscarVideojuegoPorId();
                case 4 -> modificarVideojuego();
                case 5 -> eliminarVideojuego();
                case 6 -> ordenarPorTitulo();
                case 7 -> ordenarPorPrecio();
                case 8 -> filtrarPorGenero();
                case 9 -> listarDisponibles();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenuVideojuegos() {
        System.out.println("\n--- GESTIÓN DE VIDEOJUEGOS ---");
        System.out.println("1. Agregar videojuego");
        System.out.println("2. Listar videojuegos");
        System.out.println("3. Buscar videojuego por ID");
        System.out.println("4. Modificar videojuego");
        System.out.println("5. Eliminar videojuego");
        System.out.println("6. Ordenar por título");
        System.out.println("7. Ordenar por precio");
        System.out.println("8. Filtrar por género");
        System.out.println("9. Listar disponibles");
        System.out.println("0. Volver");
    }

    // ---------- Operaciones ----------
    private static void agregarVideojuego() {
        System.out.println("\n--- AGREGAR VIDEOJUEGO ---");
        int id = leerEntero("ID: ");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        double precio = leerDouble("Precio: ");
        boolean disponible = leerBoolean("Disponible? (s/n): ");

        Videojuego v = new Videojuego(id, titulo, genero, precio, disponible);
        if (servicioVideojuego.agregarVideojuego(v)) {
            System.out.println("Videojuego agregado correctamente.");
        } else {
            System.out.println("No se pudo agregar el videojuego.");
        }
    }

    private static void listarVideojuegos() {
        System.out.println("\n--- LISTA DE VIDEOJUEGOS ---");
        servicioVideojuego.listarVideojuegos();
    }

    private static void buscarVideojuegoPorId() {
        System.out.println("\n--- BUSCAR VIDEOJUEGO ---");
        int id = leerEntero("ID: ");
        Videojuego v = servicioVideojuego.buscarPorId(id);
        if (v != null) {
            System.out.println("Encontrado: " + v);
        } else {
            System.out.println("No se encontró el videojuego solicitado.");
        }
    }

    private static void modificarVideojuego() {
        System.out.println("\n--- MODIFICAR VIDEOJUEGO ---");
        int id = leerEntero("ID a modificar: ");
        System.out.print("Nuevo título (ENTER para dejar igual): ");
        String titulo = scanner.nextLine();
        if (titulo.isBlank()) titulo = null;
        System.out.print("Nuevo género (ENTER para dejar igual): ");
        String genero = scanner.nextLine();
        if (genero.isBlank()) genero = null;
        String precioStr;
        System.out.print("Nuevo precio (ENTER para dejar igual): ");
        precioStr = scanner.nextLine();
        Double precio = precioStr.isBlank() ? null : Double.parseDouble(precioStr);
        Boolean disponible = null;
        System.out.print("Cambiar disponibilidad? (s=si / ENTER=mantener): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            disponible = leerBoolean("Disponible? (s/n): ");
        }

        boolean mod = servicioVideojuego.modificarVideojuego(id, titulo, genero, precio, disponible);
        if (mod) System.out.println("Videojuego modificado.");
        else System.out.println("No se pudo modificar (ID inexistente).");
    }

    private static void eliminarVideojuego() {
        System.out.println("\n--- ELIMINAR VIDEOJUEGO ---");
        int id = leerEntero("ID: ");
        if (servicioVideojuego.eliminarVideojuego(id)) {
            System.out.println("Videojuego eliminado.");
        } else {
            System.out.println("No se encontró el videojuego para eliminar.");
        }
    }

    private static void ordenarPorTitulo() {
        System.out.println("\nOrdenando por título...");
        servicioVideojuego.ordenarPorTitulo();
        System.out.println("Ordenado por título.");
    }

    private static void ordenarPorPrecio() {
        System.out.println("\nOrdenando por precio...");
        servicioVideojuego.ordenarPorPrecio();
        System.out.println("Ordenado por precio.");
    }

    private static void filtrarPorGenero() {
        System.out.println("\n--- FILTRAR POR GÉNERO ---");
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        servicioVideojuego.filtrarPorGenero(genero);
    }

    private static void listarDisponibles() {
        System.out.println("\n--- VIDEOJUEGOS DISPONIBLES ---");
        servicioVideojuego.listarDisponibles();
    }

    // ---------- Reportes ----------
    private static void mostrarReportes() {
        System.out.println("\n--- REPORTES ---");
        List<Videojuego> lista = servicioVideojuego.obtenerVideojuegos();
        double promedio = reporteService.precioPromedio(lista);
        long disponibles = reporteService.contarDisponibles(lista);

        System.out.printf("Precio promedio: %.2f%n", promedio);
        System.out.println("Cantidad disponibles: " + disponibles);

        System.out.print("¿Filtrar por rango de precio ahora? (s/n): ");
        String r = scanner.nextLine();
        if (r.equalsIgnoreCase("s")) {
            double min = leerDouble("Precio mínimo: ");
            double max = leerDouble("Precio máximo: ");
            List<Videojuego> filtrados = reporteService.filtrarPorPrecio(lista, min, max);
            System.out.println("Resultados del filtrado:");
            for (Videojuego v : filtrados) {
                System.out.println(v);
            }
        }
    }

    // ---------- Datos de ejemplo ----------
    private static void cargarDatosDeEjemplo() {
        System.out.println("\nCargando datos de ejemplo...");
        servicioVideojuego.agregarVideojuego(new Videojuego(1, "Hollow Knight", "Metroidvania", 1499.0, true));
        servicioVideojuego.agregarVideojuego(new Videojuego(2, "Stardew Valley", "Simulación", 999.0, true));
        servicioVideojuego.agregarVideojuego(new Videojuego(3, "Celeste", "Plataforma", 1299.0, false));
        servicioVideojuego.agregarVideojuego(new Videojuego(4, "The Witcher 3", "RPG", 1999.0, true));
        servicioVideojuego.agregarVideojuego(new Videojuego(5, "Among Us", "Multijugador", 299.0, true));
        System.out.println("Datos cargados.");
    }

    // ---------- Helpers de lectura ----------
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingresá un número entero.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String line = scanner.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingresá un número (decimales con punto).");
            }
        }
    }

    private static boolean leerBoolean(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String r = scanner.nextLine().trim();
            if (r.equalsIgnoreCase("s") || r.equalsIgnoreCase("si") || r.equalsIgnoreCase("y") || r.equalsIgnoreCase("yes"))
                return true;
            if (r.equalsIgnoreCase("n") || r.equalsIgnoreCase("no")) return false;
            System.out.println("Entrada inválida. Respondé 's' o 'n'.");
 }
    }
}