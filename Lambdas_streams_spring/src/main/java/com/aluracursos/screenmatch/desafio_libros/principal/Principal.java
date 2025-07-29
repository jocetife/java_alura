package com.aluracursos.screenmatch.desafio_libros.principal;

import java.util.Scanner;

import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import com.aluracursos.screenmatch.desafio_libros.model.DatosLibro;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public void muestraelMenu() {
        System.out.print("Por favor escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        //DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        System.out.println("----------------------");
        System.out.println(json);
        System.out.println("----------------------");
    }
    public void lol(){
        System.out.println("a ver si muy brgas");
    }
}
