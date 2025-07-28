package com.aluracursos.screenmatch.principal;

import java.util.List;
import java.util.Arrays;

public class EjemploStreams {
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Maria Fernanda", "Eric", "Genesys");
        //secuencia = stream, se puede usar con filter, sorted, map...
        nombres.stream().sorted().limit(2).filter(n -> n.startsWith("B")).map(n -> n.toUpperCase()).forEach(System.out::println);
    }
}
