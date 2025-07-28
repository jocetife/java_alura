package com.aluracursos.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

public class Principal {
    
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=4fc7c187";              //final significa constante
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraelMenu(){
        System.out.print("Por favor escribe el nombre de la serie que deseas buscar: ");
        //datos de la serie
        var nombreSerie = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println("----------------------");
        System.out.println(datos);
        System.out.println("----------------------");
        //datos de las temporadas
        List<DatosTemporada> datosTemporadas = new ArrayList<>();
		for (int i = 1; i <= datos.totalTemporadas(); i++) {
			json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
			DatosTemporada temporada = conversor.obtenerDatos(json, DatosTemporada.class);
			datosTemporadas.add(temporada);
		}
		//datosTemporadas.forEach(System.out::println);
        //System.out.println("----------------------");

        //mostrar solo el titulo de los episodios para las temporadas
        /*for (int i = 0; i < datos.totalTemporadas(); i++) {
            List<DatosEpisodio> episodios = datosTemporadas.get(i).episodios();
            System.out.println("\nTemporada " + datosTemporadas.get(i).numero() + ":\n");
            for (int j = 0; j < episodios.size(); j++) {
                System.out.println(episodios.get(j).titulo() + " - " + episodios.get(j).evaluacion()); 
            }
        }*/
        //mas o menos lo mismo que los for anteriores
        //datosTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo()+ " - " + e.evaluacion())));

        List<DatosEpisodio> datosEpisodios = datosTemporadas.stream()
                .flatMap(t -> t.episodios().stream()) 
                .collect(Collectors.toList()); 

        //Top5
        System.out.println("\nTop 5 de episodios con mejor calificación:");
        datosEpisodios.stream().filter(e -> !e.evaluacion().equalsIgnoreCase("N/A")).sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed()).limit(5).forEach(System.out::println);

        List<Episodio> episodios = datosTemporadas.stream().flatMap(t -> t.episodios().stream()
            .map(d -> new Episodio (t.numero(), d))).collect(Collectors.toList());

        episodios.forEach(System.out::println);
    }
}
