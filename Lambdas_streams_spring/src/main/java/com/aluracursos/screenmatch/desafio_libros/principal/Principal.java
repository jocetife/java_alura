package com.aluracursos.screenmatch.desafio_libros.principal;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;

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
        if (datos.libros().isEmpty()) {
            System.out.println("No se encontraron libros con el nombre: " + nombreLibro);
        } else {
            for (int i = 0; i < datos.libros().size(); i++) {
                DatosLibro libro = datos.libros().get(i);
                libros.add(libro);

                libros.forEach(l-> {
                System.out.println("--> Titulo: " + l.titulo() 
                                    + ", ID: " + l.id()
                                    + ", Numero de descargas: " + l.numeroDeDescargas()
                                    + "\n--> Autor: " + l.autor()
                                    + ", Lenguajes: " + l.lenguajes()
                                    + "\n--> Sinopsis: " + l.sinopsis() + "\n-------------------");
                });
            }
        }

        //Top 10 libros mas descargados
        System.out.println("\n\nTop 10 libros mas descargados (primera pagina):\n");
        DatosLibros general = conversor.obtenerDatos(jsonTop, DatosLibros.class);
        List<DatosLibro> topLibros = general.libros();
        topLibros.stream()
            .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
            .limit(10)
            .forEach(libro -> {
            System.out.println("--> Titulo: " + libro.titulo()+ "\n-->Descargas: " + libro.numeroDeDescargas()+ "\n");
            });
        System.out.println("--------------------------------------");

        //estadisticas
        System.out.println("Estadisticas de: "+ nombreLibro);
        IntSummaryStatistics estbusq = datos.libros()
            .stream()
            .collect(Collectors.summarizingInt(DatosLibro::numeroDeDescargas));
        System.out.println(estbusq);
        System.out.println("--------------------------------------\n Estadisticas generales: ");
        IntSummaryStatistics est = general.libros()
            .stream()
            .collect(Collectors.summarizingInt(DatosLibro::numeroDeDescargas));
            System.out.println(est);
    }
}
