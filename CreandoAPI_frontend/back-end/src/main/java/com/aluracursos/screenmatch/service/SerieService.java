package com.aluracursos.screenmatch.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerTodasLasSeries(){
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5(){
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerLanzamientosRecientes(){
        return convierteDatos(repository.lanzamientoMasReciente());
    }

    public SerieDTO obtenerPorId(Long id){
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(), s.getGenero(), s.getActores(), s.getSinopsis());
        } 
        return null; 
    }

    public List<SerieDTO> convierteDatos(List<Serie> serie){
        return serie.stream().map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(), s.getGenero(), s.getActores(), s.getSinopsis())).collect(Collectors.toList());
    }

    public List<EpisodioDTO> obtenerTodasLasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                .map(e -> new EpisodioDTO(e.getId(), e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
        } 
        return null;
    }

    // public List<EpisodioDTO> obtenerEpisodiosPorTemporada(Long id, Integer temporada) {
    //     Optional<Serie> serie = repository.findById(id);
    //     if (serie.isPresent()) {
    //         Serie s = serie.get();
    //         return s.getEpisodios().stream()
    //             .filter(e -> e.getTemporada().equals(temporada))
    //             .map(e -> new EpisodioDTO(e.getId(), e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
    //             .collect(Collectors.toList());
    //     }
    //     return null;
    // }

    public List<EpisodioDTO> obtenerEpisodiosPorTemporada(Long id, Integer temporada){
        return repository.obtenerEpisodiosPorTemporada(id, temporada).stream()
            .map(e -> new EpisodioDTO(e.getId(), e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
            .collect(Collectors.toList());
    }

    public List<SerieDTO> obtenerSeriesPorCategoria(String genero) {
        Categoria categoria = Categoria.fromEspanol(genero);
        return convierteDatos(repository.findByGenero(categoria));  
    }

}
