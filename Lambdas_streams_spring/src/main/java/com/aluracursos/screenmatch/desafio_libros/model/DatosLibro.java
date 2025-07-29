package com.aluracursos.screenmatch.desafio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") String autor,
    @JsonAlias("languages") String lenguajes,
    @JsonAlias("download_count") String numeroDeDescargas,
    @JsonAlias("id") String id,
    @JsonAlias("summaries") String sinopsis
) {}
