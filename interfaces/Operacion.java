package interfaces;

@FunctionalInterface
public interface Operacion<T> {
    void ejecutar(T t);
}
