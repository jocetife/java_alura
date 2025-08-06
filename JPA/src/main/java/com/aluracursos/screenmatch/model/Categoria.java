package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIMEN("Crime");

    private String categoriaOmdb;
    
    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text){
        for (Categoria c : Categoria.values()) {
            if (c.categoriaOmdb.equalsIgnoreCase(text)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No se encontró la categoría: " + text);
    }

}
