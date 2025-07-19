import java.util.Collections;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    
        // Crear una instancia de Tarjeta con un límite de 1000.0
        System.out.println("Ingrese el límite de la tarjeta: ");
        Scanner scanner = new Scanner(System.in);

        double limite = scanner.nextDouble();
        Tarjeta tarjeta = new Tarjeta(limite);
        
        while(true){
            if (tarjeta.getSaldo() == 0) {
                Collections.sort(tarjeta.getListaDeCompras());
                System.out.println("Ya no tiene saldo en la tarjeta.");
                System.out.println("Historial de compras: ");
                for (Compra compraf : tarjeta.getListaDeCompras()) {
                    System.out.println("--> "+ compraf);
                }
                break;
            }
            System.out.println("Ingrese el nombre de la compra: ");
            String descripcion = scanner.next();

            System.out.println("Ingrese el valor de la compra: ");
            double valor = scanner.nextDouble();

            Compra compra = new Compra(descripcion, valor);
            boolean lanzar = tarjeta.lanzarCompra(compra); 
            
            if(lanzar) {
                System.out.println("Compra realizada! ---->" + compra);
                System.out.println("Saldo actual: " + tarjeta.getSaldo());
            }
            else {
                Collections.sort(tarjeta.getListaDeCompras());
                System.out.println("Saldo insuficiente para realizar la compra. Su lista de compras es: ");
                System.out.println("Historial de compras: ");
                for (Compra compraf : tarjeta.getListaDeCompras()) {
                    System.out.println("--> "+ compraf);
                }
                break;
            }
        }

        scanner.close();
    }
}

