package com.aluracursos.screenmatch.desafio_libros.principal;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import com.aluracursos.screenmatch.desafio_libros.model.DatosLibros;
import com.aluracursos.screenmatch.desafio_libros.model.DatosLibro;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE_BUSQUEDA = "https://gutendex.com/books/?search=";
    private final String URL_BASE = "https://gutendex.com/books/";

    public void muestraelMenu() {
        System.out.print("Por favor escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE_BUSQUEDA + nombreLibro.replace(" ", "%20"));
        var jsonTop = consumoApi.obtenerDatos(URL_BASE);
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        System.out.println("----------------------");
        System.out.println("fueron encontrados " + datos.count()+ " libros");
        System.out.println("----------------------\nprimera pagina:\n");
        //System.out.println(json);

        List<DatosLibro> libros = new ArrayList<>();
        for (int i = 0; i < datos.libros().size(); i++) {
            DatosLibro libro = datos.libros().get(i);
            libros.add(libro);
        }

        libros.forEach(libro-> {
            System.out.println("--> Titulo: " + libro.titulo() 
                                + ", ID: " + libro.id()
                                + ", Numero de descargas: " + libro.numeroDeDescargas()
                                + "\n--> Autor: " + libro.autor()
                                + ", Lenguajes: " + libro.lenguajes()
                                + "\n--> Sinopsis: " + libro.sinopsis() + "\n-------------------");
        });

        //Top 10 libros mas descargados
        System.out.println("\n\nTop 10 libros mas descargados (primera pagina):\n");
        DatosLibros topDatosLibros = conversor.obtenerDatos(jsonTop, DatosLibros.class);
        List<DatosLibro> topLibros = topDatosLibros.libros();
        topLibros.stream()
            .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
            .limit(10)
            .forEach(libro -> {
            System.out.println("--> Titulo: " + libro.titulo()+ "\n-->Descargas: " + libro.numeroDeDescargas()+ "\n");
            });
    }
}
