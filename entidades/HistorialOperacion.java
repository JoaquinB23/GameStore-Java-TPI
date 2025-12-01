    package entidades;

    import utils.ListaEnlazada;

    public class HistorialOperacion {

        private ListaEnlazada<String> historial = new ListaEnlazada<>();

        // Registrar una operación
        public void registrar(String operacion) {
            historial.agregar(operacion);
        }

        // Mostrar historial
        public void mostrar() {
            historial.mostrar();
        }
    }
