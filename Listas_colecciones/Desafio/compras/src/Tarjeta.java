import java.util.List;
import java.util.ArrayList;

public class Tarjeta {
    //declarados sin inicializar
    private double limite;
    private double saldo;
    private List<Compra> listaDeCompras;

    public Tarjeta(double limite){
        this.limite = limite;
        this.saldo = limite;
        this.listaDeCompras = new ArrayList<>();
    }
//si la compra se puede realizar, se descuenta del saldo y se agrega a la lista de compras
    public boolean lanzarCompra(Compra compra) {
        if (this.saldo >= compra.getValor()) {
            this.saldo -= compra.getValor();
            this.listaDeCompras.add(compra);
            return true;
        }
        return false;
    }
    public double getLimite() {
        return limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Compra> getListaDeCompras() {
        return listaDeCompras;
    }
}
