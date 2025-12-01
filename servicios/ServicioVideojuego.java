package servicios;

import entidades.Videojuego;
import excepciones.ElementoNoEncontradoException;
import utils.ComparadorPorPrecio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioVideojuego {

    private List<Videojuego> videojuegos = new ArrayList<>();

    // Agregar videojuego
    public boolean agregarVideojuego(Videojuego v) {
        if (v == null) return false;
        videojuegos.add(v);
        return true;
    }

    // Buscar por ID con recursividad
    public Videojuego buscarPorId(int id) {
        Videojuego encontrado = buscarRecursivo(id, 0);

        if (encontrado == null) {
            ElementoNoEncontradoException e =
                    new ElementoNoEncontradoException("Videojuego con ID " + id + " no encontrado.");
            System.out.println(e.getMessage());
        }

        return encontrado;
    }

    // Método recursivo
    private Videojuego buscarRecursivo(int id, int indice) {
        if (indice >= videojuegos.size()) return null;

        Videojuego actual = videojuegos.get(indice);
        if (actual.getId() == id) return actual;

        return buscarRecursivo(id, indice + 1);
    }

    // Eliminar videojuego
    public boolean eliminarVideojuego(int id) {
        Iterator<Videojuego> it = videojuegos.iterator();

        while (it.hasNext()) {
            Videojuego v = it.next();
            if (v.getId() == id) {
                it.remove();
                return true;
            }
        }

        ElementoNoEncontradoException e =
                new ElementoNoEncontradoException("No se pudo eliminar. ID " + id + " inexistente.");
        System.out.println(e.getMessage());

        return false;
    }

    // Modificar videojuego (parcial)
    public boolean modificarVideojuego(int id, String titulo,
                                       String genero, Double precio,
                                       Boolean disponible) {

        Videojuego v = buscarRecursivo(id, 0);

        if (v == null) {
            ElementoNoEncontradoException e =
                    new ElementoNoEncontradoException("No se pudo modificar. ID " + id + " inexistente.");
            System.out.println(e.getMessage());
            return false;
        }

        if (titulo != null && !titulo.isEmpty()) v.setTitulo(titulo);
        if (genero != null && !genero.isEmpty()) v.setGenero(genero);
        if (precio != null && precio > 0) v.setPrecio(precio);
        if (disponible != null) v.setDisponible(disponible);

        return true;
    }

    // Listar
    public void listarVideojuegos() {
        if (videojuegos.isEmpty()) {
            System.out.println("No hay videojuegos cargados.");
            return;
        }

        videojuegos.forEach(v -> System.out.println(v));
    }

    // Ordenar por título usando Selection Sort
    public void ordenarPorTitulo() {
        int n = videojuegos.size();

        for (int i = 0; i < n - 1; i++) {
            int indiceMin = i;

            for (int j = i + 1; j < n; j++) {
                String t1 = videojuegos.get(j).getTitulo();
                String t2 = videojuegos.get(indiceMin).getTitulo();

                if (t1.compareToIgnoreCase(t2) < 0) {
                    indiceMin = j;
                }
            }

            if (indiceMin != i) {
                Videojuego aux = videojuegos.get(i);
                videojuegos.set(i, videojuegos.get(indiceMin));
                videojuegos.set(indiceMin, aux);
            }
        }
    }

    // Ordenar por precio usando el comparator
    public void ordenarPorPrecio() {
        videojuegos.sort(new ComparadorPorPrecio());
    }

    // Filtrar por género
    public void filtrarPorGenero(String genero) {
        for (Videojuego v : videojuegos) {
            if (v.getGenero().equalsIgnoreCase(genero)) {
                System.out.println(v);
            }
        }
    }

    // Filtrar disponibles
    public void listarDisponibles() {
        for (Videojuego v : videojuegos) {
            if (v.isDisponible()) {
                System.out.println(v);
            }
        }
    }

    // Obtener lista
    public List<Videojuego> obtenerVideojuegos() {
        return videojuegos;
    }
}
