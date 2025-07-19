public class Compra implements Comparable<Compra>  {
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
        return descripcion + " /$" + valor;
    }
    @Override
    public int compareTo(Compra otraCompra) {
        ///return Double.valueOf(this.valor).compareTo(Double.valueOf(otraCompra.getValor()));
        return Double.compare(this.valor, otraCompra.valor);
    }
}
