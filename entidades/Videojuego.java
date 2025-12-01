package entidades;

import java.util.Objects;

public class Videojuego implements Comparable<Videojuego> {

    // ==========================
    // Atributos (encapsulados)
    // ==========================
    private Integer id;
    private String titulo;
    private String genero;
    private Double precio;
    private boolean disponible;

    // ==========================
    // Constructores
    // ==========================

    // Constructor por defecto
    public Videojuego() {
        this.disponible = true; // Por defecto, el juego está disponible
    }

    // Constructor parametrizado
    public Videojuego(Integer id, String titulo, String genero, Double precio, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
        this.disponible = disponible;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // ==========================
    // Métodos propios
    // ==========================

    public void vender() {
        this.disponible = false;
    }

    public void reponer() {
        this.disponible = true;
    }

    // ==========================
    // Comparable — ordena por título
    // ==========================
    @Override
    public int compareTo(Videojuego otro) {
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    // ==========================
    // equals y hashCode — basados en ID
    // ==========================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Videojuego)) return false;
        Videojuego that = (Videojuego) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ==========================
    // Representación en texto
    // ==========================
    @Override
    public String toString() {
        return "Videojuego {" +
                "ID=" + id +
                ", Título='" + titulo + '\'' +
                ", Género='" + genero + '\'' +
                ", Precio=$" + precio +
                ", Disponible=" + (disponible ? "Sí" : "No") +
                '}';
    }
}

