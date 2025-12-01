package servicios;

import entidades.Videojuego;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ReporteService {

    // ------ PROMEDIO DE PRECIOS ------
    public double precioPromedio(List<Videojuego> lista) {
        if (lista.isEmpty()) return 0;

        return lista.stream()
                .mapToDouble(Videojuego::getPrecio)
                .average()
                .orElse(0);
    }

    // ------ PRECIO MÁXIMO ------
    public Videojuego precioMaximo(List<Videojuego> lista) {
        return lista.stream()
                .max(Comparator.comparingDouble(Videojuego::getPrecio))
                .orElse(null);
    }

    // ------ PRECIO MÍNIMO ------
    public Videojuego precioMinimo(List<Videojuego> lista) {
        return lista.stream()
                .min(Comparator.comparingDouble(Videojuego::getPrecio))
                .orElse(null);
    }

    // ------ CONTAR DISPONIBLES (disponible = true) ------
    public long contarDisponibles(List<Videojuego> lista) {
        return lista.stream()
                .filter(Videojuego::isDisponible)
                .count();
    }

    // ------ FILTRAR POR RANGO DE PRECIOS ------
    public List<Videojuego> filtrarPorPrecio(List<Videojuego> lista, double min, double max) {
        return lista.stream()
                .filter(v -> v.getPrecio() >= min && v.getPrecio() <= max)
                .collect(Collectors.toList());
    }
}
