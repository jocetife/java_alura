package com.aluracursos.screenmatch.service;
//CREADA EN EL VIDEO 1.4

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) { //metodo generico, acepta cualquier tipo de clase
        try {
            return mapper.readValue(json, clase); //convierte un JSON a un objeto de la clase especificada
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

