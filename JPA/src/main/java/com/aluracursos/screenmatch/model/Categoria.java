package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION("Action", "Accion"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");

    private String categoriaOmdb;
    private String categoriaEspanol;
    
    Categoria(String categoriaOmdb, String categoriaEspanol) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString(String text){
        for (Categoria c : Categoria.values()) {
            if (c.categoriaOmdb.equalsIgnoreCase(text)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No se encontro la categoria: " + text);
    }

    public static Categoria fromEspanol (String text){
        for (Categoria c : Categoria.values()) {
            if (c.categoriaEspanol.equalsIgnoreCase(text)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No se encontro la categoria: " + text);
    }

}
