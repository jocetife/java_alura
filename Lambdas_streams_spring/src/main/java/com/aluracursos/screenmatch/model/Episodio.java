package com.aluracursos.screenmatch.model;

import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;

    public Episodio(Integer temporada, DatosEpisodio datosEpisodio) {
        this.temporada = temporada;
        this.titulo = datosEpisodio.titulo();
        this.numeroEpisodio = datosEpisodio.numEpisodio();
        try {
            this.evaluacion = Double.valueOf(datosEpisodio.evaluacion());
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0; // o manejar el error de otra manera
        }
        this.fechaDeLanzamiento = LocalDate.parse(datosEpisodio.fechaDeLanzamiento());
    }

    //getters y setters
    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titutlo) {
        this.titulo = titutlo;
    }
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    public Double getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }
    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }
    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    @Override
    public String toString() {
        return "Temporada: " + temporada + ", Titulo: " + titulo + ", Episodio: " + numeroEpisodio +
            ", Evaluación: " + evaluacion + ", Fecha de Lanzamiento: " + fechaDeLanzamiento;
    }

}
