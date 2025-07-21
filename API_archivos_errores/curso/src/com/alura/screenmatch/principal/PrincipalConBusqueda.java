package com.alura.screenmatch.principal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.alura.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        //buenas practicas, mantener la variables en minúsculas en el record
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {
                System.out.println("Escriba el nombre de una pelicula: ");
                var busqueda = lectura.nextLine();

                if (busqueda.equalsIgnoreCase("salir")) {
                        System.out.println("Saliendo del programa...");
                        break;
                }
                String direccion = "https://www.omdbapi.com/?t="+busqueda.replace(" ", "+")+"&apikey=cf92ee14";

                try{
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(direccion))
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());

                        String json = response.body();
                        System.out.println(json);
                        
                        TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                        System.out.println(miTituloOmdb);
                
                        Titulo miTitulo = new Titulo(miTituloOmdb);
                        System.out.println("Titulo convertido: " + miTitulo);

                        titulos.add(miTitulo);
                }
                catch (NumberFormatException e){
                        System.out.println("Error, verifique la duracion: ");
                        System.out.println(e.getMessage());
                }
                catch (IllegalArgumentException e){
                        System.out.println("Error en la URI, verifique la direccion: ");
                        System.out.println(e.getMessage());
                }
                catch (ErrorEnConversionDeDuracionException e){
                        System.out.println(e.getMessage());
                }
        }
        lectura.close();
        System.out.println(titulos);

        FileWriter escritor = new FileWriter(new File("titulos.json"));
        escritor.write(gson.toJson(titulos));
        escritor.close();

        System.out.println("Finalizo la ejecucion del programa");
    }
}
