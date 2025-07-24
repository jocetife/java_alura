package com.aluracursos.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obtenerTraduccion(String texto) {
        //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
        
        OpenAiService service = new OpenAiService("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");

        CompletionRequest requisicion = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduce a español el siguiente texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var respuesta = service.createCompletion(requisicion);
        return respuesta.getChoices().get(0).getText();
    }
}
