package com.aluracursos.screenmatch.model;

import java.util.OptionalDouble;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private Double evaluacion;
    private String poster;
    private Categoria genero;
    private String actores;
    private String sinopsis;

    public Serie(DatosSerie datos) {
        this.titulo = datos.titulo();
        this.totalTemporadas = datos.totalTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datos.evaluacion())).orElse(0.0);
        this.poster = datos.poster();
        this.genero = Categoria.fromString(datos.genero().split(",")[0].trim());
        this.actores = datos.actores();
        this.sinopsis = datos.sinopsis();
    }

    //getters y setters
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }
    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }
    public Double getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public Categoria getGenero() {
        return genero;
    }
    public void setGenero(Categoria genero) {
        this.genero = genero;
    }
    public String getActores() {
        return actores;
    }
    public void setActores(String actores) {
        this.actores = actores;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }    
}
