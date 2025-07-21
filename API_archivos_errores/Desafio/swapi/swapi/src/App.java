import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ConsultaPelicula consulta = new ConsultaPelicula();
        System.out.println("Escriba el numero de la película que desea consultar: ");
        try{
            var numeroPelicula = Integer.valueOf(scanner.nextLine());
            Pelicula pelicula = consulta.buscaPelicula(numeroPelicula);
            System.out.println(pelicula);
            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(pelicula);
        }catch (NumberFormatException e) {
            System.out.println("El caracter ingresado no es valido, favor de ingresar un numero entero.");
        }catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
        }
        scanner.close();
        System.out.println("Aplicación finalizada.");
    }
}
