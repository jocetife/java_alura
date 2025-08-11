package com.aluracursos.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.dto.SerieDTO;
import java.util.stream.Collectors;


import java.util.List;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository repository;
    
    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries(){
        return repository.findAll().stream().map(s -> new SerieDTO(s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(), s.getGenero(), s.getActores(), s.getSinopsis())).collect(Collectors.toList());
    }

    @GetMapping("/inicio")
    public String muestraMensaje() {
        return "Probando LiveReloading lol";
    }
}
