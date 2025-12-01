package utils;

import entidades.Videojuego;
import java.util.Comparator;

public class ComparadorPorPrecio implements Comparator<Videojuego> {

    @Override
    public int compare(Videojuego v1, Videojuego v2) {
        // Comparación segura usando Double
        return Double.compare(v1.getPrecio(), v2.getPrecio());
    }
}
