package com.aluracursos.screenmatch.principal;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Optional;

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

        System.out.println("----------------------");
        //Todos los objetos Episodio
        List<Episodio> episodios = datosTemporadas.stream().flatMap(t -> t.episodios().stream()
            .map(d -> new Episodio (t.numero(), d))).collect(Collectors.toList());

        episodios.forEach(System.out::println);
        System.out.println("----------------------");
        
        //Busqueda de episodios por fecha
        /*System.out.println("Indica el año a partir del cual deseas buscar los episodios (formato YYYY): ");
        var fecha = scanner.nextInt();
        scanner.nextLine(); //salto de línea pendiente

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream().filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .sorted(Comparator.comparing(Episodio::getFechaDeLanzamiento))
                .forEach(e -> System.out.println(
                    "Temporada: " + e.getTemporada() + ", Episodio: " + e.getNumeroEpisodio() + ", Título: " + e.getTitulo() + ", Fecha de lanzamiento: " + e.getFechaDeLanzamiento().format(dtf) + ", Evaluación: " + e.getEvaluacion()));*/

        //Episodios por titulo, el que mas se asemeje 
        System.out.println("Indica el título del episodio que deseas buscar: ");
        var pedazoTitulo = scanner.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream().filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase())).findFirst();

        if(episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado: " + episodioBuscado.get());
        }else {
            System.out.println("No se encontro ningun episodio con ese titulo.");
        }
    }
}
