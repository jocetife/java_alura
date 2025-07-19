import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    
        // Crear una instancia de Tarjeta con un límite de 1000.0
        System.out.println("Ingrese el límite de la tarjeta: ");
        Scanner scanner = new Scanner(System.in);

        double limite = scanner.nextDouble();
        Tarjeta tarjeta = new Tarjeta(limite);
        
        while(true){
            System.out.println("Ingrese el nombre de la compra: ");
            String descripcion = scanner.next();

            System.out.println("Ingrese el valor de la compra: ");
            double valor = scanner.nextDouble();

            Compra compra = new Compra(descripcion, valor);

            if(tarjeta.lanzarCompra(compra)) {
                System.out.println("Compra realizada!" + compra);
                System.out.println("Saldo actual: " + tarjeta.getSaldo());
                System.out.println("Límite de la tarjeta: " + tarjeta.getLimite());
                System.out.println("desea ver el historial de compras? (si/no)");
                String respuesta = scanner.next();
                if(respuesta.equalsIgnoreCase("si")) {
                    System.out.println("Historial de compras: " + tarjeta.getListaDeCompras());
                }
            } else {
                System.out.println("Saldo insuficiente para realizar la compra. Su lista de compras es: " + tarjeta.getListaDeCompras());
                break;
            }
        }

        scanner.close();
    }
}

