package com.aluracursos.screenmatch.desafio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import java.util.Optional;
import java.util.ArrayList;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") ArrayList<DatosPersona> autor,
    @JsonAlias("languages") ArrayList<String> lenguajes,
    @JsonAlias("download_count") Integer numeroDeDescargas,
    @JsonAlias("id") String id,
    @JsonAlias("summaries") ArrayList<String> sinopsis
) {}
