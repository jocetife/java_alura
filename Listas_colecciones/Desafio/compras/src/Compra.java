public class Compra {
    private String descripcion;
    private double valor;

    public Compra(String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getValor() {
        return valor;
    }
// Método para mostrar la compra como una cadena
    @Override
    public String toString() {
        return "Compra{" +
                "descripcion='" + descripcion + '\'' +
                ", valor=" + valor +
                '}';
    }
}
