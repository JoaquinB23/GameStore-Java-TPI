package excepciones;

// Excepción personalizada para indicar que un elemento no fue encontrado
public class ElementoNoEncontradoException extends Exception {

    // Constructor que recibe un mensaje personalizado
    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    // Constructor por defecto
    public ElementoNoEncontradoException() {
        super("El elemento solicitado no fue encontrado.");
    }
}
