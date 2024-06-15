package com.aluracursos.literalura_desafio.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public ConsumoAPI() {
    }

    public String obtenerDatos(String url){
        //cliente
        HttpClient client = HttpClient.newHttpClient();

        //request
        URI direccion = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        //response
        HttpResponse<String> response = null;

        try{
            //System.out.println("Prueba response");
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response);
//            System.out.println(response.statusCode());
//            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}
