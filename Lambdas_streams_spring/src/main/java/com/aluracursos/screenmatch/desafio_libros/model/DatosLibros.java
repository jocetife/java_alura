package com.aluracursos.screenmatch.desafio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosLibros( 
    int count,
    @JsonAlias("results") List<DatosLibro> libros
) {}
