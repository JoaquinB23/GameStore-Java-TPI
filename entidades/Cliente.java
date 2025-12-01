package entidades;

public class Cliente extends Persona {

    private int idCliente;
    private String telefono;

    // Constructor vacío
    public Cliente() {
        super(); // llama al constructor vacío de Persona
    }

    // Constructor completo
    public Cliente(String nombre, int edad, String email,
                   int idCliente, String telefono) {
        super(nombre, edad, email); // inicializa atributos de Persona
        this.idCliente = idCliente;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // toString
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
